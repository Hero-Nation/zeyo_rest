package net.heronation.zeyo.rest.company_no_history.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

public interface CompanyNoHistoryService {

	Map<String, Object> search(Map<String, Object> where, Pageable page);

	Map<String, Object> mylist(Map<String, Object> where, Pageable page);

}