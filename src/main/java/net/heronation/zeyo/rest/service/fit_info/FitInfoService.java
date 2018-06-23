package net.heronation.zeyo.rest.service.fit_info;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.common.value.LIdDto;
import net.heronation.zeyo.rest.repository.fit_info.FitInfoDto;
import net.heronation.zeyo.rest.repository.fit_info.FitInfoUpdateDto;

public interface FitInfoService {
	Map<String,Object> search(Map<String,Object> where, Pageable page); 
	Map<String,Object> detail_list(Map<String,Object> where, Pageable page); 
	
	
	
	Page<Map<String,Object>> fitInfoOptions_search(Predicate where, Pageable page); 
	String insert(FitInfoDto param);
	String update(FitInfoUpdateDto param);
	String delete(List<LIdDto> param);
}