package net.heronation.zeyo.rest.service.material;

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
import net.heronation.zeyo.rest.common.value.LIdDto;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.controller.material.MateriaApiDto;
import net.heronation.zeyo.rest.repository.material.Material;
import net.heronation.zeyo.rest.repository.material.MaterialRepository;

@Slf4j
@Service

public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MaterialRepository materialRepository;

	@Autowired
	EntityManager entityManager;
	

	@Value(value = "${zeyo.path.upload.temp}")
	private String path_temp_upload;

	@Value(value = "${zeyo.path.material.image}")
	private String path_material_upload;


	@Override
	public Map<String, Object> search(Map<String, Object> param, Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT ");
		select_query.append("    m.id			as id, ");
		select_query.append("    m.name			as name, ");
		select_query.append("    m.image		as image, ");
		select_query.append("    m.meta_desc	as metaDesc, ");
		select_query.append("    m.create_dt 	as createDt  ");

		StringBuffer where_query = new StringBuffer();
		where_query.append("FROM ");
		where_query.append("    material m ");
		where_query.append("WHERE ");
		where_query.append("    m.use_yn = 'Y'");

		String name = (String) param.get("name");
		if (name != null) {
			where_query.append("        AND   m.name like '%" + name + "%' ");
		}

		String start = (String) param.get("start");
		if (start != null) {
			where_query.append("        AND m.create_dt >= STR_TO_DATE('" + start + "', '%Y-%m-%d %H:%i:%s')");
		}

		String end = (String) param.get("end");
		if (end != null) {
			where_query.append("        AND m.create_dt <= STR_TO_DATE('" + end + "', '%Y-%m-%d %H:%i:%s')");
		}

		where_query.append("GROUP BY m.id");

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

			// select_query.append(" m.id as id, ");
			// select_query.append(" m.name as name, ");
			// select_query.append(" m.image as image, ");
			// select_query.append(" m.meta_desc as metaDesc, ");
			// select_query.append(" m.create_dt as createDt");

			search_R.put("id", row[0]);
			search_R.put("name", row[1]);
			search_R.put("image", row[2]);
			search_R.put("metaDesc", row[3]);
			search_R.put("createDt", row[4]);

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
	@Transactional
	public String insert(MateriaApiDto param) throws CommonException {
		
		
		materialRepository.save(param.convertToEntity());
		
		if(param.getFiles() != null && param.getFiles().length > 0) {
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

			File source = new File(path_temp_upload.concat(File.separator).concat(dtf.format(now))
					.concat(File.separator).concat(param.getFiles()[0].getTemp_name()));
			File dest = new File(path_material_upload.concat(File.separator).concat(param.getFiles()[0].getTemp_name()
					.concat("_").concat(param.getFiles()[0].getReal_name())));

			try {
				FileUtils.copyFile(source, dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CommonException("material.image.upload.failed");
			}
		}

		// 검수용
		CommandLine.Sync_file();
		
		return CommonConstants.OK;
	}

	@Override
	@Transactional
	public String update(MateriaApiDto param) throws CommonException {
		Material a = materialRepository.findOne(param.getId());
		
		if(param.getFiles() != null && param.getFiles().length > 0) {
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

			File source = new File(path_temp_upload.concat(File.separator).concat(dtf.format(now))
					.concat(File.separator).concat(param.getFiles()[0].getTemp_name()));
			File dest = new File(path_material_upload.concat(File.separator).concat(param.getFiles()[0].getTemp_name()
					.concat("_").concat(param.getFiles()[0].getReal_name())));

			a.setImage(param.getFiles()[0].getTemp_name().concat("_").concat(param.getFiles()[0].getReal_name()));
			
			try {
				FileUtils.copyFile(source, dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CommonException("material.image.upload.failed");
			}
		}
		
		a.setMetaDesc(param.getMetaDesc());
		a.setName(param.getName());

		// 검수용
		CommandLine.Sync_file();
		return CommonConstants.OK;
	}

	@Override
	@Transactional
	public String delete(List<LIdDto> param){
		// TODO Auto-generated method stub
		
		for(LIdDto i : param) {
		
			Material a = materialRepository.findOne(i.getId());
			a.setUseYn("N");
		}
		
		
		return CommonConstants.OK;
	}

}