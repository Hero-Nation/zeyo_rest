package net.heronation.zeyo.rest.service.size_option;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;

public interface SizeOptionService {
	Page<SizeOption> search(Predicate where,Pageable page); 
}