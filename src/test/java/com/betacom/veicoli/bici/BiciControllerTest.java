package com.betacom.veicoli.bici;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Year;

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

import com.betacom.veicoli.dto.request.BiciRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BiciControllerTest {

	private final MockMvc mockMvc;
	private final ObjectMapper objectMapper;
	
	@Test
	@Order(1)
	public void createTest() throws Exception {
		
		BiciRequest req = new BiciRequest();
		req.setNumeroMarce((short) 3);
		req.setTipoFrenoId(1);
		req.setTipoSospensioneId(2);
		req.setPieghevole(true);
		req.setTipoVeicoloId(3);
		req.setNumeroRuote((short) 2);
		req.setTipoAlimentazioneId(4);
		req.setCategoriaId(1);
		req.setColore("rosso");
		req.setMarca("Super");
		req.setAnnoProduzione(Year.of(2020));
		req.setModello("Mega Bici");
		
		MvcResult result = mockMvc.perform(
					post("/api/bici/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isCreated())
				.andReturn();
	}

	@Test
	@Order(2)
	public void updateTest() throws Exception {
		BiciRequest req = new BiciRequest();
		req.setNumeroMarce((short) 3);
		req.setTipoFrenoId(1);
		req.setTipoSospensioneId(2);
		req.setPieghevole(true);
		req.setTipoVeicoloId(3);
		req.setNumeroRuote((short) 2);
		req.setTipoAlimentazioneId(4);
		req.setCategoriaId(1);
		req.setColore("rosso");
		req.setMarca("Super");
		req.setAnnoProduzione(Year.of(2020));
		req.setModello("Mega Bici");
		
		MvcResult result = mockMvc.perform(
					put("/api/bici/update/2")
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
	public void findByIdTest() throws Exception {

	}

	@Test
	@Order(5)
	public void removeTest() throws Exception {

	}
}
