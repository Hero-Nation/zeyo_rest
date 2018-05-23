package net.heronation.zeyo.rest.service.member;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.loader.custom.sql.SQLCustomQuery;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.sql.JPASQLQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistoryRepository;
import net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory;
import net.heronation.zeyo.rest.repository.fit_info.FitInfo;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap;
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
	@Transactional(readOnly = true)
	public Page<Map<String, Object>> search(Predicate where, Pageable page) {

		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);

		QMember m = QMember.member;
		QCompanyNoHistory cnh = QCompanyNoHistory.companyNoHistory;

		// PathBuilder<Member> queryPath = new PathBuilder<Member>(Member.class,
		// "member");
		//
		// for (Order order : page.getSort()) {
		// PathBuilder<Object> path = queryPath.get(order.getProperty());
		// query.orderBy(new
		// OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()),
		// path));
		// }

		QueryResults<Tuple> R = query
				.select(m.id, m.name, m.phone, m.email, m.manager, m.managerPhone, cnh.id, cnh.name, cnh.companyNo)
				.from(cnh).innerJoin(cnh.member, m)
				.where(cnh.id.in(JPAExpressions.select(cnh.id.max()).from(cnh).groupBy(cnh.member.id)).and(where))
				.offset((page.getPageNumber() - 1) * page.getPageSize()).limit(page.getPageSize()).fetchResults();

		List<Tuple> search_list = R.getResults();
		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Tuple row : search_list) {
			Map<String, Object> search_R = new HashMap<String, Object>();

			search_R.put("member_id", row.get(m.id));
			search_R.put("member_name", row.get(m.name));
			search_R.put("member_phone", row.get(m.phone));
			search_R.put("member_email", row.get(m.email));
			search_R.put("member_manager", row.get(m.manager));
			search_R.put("member_managerPhone", row.get(m.managerPhone));

			search_R.put("company_id", row.get(cnh.id));
			search_R.put("company_name", row.get(cnh.name));
			search_R.put("company_companyNo", row.get(cnh.companyNo));

			return_list.add(search_R);
		}

		return new PageImpl<Map<String, Object>>(return_list, page, R.getTotal());

	}

	@Override
	@Transactional(readOnly = true)
	public CompanyNoHistory getCompanyInfo(Predicate where) {

		JPAQuery<CompanyNoHistory> query = new JPAQuery<CompanyNoHistory>(entityManager);

		QCompanyNoHistory target = QCompanyNoHistory.companyNoHistory;

		return query.from(target).where(where)
				// .orderBy(target.id.desc())
				.fetchOne();

	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getUserInfo(Predicate where) {
		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);

		QMember m = QMember.member;
		QCompanyNoHistory cnh = QCompanyNoHistory.companyNoHistory;

		Tuple R = query
				.select(m.id, m.name, m.phone, m.email, m.manager, m.managerPhone, cnh.id, cnh.name, cnh.companyNo)
				.from(cnh).innerJoin(cnh.member, m)
				.where(cnh.id.in(JPAExpressions.select(cnh.id.max()).from(cnh).groupBy(cnh.member.id)).and(where))
				.fetchOne();

		Map<String, Object> search_R = new HashMap<String, Object>();

		search_R.put("member_id", R.get(m.id));
		search_R.put("member_name", R.get(m.name));
		search_R.put("member_phone", R.get(m.phone));
		search_R.put("member_email", R.get(m.email));
		search_R.put("member_manager", R.get(m.manager));
		search_R.put("member_managerPhone", R.get(m.managerPhone));

		search_R.put("company_id", R.get(cnh.id));
		search_R.put("company_name", R.get(cnh.name));
		search_R.put("company_companyNo", R.get(cnh.companyNo));

		return search_R;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Long> getUserBizInfo(Predicate where) {
		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);

		QMember target = QMember.member;

		QItem item = QItem.item;
		QBrand brand = QBrand.brand;
		QShopmall shopmall = QShopmall.shopmall;

		Tuple R = query.select(

				item.id.countDistinct(), brand.id.countDistinct(), shopmall.id.countDistinct()

		).from(target).leftJoin(target.items, item).on(item.useYn.eq("Y")).leftJoin(target.brands, brand)
				.on(brand.useYn.eq("Y")).leftJoin(target.shopmalls, shopmall).on(shopmall.useYn.eq("Y")).where(where)
				.fetchOne();

		Long item_count = R.get(item.id.countDistinct());
		Long brand_count = R.get(brand.id.countDistinct());
		Long shopmall_count = R.get(shopmall.id.countDistinct());

		Map<String, Long> returnValue = new HashMap<String, Long>();
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
	public Member update_email(String email, String confirm_no, Long member_seq) {
		Member user = memberRepository.findOne(member_seq);

		if (confirm_no.equals(user.getConfirm_no())) {
			return null;
		} else {
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

		List<CompanyNoHistory> list = companyNoHistoryRepository.findByMemberId(member_seq);
		Member user = memberRepository.findOne(member_seq);

		CompanyNoHistory last = list.get(list.size() - 1);
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

	@Override
	public Page<Map<String, Object>> my_brand(Predicate where, Pageable page) {
		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);

		QMember m = QMember.member;
		QItem i = QItem.item;
		QBrand b = QBrand.brand;
		QShopmall s = QShopmall.shopmall;
		QItemShopmallMap ism = QItemShopmallMap.itemShopmallMap;

		QueryResults<Tuple> R = query.select(

				b.name, ism.shopmall.name, i.id.countDistinct()

		).from(i)
				.leftJoin(i.brand, b).on(b.useYn.eq("Y"))
				.leftJoin(i.itemShopmallMaps, ism).on(ism.useYn.eq("Y"))
				.where(where).groupBy(ism.shopmall.id).fetchResults();

		List<Tuple> search_list = R.getResults();
		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Tuple row : search_list) {
			Map<String, Object> search_R = new HashMap<String, Object>();

			search_R.put("brand_name", row.get(b.name));
			search_R.put("shopmall_name", row.get(ism.shopmall.name));
			search_R.put("item_count", row.get(i.id.countDistinct()));

			return_list.add(search_R);
		}

		return new PageImpl<Map<String, Object>>(return_list, page, R.getTotal());
	}

	@Override
	public Page<Map<String, Object>> my_shopmall(Predicate where, Pageable page) {
		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);

		QMember m = QMember.member;
		QItem i = QItem.item;
		QBrand b = QBrand.brand;
		QShopmall s = QShopmall.shopmall;
		QItemShopmallMap ism = QItemShopmallMap.itemShopmallMap;

		QueryResults<Tuple> R = query.select(

				s.name ,b.name,i.id.countDistinct()

		).from(ism)
				.innerJoin(ism.shopmall,s)
				.innerJoin(ism.item,i)
				.innerJoin(ism.item.brand,b)
				.where(s.useYn.eq("Y").and(i.useYn.eq("Y")).and(b.useYn.eq("Y")).and(where))
				.groupBy(b.id ) .fetchResults();

		List<Tuple> search_list = R.getResults();
		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Tuple row : search_list) {
			Map<String, Object> search_R = new HashMap<String, Object>();

			search_R.put("shopmall_name", row.get(s.name));
			search_R.put("brand_name", row.get(b.name));
			search_R.put("item_count", row.get(i.id.countDistinct()));
 
			return_list.add(search_R);
		}
		return new PageImpl<Map<String, Object>>(return_list, page, R.getTotal());
	}

	@Override
	public Page<Map<String, Object>> my_item(Predicate where, Pageable page) {
		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);
 
		
		QMember m = QMember.member;
		QItem i = QItem.item;
		QBrand b = QBrand.brand;
		QShopmall s = QShopmall.shopmall;
		QItemShopmallMap ism = QItemShopmallMap.itemShopmallMap;
		

		QueryResults<Tuple> R = query.select(

				i.name,s.name ,b.name

		).from(ism)
				.innerJoin(ism.shopmall,s)
				.innerJoin(ism.item,i)
				.innerJoin(ism.item.brand,b)
				.where(s.useYn.eq("Y").and(i.useYn.eq("Y")).and(b.useYn.eq("Y")).and(where))
				.fetchResults();

		List<Tuple> search_list = R.getResults();
		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Tuple row : search_list) {
			Map<String, Object> search_R = new HashMap<String, Object>();
 
			
			search_R.put("item_name", row.get(i.name)); 
			search_R.put("shopmall_name", row.get(s.name));
			search_R.put("brand_name", row.get(b.name));
			
 
			return_list.add(search_R);
		}
		return new PageImpl<Map<String, Object>>(return_list, page, R.getTotal());
	}

	@Override
	public Page<Map<String, Object>> cn_history(Predicate where, Pageable page) {
		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);
 
		 
		QCompanyNoHistory cnh = QCompanyNoHistory.companyNoHistory;
		

		QueryResults<Tuple> R = query.select(

				cnh.name,cnh.beforeNo,cnh.companyNo,cnh.changeDt

		).from(cnh)
				 
				.where(cnh.id.in(JPAExpressions.select(cnh.id.max()).from(cnh).groupBy(cnh.member.id)).and(where))
				.fetchResults();

		List<Tuple> search_list = R.getResults();
		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Tuple row : search_list) {
			Map<String, Object> search_R = new HashMap<String, Object>();
 
			
			search_R.put("company_name", row.get(cnh.name)); 
			search_R.put("before_num", row.get(cnh.beforeNo));
			search_R.put("current_name", row.get(cnh.companyNo));
			search_R.put("change_dt", row.get(cnh.changeDt));
			
 
			return_list.add(search_R);
		}
		return new PageImpl<Map<String, Object>>(return_list, page, R.getTotal());
	}

}