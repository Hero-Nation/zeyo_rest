package net.heronation.zeyo.rest.service.madein;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.repository.madein.Madein;

public interface MadeinService {
 
	Page<Madein> search(Predicate where,Pageable page); 
 
 
}