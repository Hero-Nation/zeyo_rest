package net.heronation.zeyo.rest.item_cloth_color_map.service;
 
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.item_cloth_color_map.repository.ItemClothColorMapRepository;



@Slf4j
@Service
@Transactional
public class ItemClothColorMapServiceImpl implements ItemClothColorMapService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private ItemClothColorMapRepository item_cloth_color_mapRepository;


}