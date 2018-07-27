package net.heronation.zeyo.rest.sub_category.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import net.heronation.zeyo.rest.common.controller.CommonException;
import net.heronation.zeyo.rest.sub_category.controller.V2CategoryDto;
import net.heronation.zeyo.rest.sub_category.repository.V2Category;

public interface V2CategoryService {
	public void refresh();
	public void refresh_all();
	public void delete(List<Long> list);
	public V2Category single_info(Long id);
	public Map<String, Object> list(String name,Long parent_id,Pageable pageable);
	public String insert(V2CategoryDto param)  throws CommonException;
	public String update(V2CategoryDto param)  throws CommonException;
}
