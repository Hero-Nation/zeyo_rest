import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Category } from '../model/category';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class CategoryRest extends CommonHttp {

	private target_url = '/api/categorys';

	page_all_category() {
		return this.httpClient.get<Category>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_category( category: Category ): Observable<Category> {

		return this.httpClient.post<Category>( this.target_url, category,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_category_by_id(category: Category) {
		return this.httpClient.get<Category>( `${this.target_url}/${category.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_category(category:Category){ 

		return this.httpClient.patch<Category>( `${this.target_url}/${category.id}`, category ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_category(category:Category){
    	return this.httpClient.put<Category>( `${this.target_url}/${category.id}`, category,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_category(category:Category){ 

		return this.httpClient.delete<Category>( `${this.target_url}/${category.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}