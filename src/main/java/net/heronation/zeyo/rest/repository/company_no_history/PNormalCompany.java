package net.heronation.zeyo.rest.repository.company_no_history;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "normal_company", types = { CompanyNoHistory.class })
interface PNormalCompany {

	String getName();

	String getCompanyNo();

}