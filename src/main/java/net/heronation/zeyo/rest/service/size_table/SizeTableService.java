package net.heronation.zeyo.rest.service.size_table;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.size_table.SizeTable;

public interface SizeTableService {
	Page<SizeTable> search(Predicate where, Pageable page);
}