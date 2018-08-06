import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { CompanyNoHistory } from '../model/company_no_history';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class CompanyNoHistoryRest extends CommonHttp {

	private target_url = '/api/company_no_historys';

	page_all_company_no_history() {
		return this.httpClient.get<CompanyNoHistory>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_company_no_history( company_no_history: CompanyNoHistory ): Observable<CompanyNoHistory> {

		return this.httpClient.post<CompanyNoHistory>( this.target_url, company_no_history,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_company_no_history_by_id(company_no_history: CompanyNoHistory) {
		return this.httpClient.get<CompanyNoHistory>( `${this.target_url}/${company_no_history.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_company_no_history(company_no_history:CompanyNoHistory){ 

		return this.httpClient.patch<CompanyNoHistory>( `${this.target_url}/${company_no_history.id}`, company_no_history ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_company_no_history(company_no_history:CompanyNoHistory){
    	return this.httpClient.put<CompanyNoHistory>( `${this.target_url}/${company_no_history.id}`, company_no_history,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_company_no_history(company_no_history:CompanyNoHistory){ 

		return this.httpClient.delete<CompanyNoHistory>( `${this.target_url}/${company_no_history.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}