package com.betacom.veicoli.services.implementations;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.veicoli.dto.request.MacchinaRequest;
import com.betacom.veicoli.dto.response.MacchinaResponse;
import com.betacom.veicoli.dto.response.ResponseDTO;
import com.betacom.veicoli.exceptions.VeicoliException;
import com.betacom.veicoli.models.Macchina;
import com.betacom.veicoli.models.tipi.Categoria;
import com.betacom.veicoli.models.tipi.TipoAlimentazione;
import com.betacom.veicoli.models.tipi.TipoVeicolo;
import com.betacom.veicoli.repositories.CategoriaRepository;
import com.betacom.veicoli.repositories.MacchinaRepository;
import com.betacom.veicoli.repositories.TipoAlimentazioneRepository;
import com.betacom.veicoli.repositories.TipoVeicoloRepository;
import com.betacom.veicoli.services.interfaces.IMacchinaServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MacchinaImpl implements IMacchinaServices{
	
	private final ModelMapper modelMapper;
	private final MacchinaRepository macchinaRepository;
	private final TipoVeicoloRepository tipoVeicoloRepository;
	private final TipoAlimentazioneRepository tipoAlimentazioneRepository;
	private final CategoriaRepository categoriaRepository;

	@Transactional
	@Override
	public MacchinaResponse create(MacchinaRequest req) throws VeicoliException{
		
		if(macchinaRepository.existsByTarga(req.getTarga()))
			throw new VeicoliException("macchina.targa.exists");
		
		TipoVeicolo tipoVeicolo = tipoVeicoloRepository.findById(req.getTipoVeicoloId())
			    .orElseThrow(() -> new VeicoliException("veicolo.tipo.invalid"));
		if(!tipoVeicolo.getDescrizione().equalsIgnoreCase("MACCHINA"))
			throw new VeicoliException("veicolo.tipo.invalid");
		
		TipoAlimentazione tipoAlimentazione = tipoAlimentazioneRepository.findById(req.getTipoAlimentazioneId())
				.orElseThrow(() -> new VeicoliException("veicolo.tipo.alim.invalid"));
		if(tipoAlimentazione.getDescrizione().equalsIgnoreCase("MANUALE"))
			throw new VeicoliException("veicolo.tipo.alim.invalid");
		
		Categoria categoria = categoriaRepository.findById(req.getCategoriaId())
				.orElseThrow(() -> new VeicoliException("veicolo.categoria.invalid"));
		if(categoria.getDescrizione().equalsIgnoreCase("MOTOCROSS"))
			throw new VeicoliException("veicolo.categoria.invalid");
		
		if(req.getAnnoProduzione().isBefore(Year.of(2006)) || req.getAnnoProduzione().isAfter(Year.now()))
			throw new VeicoliException("veicolo.anno.invalid");
			
		Macchina macchina = modelMapper.map(req, Macchina.class);
		macchina.setId(null);
		macchina.setTipoVeicolo(tipoVeicolo);
		macchina.setCategoria(categoria);
		macchina.setTipoAlimentazione(tipoAlimentazione);
		macchinaRepository.save(macchina);
		
		MacchinaResponse res = modelMapper.map(macchina, MacchinaResponse.class);

		res.setTipoVeicoloDesc(macchina.getTipoVeicolo().getDescrizione());
		res.setCategoriaDesc(macchina.getCategoria().getDescrizione());
		res.setTipoAlimentazioneDesc(macchina.getTipoAlimentazione().getDescrizione());

		return res;
	}

	@Transactional
	@Override
	public MacchinaResponse update(MacchinaRequest req, Integer id) throws VeicoliException{
		
		Macchina macchina = macchinaRepository.findById(id)
				.orElseThrow(() -> new VeicoliException("veicolo.id.invalid"));
		
		if(req.getTarga() != null && !req.getTarga().isBlank()) {
		    if(!req.getTarga().equals(macchina.getTarga())) {
		        if(macchinaRepository.existsByTarga(req.getTarga()))
		            throw new VeicoliException("macchina.targa.exists");
		        macchina.setTarga(req.getTarga());
		    }
		}
		
		if(req.getCilindrata() != null) {
			if(macchina.getCilindrata().equals(req.getCilindrata())) {
				req.setCilindrata(null);
			}else {
			macchina.setCilindrata(req.getCilindrata());
			}
		}
		
		if(req.getNumeroPorte() != null) {
			if(req.getNumeroPorte() != 3 && req.getNumeroPorte() != 5)
				throw new VeicoliException("macchina.porte.invalid");
			else macchina.setNumeroPorte(req.getNumeroPorte());
		}
		
		if(req.getTipoVeicoloId() != null) {
			TipoVeicolo tipoVeicolo = tipoVeicoloRepository.findById(req.getTipoVeicoloId())
				    .orElseThrow(() -> new VeicoliException("veicolo.tipo.invalid"));
			if(!tipoVeicolo.getDescrizione().equalsIgnoreCase("MACCHINA"))
				throw new VeicoliException("veicolo.tipo.invalid");
			else macchina.setTipoVeicolo(tipoVeicolo);
		}
		
		if(req.getNumeroRuote() != null) {
			if(req.getNumeroRuote() != 4)
				throw new VeicoliException("veicolo.num.ruote.invalid");
			else macchina.setNumeroRuote(req.getNumeroRuote());
		}
		
		if(req.getTipoAlimentazioneId() != null) {
			TipoAlimentazione tipoAlimentazione = tipoAlimentazioneRepository.findById(req.getTipoAlimentazioneId())
					.orElseThrow(() -> new VeicoliException("veicolo.tipo.alim.invalid"));
			if(tipoAlimentazione.getDescrizione().equalsIgnoreCase("MANUALE"))
				throw new VeicoliException("veicolo.tipo.alim.invalid");
			else macchina.setTipoAlimentazione(tipoAlimentazione);
		}
		
		if(req.getCategoriaId() != null) {
			Categoria categoria = categoriaRepository.findById(req.getCategoriaId())
					.orElseThrow(() -> new VeicoliException("veicolo.categoria.invalid"));
			if(categoria.getDescrizione().equalsIgnoreCase("MOTOCROSS"))
				throw new VeicoliException("veicolo.categoria.invalid");
			else macchina.setCategoria(categoria);
		}
		
		if(!req.getColore().isEmpty() || !req.getColore().isBlank()) {
			if(macchina.getColore().equals(req.getColore())) {
				req.setColore(null);
			}else {
				macchina.setColore(req.getColore());
			}
		}
		
		if(!req.getMarca().isEmpty() || !req.getMarca().isBlank()) {
			if(macchina.getMarca().equals(req.getMarca())) {
				req.setMarca(null);
			}else {
				macchina.setMarca(req.getMarca());
			}
		}
		
		if(req.getAnnoProduzione() != null) {
			if(req.getAnnoProduzione().isBefore(Year.of(2006)) || req.getAnnoProduzione().isAfter(Year.now()))
				throw new VeicoliException("veicolo.anno.invalid");
			else macchina.setAnnoProduzione(req.getAnnoProduzione());
		}
		
		if(!req.getModello().isEmpty() || !req.getModello().isBlank()) {
			if(macchina.getModello().equals(req.getModello())) {
				req.setModello(null);
			}else {
				macchina.setModello(req.getModello());
			}
		}
		
		macchinaRepository.save(macchina);
		
		MacchinaResponse res = modelMapper.map(macchina, MacchinaResponse.class);

		res.setTipoVeicoloDesc(macchina.getTipoVeicolo().getDescrizione());
		res.setCategoriaDesc(macchina.getCategoria().getDescrizione());
		res.setTipoAlimentazioneDesc(macchina.getTipoAlimentazione().getDescrizione());

		return res;
	}

	@Override
	public List<MacchinaResponse> getAll() throws VeicoliException{
		return modelMapper.map(macchinaRepository.findAll(), new TypeToken<List<MacchinaResponse>>() {}.getType());
	}

	@Override
	public MacchinaResponse findById(Integer id) throws VeicoliException{
		Macchina macchina = macchinaRepository.findById(id)
				.orElseThrow(() -> new VeicoliException("veicolo.id.invalid"));
		return modelMapper.map(macchina, MacchinaResponse.class);
	}

	@Transactional
	@Override
	public ResponseDTO remove(Integer id) throws VeicoliException{
		Macchina macchina = macchinaRepository.findById(id)
				.orElseThrow(() -> new VeicoliException("veicolo.id.invalid"));
		macchinaRepository.delete(macchina);
		return ResponseDTO.builder().msg("Macchina con id: " + id + " eliminata").build();
	}

}
