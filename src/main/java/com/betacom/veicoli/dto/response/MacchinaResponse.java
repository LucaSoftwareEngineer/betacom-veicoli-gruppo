package com.betacom.veicoli.dto.response;

import java.time.Year;

import com.betacom.veicoli.dto.response.tipi.CategoriaResponse;
import com.betacom.veicoli.dto.response.tipi.TipoAlimentazioneResponse;
import com.betacom.veicoli.dto.response.tipi.TipoVeicoloResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor@NoArgsConstructor@Getter@Setter
public class MacchinaResponse {

	private Integer id;
	private TipoVeicoloResponse tipoVeicolo;
	private CategoriaResponse categoria;
	private TipoAlimentazioneResponse tipoAlimentazione;
	private String targa;
	private String marca;
	private String modello;
	private Year annoProduzione;
	private Short cilindrata;
	private Short numeroPorte;
	private Short numeroRuote;
	private String colore;
	
}
