package net.heronation.zeyo.rest.dmodel.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

public interface DmodelService {
	Map<String, Object> search(Map<String, Object> where, Pageable page);
}