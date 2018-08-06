package net.heronation.zeyo.rest.dmodel_measure_map.service;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.dmodel_measure_map.DmodelMeasureMapRepository;



@Slf4j
@Service
@Transactional
public class DmodelMeasureMapServiceImpl implements DmodelMeasureMapService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private DmodelMeasureMapRepository dmodel_measure_mapRepository;


}