import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Warranty } from '../model/warranty';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class WarrantyRest extends CommonHttp {

	private target_url = '/api/warrantys';

	page_all_warranty() {
		return this.httpClient.get<Warranty>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_warranty( warranty: Warranty ): Observable<Warranty> {

		return this.httpClient.post<Warranty>( this.target_url, warranty,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_warranty_by_id(warranty: Warranty) {
		return this.httpClient.get<Warranty>( `${this.target_url}/${warranty.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_warranty(warranty:Warranty){ 

		return this.httpClient.patch<Warranty>( `${this.target_url}/${warranty.id}`, warranty ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_warranty(warranty:Warranty){
    	return this.httpClient.put<Warranty>( `${this.target_url}/${warranty.id}`, warranty,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_warranty(warranty:Warranty){ 

		return this.httpClient.delete<Warranty>( `${this.target_url}/${warranty.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}