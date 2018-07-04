package net.heronation.zeyo.rest.service.shopmall;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import net.heronation.zeyo.rest.common.value.NameDto;
import net.heronation.zeyo.rest.common.value.ToggleDto;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallDto;

public interface ShopmallService {
	Map<String, Object> search(Map<String, Object> where, Pageable page);

	Map<String, Object> client_search(Map<String, Object> where, Pageable page);

	Shopmall insert(NameDto param, Long member_seq);

	Map<String, Object> update_name(ShopmallDto param, Long member_seq);

	Map<String, Object> delete( List<String> param, Long member_seq);

	Map<String, Object> toggle_link(List<ToggleDto>  param, Long member_seq);

	Map<String, Object> detail(Long shopmall_id, Long member_seq, Pageable page); 

	Map<String, Object> check_unique_name(String name);

	Map<String, Object> detail_info(Long id); 
	
	
	Map<String, Object> shopmall_company_use_list(Pageable page);
}