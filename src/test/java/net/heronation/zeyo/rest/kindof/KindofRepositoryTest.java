package net.heronation.zeyo.rest.kindof;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.kindof.Kindof;
import net.heronation.zeyo.rest.repository.kindof.KindofRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KindofRepositoryTest {

	@Autowired
	KindofRepository repository;

	@Autowired
	EntityManager entityManager;

	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {

		for (int a = 0; a < 100; a++) {
			Kindof item = new Kindof();
			item.setKtype("ktype_" + a);

			item.setKvalue("kvalue_" + a);

			item.setUseYn("useYn_" + a);
			repository.save(item);
		}

	}
 
}