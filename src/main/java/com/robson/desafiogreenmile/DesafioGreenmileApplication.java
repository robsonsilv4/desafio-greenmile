package com.robson.desafiogreenmile;

import com.robson.desafiogreenmile.domain.Usuario;
import com.robson.desafiogreenmile.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DesafioGreenmileApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DesafioGreenmileApplication.class, args);
    }

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        Usuario usuario = new Usuario(null, "Robson", "robson@gmail.com");

        usuarioRepository.saveAll(Arrays.asList(usuario));
    }
}
