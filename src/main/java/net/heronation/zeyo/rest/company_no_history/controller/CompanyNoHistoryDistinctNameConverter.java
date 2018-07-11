package net.heronation.zeyo.rest.company_no_history.controller;

import org.springframework.core.convert.converter.Converter;

import net.heronation.zeyo.rest.company_no_history.repository.CompanyNoHistory;
import net.heronation.zeyo.rest.company_no_history.repository.CompanyNoHistoryDistinctNameDto;

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