package com.betacom.veicoli.macchina;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Year;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.betacom.veicoli.dto.request.MacchinaRequest;
import com.betacom.veicoli.dto.response.MacchinaResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MacchinaControllerTest {

	private final MockMvc mockMvc;
	private final ObjectMapper objectMapper;
	
	@Test
	@Order(1)
	public void createTest() throws Exception {
		
		MacchinaRequest req = new MacchinaRequest();
		req.setTarga("AA111BB");
		req.setCilindrata((short) 500);
		req.setNumeroPorte((short) 5);
		req.setTipoVeicoloId(1);
		req.setNumeroRuote((short) 4);
		req.setTipoAlimentazioneId(1);
		req.setCategoriaId(1);
		req.setColore("rosso");
		req.setMarca("Ferrari");
		req.setAnnoProduzione(Year.of(2024));
		req.setModello("Testa Rossa");
		
		MvcResult result = mockMvc.perform(
					post("/api/macchina/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isCreated())
				.andReturn();
	}
	
	@Test
	@Order(2)
	public void updateTest() throws Exception {
		MacchinaRequest req = new MacchinaRequest();
		req.setTarga("AA112BB");
		req.setCilindrata((short) 500);
		req.setNumeroPorte((short) 5);
		req.setTipoVeicoloId(1);
		req.setNumeroRuote((short) 4);
		req.setTipoAlimentazioneId(1);
		req.setCategoriaId(1);
		req.setColore("rosso");
		req.setMarca("Ferrari");
		req.setAnnoProduzione(Year.of(2024));
		req.setModello("Testa Rossa 11");
		
		MvcResult result = mockMvc.perform(
					put("/api/macchina/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	@Order(3)
	public void getAllTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/api/macchina/list"))
				.andExpect(status().isOk())
				.andReturn();
		
		String json = result.getResponse().getContentAsString();
		
		List<MacchinaResponse> response = objectMapper.readValue(json, new TypeReference<List<MacchinaResponse>>() {}); 
	
		assertFalse(response.isEmpty());
	}
	
	@Test
	@Order(4)
	public void findByIdTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/api/macchina/list/1"))
				.andExpect(status().isOk())
				.andReturn();
		
		String json = result.getResponse().getContentAsString();
		
		MacchinaResponse response = objectMapper.readValue(json, MacchinaResponse.class);
		
		assertEquals(response.getId(), 1);
	}
	
	@Test
	@Order(5)
	public void removeTest() throws Exception {
		MvcResult result = mockMvc.perform(delete("/api/macchina/delete/1"))
				.andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	@Order(6)
	public void createTestError() throws Exception {
		
		MacchinaRequest req = new MacchinaRequest();
		req.setTarga("AA11BB");
		req.setCilindrata((short) 500);
		req.setNumeroPorte((short) 5);
		req.setTipoVeicoloId(1);
		req.setNumeroRuote((short) 4);
		req.setTipoAlimentazioneId(1);
		req.setCategoriaId(1);
		req.setColore("rosso");
		req.setMarca("Ferrari");
		req.setAnnoProduzione(Year.of(2024));
		req.setModello("Testa Rossa");
		
		MvcResult result = mockMvc.perform(
					post("/api/macchina/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isBadRequest())
				.andReturn();
	}
	
	@Test
	@Order(7)
	public void updateTestError() throws Exception {
		MacchinaRequest req = new MacchinaRequest();
		req.setTarga("AA12BB");
		req.setCilindrata((short) 500);
		req.setNumeroPorte((short) 5);
		req.setTipoVeicoloId(1);
		req.setNumeroRuote((short) 4);
		req.setTipoAlimentazioneId(1);
		req.setCategoriaId(1);
		req.setColore("rosso");
		req.setMarca("Ferrari");
		req.setAnnoProduzione(Year.of(2024));
		req.setModello("Testa Rossa 11");
		
		MvcResult result = mockMvc.perform(
					put("/api/macchina/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isBadRequest())
				.andReturn();
	}
	
	@Test
	@Order(8)
	public void findByIdTestError() throws Exception {
		MvcResult result = mockMvc.perform(get("/api/macchina/list/99"))
				.andExpect(status().isBadRequest())
				.andReturn();
	}
	
	@Test
	@Order(9)
	public void removeTestError() throws Exception {
		MvcResult result = mockMvc.perform(delete("/api/macchina/delete/99"))
				.andExpect(status().isBadRequest())
				.andReturn();
	}
}
