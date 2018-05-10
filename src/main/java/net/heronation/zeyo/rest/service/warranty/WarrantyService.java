package net.heronation.zeyo.rest.service.warranty;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.warranty.Warranty;

public interface WarrantyService {
	Page<Warranty> search(Predicate where,Pageable page); 
	 
}