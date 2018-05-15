package net.heronation.zeyo.rest.service.shopmall;
 
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
import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberRepository;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallRepository;



@Slf4j
@Service
@Transactional
public class ShopmallServiceImpl implements ShopmallService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private ShopmallRepository shopmallRepository;
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
		o.setSizeCpYn("N");
		o.setCreateDt(new DateTime());
		return shopmallRepository.save(o);
	}
 
}