package net.heronation.zeyo.rest.service.integrate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallRepository;

@Slf4j
@Service
@Transactional
public class IntegrateServiceImpl implements IntegrateSerivce {

	@Autowired
	ShopmallRepository shopamllRepostory;
	

	@Override
	@Transactional
	public String sync_product(Long shopmall_id) {

		Shopmall this_shop = shopamllRepostory.getOne(shopmall_id);

		if (this_shop.getStoreType().equals("cafe24")) {
		}

		return CommonConstants.OK;
	}


	@Override
	@Transactional(readOnly=true)
	public Shopmall get_shopmall_temp_identity(Long shopmall_id) { 
		log.debug("shopmall_id  "+shopmall_id);
		return shopamllRepostory.findOne(shopmall_id);
	}


	@Override
	@Transactional
	public String update_oauth_code(String auth_code, String temp_identity) {
		// TODO Auto-generated method stub
		
		QShopmall sm = QShopmall.shopmall;
		
		Shopmall this_shopmall = shopamllRepostory.findOne(sm.tempIdentity.eq(temp_identity));
		
		if(this_shopmall == null) {
			return CommonConstants.FAIL;
		}else {
			this_shopmall.setOauthCode(auth_code);
			return CommonConstants.SUCCESS;
		}
	}

}
