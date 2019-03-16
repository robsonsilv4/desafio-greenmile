package com.robson.desafiogreenmile;

import com.robson.desafiogreenmile.domain.HoraTrabalhada;
import com.robson.desafiogreenmile.domain.Usuario;
import com.robson.desafiogreenmile.domain.enumeration.Perfil;
import com.robson.desafiogreenmile.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@SpringBootApplication
public class DesafioGreenmileApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DesafioGreenmileApplication.class, args);
    }

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Usuario usuario = new Usuario(null, "Robson", "robson@gmail.com", bCryptPasswordEncoder.encode("desafio"));
        usuario.setPerfil(Perfil.ADMIN);
        HoraTrabalhada horaTrabalhada = new HoraTrabalhada(null, LocalDate.parse("17/03/2019", formatoData), 6, usuario);

        usuarioRepository.saveAll(Arrays.asList(usuario));
    }
}
