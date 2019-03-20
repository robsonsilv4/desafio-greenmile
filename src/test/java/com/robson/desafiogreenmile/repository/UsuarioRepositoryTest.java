package com.robson.desafiogreenmile.repository;

import com.robson.desafiogreenmile.domain.Usuario;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsuarioRepositoryTest {

  @Autowired private UsuarioRepository usuarioRepository;
  @Rule public ExpectedException thrown = ExpectedException.none();

  @Test
  public void insereUsuario() {
    Usuario usuario = new Usuario();

    usuario.setId(null);
    usuario.setNome("Robson");
    usuario.setEmail("robson@gmail.com");

    this.usuarioRepository.save(usuario);

    assertThat(usuario.getId()).isNotNull();
    assertThat(usuario.getNome()).isEqualTo("Robson");
    assertThat(usuario.getEmail()).isEqualTo("robson@gmail.com");
  }

  @Test
  public void removeUsuario() {
    Usuario usuario = new Usuario();

    usuario.setId(null);
    usuario.setNome("Samuel");
    usuario.setEmail("samuel@greenmile.com");

    this.usuarioRepository.save(usuario);

    this.usuarioRepository.delete(usuario);

    assertThat(usuarioRepository.findById(usuario.getId())).isEmpty();
  }

  @Test
  public void atualizaInformacoes() {
    Usuario usuario = new Usuario();

    usuario.setId(null);
    usuario.setNome("Robson");
    usuario.setEmail("robsonsilva@gmail.com");

    this.usuarioRepository.save(usuario);

    usuario.setNome("Robson Silva");
    usuario.setEmail("robsonsilva@greenmile.com");

    this.usuarioRepository.save(usuario);

    Optional<Usuario> usuarioAtualizado = usuarioRepository.findById(usuario.getId());

    assertThat(usuarioAtualizado.get().getNome()).isEqualTo("Robson Silva");
    assertThat(usuarioAtualizado.get().getEmail()).isEqualTo("robsonsilva@greenmile.com");
  }

  @Test
  public void emailNaoPodeSerNulo() {
    thrown.expect(DataIntegrityViolationException.class);
    this.usuarioRepository.save(new Usuario());
  }

  @Test
  public void emailDeveSerUnico() {
    thrown.expect(DataIntegrityViolationException.class);

    Usuario usuario1 = new Usuario();
    usuario1.setEmail("robson@gmail.com");

    Usuario usuario2 = new Usuario();
    usuario2.setEmail("robson@gmail.com");

    this.usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2));
  }
}
