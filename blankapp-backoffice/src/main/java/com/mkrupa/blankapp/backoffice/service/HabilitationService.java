package com.mkrupa.blankapp.backoffice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkrupa.blankapp.backoffice.domain.Habilitation;
import com.mkrupa.blankapp.backoffice.repository.HabilitationRepository;

/**
 * Business Service on habilitation management 
 * @author mkrupa
 *
 */
@Service
public class HabilitationService {

	/** The habilitation repository */
	@Autowired
	private HabilitationRepository habilitationRepo;
	
	/**
	 * Return all habilitations
	 * @return List<Habilitation> : the profiles
	 */
	public List<Habilitation> listAllHabilitations() {
		return habilitationRepo.findAll();
	}
}
