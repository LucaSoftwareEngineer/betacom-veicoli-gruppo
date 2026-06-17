package com.betacom.veicoli.services.implementations;

import java.util.List;

import org.modelmapper.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.veicoli.dto.request.BiciRequest;
import com.betacom.veicoli.dto.response.BiciResponse;
import com.betacom.veicoli.dto.response.ResponseDTO;
import com.betacom.veicoli.exceptions.VeicoliException;
import com.betacom.veicoli.models.Bici;
import com.betacom.veicoli.models.tipi.Categoria;
import com.betacom.veicoli.models.tipi.TipoAlimentazione;
import com.betacom.veicoli.models.tipi.TipoFreno;
import com.betacom.veicoli.models.tipi.TipoSospensione;
import com.betacom.veicoli.repositories.BiciRepository;
import com.betacom.veicoli.repositories.CategoriaRepository;
import com.betacom.veicoli.repositories.TipoAlimentazioneRepository;
import com.betacom.veicoli.repositories.TipoFrenoRepository;
import com.betacom.veicoli.repositories.TipoSospensioneRepository;
import com.betacom.veicoli.repositories.TipoVeicoloRepository;
import com.betacom.veicoli.services.interfaces.IBiciServices;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BiciImpl implements IBiciServices {

	private final ModelMapper modelMapper;
	private final BiciRepository biciRepository;
	private final CategoriaRepository categoriaRepository;
	private final TipoAlimentazioneRepository tipoAlimentazioneRepository;
	private final TipoVeicoloRepository tipoVeicoloRepository;
	private final TipoSospensioneRepository tipoSospensioneRepository;
	private final TipoFrenoRepository tipoFrenoRepository;
	
	/*
	 * Metodo che permette di creare una nuova bici
	 */
	@Override
	@Transactional
	public BiciResponse create(BiciRequest request) throws VeicoliException {
		
		if (!categoriaRepository.existsById(request.getCategoriaId()))
			throw new VeicoliException("veicolo.categoria.invalid");
		
		if (!tipoAlimentazioneRepository.existsById(request.getTipoAlimentazioneId()))
			throw new VeicoliException("veicolo.tipo.alim.invalid");
		
		if (!tipoVeicoloRepository.existsById(request.getTipoVeicoloId()))
			throw new VeicoliException("veicolo.tipo.invalid");
		
		if (!tipoSospensioneRepository.existsById(request.getTipoSospensioneId()))
			throw new VeicoliException("bici.tipo.sospensione.invalid");
		
		if (!tipoFrenoRepository.existsById(request.getTipoFrenoId()))
			throw new VeicoliException("bici.tipo.freno.invalid");
		
		Bici bici = modelMapper.map(request, Bici.class);
		biciRepository.save(bici);
		
		return modelMapper.map(bici, BiciResponse.class);
	}

	/*
	 * Metodo che permette di aggiornare una bici
	 */
	@Override
	@Transactional
	public BiciResponse update(BiciRequest request, Integer id) throws VeicoliException {
		
		Bici bici = biciRepository.findById(id).orElseThrow(() -> new VeicoliException("veicolo.id.invalid"));
		
		if (request.getNumeroMarce() != null) {
			if (request.getNumeroMarce() >= 1 || request.getNumeroMarce() <= 3)
				bici.setNumeroMarce(request.getNumeroMarce());
			else
				throw new VeicoliException("bici.num.marce.invalid");
		}
		
		if (request.getTipoFrenoId() != null) {
			if (!tipoFrenoRepository.existsById(request.getTipoFrenoId())) {
				throw new VeicoliException("bici.tipo.freno.invalid");
			}
			TipoFreno tipoFreno = tipoFrenoRepository.findById(request.getTipoFrenoId()).orElseThrow(() -> new VeicoliException("bici.tipo.freno.invalid"));
			bici.setTipoFreno(tipoFreno);
		}
		
		if (request.getTipoSospensioneId() != null) {
			if (!tipoSospensioneRepository.existsById(request.getTipoSospensioneId())) {
				throw new VeicoliException("bici.tipo.sos.invalid");
			}
			TipoSospensione tipoSospensione = tipoSospensioneRepository.findById(request.getTipoSospensioneId()).orElseThrow(() -> new VeicoliException("bici.tipo.freno.invalid"));
			bici.setTipoSospensione(tipoSospensione);
		}
		
		if (request.getPieghevole() != null) {
			bici.setPieghevole(request.getPieghevole());
		}
		
		if (request.getTipoVeicoloId() != null) {
			if (!tipoVeicoloRepository.existsById(request.getTipoVeicoloId())) {
				throw new VeicoliException("veicolo.tipo.invalid");
			}
		}
		
		if (request.getNumeroRuote() != null) {
			bici.setNumeroRuote(request.getNumeroRuote());
		}
		
		if (request.getTipoAlimentazioneId() != null) {
			if (!tipoAlimentazioneRepository.existsById(request.getTipoAlimentazioneId())) {
				throw new VeicoliException("veicolo.tipo.alim.invalid");
			}
			TipoAlimentazione tipoAlimentazione = tipoAlimentazioneRepository.findById(request.getTipoSospensioneId()).orElseThrow(() -> new VeicoliException("bici.tipo.alim.invalid"));
			bici.setTipoAlimentazione(tipoAlimentazione);
		}
		
		if (request.getCategoriaId() != null) {
			if (!categoriaRepository.existsById(request.getCategoriaId())) {
				throw new VeicoliException("veicolo.categoria.invalid");
			}
			Categoria categoria = categoriaRepository.findById(request.getCategoriaId()).orElseThrow(() -> new VeicoliException("veicolo.categoria.invalid"));
			bici.setCategoria(categoria);
		}
		
		if (!request.getColore().isEmpty() || !request.getColore().isBlank()) {
			bici.setColore(request.getColore());
		}
		
		
		if (request.getAnnoProduzione() != null) {
			bici.setAnnoProduzione(request.getAnnoProduzione());
		}
		
		if (request.getModello() != null) {
			bici.setModello(request.getModello());
		}
		
		return modelMapper.map(bici, BiciResponse.class);
	}

	/*
	 * Metodo che permette di rimuovere una bici
	 */
	@Override
	@Transactional
	public ResponseDTO remove(Integer id) throws VeicoliException {
		
		if (id == null)
			throw new VeicoliException("veicolo.id.null"); 
		
		biciRepository.findById(id).orElseThrow(() -> new VeicoliException("veicolo.id.invalid"));
		biciRepository.deleteById(id);
		
		return ResponseDTO.builder().msg("Bici con id " + id + "eliminata").build();
	}

	/*
	 * Metodo che permette di vedere l'elenco delle bici
	 */
	@Override
	public List<BiciResponse> getAll() throws VeicoliException {		
		return modelMapper.map(biciRepository.findAll(), new TypeToken<List<BiciResponse>>() {}.getType());
	}

	/*
	 * Metodo che permette di cercare una bici per id
	 */
	@Override
	public BiciResponse getById(Integer id) throws VeicoliException {
		Bici bici = biciRepository.findById(id).orElseThrow(() -> new VeicoliException(""));
		return modelMapper.map(bici, BiciResponse.class);
	}

}
