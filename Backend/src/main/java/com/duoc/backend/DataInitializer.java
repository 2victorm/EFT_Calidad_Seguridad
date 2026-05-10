package com.duoc.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    @Override
    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        crearUsuarioSiNoExiste("admin", "admin@duoc.cl", "password", encoder);
        crearUsuarioSiNoExiste("user", "user@duoc.cl", "password", encoder);
        crearUsuarioSiNoExiste("veterinario", "veterinario@duoc.cl", "password", encoder);

        crearMascotaSiNoExiste("Luna", "Gato", "Mestizo", 2, "Hembra", "Santiago",
            List.of("https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?w=200"));
        crearMascotaSiNoExiste("Max", "Perro", "Labrador", 3, "Macho", "Valparaíso",
            List.of("https://images.unsplash.com/photo-1552053831-71594a27632d?w=200"));
        crearMascotaSiNoExiste("Mia", "Gato", "Siamés", 1, "Hembra", "Concepción",
            List.of("https://images.unsplash.com/photo-1519052537078-e6302a4968d4?w=200"));
        crearMascotaSiNoExiste("Rocky", "Perro", "Golden Retriever", 4, "Macho", "Santiago",
            List.of("https://images.unsplash.com/photo-1587300003388-59208cc962cb?w=200"));
        crearMascotaSiNoExiste("Nala", "Perro", "Beagle", 2, "Hembra", "Temuco",
            List.of("https://images.unsplash.com/photo-1505628346881-b72b27e84530?w=200"));

        logger.info("Inicialización completada.");
    }

    private void crearUsuarioSiNoExiste(String username, String email, String password, BCryptPasswordEncoder encoder) {
        if (!userRepository.existsByUsername(username)) {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(encoder.encode(password));
            userRepository.save(user);
            logger.info("Usuario creado: {}", username);
        } else {
            logger.info("Usuario ya existe, se omite: {}", username);
        }
    }

        private void crearMascotaSiNoExiste(String nombre, String especie, String raza, int edad, String genero, String ubicacion, List<String> fotos) {
            List<Pet> todas = (List<Pet>) petRepository.findAll();
            Pet existente = todas.stream().filter(p -> p.getName().equals(nombre)).findFirst().orElse(null);
            if (existente == null) {
            Pet pet = new Pet(nombre, especie, raza, edad, genero, ubicacion, fotos);
        petRepository.save(pet);
        logger.info("Mascota creada: {}", nombre);
    } else {
        existente.setStatus("available");
        petRepository.save(existente);
        logger.info("Mascota reseteada a available: {}", nombre);
    }
}
}