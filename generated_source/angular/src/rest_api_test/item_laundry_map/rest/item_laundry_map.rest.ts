import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { ItemLaundryMap } from '../model/item_laundry_map';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class ItemLaundryMapRest extends CommonHttp {

	private target_url = '/api/item_laundry_maps';

	page_all_item_laundry_map() {
		return this.httpClient.get<ItemLaundryMap>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_item_laundry_map( item_laundry_map: ItemLaundryMap ): Observable<ItemLaundryMap> {

		return this.httpClient.post<ItemLaundryMap>( this.target_url, item_laundry_map,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_item_laundry_map_by_id(item_laundry_map: ItemLaundryMap) {
		return this.httpClient.get<ItemLaundryMap>( `${this.target_url}/${item_laundry_map.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_item_laundry_map(item_laundry_map:ItemLaundryMap){ 

		return this.httpClient.patch<ItemLaundryMap>( `${this.target_url}/${item_laundry_map.id}`, item_laundry_map ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_item_laundry_map(item_laundry_map:ItemLaundryMap){
    	return this.httpClient.put<ItemLaundryMap>( `${this.target_url}/${item_laundry_map.id}`, item_laundry_map,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_item_laundry_map(item_laundry_map:ItemLaundryMap){ 

		return this.httpClient.delete<ItemLaundryMap>( `${this.target_url}/${item_laundry_map.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}