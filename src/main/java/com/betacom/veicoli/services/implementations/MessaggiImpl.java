package com.betacom.veicoli.services.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.betacom.veicoli.models.system.MessageID;
import com.betacom.veicoli.models.system.Messaggi;
import com.betacom.veicoli.repositories.IMessaggiRepository;
import com.betacom.veicoli.services.interfaces.IMessaggiServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessaggiImpl implements IMessaggiServices {

	private final IMessaggiRepository messaggiRepository;
	
	@Value("${lang}")
	private String lang;
	
	/*
	 * Metodo che recupera il messaggio di sistema
	 */
	@Override
	public String get(String code) {
		String r = null;
		MessageID messageId = MessageID.builder().cod(code).lang(lang).build();
		Optional<Messaggi> messaggio = messaggiRepository.findById(messageId);
		
		if (messaggio.isEmpty())
			r = code;
		else
			r = messaggio.get().getMessaggio();
		
		return r;
	}

}
