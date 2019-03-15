package com.robson.desafiogreenmile.resource;

import com.robson.desafiogreenmile.DesafioGreenmileApplicationTests;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
public class UsuarioResourceTest extends DesafioGreenmileApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private UsuarioResource usuarioResource;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(usuarioResource).build();
    }

    @Test
    public void testFindAll() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/usuarios"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testFind() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testInsert() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"nome\": \"Samuel\", \"email\": \"samuel@greenmile.com\" }")
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"nome\": \"Robson\", \"email\": \"robson@greenmile.com\" }"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testDelete() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/usuarios/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
