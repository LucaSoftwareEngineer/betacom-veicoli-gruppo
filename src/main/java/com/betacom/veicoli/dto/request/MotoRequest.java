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
public class MotoRequest {

	private String targa;
	private Short cilindrata;
	private Integer tipoVeicoloId;
	private Integer numeroRuote;
	private Integer tipoAlimentazioneId;
	private Integer categoriaId;
	private String colore;
	private String marca;
	private Year annoProduzione;
	private String modello;

}
