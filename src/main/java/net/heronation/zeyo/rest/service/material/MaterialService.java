package net.heronation.zeyo.rest.service.material;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.material.Material;

public interface MaterialService {
	Page<Material> search(Predicate where,Pageable page); 
	
}