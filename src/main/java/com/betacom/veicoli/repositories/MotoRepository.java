package com.betacom.veicoli.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.veicoli.models.Moto;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Integer> {
	

}
