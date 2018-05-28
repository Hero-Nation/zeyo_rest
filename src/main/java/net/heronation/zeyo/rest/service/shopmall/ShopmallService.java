package net.heronation.zeyo.rest.service.shopmall;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
 
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;

public interface ShopmallService {
	Page<Map<String,Object>> search(Map<String,Object> where,Pageable page);
	Page<Map<String,Object>> client_search(Map<String,Object> where,Pageable page); 
	
	Shopmall insert(String name,Long member_seq); 
	
	
 	Map<String,Object> update(Long shopmall_id,Long member_seq,String name); 
 	Map<String,Object> delete(Long shopmall_id,Long member_seq); 
 	Map<String,Object> toggle_link(Long shopmall_id,Long member_seq);
 	Page<Map<String, Object>> detail(Long shopmall_id,Long member_seq, Pageable page);
}