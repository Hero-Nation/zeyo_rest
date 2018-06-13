package net.heronation.zeyo.rest.service.measure_item;

import java.util.Map;

import org.springframework.data.domain.Pageable;

public interface MeasureItemService {
	 Map<String,Object> search( Map<String,Object> where, Pageable page);

}