import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { FitInfoOption } from '../model/fit_info_option';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class FitInfoOptionRest extends CommonHttp {

	private target_url = '/api/fit_info_options';

	page_all_fit_info_option() {
		return this.httpClient.get<FitInfoOption>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_fit_info_option( fit_info_option: FitInfoOption ): Observable<FitInfoOption> {

		return this.httpClient.post<FitInfoOption>( this.target_url, fit_info_option,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_fit_info_option_by_id(fit_info_option: FitInfoOption) {
		return this.httpClient.get<FitInfoOption>( `${this.target_url}/${fit_info_option.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_fit_info_option(fit_info_option:FitInfoOption){ 

		return this.httpClient.patch<FitInfoOption>( `${this.target_url}/${fit_info_option.id}`, fit_info_option ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_fit_info_option(fit_info_option:FitInfoOption){
    	return this.httpClient.put<FitInfoOption>( `${this.target_url}/${fit_info_option.id}`, fit_info_option,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_fit_info_option(fit_info_option:FitInfoOption){ 

		return this.httpClient.delete<FitInfoOption>( `${this.target_url}/${fit_info_option.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}