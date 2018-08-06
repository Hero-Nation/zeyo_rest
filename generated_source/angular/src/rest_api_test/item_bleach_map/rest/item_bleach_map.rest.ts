import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { ItemBleachMap } from '../model/item_bleach_map';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class ItemBleachMapRest extends CommonHttp {

	private target_url = '/api/item_bleach_maps';

	page_all_item_bleach_map() {
		return this.httpClient.get<ItemBleachMap>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_item_bleach_map( item_bleach_map: ItemBleachMap ): Observable<ItemBleachMap> {

		return this.httpClient.post<ItemBleachMap>( this.target_url, item_bleach_map,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_item_bleach_map_by_id(item_bleach_map: ItemBleachMap) {
		return this.httpClient.get<ItemBleachMap>( `${this.target_url}/${item_bleach_map.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_item_bleach_map(item_bleach_map:ItemBleachMap){ 

		return this.httpClient.patch<ItemBleachMap>( `${this.target_url}/${item_bleach_map.id}`, item_bleach_map ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_item_bleach_map(item_bleach_map:ItemBleachMap){
    	return this.httpClient.put<ItemBleachMap>( `${this.target_url}/${item_bleach_map.id}`, item_bleach_map,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_item_bleach_map(item_bleach_map:ItemBleachMap){ 

		return this.httpClient.delete<ItemBleachMap>( `${this.target_url}/${item_bleach_map.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}