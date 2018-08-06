import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Shopmall } from '../model/shopmall';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class ShopmallRest extends CommonHttp {

	private target_url = '/api/shopmalls';

	page_all_shopmall() {
		return this.httpClient.get<Shopmall>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_shopmall( shopmall: Shopmall ): Observable<Shopmall> {

		return this.httpClient.post<Shopmall>( this.target_url, shopmall,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_shopmall_by_id(shopmall: Shopmall) {
		return this.httpClient.get<Shopmall>( `${this.target_url}/${shopmall.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_shopmall(shopmall:Shopmall){ 

		return this.httpClient.patch<Shopmall>( `${this.target_url}/${shopmall.id}`, shopmall ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_shopmall(shopmall:Shopmall){
    	return this.httpClient.put<Shopmall>( `${this.target_url}/${shopmall.id}`, shopmall,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_shopmall(shopmall:Shopmall){ 

		return this.httpClient.delete<Shopmall>( `${this.target_url}/${shopmall.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}