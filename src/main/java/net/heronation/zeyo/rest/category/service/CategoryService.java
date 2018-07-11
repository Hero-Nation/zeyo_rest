package net.heronation.zeyo.rest.category.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import net.heronation.zeyo.rest.common.dto.IdNameDto;
import net.heronation.zeyo.rest.common.dto.LIdDto;
import net.heronation.zeyo.rest.common.dto.NameDto;

public interface CategoryService {
	Map<String, Object> search(Map<String, Object> where, Pageable page);

	Map<String, Object> pure_search(Map<String, Object> where, Pageable page);

	String insert(NameDto param);
	
	String update(IdNameDto param);

	String delete(List<LIdDto> param);
}