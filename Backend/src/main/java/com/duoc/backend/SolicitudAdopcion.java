package com.duoc.backend;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SolicitudAdopcion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombreSolicitante;
    private String emailSolicitante;
    private String telefonoSolicitante;
    private String mensaje;
    private String estado; // PENDIENTE, APROBADA, RECHAZADA

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet mascota;

    private LocalDateTime fechaSolicitud;

    public SolicitudAdopcion() {}

    public SolicitudAdopcion(String nombreSolicitante, String emailSolicitante,
                              String telefonoSolicitante, String mensaje, Pet mascota) {
        this.nombreSolicitante = nombreSolicitante;
        this.emailSolicitante = emailSolicitante;
        this.telefonoSolicitante = telefonoSolicitante;
        this.mensaje = mensaje;
        this.mascota = mascota;
        this.estado = "PENDIENTE";
        this.fechaSolicitud = LocalDateTime.now();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombreSolicitante() { return nombreSolicitante; }
    public void setNombreSolicitante(String nombreSolicitante) { this.nombreSolicitante = nombreSolicitante; }
    public String getEmailSolicitante() { return emailSolicitante; }
    public void setEmailSolicitante(String emailSolicitante) { this.emailSolicitante = emailSolicitante; }
    public String getTelefonoSolicitante() { return telefonoSolicitante; }
    public void setTelefonoSolicitante(String telefonoSolicitante) { this.telefonoSolicitante = telefonoSolicitante; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Pet getMascota() { return mascota; }
    public void setMascota(Pet mascota) { this.mascota = mascota; }
    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }
}