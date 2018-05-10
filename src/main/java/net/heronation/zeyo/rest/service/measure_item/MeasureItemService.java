package net.heronation.zeyo.rest.service.measure_item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;

public interface MeasureItemService {
	Page<MeasureItem> search(Predicate where, Pageable page);

}