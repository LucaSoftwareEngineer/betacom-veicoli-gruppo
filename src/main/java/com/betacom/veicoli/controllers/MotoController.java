package com.betacom.veicoli.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.veicoli.dto.request.MacchinaRequest;
import com.betacom.veicoli.dto.request.MotoRequest;
import com.betacom.veicoli.dto.response.MacchinaResponse;
import com.betacom.veicoli.dto.response.MotoResponse;
import com.betacom.veicoli.dto.response.ResponseDTO;
import com.betacom.veicoli.exceptions.ValidationGroups;
import com.betacom.veicoli.exceptions.VeicoliException;
import com.betacom.veicoli.services.interfaces.IMacchinaServices;
import com.betacom.veicoli.services.interfaces.IMotoServices;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/moto/")
public class MotoController {
	
private final IMotoServices iMotoServices;
	
	@Operation(
            summary = "CREATE MOTO",
            description = "Questo metodo serve a inserire una nuova moto sul database",
            tags = {"Moto"}
    )
    @PostMapping
    public ResponseEntity<MotoResponse> create(
    		@RequestBody @Validated(ValidationGroups.Create.class) MotoRequest req
    		) throws VeicoliException{
        return new ResponseEntity<>(iMotoServices.create(req), HttpStatus.CREATED);
    }
	
	@Operation(
            summary = "UPDATE MOTO",
            description = "Questo metodo serve a modificare una moto",
            tags = {"Moto"}
    )
    @PutMapping("/{id}")
    public ResponseEntity<MotoResponse> update(
    		@RequestBody MotoRequest req, 
    		@PathVariable @Min(value = 1, message = "id deve essere un numero intero maggiore di 0") Integer id
    		) throws VeicoliException{
        return new ResponseEntity<>(iMotoServices.update(id, req), HttpStatus.OK);
    }
	
	@Operation(
            summary = "GET ALL MOTO",
            description = "Questo metodo restituisce una lista di tutte le moto presenti sul database",
            tags = {"Moto"}
    )
    @GetMapping("/list")
    public ResponseEntity<List<MotoResponse>> getAll() throws VeicoliException{
        return new ResponseEntity<>(iMotoServices.getAll(), HttpStatus.OK);
    }
	
	@Operation(
            summary = "FIND MOTO BY ID",
            description = "Questo metodo serve a visualizzare la moto selezionata con l'id",
            tags = {"Moto"}
    )
    @GetMapping("/list/{id}")
    public ResponseEntity<MotoResponse> findById(@Min(value = 1, message = "id deve essere un numero intero maggiore di 0") Integer id) throws VeicoliException{
        return new ResponseEntity<>(iMotoServices.findById(id), HttpStatus.OK);
    }
	
	@Operation(
            summary = "DELETE MOTO BY ID",
            description = "Questo metodo serve a eliminare la moto selezionata con l'id",
            tags = {"Moto"}
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> remove(@Min(value = 1, message = "id deve essere un numero intero maggiore di 0") Integer id) throws VeicoliException{
        return new ResponseEntity<>(iMotoServices.remove(id), HttpStatus.OK);
    }
}
