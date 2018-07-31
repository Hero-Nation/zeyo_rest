package net.heronation.zeyo.rest.dmodel_ratio;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.heronation.zeyo.rest.dmodel_ratio.repository.DmodelRatio;
import net.heronation.zeyo.rest.dmodel_ratio.repository.DmodelRatioRepository;
 
@RunWith(SpringRunner.class)
@SpringBootTest
public class DmodelRatioRepositoryTest {

	@Autowired
	DmodelRatioRepository repository;

	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {

		for (int a = 0; a < 100; a++) {
			DmodelRatio item = new DmodelRatio();
			item.setDefaultYn("default_" + a);

			item.setMinValue("minValue_" + a);

			item.setMaxValue("maxValue_" + a);

			item.setRatioValue("ratioValue_" + a);

			item.setUseYn("useYn_" + a);
			repository.save(item);
		}

	}

}