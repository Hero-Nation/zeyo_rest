package net.heronation.zeyo.rest.service.bbs;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.bbs.Bbs;
import net.heronation.zeyo.rest.repository.bbs.BbsClientInsertDto;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;

public interface BbsService {
	Map<String,Object> search(Map<String,Object> where,Pageable page);  
	Map<String,Object> client_search(Map<String,Object> where,Pageable page);  
	
	
	Bbs client_insert(BbsClientInsertDto new_post,Long member_id);
	
	Map<String, Object> getStat();
}