import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Member } from '../model/member';



const httpOptions = {
	headers: new HttpHeaders( { 'Content-Type': 'application/json' } )
};

@Injectable()
export class MemberRest extends CommonHttp {

	private target_url = '/api/members';

	page_all_member() {
		return this.httpClient.get<Member>( `${this.target_url}`,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	create_member( member: Member ): Observable<Member> {

		return this.httpClient.post<Member>( this.target_url, member,httpOptions ).pipe(
			catchError( this.handleError )
		);
	}

	get_member_by_id(member: Member) {
		return this.httpClient.get<Member>( `${this.target_url}/${member.id}`,httpOptions).pipe(
			catchError( this.handleError )
		);
	}

    update_member(member:Member){ 

		return this.httpClient.patch<Member>( `${this.target_url}/${member.id}`, member ,httpOptions).pipe(
			catchError( this.handleError )
		);
    }
    
    replace_member(member:Member){
    	return this.httpClient.put<Member>( `${this.target_url}/${member.id}`, member,httpOptions ).pipe(
    			catchError( this.handleError )
    		);
    }
    
    delete_member(member:Member){ 

		return this.httpClient.delete<Member>( `${this.target_url}/${member.id}`  ).pipe(
			catchError( this.handleError )
		);
    }
	
	 
	
}