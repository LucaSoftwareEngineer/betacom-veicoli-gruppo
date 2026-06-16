package com.betacom.veicoli.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.veicoli.models.tipi.TipoFreno;

@Repository
public interface TipoFrenoRepository extends JpaRepository<TipoFreno, Integer>{

}
