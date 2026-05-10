package com.duoc.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudAdopcionController {

    @Autowired
    private SolicitudAdopcionRepository solicitudRepository;

    @Autowired
    private PetRepository petRepository;

    @PostMapping
    public ResponseEntity<?> crearSolicitud(@RequestBody Map<String, Object> body) {
        try {
            Integer petId = (Integer) body.get("petId");
            Optional<Pet> pet = petRepository.findById(petId);
            if (pet.isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Mascota no encontrada");
                return ResponseEntity.badRequest().body(error);
            }
            SolicitudAdopcion solicitud = new SolicitudAdopcion(
                (String) body.get("nombreSolicitante"),
                (String) body.get("emailSolicitante"),
                (String) body.get("telefonoSolicitante"),
                (String) body.get("mensaje"),
                pet.get()
            );
            solicitudRepository.save(solicitud);
            return ResponseEntity.status(HttpStatus.CREATED).body(solicitud);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Error al crear solicitud: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<SolicitudAdopcion>> getAllSolicitudes() {
        return ResponseEntity.ok(solicitudRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSolicitudById(@PathVariable Integer id) {
        Optional<SolicitudAdopcion> solicitud = solicitudRepository.findById(id);
        if (solicitud.isPresent()) return ResponseEntity.ok(solicitud.get());
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> actualizarEstado(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        try {
            Optional<SolicitudAdopcion> solicitud = solicitudRepository.findById(id);
            if (solicitud.isEmpty()) return ResponseEntity.notFound().build();
            solicitud.get().setEstado(body.get("estado"));
            solicitudRepository.save(solicitud.get());
            return ResponseEntity.ok(solicitud.get());
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Error al actualizar estado: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}