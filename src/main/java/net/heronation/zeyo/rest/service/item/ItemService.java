package net.heronation.zeyo.rest.service.item;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import net.heronation.zeyo.rest.common.value.ToggleDto;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.ItemBuildDto;
import net.heronation.zeyo.rest.repository.item.ItemDto;
import net.heronation.zeyo.rest.repository.item.ItemModifyDto;

public interface ItemService {
	Map<String, Object> search(Map<String, Object> where, Pageable page);

	Map<String, Object> client_search(Map<String, Object> param, Pageable page);

	String change_connect(ItemDto param,Long seq);

	String delete(List<ToggleDto> param,Long seq);

	Map<String, Object> shopmall_list(Long item_id, Pageable pageable);

	String toggle_size_table( List<ToggleDto> param);

	Item build(ItemBuildDto itemBuildDto, Long member_id);

	Item modify(ItemModifyDto itemBuildDto, Long member_id);

	
	Map<String, Object> getStat();

	String toggle_link(List<ToggleDto> param, Long seq);
	
	String arrayExcel(List<ToggleDto> param, Pageable pageable) throws IOException;
}