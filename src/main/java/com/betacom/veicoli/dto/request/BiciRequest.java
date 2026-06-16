package com.betacom.veicoli.dto.request;

import java.time.Year;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BiciRequest {

	private Short numeroMarce;
	private Integer tipoFrenoId;
	private Integer tipoSospensioneId;
	private Boolean pieghevole;
	private Integer tipoVeicoloId;
	private Short numeroRuote;
	private Integer tipoAlimentazioneId;
	private Integer categoriaId;
	private String colore;
	private String marca;
	private Year annoProduzione;
	private String modello;
	
}
