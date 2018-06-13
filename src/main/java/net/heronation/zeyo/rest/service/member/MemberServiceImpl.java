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
import javax.persistence.Query;

import org.apache.commons.lang3.RandomUtils;
import org.hibernate.loader.custom.sql.SQLCustomQuery;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import net.heronation.zeyo.rest.common.controller.CommonException;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.controller.member.EmailUpdateVO;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistoryRepository;
import net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory;
import net.heronation.zeyo.rest.repository.email_validation.EmailValidation;
import net.heronation.zeyo.rest.repository.email_validation.EmailValidationRepository;
import net.heronation.zeyo.rest.repository.email_validation.QEmailValidation;
import net.heronation.zeyo.rest.repository.fit_info.FitInfo;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.madein.QMadein;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberDto;
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
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmailValidationRepository emailValidationRepository;

	@Autowired
	EntityManager entityManager;

	@Autowired
	private CompanyNoHistoryRepository companyNoHistoryRepository;

	@Override
	@Transactional
	public Member registry(MemberRegisterDto param) {

		Member newUser = param.getInitMember();
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		memberRepository.save(newUser);

		CompanyNoHistory newCompanyNo = new CompanyNoHistory();
		newCompanyNo.setCompanyNo(param.getCompany_no());
		newCompanyNo.setMember(newUser);
		newCompanyNo.setName(param.getCompany_name());
		newCompanyNo.setChangeDt(new DateTime());

		companyNoHistoryRepository.save(newCompanyNo);

		return newUser;
	}

	@Autowired
	public JavaMailSender emailSender;

	// @Override
	// @Transactional(readOnly = true)
	// public Page<Map<String, Object>> search(Predicate where, Pageable page) {
	//
	// JPAQuery<Member> query = new JPAQuery<Member>(entityManager);
	//
	// QMember m = QMember.member;
	// QCompanyNoHistory cnh = QCompanyNoHistory.companyNoHistory;
	//
	// // PathBuilder<Member> queryPath = new PathBuilder<Member>(Member.class,
	// // "member");
	// //
	// // for (Order order : page.getSort()) {
	// // PathBuilder<Object> path = queryPath.get(order.getProperty());
	// // query.orderBy(new
	// //
	// OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()),
	// // path));
	// // }
	//
	// QueryResults<Tuple> R = query
	// .select(m.id, m.name, m.phone, m.email, m.manager, m.managerPhone, cnh.id,
	// cnh.name, cnh.companyNo)
	// .from(cnh).innerJoin(cnh.member, m)
	// .where(cnh.id.in(JPAExpressions.select(cnh.id.max()).from(cnh).groupBy(cnh.member.id)).and(where))
	// .offset((page.getPageNumber() - 1) *
	// page.getPageSize()).limit(page.getPageSize()).fetchResults();
	//
	// List<Tuple> search_list = R.getResults();
	// List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();
	//
	// for (Tuple row : search_list) {
	// Map<String, Object> search_R = new HashMap<String, Object>();
	//
	// search_R.put("member_id", row.get(m.id));
	// search_R.put("member_name", row.get(m.name));
	// search_R.put("member_phone", row.get(m.phone));
	// search_R.put("member_email", row.get(m.email));
	// search_R.put("member_manager", row.get(m.manager));
	// search_R.put("member_managerPhone", row.get(m.managerPhone));
	//
	// search_R.put("company_id", row.get(cnh.id));
	// search_R.put("company_name", row.get(cnh.name));
	// search_R.put("company_companyNo", row.get(cnh.companyNo));
	//
	// return_list.add(search_R);
	// }
	//
	// return new PageImpl<Map<String, Object>>(return_list, page, R.getTotal());
	//
	// }

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> search(Map<String, Object> param, Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT ");
		select_query.append("    m.id				as member_id, ");
		select_query.append("    m.name				as member_name, ");
		select_query.append("    m.phone			as member_phone, ");
		select_query.append("    m.email			as member_email, ");
		select_query.append("    bt.name			as company_name, ");
		select_query.append("    bt.id				as company_id, ");
		select_query.append("    bt.company_no		as company_companyNo, ");
		select_query.append("    m.manager			as member_manager, ");
		select_query.append("    m.manager_phone	as member_managerPhone   ");

		StringBuffer where_query = new StringBuffer();
		where_query.append("FROM ");
		where_query.append("    (SELECT ");
		where_query.append("        cnh.* ");
		where_query.append("    FROM ");
		where_query.append("        company_no_history cnh ");
		where_query.append("    INNER JOIN (SELECT ");
		where_query.append("        MAX(id) AS id ");
		where_query.append("    FROM ");
		where_query.append("        company_no_history ");
		where_query.append("    GROUP BY member_id) pcnh ON cnh.id = pcnh.id) bt ");
		where_query.append("        LEFT OUTER JOIN ");
		where_query.append("    member m ON bt.member_id = m.id ");
		where_query.append("WHERE ");
		where_query.append("    m.use_yn = 'Y'");

		String identity = (String) param.get("identity");
		if (identity != null) {
			where_query.append("        AND ( m.name like '%" + identity + "%' ");
			where_query.append("        OR m.member_id like '%" + identity + "%' )");
		}

		String phone1 = (String) param.get("phone1");
		if (phone1 != null) {
			where_query.append("        AND   m.phone like '" + phone1 + ",%' ");
		}

		String phone2 = (String) param.get("phone2");
		if (phone2 != null) {
			where_query.append("        AND   m.phone like '," + phone2 + ",%' ");
		}

		String phone3 = (String) param.get("phone3");
		if (phone3 != null) {
			where_query.append("        AND   m.phone like '%," + phone3 + "' ");
		}

		String email1 = (String) param.get("email1");
		if (email1 != null) {
			where_query.append("        AND m.email like '" + email1 + "%' ");
		}

		String email2 = (String) param.get("email2");
		if (email2 != null) {
			where_query.append("        AND   m.email like '%" + email2 + "' ");
		}

		String cn1 = (String) param.get("cn1");
		if (cn1 != null) {
			where_query.append("        AND bt.company_no like '" + cn1 + ",%' ");
		}

		String cn2 = (String) param.get("cn2");
		if (cn2 != null) {
			where_query.append("        AND   m.company_no like '%," + cn2 + ",%' ");
		}

		String cn3 = (String) param.get("cn3");
		if (cn3 != null) {
			where_query.append("        AND  m.company_no like '%," + cn3 + "' ");
		}

		StringBuffer sort_query = new StringBuffer();
		sort_query.append("  ORDER BY m.");
		Sort sort = page.getSort();
		String sep = "";
		for (Sort.Order order : sort) {
			sort_query.append(sep);
			sort_query.append(order.getProperty());
			sort_query.append(" ");
			sort_query.append(order.getDirection());
			sep = ", ";
		}

		StringBuffer page_query = new StringBuffer();
		page_query.append("  limit ");
		page_query.append((page.getPageNumber() - 1) * page.getPageSize());
		page_query.append(" , ");
		page_query.append(page.getPageSize());

		Query count_q = entityManager.createNativeQuery(count_query.append(where_query).toString());
		List<Map<String, Object>> count_list = count_q.getResultList();

		Query q = entityManager
				.createNativeQuery(select_query.append(where_query).append(sort_query).append(page_query).toString());
		List<Object[]> list = q.getResultList();

		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();

			// [member_id] : [NUMERIC]) - [1]
			// [member_name] : [VARCHAR]) - [김수언]
			// [member_phone] : [VARCHAR]) - [111,1111,1111]
			// [member_email] : [VARCHAR]) - [superpeace@naver.com]
			// [company_name] : [VARCHAR]) - [name_9]
			// [company_id] : [NUMERIC]) - [12]
			// [company_companyNo] : [VARCHAR]) - [companyNo_9]
			// [member_manager] : [VARCHAR]) - [수언이]
			// [member_managerPhone] : [VARCHAR]) - [222,2222,2222]

			search_R.put("member_id", row[0]);
			search_R.put("member_name", row[1]);
			search_R.put("member_phone", row[2]);
			search_R.put("member_email", row[3]);
			search_R.put("company_name", row[4]);
			search_R.put("company_id", row[5]);
			search_R.put("company_companyNo", row[6]);
			search_R.put("member_manager", row[7]);
			search_R.put("member_managerPhone", row[8]);

			return_list.add(search_R);
		}

		int totalPages = (count_list.size() / page.getPageSize());
		if (count_list.size() % page.getPageSize() > 0)
			totalPages = totalPages + 1;

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("content", return_list);
		R.put("totalPages", totalPages);
		R.put("totalElements", count_list.size());
		R.put("number", page.getPageNumber());
		R.put("size", return_list.size());

		return R;

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
				.select(m.memberId, m.name, m.phone, m.email, m.manager, m.managerPhone, m.email_noti_yn, cnh.id,
						cnh.name, cnh.companyNo)
				.from(cnh).innerJoin(cnh.member, m)
				.where(cnh.id.in(JPAExpressions.select(cnh.id.max()).from(cnh).groupBy(cnh.member.id)).and(where))
				.fetchOne();

		Map<String, Object> search_R = new HashMap<String, Object>();

		//search_R.put("member_id", R.get(m.memberId));
		search_R.put("member_name", R.get(m.name));
		search_R.put("member_phone", R.get(m.phone));
		search_R.put("member_email", R.get(m.email));
		search_R.put("member_email_noti_yn", R.get(m.email_noti_yn));
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
	public Member update_phone(MemberDto param, Long member_seq) {
		// TODO Auto-generated method stub

		Member user = memberRepository.findOne(member_seq);
		user.setPhone(param.getPhone());

		return user;
	}

	@Override
	@Transactional
	public String update_email(EmailUpdateVO param, Long member_seq) {
		Member user = memberRepository.findOne(member_seq);
		
		QEmailValidation target = QEmailValidation.emailValidation;

		EmailValidation db_v = emailValidationRepository.findOne(target.email.eq(param.getEmail()));
		
		if (param.getConfirm_no().trim().equals(db_v.getOtp().trim())) {
			
			user.setEmail(param.getEmail());
			return CommonConstants.SUCCESS;
			
		} else {
			
			return CommonConstants.FAIL;
		}

	}

	@Override
	@Transactional
	public String update_password(String old_pw, String new_pw, Long member_seq) {

		Member user = memberRepository.findOne(member_seq);

		if (user.getPassword().equals(passwordEncoder.encode(old_pw))) {
			user.setPassword(passwordEncoder.encode(new_pw));
			return CommonConstants.SUCCESS;
		} else {
			return "old.pw.not.equal";
		}

	}

	@Override
	@Transactional
	public CompanyNoHistory update_cp_no(MemberDto param, Long member_seq) {

		List<CompanyNoHistory> list = companyNoHistoryRepository.findByMemberId(member_seq);
		Member user = memberRepository.findOne(member_seq);

		CompanyNoHistory last = list.get(list.size() - 1);
		last.setBeforeNo(param.getCp_no());

		CompanyNoHistory new_record = new CompanyNoHistory();
		new_record.setCompanyNo(param.getCp_no());
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
	@Transactional
	public String send_confirm_email(String email) throws CommonException {

		// 이미 이메일이 존재하는지 여부 ..>>> 여러번 보낼수 있다고 가정

		String ri = String.format("%06d", RandomUtils.nextInt(0, 999999)); 
	 

		QEmailValidation target = QEmailValidation.emailValidation;

		EmailValidation db_v = emailValidationRepository.findOne(target.email.eq(email));

		if (db_v != null) {
			// 새로운 번호로 입력한다.
			db_v.setOtp(ri);
			// db_v.set
		} else {
			
			EmailValidation a = new EmailValidation();
			a.setEmail(email);
			a.setCreateDt(new DateTime());
			a.setOtp(ri);			
			emailValidationRepository.save(a);
		}



		// 임시 패스워드 이메일 발송

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("help@heronation.net");
		message.setTo(db_v.getEmail());
		message.setSubject("히어로네이션 이메일 변경 인증 메일입니다.");
		message.setText("인증 번호는 "+ri+" 입니다.");
		emailSender.send(message);
		
		return CommonConstants.COMPLETE;
	}

	@Override
	public String confirm_otp(String email, String otp) throws CommonException {

		QEmailValidation target = QEmailValidation.emailValidation;

		EmailValidation db_v = emailValidationRepository.findOne(target.email.eq(email));

		if (db_v == null) {
			CommonException exp = new CommonException("EMAIL.NOT.EXIST");

			throw exp;
		} else {

			if (db_v.getOtp().equals(otp)) {
				return CommonConstants.EQUAL;
			} else {
				return CommonConstants.NOT_EQUAL;
			}
		}

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

		).from(i).leftJoin(i.brand, b).on(b.useYn.eq("Y")).leftJoin(i.itemShopmallMaps, ism).on(ism.useYn.eq("Y"))
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

				s.name, b.name, i.id.countDistinct()

		).from(ism).innerJoin(ism.shopmall, s).innerJoin(ism.item, i).innerJoin(ism.item.brand, b)
				.where(s.useYn.eq("Y").and(i.useYn.eq("Y")).and(b.useYn.eq("Y")).and(where)).groupBy(b.id)
				.fetchResults();

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

				i.name, s.name, b.name

		).from(ism).innerJoin(ism.shopmall, s).innerJoin(ism.item, i).innerJoin(ism.item.brand, b)
				.where(s.useYn.eq("Y").and(i.useYn.eq("Y")).and(b.useYn.eq("Y")).and(where)).fetchResults();

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

				cnh.name, cnh.beforeNo, cnh.companyNo, cnh.changeDt

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

	@Override
	@Transactional(readOnly = true)
	public Member findByMemberId(String member_id) {
		Member user = memberRepository.findByMemberId(member_id);

		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getStat() {

		// 회원 현황
		//
		// 일반회원
		// 총 회원 수 : 1231
		// 신규 회원 수 : 14
		// 탈퇴 회원 수 : 5
		// 전일 대비 증감율: 5%
		//
		// 판매회원
		// 총 업체회원 수 : 1231
		// 신규 업체회원 수 : 14
		// 탈퇴 업체회원 수 : 5
		// 전일 대비 증감율: 5%
		//
		// 상품 현황
		// 총 상품 수 : 1231
		// 신규 상품 수 : 14
		// 연동 상품 수 : 5
		// 전일 대비 증감율: 5%
		//
		// 사이즈표
		// 총 등록 수 : 1231
		// 신규 등록 수 : 14
		// 전일 대비 증감율: 5%

		return null;
	}

	@Override
	public String find_id_by_email(String name, String email) {

		QMember m = QMember.member;
		QEmailValidation target = QEmailValidation.emailValidation;

		Member db_v = memberRepository.findOne(m.name.eq(name).and(m.email.eq(email)));

		if (db_v == null) {
			return "member.not.exist";
		} else {

			EmailValidation db_ev = emailValidationRepository.findOne(target.email.eq(email));
			if (db_ev == null) {

				db_ev = new EmailValidation();
				db_ev.setCreateDt(new DateTime());
				db_ev.setEmail(email);
				db_ev.setOtp("1111");
				emailValidationRepository.save(db_ev);

			} else {
				db_ev.setOtp("1111");
			}

			// 이메일을 발송한다.

		}

		return "mail.sended";
	}

	@Override
	public String find_id_by_email_confirm(String name, String email, String otp) {
		QMember m = QMember.member;
		QEmailValidation target = QEmailValidation.emailValidation;

		Member db_v = memberRepository.findOne(m.name.eq(name).and(m.email.eq(email)));

		if (db_v == null) {
			return "member.not.exist";
		} else {

			EmailValidation db_ev = emailValidationRepository.findOne(target.email.eq(email));
			if (db_ev == null) {

				return "sended.mail.not.exist";

			} else {
				if (db_ev.getOtp() == null) {
					return "sended.mail.not.exist";
				} else {
					if (db_ev.getOtp().equals(otp)) {
						return db_v.getMemberId();
					} else {
						return "otp.not.equal";
					}
				}
			}

		}

	}

	@Override
	public String find_id_by_phone(String name, String phone) {
		QMember m = QMember.member;
		QEmailValidation target = QEmailValidation.emailValidation;

		Member db_v = memberRepository.findOne(m.name.eq(name).and(m.phone.eq(phone)));

		if (db_v == null) {
			return "member.not.exist";
		} else {

			EmailValidation db_ev = emailValidationRepository.findOne(target.email.eq(phone));

			if (db_ev == null) {

				db_ev = new EmailValidation();
				db_ev.setCreateDt(new DateTime());
				db_ev.setEmail(phone);
				db_ev.setOtp("1111");
				emailValidationRepository.save(db_ev);

			} else {
				db_ev.setOtp("1111");
			}

			// 문자 메세지를 전송한다.

		}

		return "phone.msg.sended";
	}

	@Override
	public String find_id_by_phone_confirm(String name, String phone, String otp) {
		QMember m = QMember.member;
		QEmailValidation target = QEmailValidation.emailValidation;

		Member db_v = memberRepository.findOne(m.name.eq(name).and(m.phone.eq(phone)));

		if (db_v == null) {
			return "member.not.exist";
		} else {

			EmailValidation db_ev = emailValidationRepository.findOne(target.email.eq(phone));
			if (db_ev == null) {

				return "sended.phone.not.exist";

			} else {
				if (db_ev.getOtp() == null) {
					return "sended.phone.not.exist";
				} else {
					if (db_ev.getOtp().equals(otp)) {
						return db_v.getMemberId();
					} else {
						return "otp.not.equal";
					}
				}
			}
		}
	}

	@Override
	public String find_password(String member_id, String member_name, String member_email) {
		QMember m = QMember.member;
		QEmailValidation target = QEmailValidation.emailValidation;
 
		Member db_v = memberRepository
				.findOne(m.memberId.eq(member_id).and(m.name.eq(member_name)).and(m.email.eq(member_email)));

		if (db_v == null) {
			return "member.not.exist";
		} else {
			
			String ri = String.format("%06d", RandomUtils.nextInt(0, 999999)); 
			db_v.setPassword(passwordEncoder.encode(ri));

			// 임시 패스워드 이메일 발송

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("help@heronation.net");
			message.setTo(db_v.getEmail());
			message.setSubject("히어로네이션 임시 비밀번호");
			message.setText("임시비밀번호는 "+ri+"입니다.");
			emailSender.send(message);

			return "new.password.sended";
		}
	}

	@Override
	@Transactional
	public Map<String, Object> toggle_email_noti(Long member_seq, MemberDto param) {
		// TODO Auto-generated method stub
		Member user = memberRepository.findOne(member_seq);
		user.setEmail_noti_yn(param.getFlag());

		Map<String, Object> search_R = new HashMap<String, Object>();

		search_R.put("current_email_noti", user.getEmail_noti_yn());

		return search_R;
	}

	@Override
	@Transactional
	public String send_register_mail(String email) throws CommonException {
		log.debug("send_register_mail");
		// 이미 이메일이 존재하는지 여부 ..>>> 여러번 보낼수 있다고 가정

		// 랜덤 문자열 생성 6자리
		String ri = String.format("%06d", RandomUtils.nextInt(0, 999999)); 


		QEmailValidation target = QEmailValidation.emailValidation;

		EmailValidation db_v = emailValidationRepository.findOne(target.email.eq(email));

		if (db_v != null) {
			// 새로운 번호로 입력한다.
			db_v.setOtp(ri);
			db_v.setCreateDt(new DateTime());
			// db_v.set
		} else {
			
			EmailValidation a = new EmailValidation();
			a.setEmail(email);
			a.setCreateDt(new DateTime());
			a.setOtp(ri);
			
			emailValidationRepository.save(a);
		}

		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("help@heronation.net");
			message.setTo(email);
			message.setSubject("히어로네이션 회원 가입 이메일 확인");
			message.setText("임시비밀번호는 "+ri+"입니다.");
			emailSender.send(message);
		}catch(Exception e) {
			CommonException exp = new CommonException("SENDING EMAIL ERROR");

			throw exp;
		}

		
 

		return CommonConstants.COMPLETE;
	}

}