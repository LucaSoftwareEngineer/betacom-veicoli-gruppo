package com.betacom.veicoli.dto.request;


import java.time.Year;

import com.betacom.veicoli.exceptions.ValidationGroups;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MotoRequest {
	
	@NotBlank(groups = {ValidationGroups.Create.class} , message = "moto.targa.null")
	@Pattern(
	        regexp = "^[A-Z]{2}[0-9]{2}[A-Z]{2}$",
	        groups = {ValidationGroups.Update.class, ValidationGroups.Create.class},
	        message = "moto.targa.invalid")
	private String targa;
	
	@NotNull(groups = {ValidationGroups.Create.class}, message = "macchina.cilindrata.null")
	private Short cilindrata;
	
	@NotNull(groups = {ValidationGroups.Create.class}, message = "veicolo.tipo.alim.null")
	private Integer tipoVeicoloId;
	
	@NotNull(groups = {ValidationGroups.Create.class}, message = "veicolo.num.ruote.null")
	@Min(value = 2, groups = { ValidationGroups.Create.class}, message = "veicolo.num.ruote.invalid")
	@Max(value = 3, groups = { ValidationGroups.Create.class}, message = "veicolo.num.ruote.invalid")
	private Short numeroRuote;
	
	@NotNull(groups = {ValidationGroups.Create.class}, message = "veicolo.tipo.alim.null")
	private Integer tipoAlimentazioneId;
	
	@NotNull(groups = {ValidationGroups.Create.class}, message = "veicolo.categoria.null")
	private Integer categoriaId;
	
	@NotBlank(groups = {ValidationGroups.Create.class} , message = "veicolo.colore.nul")
	private String colore;
	
	@NotBlank(groups = { ValidationGroups.Create.class} , message =  "veicolo.marca.null")
	private String marca;
	
	@NotNull(groups = { ValidationGroups.Create.class} , message = "veicolo.anno.null")
	@PastOrPresent(groups = { ValidationGroups.Create.class} , message = "veicolo.anno.invalid")
	private Year annoProduzione;
	
	@NotBlank(groups = { ValidationGroups.Create.class} , message =  "veicolo.modello.null")
	private String modello;

}
