import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { FitInfoOption } from './model/fit_info_option'; 



describe( "FitInfoOption REST test", function() {

	let http: HttpClient;

	let get_test_fit_info_option: FitInfoOption;
	let put_test_fit_info_option: FitInfoOption;
	let patch_test_fit_info_option: FitInfoOption;
	let delete_test_fit_info_option: FitInfoOption;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/fit_info_options`,
			( done ) => {
				
				http.head( `/api/fit_info_options` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/fit_info_options`,
		( done ) => {
			
			http.options( `/api/fit_info_options` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/fit_info_options`,
		( done ) => {
			http.get<FitInfoOption>( `/api/fit_info_options` ).subscribe( R => {


				get_test_fit_info_option = R._embedded.fit_info_options[0];
				put_test_fit_info_option = R._embedded.fit_info_options[1];
				patch_test_fit_info_option = R._embedded.fit_info_options[2];
				delete_test_fit_info_option = R._embedded.fit_info_options[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/fit_info_options`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new FitInfoOption()
			
			
			params.name= "fit_info_option POST name";
params.use_yn= "fit_info_option POST use_yn";



			const req = http.post<FitInfoOption>( `/api/fit_info_options`, params, httpOptions )
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
	
	

	it( `HEAD /api/fit_info_options/fit_info_option_id`,
			( done ) => {
				
				http.head( `/api/fit_info_options/${get_test_fit_info_option.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/fit_info_options/fit_info_option_id`,
		( done ) => {
			
			http.options( `/api/fit_info_options/${get_test_fit_info_option.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/fit_info_options/fit_info_option_id`,
		( done ) => {
			http.get<FitInfoOption>( `/api/fit_info_options/${get_test_fit_info_option.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/fit_info_options/fit_info_option_id`,
		( done ) => {
		
			put_test_fit_info_option.name = "fit_info_option PUT name";

put_test_fit_info_option.use_yn = "fit_info_option PUT use_yn";

			http.put<FitInfoOption>( `/api/fit_info_options/${put_test_fit_info_option.id}`, put_test_fit_info_option ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/fit_info_options/fit_info_option_id`,
		( done ) => {


			patch_test_fit_info_option.name = "fit_info_option PATCH name";

patch_test_fit_info_option.use_yn = "fit_info_option PATCH use_yn";

			http.patch<FitInfoOption>( `/api/fit_info_options/${patch_test_fit_info_option.id}`, patch_test_fit_info_option ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/fit_info_options/fit_info_option_id`,
		( done ) => {

			http.delete<FitInfoOption>( `/api/fit_info_options/${delete_test_fit_info_option.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );