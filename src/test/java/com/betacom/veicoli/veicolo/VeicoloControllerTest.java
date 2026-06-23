package com.betacom.veicoli.veicolo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.web.servlet.MockMvc;

import com.betacom.veicoli.components.VwComplessivoVeicoliModelToDto;
import com.betacom.veicoli.dto.request.VwComplessivoVeicoliRequest;
import com.betacom.veicoli.dto.response.VwComplessivoVeicoliResponse;
import com.betacom.veicoli.models.VwComplessivoVeicoli;
import com.betacom.veicoli.repositories.VwComplessivoVeicoliRepository;
import com.betacom.veicoli.services.implementations.VeicoloImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VeicoloControllerTest {

	private final MockMvc mockMvc;
	private final ObjectMapper objectMapper;

	@InjectMocks
	private VeicoloImpl veicoloImpl;

	@Mock
	private VwComplessivoVeicoliRepository vwComplessivoVeicoliRepository;

	@Mock
	private ModelMapper modelMapper;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Mock
	private VwComplessivoVeicoliModelToDto vwComplessivoVeicoliModelToDto; // stesso nome del campo in VeicoloImpl

	@Test
	public void search() {
		List<VwComplessivoVeicoli> mockVeicoli = loadMacchine();
		List<VwComplessivoVeicoliResponse> mockResponse = new ArrayList<>();
		VwComplessivoVeicoliResponse resp = new VwComplessivoVeicoliResponse();
		resp.setColore("rosso");
		resp.setMacchinaTarga("AA111BB");
		mockResponse.add(resp);

		when(vwComplessivoVeicoliRepository.findAll(any(Specification.class))).thenReturn(mockVeicoli);
		when(modelMapper.map(any(), any())).thenReturn(mockResponse);
		when(vwComplessivoVeicoliModelToDto.execute(any(), any())).thenReturn(mockResponse);

		VwComplessivoVeicoliRequest req = new VwComplessivoVeicoliRequest();
		req.setColore("rosso");

		List<VwComplessivoVeicoliResponse> result = veicoloImpl.search(req);

		System.err.println(result.getFirst());
		assertFalse(result.isEmpty());
	}

	public List<VwComplessivoVeicoli> loadMacchine() {
		VwComplessivoVeicoli vei = new VwComplessivoVeicoli();
		vei.setMacchinaTarga("AA111BB");
		vei.setMacchinaCilindrata(500);
		vei.setNumeroPorte(5);
		vei.setTipoVeicolo(1L);
		vei.setNumeroRuote(4);
		vei.setTipoAlimentazione(1L);
		vei.setCategoria(1L);
		vei.setColore("rosso");
		vei.setMarca("Ferrari");
		vei.setAnnoProduzione(2024);
		vei.setModello("Testa Rossa");

		List<VwComplessivoVeicoli> veicolis = new ArrayList<>();
		veicolis.add(vei);

		return veicolis;
	}

}
