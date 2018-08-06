import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Kindof } from '../model/kindof';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class KindofRest extends CommonHttp {

	private target_url = '/api/kindofs';

	page_all_kindof() {
		return this.httpClient.get<Kindof>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_kindof( kindof: Kindof ): Observable<Kindof> {

		return this.httpClient.post<Kindof>( this.target_url, kindof,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_kindof_by_id(kindof: Kindof) {
		return this.httpClient.get<Kindof>( `${this.target_url}/${kindof.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_kindof(kindof:Kindof){ 

		return this.httpClient.patch<Kindof>( `${this.target_url}/${kindof.id}`, kindof ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_kindof(kindof:Kindof){
    	return this.httpClient.put<Kindof>( `${this.target_url}/${kindof.id}`, kindof,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_kindof(kindof:Kindof){ 

		return this.httpClient.delete<Kindof>( `${this.target_url}/${kindof.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}