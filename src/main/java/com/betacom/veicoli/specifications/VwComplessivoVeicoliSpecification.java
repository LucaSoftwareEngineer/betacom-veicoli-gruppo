package com.betacom.veicoli.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import com.betacom.veicoli.dto.request.VwComplessivoVeicoliRequest;
import com.betacom.veicoli.models.VwComplessivoVeicoli;

public class VwComplessivoVeicoliSpecification {

	public static Specification<VwComplessivoVeicoli> search(VwComplessivoVeicoliRequest request) {
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (request.getIdVeicoli() != null)
				predicates.add(cb.equal(root.get("idVeicoli"), request.getIdVeicoli()));
			if (request.getTipoVeicolo() != null)
				predicates.add(cb.equal(root.get("tipoVeicolo"), request.getTipoVeicolo()));
			if (request.getNumeroRuote() != null)
				predicates.add(cb.equal(root.get("numeroRuote"), request.getNumeroRuote()));
			if (request.getTipoAlimentazione() != null)
				predicates.add(cb.equal(root.get("tipoAlimentazione"), request.getTipoAlimentazione()));
			if (request.getCategoria() != null)
				predicates.add(cb.equal(root.get("categoria"), request.getCategoria()));
			if (request.getAnnoProduzione() != null)
				predicates.add(cb.equal(root.get("annoProduzione"), request.getAnnoProduzione()));
			if (request.getMacchinaCilindrata() != null)
				predicates.add(cb.equal(root.get("macchinaCilindrata"), request.getMacchinaCilindrata()));
			if (request.getNumeroPorte() != null)
				predicates.add(cb.equal(root.get("numeroPorte"), request.getNumeroPorte()));
			if (request.getNumeroMarce() != null)
				predicates.add(cb.equal(root.get("numeroMarce"), request.getNumeroMarce()));
			if (request.getTipoFreno() != null)
				predicates.add(cb.equal(root.get("tipoFreno"), request.getTipoFreno()));
			if (request.getTipoSospensione() != null)
				predicates.add(cb.equal(root.get("tipoSospensione"), request.getTipoSospensione()));
			if (request.getPieghevole() != null)
				predicates.add(cb.equal(root.get("pieghevole"), request.getPieghevole()));
			if (request.getMotoCilindrata() != null)
				predicates.add(cb.equal(root.get("motoCilindrata"), request.getMotoCilindrata()));

			if (request.getColore() != null && !request.getColore().trim().isEmpty())
				predicates.add(cb.like(root.get("colore"), "%" + request.getColore() + "%"));
			if (request.getMarca() != null && !request.getMarca().trim().isEmpty())
				predicates.add(cb.like(root.get("marca"), "%" + request.getMarca() + "%"));
			if (request.getModello() != null && !request.getModello().trim().isEmpty())
				predicates.add(cb.like(root.get("modello"), "%" + request.getModello() + "%"));
			if (request.getMacchinaTarga() != null && !request.getMacchinaTarga().trim().isEmpty())
				predicates.add(cb.equal(root.get("macchinaTarga"), request.getMacchinaTarga()));
			if (request.getMotoTarga() != null && !request.getMotoTarga().trim().isEmpty())
				predicates.add(cb.equal(root.get("motoTarga"), request.getMotoTarga()));

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
}