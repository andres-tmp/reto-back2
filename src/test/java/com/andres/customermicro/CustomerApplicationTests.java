package com.andres.customermicro;

import com.andres.customermicro.data.KpiClientes;
import com.andres.customermicro.domain.Cliente;
import com.andres.customermicro.dto.ClienteCreateRequest;
import com.andres.customermicro.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    public void testListClientes() throws Exception {

        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombre("SARA");
        cliente.setApellido("VEGA");

        List<Cliente> clientes = Collections.singletonList(cliente);
        given(clienteService.findAll()).willReturn(clientes);

        // when + then
        this.mockMvc.perform(get("/listclientes"))
                .andExpect(status().isOk())
                //.andExpect(content().string(containsString("SARA")));
                .andExpect(content().json("[{\"id\":1,\"nombre\":\"SARA\",\"apellido\":\"VEGA\",\"edad\":null,\"fechaNacimiento\":null}]"));
    }

    @Test
    public void testKpi() throws Exception {

        KpiClientes kpiClientes = new KpiClientes(30.0, 10.0);

        given(clienteService.kpiClientes()).willReturn(kpiClientes);

        // when + then
        this.mockMvc.perform(get("/kpideclientes"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"promedioEdad\":30.0,\"desviacionEstandarEdad\":10.0}"));
    }

    @Test
    public void testCrearCliente() throws Exception {

        ClienteCreateRequest cliente = new ClienteCreateRequest("SARA", "MENDOZA", "1989-09-30");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cliente);

        given(clienteService.create(cliente)).willReturn(cliente.toEntity());

        this.mockMvc.perform(post("/creacliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void testNullNombreCreateRequest() throws Exception {

        ClienteCreateRequest cliente = new ClienteCreateRequest(null, "MENDOZA", "1989-09-30");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cliente);

        given(clienteService.create(cliente)).willReturn(cliente.toEntity());

        this.mockMvc.perform(post("/creacliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testNullApellidoCreateRequest() throws Exception {

        ClienteCreateRequest cliente = new ClienteCreateRequest("JUAN", null, "1989-09-30");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cliente);

        given(clienteService.create(cliente)).willReturn(cliente.toEntity());

        this.mockMvc.perform(post("/creacliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testNullFecNacimientoCreateRequest() throws Exception {

        ClienteCreateRequest cliente = new ClienteCreateRequest("JUAN", "MENDOZA", null);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cliente);

        given(clienteService.create(cliente)).willReturn(cliente.toEntity());

        this.mockMvc.perform(post("/creacliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }
}
