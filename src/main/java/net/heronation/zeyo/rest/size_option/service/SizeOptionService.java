package net.heronation.zeyo.rest.size_option.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.common.dto.LIdDto;
import net.heronation.zeyo.rest.size_option.repository.SizeOptionDto;

public interface SizeOptionService {
	Map<String, Object> search(Map<String, Object> param,Pageable page); 
	
	Map<String, Object> category_count(Predicate where); 
	
	Map<String, Object> single(Predicate where); 
	
	String insert(SizeOptionDto param);

	String update(SizeOptionDto param);

	String delete(List<LIdDto> param);
}