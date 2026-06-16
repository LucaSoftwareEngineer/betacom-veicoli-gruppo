package com.betacom.veicoli.models;

import java.time.Year;

import com.betacom.veicoli.models.tipi.Categoria;
import com.betacom.veicoli.models.tipi.TipoAlimentazione;
import com.betacom.veicoli.models.tipi.TipoVeicolo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	private Year annoProduzione;
	
	@Column(nullable = false)
	private Short numeroRuote;
	
	@Column(nullable = false)
	private String colore;
	
	@Column(nullable = false)
	private String marca;
	
	@Column(nullable = false)
	private String modello;
	
	@ManyToOne
	@JoinColumn(name="tipo_veicolo_id", foreignKey = @ForeignKey(name ="fk_tipo_veicolo" ))
	private TipoVeicolo tipoVeicolo;
	
	@ManyToOne
	@JoinColumn(name="vei_tip_alim_id", foreignKey = @ForeignKey(name ="fk_tipo_alimentazione_veicolo" ))
	private TipoAlimentazione tipoAlimentazione;
	
	@ManyToOne
	@JoinColumn(name="categoria_id", foreignKey = @ForeignKey(name ="fk_categoria_veicolo" ))
	private Categoria categoria;

}
