package net.heronation.zeyo.rest.member.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.RandomUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.constants.CommonConstants;
import net.heronation.zeyo.rest.common.controller.CommonException;
import net.heronation.zeyo.rest.common.dto.FlagDto;
import net.heronation.zeyo.rest.common.dto.ToggleDto;
import net.heronation.zeyo.rest.company_no_history.repository.CompanyNoHistory;
import net.heronation.zeyo.rest.company_no_history.repository.CompanyNoHistoryRepository;
import net.heronation.zeyo.rest.company_no_history.repository.QCompanyNoHistory;
import net.heronation.zeyo.rest.email_validation.repository.EmailValidation;
import net.heronation.zeyo.rest.email_validation.repository.EmailValidationRepository;
import net.heronation.zeyo.rest.email_validation.repository.QEmailValidation;
import net.heronation.zeyo.rest.member.controller.AdminUpdateDto;
import net.heronation.zeyo.rest.member.controller.CpNoUpdateDto;
import net.heronation.zeyo.rest.member.controller.EmailUpdateVO;
import net.heronation.zeyo.rest.member.controller.PasswordUpdateDto;
import net.heronation.zeyo.rest.member.repository.Member;
import net.heronation.zeyo.rest.member.repository.MemberRegisterDto;
import net.heronation.zeyo.rest.member.repository.MemberRepository;
import net.heronation.zeyo.rest.member.repository.QMember;

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
		count_query.append("    count(*) from ( ");

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
			where_query.append("        AND   m.phone like '" + phone1 + "%' ");
		}

		String phone2 = (String) param.get("phone2");
		if (phone2 != null) {
			where_query.append("        AND   m.phone like '%" + phone2 + "%' ");
		}

		String phone3 = (String) param.get("phone3");
		if (phone3 != null) {
			where_query.append("        AND   m.phone like '%" + phone3 + "' ");
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
			where_query.append("        AND bt.company_no like '" + cn1 + "%' ");
		}

		String cn2 = (String) param.get("cn2");
		if (cn2 != null) {
			where_query.append("        AND   m.company_no like '%" + cn2 + "%' ");
		}

		String cn3 = (String) param.get("cn3");
		if (cn3 != null) {
			where_query.append("        AND  m.company_no like '%" + cn3 + "' ");
		}

		StringBuffer sort_query = new StringBuffer();

		Sort sort = page.getSort();
		if (sort != null) {
			sort_query.append("  ORDER BY m.");
			String sep = "";
			for (Sort.Order order : sort) {
				sort_query.append(sep);
				sort_query.append(order.getProperty());
				sort_query.append(" ");
				sort_query.append(order.getDirection());
				sep = ", ";
			}
		}

		StringBuffer page_query = new StringBuffer();
		page_query.append("  limit ");
		page_query.append((page.getPageNumber() - 1) * page.getPageSize());
		page_query.append(" , ");
		page_query.append(page.getPageSize());

		Query count_q = entityManager.createNativeQuery(
				count_query.append(select_query).append(where_query).append("  ) count_table  ").toString());
		BigInteger count_list = BigInteger.ZERO;

		List<BigInteger> count_result = count_q.getResultList();
		if (count_result.isEmpty()) {

		} else {
			count_list = count_result.get(0);
		}

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

		int totalPages = (count_list.intValue() / page.getPageSize());
		if (count_list.intValue() % page.getPageSize() > 0)
			totalPages = totalPages + 1;

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("content", return_list);
		R.put("totalPages", totalPages);
		R.put("totalElements", count_list.intValue());
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

		// search_R.put("member_id", R.get(m.memberId));
		search_R.put("member_name", R.get(m.name));
		search_R.put("member_phone", R.get(m.phone));
		search_R.put("member_email", R.get(m.email));
		search_R.put("member_email_noti_yn", R.get(m.email_noti_yn));
		search_R.put("member_manager", R.get(m.manager));
		search_R.put("member_managerPhone", R.get(m.managerPhone));
		search_R.put("member_id", R.get(m.memberId));

		search_R.put("company_id", R.get(cnh.id));
		search_R.put("company_name", R.get(cnh.name));
		search_R.put("company_companyNo", R.get(cnh.companyNo));

		return search_R;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getUserBizInfo(Map<String, Object> param) {

		String member_id = (String) param.get("member_id");

		StringBuffer select_query = new StringBuffer();
		select_query.append(" SELECT Count(DISTINCT i.id) AS item_count, ");
		select_query.append("       Count(DISTINCT s.id) AS shopmall_count, ");
		select_query.append("       Count(DISTINCT b.id) AS brand_count ");
		select_query.append(" FROM   item_shopmall_map ism ");
		select_query.append("       LEFT JOIN item i ");
		select_query.append("              ON ism.item_id = i.id ");
		select_query.append("                 AND i.use_yn = 'Y' ");
		select_query.append("       LEFT JOIN shopmall s ");
		select_query.append("              ON ism.shopmall_id = s.id ");
		select_query.append("                 AND s.use_yn = 'Y' ");
		select_query.append("       LEFT JOIN brand b ");
		select_query.append("              ON i.brand_id = b.id ");
		select_query.append("                 AND b.use_yn = 'Y' ");
		select_query.append(" WHERE  i.use_yn = 'Y' ");
		select_query.append("       AND i.member_id = " + member_id);

		Query q = entityManager.createNativeQuery(select_query.toString());
		List<Object[]> list = q.getResultList();

		Map<String, Object> R = new HashMap<String, Object>();

		for (Object[] row : list) {

			R.put("item_count", row[0]);
			R.put("shopmall_count", row[1]);
			R.put("brand_count", row[2]);

		}

		return R;

	}

	@Override
	@Transactional
	public Member update_phone(PasswordUpdateDto param, Long member_seq) {
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

		if (passwordEncoder.matches(old_pw, user.getPassword())) {
			user.setPassword(passwordEncoder.encode(new_pw));
			return CommonConstants.SUCCESS;
		} else if (old_pw.equals(new_pw)) {
			return "old.new.same";
		} else {
			return "old.pw.not.equal";
		}

	}

	@Override
	@Transactional
	public CompanyNoHistory update_cp_no(CpNoUpdateDto param, Long member_seq) {

		List<CompanyNoHistory> list = companyNoHistoryRepository.findByMemberId(member_seq);
		Member user = memberRepository.findOne(member_seq);

		CompanyNoHistory last = list.get(list.size() - 1);
		// last.setBeforeNo(param.getCp_no());

		CompanyNoHistory new_record = new CompanyNoHistory();
		new_record.setBeforeNo(last.getCompanyNo());
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

			db_v = new EmailValidation();
			db_v.setEmail(email);
			db_v.setCreateDt(new DateTime());
			db_v.setOtp(ri);
			emailValidationRepository.save(db_v);
		}

		// 임시 패스워드 이메일 발송

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("help@heronation.net");
		message.setTo(db_v.getEmail());
		message.setSubject("히어로네이션 이메일 변경 인증 메일입니다.");
		message.setText("인증 번호는 '" + ri + "' 입니다.");
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
	@Transactional(readOnly = true)
	public Map<String, Object> my_brand(Map<String, Object> param, Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) from ( ");

		StringBuffer select_query = new StringBuffer();
		select_query.append(" SELECT b.name as brand_name, ");
		select_query.append("       s.name as shopmall_name , ");
		select_query.append("       Count(DISTINCT i.id) AS item_count ");

		String member_id = (String) param.get("member_id");

		StringBuffer where_query = new StringBuffer();
		where_query.append(" FROM   item_shopmall_map ism ");
		where_query.append("       LEFT JOIN item i ");
		where_query.append("              ON ism.item_id = i.id ");
		where_query.append("                 AND i.use_yn = 'Y' ");
		where_query.append("       LEFT JOIN shopmall s ");
		where_query.append("              ON ism.shopmall_id = s.id ");
		where_query.append("                 AND s.use_yn = 'Y' ");
		where_query.append("       LEFT JOIN brand b ");
		where_query.append("              ON i.brand_id = b.id ");
		where_query.append("                 AND b.use_yn = 'Y' ");
		where_query.append(" WHERE  ism.use_yn = 'Y' ");
		where_query.append("   AND  i.member_id =  " + member_id);
		where_query.append(" GROUP  BY b.id, ");
		where_query.append("          s.id");

		StringBuffer sort_query = new StringBuffer();

		Sort sort = page.getSort();
		if (sort != null) {
			sort_query.append("  ORDER BY i.");
			String sep = "";
			for (Sort.Order order : sort) {
				sort_query.append(sep);
				sort_query.append(order.getProperty());
				sort_query.append(" ");
				sort_query.append(order.getDirection());
				sep = ", ";
			}
		}

		StringBuffer page_query = new StringBuffer();
		page_query.append("  limit ");
		page_query.append((page.getPageNumber() - 1) * page.getPageSize());
		page_query.append(" , ");
		page_query.append(page.getPageSize());

		Query count_q = entityManager.createNativeQuery(
				count_query.append(select_query).append(where_query).append(" ) count_table   ").toString());
		BigInteger count_list = BigInteger.ZERO;

		List<BigInteger> count_result = count_q.getResultList();
		if (count_result.isEmpty()) {

		} else {
			count_list = count_result.get(0);
		}

		Query q = entityManager
				.createNativeQuery(select_query.append(where_query).append(sort_query).append(page_query).toString());
		List<Object[]> list = q.getResultList();

		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();

			search_R.put("brand_name", row[0]);
			search_R.put("shopmall_name", row[1]);
			search_R.put("item_count", row[2]);

			return_list.add(search_R);
		}

		int totalPages = (count_list.intValue() / page.getPageSize());
		if (count_list.intValue() % page.getPageSize() > 0)
			totalPages = totalPages + 1;

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("content", return_list);
		R.put("totalPages", totalPages);
		R.put("totalElements", count_list.intValue());
		R.put("number", page.getPageNumber());
		R.put("size", return_list.size());

		return R;

	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> my_shopmall(Map<String, Object> param, Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) from ( ");

		StringBuffer select_query = new StringBuffer();
		select_query.append(" SELECT b.name as brand_name, ");
		select_query.append("       s.name as shopmall_name , ");
		select_query.append("       Count(DISTINCT i.id) AS item_count ");

		String member_id = (String) param.get("member_id");

		StringBuffer where_query = new StringBuffer();
		where_query.append(" FROM   item_shopmall_map ism ");
		where_query.append("       LEFT JOIN item i ");
		where_query.append("              ON ism.item_id = i.id ");
		where_query.append("                 AND i.use_yn = 'Y' ");
		where_query.append("       LEFT JOIN shopmall s ");
		where_query.append("              ON ism.shopmall_id = s.id ");
		where_query.append("                 AND s.use_yn = 'Y' ");
		where_query.append("       LEFT JOIN brand b ");
		where_query.append("              ON i.brand_id = b.id ");
		where_query.append("                 AND b.use_yn = 'Y' ");
		where_query.append(" WHERE  ism.use_yn = 'Y' ");
		where_query.append("   AND  i.member_id =  " + member_id);
		where_query.append(" GROUP  BY s.id, ");
		where_query.append("          b.id");

		StringBuffer sort_query = new StringBuffer();

		Sort sort = page.getSort();
		if (sort != null) {
			sort_query.append("  ORDER BY i.");
			String sep = "";
			for (Sort.Order order : sort) {
				sort_query.append(sep);
				sort_query.append(order.getProperty());
				sort_query.append(" ");
				sort_query.append(order.getDirection());
				sep = ", ";
			}
		}

		StringBuffer page_query = new StringBuffer();
		page_query.append("  limit ");
		page_query.append((page.getPageNumber() - 1) * page.getPageSize());
		page_query.append(" , ");
		page_query.append(page.getPageSize());

		Query count_q = entityManager.createNativeQuery(
				count_query.append(select_query).append(where_query).append(" ) count_table    ").toString());
		BigInteger count_list = BigInteger.ZERO;

		List<BigInteger> count_result = count_q.getResultList();
		if (count_result.isEmpty()) {

		} else {
			count_list = count_result.get(0);
		}

		Query q = entityManager
				.createNativeQuery(select_query.append(where_query).append(sort_query).append(page_query).toString());
		List<Object[]> list = q.getResultList();

		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();

			search_R.put("brand_name", row[0]);
			search_R.put("shopmall_name", row[1]);
			search_R.put("item_count", row[2]);

			return_list.add(search_R);
		}

		int totalPages = (count_list.intValue() / page.getPageSize());
		if (count_list.intValue() % page.getPageSize() > 0)
			totalPages = totalPages + 1;

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("content", return_list);
		R.put("totalPages", totalPages);
		R.put("totalElements", count_list.intValue());
		R.put("number", page.getPageNumber());
		R.put("size", return_list.size());

		return R;

	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> my_item(Map<String, Object> where, Pageable page) {
		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) from (");

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT i.name AS item_name, ");
		select_query.append("       s.name AS shopmall_name, ");
		select_query.append("       b.name AS brand_name ");

		StringBuffer where_query = new StringBuffer();

		where_query.append(" FROM   item i ");
		where_query.append("       LEFT JOIN item_shopmall_map ism ");
		where_query.append("              ON i.id = ism.item_id ");
		where_query.append("                 AND ism.use_yn = 'Y' ");
		where_query.append("       LEFT JOIN shopmall s ");
		where_query.append("              ON ism.shopmall_id = s.id ");
		where_query.append("                 AND s.use_yn = 'Y' ");
		where_query.append("       LEFT JOIN brand b ");
		where_query.append("              ON i.brand_id = b.id ");
		where_query.append("                 AND b.use_yn = 'Y' ");
		where_query.append(" WHERE  i.use_yn = 'Y'");

		String member_id = (String) where.get("member_id");
		if (member_id != null) {
			where_query.append("   AND i.member_id = " + member_id);
		}

		StringBuffer sort_query = new StringBuffer();
		Sort sort = page.getSort();
		if (sort != null) {

			sort_query.append("  ORDER BY i.");
			String sep = "";
			for (Sort.Order order : sort) {
				sort_query.append(sep);
				sort_query.append(order.getProperty());
				sort_query.append(" ");
				sort_query.append(order.getDirection());
				sep = ", ";
			}
		}

		StringBuffer page_query = new StringBuffer();
		page_query.append("  limit ");
		page_query.append((page.getPageNumber() - 1) * page.getPageSize());
		page_query.append(" , ");
		page_query.append(page.getPageSize());

		Query count_q = entityManager.createNativeQuery(
				count_query.append(select_query).append(where_query).append(" ) count_table ").toString());
		BigInteger count_list = BigInteger.ZERO;

		List<BigInteger> count_result = count_q.getResultList();
		if (count_result.isEmpty()) {

		} else {
			count_list = count_result.get(0);
		}

		Query q = entityManager
				.createNativeQuery(select_query.append(where_query).append(sort_query).append(page_query).toString());
		List<Object[]> list = q.getResultList();

		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();

			// "shopmall_name" : "heronation cafe24",
			// "item_name" : "베이비파우더NT",
			// "brand_name" : "IMPORT_DEFAULT"

			// select_query.append("SELECT i.name AS item_name, ");
			// select_query.append(" s.name AS shopmall_name, ");
			// select_query.append(" b.name AS brand_name ");

			search_R.put("item_name", row[0]);
			search_R.put("shopmall_name", row[1]);
			search_R.put("brand_name", row[2]);

			return_list.add(search_R);
		}

		int totalPages = (count_list.intValue() / page.getPageSize());
		if (count_list.intValue() % page.getPageSize() > 0)
			totalPages = totalPages + 1;

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("content", return_list);
		R.put("totalPages", totalPages);
		R.put("totalElements", count_list.intValue());
		R.put("number", page.getPageNumber());
		R.put("size", return_list.size());

		return R;
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
	public String find_id_by_email(String name, String email) throws CommonException {

		QMember m = QMember.member;
		QEmailValidation target = QEmailValidation.emailValidation;

		Iterable<Member> db_v =  memberRepository.findAll(m.name.eq(name).and(m.email.eq(email)));

		if (!db_v.iterator().hasNext()) {
			return "member.not.exist";
		} else {
			Member this_member = db_v.iterator().next();
			
			try {
				SimpleMailMessage message = new SimpleMailMessage();
				message.setFrom("help@heronation.net");
				message.setTo(email);
				message.setSubject("히어로네이션 아이디 찾기 안내 메일");
				message.setText("현재 사용하시는 아이디는 '" + this_member.getMemberId() + "' 입니다.");
				emailSender.send(message);
			} catch (Exception e) {
				CommonException exp = new CommonException("SENDING EMAIL ERROR");

				throw exp;
			}

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
			String ri = String.format("%06d", RandomUtils.nextInt(0, 999999));

			if (db_ev == null) {

				db_ev = new EmailValidation();
				db_ev.setCreateDt(new DateTime());
				db_ev.setEmail(phone);
				db_ev.setOtp(ri);
				emailValidationRepository.save(db_ev);

			} else {
				db_ev.setOtp(ri);
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
			message.setText("임시비밀번호는 '" + ri + "' 입니다.");
			emailSender.send(message);

			return "new.password.sended";
		}
	}

	@Override
	@Transactional
	public Map<String, Object> toggle_email_noti(Long member_seq, FlagDto param) {
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
			message.setText("인증번호는 '" + ri + "' 입니다.");
			emailSender.send(message);
		} catch (Exception e) {
			CommonException exp = new CommonException("SENDING EMAIL ERROR");

			throw exp;
		}

		return CommonConstants.COMPLETE;
	}

	@Override
	@Transactional
	public String admin_update(AdminUpdateDto param) throws CommonException {
		String R = CommonConstants.OK;

		Member db_val = memberRepository.findOne(param.getMember_id());

		db_val.setEmail(param.getMember_email());
		db_val.setManager(param.getMember_manager());
		db_val.setManagerPhone(param.getMember_manager_phone());
		db_val.setPhone(param.getMember_phone());

		CompanyNoHistory last = companyNoHistoryRepository.findOne(param.getCompany_id());
		Member user = memberRepository.findOne(param.getMember_id());

		// 사업자 번호가 변경되면 이력을 넣어주고 변경시킨다.
		if (!last.getCompanyNo().equals(param.getCompany_companyNo())) {

			CompanyNoHistory new_cnh = new CompanyNoHistory();
			new_cnh.setBeforeNo(last.getCompanyNo());
			new_cnh.setChangeDt(new DateTime());
			new_cnh.setCompanyNo(param.getCompany_companyNo());
			new_cnh.setMember(user);
			new_cnh.setName(last.getName());
			companyNoHistoryRepository.save(new_cnh);
		}

		return R;
	}

	@Override
	@Transactional
	public String delete(List<ToggleDto> param, Long seq) {
		for (ToggleDto tv : param) {

			Member i = memberRepository.findOne(tv.getId());
			i.setUseYn("N");

		}

		return "Y";
	}

}