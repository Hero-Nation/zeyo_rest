package net.heronation.zeyo.rest.item.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import net.heronation.zeyo.rest.common.controller.CommonException;
import net.heronation.zeyo.rest.common.dto.LIdDto;
import net.heronation.zeyo.rest.common.dto.ToggleDto;
import net.heronation.zeyo.rest.item.controller.ItemImageUploadDto;
import net.heronation.zeyo.rest.item.controller.ItemSizeMeasureImageUploadDto;
import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.item.repository.ItemBuildDto;
import net.heronation.zeyo.rest.item.repository.ItemDto;
import net.heronation.zeyo.rest.item.repository.ItemModifyDto;

public interface ItemService {
	Map<String, Object> search(Map<String, Object> where, Pageable page);

	Map<String, Object> client_search(Map<String, Object> param, Pageable page);

	String change_connect(ItemDto param,Long seq);

	String delete(List<ToggleDto> param,Long seq);

	Map<String, Object> shopmall_list(Long item_id, Pageable pageable);

	String toggle_size_table( List<ToggleDto> param);

	Item build(ItemBuildDto itemBuildDto, Long member_id);

	Item modify(ItemModifyDto itemBuildDto, Long member_id) throws CommonException;

	
	Map<String, Object> getStat();

	String toggle_link(List<ToggleDto> param, Long seq);
	
	String arrayExcel(List<LIdDto> param, Pageable pageable) throws IOException;
	
	
	
	String update_item_image(ItemImageUploadDto param,String member_id)  throws CommonException; 

	String update_size_measure_image(ItemSizeMeasureImageUploadDto param,String member_id) throws CommonException;
	
}