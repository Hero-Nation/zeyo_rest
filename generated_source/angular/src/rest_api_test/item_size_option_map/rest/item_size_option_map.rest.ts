import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { ItemSizeOptionMap } from '../model/item_size_option_map';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class ItemSizeOptionMapRest extends CommonHttp {

	private target_url = '/api/item_size_option_maps';

	page_all_item_size_option_map() {
		return this.httpClient.get<ItemSizeOptionMap>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_item_size_option_map( item_size_option_map: ItemSizeOptionMap ): Observable<ItemSizeOptionMap> {

		return this.httpClient.post<ItemSizeOptionMap>( this.target_url, item_size_option_map,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_item_size_option_map_by_id(item_size_option_map: ItemSizeOptionMap) {
		return this.httpClient.get<ItemSizeOptionMap>( `${this.target_url}/${item_size_option_map.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_item_size_option_map(item_size_option_map:ItemSizeOptionMap){ 

		return this.httpClient.patch<ItemSizeOptionMap>( `${this.target_url}/${item_size_option_map.id}`, item_size_option_map ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_item_size_option_map(item_size_option_map:ItemSizeOptionMap){
    	return this.httpClient.put<ItemSizeOptionMap>( `${this.target_url}/${item_size_option_map.id}`, item_size_option_map,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_item_size_option_map(item_size_option_map:ItemSizeOptionMap){ 

		return this.httpClient.delete<ItemSizeOptionMap>( `${this.target_url}/${item_size_option_map.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}