import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { Kindof } from './model/kindof'; 



describe( "Kindof REST test", function() {

	let http: HttpClient;

	let get_test_kindof: Kindof;
	let put_test_kindof: Kindof;
	let patch_test_kindof: Kindof;
	let delete_test_kindof: Kindof;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/kindofs`,
			( done ) => {
				
				http.head( `/api/kindofs` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/kindofs`,
		( done ) => {
			
			http.options( `/api/kindofs` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/kindofs`,
		( done ) => {
			http.get<Kindof>( `/api/kindofs` ).subscribe( R => {


				get_test_kindof = R._embedded.kindofs[0];
				put_test_kindof = R._embedded.kindofs[1];
				patch_test_kindof = R._embedded.kindofs[2];
				delete_test_kindof = R._embedded.kindofs[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/kindofs`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new Kindof()
			
			
			params.ktype= "kindof POST ktype";
params.kvalue= "kindof POST kvalue";
params.use_yn= "kindof POST use_yn";



			const req = http.post<Kindof>( `/api/kindofs`, params, httpOptions )
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
	
	

	it( `HEAD /api/kindofs/kindof_id`,
			( done ) => {
				
				http.head( `/api/kindofs/${get_test_kindof.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/kindofs/kindof_id`,
		( done ) => {
			
			http.options( `/api/kindofs/${get_test_kindof.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/kindofs/kindof_id`,
		( done ) => {
			http.get<Kindof>( `/api/kindofs/${get_test_kindof.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/kindofs/kindof_id`,
		( done ) => {
		
			put_test_kindof.ktype = "kindof PUT ktype";

put_test_kindof.kvalue = "kindof PUT kvalue";

put_test_kindof.use_yn = "kindof PUT use_yn";

			http.put<Kindof>( `/api/kindofs/${put_test_kindof.id}`, put_test_kindof ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/kindofs/kindof_id`,
		( done ) => {


			patch_test_kindof.ktype = "kindof PATCH ktype";

patch_test_kindof.kvalue = "kindof PATCH kvalue";

patch_test_kindof.use_yn = "kindof PATCH use_yn";

			http.patch<Kindof>( `/api/kindofs/${patch_test_kindof.id}`, patch_test_kindof ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/kindofs/kindof_id`,
		( done ) => {

			http.delete<Kindof>( `/api/kindofs/${delete_test_kindof.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );