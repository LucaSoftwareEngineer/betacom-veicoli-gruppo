package com.betacom.veicoli.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "bici")
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "veicolo_id")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true) @ToString
public class Bici extends Veicolo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
	
	@Column(nullable = false)
	private Short numeroMarce;
	
	@Column(nullable = false)
	private Boolean pieghevole;
	
	private Integer tipoFrenoId;
	
	private Integer tipoSospensione;
	
}
