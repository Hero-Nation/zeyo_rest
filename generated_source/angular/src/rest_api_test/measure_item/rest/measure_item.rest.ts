import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { MeasureItem } from '../model/measure_item';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class MeasureItemRest extends CommonHttp {

	private target_url = '/api/measure_items';

	page_all_measure_item() {
		return this.httpClient.get<MeasureItem>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_measure_item( measure_item: MeasureItem ): Observable<MeasureItem> {

		return this.httpClient.post<MeasureItem>( this.target_url, measure_item,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_measure_item_by_id(measure_item: MeasureItem) {
		return this.httpClient.get<MeasureItem>( `${this.target_url}/${measure_item.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_measure_item(measure_item:MeasureItem){ 

		return this.httpClient.patch<MeasureItem>( `${this.target_url}/${measure_item.id}`, measure_item ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_measure_item(measure_item:MeasureItem){
    	return this.httpClient.put<MeasureItem>( `${this.target_url}/${measure_item.id}`, measure_item,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_measure_item(measure_item:MeasureItem){ 

		return this.httpClient.delete<MeasureItem>( `${this.target_url}/${measure_item.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}