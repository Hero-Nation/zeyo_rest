import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { ItemShopmallMap } from '../model/item_shopmall_map';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class ItemShopmallMapRest extends CommonHttp {

	private target_url = '/api/item_shopmall_maps';

	page_all_item_shopmall_map() {
		return this.httpClient.get<ItemShopmallMap>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_item_shopmall_map( item_shopmall_map: ItemShopmallMap ): Observable<ItemShopmallMap> {

		return this.httpClient.post<ItemShopmallMap>( this.target_url, item_shopmall_map,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_item_shopmall_map_by_id(item_shopmall_map: ItemShopmallMap) {
		return this.httpClient.get<ItemShopmallMap>( `${this.target_url}/${item_shopmall_map.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_item_shopmall_map(item_shopmall_map:ItemShopmallMap){ 

		return this.httpClient.patch<ItemShopmallMap>( `${this.target_url}/${item_shopmall_map.id}`, item_shopmall_map ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_item_shopmall_map(item_shopmall_map:ItemShopmallMap){
    	return this.httpClient.put<ItemShopmallMap>( `${this.target_url}/${item_shopmall_map.id}`, item_shopmall_map,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_item_shopmall_map(item_shopmall_map:ItemShopmallMap){ 

		return this.httpClient.delete<ItemShopmallMap>( `${this.target_url}/${item_shopmall_map.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}