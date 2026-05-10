package com.duoc.seguridadcalidad;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class AppointmentTests {

    @Test
    void constructorCompletoDebeAsignarTodosLosCampos() {
        LocalDate fecha = LocalDate.of(2026, 1, 15);
        LocalTime hora = LocalTime.of(10, 30);
        Appointment appt = new Appointment(1L, 2L, fecha, hora, "Control", "Dr. Pérez");

        assertEquals(1L, appt.getId());
        assertEquals(2L, appt.getPatientId());
        assertEquals(fecha, appt.getDate());
        assertEquals(hora, appt.getTime());
        assertEquals("Control", appt.getReason());
        assertEquals("Dr. Pérez", appt.getVeterinarian());
    }

    @Test
    void constructorVacioDebeCrearInstancia() {
        Appointment appt = new Appointment();
        assertNotNull(appt);
    }

    @Test
    void settersDebenActualizarValores() {
        Appointment appt = new Appointment();
        LocalDate fecha = LocalDate.of(2026, 5, 1);
        LocalTime hora = LocalTime.of(9, 0);

        appt.setId(5L);
        appt.setPatientId(10L);
        appt.setDate(fecha);
        appt.setTime(hora);
        appt.setReason("Vacuna");
        appt.setVeterinarian("Dr. López");

        assertEquals(5L, appt.getId());
        assertEquals(10L, appt.getPatientId());
        assertEquals(fecha, appt.getDate());
        assertEquals(hora, appt.getTime());
        assertEquals("Vacuna", appt.getReason());
        assertEquals("Dr. López", appt.getVeterinarian());
    }
}