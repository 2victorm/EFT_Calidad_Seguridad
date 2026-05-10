package com.duoc.seguridadcalidad;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudRestController {

    private final BackendService backendService;
    private final JwtCookieService jwtCookieService;

    public SolicitudRestController(BackendService backendService, JwtCookieService jwtCookieService) {
        this.backendService = backendService;
        this.jwtCookieService = jwtCookieService;
    }

    @GetMapping
    public ResponseEntity<?> getSolicitudes(HttpServletRequest request) {
        String token = jwtCookieService.extractToken(request);
        if (token == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        try {
            return ResponseEntity.ok(backendService.getSolicitudes(token));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crearSolicitud(HttpServletRequest request,
            @RequestBody Map<String, Object> body) {
        String token = jwtCookieService.extractToken(request);
        if (token == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        try {
            return ResponseEntity.ok(backendService.crearSolicitud(body, token));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}