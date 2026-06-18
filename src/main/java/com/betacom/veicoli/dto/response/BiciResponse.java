package com.betacom.veicoli.dto.response;

import java.time.Year;

import com.betacom.veicoli.dto.response.tipi.CategoriaResponse;
import com.betacom.veicoli.dto.response.tipi.TipoAlimentazioneResponse;
import com.betacom.veicoli.dto.response.tipi.TipoFrenoResponse;
import com.betacom.veicoli.dto.response.tipi.TipoSospensioneResponse;
import com.betacom.veicoli.dto.response.tipi.TipoVeicoloResponse;
import com.betacom.veicoli.models.tipi.TipoAlimentazione;
import com.betacom.veicoli.models.tipi.TipoFreno;
import com.betacom.veicoli.models.tipi.TipoSospensione;
import com.betacom.veicoli.models.tipi.TipoVeicolo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BiciResponse {

	private Integer id;
	private TipoVeicoloResponse tipoVeicolo;
	private CategoriaResponse categoria;
	private TipoFrenoResponse tipoFreno;
	private TipoSospensioneResponse tipoSospensione;
	private TipoAlimentazioneResponse tipoAlimentazione;
	private Short numeroMarce;
	private Boolean pieghevole;
	private Short numeroRuote;
	private String colore;
	private String marca;
	private Year annoProduzione;
	private String modello;
	
}
