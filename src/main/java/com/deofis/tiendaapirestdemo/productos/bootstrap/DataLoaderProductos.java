package com.deofis.tiendaapirestdemo.productos.bootstrap;

import com.deofis.tiendaapirestdemo.productos.domain.Categoria;
import com.deofis.tiendaapirestdemo.productos.domain.Subcategoria;
import com.deofis.tiendaapirestdemo.productos.domain.UnidadMedida;
import com.deofis.tiendaapirestdemo.productos.exceptions.ProductoException;
import com.deofis.tiendaapirestdemo.productos.repositories.CategoriaRepository;
import com.deofis.tiendaapirestdemo.productos.repositories.UnidadMedidaRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DataLoaderProductos implements CommandLineRunner {

    private final UnidadMedidaRepository unidadMedidaRepository;

    private final CategoriaRepository categoriaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (this.unidadMedidaRepository.findByNombre("Unidad").isEmpty()) {
            UnidadMedida unidad = UnidadMedida.builder()
                    .nombre("Unidad")
                    .codigo("U").build();

            try {
                this.unidadMedidaRepository.save(unidad);
            } catch (DataIntegrityViolationException e) {
                throw new ProductoException(e.getMessage());
            }
        }

        if (this.categoriaRepository.findAll().size() == 0) {
            // Categorías y Subcategorías
            List<Categoria> categorias = new ArrayList<>();

            Categoria fertilizantes = Categoria.builder()
                    .nombre("Fertilizantes").subcategorias(new ArrayList<>()).build();
            Subcategoria biologicos = Subcategoria.builder()
                    .nombre("Biológicos").codigo("BIO").propiedades(new ArrayList<>()).build();
            Subcategoria biominerales = Subcategoria.builder()
                    .nombre("Bio-minerales").codigo("BMI").propiedades(new ArrayList<>()).build();
            Subcategoria minerales = Subcategoria.builder()
                    .nombre("Minerales").codigo("MIN").propiedades(new ArrayList<>()).build();
            Subcategoria solidos = Subcategoria.builder()
                    .nombre("Sólidos").codigo("SOL").propiedades(new ArrayList<>()).build();
            Subcategoria kitAbonos = Subcategoria.builder()
                    .nombre("Kit de abonos").codigo("KAB").propiedades(new ArrayList<>()).build();
            Subcategoria usos = Subcategoria.builder()
                    .nombre("Usos").codigo("USO").propiedades(new ArrayList<>()).build();
            fertilizantes.getSubcategorias().add(biologicos);
            fertilizantes.getSubcategorias().add(biominerales);
            fertilizantes.getSubcategorias().add(minerales);
            fertilizantes.getSubcategorias().add(solidos);
            fertilizantes.getSubcategorias().add(kitAbonos);
            fertilizantes.getSubcategorias().add(usos);
            categorias.add(fertilizantes);

            Categoria sustratos = Categoria.builder()
                    .nombre("Sustratos").subcategorias(new ArrayList<>()).build();
            Subcategoria tierra = Subcategoria.builder()
                    .nombre("Tierra").codigo("TIE").propiedades(new ArrayList<>()).build();
            Subcategoria coco = Subcategoria.builder()
                    .nombre("Coco").codigo("COC").propiedades(new ArrayList<>()).build();
            Subcategoria lanaRoca = Subcategoria.builder()
                    .nombre("Lana de roca").codigo("LRO").propiedades(new ArrayList<>()).build();
            Subcategoria varios = Subcategoria.builder()
                    .nombre("Varios").codigo("VAR").propiedades(new ArrayList<>()).build();
            sustratos.getSubcategorias().add(tierra);
            sustratos.getSubcategorias().add(coco);
            sustratos.getSubcategorias().add(lanaRoca);
            sustratos.getSubcategorias().add(varios);
            categorias.add(sustratos);

            Categoria controlPlagas = Categoria.builder()
                    .nombre("Control de plagas").subcategorias(new ArrayList<>()).build();
            Subcategoria biologicosCP = Subcategoria.builder()
                    .nombre("Biológicos").codigo("BIC").propiedades(new ArrayList<>()).build();
            Subcategoria quimicos = Subcategoria.builder()
                    .nombre("Químicos").codigo("QUI").propiedades(new ArrayList<>()).build();
            Subcategoria kitInsFung = Subcategoria.builder()
                    .nombre("Kit de insecticidas y fungicidas").codigo("KIF").propiedades(new ArrayList<>()).build();
            controlPlagas.getSubcategorias().add(biologicosCP);
            controlPlagas.getSubcategorias().add(quimicos);
            controlPlagas.getSubcategorias().add(kitInsFung);
            categorias.add(controlPlagas);

            Categoria cultivoInterior = Categoria.builder()
                    .nombre("Cultivo de interior").subcategorias(new ArrayList<>()).build();
            Subcategoria armariosCultivos = Subcategoria.builder()
                    .nombre("Armarios de cultivo").codigo("ACU").propiedades(new ArrayList<>()).build();
            Subcategoria iluminacion = Subcategoria.builder()
                    .nombre("Iluminacion").codigo("ILU").propiedades(new ArrayList<>()).build();
            Subcategoria sistemaAntiOlor = Subcategoria.builder()
                    .nombre("Sistema anti olor").codigo("SAO").propiedades(new ArrayList<>()).build();
            Subcategoria ventilacion = Subcategoria.builder()
                    .nombre("Ventilación").codigo("VEN").propiedades(new ArrayList<>()).build();
            Subcategoria ventilacionExtraccion = Subcategoria.builder()
                    .nombre("Ventilación y extracción").codigo("VYE").propiedades(new ArrayList<>()).build();
            Subcategoria sistemasAntiRuido = Subcategoria.builder()
                    .nombre("Sistemas anti ruido").codigo("SAR").propiedades(new ArrayList<>()).build();
            Subcategoria generadoresCO2 = Subcategoria.builder()
                    .nombre("Generadores de co2").codigo("GCO").propiedades(new ArrayList<>()).build();
            cultivoInterior.getSubcategorias().add(armariosCultivos);
            cultivoInterior.getSubcategorias().add(iluminacion);
            cultivoInterior.getSubcategorias().add(sistemaAntiOlor);
            cultivoInterior.getSubcategorias().add(ventilacion);
            cultivoInterior.getSubcategorias().add(ventilacionExtraccion);
            cultivoInterior.getSubcategorias().add(sistemasAntiRuido);
            cultivoInterior.getSubcategorias().add(generadoresCO2);
            categorias.add(cultivoInterior);

            Categoria accesoriosCultivo = Categoria.builder()
                    .nombre("Accesorios de cultivo").subcategorias(new ArrayList<>()).build();
            Subcategoria contenedoresAccesorios = Subcategoria.builder()
                    .nombre("Contenedores y accesorios").codigo("CYA").propiedades(new ArrayList<>()).build();
            Subcategoria herramientasRiego = Subcategoria.builder()
                    .nombre("Herramientas y riego").codigo("HYR").propiedades(new ArrayList<>()).build();
            Subcategoria controlClimaCultivo = Subcategoria.builder()
                    .nombre("Control clima y cultivo").codigo("CLC").propiedades(new ArrayList<>()).build();
            accesoriosCultivo.getSubcategorias().add(contenedoresAccesorios);
            accesoriosCultivo.getSubcategorias().add(herramientasRiego);
            accesoriosCultivo.getSubcategorias().add(controlClimaCultivo);
            categorias.add(accesoriosCultivo);

            Categoria accesorioFumadores = Categoria.builder()
                    .nombre("Accesorio fumadores").subcategorias(new ArrayList<>()).build();
            Subcategoria pipasBongShisha = Subcategoria.builder()
                    .nombre("Pipas, Bong, Shisha").codigo("PBS").propiedades(new ArrayList<>()).build();
            Subcategoria balanzas = Subcategoria.builder()
                    .nombre("Balanzas").codigo("BAL").propiedades(new ArrayList<>()).build();
            Subcategoria papelLiar = Subcategoria.builder()
                    .nombre("Papel de liar").codigo("PLI").propiedades(new ArrayList<>()).build();
            Subcategoria bandejasLiar = Subcategoria.builder()
                    .nombre("Bandejas de liar").codigo("BLI").propiedades(new ArrayList<>()).build();
            Subcategoria cajasMetalicas = Subcategoria.builder()
                    .nombre("Cajas metalicas").codigo("CME").propiedades(new ArrayList<>()).build();
            Subcategoria picadores = Subcategoria.builder()
                    .nombre("Picadores").codigo("PIC").propiedades(new ArrayList<>()).build();
            Subcategoria packs = Subcategoria.builder()
                    .nombre("Packs").codigo("PAC").propiedades(new ArrayList<>()).build();
            accesorioFumadores.getSubcategorias().add(pipasBongShisha);
            accesorioFumadores.getSubcategorias().add(balanzas);
            accesorioFumadores.getSubcategorias().add(papelLiar);
            accesorioFumadores.getSubcategorias().add(bandejasLiar);
            accesorioFumadores.getSubcategorias().add(bandejasLiar);
            accesorioFumadores.getSubcategorias().add(cajasMetalicas);
            accesorioFumadores.getSubcategorias().add(picadores);
            accesorioFumadores.getSubcategorias().add(packs);
            categorias.add(accesorioFumadores);

            this.categoriaRepository.saveAll(categorias);
        }
    }
}
