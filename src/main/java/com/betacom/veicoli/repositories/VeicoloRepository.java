package com.betacom.veicoli.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.veicoli.models.Veicolo;

@Repository
public interface VeicoloRepository extends JpaRepository<Veicolo, Integer> {

}
