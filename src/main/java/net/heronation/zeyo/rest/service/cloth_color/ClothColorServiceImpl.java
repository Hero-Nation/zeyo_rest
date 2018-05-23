package net.heronation.zeyo.rest.service.cloth_color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColor;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColorRepository;
import net.heronation.zeyo.rest.repository.cloth_color.QClothColor;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.item_cloth_color_map.QItemClothColorMap;
import net.heronation.zeyo.rest.repository.kindof.QKindof;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.madein.QMadein;
import net.heronation.zeyo.rest.repository.size_option.QSizeOption;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;

@Slf4j
@Service
@Transactional
public class ClothColorServiceImpl implements ClothColorService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ClothColorRepository cloth_colorRepository;
	@Autowired
	EntityManager entityManager;

	@Override
	public Page<Map<String,Object>> search(Predicate where, Pageable page) {

		JPAQuery<Madein> query = new JPAQuery<Madein>(entityManager);
		
		
//		PathBuilder<ClothColor> madeinPath = new PathBuilder<ClothColor>(ClothColor.class, "clothcolor");
//		
//		for (Order order : page.getSort()) {
//		    PathBuilder<Object> path = madeinPath.get(order.getProperty());
//		    query.orderBy(new OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()), path));
//		}

		QItem i = QItem.item;
		QClothColor cc = QClothColor.clothColor;
		QItemClothColorMap iccm = QItemClothColorMap.itemClothColorMap;
		QKindof ko = QKindof.kindof;
		
		QueryResults<Tuple> R = query.select(iccm.clothColor.id,iccm.clothColor.name,iccm.clothColor.kindof.kvalue,iccm.clothColor.createDt,i.id.countDistinct())
				.from(iccm) 
				.where(where) 
				.leftJoin(iccm.item,i).on(i.useYn.eq("Y"))
				.leftJoin(iccm.clothColor,cc).on(cc.useYn.eq("Y"))
				.leftJoin(cc.kindof,ko).on(ko.useYn.eq("Y"))
				.groupBy(iccm.clothColor)
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize())
				.fetchResults();
 
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<Tuple> db_list = R.getResults();
		
		for(Tuple row : db_list) {
			Map<String,Object>  r_row = new HashMap<String,Object>();
			 
			r_row.put("id", row.get(iccm.clothColor.id));
			r_row.put("name", row.get(iccm.clothColor.name));
			r_row.put("kindof", row.get(iccm.clothColor.kindof.kvalue));
			r_row.put("createDt", row.get(iccm.clothColor.createDt));
			r_row.put("itemCount", row.get(i.id.countDistinct()));
			
			
			list.add(r_row);
			
		}
		
		return new PageImpl<Map<String,Object>>(list, page, R.getTotal());

	}
}