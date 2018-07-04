package net.heronation.zeyo.rest.service.measure_item;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import net.heronation.zeyo.rest.repository.measure_item.MeasureItemDto;

public interface MeasureItemService {
	Map<String, Object> search(Map<String, Object> where, Pageable page);

	Map<String, Object> detail_list(Map<String, Object> where, Pageable page);
	
	String insert(MeasureItemDto param); 
}