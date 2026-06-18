package com.betacom.veicoli.repositories;

import com.betacom.veicoli.models.VwComplessivoVeicoli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VwComplessivoVeicoliRepository extends JpaRepository<VwComplessivoVeicoli, Long>, JpaSpecificationExecutor<VwComplessivoVeicoli> {

}