package net.heronation.zeyo.rest.sub_category_fit_info_map.service;
 
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.sub_category_fit_info_map.repository.SubCategoryFitInfoMapRepository;



@Slf4j
@Service
@Transactional
public class SubCategoryFitInfoMapServiceImpl implements SubCategoryFitInfoMapService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private SubCategoryFitInfoMapRepository sub_category_fit_info_mapRepository;


}