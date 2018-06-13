package net.heronation.zeyo.rest.service.item_size_option_map;
 
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMapRepository;



@Slf4j
@Service
@Transactional
public class ItemSizeOptionMapServiceImpl implements ItemSizeOptionMapService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private ItemSizeOptionMapRepository item_size_option_mapRepository;


}