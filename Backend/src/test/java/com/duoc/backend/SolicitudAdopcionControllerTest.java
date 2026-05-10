package com.duoc.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SolicitudAdopcionControllerTest {

    @Mock
    private SolicitudAdopcionRepository solicitudRepository;

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private SolicitudAdopcionController solicitudController;

    private Pet pet;
    private SolicitudAdopcion solicitud;

    @BeforeEach
    void setUp() {
        pet = new Pet("Luna", "Gato", "Mestizo", 2, "Hembra", "Santiago", List.of());
        pet.setId(1);
        solicitud = new SolicitudAdopcion("Victor", "victor@mail.com", "123456789", "Me gusta", pet);
        solicitud.setId(1);
    }

    @Test
    void crearSolicitudShouldReturnCreatedWhenPetExists() {
        Map<String, Object> body = new HashMap<>();
        body.put("petId", 1);
        body.put("nombreSolicitante", "Victor");
        body.put("emailSolicitante", "victor@mail.com");
        body.put("telefonoSolicitante", "123456789");
        body.put("mensaje", "Me gusta");

        when(petRepository.findById(1)).thenReturn(Optional.of(pet));
        when(solicitudRepository.save(any())).thenReturn(solicitud);

        ResponseEntity<?> response = solicitudController.crearSolicitud(body);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void crearSolicitudShouldReturnBadRequestWhenPetNotFound() {
        Map<String, Object> body = new HashMap<>();
        body.put("petId", 99);
        body.put("nombreSolicitante", "Victor");
        body.put("emailSolicitante", "victor@mail.com");
        body.put("telefonoSolicitante", "123456789");
        body.put("mensaje", "Me gusta");

        when(petRepository.findById(99)).thenReturn(Optional.empty());

        ResponseEntity<?> response = solicitudController.crearSolicitud(body);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getAllSolicitudesShouldReturnOk() {
        when(solicitudRepository.findAll()).thenReturn(List.of(solicitud));

        ResponseEntity<Iterable<SolicitudAdopcion>> response = solicitudController.getAllSolicitudes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getSolicitudByIdShouldReturnOkWhenExists() {
        when(solicitudRepository.findById(1)).thenReturn(Optional.of(solicitud));

        ResponseEntity<?> response = solicitudController.getSolicitudById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getSolicitudByIdShouldReturnNotFoundWhenNotExists() {
        when(solicitudRepository.findById(99)).thenReturn(Optional.empty());

        ResponseEntity<?> response = solicitudController.getSolicitudById(99);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void actualizarEstadoShouldReturnOkWhenExists() {
        Map<String, String> body = new HashMap<>();
        body.put("estado", "APROBADA");

        when(solicitudRepository.findById(1)).thenReturn(Optional.of(solicitud));
        when(solicitudRepository.save(any())).thenReturn(solicitud);

        ResponseEntity<?> response = solicitudController.actualizarEstado(1, body);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void actualizarEstadoShouldReturnNotFoundWhenNotExists() {
        Map<String, String> body = new HashMap<>();
        body.put("estado", "APROBADA");

        when(solicitudRepository.findById(99)).thenReturn(Optional.empty());

        ResponseEntity<?> response = solicitudController.actualizarEstado(99, body);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}