package net.heronation.zeyo.rest.item_bleach_map.service;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.item_bleach_map.ItemBleachMapRepository;



@Slf4j
@Service
@Transactional
public class ItemBleachMapServiceImpl implements ItemBleachMapService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private ItemBleachMapRepository item_bleach_mapRepository;


}