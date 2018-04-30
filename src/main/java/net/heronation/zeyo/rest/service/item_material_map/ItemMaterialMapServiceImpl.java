package net.heronation.zeyo.rest.service.item_material_map;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMapRepository;



@Slf4j
@Service
@Transactional
public class ItemMaterialMapServiceImpl implements ItemMaterialMapService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private ItemMaterialMapRepository item_material_mapRepository;


}