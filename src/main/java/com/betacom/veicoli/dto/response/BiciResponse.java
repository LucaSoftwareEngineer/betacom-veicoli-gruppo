package com.betacom.veicoli.dto.response;

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
	private Integer numeroMarce;
	private Integer tipoFrenoId;
	private Integer tipoSospensioneId;
	private Boolean pieghevole;
	private Integer tipoVeicoloId;
	private Integer numeroRuote;
	private Integer tipoAlimentazioneId;
	private Integer categoriaId;
	private String colore;
	private String marca;
	private Integer annoProduzione;
	private String modello;
	
}
