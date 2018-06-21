package net.heronation.zeyo.rest.service.measure_item;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import lombok.Value;
import net.heronation.zeyo.rest.common.value.LIdVO;
import net.heronation.zeyo.rest.repository.fit_info.FitInfoDto;
import net.heronation.zeyo.rest.repository.fit_info.FitInfoUpdateDto;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItemDto;

public interface MeasureItemService {
	Map<String, Object> search(Map<String, Object> where, Pageable page);

	Map<String, Object> detail_list(Map<String, Object> where, Pageable page);
	
	String insert(MeasureItemDto param); 
}