package net.heronation.zeyo.rest.service.brand;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import net.heronation.zeyo.rest.common.value.IdNameDto;
import net.heronation.zeyo.rest.common.value.NameDto;
import net.heronation.zeyo.rest.common.value.ToggleDto;
import net.heronation.zeyo.rest.repository.brand.Brand;

public interface BrandService {
	Map<String, Object> search(Map<String, Object> where, Pageable page);

	Map<String, Object> client_search(Map<String, Object> where, Pageable page);

	Brand insert(NameDto name, Long member_seq);

	Map<String, Object> findByName(String name);

	Map<String, Object> update_name(IdNameDto param,Long member_seq);

	Map<String, Object> delete( List<ToggleDto> param, Long member_seq);

	Map<String, Object> toggle_link(List<ToggleDto> param,  Long member_seq );

	Map<String, Object> detail(Long brand_id, Long member_seq, Pageable page);

	Map<String, Object> brand_company_use_list(Pageable page);

	Map<String, Object> use_count();

	Map<String, Object> distinct_with_member_id();

	Map<String, Object> getStat();
	
	Map<String, Object> detail_info(Long id); 
}