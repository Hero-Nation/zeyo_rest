package net.heronation.zeyo.rest.service.item;

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
	Page<Item> search(Predicate where, Pageable page);
	String change_connect(String target);
	String delete(String target);
	
	Page<ItemShopmallMap> shopmall_list(Long item_id,Pageable pageable);
	
	String toggle_size_table(Long item_id);
	
	
	Item build(ItemBuildDto itemBuildDto);
	
}