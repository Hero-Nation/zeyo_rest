import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { CommonHttp } from '../../common/service/common.http';
import { Member } from '../model/member';
import { MemberRest } from '../model/member.rest';

@Injectable()
export class MemberService extends CommonHttp {

	constructor( httpClient: HttpClient, memberRest: MemberRest ) {
		super( httpClient );
	}

	test() {
		return this.httpClient.get<String>( `/api/members/test` ).pipe(
			catchError( this.handleError )
		);
	}

	

}