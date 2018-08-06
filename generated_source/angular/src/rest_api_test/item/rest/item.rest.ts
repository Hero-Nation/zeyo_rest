import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Item } from '../model/item';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class ItemRest extends CommonHttp {

	private target_url = '/api/items';

	page_all_item() {
		return this.httpClient.get<Item>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_item( item: Item ): Observable<Item> {

		return this.httpClient.post<Item>( this.target_url, item,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_item_by_id(item: Item) {
		return this.httpClient.get<Item>( `${this.target_url}/${item.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_item(item:Item){ 

		return this.httpClient.patch<Item>( `${this.target_url}/${item.id}`, item ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_item(item:Item){
    	return this.httpClient.put<Item>( `${this.target_url}/${item.id}`, item,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_item(item:Item){ 

		return this.httpClient.delete<Item>( `${this.target_url}/${item.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}