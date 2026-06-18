package com.betacom.veicoli.services.implementations;

import java.time.Year;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.betacom.veicoli.dto.request.MotoRequest;
import com.betacom.veicoli.dto.response.MotoResponse;
import com.betacom.veicoli.dto.response.ResponseDTO;
import com.betacom.veicoli.exceptions.VeicoliException;
import com.betacom.veicoli.models.Moto;
import com.betacom.veicoli.models.tipi.Categoria;
import com.betacom.veicoli.models.tipi.TipoAlimentazione;
import com.betacom.veicoli.models.tipi.TipoVeicolo;
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
			throw new VeicoliException("moto.targa.exists");
		
		TipoVeicolo tipoVeicolo = tipoVeicoloRepository.findById(req.getTipoVeicoloId())
			    .orElseThrow(() -> new VeicoliException("veicolo.tipo.invalid"));
		if(!tipoVeicolo.getDescrizione().equalsIgnoreCase("MOTO"))
			throw new VeicoliException("veicolo.tipo.invalid");
		
		TipoAlimentazione tipoAlimentazione = tipoAlimentazioneRepository.findById(req.getTipoAlimentazioneId())
				.orElseThrow(() -> new VeicoliException("veicolo.tipo.alim.invalid"));
		if(tipoAlimentazione.getDescrizione().equalsIgnoreCase("MANUALE") || tipoAlimentazione.getDescrizione().equalsIgnoreCase("DIESEL"))
			throw new VeicoliException("veicolo.tipo.alim.invalid");
		
		Categoria categoria = categoriaRepository.findById(req.getCategoriaId())
				.orElseThrow(() -> new VeicoliException("veicolo.categoria.invalid"));
		if(categoria.getDescrizione().equalsIgnoreCase("FUORISTRADA") || categoria.getDescrizione().equalsIgnoreCase("SUV"))
			throw new VeicoliException("veicolo.categoria.invalid");
		
		if(req.getAnnoProduzione().isBefore(Year.of(2006)))
			throw new VeicoliException("veicolo.anno.invalid");
		
		Moto moto = modelMapper.map(req, Moto.class);
		moto.setId(null);
		moto.setCategoria(categoria);
		moto.setTipoVeicolo(tipoVeicolo);
		moto.setTipoAlimentazione(tipoAlimentazione);
		motoRepository.save(moto);
		
		MotoResponse res = modelMapper.map(moto, MotoResponse.class);

		return res;
	}

	@Override
	public MotoResponse update(Integer id, MotoRequest req) throws VeicoliException{
		
		Moto moto = motoRepository.findById(id)
				.orElseThrow(() -> new VeicoliException("veicolo.id.invalid"));
		
		if(req.getTarga() != null && !req.getTarga().isBlank()) {
		    if(!req.getTarga().equals(moto.getTarga())) {
		        if(motoRepository.existsByTarga(req.getTarga()))
		            throw new VeicoliException("moto.targa.exists");
		        moto.setTarga(req.getTarga());
		    }
		}
		
		if(req.getCilindrata() != null) {
			if(moto.getCilindrata().equals(req.getCilindrata())) {
				req.setCilindrata(null);
			}else {
			moto.setCilindrata(req.getCilindrata());
			}
		}
		
		if(req.getTipoVeicoloId() != null) {
			TipoVeicolo tipoVeicolo = tipoVeicoloRepository.findById(req.getTipoVeicoloId())
				    .orElseThrow(() -> new VeicoliException("veicolo.tipo.invalid"));
			if(!tipoVeicolo.getDescrizione().equalsIgnoreCase("MOTO"))
				throw new VeicoliException("veicolo.tipo.invalid");
			else moto.setTipoVeicolo(tipoVeicolo);
		}
		
		if(req.getNumeroRuote() != null) {
			if(req.getNumeroRuote() < 2 || req.getNumeroRuote() > 3)
				throw new VeicoliException("veicolo.num.ruote.invalid");
			else moto.setNumeroRuote(req.getNumeroRuote());
		}
		
		if(req.getTipoAlimentazioneId() != null) {
			TipoAlimentazione tipoAlimentazione = tipoAlimentazioneRepository.findById(req.getTipoAlimentazioneId())
					.orElseThrow(() -> new VeicoliException("veicolo.tipo.alim.invalid"));
			if(tipoAlimentazione.getDescrizione().equalsIgnoreCase("MANUALE") || tipoAlimentazione.getDescrizione().equalsIgnoreCase("DIESEL"))
				throw new VeicoliException("veicolo.tipo.alim.invalid");
			else moto.setTipoAlimentazione(tipoAlimentazione);
		}
		
		if(req.getCategoriaId() != null) {
			Categoria categoria = categoriaRepository.findById(req.getCategoriaId())
					.orElseThrow(() -> new VeicoliException("veicolo.categoria.invalid"));
			if(categoria.getDescrizione().equalsIgnoreCase("FUORISTRADA") || categoria.getDescrizione().equalsIgnoreCase("SUV"))
				throw new VeicoliException("veicolo.categoria.invalid");
			else moto.setCategoria(categoria);
		}
		
		if(!req.getColore().isEmpty() || !req.getColore().isBlank()) {
			if(moto.getColore().equals(req.getColore())) {
				req.setColore(null);
			}else {
				moto.setColore(req.getColore());
			}
		}
		
		if(!req.getMarca().isEmpty() || !req.getMarca().isBlank()) {
			if(moto.getMarca().equals(req.getMarca())) {
				req.setMarca(null);
			}else {
				moto.setMarca(req.getMarca());
			}
		}
		
		if(req.getAnnoProduzione() != null) {
			if(req.getAnnoProduzione().isBefore(Year.of(2006)) || req.getAnnoProduzione().isAfter(Year.now()))
				throw new VeicoliException("veicolo.anno.invalid");
			else moto.setAnnoProduzione(req.getAnnoProduzione());
		}
		
		if(!req.getModello().isEmpty() || !req.getModello().isBlank()) {
			if(moto.getModello().equals(req.getModello())) {
				req.setModello(null);
			}else {
				moto.setModello(req.getModello());
			}
		}

		motoRepository.save(moto);
		
		MotoResponse res = modelMapper.map(moto, MotoResponse.class);

		return res;
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
