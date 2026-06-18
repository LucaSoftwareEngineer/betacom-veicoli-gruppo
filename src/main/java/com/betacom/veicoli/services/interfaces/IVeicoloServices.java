package com.betacom.veicoli.services.interfaces;

import java.util.List;

import com.betacom.veicoli.dto.request.VwComplessivoVeicoliRequest;
import com.betacom.veicoli.dto.response.VwComplessivoVeicoliResponse;

public interface IVeicoloServices {

	public List<VwComplessivoVeicoliResponse> search(VwComplessivoVeicoliRequest request);
	
}
