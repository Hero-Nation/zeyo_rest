package net.heronation.zeyo.rest.service.size_table;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import net.heronation.zeyo.rest.common.value.ToggleDto;
import net.heronation.zeyo.rest.repository.size_table.SizeTableDto;

public interface SizeTableService {
	Map<String, Object> search(Map<String, Object> where, Pageable page);

	Map<String, Object> client_search(Map<String, Object> where, Pageable page);

	String delete(List<ToggleDto> param, Long seq);

	String batch_build(List<ToggleDto> param, Long seq);

	Map<String, Object> preview(Long seq);

	String modify(SizeTableDto param);
	
	String create(SizeTableDto param);
	
	

}