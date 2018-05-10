package net.heronation.zeyo.rest.service.fit_info;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.fit_info.FitInfo;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;

public interface FitInfoService {
	Page<FitInfo> search(Predicate where, Pageable page);

}