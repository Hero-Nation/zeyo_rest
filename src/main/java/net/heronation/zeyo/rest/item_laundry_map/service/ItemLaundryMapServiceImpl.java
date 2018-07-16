package net.heronation.zeyo.rest.item_laundry_map.service;
 
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.item_laundry_map.repository.ItemLaundryMapRepository;



@Slf4j
@Service
@Transactional
public class ItemLaundryMapServiceImpl implements ItemLaundryMapService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private ItemLaundryMapRepository item_laundry_mapRepository;


}