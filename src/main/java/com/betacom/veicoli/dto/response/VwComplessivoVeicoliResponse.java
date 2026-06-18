package com.betacom.veicoli.dto.response;

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
	private Long tipoVeicolo;
	private Integer numeroRuote;
	private Long tipoAlimentazione;
	private Long categoria;
	private String colore;
	private String marca;
	private Integer annoProduzione;
	private String modello;
	private String macchinaTarga;
	private Integer macchinaCilindrata;
	private Integer numeroPorte;
	private Integer numeroMarce;
	private Long tipoFreno;
	private Long tipoSospensione;
	private Boolean pieghevole;
	private String motoTarga;
	private Integer motoCilindrata;

}