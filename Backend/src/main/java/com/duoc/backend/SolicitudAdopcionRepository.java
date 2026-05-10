package com.duoc.backend;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface SolicitudAdopcionRepository extends CrudRepository<SolicitudAdopcion, Integer> {
    List<SolicitudAdopcion> findByEstado(String estado);
    List<SolicitudAdopcion> findByMascotaId(Integer petId);
}