package net.heronation.zeyo.rest.member.service;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.member.MemberRepository;



@Slf4j
@Service
@Transactional
public class MemberServiceImpl implements MemberService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private MemberRepository memberRepository;


}