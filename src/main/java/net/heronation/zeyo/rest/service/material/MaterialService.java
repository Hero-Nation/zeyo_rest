package net.heronation.zeyo.rest.service.material;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import net.heronation.zeyo.rest.common.controller.CommonException;
import net.heronation.zeyo.rest.common.dto.LIdDto;
import net.heronation.zeyo.rest.controller.material.MateriaApiDto;

public interface MaterialService {
	//Page<Material> search(Predicate where,Pageable page);
	
	Map<String,Object> search(Map<String,Object> where,Pageable page);
	
	String insert(MateriaApiDto param) throws CommonException;
	
	String update(MateriaApiDto param) throws CommonException;
	
	String delete(List<LIdDto> param);
	
	
}