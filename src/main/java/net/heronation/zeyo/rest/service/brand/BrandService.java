package net.heronation.zeyo.rest.service.brand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.madein.Madein;

public interface BrandService {
	Page<Brand> search(Predicate where,Pageable page); 
	 
}