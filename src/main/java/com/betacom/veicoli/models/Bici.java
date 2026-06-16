package com.betacom.veicoli.models;

import com.betacom.veicoli.models.tipi.TipoFreno;
import com.betacom.veicoli.models.tipi.TipoSospensione;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@Column(nullable = false)
	private Short numeroMarce;
	
	@Column(nullable = false)
	private Boolean pieghevole;
	
	@ManyToOne
	@JoinColumn(name = "tipo_freno_id", foreignKey = @ForeignKey(name ="fk_bici_tipo_freno" ))
	private TipoFreno tipoFreno;
	
	@ManyToOne
	@JoinColumn(name = "tipo_sosponsione_id", foreignKey = @ForeignKey(name ="fk_bici_tipo_sospensione" ))
	private TipoSospensione tipoSospensione;
	
}
