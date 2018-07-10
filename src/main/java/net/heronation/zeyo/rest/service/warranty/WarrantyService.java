package net.heronation.zeyo.rest.service.warranty;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import net.heronation.zeyo.rest.common.dto.IdNameDto;
import net.heronation.zeyo.rest.common.dto.LIdDto;
import net.heronation.zeyo.rest.controller.warranty.ScopeDto;

public interface WarrantyService {
	Map<String, Object> search(Map<String, Object> where, Pageable page);

	String insert(ScopeDto param);

	String update(IdNameDto param);

	String delete(List<LIdDto> param);
}