package com.deofis.tiendaapirestdemo.operaciones.services;

import com.deofis.tiendaapirestdemo.operaciones.domain.EstadoOperacion;
import com.deofis.tiendaapirestdemo.operaciones.domain.EventoOperacion;
import com.deofis.tiendaapirestdemo.operaciones.domain.Operacion;
import com.deofis.tiendaapirestdemo.operaciones.exceptions.OperacionException;
import com.deofis.tiendaapirestdemo.operaciones.repositories.OperacionRepository;
import lombok.AllArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StateMachineServiceImpl implements StateMachineService {

    private final StateMachineFactory<EstadoOperacion, EventoOperacion> stateMachineFactory;
    private final OperacionStateChangeInterceptor operacionStateChangeInterceptor;
    private final OperacionRepository operacionRepository;

    @Override
    public StateMachine<EstadoOperacion, EventoOperacion> build(Long nroOperacion) {
        Operacion operacion = this.operacionRepository.findById(nroOperacion)
                .orElseThrow(() -> new OperacionException("No existe la operación con id: "
                        + nroOperacion));

        StateMachine<EstadoOperacion, EventoOperacion> sm = this.stateMachineFactory.getStateMachine(Long.toString(operacion.getNroOperacion()));

        sm.stop();

        sm.getStateMachineAccessor()
                .doWithAllRegions(sma -> {
                    sma.addStateMachineInterceptor(operacionStateChangeInterceptor);
                    sma.resetStateMachine(new DefaultStateMachineContext<>(operacion.getEstado()
                            ,null, null, null));
                });

        sm.start();

        return sm;
    }

    @Override
    public void enviarEvento(Long nroOperacion, StateMachine<EstadoOperacion, EventoOperacion> sm, EventoOperacion evento) {
        Message<EventoOperacion> msg = MessageBuilder.withPayload(evento)
                .setHeader(OperacionServiceImpl.NRO_OPERACION_HEADER, nroOperacion)
                .build();

        sm.sendEvent(msg);
    }
}
