import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Brand } from '../model/brand';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class BrandRest extends CommonHttp {

	private target_url = '/api/brands';

	page_all_brand() {
		return this.httpClient.get<Brand>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_brand( brand: Brand ): Observable<Brand> {

		return this.httpClient.post<Brand>( this.target_url, brand,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_brand_by_id(brand: Brand) {
		return this.httpClient.get<Brand>( `${this.target_url}/${brand.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_brand(brand:Brand){ 

		return this.httpClient.patch<Brand>( `${this.target_url}/${brand.id}`, brand ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_brand(brand:Brand){
    	return this.httpClient.put<Brand>( `${this.target_url}/${brand.id}`, brand,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_brand(brand:Brand){ 

		return this.httpClient.delete<Brand>( `${this.target_url}/${brand.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}