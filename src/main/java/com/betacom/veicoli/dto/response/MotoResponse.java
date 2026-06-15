package com.betacom.veicoli.dto.response;

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
	private Integer cilindrata;
	private Integer tipoVeicoloId;
	private Integer numeroRuote;
	private Integer tipoAlimentazioneId;
	private Integer categoriaId;
	private String colore;
	private String marca;
	private Integer annoProduzione;
	private String modello;
	
}
