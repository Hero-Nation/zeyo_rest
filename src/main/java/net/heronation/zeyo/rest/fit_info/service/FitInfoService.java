package net.heronation.zeyo.rest.fit_info.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.common.dto.LIdDto;
import net.heronation.zeyo.rest.fit_info.repository.FitInfoDto;
import net.heronation.zeyo.rest.fit_info.repository.FitInfoUpdateDto;

public interface FitInfoService {
	Map<String,Object> search(Map<String,Object> where, Pageable page); 
	Map<String,Object> detail_list(Map<String,Object> where, Pageable page); 
	
	
	
	Page<Map<String,Object>> fitInfoOptions_search(Predicate where, Pageable page); 
	String insert(FitInfoDto param);
	String update(FitInfoUpdateDto param);
	String delete(List<LIdDto> param);
}