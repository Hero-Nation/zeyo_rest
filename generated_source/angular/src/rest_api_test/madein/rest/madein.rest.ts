import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Madein } from '../model/madein';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class MadeinRest extends CommonHttp {

	private target_url = '/api/madeins';

	page_all_madein() {
		return this.httpClient.get<Madein>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_madein( madein: Madein ): Observable<Madein> {

		return this.httpClient.post<Madein>( this.target_url, madein,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_madein_by_id(madein: Madein) {
		return this.httpClient.get<Madein>( `${this.target_url}/${madein.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_madein(madein:Madein){ 

		return this.httpClient.patch<Madein>( `${this.target_url}/${madein.id}`, madein ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_madein(madein:Madein){
    	return this.httpClient.put<Madein>( `${this.target_url}/${madein.id}`, madein,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_madein(madein:Madein){ 

		return this.httpClient.delete<Madein>( `${this.target_url}/${madein.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}