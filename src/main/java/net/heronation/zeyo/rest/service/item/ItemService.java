package net.heronation.zeyo.rest.service.item;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.ItemBuildDto;
import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMap;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;

public interface ItemService {
	Map<String,Object> search(Map<String,Object> where, Pageable page);
	Page<Map<String, Object>> client_search(Map<String, Object> param, Pageable page);
	
	
	String change_connect(String target);
	String delete(String target);
	
	Page<Map<String,Object>> shopmall_list(Long item_id,Pageable pageable);
	
	String toggle_size_table(Long item_id);
	
	
	Item build(ItemBuildDto itemBuildDto,Long member_id);
	
}