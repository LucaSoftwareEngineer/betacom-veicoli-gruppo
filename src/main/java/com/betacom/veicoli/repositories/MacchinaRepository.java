package com.betacom.veicoli.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.veicoli.models.Macchina;

@Repository
public interface MacchinaRepository extends JpaRepository<Macchina, Integer> {
	
	boolean existsByTarga(String targa);

}
