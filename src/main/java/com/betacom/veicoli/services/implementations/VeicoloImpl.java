package com.betacom.veicoli.services.implementations;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.betacom.veicoli.components.VwComplessivoVeicoliModelToDto;
import com.betacom.veicoli.dto.request.VwComplessivoVeicoliRequest;
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
	private final VwComplessivoVeicoliModelToDto vwComplessivoVeicoliModelToDto;

	@Override
	public List<VwComplessivoVeicoliResponse> search(VwComplessivoVeicoliRequest request) {

		Specification<VwComplessivoVeicoli> spec = VwComplessivoVeicoliSpecification.search(request);
		List<VwComplessivoVeicoli> veicoli = vwVeicoliRepository.findAll(spec);
		
		List<VwComplessivoVeicoliResponse> veicoliResponse = modelMapper.map(veicoli, new TypeToken<List<VwComplessivoVeicoliResponse>>() {}.getType());
		veicoliResponse = vwComplessivoVeicoliModelToDto.execute(veicoli, veicoliResponse);
		
		return veicoliResponse;
	}

}
