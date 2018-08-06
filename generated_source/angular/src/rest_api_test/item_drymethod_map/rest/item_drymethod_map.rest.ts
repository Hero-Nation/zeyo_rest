import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { ItemDrymethodMap } from '../model/item_drymethod_map';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class ItemDrymethodMapRest extends CommonHttp {

	private target_url = '/api/item_drymethod_maps';

	page_all_item_drymethod_map() {
		return this.httpClient.get<ItemDrymethodMap>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_item_drymethod_map( item_drymethod_map: ItemDrymethodMap ): Observable<ItemDrymethodMap> {

		return this.httpClient.post<ItemDrymethodMap>( this.target_url, item_drymethod_map,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_item_drymethod_map_by_id(item_drymethod_map: ItemDrymethodMap) {
		return this.httpClient.get<ItemDrymethodMap>( `${this.target_url}/${item_drymethod_map.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_item_drymethod_map(item_drymethod_map:ItemDrymethodMap){ 

		return this.httpClient.patch<ItemDrymethodMap>( `${this.target_url}/${item_drymethod_map.id}`, item_drymethod_map ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_item_drymethod_map(item_drymethod_map:ItemDrymethodMap){
    	return this.httpClient.put<ItemDrymethodMap>( `${this.target_url}/${item_drymethod_map.id}`, item_drymethod_map,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_item_drymethod_map(item_drymethod_map:ItemDrymethodMap){ 

		return this.httpClient.delete<ItemDrymethodMap>( `${this.target_url}/${item_drymethod_map.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}