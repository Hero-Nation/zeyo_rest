package net.heronation.zeyo.rest.item_scmm_so_value.service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.item_scmm_so_value.repository.ItemScmmSoValueRepository;

@Slf4j
@Service
@Transactional
public class ItemScmmSoValueServiceImpl implements ItemScmmSoValueService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ItemScmmSoValueRepository item_scmm_so_valueRepository;

	@Autowired
	EntityManager entityManager;

 
}