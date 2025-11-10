package com.ipn.mx.administracioneventos.features.evento.service;

import com.ipn.mx.administracioneventos.core.domain.Evento;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface AsistenteService {
    public List<Evento> findAll();
    public Evento findById(Long id);
    public Evento save(Evento evento); // Para crear o actualizar
    public void delete(Long id);

    public ByteArrayInputStream reportePDF(List<Evento> listaEventos);
}
