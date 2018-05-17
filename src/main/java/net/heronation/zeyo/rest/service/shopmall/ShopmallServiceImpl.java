package net.heronation.zeyo.rest.service.shopmall;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager; 

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.brand_member_map.BrandMemberMap;
import net.heronation.zeyo.rest.repository.brand_member_map.QBrandMemberMap;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberRepository;
import net.heronation.zeyo.rest.repository.member.QMember;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallRepository;
import net.heronation.zeyo.rest.repository.shopmall_member_map.QShopmallMemberMap;
import net.heronation.zeyo.rest.repository.shopmall_member_map.ShopmallMemberMap;
import net.heronation.zeyo.rest.repository.shopmall_member_map.ShopmallMemberMapRepository;



@Slf4j
@Service
@Transactional
public class ShopmallServiceImpl implements ShopmallService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private ShopmallRepository shopmallRepository;
	
	
	@Autowired
	private ShopmallMemberMapRepository shopmallMemberMapRepository;
	
	
	
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	EntityManager entityManager;

	@Override
	public Page<Shopmall> search(Predicate where, Pageable page) {

		JPAQuery<Shopmall> query = new JPAQuery<Shopmall>(entityManager);

		QShopmall target = QShopmall.shopmall;

		QueryResults<Shopmall> R = query.from(target)
				.leftJoin(target.itemShopmallMaps)
				.where(where)
				//.orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize())
				.fetchResults();
 
		return new PageImpl<Shopmall>(R.getResults(), page, R.getTotal());

	}

	@Override
	public Page<Shopmall> client_search(Predicate where, Pageable page) {
		JPAQuery<Shopmall> query = new JPAQuery<Shopmall>(entityManager);

		QShopmall target = QShopmall.shopmall;

		QueryResults<Shopmall> R = query.from(target)
				 
				.where(where)
				//.orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize())
				.fetchResults();
 
		return new PageImpl<Shopmall>(R.getResults(), page, R.getTotal());
	}
	

	@Override
	@Transactional
	public Shopmall insert(String name, Long member_seq) {
		// TODO Auto-generated method stub
		
		Member m = memberRepository.getOne(member_seq);
		
		Shopmall o = new Shopmall();
		o.setMember(m);
		o.setName(name);
		o.setUseYn("Y"); 
		o.setCreateDt(new DateTime());
		return shopmallRepository.save(o);
	}

	@Override
	@Transactional
	public Map<String, Object> update(Long shopmall_id, Long member_seq, String name) {

		Map<String, Object> R = new HashMap<String, Object>();

		Shopmall target = shopmallRepository.findOne(shopmall_id);
		Member user = memberRepository.findOne(member_seq);

		if (target == null || target.getUseYn().equals("N")) {
			// brand is not exist
			R.put("CODE", "A");

		} else if (!target.getMember().equals(user)) {
			// user is not owner
			R.put("CODE", "B");
		} else {

			// 현재 브랜드가 다른 사업자에게 사용중인지를 체크 한다.

			QShopmallMemberMap smm = QShopmallMemberMap.shopmallMemberMap;

			JPAQuery<Item> query = new JPAQuery<Item>(entityManager);

			Long member_count = query.select(smm.member.countDistinct())
					.from(smm)
					.innerJoin(smm.shopmall)
					.on(smm.shopmall.useYn.eq("Y"))
					.where(smm.shopmall.id.eq(shopmall_id).and(smm.useYn.eq("Y"))).fetchCount();

			if (member_count == 1) {

				target.setName(name);
				shopmallRepository.save(target);
				R.put("CODE", "OK");

			} else {
				// Brand count in use is more than 1.
				R.put("CODE", "C");
			}

		}

		return R;
	}
	
	
	@Override
	@Transactional
	public Map<String, Object> delete(Long shopmall_id, Long member_seq) {

		Map<String, Object> R = new HashMap<String, Object>();

		Shopmall target = shopmallRepository.findOne(shopmall_id);
		Member user = memberRepository.findOne(member_seq);

		if (target == null || target.getUseYn().equals("N")) {
			// brand is not exist
			R.put("CODE", "A");

		} else if (!target.getMember().equals(user)) {
			// user is not owner
			R.put("CODE", "B");
		} else {

			// 현재 브랜드가 다른 사업자에게 사용중인지를 체크 한다.


			QShopmallMemberMap smm = QShopmallMemberMap.shopmallMemberMap;

			JPAQuery<Item> query = new JPAQuery<Item>(entityManager);

			Long member_count = query.select(smm.member.countDistinct())
					.from(smm)
					.innerJoin(smm.shopmall)
					.on(smm.shopmall.useYn.eq("Y"))
					.where(smm.shopmall.id.eq(shopmall_id).and(smm.useYn.eq("Y"))).fetchCount();

			if (member_count == 1) {

				shopmallRepository.delete(target);
				R.put("CODE", "OK");

			} else {
				// Brand count in use is more than 1.
				R.put("CODE", "C");
			}

		}

		return R;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> toggle_link(Long shopmall_id, Long member_seq) {
		Map<String, Object> R = new HashMap<String, Object>();

		Shopmall target = shopmallRepository.findOne(shopmall_id);
		Member user = memberRepository.findOne(member_seq);
		
		QMember qm = QMember.member; 
		QShopmall sm = QShopmall.shopmall;
		
		QShopmallMemberMap smm = QShopmallMemberMap.shopmallMemberMap;
		
		ShopmallMemberMap db_smm = shopmallMemberMapRepository.findOne(smm.shopmall.id.eq(shopmall_id).and(smm.member.id.eq(member_seq)));

		if (target == null || target.getUseYn().equals("N")) {
			// brand is not exist
			R.put("CODE", "A");

		} 
//		else if (!target.getMember().equals(user)) {
//			// user is not owner
//			R.put("CODE", "B");
//		} 
		else {
			
			// 토글 데이터가 없다면 새로생성하고 연동으로 만들어서 저장한다.  무조건 존재
//			if(db_qbmm == null) {
//				BrandMemberMap new_db_qbmm = new BrandMemberMap();
//				new_db_qbmm.setBrand(target);
//				new_db_qbmm.setMember(user);
//				new_db_qbmm.setLinkYn("Y");
//				new_db_qbmm.setUseYn("Y");
//				
//				brandMemberMapRepository.save(new_db_qbmm);
//				
//				R.put("CURRENT_LINK_YN", "Y");
//				
//			}else {
				
				String current_link_yn = db_smm.getLinkYn();
				if(current_link_yn.equals("Y")) {
					db_smm.setLinkYn("N");
					R.put("CURRENT_LINK_YN", "N");
				}else {
					db_smm.setLinkYn("Y");
					R.put("CURRENT_LINK_YN", "Y");
				}
				
				
//			}
 
			R.put("CODE", "OK");

		}

		return R;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Map<String, Object>> detail(Long shopmall_id, Long member_seq, Pageable page) {
 
		
		Map<String, Object> R = new HashMap<String, Object>();

		QItemShopmallMap qism = QItemShopmallMap.itemShopmallMap;
		QItem qi = QItem.item;
		QBrand qb = QBrand.brand;
		QShopmall qs = QShopmall.shopmall;
		
		JPAQuery<Item> query = new JPAQuery<Item>(entityManager);

		QueryResults<Tuple> db_result = query.select(qism.item.brand.name,qism.item.countDistinct())
				.from(qism)
				.innerJoin(qism.item).on(qism.item.useYn.eq("Y"))
				.innerJoin(qism.item.brand)
				.innerJoin(qism.shopmall).on(qism.shopmall.useYn.eq("Y"))
				//.innerJoin(qism.item.brand).on(qism.item.brand.useYn.eq("Y"))
				//.innerJoin(qism.shopmall).on(qism.shopmall.useYn.eq("Y"))
				.where(qism.shopmall.id.eq(shopmall_id).and(qism.useYn.eq("Y")).and(qism.item.brand.useYn.eq("Y"))) 
				.groupBy(qism.item.brand) 
				.offset((page.getPageNumber() - 1) * page.getPageSize()).limit(page.getPageSize()).fetchResults(); 

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		
		for(Tuple row : db_result.getResults()) {
			Map<String,Object> item = new HashMap<String,Object>();
			
			item.put("brand", row.get(qism.item.brand.name));
			item.put("item_count", row.get(qism.item.countDistinct())); 
			
			list.add(item);
		}
		
		return new PageImpl<Map<String, Object>>(list, page, db_result.getTotal());
	}
 
}