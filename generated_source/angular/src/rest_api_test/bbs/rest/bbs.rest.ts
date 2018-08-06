import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Bbs } from '../model/bbs';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class BbsRest extends CommonHttp {

	private target_url = '/api/bbss';

	page_all_bbs() {
		return this.httpClient.get<Bbs>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_bbs( bbs: Bbs ): Observable<Bbs> {

		return this.httpClient.post<Bbs>( this.target_url, bbs,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_bbs_by_id(bbs: Bbs) {
		return this.httpClient.get<Bbs>( `${this.target_url}/${bbs.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_bbs(bbs:Bbs){ 

		return this.httpClient.patch<Bbs>( `${this.target_url}/${bbs.id}`, bbs ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_bbs(bbs:Bbs){
    	return this.httpClient.put<Bbs>( `${this.target_url}/${bbs.id}`, bbs,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_bbs(bbs:Bbs){ 

		return this.httpClient.delete<Bbs>( `${this.target_url}/${bbs.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}