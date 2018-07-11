package net.heronation.zeyo.rest.sub_category_measure_map.service;
 
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.sub_category_measure_map.repository.SubCategoryMeasureMapRepository;



@Slf4j
@Service
@Transactional
public class SubCategoryMeasureMapServiceImpl implements SubCategoryMeasureMapService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private SubCategoryMeasureMapRepository sub_category_measure_mapRepository;


}