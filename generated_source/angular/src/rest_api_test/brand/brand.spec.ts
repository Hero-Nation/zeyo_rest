import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { Brand } from './model/brand'; 



describe( "Brand REST test", function() {

	let http: HttpClient;

	let get_test_brand: Brand;
	let put_test_brand: Brand;
	let patch_test_brand: Brand;
	let delete_test_brand: Brand;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/brands`,
			( done ) => {
				
				http.head( `/api/brands` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/brands`,
		( done ) => {
			
			http.options( `/api/brands` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/brands`,
		( done ) => {
			http.get<Brand>( `/api/brands` ).subscribe( R => {


				get_test_brand = R._embedded.brands[0];
				put_test_brand = R._embedded.brands[1];
				patch_test_brand = R._embedded.brands[2];
				delete_test_brand = R._embedded.brands[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/brands`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new Brand()
			
			
			params.name= "brand POST name";
params.use_yn= "brand POST use_yn";



			const req = http.post<Brand>( `/api/brands`, params, httpOptions )
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
	
	

	it( `HEAD /api/brands/brand_id`,
			( done ) => {
				
				http.head( `/api/brands/${get_test_brand.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/brands/brand_id`,
		( done ) => {
			
			http.options( `/api/brands/${get_test_brand.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/brands/brand_id`,
		( done ) => {
			http.get<Brand>( `/api/brands/${get_test_brand.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/brands/brand_id`,
		( done ) => {
		
			put_test_brand.name = "brand PUT name";

put_test_brand.use_yn = "brand PUT use_yn";

			http.put<Brand>( `/api/brands/${put_test_brand.id}`, put_test_brand ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/brands/brand_id`,
		( done ) => {


			patch_test_brand.name = "brand PATCH name";

patch_test_brand.use_yn = "brand PATCH use_yn";

			http.patch<Brand>( `/api/brands/${patch_test_brand.id}`, patch_test_brand ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/brands/brand_id`,
		( done ) => {

			http.delete<Brand>( `/api/brands/${delete_test_brand.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );