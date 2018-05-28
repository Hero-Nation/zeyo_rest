package net.heronation.zeyo.rest.service.size_option;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.category.QCategory;
import net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap;
import net.heronation.zeyo.rest.repository.kindof.Kindof;
import net.heronation.zeyo.rest.repository.kindof.QKindof;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.QMember;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.size_option.QSizeOption;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;
import net.heronation.zeyo.rest.repository.size_option.SizeOptionRepository;
import net.heronation.zeyo.rest.repository.sub_category.QSubCategory;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;

@Slf4j
@Service
@Transactional
public class SizeOptionServiceImpl implements SizeOptionService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private SizeOptionRepository size_optionRepository;
	@Autowired
	EntityManager entityManager;
	@Override
	public Page<Map<String, Object>> search(Predicate where, Pageable page) {
	 
		
		QSizeOption so = QSizeOption.sizeOption; 
		QCategory c = QCategory.category; 
		QSubCategory sc = QSubCategory.subCategory;
		QKindof ko =QKindof.kindof;
		
		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);
		 
	 
		QueryResults<Tuple> R = query.select( 
				so.id,c.name,sc.name,ko.kvalue,so.name
				//,so.itemSizeOptionMaps.size()
				,so.createDt
		).from(so)
				.innerJoin(so.category,c)
				.innerJoin(so.subCategory,sc)
				.innerJoin(so.kindof,ko) 
				//.innerJoin(so.itemSizeOptionMaps) 
				 
				.where(c.useYn.eq("Y")
						.and(sc.useYn.eq("Y"))
						.and(ko.useYn.eq("Y"))
						.and(where))
						 
				.fetchResults();

		List<Tuple> search_list = R.getResults();
		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Tuple row : search_list) {
			Map<String, Object> search_R = new HashMap<String, Object>();
 
			search_R.put("size_option_id", row.get(so.id));
			search_R.put("category_name", row.get(c.name));  
			search_R.put("sub_category_name", row.get(sc.name)); 
			search_R.put("kindof_name", row.get(ko.kvalue));
			search_R.put("size_option_name", row.get(so.name));
		//	search_R.put("size_option_item_count", row.get(so.itemSizeOptionMaps.size()));
			search_R.put("size_option_create_dt", row.get(so.createDt)); 
				
 
			return_list.add(search_R);
		}
		return new PageImpl<Map<String, Object>>(return_list, page, R.getTotal());
		
	}
	
	
	@Override
	public Map<String, Object> category_count(Predicate where) {
		
		Map<String, Object> RV = new HashMap<String,Object>();
		
		QSizeOption so = QSizeOption.sizeOption; 
		QCategory c = QCategory.category; 
		QSubCategory sc = QSubCategory.subCategory;
		QKindof ko =QKindof.kindof;
		
		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);
		 
	 
		QueryResults<Tuple> R = query.select( 
				so.id.count(),so.id.countDistinct()
		).from(so)
				 .groupBy(so.subCategory)
				.where(where)  
				.fetchResults();

		RV.put("category_count",R.getResults().size());
		
		return RV;
	}


	@Override
	public Map<String, Object> single(Predicate where) {
		
		Map<String, Object> R= new HashMap<String,Object>();
		
		QSizeOption so = QSizeOption.sizeOption; 
		QCategory c = QCategory.category; 
		QSubCategory sc = QSubCategory.subCategory;
		QKindof ko =QKindof.kindof;
		
		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);
		 
//		private Long id;
//
//		private Category category;
//
//		private SubCategory subCategory;
//
//		private Kindof kindof;
//
//		private String name;
//
//		private DateTime createDt;
//
//		private String useYn;
		
		
		Tuple db_R = query.select( 
				so.name,so.createDt,c.id,sc.id,ko.id
				,so.createDt
		).from(so)
				.innerJoin(so.category,c)
				.innerJoin(so.subCategory,sc)
				.innerJoin(so.kindof,ko) 
				//.innerJoin(so.itemSizeOptionMaps) 
				 
				.where(c.useYn.eq("Y")
						.and(sc.useYn.eq("Y"))
						.and(ko.useYn.eq("Y"))
						.and(where))
						 
				.fetchOne();
  
 
			R.put("size_option_name", db_R.get(so.name));
			R.put("size_option_create_dt", db_R.get(so.createDt));   
			R.put("category_id", db_R.get(c.id)); 
			R.put("sub_category_id", db_R.get(sc.id));
			R.put("kindof_id", db_R.get(ko.id)); 
			
				
  
	 
		return R;
	}

}