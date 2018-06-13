package net.heronation.zeyo.rest.service.material;

import java.util.Map;

import org.springframework.data.domain.Pageable;

public interface MaterialService {
	//Page<Material> search(Predicate where,Pageable page);
	
	Map<String,Object> search(Map<String,Object> where,Pageable page);
	
	
}