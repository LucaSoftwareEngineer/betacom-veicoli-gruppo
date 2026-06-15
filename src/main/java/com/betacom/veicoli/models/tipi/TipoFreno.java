package com.betacom.veicoli.models.tipi;

import java.util.List;

import com.betacom.veicoli.models.Bici;

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
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "tipo_freno")
public class TipoFreno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tip_fre_id")
	private Integer id;
	
	@Column(name = "tip_fre_descrizione", nullable = false)
	private String descrizione;
	
	@OneToMany(mappedBy = "tipoFreno", fetch = FetchType.EAGER)
	private List<Bici> bici;
	
}
