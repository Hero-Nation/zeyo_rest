package net.heronation.zeyo.rest.service.item_ironing_map;
 
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.item_ironing_map.ItemIroningMapRepository;



@Slf4j
@Service
@Transactional
public class ItemIroningMapServiceImpl implements ItemIroningMapService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private ItemIroningMapRepository item_ironing_mapRepository;


}