package net.heronation.zeyo.rest.service.cloth_color;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColor;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColorRepository;
import net.heronation.zeyo.rest.repository.cloth_color.QClothColor;
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
	public Page<ClothColor> search(Predicate where, Pageable page) {

		JPAQuery<ClothColor> query = new JPAQuery<ClothColor>(entityManager);

		QClothColor target = QClothColor.clothColor;

		QueryResults<ClothColor> R = query
				.from(target)
				.leftJoin(target.kindof)
				.where(where)
				.offset((page.getPageNumber() - 1) * page.getPageSize())
				.limit(page.getPageSize())
				.fetchResults();

		return new PageImpl<ClothColor>(R.getResults(), page, R.getTotal());

	}
}