import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { Member } from './model/member'; 



describe( "Member REST test", function() {

	let http: HttpClient;

	let get_test_member: Member;
	let put_test_member: Member;
	let patch_test_member: Member;
	let delete_test_member: Member;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/members`,
			( done ) => {
				
				http.head( `/api/members` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/members`,
		( done ) => {
			
			http.options( `/api/members` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/members`,
		( done ) => {
			http.get<Member>( `/api/members` ).subscribe( R => {


				get_test_member = R._embedded.members[0];
				put_test_member = R._embedded.members[1];
				patch_test_member = R._embedded.members[2];
				delete_test_member = R._embedded.members[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/members`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new Member()
			
			
			params.member_id= "member POST member_id";
params.name= "member POST name";
params.password= "member POST password";
params.phone= "member POST phone";
params.email= "member POST email";
params.manager= "member POST manager";
params.manager_email= "member POST manager_email";
params.manager_phone= "member POST manager_phone";
params.create_dt= "member POST create_dt";
params.delete_dt= "member POST delete_dt";
params.use_yn= "member POST use_yn";



			const req = http.post<Member>( `/api/members`, params, httpOptions )
				.subscribe(
				R => {
					console.log( R );
					done();
				},
				err => {
					console.log( "Error occured" );
					done();
				}
				);
		}
	);
	
	

	it( `HEAD /api/members/member_id`,
			( done ) => {
				
				http.head( `/api/members/${get_test_member.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/members/member_id`,
		( done ) => {
			
			http.options( `/api/members/${get_test_member.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/members/member_id`,
		( done ) => {
			http.get<Member>( `/api/members/${get_test_member.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/members/member_id`,
		( done ) => {
		
			put_test_member.member_id = "member PUT member_id";

put_test_member.name = "member PUT name";

put_test_member.password = "member PUT password";

put_test_member.phone = "member PUT phone";

put_test_member.email = "member PUT email";

put_test_member.manager = "member PUT manager";

put_test_member.manager_email = "member PUT manager_email";

put_test_member.manager_phone = "member PUT manager_phone";

put_test_member.create_dt = "member PUT create_dt";

put_test_member.delete_dt = "member PUT delete_dt";

put_test_member.use_yn = "member PUT use_yn";

			http.put<Member>( `/api/members/${put_test_member.id}`, put_test_member ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/members/member_id`,
		( done ) => {


			patch_test_member.member_id = "member PATCH member_id";

patch_test_member.name = "member PATCH name";

patch_test_member.password = "member PATCH password";

patch_test_member.phone = "member PATCH phone";

patch_test_member.email = "member PATCH email";

patch_test_member.manager = "member PATCH manager";

patch_test_member.manager_email = "member PATCH manager_email";

patch_test_member.manager_phone = "member PATCH manager_phone";

patch_test_member.create_dt = "member PATCH create_dt";

patch_test_member.delete_dt = "member PATCH delete_dt";

patch_test_member.use_yn = "member PATCH use_yn";

			http.patch<Member>( `/api/members/${patch_test_member.id}`, patch_test_member ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/members/member_id`,
		( done ) => {

			http.delete<Member>( `/api/members/${delete_test_member.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );