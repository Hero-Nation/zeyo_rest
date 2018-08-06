import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { Warranty } from './model/warranty'; 



describe( "Warranty REST test", function() {

	let http: HttpClient;

	let get_test_warranty: Warranty;
	let put_test_warranty: Warranty;
	let patch_test_warranty: Warranty;
	let delete_test_warranty: Warranty;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/warrantys`,
			( done ) => {
				
				http.head( `/api/warrantys` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/warrantys`,
		( done ) => {
			
			http.options( `/api/warrantys` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/warrantys`,
		( done ) => {
			http.get<Warranty>( `/api/warrantys` ).subscribe( R => {


				get_test_warranty = R._embedded.warrantys[0];
				put_test_warranty = R._embedded.warrantys[1];
				patch_test_warranty = R._embedded.warrantys[2];
				delete_test_warranty = R._embedded.warrantys[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/warrantys`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new Warranty()
			
			
			params.scope= "warranty POST scope";
params.create_dt= "warranty POST create_dt";
params.use_yn= "warranty POST use_yn";



			const req = http.post<Warranty>( `/api/warrantys`, params, httpOptions )
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
	
	

	it( `HEAD /api/warrantys/warranty_id`,
			( done ) => {
				
				http.head( `/api/warrantys/${get_test_warranty.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/warrantys/warranty_id`,
		( done ) => {
			
			http.options( `/api/warrantys/${get_test_warranty.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/warrantys/warranty_id`,
		( done ) => {
			http.get<Warranty>( `/api/warrantys/${get_test_warranty.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/warrantys/warranty_id`,
		( done ) => {
		
			put_test_warranty.scope = "warranty PUT scope";

put_test_warranty.create_dt = "warranty PUT create_dt";put_test_warranty.use_yn = "warranty PUT use_yn";

			http.put<Warranty>( `/api/warrantys/${put_test_warranty.id}`, put_test_warranty ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/warrantys/warranty_id`,
		( done ) => {


			patch_test_warranty.scope = "warranty PATCH scope";

patch_test_warranty.create_dt = "warranty PATCH create_dt";
patch_test_warranty.use_yn = "warranty PATCH use_yn";

			http.patch<Warranty>( `/api/warrantys/${patch_test_warranty.id}`, patch_test_warranty ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/warrantys/warranty_id`,
		( done ) => {

			http.delete<Warranty>( `/api/warrantys/${delete_test_warranty.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );