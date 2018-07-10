package net.heronation.zeyo.rest.service.madein;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.common.dto.IdNameDto;
import net.heronation.zeyo.rest.common.dto.LIdDto;
import net.heronation.zeyo.rest.common.dto.NameDto;

public interface MadeinService {

	Map<String, Object> search(Map<String, Object> where, Pageable page);

	Map<String, Object> use_list(Predicate where, Pageable page);

	String insert(NameDto param);

	String update(IdNameDto param);

	String delete(List<LIdDto> param);

}