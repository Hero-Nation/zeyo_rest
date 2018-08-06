import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Material } from '../model/material';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class MaterialRest extends CommonHttp {

	private target_url = '/api/materials';

	page_all_material() {
		return this.httpClient.get<Material>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_material( material: Material ): Observable<Material> {

		return this.httpClient.post<Material>( this.target_url, material,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_material_by_id(material: Material) {
		return this.httpClient.get<Material>( `${this.target_url}/${material.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_material(material:Material){ 

		return this.httpClient.patch<Material>( `${this.target_url}/${material.id}`, material ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_material(material:Material){
    	return this.httpClient.put<Material>( `${this.target_url}/${material.id}`, material,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_material(material:Material){ 

		return this.httpClient.delete<Material>( `${this.target_url}/${material.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}