package net.heronation.zeyo.rest.size_table.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import net.heronation.zeyo.rest.common.controller.CommonException;
import net.heronation.zeyo.rest.common.dto.ToggleDto;
import net.heronation.zeyo.rest.size_table.repository.SizeTable;
import net.heronation.zeyo.rest.size_table.repository.SizeTableDto;

public interface SizeTableService {
	Map<String, Object> search(Map<String, Object> where, Pageable page);

	Map<String, Object> client_search(Map<String, Object> where, Pageable page);

	Map<String, Object> get_size_count(Map<String, Object> where);
 
	String delete(List<ToggleDto> param, Long seq);

	String batch_build(List<ToggleDto> param, Long seq);

	Map<String, Object> preview(Long seq);

	String modify(SizeTableDto param) throws CommonException;
	
	String create(SizeTableDto param) throws CommonException;
	
	String update_item_image(SizeTableDto param);
	
	SizeTable single_infos(long id);

}