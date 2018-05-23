package net.heronation.zeyo.rest.service.company_no_history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.madein.Madein;

public interface CompanyNoHistoryService {
	Page<CompanyNoHistory> search(Predicate where, Pageable page);
	Page<CompanyNoHistory> mylist(Predicate where, Pageable page);

}