import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { SubCategoryMeasureMap } from '../model/sub_category_measure_map';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class SubCategoryMeasureMapRest extends CommonHttp {

	private target_url = '/api/sub_category_measure_maps';

	page_all_sub_category_measure_map() {
		return this.httpClient.get<SubCategoryMeasureMap>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_sub_category_measure_map( sub_category_measure_map: SubCategoryMeasureMap ): Observable<SubCategoryMeasureMap> {

		return this.httpClient.post<SubCategoryMeasureMap>( this.target_url, sub_category_measure_map,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_sub_category_measure_map_by_id(sub_category_measure_map: SubCategoryMeasureMap) {
		return this.httpClient.get<SubCategoryMeasureMap>( `${this.target_url}/${sub_category_measure_map.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_sub_category_measure_map(sub_category_measure_map:SubCategoryMeasureMap){ 

		return this.httpClient.patch<SubCategoryMeasureMap>( `${this.target_url}/${sub_category_measure_map.id}`, sub_category_measure_map ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_sub_category_measure_map(sub_category_measure_map:SubCategoryMeasureMap){
    	return this.httpClient.put<SubCategoryMeasureMap>( `${this.target_url}/${sub_category_measure_map.id}`, sub_category_measure_map,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_sub_category_measure_map(sub_category_measure_map:SubCategoryMeasureMap){ 

		return this.httpClient.delete<SubCategoryMeasureMap>( `${this.target_url}/${sub_category_measure_map.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}