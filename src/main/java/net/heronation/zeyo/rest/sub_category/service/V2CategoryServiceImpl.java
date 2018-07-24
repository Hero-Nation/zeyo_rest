package net.heronation.zeyo.rest.sub_category.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import net.heronation.zeyo.rest.common.dto.IdNameDto;
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;
import net.heronation.zeyo.rest.sub_category.repository.SubCategoryRepository;
import net.heronation.zeyo.rest.sub_category.repository.V2Category;

public class V2CategoryServiceImpl implements V2CategoryService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Value(value = "${v2.category.id}")
	private Long v2_category_id;

	private List<V2Category> list = new ArrayList<V2Category>();
	private Map<Long, V2Category> map = new HashMap<Long, V2Category>();

	public void refresh() {
		List<SubCategory> all = subCategoryRepository.findAll();

		list.clear();
		map.clear();

		for (SubCategory sc : all) {

			// 현재 시스템은 트리방식과 단순방식이 혼합된 상태이다.
			// 트리방식의 카테고리의 category는 모두 V2로 설정되어있다.

			if (sc.getCategory().getId() == v2_category_id) {

				V2Category this_v2 = new V2Category();
				this_v2.setName(sc.getName());
				this_v2.setInfo(sc);

				list.add(this_v2);
				map.put(sc.getId(), this_v2);

			}
		}

		// 부모 값 가지고 오기
		for (V2Category vc : list) {
			vc.setParent(getParent(vc.getInfo().getParentId(), new ArrayList<IdNameDto>()));
		}

	}

	private List<IdNameDto> getParent(long this_id, List<IdNameDto> list) {

		V2Category this_vc = map.get(this_id);
		long parent_id = this_vc.getInfo().getParentId();

		if (parent_id == 0L) {
			return list;
		} else {
			V2Category parent_vc = map.get(parent_id);
			IdNameDto indto = new IdNameDto();
			indto.setId(parent_id);
			if (parent_vc.getInfo().getUseYn().equals("N")) {
				indto.setName("[삭]" + parent_vc.getInfo().getName());
			} else {
				indto.setName(parent_vc.getInfo().getName());
			}

			list.add(indto);
			return getParent(parent_id, list);
		}

	}
}