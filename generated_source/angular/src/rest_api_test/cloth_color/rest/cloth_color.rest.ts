import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { ClothColor } from '../model/cloth_color';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class ClothColorRest extends CommonHttp {

	private target_url = '/api/cloth_colors';

	page_all_cloth_color() {
		return this.httpClient.get<ClothColor>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_cloth_color( cloth_color: ClothColor ): Observable<ClothColor> {

		return this.httpClient.post<ClothColor>( this.target_url, cloth_color,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_cloth_color_by_id(cloth_color: ClothColor) {
		return this.httpClient.get<ClothColor>( `${this.target_url}/${cloth_color.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_cloth_color(cloth_color:ClothColor){ 

		return this.httpClient.patch<ClothColor>( `${this.target_url}/${cloth_color.id}`, cloth_color ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_cloth_color(cloth_color:ClothColor){
    	return this.httpClient.put<ClothColor>( `${this.target_url}/${cloth_color.id}`, cloth_color,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_cloth_color(cloth_color:ClothColor){ 

		return this.httpClient.delete<ClothColor>( `${this.target_url}/${cloth_color.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}