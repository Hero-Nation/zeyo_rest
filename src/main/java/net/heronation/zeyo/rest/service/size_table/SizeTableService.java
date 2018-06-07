package net.heronation.zeyo.rest.service.size_table;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.ItemDto;
import net.heronation.zeyo.rest.repository.size_table.SizeTable;
import net.heronation.zeyo.rest.repository.size_table.SizeTableDto;

public interface SizeTableService {
	Map<String,Object> search(Map<String,Object> where, Pageable page);
	
	Map<String,Object> client_search(Map<String,Object> where, Pageable page);
	
	String delete(SizeTableDto param,Long seq);
	
	String batch_build(SizeTableDto param,Long seq);
	
	Map<String,Object> preview(Long seq);
	
	
	String modify(SizeTableDto param);
	
	
	
	
	
}