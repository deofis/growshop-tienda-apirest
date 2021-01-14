package com.deofis.tiendaapirestdemo.pagos.bootstrap;

import com.deofis.tiendaapirestdemo.pagos.domain.MedioPago;
import com.deofis.tiendaapirestdemo.pagos.domain.MedioPagoEnum;
import com.deofis.tiendaapirestdemo.pagos.repositories.MedioPagoRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DataLoaderPagos implements CommandLineRunner {

    private final MedioPagoRepository medioPagoRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        if (this.medioPagoRepository.findAll().size() == 0) {
            List<MedioPago> mediosPago = new ArrayList<>();

            MedioPago efectivo = new MedioPago();
            efectivo.setNombre(MedioPagoEnum.EFECTIVO);
            mediosPago.add(efectivo);

            MedioPago paypal = new MedioPago();
            paypal.setNombre(MedioPagoEnum.PAYPAL);
            mediosPago.add(paypal);

            this.medioPagoRepository.saveAll(mediosPago);
        }
    }
}
