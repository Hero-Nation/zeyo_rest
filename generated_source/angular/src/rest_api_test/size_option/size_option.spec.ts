import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { SizeOption } from './model/size_option'; 



describe( "SizeOption REST test", function() {

	let http: HttpClient;

	let get_test_size_option: SizeOption;
	let put_test_size_option: SizeOption;
	let patch_test_size_option: SizeOption;
	let delete_test_size_option: SizeOption;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/size_options`,
			( done ) => {
				
				http.head( `/api/size_options` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/size_options`,
		( done ) => {
			
			http.options( `/api/size_options` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/size_options`,
		( done ) => {
			http.get<SizeOption>( `/api/size_options` ).subscribe( R => {


				get_test_size_option = R._embedded.size_options[0];
				put_test_size_option = R._embedded.size_options[1];
				patch_test_size_option = R._embedded.size_options[2];
				delete_test_size_option = R._embedded.size_options[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/size_options`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new SizeOption()
			
			
			params.name= "size_option POST name";
params.create_dt= "size_option POST create_dt";
params.use_yn= "size_option POST use_yn";



			const req = http.post<SizeOption>( `/api/size_options`, params, httpOptions )
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
	
	

	it( `HEAD /api/size_options/size_option_id`,
			( done ) => {
				
				http.head( `/api/size_options/${get_test_size_option.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/size_options/size_option_id`,
		( done ) => {
			
			http.options( `/api/size_options/${get_test_size_option.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/size_options/size_option_id`,
		( done ) => {
			http.get<SizeOption>( `/api/size_options/${get_test_size_option.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/size_options/size_option_id`,
		( done ) => {
		
			put_test_size_option.name = "size_option PUT name";

put_test_size_option.create_dt = "size_option PUT create_dt";

put_test_size_option.use_yn = "size_option PUT use_yn";

			http.put<SizeOption>( `/api/size_options/${put_test_size_option.id}`, put_test_size_option ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/size_options/size_option_id`,
		( done ) => {


			patch_test_size_option.name = "size_option PATCH name";

patch_test_size_option.create_dt = "size_option PATCH create_dt";

patch_test_size_option.use_yn = "size_option PATCH use_yn";

			http.patch<SizeOption>( `/api/size_options/${patch_test_size_option.id}`, patch_test_size_option ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/size_options/size_option_id`,
		( done ) => {

			http.delete<SizeOption>( `/api/size_options/${delete_test_size_option.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );