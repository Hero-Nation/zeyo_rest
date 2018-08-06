import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { Madein } from './model/madein'; 



describe( "Madein REST test", function() {

	let http: HttpClient;

	let get_test_madein: Madein;
	let put_test_madein: Madein;
	let patch_test_madein: Madein;
	let delete_test_madein: Madein;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/madeins`,
			( done ) => {
				
				http.head( `/api/madeins` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/madeins`,
		( done ) => {
			
			http.options( `/api/madeins` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/madeins`,
		( done ) => {
			http.get<Madein>( `/api/madeins` ).subscribe( R => {


				get_test_madein = R._embedded.madeins[0];
				put_test_madein = R._embedded.madeins[1];
				patch_test_madein = R._embedded.madeins[2];
				delete_test_madein = R._embedded.madeins[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/madeins`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new Madein()
			
			
			params.name= "madein POST name";
params.create_dt= "madein POST create_dt";
params.use_yn= "madein POST use_yn";



			const req = http.post<Madein>( `/api/madeins`, params, httpOptions )
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
	
	

	it( `HEAD /api/madeins/madein_id`,
			( done ) => {
				
				http.head( `/api/madeins/${get_test_madein.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/madeins/madein_id`,
		( done ) => {
			
			http.options( `/api/madeins/${get_test_madein.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/madeins/madein_id`,
		( done ) => {
			http.get<Madein>( `/api/madeins/${get_test_madein.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/madeins/madein_id`,
		( done ) => {
		
			put_test_madein.name = "madein PUT name";

put_test_madein.create_dt = "madein PUT create_dt";

put_test_madein.use_yn = "madein PUT use_yn";

			http.put<Madein>( `/api/madeins/${put_test_madein.id}`, put_test_madein ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/madeins/madein_id`,
		( done ) => {


			patch_test_madein.name = "madein PATCH name";

patch_test_madein.create_dt = "madein PATCH create_dt";

patch_test_madein.use_yn = "madein PATCH use_yn";

			http.patch<Madein>( `/api/madeins/${patch_test_madein.id}`, patch_test_madein ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/madeins/madein_id`,
		( done ) => {

			http.delete<Madein>( `/api/madeins/${delete_test_madein.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );