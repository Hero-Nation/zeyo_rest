package net.heronation.zeyo.rest.dmodel_ratio.service;

import java.util.Map;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.dmodel_measure_map.repository.DmodelMeasureMapRepository;
import net.heronation.zeyo.rest.dmodel_ratio.repository.DmodelRatioRepository;
import net.heronation.zeyo.rest.measure_item.repository.MeasureItemRepository; 

@Slf4j
@Service
@Transactional
public class DmodelRatioServiceImpl implements DmodelRatioService {

	@Autowired
	private RestTemplate restTemplate;
 
	@Autowired
	private DmodelMeasureMapRepository dmmrepo;
	
	@Autowired
	private MeasureItemRepository mirepo;
	
	@Autowired
	private DmodelRatioRepository drrepo;
}