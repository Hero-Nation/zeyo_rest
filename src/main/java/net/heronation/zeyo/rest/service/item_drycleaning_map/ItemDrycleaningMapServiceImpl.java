package net.heronation.zeyo.rest.service.item_drycleaning_map;
 
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.item_drycleaning_map.ItemDrycleaningMapRepository;



@Slf4j
@Service
@Transactional
public class ItemDrycleaningMapServiceImpl implements ItemDrycleaningMapService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private ItemDrycleaningMapRepository item_drycleaning_mapRepository;


}