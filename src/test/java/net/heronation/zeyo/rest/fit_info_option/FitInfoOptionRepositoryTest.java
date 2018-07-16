package net.heronation.zeyo.rest.fit_info_option;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.heronation.zeyo.rest.fit_info_option.repository.FitInfoOption;
import net.heronation.zeyo.rest.fit_info_option.repository.FitInfoOptionRepository;
import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.item.repository.ItemRepository;
import net.heronation.zeyo.rest.item_fit_info_option_map.repository.ItemFitInfoOptionMap;
import net.heronation.zeyo.rest.item_fit_info_option_map.repository.ItemFitInfoOptionMapRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FitInfoOptionRepositoryTest {

	@Autowired
	FitInfoOptionRepository repository;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ItemFitInfoOptionMapRepository mapRepository;

	@Test
	public void initializesRepositoryWithSampleData() {

		
		

	}

}