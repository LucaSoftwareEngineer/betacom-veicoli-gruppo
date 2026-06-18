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
import com.betacom.veicoli.dto.response.tipi.TipoAlimentazioneResponse;
import com.betacom.veicoli.dto.response.tipi.TipoFrenoResponse;
import com.betacom.veicoli.dto.response.tipi.TipoSospensioneResponse;
import com.betacom.veicoli.dto.response.tipi.TipoVeicoloResponse;
import com.betacom.veicoli.models.VwComplessivoVeicoli;
import com.betacom.veicoli.models.tipi.TipoAlimentazione;
import com.betacom.veicoli.models.tipi.TipoFreno;
import com.betacom.veicoli.models.tipi.TipoSospensione;
import com.betacom.veicoli.models.tipi.TipoVeicolo;
import com.betacom.veicoli.repositories.TipoAlimentazioneRepository;
import com.betacom.veicoli.repositories.TipoFrenoRepository;
import com.betacom.veicoli.repositories.TipoSospensioneRepository;
import com.betacom.veicoli.repositories.TipoVeicoloRepository;
import com.betacom.veicoli.repositories.VwComplessivoVeicoliRepository;
import com.betacom.veicoli.services.interfaces.IVeicoloServices;
import com.betacom.veicoli.specifications.VwComplessivoVeicoliSpecification;
import com.betacom.veicoli.viewmodels.VwComplessivoVeicoliViewModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VeicoloImpl implements IVeicoloServices {

	private final ModelMapper modelMapper;
	private final VwComplessivoVeicoliRepository vwVeicoliRepository;
	private final TipoFrenoRepository tipoFrenoRepository;
	private final TipoSospensioneRepository tipoSospensioneRepository;
	private final TipoAlimentazioneRepository tipoAlimentazioneRepository;
	private final TipoVeicoloRepository tipoVeicoloRepository;

	@Override
	public List<VwComplessivoVeicoliResponse> search(VwComplessivoVeicoliRequest request) {

		Specification<VwComplessivoVeicoli> spec = VwComplessivoVeicoliSpecification.search(request);
		List<VwComplessivoVeicoli> veicoli = vwVeicoliRepository.findAll(spec);
		List<VwComplessivoVeicoliViewModel> veicoliViewModel = modelMapper.map(veicoli, new TypeToken<List<VwComplessivoVeicoliViewModel>>() {}.getType());
		List<VwComplessivoVeicoliResponse> veicoliResponse = modelMapper.map(veicoliViewModel, new TypeToken<List<VwComplessivoVeicoliResponse>>() {}.getType());
		
		for (int i=0; i<veicoliViewModel.size(); i++) {
			VwComplessivoVeicoliViewModel veicoloViewModel = veicoliViewModel.get(i);
			VwComplessivoVeicoliResponse veicoloResponse = veicoliResponse.get(i);
			
			if (veicoloViewModel.getTipoFreno() != null) {
				Integer idTipoFreno = Integer.valueOf(veicoloViewModel.getTipoFreno().toString());
				TipoFreno tipoFreno = tipoFrenoRepository.findById(idTipoFreno).get();
				TipoFrenoResponse tipoFrenoResponse = modelMapper.map(tipoFreno, TipoFrenoResponse.class);
				veicoloResponse.setTipoFreno(tipoFrenoResponse);
			}
			
			if (veicoloViewModel.getTipoSospensione() != null) {
				Integer idTipoSospensione = Integer.valueOf(veicoloViewModel.getTipoSospensione().toString());
				TipoSospensione tipoSospensione = tipoSospensioneRepository.findById(idTipoSospensione).get();
				TipoSospensioneResponse tipoSospensioneResponse = modelMapper.map(tipoSospensione, TipoSospensioneResponse.class);
				veicoloResponse.setTipoSospensione(tipoSospensioneResponse);
			}
			
			if (veicoloViewModel.getTipoAlimentazione() != null) {
				Integer idTipoAlimentazione = Integer.valueOf(veicoloViewModel.getTipoAlimentazione().toString());
				TipoAlimentazione tipoAlimentazione = tipoAlimentazioneRepository.findById(idTipoAlimentazione).get();
				TipoAlimentazioneResponse tipoAlimentazioneResponse = modelMapper.map(tipoAlimentazione, TipoAlimentazioneResponse.class);
				veicoloResponse.setTipoAlimentazione(tipoAlimentazioneResponse);
			}
			
			if (veicoloViewModel.getTipoVeicolo() != null) {
				Integer idTipoVeicolo = Integer.valueOf(veicoloViewModel.getTipoVeicolo().toString());
				TipoVeicolo tipoVeicolo = tipoVeicoloRepository.findById(idTipoVeicolo).get();
				TipoVeicoloResponse tipoVeicoloResponse = modelMapper.map(tipoVeicolo, TipoVeicoloResponse.class);
				veicoloResponse.setTipoVeicolo(tipoVeicoloResponse);
			}
		}
		
		return veicoliResponse;
	}

}
