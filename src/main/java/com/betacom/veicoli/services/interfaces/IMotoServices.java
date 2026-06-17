package com.betacom.veicoli.services.interfaces;

import java.util.List;

import com.betacom.veicoli.dto.request.MotoRequest;
import com.betacom.veicoli.dto.response.MotoResponse;
import com.betacom.veicoli.dto.response.ResponseDTO;
import com.betacom.veicoli.exceptions.VeicoliException;

public interface IMotoServices {
	
	public abstract MotoResponse create(MotoRequest req) throws VeicoliException;
	public abstract MotoResponse update(Integer id, MotoRequest req) throws VeicoliException;
	public abstract List<MotoResponse> getAll() throws VeicoliException;
	public abstract MotoResponse findById(Integer id) throws VeicoliException;
	public abstract ResponseDTO remove(Integer id) throws VeicoliException;

}
