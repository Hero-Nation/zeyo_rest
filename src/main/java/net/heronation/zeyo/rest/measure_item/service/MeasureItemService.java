package net.heronation.zeyo.rest.measure_item.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import net.heronation.zeyo.rest.common.dto.ToggleDto;
import net.heronation.zeyo.rest.measure_item.controller.MeasureItemUpdateDto;
import net.heronation.zeyo.rest.measure_item.repository.MeasureItemDto;

public interface MeasureItemService {
	Map<String, Object> search(Map<String, Object> where, Pageable page);

	Map<String, Object> detail_list(Map<String, Object> where, Pageable page);
	
	String insert(MeasureItemDto param);
	
	String update(MeasureItemUpdateDto param);
	
	Map<String, Object> delete( List<ToggleDto> param, Long member_seq);
}