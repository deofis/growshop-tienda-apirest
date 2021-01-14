package com.deofis.tiendaapirestdemo.operaciones.services;

import com.deofis.tiendaapirestdemo.operaciones.domain.EstadoOperacion;
import com.deofis.tiendaapirestdemo.operaciones.domain.EventoOperacion;
import org.springframework.statemachine.StateMachine;

public interface StateMachineService {

    StateMachine<EstadoOperacion, EventoOperacion> build(Long nroOperacion);

    void enviarEvento(Long nroOperacion, StateMachine<EstadoOperacion, EventoOperacion> sm, EventoOperacion evento);
}
