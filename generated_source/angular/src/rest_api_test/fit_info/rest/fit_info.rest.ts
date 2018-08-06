import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { FitInfo } from '../model/fit_info';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class FitInfoRest extends CommonHttp {

	private target_url = '/api/fit_infos';

	page_all_fit_info() {
		return this.httpClient.get<FitInfo>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_fit_info( fit_info: FitInfo ): Observable<FitInfo> {

		return this.httpClient.post<FitInfo>( this.target_url, fit_info,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_fit_info_by_id(fit_info: FitInfo) {
		return this.httpClient.get<FitInfo>( `${this.target_url}/${fit_info.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_fit_info(fit_info:FitInfo){ 

		return this.httpClient.patch<FitInfo>( `${this.target_url}/${fit_info.id}`, fit_info ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_fit_info(fit_info:FitInfo){
    	return this.httpClient.put<FitInfo>( `${this.target_url}/${fit_info.id}`, fit_info,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_fit_info(fit_info:FitInfo){ 

		return this.httpClient.delete<FitInfo>( `${this.target_url}/${fit_info.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}