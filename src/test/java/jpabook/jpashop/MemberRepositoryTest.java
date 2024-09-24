package jpabook.jpashop;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@Test
	@Transactional 	// DB에 저장하고 싶을 때
	@Rollback(false) // Rollback을 하지 않고 DB에 저장하고 싶을 때
	public void testMember () throws Exception {
	    // given
	    Member member = new Member();
		member.setUsername("memberA");

		// when
		Long saveId = memberRepository.save(member);
		Member findMember = memberRepository.find(saveId);

	    // then
		Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
		Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		Assertions.assertThat(findMember).isEqualTo(member);
	 }
}