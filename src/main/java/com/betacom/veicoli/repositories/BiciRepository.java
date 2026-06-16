package com.betacom.veicoli.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.veicoli.models.Bici;

@Repository
public interface BiciRepository extends JpaRepository<Bici, Integer>{

}
