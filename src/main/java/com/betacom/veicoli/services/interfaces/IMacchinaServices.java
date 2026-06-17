package com.betacom.veicoli.services.interfaces;

import java.util.List;

import com.betacom.veicoli.dto.request.MacchinaRequest;
import com.betacom.veicoli.dto.response.MacchinaResponse;
import com.betacom.veicoli.dto.response.ResponseDTO;
import com.betacom.veicoli.exceptions.VeicoliException;

public interface IMacchinaServices {
	
	public abstract MacchinaResponse create(MacchinaRequest req) throws VeicoliException;
	public abstract MacchinaResponse update(MacchinaRequest req, Integer id) throws VeicoliException;
	public abstract List<MacchinaResponse> getAll() throws VeicoliException;
	public abstract MacchinaResponse findById(Integer id) throws VeicoliException;
	public abstract ResponseDTO remove(Integer id) throws VeicoliException;

}
