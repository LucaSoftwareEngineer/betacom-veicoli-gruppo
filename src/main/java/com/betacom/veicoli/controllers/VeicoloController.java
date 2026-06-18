package com.betacom.veicoli.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.veicoli.dto.request.VwComplessivoVeicoliRequest;
import com.betacom.veicoli.dto.response.VwComplessivoVeicoliResponse;
import com.betacom.veicoli.exceptions.VeicoliException;
import com.betacom.veicoli.services.interfaces.IVeicoloServices;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/veicolo/")
@RequiredArgsConstructor
public class VeicoloController {

	private final IVeicoloServices veicoloService;
	
	@Operation(
            summary = "GET ALL FILTER VEICOLI",
            description = "Questo metodo restituisce i veicoli filtrando le varie richieste",
            tags = {"Veicolo"}
    )
	@GetMapping("search")
	public ResponseEntity<List<VwComplessivoVeicoliResponse>> search(@ParameterObject @ModelAttribute VwComplessivoVeicoliRequest request) throws VeicoliException {
		return ResponseEntity.ok(veicoloService.search(request));
	}
	
}
