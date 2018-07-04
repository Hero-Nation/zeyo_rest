package net.heronation.zeyo.rest.measure_item;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasureItemRepositoryTest {

	@Autowired
	MeasureItemRepository repository;

	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {

		for (int a = 0; a < 100; a++) {
			MeasureItem item = new MeasureItem();
			item.setName("name_" + a);

			item.setMetaDesc("metaDesc_" + a);

		//	item.setCreateDt(new Date());

			item.setUseYn("useYn_" + a);
			repository.save(item);
		}

	}

}