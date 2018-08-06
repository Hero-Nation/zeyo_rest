import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { ItemClothColorMap } from '../model/item_cloth_color_map';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class ItemClothColorMapRest extends CommonHttp {

	private target_url = '/api/item_cloth_color_maps';

	page_all_item_cloth_color_map() {
		return this.httpClient.get<ItemClothColorMap>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_item_cloth_color_map( item_cloth_color_map: ItemClothColorMap ): Observable<ItemClothColorMap> {

		return this.httpClient.post<ItemClothColorMap>( this.target_url, item_cloth_color_map,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_item_cloth_color_map_by_id(item_cloth_color_map: ItemClothColorMap) {
		return this.httpClient.get<ItemClothColorMap>( `${this.target_url}/${item_cloth_color_map.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_item_cloth_color_map(item_cloth_color_map:ItemClothColorMap){ 

		return this.httpClient.patch<ItemClothColorMap>( `${this.target_url}/${item_cloth_color_map.id}`, item_cloth_color_map ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_item_cloth_color_map(item_cloth_color_map:ItemClothColorMap){
    	return this.httpClient.put<ItemClothColorMap>( `${this.target_url}/${item_cloth_color_map.id}`, item_cloth_color_map,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_item_cloth_color_map(item_cloth_color_map:ItemClothColorMap){ 

		return this.httpClient.delete<ItemClothColorMap>( `${this.target_url}/${item_cloth_color_map.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}