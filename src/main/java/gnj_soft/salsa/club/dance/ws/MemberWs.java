package gnj_soft.salsa.club.dance.ws;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gnj_soft.salsa.club.dance.model.Member;
import gnj_soft.salsa.club.dance.service.MemberService;

/**
 * This class provides web services for salsa-club members
 * 
 * @author gnj_soft
 */
@RestController
@RequestMapping("members")
public class MemberWs {

	@Autowired
	private MemberService memberService;

	@GetMapping("")
	public List<Member> getMembers() {
		List<Member> membres = new ArrayList<>();
		Optional<List<Member>> optionalMembers = this.memberService.getMembers();
		if(optionalMembers.isPresent()) {
			membres = optionalMembers.get().stream().sorted(Comparator.comparing(Member::getLastName).thenComparing(Member::getFirstName))
			.collect(Collectors.toList());
		}
		return membres;
	}
	
	@GetMapping("{id}")
	public Member getMemberById(@PathVariable Long id) {
		Member member = null;
		Optional<Member> optionalMember = this.memberService.getMemberByMemberId(id);
		if (optionalMember.isPresent()) {
			member = optionalMember.get();
		}
		return member;
	}

	@PostMapping("")
	public void addMember(@RequestBody Member member) {
		this.memberService.saveMember(member);
	}

	@PutMapping("")
	public Member updateMember(@RequestBody Member member) {
		this.memberService.updateMember(member);
		return member;
	}

	@DeleteMapping("{id}")
	public void deleteMember(@PathVariable Long id) {
		this.memberService.deleteMemberById(id);
	}
}
