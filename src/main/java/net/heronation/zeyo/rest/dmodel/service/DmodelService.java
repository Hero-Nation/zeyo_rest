package net.heronation.zeyo.rest.dmodel.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import net.heronation.zeyo.rest.common.dto.ToggleDto;
import net.heronation.zeyo.rest.dmodel.controller.DmodelDto;

public interface DmodelService {
	Map<String, Object> search(Map<String, Object> where, Pageable page);
	String delete(List<String> param,Long seq);
	String insert(DmodelDto insertDto);
}