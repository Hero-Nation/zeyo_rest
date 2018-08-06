import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { ItemMaterialMap } from '../model/item_material_map';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class ItemMaterialMapRest extends CommonHttp {

	private target_url = '/api/item_material_maps';

	page_all_item_material_map() {
		return this.httpClient.get<ItemMaterialMap>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_item_material_map( item_material_map: ItemMaterialMap ): Observable<ItemMaterialMap> {

		return this.httpClient.post<ItemMaterialMap>( this.target_url, item_material_map,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_item_material_map_by_id(item_material_map: ItemMaterialMap) {
		return this.httpClient.get<ItemMaterialMap>( `${this.target_url}/${item_material_map.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_item_material_map(item_material_map:ItemMaterialMap){ 

		return this.httpClient.patch<ItemMaterialMap>( `${this.target_url}/${item_material_map.id}`, item_material_map ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_item_material_map(item_material_map:ItemMaterialMap){
    	return this.httpClient.put<ItemMaterialMap>( `${this.target_url}/${item_material_map.id}`, item_material_map,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_item_material_map(item_material_map:ItemMaterialMap){ 

		return this.httpClient.delete<ItemMaterialMap>( `${this.target_url}/${item_material_map.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}