package net.heronation.zeyo.rest.service.common;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.daou.BizSend;
import com.daou.entity.SendMsgEntity;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;

@Slf4j
@Service
@Transactional
public class CommonServiceImpl implements CommonService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	EntityManager entityManager;

	@Override
	public Map<String, Object> dash_board_statistic() {

		Map<String, Object> R = new HashMap<String, Object>();
		
		
		
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 게시판 		
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

		Map<String, Object> bbs_related = new HashMap<String, Object>();
		// 게시판 글 관련 통계
		// 총 게시글 수 : 1231
		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    bbs b ");
		query.append("WHERE ");
		query.append("    b.use_yn = 'Y'");

		Query q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> bbs_all_count_r = q.getResultList();
		BigInteger bbs_all_count = bbs_all_count_r.get(0);

		bbs_related.put("bbs_all_count", bbs_all_count);

		// 신규 게시글 수 : 42

		query = new StringBuffer();

		query.append("SELECT ");
		query.append("    COUNT(*) AS today_all_count ");
		query.append("FROM ");
		query.append("    bbs b ");
		query.append("WHERE ");
		query.append("    b.use_yn = 'Y' ");
		query.append("        AND DATE(b.create_dt) = DATE(NOW())");

		q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> bbs_today_all_count_r = q.getResultList();
		BigInteger bbs_today_new = bbs_today_all_count_r.get(0);

		bbs_related.put("bbs_today_new", bbs_today_new);

		// "A : 접수중
		// B : 확인중
		// C : 답변완료"

		// 답변 완료 : 1231

		query = new StringBuffer();

		query.append("SELECT ");
		query.append("    COUNT(*) AS today_complete ");
		query.append("FROM ");
		query.append("    bbs b ");
		query.append("WHERE ");
		query.append("    b.use_yn = 'Y' ");
		query.append("    AND b.status = 'C' ");
		query.append("        AND DATE(b.create_dt) = DATE(NOW())");

		q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> bbs_today_complete_r = q.getResultList();
		BigInteger bbs_today_complete = bbs_today_complete_r.get(0);

		bbs_related.put("bbs_today_complete", bbs_today_complete);

		// 확인 중 : 42

		query = new StringBuffer();

		query.append("SELECT ");
		query.append("    COUNT(*) AS today_ing ");
		query.append("FROM ");
		query.append("    bbs b ");
		query.append("WHERE ");
		query.append("    b.use_yn = 'Y' ");
		query.append("    AND b.status = 'B' ");
		query.append("        AND DATE(b.create_dt) = DATE(NOW())");

		q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> bbs_today_ing_r = q.getResultList();
		BigInteger bbs_today_ing = bbs_today_ing_r.get(0);

		bbs_related.put("bbs_today_ing", bbs_today_ing);

		// 접수 중 : 123

		query = new StringBuffer();

		query.append("SELECT ");
		query.append("    COUNT(*) AS today_receiving ");
		query.append("FROM ");
		query.append("    bbs b ");
		query.append("WHERE ");
		query.append("    b.use_yn = 'Y' ");
		query.append("    AND b.status = 'A' ");
		query.append("        AND DATE(b.create_dt) = DATE(NOW())");

		q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> bbs_today_receiving_r = q.getResultList();
		BigInteger bbs_today_receiving = bbs_today_receiving_r.get(0);

		bbs_related.put("bbs_today_receiving", bbs_today_receiving);

		// 제휴 문의 : 42

		query = new StringBuffer();

		query.append("SELECT ");
		query.append("    COUNT(*) AS today_receiving ");
		query.append("FROM ");
		query.append("    bbs b ");
		query.append("WHERE ");
		query.append("    b.use_yn = 'Y' ");
		query.append("    AND b.kindof_id = 3 ");
		query.append("        AND DATE(b.create_dt) = DATE(NOW())");

		q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> bbs_kindof_3_r = q.getResultList();
		BigInteger bbs_kindof_3 = bbs_kindof_3_r.get(0);

		bbs_related.put("bbs_kindof_3", bbs_kindof_3);
 	
		// 1대1 문의 : 123

		query = new StringBuffer();

		query.append("SELECT ");
		query.append("    COUNT(*) AS today_receiving ");
		query.append("FROM ");
		query.append("    bbs b ");
		query.append("WHERE ");
		query.append("    b.use_yn = 'Y' ");
		query.append("    AND b.kindof_id = 4 ");
		query.append("        AND DATE(b.create_dt) = DATE(NOW())");

		q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> bbs_kindof_4_r = q.getResultList();
		BigInteger bbs_kindof_4 = bbs_kindof_4_r.get(0);
		bbs_related.put("bbs_kindof_4", bbs_kindof_4);

		R.put("bbs", bbs_related);
		
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 일반 회원		
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		Map<String, Object> end_user_related = new HashMap<String, Object>();
		end_user_related.put("end_user_all_count", 0);
		end_user_related.put("end_user_today_new_member", 0);
		end_user_related.put("end_user_today_quit_member", 0);
		end_user_related.put("end_usert_increase_rate", 0);
		
		R.put("end_user", end_user_related);
		
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 고객사 회원		
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		Map<String, Object> client_related = new HashMap<String, Object>();
		
		// 판매회원
		// 총 회원 수 : 1231
		query = new StringBuffer();
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    member m ");
		query.append("WHERE ");
		query.append("    m.use_yn = 'Y'");

		q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> client_all_count_r = q.getResultList();
		BigInteger client_all_count = client_all_count_r.get(0);
				
		client_related.put("client_all_count", client_all_count);
		
		
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
		List<BigInteger> client_today_new_member_r = q.getResultList();
		BigInteger client_today_new_member = client_today_new_member_r.get(0);
	 
		client_related.put("client_today_new_member", client_today_new_member);
		
		
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
		List<BigInteger> client_today_quit_member_r = q.getResultList();
		BigInteger client_today_quit_member = client_today_quit_member_r.get(0);
	 
		client_related.put("client_today_quit_member", client_today_quit_member);
		
		
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
		List<BigInteger> client_increase_rate_r = q.getResultList();
		BigInteger client_increase_rate = client_increase_rate_r.get(0);
		if(client_increase_rate == null) {
			client_increase_rate = BigInteger.valueOf(0L); 
		}
		
		client_related.put("client_increase_rate", client_increase_rate);
		
		
		R.put("client", client_related);
		
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 상품 관련		
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		Map<String, Object> item_related = new HashMap<String, Object>();
		
		
//		상품 현황
//		총 상품 수 : 1231

		query = new StringBuffer();
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    item i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		
		q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> item_all_count_r = q.getResultList();
		BigInteger item_all_count = item_all_count_r.get(0); 
		item_related.put("item_all_count", item_all_count);
//		상품 현황 
//		신규 상품 수 : 14
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    item i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y' ");
		query.append("        AND DATE(i.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> item_today_new_item_r = q.getResultList();
		BigInteger item_today_new_item = item_today_new_item_r.get(0); 
		item_related.put("item_today_new_item", item_today_new_item);
		
//		상품 현황 
//		연동 상품 수 : 5
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    item i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		query.append("        AND i.link_yn = 'Y' ");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> item_link_item_r = q.getResultList();
		BigInteger item_link_item = item_link_item_r.get(0); 
		
		item_related.put("item_link_item", item_link_item);
		
		
//		상품 현황 
//		전일 대비 증감율: 5%
		
		query = new StringBuffer();
		   
		query.append("SELECT ");
		query.append("    FLOOR((today.new_count / yesterday.new_count) * 100) AS increase_rate ");
		query.append("FROM ");
		query.append("    (SELECT ");
		query.append("        COUNT(*) AS new_count ");
		query.append("    FROM ");
		query.append("        item i ");
		query.append("    WHERE ");
		query.append("        i.use_yn = 'Y' ");
		query.append("            AND DATE(i.create_dt) = DATE(NOW() - 1)) yesterday, ");
		query.append("    (SELECT ");
		query.append("        COUNT(*) AS new_count ");
		query.append("    FROM ");
		query.append("        item i ");
		query.append("    WHERE ");
		query.append("        i.use_yn = 'Y' ");
		query.append("            AND DATE(i.create_dt) = DATE(NOW())) today");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> item_increase_rate_r = q.getResultList();
		BigInteger item_increase_rate = item_increase_rate_r.get(0); 
		
		if(item_increase_rate == null) {
			item_increase_rate = BigInteger.valueOf(0L); 
		}
		
		item_related.put("item_increase_rate", item_increase_rate);
		
		R.put("item", item_related);
		
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 사이즈표 
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		Map<String, Object> size_table_related = new HashMap<String, Object>();
		
		
		
//		상품 현황
//		총 상품 수 : 1231

		query = new StringBuffer(); 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    size_table s ");
		query.append("WHERE ");
		query.append("    s.use_yn = 'Y'");
		
		q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> size_tableall_count_r = q.getResultList();
		BigInteger size_table_all_count = size_tableall_count_r.get(0); 
		
		
		size_table_related.put("size_table_all_count", size_table_all_count);
		
		
//		상품 현황 
//		신규 상품 수 : 14
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    size_table s ");
		query.append("WHERE ");
		query.append("    s.use_yn = 'Y' ");
		query.append("        AND DATE(s.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> size_tabletoday_new_item_r = q.getResultList();
		BigInteger size_table_today_new_item = size_tabletoday_new_item_r.get(0); 
 
		size_table_related.put("size_table_today_new_item", size_table_today_new_item);
		
		
//		상품 현황 
//		전일 대비 증감율: 5%
		
		query = new StringBuffer();
		   
		query.append("SELECT ");
		query.append("    FLOOR((today.new_count / yesterday.new_count) * 100) AS increase_rate ");
		query.append("FROM ");
		query.append("    (SELECT ");
		query.append("        COUNT(*) AS new_count ");
		query.append("    FROM ");
		query.append("        size_table s ");
		query.append("    WHERE ");
		query.append("        s.use_yn = 'Y' ");
		query.append("            AND DATE(s.create_dt) = DATE(NOW() - 1)) yesterday, ");
		query.append("    (SELECT ");
		query.append("        COUNT(*) AS new_count ");
		query.append("    FROM ");
		query.append("        size_table s ");
		query.append("    WHERE ");
		query.append("        s.use_yn = 'Y' ");
		query.append("            AND DATE(s.create_dt) = DATE(NOW())) today");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> size_tableincrease_rate_r = q.getResultList();
		BigInteger size_tableincrease_rate = size_tableincrease_rate_r.get(0); 
		
		if(size_tableincrease_rate == null) {
			size_tableincrease_rate = BigInteger.valueOf(0L); 
		}
		
		size_table_related.put("size_table_increase_rate", size_tableincrease_rate);
		
		R.put("size_table", size_table_related);
		
		
		
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 제조국 
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		Map<String, Object> madein_related = new HashMap<String, Object>();		
		
//		제조국
//		총 등록 수 : 1231

		query = new StringBuffer(); 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    madein i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		
		q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> madein_all_count_r = q.getResultList();
		BigInteger madein_all_count = madein_all_count_r.get(0);
		
		madein_related.put("madein_all_count", madein_all_count);
	 	
//		제조국 
//		신규 등록 수 : 14
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    madein i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y' ");
		query.append("        AND DATE(i.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> madein_today_new_item_r = q.getResultList();
		BigInteger madein_today_new_item = madein_today_new_item_r.get(0);
		
		madein_related.put("madein_today_new_item", madein_today_new_item);
	 
		
//		제조국 
//		관리자입력 : 5
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    madein i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		query.append("        AND i.kindof_id = 1 ");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> madein_kindof_1_r = q.getResultList();
		BigInteger madein_kindof_1 = madein_kindof_1_r.get(0);
		
		madein_related.put("madein_kindof_1", madein_kindof_1);
	 
		
//		제조국 
//		직접입력 : 5
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    madein i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		query.append("        AND i.kindof_id = 2 ");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> madein_kindof_2_r = q.getResultList();
		BigInteger madein_kindof_2 = madein_kindof_2_r.get(0);
 	
		if(madein_kindof_2 == null) {
			madein_kindof_2 = BigInteger.valueOf(0L); 
		}
		
		madein_related.put("madein_kindof_2", madein_kindof_2);
		
		R.put("madein", madein_related);
		
		
		
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 품질 보증 기간  
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		Map<String, Object> warranty_related = new HashMap<String, Object>();		
		
		
//		품질보증기간
//		총 등록 수 : 1231

		query = new StringBuffer(); 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    warranty i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		
		q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> warranty_all_count_r = q.getResultList();
		BigInteger warranty_all_count = warranty_all_count_r.get(0);
		
		warranty_related.put("warranty_all_count", warranty_all_count);
		 
//		품질보증기간
//		신규 등록 수 : 14
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    warranty i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y' ");
		query.append("        AND DATE(i.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> warranty_today_new_item_r = q.getResultList();
		BigInteger warranty_today_new_item = warranty_today_new_item_r.get(0);
		warranty_related.put("warranty_today_new_item", warranty_today_new_item);
		
//		품질보증기간
//		관리자입력 : 5
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    warranty i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		query.append("        AND i.kindof_id = 1 ");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> warranty_kindof_1_r = q.getResultList();
		BigInteger warranty_kindof_1 = warranty_kindof_1_r.get(0);
	 
		warranty_related.put("warranty_kindof_1", warranty_kindof_1);
//		품질보증기간
//		직접입력 : 5
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    warranty i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		query.append("        AND i.kindof_id = 2 ");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> warranty_kindof_2_r = q.getResultList();
		BigInteger warranty_kindof_2 = warranty_kindof_2_r.get(0);
		warranty_related.put("warranty_kindof_2", warranty_kindof_2); 
		
		
		R.put("warranty", warranty_related);
		
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 품질 보증 기간  
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		Map<String, Object> material_related = new HashMap<String, Object>();		
		
		
		
		
//		소재
//		총 등록 수 : 1231

		query = new StringBuffer(); 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    material i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		
		q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> material_all_count_r = q.getResultList();
		BigInteger material_all_count = material_all_count_r.get(0);
		
		material_related.put("material_all_count", material_all_count);
		 
		
//		소재
//		신규 등록 수 : 14
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    material i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y' ");
		query.append("        AND DATE(i.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> material_today_new_item_r = q.getResultList();
		BigInteger material_today_new_item = material_today_new_item_r.get(0);
	 	
		material_related.put("material_today_new_item", material_today_new_item);
		
		R.put("material", material_related);
		
		
		
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 사이즈 옵션 
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		Map<String, Object> size_option_related = new HashMap<String, Object>();		
		
//		옵션
//		총 등록 수 : 1231

		query = new StringBuffer(); 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    size_option i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		
		q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> size_option_all_count_r = q.getResultList();
		BigInteger size_option_all_count = size_option_all_count_r.get(0);
		size_option_related.put("size_option_all_count", size_option_all_count);
		
		
//		옵션
//		신규 등록 수 : 14
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    size_option i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y' ");
		query.append("        AND DATE(i.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> size_option_today_new_item_r = q.getResultList();
		BigInteger size_option_today_new_item = size_option_today_new_item_r.get(0);
		size_option_related.put("size_option_today_new_item", size_option_today_new_item);
		
		
//		옵션
//		'6', 'size_option', '기호', 'Y'
//		'7', 'size_option', '숫자', 'Y'
//		'8', 'size_option', '직접입력', 'Y'
//		'9', 'size_option', '숫자 - 하위', 'Y'

		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    size_option i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		query.append("        AND i.kindof_id = 8 ");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> size_option_kindof_8_r = q.getResultList();
		BigInteger size_option_kindof_8 = size_option_kindof_8_r.get(0);
		size_option_related.put("size_option_kindof_8", size_option_kindof_8);
		
		
		
//		옵션
//		'6', 'size_option', '기호', 'Y'
//		'7', 'size_option', '숫자', 'Y'
//		'8', 'size_option', '직접입력', 'Y'
//		'9', 'size_option', '숫자 - 하위', 'Y'
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    size_option i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		query.append("        AND i.kindof_id != 8 ");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> size_option_kindof_not_8_r = q.getResultList();
		BigInteger size_option_kindof_not_8 = size_option_kindof_not_8_r.get(0);
		size_option_related.put("size_option_kindof_not_8", size_option_kindof_not_8);
		
		R.put("size_option", size_option_related);
		
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 브랜드 
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		Map<String, Object> brand_related = new HashMap<String, Object>();		
			
//		총 등록 수 : 1231	

		query = new StringBuffer(); 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    brand i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		
		q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> brand_all_count_r = q.getResultList();
		BigInteger brand_all_count = brand_all_count_r.get(0);
		
		brand_related.put("brand_all_count", brand_all_count);
		 
//		신규 등록 수 : 14	
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    brand i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y' ");
		query.append("        AND DATE(i.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> brand_today_new_item_r = q.getResultList();
		BigInteger brand_today_new_item = brand_today_new_item_r.get(0); 
		brand_related.put("brand_today_new_item", brand_today_new_item);
		
		
//		삭제건수 : 5	
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    brand i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y' ");
		query.append("        AND DATE(i.delete_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> brand_today_delete_item_r = q.getResultList();
		BigInteger brand_today_delete_item = brand_today_delete_item_r.get(0); 
		brand_related.put("brand_today_delete_item", brand_today_delete_item);
		
		
		
		
		 
//		전일 대비 증감율: 5%
		
		query = new StringBuffer();
		   
		query.append("SELECT ");
		query.append("    FLOOR((today.new_count / yesterday.new_count) * 100) AS increase_rate ");
		query.append("FROM ");
		query.append("    (SELECT ");
		query.append("        COUNT(*) AS new_count ");
		query.append("    FROM ");
		query.append("        brand i ");
		query.append("    WHERE ");
		query.append("        i.use_yn = 'Y' ");
		query.append("            AND DATE(i.create_dt) = DATE(NOW() - 1)) yesterday, ");
		query.append("    (SELECT ");
		query.append("        COUNT(*) AS new_count ");
		query.append("    FROM ");
		query.append("        brand i ");
		query.append("    WHERE ");
		query.append("        i.use_yn = 'Y' ");
		query.append("            AND DATE(i.create_dt) = DATE(NOW())) today");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> brand_increase_rate_r = q.getResultList();
		BigInteger brand_increase_rate = brand_increase_rate_r.get(0); 
		if(brand_increase_rate == null) {
			brand_increase_rate = BigInteger.valueOf(0L); 
		}
		brand_related.put("brand_increase_rate", brand_increase_rate);
		
		

		 
//		단일 사용 : 1231
		
		query = new StringBuffer();
		  
		query.append("SELECT ");
		query.append("    COUNT(*) AS brand_count ");
		query.append("FROM ");
		query.append("    (SELECT ");
		query.append("        ct.brand, COUNT(ct.member) AS use_count ");
		query.append("    FROM ");
		query.append("        (SELECT ");
		query.append("        i.brand_id AS brand, i.member_id AS member ");
		query.append("    FROM ");
		query.append("        item i ");
		query.append("    JOIN brand b ON i.brand_id = b.id AND b.use_yn = 'Y' ");
		query.append("    JOIN member m ON i.member_id = m.id AND m.use_yn = 'Y' ");
		query.append("    WHERE ");
		query.append("        i.use_yn = 'Y' ");
		query.append("    GROUP BY i.brand_id , i.member_id) ct ");
		query.append("    GROUP BY ct.brand) aaa ");
		query.append("WHERE ");
		query.append("    aaa.use_count = 1");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> brand_use_count_1_r = q.getResultList();
		BigInteger brand_use_count_1 = brand_use_count_1_r.get(0); 

		brand_related.put("brand_use_count_1", brand_use_count_1);
		
//		복수 사용 : 1231
		
		query = new StringBuffer();
		  
		query.append("SELECT ");
		query.append("    COUNT(*) AS brand_count ");
		query.append("FROM ");
		query.append("    (SELECT ");
		query.append("        ct.brand, COUNT(ct.member) AS use_count ");
		query.append("    FROM ");
		query.append("        (SELECT ");
		query.append("        i.brand_id AS brand, i.member_id AS member ");
		query.append("    FROM ");
		query.append("        item i ");
		query.append("    JOIN brand b ON i.brand_id = b.id AND b.use_yn = 'Y' ");
		query.append("    JOIN member m ON i.member_id = m.id AND m.use_yn = 'Y' ");
		query.append("    WHERE ");
		query.append("        i.use_yn = 'Y' ");
		query.append("    GROUP BY i.brand_id , i.member_id) ct ");
		query.append("    GROUP BY ct.brand) aaa ");
		query.append("WHERE ");
		query.append("    aaa.use_count >= 2");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> brand_use_count_2_r = q.getResultList();
		BigInteger brand_use_count_2 = brand_use_count_2_r.get(0); 
		brand_related.put("brand_use_count_2", brand_use_count_2);
		

		 
//		연동 상품 수 : 5
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(ct.brand) as link_count ");
		query.append("FROM ");
		query.append("    (SELECT ");
		query.append("        i.brand_id AS brand ");
		query.append("    FROM ");
		query.append("        item i ");
		query.append("    WHERE ");
		query.append("        i.use_yn = 'Y' AND i.link_yn = 'Y' ");
		query.append("    GROUP BY i.brand_id) ct");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> brand_link_count_r = q.getResultList();
		BigInteger brand_link_count = brand_link_count_r.get(0); 
		brand_related.put("brand_link_count", brand_link_count);
		
		
		
		
		R.put("brand", brand_related);
		
		
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 쇼핑몰 
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		Map<String, Object> shopmall_related = new HashMap<String, Object>();		
		
		
		
		 
//		총 등록 수 : 1231	

		query = new StringBuffer(); 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    shopmall i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		
		q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> shopmall_all_count_r = q.getResultList();
		BigInteger shopmall_all_count = shopmall_all_count_r.get(0); 
		
		shopmall_related.put("shopmall_all_count", shopmall_all_count);
		
//		신규 등록 수 : 14	
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    shopmall i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y' ");
		query.append("        AND DATE(i.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> shopmall_today_new_item_r = q.getResultList();
		BigInteger shopmall_today_new_item = shopmall_today_new_item_r.get(0); 
		shopmall_related.put("shopmall_today_new_item", shopmall_today_new_item);
		
//		삭제건수 : 5	
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    shopmall i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y' ");
		query.append("        AND DATE(i.delete_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> shopmall_today_delete_item_r = q.getResultList();
		BigInteger shopmall_today_delete_item = shopmall_today_delete_item_r.get(0); 
		
		shopmall_related.put("shopmall_today_delete_item", shopmall_today_delete_item);
		
		
		
		 
//		전일 대비 증감율: 5%
		
		query = new StringBuffer();
		   
		query.append("SELECT ");
		query.append("    FLOOR((today.new_count / yesterday.new_count) * 100) AS increase_rate ");
		query.append("FROM ");
		query.append("    (SELECT ");
		query.append("        COUNT(*) AS new_count ");
		query.append("    FROM ");
		query.append("        shopmall i ");
		query.append("    WHERE ");
		query.append("        i.use_yn = 'Y' ");
		query.append("            AND DATE(i.create_dt) = DATE(NOW() - 1)) yesterday, ");
		query.append("    (SELECT ");
		query.append("        COUNT(*) AS new_count ");
		query.append("    FROM ");
		query.append("        shopmall i ");
		query.append("    WHERE ");
		query.append("        i.use_yn = 'Y' ");
		query.append("            AND DATE(i.create_dt) = DATE(NOW())) today");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> shopmall_increase_rate_r = q.getResultList();
		BigInteger shopmall_increase_rate = shopmall_increase_rate_r.get(0); 
		if(shopmall_increase_rate == null) {
			shopmall_increase_rate = BigInteger.valueOf(0L); 
		}
		shopmall_related.put("shopmall_increase_rate", shopmall_increase_rate);
		
		 
//		단일 사용 : 1231
		
		query = new StringBuffer();
		   
		query.append("SELECT ");
		query.append("    COUNT(*) ");
		query.append("FROM ");
		query.append("    (SELECT ");
		query.append("        ct.shopmall, COUNT(ct.member) AS use_count ");
		query.append("    FROM ");
		query.append("        (SELECT ");
		query.append("        s.id AS shopmall, i.member_id AS member ");
		query.append("    FROM ");
		query.append("        item_shopmall_map ism ");
		query.append("    LEFT JOIN item i ON ism.item_id = i.id AND i.use_yn = 'Y' ");
		query.append("    LEFT JOIN shopmall s ON ism.shopmall_id = s.id ");
		query.append("        AND s.use_yn = 'Y' ");
		query.append("    WHERE ");
		query.append("        ism.use_yn = 'Y' ");
		query.append("    GROUP BY s.id , i.member_id) ct ");
		query.append("    GROUP BY ct.shopmall) aaa ");
		query.append("WHERE ");
		query.append("    aaa.use_count = 1");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> shopmall_use_count_1_r = q.getResultList();
		BigInteger shopmall_use_count_1 = shopmall_use_count_1_r.get(0);
	 
		shopmall_related.put("shopmall_use_count_1", shopmall_use_count_1);
		
		 
//		복수 사용 : 1231
		
		query = new StringBuffer();
		  
		query.append("SELECT ");
		query.append("    COUNT(*) ");
		query.append("FROM ");
		query.append("    (SELECT ");
		query.append("        ct.shopmall, COUNT(ct.member) AS use_count ");
		query.append("    FROM ");
		query.append("        (SELECT ");
		query.append("        s.id AS shopmall, i.member_id AS member ");
		query.append("    FROM ");
		query.append("        item_shopmall_map ism ");
		query.append("    LEFT JOIN item i ON ism.item_id = i.id AND i.use_yn = 'Y' ");
		query.append("    LEFT JOIN shopmall s ON ism.shopmall_id = s.id ");
		query.append("        AND s.use_yn = 'Y' ");
		query.append("    WHERE ");
		query.append("        ism.use_yn = 'Y' ");
		query.append("    GROUP BY s.id , i.member_id) ct ");
		query.append("    GROUP BY ct.shopmall) aaa ");
		query.append("WHERE ");
		query.append("    aaa.use_count > 1");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> shopmall_use_count_2_r = q.getResultList();
		BigInteger shopmall_use_count_2 = shopmall_use_count_2_r.get(0);
	 
		shopmall_related.put("shopmall_use_count_2", shopmall_use_count_2);
		
//		연동 상품 수 : 5
		
		query = new StringBuffer();  
		query.append("SELECT ");
		query.append("    COUNT(ct.shopmall) AS use_count ");
		query.append("FROM ");
		query.append("    (SELECT ");
		query.append("        ct.shopmall, COUNT(ct.item) AS use_count ");
		query.append("    FROM ");
		query.append("        (SELECT ");
		query.append("        ism.shopmall_id AS shopmall, ");
		query.append("            ism.item_id AS item, ");
		query.append("            COUNT(m.id) AS use_count ");
		query.append("    FROM ");
		query.append("        item_shopmall_map ism ");
		query.append("    LEFT JOIN item i ON ism.item_id = i.id AND i.use_yn = 'Y' ");
		query.append("        AND i.link_yn = 'Y' ");
		query.append("    LEFT JOIN member m ON i.member_id = m.id AND m.use_yn = 'Y' ");
		query.append("    WHERE ");
		query.append("        ism.use_yn = 'Y' ");
		query.append("    GROUP BY ism.shopmall_id , ism.item_id) ct ");
		query.append("    WHERE ");
		query.append("        ct.use_count > 0 ");
		query.append("    GROUP BY ct.shopmall) ct");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> shopmall_link_count_r = q.getResultList();
		BigInteger shopmall_link_count = shopmall_link_count_r.get(0);
		shopmall_related.put("shopmall_link_count", shopmall_link_count);
		
		
		
		R.put("shopmall", shopmall_related);
		
		return R;
	}

	
	public String send_sms(String p_from_phone,String p_to_phone,String p_msg) {
		

		BizSend bs        = new BizSend();    // 메시지 전송 클래스
		SendMsgEntity sme = null;             // 메시지 클래스

		/* Console 로그 설정 */
		// Console 에서 로그를 확인할 경우 설정
		bs.setLogEnabled(true);

		/* 서버 연결 시작 & 인증 */
		// ip   = biz.ppurio.com = 211.189.43.25
		// port = 5000 / 15001
		bs.doBegin("biz.ppurio.com", 15001, "user_id", "user_pw");

		/* 전송할 파일 경로 설정 */
		// 전송할 파일이 있을 경우 설정
		// ex. FAX, PHONE, MMS 등
//		bs.setFilePath("./spool");

		/* 블랙리스트 파일 경로 설정 */
		// 블랙리스트를 사용할 경우 설정
//		bs.setBlkPath("./blk");

		/* PING-PONG */
		// 장시간 연결하여 메시지를 전송할 경우 연결이 끊어지지 않기 위해 실행
		// 이전 호출 시점으로부터 30초 이상 지난 경우에만 PING 을 전송하도록 함수내  정의되어 있음
		// 실행하지 않아도 메시지 전송에는 영향을 미치지 않으나 안전한 연결을 위해 주기적으로 실행하길 권장
//		boolean bool = bs.sendPing();
//		System.out.println("Result of a Ping=" + bool);

		/* 리포트 재요청 */
		// msgid 에 해당하는 메시지의 리포트가 시간이 지나도 오지 않는 경우 실행
//		bool = bs.reconfirmReport("");    // @param msgid    다우기술의 서버에서 정의한 message id
//		System.out.println("Result of Reconfirming=" + bool);

		/* 메시지 정의 */
		// 다음의 setter 를 사용하여 필요한 정보를 정의
		// ex. SMS 의 경우, MSG_TYPE, DEST_PHONE, SEND_PHONE, MSG_BODY 정보를 정의
		sme = new SendMsgEntity();
		sme.setCMID      ("");    // 데이터 id
		sme.setMSG_TYPE  (0 );    // 데이터 타입 (SMS 0 / WAP 1 / FAX 2 / PHONE 3 / SMS_INBOUND 4 / MMS 5)
		sme.setSEND_TIME ("");    // 발송 (예약) 시간 (Unix Time, 정의하지 않을 경우 즉시 발송)
		sme.setDEST_PHONE(p_to_phone);    // 받는 사람 전화 번호
		sme.setDEST_NAME ("");    // 받는 사람 이름
		sme.setSEND_PHONE("07041180133");    // 보내는 사람 전화 번호
		sme.setSEND_NAME ("");    // 보내는 사람 이름
		sme.setSUBJECT   ("");    // (FAX/MMS) 제목, (SMS_INBOUND) 데이터 내용
		sme.setMSG_BODY  ("");    // 데이터 내용
		sme.setWAP_URL   ("");    // (WAP) URL 주소
		sme.setCOVER_FLAG(0 );    // (FAX) 표지 발송 옵션
		sme.setSMS_FLAG  (0 );    // (PHONE) PHONE 실패 시 문자 전송 옵션
		sme.setREPLY_FLAG(0 );    // (PHONE) 응답 받기 선택
		sme.setRETRY_CNT (0 );    // (FAX/PHONE) 재시도 회수 (5~10분 간격: 최대 3회)
		sme.setFAX_FILE  ("");    // (PHONE/FAX/MMS) 파일 전송시 파일 이름
		sme.setVXML_FILE ("");    // (PHONE) 음성 시나리오 파일 이름

		/* 메시지 전송 */
		try {
			String msgid = "";
			msgid = bs.sendMsg(sme);    // @param  sme       SendMsgEntity
			                            // @retrun String    다우기술의 서버에서 정의한 message id

			System.out.println("msgid=" + msgid);
		} catch (Exception ex) {
			System.out.println("Failed to Send a Msg " + ex.getMessage());
		}

		/* 서버 연결 종료 */
		bs.doEnd();
		
		return CommonConstants.OK;
	}
}