package com.mkrupa.blankapp.backoffice.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.mkrupa.blankapp.backoffice.BackOfficeApplication;
import com.mkrupa.blankapp.backoffice.domain.Habilitation;
import com.mkrupa.blankapp.backoffice.repository.HabilitationRepository;
import com.mkrupa.blankapp.backoffice.service.HabilitationService;

/**
 * Test Class for Habilitation Service.
 * 
 * @author mkrupa
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BackOfficeApplication.class)
public class HabilitationServiceTest {

	/** Habilitation Service */
	@Autowired
	@InjectMocks
	private HabilitationService habilitationService;
	
	/** Mock habilitation Repo */
	@Mock
	private HabilitationRepository habilitationRepo;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		List<Habilitation> vHabilitations = new ArrayList<Habilitation>();
		Habilitation vHabilitation = new Habilitation();
		vHabilitation.setId(1L);
		vHabilitation.setCode("users.list");
		vHabilitation.setLabel("Utilisateurs - Voir");
		vHabilitations.add(vHabilitation);
		Mockito.when(habilitationRepo.findAll()).thenReturn(vHabilitations);
	}
	
	/**
	 * Test listAllHabilitations Method 
	 */
	@Test
	public void listAllHabilitationsTest() {
		List<Habilitation> vHabilitations = habilitationService.listAllHabilitations();
		Assert.notNull(vHabilitations, "habilitations souldn't be null");
		Assert.notEmpty(vHabilitations, "habilitations shouldn't be empy");
	}
}
