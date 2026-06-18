package com.betacom.veicoli.dto.response;

import com.betacom.veicoli.dto.response.tipi.TipoAlimentazioneResponse;
import com.betacom.veicoli.dto.response.tipi.TipoFrenoResponse;
import com.betacom.veicoli.dto.response.tipi.TipoSospensioneResponse;
import com.betacom.veicoli.dto.response.tipi.TipoVeicoloResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VwComplessivoVeicoliResponse {

	private Long idVeicoli;
	private Integer numeroRuote;
	private Long categoria;
	private String colore;
	private String marca;
	private Integer annoProduzione;
	private String modello;
	private String macchinaTarga;
	private Integer macchinaCilindrata;
	private Integer numeroPorte;
	private Integer numeroMarce;
	private Boolean pieghevole;
	private String motoTarga;
	private Integer motoCilindrata;
	
	private TipoFrenoResponse tipoFreno;
	private TipoSospensioneResponse tipoSospensione;
	private TipoAlimentazioneResponse tipoAlimentazione;
	private TipoVeicoloResponse tipoVeicolo;
}