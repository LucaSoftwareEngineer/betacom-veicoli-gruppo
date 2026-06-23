package com.betacom.veicoli.bici;

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

import com.betacom.veicoli.dto.request.BiciRequest;
import com.betacom.veicoli.dto.response.BiciResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.core.type.TypeReference;
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
		MvcResult result = mockMvc.perform(get("/api/bici/list"))
				.andExpect(status().isOk())
				.andReturn();
		
		String json = result.getResponse().getContentAsString();
		
		List<BiciResponse> response = objectMapper.readValue(json, new TypeReference<List<BiciResponse>>() {}); 
	
		assertFalse(response.isEmpty());
	}

	@Test
	@Order(4)
	public void findByIdTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/api/bici/list/2"))
				.andExpect(status().isOk())
				.andReturn();
		
		String json = result.getResponse().getContentAsString();
		
		BiciResponse response = objectMapper.readValue(json, BiciResponse.class);
		
		assertEquals(response.getId(), 2);
	}

	@Test
	@Order(5)
	public void removeTest() throws Exception {
		MvcResult result = mockMvc.perform(delete("/api/bici/remove/2"))
				.andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	@Order(6)
	public void createTestError() throws Exception {
		
		BiciRequest req = new BiciRequest();
		req.setNumeroMarce((short) 3);
		req.setTipoFrenoId(1);
		req.setTipoSospensioneId(10);
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
				).andExpect(status().isBadRequest())
				.andReturn();
	}
	
	@Test
	@Order(7)
	public void updateTestError() throws Exception {
		BiciRequest req = new BiciRequest();
		req.setNumeroMarce((short) 3);
		req.setTipoFrenoId(1);
		req.setTipoSospensioneId(2);
		req.setPieghevole(true);
		req.setTipoVeicoloId(3);
		req.setNumeroRuote((short) 2);
		req.setTipoAlimentazioneId(10);
		req.setCategoriaId(1);
		req.setColore("rosso");
		req.setMarca("Super");
		req.setAnnoProduzione(Year.of(2020));
		req.setModello("Mega Bici");
		
		MvcResult result = mockMvc.perform(
					put("/api/bici/update/2")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(req))
				).andExpect(status().isBadRequest())
				.andReturn();
	}
	
	@Test
	@Order(8)
	public void findByIdTestError() throws Exception {
		MvcResult result = mockMvc.perform(get("/api/bici/list/99"))
				.andExpect(status().isBadRequest())
				.andReturn();
	}
	
	@Test
	@Order(9)
	public void removeTestError() throws Exception {
		MvcResult result = mockMvc.perform(delete("/api/bici/remove/99"))
				.andExpect(status().isBadRequest())
				.andReturn();
	}
	
}

