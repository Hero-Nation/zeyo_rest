package net.heronation.zeyo.rest.service.brand;
 
import java.util.Date;
import java.util.List;

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
public class BrandServiceImpl implements BrandService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	EntityManager entityManager;


	@Override
	@Transactional(readOnly=true)
	public Page<Brand> search(Predicate where, Pageable page) {

		JPAQuery<Brand> query = new JPAQuery<Brand>(entityManager);

		QBrand target = QBrand.brand;

		QueryResults<Brand> R = query.from(target)
				.leftJoin(target.items)
				.where(where)
				//.orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize())
				.fetchResults();
 
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
	@Transactional(readOnly=true)
	public Page<Tuple> client_search(Predicate where, Pageable page) {
		JPAQuery<Item> query = new JPAQuery<Item>(entityManager);

		QItem i = QItem.item;

		QueryResults<Tuple> R = query
				.select(i.brand.name, i.member.countDistinct())
				.from(i)
				.innerJoin(i.brand)
				.innerJoin(i.member)
				.where(where)
				.groupBy(i.member)
				//.orderBy(i.id.desc())
				.offset((page.getPageNumber() - 1) * page.getPageSize()).limit(page.getPageSize()).fetchResults();

		return new PageImpl<Tuple>(R.getResults(), page, R.getTotal());
	}


}