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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.mkrupa.blankapp.backoffice.BackOfficeApplication;
import com.mkrupa.blankapp.backoffice.domain.Profile;
import com.mkrupa.blankapp.backoffice.exception.BusinessException;
import com.mkrupa.blankapp.backoffice.repository.ProfileRepository;
import com.mkrupa.blankapp.backoffice.repository.UserRepository;
import com.mkrupa.blankapp.backoffice.service.ProfileService;

/**
 * Test Class for Profile Service.
 * 
 * @author mkrupa
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BackOfficeApplication.class)
public class ProfileServiceTest {

	/** Profile Service */
	@Autowired
	@InjectMocks
	private ProfileService profileService;
	
	/** Mock Profile Repo */
	@Mock
	private ProfileRepository profileRepo;
	
	/** Mock User Repo */
	@Mock
	private UserRepository userRepo;
	
	/** Variable for mocks */
	private Long profileId = 1L;
	private Long profileIdUnknown = 13L;
	private String profileAdminLabel = "Administrateur";
	private String profileTestLabel = "Test";
	
	/**
	 * Initialize mocks
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		List<Profile> vProfiles = new ArrayList<Profile>();
		Profile vProfile = new Profile();
		vProfile.setId(1L);
		vProfile.setLibelle("Administrateur");
		vProfile.setDescription("Super admin avec tous les pouvoirs");
		vProfile.setHabilitations(null);
		vProfiles.add(vProfile);
		Mockito.when(profileRepo.findAll()).thenReturn(vProfiles);
		Mockito.when(profileRepo.getOne(profileId)).thenReturn(vProfile);
		Mockito.when(profileRepo.getOne(profileIdUnknown)).thenReturn(null);
		Mockito.when(profileRepo.findByLibelle(profileAdminLabel)).thenReturn(vProfile);
		Mockito.when(profileRepo.findByLibelle(profileTestLabel)).thenReturn(null);
		Mockito.when(profileRepo.save(Mockito.any(Profile.class))).thenAnswer(new Answer<Profile>() {
			public Profile answer(InvocationOnMock invocation) throws Throwable {
				Profile vProfile = (Profile) invocation.getArguments()[0];
				vProfile.setId(1000L);
				return vProfile;
			}
		});
		Mockito.when(userRepo.countQuery(Mockito.anyString())).thenReturn(1L);
	}
	
	/**
	 * Test listAllProfiles Method 
	 */
	@Test
	public void listAllProfilesTest() {
		List<Profile> vProfiles = profileService.listAllProfiles();
		Assert.notNull(vProfiles, "profiles souldn't be null");
		Assert.notEmpty(vProfiles, "profiles shouldn't be empy");
	}
	
	/**
	 * Test getProfile Method
	 */
	@Test
	public void getProfileTest() {
		try {
			Profile vProfile = profileService.getProfile(profileId);
			Assert.notNull(vProfile, "profile souldn't be null");
			org.junit.Assert.assertEquals("Label must be equal", vProfile.getLibelle(), "Administrateur");
		} catch (BusinessException vBe) {
			Assert.isTrue(false, "Business Exception : " + vBe.getMessage());
		}
	}
	
	/**
	 * Test getProfile Method with unknown profile
	 */
	@Test
	public void getProfileUnknownTest() {
		try {
			profileService.getProfile(profileIdUnknown);
			Assert.isTrue(false, "Profile must be unknown");
		} catch (BusinessException vBe) {
			org.junit.Assert.assertEquals("BE code should be error.profile.notfound", 
					"error.profile.notfound", vBe.getCode());
		}
	}
	
	/**
	 * Test createProfile Method
	 */
	@Test
	public void createProfileTest() {
		try {
			Profile vProfile = new Profile();
			vProfile.setLibelle(profileTestLabel);
			vProfile.setDescription("hdsjhdkjqs");
			vProfile.setHabilitations(null);
			
			profileService.createProfile(vProfile);
			
			Assert.notNull(vProfile.getId(), "profile ID souldn't be null");
			
		} catch (BusinessException vBe) {
			Assert.isTrue(false, "Business Exception : " + vBe.getMessage());
		}
	}
	
	/**
	 * Test createProfile Method with same label
	 */
	@Test
	public void createProfileSameLabelTest() {
		try {
			Profile vProfile = new Profile();
			vProfile.setLibelle(profileAdminLabel);
			vProfile.setDescription("hdsjhdkjqs");
			vProfile.setHabilitations(null);
			
			profileService.createProfile(vProfile);
			
			Assert.isTrue(false, "An exception must be thrown");
			
		} catch (BusinessException vBe) {
			org.junit.Assert.assertEquals("BE code should be error.profile.create.same.libelle", 
					"error.profile.create.same.libelle", vBe.getCode());
		}
	}
	
	/**
	 * Test deleteProfile Method
	 */
	@Test
	public void deleteProfileTest() {
		try {
			
			profileService.deleteProfile(profileId);
			
			Assert.isTrue(false, "An exception must be thrown");
			
		} catch (BusinessException vBe) {
			org.junit.Assert.assertEquals("BE code should be error.profile.remove.still.users", 
					"error.profile.remove.still.users", vBe.getCode());
		}
	}
}
