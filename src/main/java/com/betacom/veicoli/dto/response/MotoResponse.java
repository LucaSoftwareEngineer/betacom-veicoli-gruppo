package com.betacom.veicoli.dto.response;

import java.time.Year;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MotoResponse {

	private Integer id;
	private String targa;
	private Short cilindrata;
	private Integer tipoVeicoloId;
	private Short numeroRuote;
	private Integer tipoAlimentazioneId;
	private Integer categoriaId;
	private String tipoAlimentazioneDesc;
	private String categoriaDesc;
	private String tipoVeicoloDesc;
	private String colore;
	private String marca;
	private Year annoProduzione;
	private String modello;
	
}
