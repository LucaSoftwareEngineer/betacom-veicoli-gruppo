package com.betacom.veicoli.services.implementations;

import java.time.Year;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.veicoli.dto.request.MacchinaRequest;
import com.betacom.veicoli.dto.response.MacchinaResponse;
import com.betacom.veicoli.dto.response.ResponseDTO;
import com.betacom.veicoli.exceptions.VeicoliException;
import com.betacom.veicoli.models.Macchina;
import com.betacom.veicoli.repositories.CategoriaRepository;
import com.betacom.veicoli.repositories.MacchinaRepository;
import com.betacom.veicoli.repositories.TipoAlimentazioneRepository;
import com.betacom.veicoli.repositories.TipoVeicoloRepository;
import com.betacom.veicoli.services.interfaces.IMacchinaServices;

import lombok.RequiredArgsConstructor;

@Service
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
		
		if(!tipoVeicoloRepository.existsById(req.getTipoVeicoloId()))
			throw new VeicoliException("veicolo.tipo.invalid");
		
		if(!tipoAlimentazioneRepository.existsById(req.getTipoAlimentazioneId()))
			throw new VeicoliException("veicolo.tipo.alim.invalid");
		
		if(!categoriaRepository.existsById(req.getCategoriaId()))
			throw new VeicoliException("veicolo.categoria.invalid");
		
		if(req.getAnnoProduzione().isBefore(Year.of(2006)))
			throw new VeicoliException("veicolo.anno.invalid");
			
		Macchina macchina = modelMapper.map(req, Macchina.class);
		macchinaRepository.save(macchina);
		
		return modelMapper.map(macchina, MacchinaResponse.class);
	}

	@Transactional
	@Override
	public MacchinaResponse update(Integer id, MacchinaRequest req) throws VeicoliException{
		
		Macchina macchina = macchinaRepository.findById(id)
				.orElseThrow(() -> new VeicoliException("veicolo.id.invalid"));
		
		if(req.getTarga() != null) {
			if(macchina.getTarga().equals(req.getTarga())) {
				req.setTarga(null);
			}
		    if(macchinaRepository.existsByTarga(req.getTarga()))
		        throw new VeicoliException("macchina.targa.exists");
		}
		
		if(req.getCilindrata() != null) {
			if(macchina.getCilindrata().equals(req.getCilindrata())) {
				req.setCilindrata(null);
			}
		}
		
		if(req.getNumeroPorte() != null) {
			if(req.getNumeroPorte() == 4)
				throw new VeicoliException("macchina.porte.invalid");
		}
		
		if(req.getTipoVeicoloId() != null) {
			if(!tipoVeicoloRepository.existsById(req.getTipoVeicoloId()))
				throw new VeicoliException("veicolo.tipo.invalid");
		}
		
		if(req.getNumeroRuote() != null) {
			if(req.getNumeroRuote() != 4)
				throw new VeicoliException("veicolo.num.ruote.invalid");
		}
		
		if(req.getTipoAlimentazioneId() != null) {
			if(!tipoAlimentazioneRepository.existsById(req.getTipoAlimentazioneId()))
				throw new VeicoliException("veicolo.tipo.alim.invalid");
		}
		
		if(req.getCategoriaId() != null) {
			if(!categoriaRepository.existsById(req.getCategoriaId()))
				throw new VeicoliException("veicolo.categoria.invalid");
		}
		
		if(!req.getColore().isEmpty() || !req.getColore().isBlank()) {
			if(macchina.getColore().equals(req.getColore())) {
				req.setColore(null);
			}
		}
		
		if(!req.getMarca().isEmpty() || !req.getMarca().isBlank()) {
			if(macchina.getMarca().equals(req.getMarca())) {
				req.setMarca(null);
			}
		}
		
		if(req.getAnnoProduzione() != null) {
			if(req.getAnnoProduzione().isBefore(Year.of(2006)) || req.getAnnoProduzione().isAfter(Year.now()))
				throw new VeicoliException("veicolo.anno.invalid");
		}
		
		if(!req.getModello().isEmpty() || !req.getModello().isBlank()) {
			if(macchina.getModello().equals(req.getModello())) {
				req.setModello(null);
			}
		}
		
		if(req.getTarga() != null) macchina.setTarga(req.getTarga());
		if(req.getCilindrata() != null) macchina.setCilindrata(req.getCilindrata());
		if(req.getNumeroPorte() != null) macchina.setNumeroPorte(req.getNumeroPorte());
		if(req.getNumeroRuote() != null) macchina.setNumeroRuote(req.getNumeroRuote());
		if(req.getColore() != null) macchina.setColore(req.getColore());
		if(req.getMarca() != null) macchina.setMarca(req.getMarca());
		if(req.getModello() != null) macchina.setModello(req.getModello());
		if(req.getAnnoProduzione() != null) macchina.setAnnoProduzione(req.getAnnoProduzione());

		if(req.getTipoVeicoloId() != null)
		    macchina.setTipoVeicolo(tipoVeicoloRepository.findById(req.getTipoVeicoloId()).get());

		if(req.getTipoAlimentazioneId() != null)
		    macchina.setTipoAlimentazione(tipoAlimentazioneRepository.findById(req.getTipoAlimentazioneId()).get());

		if(req.getCategoriaId() != null)
		    macchina.setCategoria(categoriaRepository.findById(req.getCategoriaId()).get());

		macchinaRepository.save(macchina);

		return modelMapper.map(macchina, MacchinaResponse.class);
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

	@Override
	public ResponseDTO remove(Integer id) throws VeicoliException{
		Macchina macchina = macchinaRepository.findById(id)
				.orElseThrow(() -> new VeicoliException("veicolo.id.invalid"));
		macchinaRepository.delete(macchina);
		return ResponseDTO.builder().msg("Macchina con id: " + id + " eliminata").build();
	}

}
