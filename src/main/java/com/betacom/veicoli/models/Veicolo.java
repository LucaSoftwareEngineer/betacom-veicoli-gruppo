package com.betacom.veicoli.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "veicoli")
@Getter @Setter @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true) @ToString
public class Veicolo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
	
	@Column(nullable = false)
	private LocalDate annoProduzione;
	
	@Column(nullable = false)
	private Short numeroRuote;
	
	@Column(nullable = false)
	private String colore;
	
	@Column(nullable = false)
	private String marca;
	
	@Column(nullable = false)
	private String modello;
	

}
