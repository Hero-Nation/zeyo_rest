package net.heronation.zeyo.rest.shopmall.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import net.heronation.zeyo.rest.common.dto.NameDto;
import net.heronation.zeyo.rest.common.dto.ToggleDto;
import net.heronation.zeyo.rest.shopmall.repository.Shopmall;
import net.heronation.zeyo.rest.shopmall.repository.ShopmallDto;

public interface ShopmallService {
	Map<String, Object> search(Map<String, Object> where, Pageable page);

	Map<String, Object> client_search(Map<String, Object> where, Pageable page);

	Shopmall insert(NameDto param, Long member_seq);

	Map<String, Object> update_name(ShopmallDto param, Long member_seq);

	Map<String, Object> delete( List<ToggleDto> param, Long member_seq);

	Map<String, Object> toggle_link(List<ToggleDto>  param, Long member_seq);

	Map<String, Object> detail(Long shopmall_id, Long member_seq, Pageable page); 

	Map<String, Object> check_unique_name(String name);

	Map<String, Object> detail_info(Long id); 
	
	
	Map<String, Object> shopmall_company_use_list(Pageable page);
}