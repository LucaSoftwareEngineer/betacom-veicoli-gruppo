package com.betacom.veicoli.services.implementations;

import java.time.Year;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.betacom.veicoli.dto.request.MotoRequest;
import com.betacom.veicoli.dto.response.MacchinaResponse;
import com.betacom.veicoli.dto.response.MotoResponse;
import com.betacom.veicoli.dto.response.ResponseDTO;
import com.betacom.veicoli.exceptions.VeicoliException;
import com.betacom.veicoli.models.Macchina;
import com.betacom.veicoli.models.Moto;
import com.betacom.veicoli.repositories.CategoriaRepository;
import com.betacom.veicoli.repositories.MotoRepository;
import com.betacom.veicoli.repositories.TipoAlimentazioneRepository;
import com.betacom.veicoli.repositories.TipoVeicoloRepository;
import com.betacom.veicoli.services.interfaces.IMotoServices;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MotoImpl implements IMotoServices {
	
	private final MotoRepository motoRepository;
	private final ModelMapper modelMapper;
	private final TipoVeicoloRepository tipoVeicoloRepository;
	private final TipoAlimentazioneRepository tipoAlimentazioneRepository;
	private final CategoriaRepository categoriaRepository;

	@Override
	public MotoResponse create(MotoRequest req) throws VeicoliException{
		
		if(motoRepository.existsByTarga(req.getTarga()))
			throw new VeicoliException("macchina.targa.exists");
		
		if(!tipoVeicoloRepository.existsById(req.getTipoVeicoloId()))
			throw new VeicoliException("veicolo.tipo.invalid");
		
		if(!tipoAlimentazioneRepository.existsById(req.getTipoAlimentazioneId()))
			throw new VeicoliException("veicolo.tipo.alim.invalid");
		
		if(!categoriaRepository.existsById(req.getCategoriaId()))
			throw new VeicoliException("veicolo.categoria.invalid");
		
		if(req.getAnnoProduzione().isBefore(Year.of(2006)))
			throw new VeicoliException("veicolo.anno.invalid");
		
		Moto moto = modelMapper.map(req, Moto.class);
		moto.setId(null);
		motoRepository.save(moto);
		
		return modelMapper.map(moto, MotoResponse.class);
	}

	@Override
	public MotoResponse update(Integer id, MotoRequest req) throws VeicoliException{
		
		Moto moto = motoRepository.findById(id)
				.orElseThrow(() -> new VeicoliException("veicolo.id.invalid"));
		
		if(req.getTarga() != null) {
			if(moto.getTarga().equals(req.getTarga())) {
				req.setTarga(null);
			}
		    if(motoRepository.existsByTarga(req.getTarga()))
		        throw new VeicoliException("moto.targa.exists");
		}
		
		if(req.getCilindrata() != null) {
			if(moto.getCilindrata().equals(req.getCilindrata())) {
				req.setCilindrata(null);
			}
		}
		
		if(req.getTipoVeicoloId() != null) {
			if(!tipoVeicoloRepository.existsById(req.getTipoVeicoloId()))
				throw new VeicoliException("veicolo.tipo.invalid");
		}
		
		if(req.getNumeroRuote() != null) {
			if(req.getNumeroRuote() < 2 || req.getNumeroRuote() > 3)
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
			if(moto.getColore().equals(req.getColore())) {
				req.setColore(null);
			}
		}
		
		if(!req.getMarca().isEmpty() || !req.getMarca().isBlank()) {
			if(moto.getMarca().equals(req.getMarca())) {
				req.setMarca(null);
			}
		}
		
		if(req.getAnnoProduzione() != null) {
			if(req.getAnnoProduzione().isBefore(Year.of(2006)) || req.getAnnoProduzione().isAfter(Year.now()))
				throw new VeicoliException("veicolo.anno.invalid");
		}
		
		if(!req.getModello().isEmpty() || !req.getModello().isBlank()) {
			if(moto.getModello().equals(req.getModello())) {
				req.setModello(null);
			}
		}
		
		if(req.getTarga() != null) moto.setTarga(req.getTarga());
		if(req.getCilindrata() != null) moto.setCilindrata(req.getCilindrata());
		if(req.getNumeroRuote() != null) moto.setNumeroRuote(req.getNumeroRuote());
		if(req.getNumeroRuote() != null) moto.setNumeroRuote(req.getNumeroRuote());
		if(req.getColore() != null) moto.setColore(req.getColore());
		if(req.getMarca() != null) moto.setMarca(req.getMarca());
		if(req.getModello() != null) moto.setModello(req.getModello());
		if(req.getAnnoProduzione() != null) moto.setAnnoProduzione(req.getAnnoProduzione());

		if(req.getTipoVeicoloId() != null)
		    moto.setTipoVeicolo(tipoVeicoloRepository.findById(req.getTipoVeicoloId()).get());

		if(req.getTipoAlimentazioneId() != null)
		    moto.setTipoAlimentazione(tipoAlimentazioneRepository.findById(req.getTipoAlimentazioneId()).get());

		if(req.getCategoriaId() != null)
		    moto.setCategoria(categoriaRepository.findById(req.getCategoriaId()).get());

		motoRepository.save(moto);

		return modelMapper.map(moto, MotoResponse.class);
	}

	@Override
	public List<MotoResponse> getAll() throws VeicoliException{
		return modelMapper.map(motoRepository.findAll(), new TypeToken<List<MotoResponse>>() {}.getType());
	}

	@Override
	public MotoResponse findById(Integer id) throws VeicoliException{
		Moto moto = motoRepository.findById(id)
				.orElseThrow(() -> new VeicoliException("veicolo.id.invalid"));
		return modelMapper.map(moto, MotoResponse.class);
	}

	@Override
	public ResponseDTO remove(Integer id) throws VeicoliException{
		Moto moto = motoRepository.findById(id)
				.orElseThrow(() -> new VeicoliException("veicolo.id.invalid"));
		motoRepository.delete(moto);
		return ResponseDTO.builder().msg("Macchina con id: " + id + " eliminata").build();
	}

}
