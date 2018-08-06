import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { SizeOption } from '../model/size_option';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class SizeOptionRest extends CommonHttp {

	private target_url = '/api/size_options';

	page_all_size_option() {
		return this.httpClient.get<SizeOption>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_size_option( size_option: SizeOption ): Observable<SizeOption> {

		return this.httpClient.post<SizeOption>( this.target_url, size_option,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_size_option_by_id(size_option: SizeOption) {
		return this.httpClient.get<SizeOption>( `${this.target_url}/${size_option.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_size_option(size_option:SizeOption){ 

		return this.httpClient.patch<SizeOption>( `${this.target_url}/${size_option.id}`, size_option ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_size_option(size_option:SizeOption){
    	return this.httpClient.put<SizeOption>( `${this.target_url}/${size_option.id}`, size_option,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_size_option(size_option:SizeOption){ 

		return this.httpClient.delete<SizeOption>( `${this.target_url}/${size_option.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}