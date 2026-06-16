package com.betacom.veicoli.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.veicoli.models.system.MessageID;
import com.betacom.veicoli.models.system.Messaggi;

@Repository
public interface IMessaggiRepository extends JpaRepository<Messaggi, MessageID> {

}
