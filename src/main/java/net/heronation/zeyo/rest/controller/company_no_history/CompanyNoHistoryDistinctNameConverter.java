package net.heronation.zeyo.rest.controller.company_no_history;

import org.springframework.core.convert.converter.Converter;

import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistoryDistinctNameDto;

public class CompanyNoHistoryDistinctNameConverter implements Converter<CompanyNoHistory, CompanyNoHistoryDistinctNameDto> {

  
    public CompanyNoHistoryDistinctNameConverter ( ) { 
    }

 
	@Override
	public CompanyNoHistoryDistinctNameDto convert(CompanyNoHistory input) {
		// TODO Auto-generated method stub
		
		CompanyNoHistoryDistinctNameDto a = new CompanyNoHistoryDistinctNameDto(input.getName());
		
		
		return a;
	}
}