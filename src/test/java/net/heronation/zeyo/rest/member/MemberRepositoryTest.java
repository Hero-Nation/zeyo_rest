package net.heronation.zeyo.rest.member;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;

//import com.daou.BizSend;
//import com.daou.entity.SendMsgEntity;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap;
import net.heronation.zeyo.rest.repository.member.*;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository repository;

	
	@Autowired
	EntityManager entityManager;

	
//	@Test 
//	public void send_sms_test() {
//		
//		this.send_sms("01028063412","메세지 전송 테스트");
//	}
//	public String send_sms(String p_to_phone,String p_msg) {
//		
//
//		BizSend bs        = new BizSend();    // 메시지 전송 클래스
//		SendMsgEntity sme = null;             // 메시지 클래스
//
//		/* Console 로그 설정 */
//		// Console 에서 로그를 확인할 경우 설정
//		bs.setLogEnabled(true);
//
//		/* 서버 연결 시작 & 인증 */
//		// ip   = biz.ppurio.com = 211.189.43.25
//		// port = 5000 / 15001 /18100
//		bs.doBegin("biz.ppurio.com", 5000, "heronation", "ironman4$$");
//
//		/* 전송할 파일 경로 설정 */
//		// 전송할 파일이 있을 경우 설정
//		// ex. FAX, PHONE, MMS 등
////		bs.setFilePath("./spool");
//
//		/* 블랙리스트 파일 경로 설정 */
//		// 블랙리스트를 사용할 경우 설정
////		bs.setBlkPath("./blk");
//
//		/* PING-PONG */
//		// 장시간 연결하여 메시지를 전송할 경우 연결이 끊어지지 않기 위해 실행
//		// 이전 호출 시점으로부터 30초 이상 지난 경우에만 PING 을 전송하도록 함수내  정의되어 있음
//		// 실행하지 않아도 메시지 전송에는 영향을 미치지 않으나 안전한 연결을 위해 주기적으로 실행하길 권장
////		boolean bool = bs.sendPing();
////		System.out.println("Result of a Ping=" + bool);
//
//		/* 리포트 재요청 */
//		// msgid 에 해당하는 메시지의 리포트가 시간이 지나도 오지 않는 경우 실행
////		bool = bs.reconfirmReport("");    // @param msgid    다우기술의 서버에서 정의한 message id
////		System.out.println("Result of Reconfirming=" + bool);
//
//		/* 메시지 정의 */
//		// 다음의 setter 를 사용하여 필요한 정보를 정의
//		// ex. SMS 의 경우, MSG_TYPE, DEST_PHONE, SEND_PHONE, MSG_BODY 정보를 정의
//		sme = new SendMsgEntity();
//		sme.setCMID      ("");    // 데이터 id
//		sme.setMSG_TYPE  (0 );    // 데이터 타입 (SMS 0 / WAP 1 / FAX 2 / PHONE 3 / SMS_INBOUND 4 / MMS 5)
//		sme.setSEND_TIME ("");    // 발송 (예약) 시간 (Unix Time, 정의하지 않을 경우 즉시 발송)
//		sme.setDEST_PHONE(p_to_phone);    // 받는 사람 전화 번호
//		sme.setDEST_NAME ("");    // 받는 사람 이름
//		sme.setSEND_PHONE("01038863419");    // 보내는 사람 전화 번호
//		sme.setSEND_NAME ("");    // 보내는 사람 이름
//		sme.setSUBJECT   ("");    // (FAX/MMS) 제목, (SMS_INBOUND) 데이터 내용
//		sme.setMSG_BODY  (p_msg);    // 데이터 내용
//		sme.setWAP_URL   ("");    // (WAP) URL 주소
//		sme.setCOVER_FLAG(0 );    // (FAX) 표지 발송 옵션
//		sme.setSMS_FLAG  (0 );    // (PHONE) PHONE 실패 시 문자 전송 옵션
//		sme.setREPLY_FLAG(0 );    // (PHONE) 응답 받기 선택
//		sme.setRETRY_CNT (0 );    // (FAX/PHONE) 재시도 회수 (5~10분 간격: 최대 3회)
//		sme.setFAX_FILE  ("");    // (PHONE/FAX/MMS) 파일 전송시 파일 이름
//		sme.setVXML_FILE ("");    // (PHONE) 음성 시나리오 파일 이름
//
//		/* 메시지 전송 */
//		try {
//			String msgid = "";
//			msgid = bs.sendMsg(sme);    // @param  sme       SendMsgEntity
//			                            // @retrun String    다우기술의 서버에서 정의한 message id
//
//			System.out.println("msgid=" + msgid);
//		} catch (Exception ex) {
//			System.out.println("Failed to Send a Msg " + ex.getMessage());
//		}
//
//		/* 서버 연결 종료 */
//		bs.doEnd();
//		
//		return CommonConstants.OK;
//	}
//	
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {

		for (int a = 0; a < 100; a++) {
			Member item = new Member();
			item.setMemberId("memberId_" + a);

			item.setName("name_" + a);

			item.setPassword("password_" + a);

			item.setPhone("phone_" + a);

			item.setEmail("email_" + a);

			item.setManager("manager_" + a);

			item.setManagerEmail("managerEmail_" + a);

			item.setManagerPhone("managerPhone_" + a);

			//item.setCreateDt(new Date());

//			item.setDeleteDt(new Date());

			item.setAdminYn("adminYn_" + a);

			item.setUseYn("useYn_" + a);
			repository.save(item);
		}

	}
	
	
	@Test 
	
	@Ignore
	public void my_brand() {
		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);

		
		/*
		  
		  
		   SELECT * FROM zeyo.brand_member_map;SELECT 
			    brand1_.name AS col_0_0_,
			    s.name,
			    COUNT(DISTINCT item0_.id) AS col_1_0_
			FROM
			    item item0_
			        LEFT OUTER JOIN
			    brand brand1_ ON item0_.brand_id = brand1_.id
			        AND (brand1_.use_yn = 'Y')
			        LEFT OUTER JOIN
			    item_shopmall_map m ON item0_.id = m.item_id
			        LEFT OUTER JOIN
			    shopmall s ON m.shopmall_id = s.id
			WHERE
			    brand1_.member_id = 1
			GROUP BY brand1_.id , m.shopmall_id
		  
		 */
		
		QMember m = QMember.member;
		QItem i = QItem.item;
		QBrand b = QBrand.brand;
		QShopmall s = QShopmall.shopmall;
		QItemShopmallMap ism = QItemShopmallMap.itemShopmallMap;
		

		QueryResults<Tuple> R = query.select(

				b.name,ism.shopmall.name, i.id.countDistinct()

		).from(i)
				.leftJoin(i.brand, b).on(b.useYn.eq("Y"))  
				.leftJoin(i.itemShopmallMaps,ism).on(ism.useYn.eq("Y"))
				//.leftJoin(ism.shopmall,s).on(s.useYn.eq("Y"))
				
				.where(b.member.id.eq(1L))
				.groupBy(ism.shopmall.id )
				.fetchResults();

		List<Tuple> search_list = R.getResults();
		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Tuple row : search_list) {
			Map<String, Object> search_R = new HashMap<String, Object>();
 
			search_R.put("brand_name", row.get(b.name));
			search_R.put("shopmall_name", row.get(ism.shopmall.name)); 
			search_R.put("item_count", row.get(i.id.countDistinct())); 

			log.debug(search_R.toString());
			return_list.add(search_R);
		}
	}
	
	
	@Test 
	@Ignore
	public void my_shopmall() {
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
				.where(s.useYn.eq("Y").and(i.useYn.eq("Y")).and(b.useYn.eq("Y")).and(s.member.id.eq(2L)))
				.groupBy(b.id )
				.fetchResults();

		List<Tuple> search_list = R.getResults();
		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Tuple row : search_list) {
			Map<String, Object> search_R = new HashMap<String, Object>();
 
			
			search_R.put("shopmall_name", row.get(ism.shopmall.name));
			search_R.put("brand_name", row.get(ism.item.brand.name));
			search_R.put("item_count", row.get(ism.item.id.countDistinct())); 

			log.debug(search_R.toString());
			return_list.add(search_R);
		}
	}
	
	
	
	@Test
	@Ignore
	public void my_item() {
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
				.where(s.useYn.eq("Y").and(i.useYn.eq("Y")).and(b.useYn.eq("Y")).and(i.member.id.eq(2L)))
				.fetchResults();

		List<Tuple> search_list = R.getResults();
		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Tuple row : search_list) {
			Map<String, Object> search_R = new HashMap<String, Object>();
 
			
			search_R.put("item_name", row.get(i.name)); 
			search_R.put("shopmall_name", row.get(s.name));
			search_R.put("brand_name", row.get(b.name));
			

			log.debug(search_R.toString());
			return_list.add(search_R);
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Test 
	@Ignore
	public void statistic_query() {
		
		
		
//		회원 현황
//		
//		일반회원
//		총 회원 수 : 1231
//		신규 회원 수 : 14
//		탈퇴 회원 수 : 5
//		전일 대비 증감율: 5%
//		
//		판매회원
//		총 업체회원 수 : 1231
//		신규 업체회원 수 : 14
//		탈퇴 업체회원 수 : 5
//		전일 대비 증감율: 5%
//		
//		상품 현황
//		총 상품 수 : 1231
//		신규 상품 수 : 14
//		연동 상품 수 : 5
//		전일 대비 증감율: 5%
//		
//		사이즈표
//		총 등록 수 : 1231
//		신규 등록 수 : 14
//		전일 대비 증감율: 5%
		
		
		
		// 판매회원
		// 총 회원 수 : 1231

		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    member m ");
		query.append("WHERE ");
		query.append("    m.use_yn = 'Y'");

		Query q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> all_count_r = q.getResultList();
		BigInteger all_count = all_count_r.get(0);
		log.debug(all_count_r.size() + " " +all_count);
		
//		판매회원 
//		신규 회원 수 : 14
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS today_new_member ");
		query.append("FROM ");
		query.append("    member m ");
		query.append("WHERE ");
		query.append("    m.use_yn = 'Y'");
		query.append("        AND DATE(m.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> today_new_member_r = q.getResultList();
		BigInteger today_new_member = today_new_member_r.get(0);
		log.debug(today_new_member_r.size() + " " +today_new_member);
		
		
//		판매회원 
//		탈퇴 회원 수 : 5	
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS today_quit_member ");
		query.append("FROM ");
		query.append("    member m ");
		query.append("WHERE ");
		query.append("    m.use_yn = 'Y'");
		query.append("        AND DATE(m.delete_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> today_quit_member_r = q.getResultList();
		BigInteger today_quit_member = today_quit_member_r.get(0);
		log.debug(today_quit_member_r.size() + " " +today_quit_member);
		
		
		
//		판매회원 
//		전일 대비 증감율: 5%
		
		query = new StringBuffer();
		   
		query.append("SELECT ");
		query.append("    FLOOR((today.new_member / yesterday.new_member) * 100) AS increase_rate ");
		query.append("FROM ");
		query.append("    (SELECT ");
		query.append("        COUNT(*) AS new_member ");
		query.append("    FROM ");
		query.append("        member m ");
		query.append("    WHERE ");
		query.append("        m.use_yn = 'Y' ");
		query.append("            AND DATE(m.create_dt) = DATE(NOW() - 1)) yesterday, ");
		query.append("    (SELECT ");
		query.append("        COUNT(*) AS new_member ");
		query.append("    FROM ");
		query.append("        member m ");
		query.append("    WHERE ");
		query.append("        m.use_yn = 'Y' ");
		query.append("            AND DATE(m.create_dt) = DATE(NOW())) today");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> increase_rate_r = q.getResultList();
		BigInteger increase_rate = increase_rate_r.get(0);
		log.debug(increase_rate_r.size() + " " +increase_rate);
			
	}

}