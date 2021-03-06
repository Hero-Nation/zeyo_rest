package net.heronation.zeyo.rest.service.brand;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;

import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.brand.BrandDto;
import net.heronation.zeyo.rest.repository.madein.Madein;

public interface BrandService {
	Map<String, Object> search(Map<String, Object> where, Pageable page);

	Map<String, Object> client_search(Map<String, Object> where, Pageable page);

	Brand insert(BrandDto name, Long member_seq);

	Map<String, Object> findByName(String name);

	Map<String, Object> update_name(BrandDto param,Long member_seq);

	Map<String, Object> delete(BrandDto param, Long member_seq);

	Map<String, Object> toggle_link(BrandDto param,  Long member_seq );

	Map<String, Object> detail(Long brand_id, Long member_seq, Pageable page);

	Map<String, Object> brand_company_use_list(Pageable page);

	Map<String, Object> use_count();

	Map<String, Object> distinct_with_member_id();

	Map<String, Object> getStat();
	
	Map<String, Object> detail_info(Long id); 
}