package com.betacom.veicoli.viewmodels;

import java.util.List;

import com.betacom.veicoli.dto.response.VwComplessivoVeicoliResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VwComplessivoVeicoliViewModel {

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
