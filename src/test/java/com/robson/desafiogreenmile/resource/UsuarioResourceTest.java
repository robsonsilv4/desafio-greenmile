package com.robson.desafiogreenmile.resource;

import com.jayway.jsonpath.JsonPath;
import com.robson.desafiogreenmile.repository.UsuarioRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UsuarioResourceTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private UsuarioRepository usuarioRepository;
  @Autowired private WebApplicationContext webApplicationContext;

  private ResultActions insertUsuario(String json) throws Exception {
    return mockMvc.perform(post("/usuarios").contentType(MediaType.APPLICATION_JSON).content(json));
  }

  private ResultActions login(String json) throws Exception {
    return mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(json));
  }

  private String extractToken(MvcResult result) throws UnsupportedEncodingException {
    return JsonPath.read(result.getResponse().getContentAsString(), "$.token");
  }

  @Test
  public void listaUsuarios() throws Exception {
    mockMvc.perform(get("/usuarios")).andExpect(status().isOk());
  }

  @Test
  public void realizaCadastro() throws Exception {
    mockMvc
        .perform(
            post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\"nome\":\"Robson\",\"email\":\"robson@greenmile.com\",\"senha\":\"desafio\"}"))
        .andExpect(status().isCreated());
  }

  @Test
  public void realizaLogin() throws Exception {
    mockMvc
        .perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"robson@gmail.com\", \"senha\":\"greenmile\"}"))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void negaLogin() throws Exception {
    login("{\"email\":\"samuel@greenmile.com\", \"senha\":\"greenmile\"}")
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void negaLoginVazio() throws Exception {
    login("{}").andExpect(status().isUnauthorized());
  }
}
