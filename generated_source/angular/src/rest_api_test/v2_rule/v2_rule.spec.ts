import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { V2Rule } from './model/v2_rule'; 



describe( "V2Rule REST test", function() {

	let http: HttpClient;

	let get_test_v2_rule: V2Rule;
	let put_test_v2_rule: V2Rule;
	let patch_test_v2_rule: V2Rule;
	let delete_test_v2_rule: V2Rule;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/v2_rules`,
			( done ) => {
				
				http.head( `/api/v2_rules` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/v2_rules`,
		( done ) => {
			
			http.options( `/api/v2_rules` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/v2_rules`,
		( done ) => {
			http.get<V2Rule>( `/api/v2_rules` ).subscribe( R => {


				get_test_v2_rule = R._embedded.v2_rules[0];
				put_test_v2_rule = R._embedded.v2_rules[1];
				patch_test_v2_rule = R._embedded.v2_rules[2];
				delete_test_v2_rule = R._embedded.v2_rules[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/v2_rules`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new V2Rule()
			
			
			params.title= "v2_rule POST title";
params.rule_type= "v2_rule POST rule_type";
params.rule_message= "v2_rule POST rule_message";
params.first_include_child= "v2_rule POST first_include_child";
params.second_include_child= "v2_rule POST second_include_child";
params.create_dt= "v2_rule POST create_dt";
params.use_yn= "v2_rule POST use_yn";



			const req = http.post<V2Rule>( `/api/v2_rules`, params, httpOptions )
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
	
	

	it( `HEAD /api/v2_rules/v2_rule_id`,
			( done ) => {
				
				http.head( `/api/v2_rules/${get_test_v2_rule.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/v2_rules/v2_rule_id`,
		( done ) => {
			
			http.options( `/api/v2_rules/${get_test_v2_rule.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/v2_rules/v2_rule_id`,
		( done ) => {
			http.get<V2Rule>( `/api/v2_rules/${get_test_v2_rule.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/v2_rules/v2_rule_id`,
		( done ) => {
		
			put_test_v2_rule.title = "v2_rule PUT title";

put_test_v2_rule.rule_type = "v2_rule PUT rule_type";

put_test_v2_rule.rule_message = "v2_rule PUT rule_message";

put_test_v2_rule.first_include_child = "v2_rule PUT first_include_child";

put_test_v2_rule.second_include_child = "v2_rule PUT second_include_child";

put_test_v2_rule.create_dt = "v2_rule PUT create_dt";put_test_v2_rule.use_yn = "v2_rule PUT use_yn";

			http.put<V2Rule>( `/api/v2_rules/${put_test_v2_rule.id}`, put_test_v2_rule ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/v2_rules/v2_rule_id`,
		( done ) => {


			patch_test_v2_rule.title = "v2_rule PATCH title";

patch_test_v2_rule.rule_type = "v2_rule PATCH rule_type";

patch_test_v2_rule.rule_message = "v2_rule PATCH rule_message";

patch_test_v2_rule.first_include_child = "v2_rule PATCH first_include_child";

patch_test_v2_rule.second_include_child = "v2_rule PATCH second_include_child";

patch_test_v2_rule.create_dt = "v2_rule PATCH create_dt";
patch_test_v2_rule.use_yn = "v2_rule PATCH use_yn";

			http.patch<V2Rule>( `/api/v2_rules/${patch_test_v2_rule.id}`, patch_test_v2_rule ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/v2_rules/v2_rule_id`,
		( done ) => {

			http.delete<V2Rule>( `/api/v2_rules/${delete_test_v2_rule.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );