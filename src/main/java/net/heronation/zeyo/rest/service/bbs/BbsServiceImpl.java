package net.heronation.zeyo.rest.service.bbs;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.CommonException;
import net.heronation.zeyo.rest.common.util.CommandLine;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.controller.bbs.UpdateStatusDto;
import net.heronation.zeyo.rest.repository.bbs.Bbs;
import net.heronation.zeyo.rest.repository.bbs.BbsClientInsertDto;
import net.heronation.zeyo.rest.repository.bbs.BbsRepository;
import net.heronation.zeyo.rest.repository.kindof.Kindof;
import net.heronation.zeyo.rest.repository.kindof.KindofRepository;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberRepository;

@Slf4j
@Service
@Transactional
public class BbsServiceImpl implements BbsService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private BbsRepository bbsRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private KindofRepository kindofRepository;

	@Autowired
	EntityManager entityManager;

	@Value(value = "${zeyo.path.upload.temp}")
	private String path_temp_upload;

	@Value(value = "${zeyo.path.bbs.image}")
	private String path_bbs_upload;

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> search(Map<String, Object> param, Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT ");
		select_query.append("    b.id			as id, ");
		select_query.append("    k.kvalue		as kindof, ");
		select_query.append("    b.title		as title, ");
		select_query.append("    m.name			as member_name, ");
		select_query.append("    b.create_dt	as createDt, ");
		select_query.append("    b.status 		as status");

		StringBuffer where_query = new StringBuffer();
		where_query.append(" FROM ");
		where_query.append("    bbs b ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    kindof k ON b.kindof_id = k.id AND k.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    member m ON b.member_id = m.id AND m.use_yn = 'Y' ");
		where_query.append(" WHERE ");
		where_query.append("    b.use_yn = 'Y'");

		String title = (String) param.get("title");
		if (title != null) {
			where_query.append("        AND   b.scope like '%" + title + "%' ");
		}

		String start = (String) param.get("start");
		if (start != null) {
			where_query.append("        AND b.create_dt >= STR_TO_DATE('" + start + "', '%Y-%m-%d %H:%i:%s')");
		}

		String end = (String) param.get("end");
		if (end != null) {
			where_query.append("        AND b.create_dt <= STR_TO_DATE('" + end + "', '%Y-%m-%d %H:%i:%s')");
		}

		where_query.append("GROUP BY b.id");

		StringBuffer sort_query = new StringBuffer();
		sort_query.append("  ORDER BY b.");
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

		// item.kindof
		// item.id
		// item.title
		// item.member.name
		// item.createDt
		// item.status

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();

			// select_query.append(" b.id as id, ");
			// select_query.append(" k.kvalue as kindof, ");
			// select_query.append(" b.title as title, ");
			// select_query.append(" m.name as member_name, ");
			// select_query.append(" b.create_dt as createDt, ");
			// select_query.append(" b.status as status");

			search_R.put("id", row[0]);
			search_R.put("kindof", row[1]);
			search_R.put("title", row[2]);
			search_R.put("member_name", row[3]);
			search_R.put("createDt", row[4]);
			search_R.put("status", row[5]);

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

		// JPAQuery<Bbs> query = new JPAQuery<Bbs>(entityManager);
		//
		// QBbs target = QBbs.bbs;
		//
		// QueryResults<Bbs> R = query.from(target)
		// .where(where)
		// //.orderBy(target.id.desc())
		// .offset((page.getPageNumber() - 1)* page.getPageSize())
		// .limit(page.getPageSize())
		// .fetchResults();
		//
		// return new PageImpl<Bbs>(R.getResults(), page, R.getTotal());

	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> client_search(Map<String, Object> param, Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT ");
		select_query.append("    b.id			as id, ");
		select_query.append("    k.kvalue		as kindof, ");
		select_query.append("    b.title		as title, ");
		select_query.append("    m.name			as member_name, ");
		select_query.append("    b.create_dt	as createDt, ");
		select_query.append("    b.status 		as status ,");
		select_query.append("    b.reply_content 		as reply_content ,");
		select_query.append("    b.attach_file 		as attach_file,");
		select_query.append("    b.bbs_content 		as bbs_content");

		// item.kindof
		// item.id
		// item.title
		// item.member.name
		// item.createDt
		// item.status

		StringBuffer where_query = new StringBuffer();
		where_query.append(" FROM ");
		where_query.append("    bbs b ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    kindof k ON b.kindof_id = k.id AND k.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    member m ON b.member_id = m.id AND m.use_yn = 'Y' ");
		where_query.append(" WHERE ");
		where_query.append("    b.use_yn = 'Y'");

		Long member_id = (Long) param.get("member_id");
		where_query.append("  and  b.member_id = " + member_id);

		where_query.append(" GROUP BY b.id");

		StringBuffer sort_query = new StringBuffer();
		sort_query.append("  ORDER BY b.");
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

			search_R.put("id", row[0]);
			search_R.put("kindof", row[1]);
			search_R.put("title", row[2]);
			search_R.put("member_name", row[3]);
			search_R.put("createDt", row[4]);
			search_R.put("status", row[5]);
			search_R.put("reply_content", row[6]);
			search_R.put("file", row[7]);
			search_R.put("bbs_content", row[8]);

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
	@Transactional
	public Bbs client_insert(BbsClientInsertDto new_bbs, Long member_id) throws CommonException {

		Member client = memberRepository.getOne(member_id);
		Kindof kind = kindofRepository.findOne(4L);

		Bbs new_post = new_bbs.getNewBbs();
		new_post.setMember(client);
		new_post.setKindof(kind);

		if (new_bbs.getAttachFile() != null && new_bbs.getAttachFile().length > 0) {
			new_post.setAttach_file(new_bbs.getAttachFile()[0].getTemp_name().concat("_")
					.concat(new_bbs.getAttachFile()[0].getReal_name()));
		}

		bbsRepository.save(new_post);

		if (new_bbs.getAttachFile() != null && new_bbs.getAttachFile().length > 0) {
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

			File source = new File(path_temp_upload.concat(File.separator).concat(dtf.format(now))
					.concat(File.separator).concat(new_bbs.getAttachFile()[0].getTemp_name()));
			File dest = new File(path_bbs_upload.concat(File.separator).concat(new_bbs.getAttachFile()[0].getTemp_name()
					.concat("_").concat(new_bbs.getAttachFile()[0].getReal_name())));

			try {
				FileUtils.copyFile(source, dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CommonException("item.image.upload.failed");
			}
			
			// 검수용
			CommandLine.Sync_file();
			
		}

		return new_post;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getStat() {

		// 총 게시글 수 : 1231
		// 신규 게시글 수 : 42
		// 답변 완료 : 1231
		// 제휴 문의 : 42
		// 1대1 문의 : 123
		// 확인 중 : 42
		// 접수 중 : 123

		// 총 게시글 수 : 1231

		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    bbs b ");
		query.append("WHERE ");
		query.append("    b.use_yn = 'Y'");

		Query q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> all_count_r = q.getResultList();
		BigInteger all_count = all_count_r.get(0);

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
		List<BigInteger> today_all_count_r = q.getResultList();
		BigInteger today_all_count = today_all_count_r.get(0);

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
		List<BigInteger> today_complete_r = q.getResultList();
		BigInteger today_complete = today_complete_r.get(0);

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
		List<BigInteger> today_ing_r = q.getResultList();
		BigInteger today_ing = today_ing_r.get(0);

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
		List<BigInteger> today_receiving_r = q.getResultList();
		BigInteger today_receiving = today_receiving_r.get(0);

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
		List<BigInteger> kindof_3_r = q.getResultList();
		BigInteger kindof_3 = kindof_3_r.get(0);

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
		List<BigInteger> kindof_4_r = q.getResultList();
		BigInteger kindof_4 = kindof_4_r.get(0);

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("all_count", all_count);
		R.put("today_all_count", today_all_count);
		R.put("today_ing", today_ing);
		R.put("today_receiving", today_receiving);
		R.put("kindof_3", kindof_3);
		R.put("kindof_4", kindof_4);

		return R;
	}

	@Override
	@Transactional
	public String update_status(UpdateStatusDto param) {
		// TODO Auto-generated method stub

		Bbs post = bbsRepository.findOne(param.getId());
		if (param.getStatus().equals("C")) {
			post.setReplyContent(param.getReply_content());
		} else {

		}
		post.setStatus(param.getStatus());

		return CommonConstants.OK;
	}
}