package com.deofis.tiendaapirest.productos.services;

import com.deofis.tiendaapirest.productos.domain.Producto;
import com.deofis.tiendaapirest.productos.domain.PropiedadProducto;
import com.deofis.tiendaapirest.productos.domain.Sku;
import com.deofis.tiendaapirest.productos.domain.ValorPropiedadProducto;
import com.deofis.tiendaapirest.productos.repositories.ProductoRepository;
import com.deofis.tiendaapirest.productos.repositories.SkuRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class GeneradorSkusImpl implements GeneradorSkus {

    private final SkuRepository skuRepository;
    private final ProductoRepository productoRepository;

    @Override
    public Integer generarSkusProducto(Producto producto) {

        // El producto no tiene propiedades.
        if (CollectionUtils.isEmpty(producto.getPropiedades())) return -1;

        List<List<ValorPropiedadProducto>> todasCombinaciones = this.generarCombinaciones(0,
                new ArrayList<>(), producto.getPropiedades());

        // No se generaron combinaciones.
        if (todasCombinaciones  == null) return -2;

        log.info("Total de combinaciones: " + todasCombinaciones.size());

        // Si algún SKU del producto tiene valores asignados, se los agrega al array de combinaciones
        // previamente generadas.
        List<List<ValorPropiedadProducto>> combinacionesPreviamenteGeneradas = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(producto.getSkus())) {
            for (Sku skuAdicional: producto.getSkus()) {
                if (CollectionUtils.isNotEmpty(skuAdicional.getValores())) {
                    combinacionesPreviamenteGeneradas.add(skuAdicional.getValores());
                }
            }
        }

        // Verifica, por cada combinación de todas las combinaciones, si alguna combinacion previamente
        // generada es la misma que la combinación de todas las combinaciones, para evitar agregarla
        // nuevamente.

        // Nuestro array de combinaciones a generar es efectivamente el que posee todas las combinaciones que
        // SI serán generadas. Difiere de todasCombinaciones en que este ultimo posee todas las combinaciones
        // sin tener en cuenta si ya fue o no generada alguna combinacion.
        List<List<ValorPropiedadProducto>> combinacionesAGenerar = new ArrayList<>();
        for (List<ValorPropiedadProducto> combinacion: todasCombinaciones) {
            boolean generadaPreviamente = false;
            for (List<ValorPropiedadProducto> combinacionGenerada: combinacionesPreviamenteGeneradas) {
                if (this.esMismaCombinacion(combinacion, combinacionGenerada)) {
                    generadaPreviamente = true;
                    break;
                }
            }
            if (!generadaPreviamente) combinacionesAGenerar.add(combinacion);
        }

        log.info("Total de combinaciones a generar: " + combinacionesAGenerar.size());
        int cantCombinacionesCreadas = 0;

        if (CollectionUtils.isNotEmpty(combinacionesAGenerar)) {
            // Persistimos en la bae de datos todas las combinaciones generadas, tanto el producto
            // como cada uno de los SKUs generados y asociados a dicho producto.
            cantCombinacionesCreadas = this.persistirCombinaciones(producto, combinacionesAGenerar);
        }

        log.info("Combinaciones generadas: " + cantCombinacionesCreadas);
        return cantCombinacionesCreadas;
    }

    @Override
    public List<List<ValorPropiedadProducto>> generarCombinaciones(Integer indexActual,
                                                                   List<ValorPropiedadProducto> combinacionActual,
                                                                   List<PropiedadProducto> propiedades) {

        List<List<ValorPropiedadProducto>> combinaciones = new ArrayList<>();
        if (indexActual == propiedades.size()) {
            combinaciones.add(combinacionActual);
            return combinaciones;
        }

        PropiedadProducto propiedadActual = propiedades.get(indexActual);
        List<ValorPropiedadProducto> valoresPermitidos = propiedadActual.getValores();
        // Si la propiedad del producto no es variable (no se generan skus a partir de esta), entonces
        // no la agregamos: ignoramos esta propiedad y pasamos a generar las combinaciones para la propiedad
        // siguiente.
        if (!propiedadActual.isVariable()) {
            combinaciones.addAll(this.generarCombinaciones(indexActual + 1, combinacionActual, propiedades));
            return combinaciones;
        }

        // Si no existe ni si quiera un valor en la propiedad, retorna null (indicando que no tiene valores).
        if (propiedadActual.getValores().isEmpty()) return null;

        for (ValorPropiedadProducto valor: valoresPermitidos) {
            List<ValorPropiedadProducto> combinacion = new ArrayList<>(combinacionActual);
            combinacion.add(valor);
            combinaciones.addAll(this.generarCombinaciones(indexActual + 1, combinacion, propiedades));
        }

        if (valoresPermitidos.size() == 0) {
            combinaciones.addAll(this.generarCombinaciones(indexActual + 1, combinacionActual, propiedades));
        }

        return combinaciones;
    }

    @Override
    public boolean esMismaCombinacion(List<ValorPropiedadProducto> combinacion1,
                                      List<ValorPropiedadProducto> combinacion2) {

        if (combinacion1.size() == combinacion2.size()) {
            Collection<Long> combinacion1Ids = CustomCollectionUtils.collect(combinacion1, input -> ((ValorPropiedadProducto) input).getId());

            Collection<Long> combinacion2Ids =CustomCollectionUtils.collect(combinacion2, input -> ((ValorPropiedadProducto) input).getId());

            return combinacion1Ids.containsAll(combinacion2Ids);
        }

        return false;
    }

    @Transactional
    @Override
    public Integer persistirCombinaciones(Producto producto, List<List<ValorPropiedadProducto>> combinaciones) {
        int cantCombinacionesCreadas = 0;

        for (List<ValorPropiedadProducto> combinacion: combinaciones) {
            if (combinacion.isEmpty()) continue;
            Sku skuCombinado = Sku.builder()
                    .nombre(producto.getNombre())
                    .descripcion(producto.getDescripcion())
                    .precio(producto.getPrecio())
                    .disponibilidad(0)
                    .fechaCreacion(new Date())
                    .foto(null)
                    .defaultProducto(null)
                    .producto(producto)
                    .valores(combinacion).build();

            String valoresData = "";
            for (ValorPropiedadProducto valor: combinacion) {
                valoresData = valoresData.concat(valor.getValor().concat("/"));
            }
            skuCombinado.setValoresData(valoresData);

            Sku skuGuardado = this.skuRepository.save(skuCombinado);
            producto.getSkus().add(skuGuardado);
            cantCombinacionesCreadas++;
        }

        if (cantCombinacionesCreadas != 0) this.productoRepository.save(producto);

        return cantCombinacionesCreadas;
    }

    private interface TypedTransformer<K> extends Transformer<Object, K> {
        K transform(Object var1);
    }

    private static class CustomCollectionUtils {

        public CustomCollectionUtils() {

        }

        public static <T> Collection<T> collect(Collection inputCollection, TypedTransformer<T> transformer) {
            return CollectionUtils.collect(inputCollection, transformer);
        }
    }
}
