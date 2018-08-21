package gnj_soft.salsa.club.dance.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import gnj_soft.salsa.club.dance.AppTestConfig;
import gnj_soft.salsa.club.dance.model.Member;

/**
 * Test class for {@link MemberService}
 * @author gnj_soft
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestConfig.class)
@Transactional
public class MemberServiceTest {
	
	@Autowired
	private MemberService membreService;
	
	@Test
	public void saveMembre() {
		Assert.assertTrue(this.membreService.getMembers().isPresent());
	}
	
	@Test
	public void getMembers() {
		
		Optional<List<Member>> optionalMembers = this.membreService.getMembers();
		
		Assert.assertTrue(optionalMembers.isPresent());
		Assert.assertEquals(12, optionalMembers.get().size());
	}
	
	@Test
	public void getMemberByMemberId() {
		
		String lastName = "JEF";
		Optional<Member> optionalMember = this.membreService.getMemberByMemberId(this.getMemberIdForGivingLastName(lastName, ""));
		
		Assert.assertTrue(optionalMember.isPresent());
		Member member = optionalMember.get();
		Assert.assertTrue(lastName.equals(member.getLastName()));
	}
	
	@Test
	public void getMemberByLastName() {
		
		String lastName = "DUSSEAUX";
		String firstName = "Marion";
		Optional<Member> optionalMember = this.membreService.getMemberByLastName(lastName, "");
		
		Assert.assertTrue(optionalMember.isPresent());
		Member member = optionalMember.get();
		Assert.assertTrue(lastName.equals(member.getLastName()) && firstName.equals(member.getFirstName()));
		Assert.assertEquals(0, member.getLevel().longValue());
	}
	
	@Test
	public void getMembersByLevel() {
		
		Integer level = 3;
		Optional<List<Member>> optionalMembers = this.membreService.getMembersByLevel(level);
		
		Assert.assertTrue(optionalMembers.isPresent());
		List<Member> members = optionalMembers.get();
		members.forEach(member -> Assert.assertEquals(level, member.getLevel()));
		Assert.assertEquals(3, members.size());
	}
	
	@Test
	public void isMemberExist() {
		
		String lastName = "DE FONTES";
		String firstName = "Christine";
		Optional<Boolean> optionalBoolean = this.membreService.isMemberExist(this.getMemberIdForGivingLastName(lastName, firstName));
		Assert.assertTrue(optionalBoolean.isPresent());
		Assert.assertTrue(optionalBoolean.get());
	}
	
	@Test
	public void countExistingMembers() {
		
		OptionalLong optionalLong = this.membreService.countExistingMembers();
		Assert.assertTrue(optionalLong.isPresent());
		Assert.assertEquals(12, optionalLong.getAsLong());
	}
	
	@Test
	public void updateMember() {
	
		String lastName = "DRUESNE";
		Integer newLevel = 2;
		Optional<Member> optionalMember = this.membreService.getMemberByLastName(lastName, "");
		Assert.assertTrue(optionalMember.isPresent());
		Member member = optionalMember.get();
		member.setLevel(newLevel);
		this.membreService.updateMember(member);
		
		optionalMember = this.membreService.getMemberByLastName(lastName, "");
		Assert.assertTrue(optionalMember.isPresent());
		Assert.assertEquals(newLevel, optionalMember.get().getLevel());
	}
	
	@Test
	public void deleteMemberById() {
		
		String lastName = "CARMEN";
		String firstName = "Mihaela";
		Member mihaela = new Member(null, firstName, lastName, 2);
		this.membreService.saveMember(mihaela);
		
		OptionalLong optionalLong = this.membreService.countExistingMembers();
		Assert.assertTrue(optionalLong.isPresent());
		Assert.assertEquals(13, optionalLong.getAsLong());
		
		Long memberId = this.getMemberIdForGivingLastName(lastName, firstName);
		this.membreService.deleteMemberById(memberId);
		
		Optional<Boolean> optionalBoolean = this.membreService.isMemberExist(memberId);
		Assert.assertTrue(optionalBoolean.isPresent());
		Assert.assertFalse(optionalBoolean.get());
		
		optionalLong = this.membreService.countExistingMembers();
		Assert.assertTrue(optionalLong.isPresent());
		Assert.assertEquals(12, optionalLong.getAsLong());
	}
	
	@Before
	public void saveTestData() {
		
		List<Member> members = new ArrayList<Member>();
		Member raphaella = new Member(null, "Raphaëlla", "STUDENT", 1);
		members.add(raphaella);
		Member gigi = new Member(null, "Gigi", "JEF", 4);
		members.add(gigi);
		Member aurelie = new Member(null, "Aurélie", "SALSA", 2);
		members.add(aurelie);
		Member christine = new Member(null, "Christine", "DE FONTES", 3);
		members.add(christine);
		Member florence = new Member(null, "Florence", "BOET", 3);
		members.add(florence);
		Member rachid = new Member(null, "Rachid", "GUENDOUZE", 4);
		members.add(rachid);
		Member aurore = new Member(null, "Aurore", "LERICHE MUNOZ", 0);
		members.add(aurore);
		Member carles = new Member(null, "Carles", "KOUAM", 2);
		members.add(carles);
		Member richard = new Member(null, "Richard", "PULA", 1);
		members.add(richard);
		Member tima = new Member(null, "Tima Zahra", "DIRANE", 0);
		members.add(tima);
		Member marion = new Member(null, "Marion", "DUSSEAUX", 0);
		members.add(marion);
		Member jose = new Member(null, "Joséphine", "DRUESNE", 3);
		members.add(jose);
		
		members.forEach(member -> this.membreService.saveMember(member));
	}
	
	public Long getMemberIdForGivingLastName(String lastName, String firstName) {
		Optional<Member> optionalMember = this.membreService.getMemberByLastName(lastName, firstName);
		Assert.assertTrue(optionalMember.isPresent());
		return optionalMember.get().getMemberId();
	}
}
