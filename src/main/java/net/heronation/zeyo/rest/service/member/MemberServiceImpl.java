package net.heronation.zeyo.rest.service.member;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistoryRepository;
import net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.madein.QMadein;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberRegisterDto;
import net.heronation.zeyo.rest.repository.member.MemberRepository;
import net.heronation.zeyo.rest.repository.member.QMember;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;

@Slf4j
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	EntityManager entityManager;

	@Autowired
	private CompanyNoHistoryRepository companyNoHistoryRepository;

	@Override
	@Transactional
	public Member registry(MemberRegisterDto param) {

		Member newUser = param.getInitMember();
		memberRepository.saveAndFlush(newUser);

		CompanyNoHistory newCompanyNo = new CompanyNoHistory();
		newCompanyNo.setCompanyNo(param.getCompany_no());
		newCompanyNo.setMember(newUser);
		newCompanyNo.setName(param.getCompany_name());
		newCompanyNo.setChangeDt(new DateTime());

		companyNoHistoryRepository.saveAndFlush(newCompanyNo);

		return newUser;
	}

	@Override
	public String send_register_mail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String find_id_by_email() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String find_id_by_phone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Page<Member> search(Predicate where, Pageable page) {

		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);

		QMember target = QMember.member;

		QueryResults<Member> R = query.from(target)
		 
				.where(where)
				//.orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize())
				.fetchResults();
 
		return new PageImpl<Member>(R.getResults(), page, R.getTotal());

	}

 
	@Override
	@Transactional(readOnly=true)
	public CompanyNoHistory getCompanyInfo(Predicate where) {  
		
		
		JPAQuery<CompanyNoHistory> query = new JPAQuery<CompanyNoHistory>(entityManager);

		QCompanyNoHistory target = QCompanyNoHistory.companyNoHistory;

		return query.from(target)
		.where(where)
		//.orderBy(target.id.desc())
		.fetchOne();

		 
	}

	@Override
	@Transactional(readOnly=true)
	public Member getUserInfo(Predicate where) {
		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);

		QMember target = QMember.member;

		return query.from(target)
		.where(where) 
		.fetchOne();
	}

	@Override
	@Transactional(readOnly=true)
	public Map<String,Long> getUserBizInfo(Predicate where) {
		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);

		QMember target = QMember.member;
		
		QItem item = QItem.item;
		QBrand brand = QBrand.brand;
		QShopmall shopmall = QShopmall.shopmall;
		
		Tuple R =   query.select(
				
				item.id.countDistinct()
				,brand.id.countDistinct()
				,shopmall.id.countDistinct()
				
				).from(target)
				.leftJoin(target.items,item).on(item.useYn.eq("Y"))
				.leftJoin(target.brands,brand).on(brand.useYn.eq("Y"))
				.leftJoin(target.shopmalls,shopmall).on(shopmall.useYn.eq("Y"))
		.where(where).fetchOne();
	
		
		
		Long item_count = R.get(item.id.countDistinct());
		Long brand_count = R.get(brand.id.countDistinct());
		Long shopmall_count = R.get(shopmall.id.countDistinct());
		 
		
		Map<String,Long> returnValue = new HashMap<String,Long>();
		returnValue.put("item_count", item_count);
		returnValue.put("brand_count", brand_count);
		returnValue.put("shopmall_count", shopmall_count);
		
		
		return returnValue;
				
	}

	@Override
	@Transactional
	public Member update_phone(String phone, Long member_seq) {
		// TODO Auto-generated method stub
		
		Member user = memberRepository.findOne(member_seq);
		user.setPhone(phone);
		
		return user;
	}

	@Override
	@Transactional
	public Member update_email(String email,String confirm_no, Long member_seq) {
		Member user = memberRepository.findOne(member_seq);
		
		if(confirm_no.equals(user.getConfirm_no())){
			return null;
		}else {
			user.setEmail(email);
			return user;
		}
		 
	}

	@Override
	@Transactional
	public Member update_password(String password, Long member_seq) {
		Member user = memberRepository.findOne(member_seq);
		user.setPassword(password);
		// 인코딩 필요
		
		return user;
	}

	@Override
	@Transactional
	public CompanyNoHistory update_cp_no(String cp_no, Long member_seq) {
		
		List<CompanyNoHistory> list =companyNoHistoryRepository.findByMemberId(member_seq);
		Member user = memberRepository.findOne(member_seq);
		
		CompanyNoHistory  last = list.get(list.size()-1);
		last.setCompanyNo(cp_no);
		
		CompanyNoHistory new_record = new CompanyNoHistory();
		new_record.setBeforeNo(cp_no);
		new_record.setChangeDt(new DateTime());
		new_record.setMember(user);
		new_record.setName(last.getName());
		companyNoHistoryRepository.save(new_record);
		
		return new_record;
	}

	@Override
	@Transactional
	public Member update_mng_name(String mng_name, Long member_seq) {
		Member user = memberRepository.findOne(member_seq);
		user.setManager(mng_name);
		
		return user;
	}

	@Override
	@Transactional
	public Member update_mng_phone(String mng_phone, Long member_seq) {
		Member user = memberRepository.findOne(member_seq);
		user.setManagerPhone(mng_phone);
		
		return user;
	}

	@Override
	public Member send_confirm_email(String email, Long member_seq) {
		
		
		
		Member user = memberRepository.findOne(member_seq);
		user.setConfirm_no("1111");
		
		// 랜덤 문자열 생성 6자리
		
		// 이메일을 보낸다.
		
		// 문자열 저장
		
		// 인코딩 필요
		
		return user;
	}
 

}