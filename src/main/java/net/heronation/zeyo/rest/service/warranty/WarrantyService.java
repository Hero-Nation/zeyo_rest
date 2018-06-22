package net.heronation.zeyo.rest.service.warranty;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import net.heronation.zeyo.rest.common.value.IdNameVO;
import net.heronation.zeyo.rest.common.value.LIdVO;
import net.heronation.zeyo.rest.common.value.NameVO;
import net.heronation.zeyo.rest.controller.warranty.ScopeVO;

public interface WarrantyService {
	Map<String, Object> search(Map<String, Object> where, Pageable page);

	String insert(ScopeVO param);

	String update(IdNameVO param);

	String delete(List<LIdVO> param);
}