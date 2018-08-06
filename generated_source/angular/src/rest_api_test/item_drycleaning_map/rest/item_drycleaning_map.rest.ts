import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { ItemDrycleaningMap } from '../model/item_drycleaning_map';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class ItemDrycleaningMapRest extends CommonHttp {

	private target_url = '/api/item_drycleaning_maps';

	page_all_item_drycleaning_map() {
		return this.httpClient.get<ItemDrycleaningMap>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_item_drycleaning_map( item_drycleaning_map: ItemDrycleaningMap ): Observable<ItemDrycleaningMap> {

		return this.httpClient.post<ItemDrycleaningMap>( this.target_url, item_drycleaning_map,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_item_drycleaning_map_by_id(item_drycleaning_map: ItemDrycleaningMap) {
		return this.httpClient.get<ItemDrycleaningMap>( `${this.target_url}/${item_drycleaning_map.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_item_drycleaning_map(item_drycleaning_map:ItemDrycleaningMap){ 

		return this.httpClient.patch<ItemDrycleaningMap>( `${this.target_url}/${item_drycleaning_map.id}`, item_drycleaning_map ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_item_drycleaning_map(item_drycleaning_map:ItemDrycleaningMap){
    	return this.httpClient.put<ItemDrycleaningMap>( `${this.target_url}/${item_drycleaning_map.id}`, item_drycleaning_map,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_item_drycleaning_map(item_drycleaning_map:ItemDrycleaningMap){ 

		return this.httpClient.delete<ItemDrycleaningMap>( `${this.target_url}/${item_drycleaning_map.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}