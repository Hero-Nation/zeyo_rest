package net.heronation.zeyo.rest.bbs.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import net.heronation.zeyo.rest.bbs.controller.UpdateStatusDto;
import net.heronation.zeyo.rest.bbs.repository.Bbs;
import net.heronation.zeyo.rest.bbs.repository.BbsClientInsertDto;
import net.heronation.zeyo.rest.common.controller.CommonException;

public interface BbsService {
	Map<String,Object> search(Map<String,Object> where,Pageable page);  
	Map<String,Object> client_search(Map<String,Object> where,Pageable page);  
	
	
	Bbs client_insert(BbsClientInsertDto new_post,Long member_id) throws CommonException;
	
	Map<String, Object> getStat();
	
	String update_status(UpdateStatusDto param);
}