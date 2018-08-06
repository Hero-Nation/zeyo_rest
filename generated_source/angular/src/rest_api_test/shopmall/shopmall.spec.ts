import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { Shopmall } from './model/shopmall'; 



describe( "Shopmall REST test", function() {

	let http: HttpClient;

	let get_test_shopmall: Shopmall;
	let put_test_shopmall: Shopmall;
	let patch_test_shopmall: Shopmall;
	let delete_test_shopmall: Shopmall;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/shopmalls`,
			( done ) => {
				
				http.head( `/api/shopmalls` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/shopmalls`,
		( done ) => {
			
			http.options( `/api/shopmalls` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/shopmalls`,
		( done ) => {
			http.get<Shopmall>( `/api/shopmalls` ).subscribe( R => {


				get_test_shopmall = R._embedded.shopmalls[0];
				put_test_shopmall = R._embedded.shopmalls[1];
				patch_test_shopmall = R._embedded.shopmalls[2];
				delete_test_shopmall = R._embedded.shopmalls[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/shopmalls`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new Shopmall()
			
			
			params.name= "shopmall POST name";
params.use_yn= "shopmall POST use_yn";



			const req = http.post<Shopmall>( `/api/shopmalls`, params, httpOptions )
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
	
	

	it( `HEAD /api/shopmalls/shopmall_id`,
			( done ) => {
				
				http.head( `/api/shopmalls/${get_test_shopmall.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/shopmalls/shopmall_id`,
		( done ) => {
			
			http.options( `/api/shopmalls/${get_test_shopmall.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/shopmalls/shopmall_id`,
		( done ) => {
			http.get<Shopmall>( `/api/shopmalls/${get_test_shopmall.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/shopmalls/shopmall_id`,
		( done ) => {
		
			put_test_shopmall.name = "shopmall PUT name";

put_test_shopmall.use_yn = "shopmall PUT use_yn";

			http.put<Shopmall>( `/api/shopmalls/${put_test_shopmall.id}`, put_test_shopmall ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/shopmalls/shopmall_id`,
		( done ) => {


			patch_test_shopmall.name = "shopmall PATCH name";

patch_test_shopmall.use_yn = "shopmall PATCH use_yn";

			http.patch<Shopmall>( `/api/shopmalls/${patch_test_shopmall.id}`, patch_test_shopmall ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/shopmalls/shopmall_id`,
		( done ) => {

			http.delete<Shopmall>( `/api/shopmalls/${delete_test_shopmall.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );