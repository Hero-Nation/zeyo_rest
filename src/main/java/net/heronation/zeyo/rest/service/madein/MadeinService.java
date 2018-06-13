package net.heronation.zeyo.rest.service.madein;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.common.value.IdNameVO;
import net.heronation.zeyo.rest.common.value.LIdVO;
import net.heronation.zeyo.rest.common.value.NameVO;

public interface MadeinService {

	Map<String, Object> search(Map<String, Object> where, Pageable page);

	Map<String, Object> use_list(Predicate where, Pageable page);

	String insert(NameVO param);

	String update(IdNameVO param);

	String delete(List<LIdVO> param);

}