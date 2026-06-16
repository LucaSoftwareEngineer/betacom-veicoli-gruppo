package com.betacom.veicoli.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "moto")
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "veicolo_id")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true) @ToString
public class Moto extends Veicolo {

	@Column(nullable = false)
	private Short cilindrata;
	
	@Column(nullable = false)
	private String targa;
	
}
