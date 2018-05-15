package net.heronation.zeyo.rest.service.shopmall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;

public interface ShopmallService {
	Page<Shopmall> search(Predicate where,Pageable page); 
	Page<Shopmall> client_search(Predicate where,Pageable page); 
	
	Shopmall insert(String name,Long member_seq); 
}