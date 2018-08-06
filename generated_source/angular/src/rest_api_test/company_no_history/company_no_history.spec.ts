import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { CompanyNoHistory } from './model/company_no_history'; 



describe( "CompanyNoHistory REST test", function() {

	let http: HttpClient;

	let get_test_company_no_history: CompanyNoHistory;
	let put_test_company_no_history: CompanyNoHistory;
	let patch_test_company_no_history: CompanyNoHistory;
	let delete_test_company_no_history: CompanyNoHistory;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/company_no_historys`,
			( done ) => {
				
				http.head( `/api/company_no_historys` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/company_no_historys`,
		( done ) => {
			
			http.options( `/api/company_no_historys` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/company_no_historys`,
		( done ) => {
			http.get<CompanyNoHistory>( `/api/company_no_historys` ).subscribe( R => {


				get_test_company_no_history = R._embedded.company_no_historys[0];
				put_test_company_no_history = R._embedded.company_no_historys[1];
				patch_test_company_no_history = R._embedded.company_no_historys[2];
				delete_test_company_no_history = R._embedded.company_no_historys[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/company_no_historys`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new CompanyNoHistory()
			
			
			params.name= "company_no_history POST name";
params.company_no= "company_no_history POST company_no";
params.before_no= "company_no_history POST before_no";
params.change_dt= "company_no_history POST change_dt";



			const req = http.post<CompanyNoHistory>( `/api/company_no_historys`, params, httpOptions )
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
	
	

	it( `HEAD /api/company_no_historys/company_no_history_id`,
			( done ) => {
				
				http.head( `/api/company_no_historys/${get_test_company_no_history.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/company_no_historys/company_no_history_id`,
		( done ) => {
			
			http.options( `/api/company_no_historys/${get_test_company_no_history.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/company_no_historys/company_no_history_id`,
		( done ) => {
			http.get<CompanyNoHistory>( `/api/company_no_historys/${get_test_company_no_history.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/company_no_historys/company_no_history_id`,
		( done ) => {
		
			put_test_company_no_history.name = "company_no_history PUT name";

put_test_company_no_history.company_no = "company_no_history PUT company_no";

put_test_company_no_history.before_no = "company_no_history PUT before_no";

put_test_company_no_history.change_dt = "company_no_history PUT change_dt";

			http.put<CompanyNoHistory>( `/api/company_no_historys/${put_test_company_no_history.id}`, put_test_company_no_history ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/company_no_historys/company_no_history_id`,
		( done ) => {


			patch_test_company_no_history.name = "company_no_history PATCH name";

patch_test_company_no_history.company_no = "company_no_history PATCH company_no";

patch_test_company_no_history.before_no = "company_no_history PATCH before_no";

patch_test_company_no_history.change_dt = "company_no_history PATCH change_dt";

			http.patch<CompanyNoHistory>( `/api/company_no_historys/${patch_test_company_no_history.id}`, patch_test_company_no_history ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/company_no_historys/company_no_history_id`,
		( done ) => {

			http.delete<CompanyNoHistory>( `/api/company_no_historys/${delete_test_company_no_history.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );