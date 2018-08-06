import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { SubCategoryFitInfoMap } from '../model/sub_category_fit_info_map';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class SubCategoryFitInfoMapRest extends CommonHttp {

	private target_url = '/api/sub_category_fit_info_maps';

	page_all_sub_category_fit_info_map() {
		return this.httpClient.get<SubCategoryFitInfoMap>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_sub_category_fit_info_map( sub_category_fit_info_map: SubCategoryFitInfoMap ): Observable<SubCategoryFitInfoMap> {

		return this.httpClient.post<SubCategoryFitInfoMap>( this.target_url, sub_category_fit_info_map,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_sub_category_fit_info_map_by_id(sub_category_fit_info_map: SubCategoryFitInfoMap) {
		return this.httpClient.get<SubCategoryFitInfoMap>( `${this.target_url}/${sub_category_fit_info_map.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_sub_category_fit_info_map(sub_category_fit_info_map:SubCategoryFitInfoMap){ 

		return this.httpClient.patch<SubCategoryFitInfoMap>( `${this.target_url}/${sub_category_fit_info_map.id}`, sub_category_fit_info_map ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_sub_category_fit_info_map(sub_category_fit_info_map:SubCategoryFitInfoMap){
    	return this.httpClient.put<SubCategoryFitInfoMap>( `${this.target_url}/${sub_category_fit_info_map.id}`, sub_category_fit_info_map,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_sub_category_fit_info_map(sub_category_fit_info_map:SubCategoryFitInfoMap){ 

		return this.httpClient.delete<SubCategoryFitInfoMap>( `${this.target_url}/${sub_category_fit_info_map.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}