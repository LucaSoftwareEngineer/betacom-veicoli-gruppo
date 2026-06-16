package com.betacom.veicoli.dto.request;

import java.time.Year;

import com.betacom.veicoli.exceptions.ValidationGroups;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BiciRequest {


	@NotNull(groups = ValidationGroups.Create.class, message = "bici.num.marce.null")
	@NotBlank(groups = ValidationGroups.Create.class, message = "bici.num.marce.null")
	private Short numeroMarce;
	
	@NotNull(groups = ValidationGroups.Create.class, message = "bici.tipo.freno.null")
	@NotBlank(groups = ValidationGroups.Create.class, message = "bici.tipo.freno.null")
	private Integer tipoFrenoId;
	
	@NotNull(groups = ValidationGroups.Create.class, message = "bici.tipo.sospensione.null")
	@NotBlank(groups = ValidationGroups.Create.class, message = "bici.tipo.sospensione.null")
	private Integer tipoSospensioneId;
	
	@NotNull(groups = ValidationGroups.Create.class, message = "bici.pieghevole.null")
	@NotBlank(groups = ValidationGroups.Create.class, message = "bici.pieghevole.null")
	private Boolean pieghevole;
	
	@NotNull(groups = ValidationGroups.Create.class, message = "veicolo.tipo.null ")
	@NotBlank(groups = ValidationGroups.Create.class, message = "veicolo.tipo.null ")
	private Integer tipoVeicoloId;
	
	@NotNull(groups = ValidationGroups.Create.class, message = "veicolo.num.ruote.null")
	@NotBlank(groups = ValidationGroups.Create.class, message = "veicolo.num.ruote.null")
	private Short numeroRuote;
	
	@NotNull(groups = ValidationGroups.Create.class, message = "veicolo.tipo.alim.null")
	@NotBlank(groups = ValidationGroups.Create.class, message = "veicolo.tipo.alim.null")
	private Integer tipoAlimentazioneId;
	
	@NotNull(groups = ValidationGroups.Create.class, message = "veicolo.categoria.null")
	@NotBlank(groups = ValidationGroups.Create.class, message = "veicolo.categoria.null")
	private Integer categoriaId;
	
	@NotNull(groups = ValidationGroups.Create.class, message = "veicolo.colore.null")
	@NotBlank(groups = ValidationGroups.Create.class, message = "veicolo.colore.null")
	private String colore;
	
	@NotNull(groups = ValidationGroups.Create.class, message = "veicolo.marca.null")
	@NotBlank(groups = ValidationGroups.Create.class, message = "veicolo.marca.null")
	private String marca;
	
	@NotNull(groups = ValidationGroups.Create.class, message = "veicolo.anno.null")
	@NotBlank(groups = ValidationGroups.Create.class, message = "veicolo.anno.null")
	private Year annoProduzione;
	
	@NotNull(groups = ValidationGroups.Create.class, message = "veicolo.modello.null")
	@NotBlank(groups = ValidationGroups.Create.class, message = "veicolo.modello.null")
	private String modello;
	
}
