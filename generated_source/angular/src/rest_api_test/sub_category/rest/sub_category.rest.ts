import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { SubCategory } from '../model/sub_category';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class SubCategoryRest extends CommonHttp {

	private target_url = '/api/sub_categorys';

	page_all_sub_category() {
		return this.httpClient.get<SubCategory>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_sub_category( sub_category: SubCategory ): Observable<SubCategory> {

		return this.httpClient.post<SubCategory>( this.target_url, sub_category,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_sub_category_by_id(sub_category: SubCategory) {
		return this.httpClient.get<SubCategory>( `${this.target_url}/${sub_category.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_sub_category(sub_category:SubCategory){ 

		return this.httpClient.patch<SubCategory>( `${this.target_url}/${sub_category.id}`, sub_category ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_sub_category(sub_category:SubCategory){
    	return this.httpClient.put<SubCategory>( `${this.target_url}/${sub_category.id}`, sub_category,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_sub_category(sub_category:SubCategory){ 

		return this.httpClient.delete<SubCategory>( `${this.target_url}/${sub_category.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}