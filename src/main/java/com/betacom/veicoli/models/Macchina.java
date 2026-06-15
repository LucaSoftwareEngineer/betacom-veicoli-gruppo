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
@Table(name = "macchine")
@Getter @Setter @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "veicolo_id")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true) @ToString
public class Macchina extends Veicolo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
	
	@Column(nullable = false)
	private Short cilindrata;
	
	@Column(nullable = false)
	private Short numeroPorte;
	
	@Column(nullable = false)
	private String targa;

}
