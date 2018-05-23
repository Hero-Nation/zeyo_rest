package net.heronation.zeyo.rest.service.fit_info;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.fit_info.FitInfo;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;

public interface FitInfoService {
	Page<Map<String,Object>> search(Predicate where, Pageable page); 
	Page<Map<String,Object>> fitInfoOptions_search(Predicate where, Pageable page); 
}