package net.heronation.zeyo.rest.service.brand;

import java.util.Date;
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

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.brand.BrandRepository;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.madein.QMadein;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberRepository;

@Slf4j
@Service
@Transactional
public class BrandServiceImpl implements BrandService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public Page<Brand> search(Predicate where, Pageable page) {

		JPAQuery<Brand> query = new JPAQuery<Brand>(entityManager);

		QBrand target = QBrand.brand;

		QueryResults<Brand> R = query.from(target).leftJoin(target.items).where(where)
				// .orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1) * page.getPageSize()).limit(page.getPageSize()).fetchResults();

		return new PageImpl<Brand>(R.getResults(), page, R.getTotal());

	}

	@Override
	@Transactional
	public Brand insert(String name, Long member_seq) {
		// TODO Auto-generated method stub

		Member m = memberRepository.getOne(member_seq);

		Brand o = new Brand();
		o.setMember(m);
		o.setName(name);
		o.setUseYn("Y");
		o.setCreateDt(new DateTime());
		return brandRepository.save(o);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Tuple> client_search(Predicate where, Pageable page) {
		JPAQuery<Item> query = new JPAQuery<Item>(entityManager);

		QItem i = QItem.item;

		QueryResults<Tuple> R = query.select(i.brand.name, i.member.countDistinct()).from(i).innerJoin(i.brand)
				.innerJoin(i.member).where(where).groupBy(i.member)
				// .orderBy(i.id.desc())
				.offset((page.getPageNumber() - 1) * page.getPageSize()).limit(page.getPageSize()).fetchResults();

		return new PageImpl<Tuple>(R.getResults(), page, R.getTotal());
	}

 

	@Override
	public Brand delete(String name, Long member_seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Map<String,Object> update(Long brand_id, Long member_seq,String name) {
		
		Map<String,Object> R = new HashMap<String,Object>();
		
		
		Brand target = brandRepository.findOne(brand_id);
		Member user = memberRepository.findOne(member_seq);
		
		if(target == null || target.getUseYn().equals("N")) {
			// brand is not exist
			R.put("CODE", "A");
			
		}else if(!target.getMember().equals(user)){
			// user is not owner
			R.put("CODE", "B");
		}else {
			
			// 현재 브랜드가 다른 사업자에게 사용중인지를 체크 한다.
			
			QItem i = QItem.item; 
			
			
			JPAQuery<Item> query = new JPAQuery<Item>(entityManager);

			Long member_count = query.select(i.member.countDistinct())
					.from(i)
					.innerJoin(i.brand).on(i.brand.useYn.eq("Y"))
					.where(i.brand.id.eq(brand_id).and(i.useYn.eq("Y"))).fetchCount();
					
			if(member_count == 1) {
				
				target.setName(name);
				brandRepository.save(target);
				R.put("CODE", "OK");
				
			}else {
				// Brand count in use is more than 1.
				R.put("CODE", "C");
			}
			
			
			
		}
		
		
		return R;
	}

 
}