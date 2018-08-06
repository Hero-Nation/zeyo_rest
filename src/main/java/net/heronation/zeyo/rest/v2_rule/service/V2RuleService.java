package net.heronation.zeyo.rest.v2_rule.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import net.heronation.zeyo.rest.v2_rule.controller.V2RuleDto;
import net.heronation.zeyo.rest.v2_rule.repository.V2Rule;

public interface V2RuleService {
	Map<String, Object> search(Map<String, Object> where, Pageable page);
	Map<String, Object> single(long id);
	String delete(List<String> param,Long seq);
	String insert(V2RuleDto insertDto);
	String update(V2RuleDto updateDto);
}