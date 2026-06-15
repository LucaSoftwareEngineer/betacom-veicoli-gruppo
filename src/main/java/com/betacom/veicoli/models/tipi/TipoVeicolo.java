package com.betacom.veicoli.models.tipi;

import java.util.List;

import com.betacom.veicoli.models.Veicolo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tipo_veicolo")
public class TipoVeicolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tip_vei_id")
	private Integer id;
	
	@Column(name = "tip_vei_descrizione", nullable = false)
	private String descrizione;
	
	@OneToMany(mappedBy = "tipoVeicolo", fetch = FetchType.EAGER)
	private List<Veicolo> veicoli;
	
}
