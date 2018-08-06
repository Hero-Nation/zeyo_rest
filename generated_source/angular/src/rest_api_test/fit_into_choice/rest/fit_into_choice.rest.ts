import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { FitIntoChoice } from '../model/fit_into_choice';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class FitIntoChoiceRest extends CommonHttp {

	private target_url = '/api/fit_into_choices';

	page_all_fit_into_choice() {
		return this.httpClient.get<FitIntoChoice>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_fit_into_choice( fit_into_choice: FitIntoChoice ): Observable<FitIntoChoice> {

		return this.httpClient.post<FitIntoChoice>( this.target_url, fit_into_choice,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_fit_into_choice_by_id(fit_into_choice: FitIntoChoice) {
		return this.httpClient.get<FitIntoChoice>( `${this.target_url}/${fit_into_choice.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_fit_into_choice(fit_into_choice:FitIntoChoice){ 

		return this.httpClient.patch<FitIntoChoice>( `${this.target_url}/${fit_into_choice.id}`, fit_into_choice ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_fit_into_choice(fit_into_choice:FitIntoChoice){
    	return this.httpClient.put<FitIntoChoice>( `${this.target_url}/${fit_into_choice.id}`, fit_into_choice,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_fit_into_choice(fit_into_choice:FitIntoChoice){ 

		return this.httpClient.delete<FitIntoChoice>( `${this.target_url}/${fit_into_choice.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}