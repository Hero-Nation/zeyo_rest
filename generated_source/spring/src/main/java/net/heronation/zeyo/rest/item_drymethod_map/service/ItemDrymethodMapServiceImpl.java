package net.heronation.zeyo.rest.item_drymethod_map.service;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.item_drymethod_map.ItemDrymethodMapRepository;



@Slf4j
@Service
@Transactional
public class ItemDrymethodMapServiceImpl implements ItemDrymethodMapService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private ItemDrymethodMapRepository item_drymethod_mapRepository;


}