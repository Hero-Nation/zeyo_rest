package net.heronation.zeyo.rest.service.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.item.Item;

public interface ItemService {
	Page<Item> search(Predicate where, Pageable page);

}