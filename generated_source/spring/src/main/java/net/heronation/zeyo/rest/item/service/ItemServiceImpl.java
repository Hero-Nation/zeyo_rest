package net.heronation.zeyo.rest.item.service;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.item.ItemRepository;



@Slf4j
@Service
@Transactional
public class ItemServiceImpl implements ItemService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private ItemRepository itemRepository;


}