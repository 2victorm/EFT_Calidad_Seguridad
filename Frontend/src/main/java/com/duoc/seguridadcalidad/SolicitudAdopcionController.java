package com.duoc.seguridadcalidad;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/solicitudes")
public class SolicitudAdopcionController {

    @Autowired
    private BackendService backendService;

    @Autowired
    private JwtCookieService jwtCookieService;

    @GetMapping
    public String listarSolicitudes(Model model, HttpServletRequest request) {
        String jwt = jwtCookieService.extractToken(request);
        if (jwt == null) return "redirect:/login";
        try {
            model.addAttribute("solicitudes", backendService.getSolicitudes(jwt));
        } catch (Exception e) {
            model.addAttribute("error", "Error al cargar solicitudes");
        }
        return "solicitudes";
    }

    @GetMapping("/nueva/{petId}")
    public String nuevaSolicitud(@PathVariable Integer petId, Model model, HttpServletRequest request) {
        String jwt = jwtCookieService.extractToken(request);
        if (jwt == null) return "redirect:/login";
        model.addAttribute("petId", petId);
        return "nueva_solicitud";
    }
}