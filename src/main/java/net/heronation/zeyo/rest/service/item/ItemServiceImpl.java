package net.heronation.zeyo.rest.service.item;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.ItemBuildDto;
import net.heronation.zeyo.rest.repository.item.ItemRepository;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMap;
import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMapRepository;
import net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.size_table.QSizeTable;
import net.heronation.zeyo.rest.repository.size_table.SizeTable;
import net.heronation.zeyo.rest.repository.size_table.SizeTableRepository;

@Slf4j
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemShopmallMapRepository itemShopmallMapRepository;
	
	@Autowired
	private SizeTableRepository sizeTableRepository;
	
	
	@Autowired
	EntityManager entityManager;

	@Override
	public Page<Item> search(Predicate where, Pageable page) {

		JPAQuery<Item> query = new JPAQuery<Item>(entityManager);

		QItem target = QItem.item;

		QueryResults<Item> R = query.from(target)

				.where(where)
				// .orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1) * page.getPageSize()).limit(page.getPageSize()).fetchResults();

		return new PageImpl<Item>(R.getResults(), page, R.getTotal());

	}

	@Override
	@Transactional
	public String change_connect(String target) {
		// TODO Auto-generated method stub

		String[] targets = target.split(",");

		for (int a = 0; a < targets.length; a++) {

			Item i = itemRepository.findOne(Long.valueOf(targets[a]));
			String linkYN = i.getLinkYn();
			if (linkYN == null) {

			} else {
				if (linkYN.equals("Y")) {
					i.setLinkYn("N");
				} else {
					i.setLinkYn("Y");
				}
			}
		}

		return "Y";
	}

	@Override
	@Transactional
	public String delete(String target) {
		String[] targets = target.split(",");

		for (int a = 0; a < targets.length; a++) {

			itemRepository.delete(Long.valueOf(targets[a]));

		}

		return "Y";
	}

	@Override
	@Transactional(readOnly=true)
	public Page<ItemShopmallMap> shopmall_list(Long item_id,Pageable pageable) {
		 
//		PathBuilder<Madein> madeinPath = new PathBuilder<Madein>(Madein.class, "madein");
//		
//		for (Order order : page.getSort()) {
//		    PathBuilder<Object> path = madeinPath.get(order.getProperty());
//		    query.orderBy(new OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()), path));
//		}

		
		QItemShopmallMap target = QItemShopmallMap.itemShopmallMap;
		QItem qi = QItem.item;
		QShopmall qs = QShopmall.shopmall;
		
		JPAQuery<ItemShopmallMap> query = new JPAQuery<ItemShopmallMap>(entityManager);
 
		QueryResults<ItemShopmallMap> R = query.from(target)
				.where(target.item.id.eq(item_id).and(target.useYn.eq("Y")))
				.innerJoin(target.item).on(target.item.useYn.eq("Y"))
				.innerJoin(target.shopmall).on(target.shopmall.useYn.eq("Y"))
				.offset((pageable.getPageNumber() - 1) * pageable.getPageSize()).limit(pageable.getPageSize()).fetchResults();

		return new PageImpl<ItemShopmallMap>(R.getResults(), pageable, R.getTotal()); 
	}

	@Override
	@Transactional
	public String toggle_size_table(Long item_id) {
		Item r = itemRepository.findOne(item_id);
		
		if(r == null) {
			return null;
		}else {
			 String table = r.getSizeTableYn();
			 QSizeTable st = QSizeTable.sizeTable;
			 
			 SizeTable db_st= sizeTableRepository.findOne(st.item.id.eq(item_id));
			 
			 if(table.equals("N")) {
				 
				 if(db_st == null) {
					 
					 SizeTable new_st = new SizeTable();
					 // 정보입력 
					 
					 
					 
					 new_st.setItem(r);
					 sizeTableRepository.save(new_st);
					 
				 }else{
					 
				 }
				 
				 r.setSizeTableYn("Y");
			 }else {
				 r.setSizeTableYn("N");
			 }
		}
		
		
		return "S";
	}

	@Override
	public Item build(ItemBuildDto itemBuildDto) {
		// TODO Auto-generated method stub
		return null;
	}

}