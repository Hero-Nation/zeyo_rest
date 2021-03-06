package net.heronation.zeyo.rest.service.shopmall;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallDto;

public interface ShopmallService {
	Map<String, Object> search(Map<String, Object> where, Pageable page);

	Page<Map<String, Object>> client_search(Map<String, Object> where, Pageable page);

	Shopmall insert(ShopmallDto param, Long member_seq);

	Map<String, Object> update_name(ShopmallDto param, Long member_seq);

	Map<String, Object> delete(ShopmallDto param, Long member_seq);

	Map<String, Object> toggle_link(ShopmallDto param, Long member_seq);

	Page<Map<String, Object>> detail(Long shopmall_id, Long member_seq, Pageable page); 

	Map<String, Object> check_unique_name(String name);

	Map<String, Object> detail_info(Long id); 
	
	
	Map<String, Object> shopmall_company_use_list(Pageable page);
}