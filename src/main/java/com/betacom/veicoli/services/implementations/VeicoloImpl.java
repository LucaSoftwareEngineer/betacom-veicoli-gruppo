package com.betacom.veicoli.services.implementations;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.betacom.veicoli.dto.request.VwComplessivoVeicoliRequest;
import com.betacom.veicoli.dto.response.BiciResponse;
import com.betacom.veicoli.dto.response.MotoResponse;
import com.betacom.veicoli.dto.response.VwComplessivoVeicoliResponse;
import com.betacom.veicoli.models.VwComplessivoVeicoli;
import com.betacom.veicoli.repositories.VwComplessivoVeicoliRepository;
import com.betacom.veicoli.services.interfaces.IVeicoloServices;
import com.betacom.veicoli.specifications.VwComplessivoVeicoliSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VeicoloImpl implements IVeicoloServices {

	private final ModelMapper modelMapper;
	private final VwComplessivoVeicoliRepository vwVeicoliRepository;

	@Override
	public List<VwComplessivoVeicoliResponse> search(VwComplessivoVeicoliRequest request) {

		Specification<VwComplessivoVeicoli> spec = VwComplessivoVeicoliSpecification.search(request);
		List<VwComplessivoVeicoli> veicoli = vwVeicoliRepository.findAll(spec);

		return modelMapper.map(veicoli, new TypeToken<List<VwComplessivoVeicoliResponse>>() {}.getType());
	}

}
