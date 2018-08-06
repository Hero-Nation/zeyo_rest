import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { FitIntoChoice } from './model/fit_into_choice'; 



describe( "FitIntoChoice REST test", function() {

	let http: HttpClient;

	let get_test_fit_into_choice: FitIntoChoice;
	let put_test_fit_into_choice: FitIntoChoice;
	let patch_test_fit_into_choice: FitIntoChoice;
	let delete_test_fit_into_choice: FitIntoChoice;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/fit_into_choices`,
			( done ) => {
				
				http.head( `/api/fit_into_choices` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/fit_into_choices`,
		( done ) => {
			
			http.options( `/api/fit_into_choices` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/fit_into_choices`,
		( done ) => {
			http.get<FitIntoChoice>( `/api/fit_into_choices` ).subscribe( R => {


				get_test_fit_into_choice = R._embedded.fit_into_choices[0];
				put_test_fit_into_choice = R._embedded.fit_into_choices[1];
				patch_test_fit_into_choice = R._embedded.fit_into_choices[2];
				delete_test_fit_into_choice = R._embedded.fit_into_choices[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/fit_into_choices`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new FitIntoChoice()
			
			
			



			const req = http.post<FitIntoChoice>( `/api/fit_into_choices`, params, httpOptions )
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
	
	

	it( `HEAD /api/fit_into_choices/fit_into_choice_id`,
			( done ) => {
				
				http.head( `/api/fit_into_choices/${get_test_fit_into_choice.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/fit_into_choices/fit_into_choice_id`,
		( done ) => {
			
			http.options( `/api/fit_into_choices/${get_test_fit_into_choice.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/fit_into_choices/fit_into_choice_id`,
		( done ) => {
			http.get<FitIntoChoice>( `/api/fit_into_choices/${get_test_fit_into_choice.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/fit_into_choices/fit_into_choice_id`,
		( done ) => {
		
			

			http.put<FitIntoChoice>( `/api/fit_into_choices/${put_test_fit_into_choice.id}`, put_test_fit_into_choice ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/fit_into_choices/fit_into_choice_id`,
		( done ) => {


			

			http.patch<FitIntoChoice>( `/api/fit_into_choices/${patch_test_fit_into_choice.id}`, patch_test_fit_into_choice ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/fit_into_choices/fit_into_choice_id`,
		( done ) => {

			http.delete<FitIntoChoice>( `/api/fit_into_choices/${delete_test_fit_into_choice.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );