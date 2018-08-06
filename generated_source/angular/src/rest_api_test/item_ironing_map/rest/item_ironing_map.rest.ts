import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { ItemIroningMap } from '../model/item_ironing_map';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class ItemIroningMapRest extends CommonHttp {

	private target_url = '/api/item_ironing_maps';

	page_all_item_ironing_map() {
		return this.httpClient.get<ItemIroningMap>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_item_ironing_map( item_ironing_map: ItemIroningMap ): Observable<ItemIroningMap> {

		return this.httpClient.post<ItemIroningMap>( this.target_url, item_ironing_map,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_item_ironing_map_by_id(item_ironing_map: ItemIroningMap) {
		return this.httpClient.get<ItemIroningMap>( `${this.target_url}/${item_ironing_map.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_item_ironing_map(item_ironing_map:ItemIroningMap){ 

		return this.httpClient.patch<ItemIroningMap>( `${this.target_url}/${item_ironing_map.id}`, item_ironing_map ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_item_ironing_map(item_ironing_map:ItemIroningMap){
    	return this.httpClient.put<ItemIroningMap>( `${this.target_url}/${item_ironing_map.id}`, item_ironing_map,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_item_ironing_map(item_ironing_map:ItemIroningMap){ 

		return this.httpClient.delete<ItemIroningMap>( `${this.target_url}/${item_ironing_map.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}