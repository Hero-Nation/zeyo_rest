package net.heronation.zeyo.rest.service.sub_category_measure_map;
 
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMapRepository;



@Slf4j
@Service
@Transactional
public class SubCategoryMeasureMapServiceImpl implements SubCategoryMeasureMapService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private SubCategoryMeasureMapRepository sub_category_measure_mapRepository;


}