package net.heronation.zeyo.rest.item_shopmall_map.service;
 
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.item_shopmall_map.repository.ItemShopmallMapRepository;



@Slf4j
@Service
@Transactional
public class ItemShopmallMapServiceImpl implements ItemShopmallMapService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private ItemShopmallMapRepository item_shopmall_mapRepository;


}