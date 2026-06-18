package com.betacom.veicoli.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "vw_complessivo_veicoli")
public class VwComplessivoVeicoli {

	@Id
	@Column(name = "id_veicoli")
	private Long idVeicoli;

	@Column(name = "tipo_veicolo")
	private Long tipoVeicolo;

	@Column(name = "numero_ruote")
	private Integer numeroRuote;

	@Column(name = "tipo_alimentazione")
	private Long tipoAlimentazione;

	@Column(name = "categoria")
	private Long categoria;

	@Column(name = "colore")
	private String colore;

	@Column(name = "marca")
	private String marca;

	@Column(name = "anno_produzione")
	private Integer annoProduzione;

	@Column(name = "modello")
	private String modello;

	@Column(name = "macchina_targa")
	private String macchinaTarga;

	@Column(name = "macchina_cilindrata")
	private Integer macchinaCilindrata;

	@Column(name = "numero_porte")
	private Integer numeroPorte;

	@Column(name = "numero_marce")
	private Integer numeroMarce;

	@Column(name = "tipo_freno")
	private Long tipoFreno;

	@Column(name = "tipo_sospensione")
	private Long tipoSospensione;

	@Column(name = "pieghevole")
	private Boolean pieghevole;

	@Column(name = "moto_targa")
	private String motoTarga;

	@Column(name = "moto_cilindrata")
	private Integer motoCilindrata;
}