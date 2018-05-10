package net.heronation.zeyo.rest.service.bbs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.bbs.Bbs;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;

public interface BbsService {
	Page<Bbs> search(Predicate where,Pageable page);  
}