package com.betacom.veicoli.macchina;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
		
	}
	
	@Test
	@Order(4)
	public void findByIdTest() {
		
	}
	
	@Test
	@Order(5)
	public void remove() {
		
	}
	
}
