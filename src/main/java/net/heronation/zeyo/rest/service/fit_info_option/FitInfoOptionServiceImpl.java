package net.heronation.zeyo.rest.service.fit_info_option;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOptionRepository;

@Slf4j
@Service
@Transactional
public class FitInfoOptionServiceImpl implements FitInfoOptionService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private FitInfoOptionRepository fit_info_optionRepository;

	@Autowired
	EntityManager entityManager;
	
	
	@Override
	public Map<String, Object> select_options(String fitinfo){
 

		StringBuffer select_query = new StringBuffer(); 
		select_query.append("SELECT ");
		select_query.append("    fio.id ");
		select_query.append("   , fio.name ");
		select_query.append("FROM ");
		select_query.append("    fit_info_option fio ");
		select_query.append("WHERE ");
		select_query.append("    fio.use_yn = 'Y' and fio.fit_info_id = "+fitinfo);
 

		Query count_q = entityManager.createNativeQuery(select_query.toString());
		List<Object[]> list = count_q.getResultList(); 
		
		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();
			search_R.put("fit_info_option_id", row[0]);
			search_R.put("fit_info_option_name", row[1]); 

			return_list.add(search_R);
		}
 
		Map<String, Object> R = new HashMap<String, Object>();
		R.put("content", return_list); 

		return R;
	}

}