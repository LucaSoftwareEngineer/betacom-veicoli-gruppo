package com.betacom.veicoli.services.interfaces;

import java.util.List;

import com.betacom.veicoli.dto.request.BiciRequest;
import com.betacom.veicoli.dto.response.BiciResponse;
import com.betacom.veicoli.dto.response.ResponseDTO;
import com.betacom.veicoli.exceptions.VeicoliException;

public interface IBiciServices {

	public BiciResponse create(BiciRequest request) throws VeicoliException;
	public BiciResponse update(BiciRequest request, Integer id) throws VeicoliException;
	public ResponseDTO remove(Integer id) throws VeicoliException;
	public List<BiciResponse> getAll() throws VeicoliException;
	public BiciResponse getById(Integer id) throws VeicoliException;
	
}
