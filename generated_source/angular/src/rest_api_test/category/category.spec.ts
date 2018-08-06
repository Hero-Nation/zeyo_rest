import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { Category } from './model/category'; 



describe( "Category REST test", function() {

	let http: HttpClient;

	let get_test_category: Category;
	let put_test_category: Category;
	let patch_test_category: Category;
	let delete_test_category: Category;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/categorys`,
			( done ) => {
				
				http.head( `/api/categorys` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/categorys`,
		( done ) => {
			
			http.options( `/api/categorys` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/categorys`,
		( done ) => {
			http.get<Category>( `/api/categorys` ).subscribe( R => {


				get_test_category = R._embedded.categorys[0];
				put_test_category = R._embedded.categorys[1];
				patch_test_category = R._embedded.categorys[2];
				delete_test_category = R._embedded.categorys[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/categorys`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new Category()
			
			
			params.name= "category POST name";
params.create_dt= "category POST create_dt";
params.use_yn= "category POST use_yn";



			const req = http.post<Category>( `/api/categorys`, params, httpOptions )
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
	
	

	it( `HEAD /api/categorys/category_id`,
			( done ) => {
				
				http.head( `/api/categorys/${get_test_category.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/categorys/category_id`,
		( done ) => {
			
			http.options( `/api/categorys/${get_test_category.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/categorys/category_id`,
		( done ) => {
			http.get<Category>( `/api/categorys/${get_test_category.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/categorys/category_id`,
		( done ) => {
		
			put_test_category.name = "category PUT name";

put_test_category.create_dt = "category PUT create_dt";

put_test_category.use_yn = "category PUT use_yn";

			http.put<Category>( `/api/categorys/${put_test_category.id}`, put_test_category ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/categorys/category_id`,
		( done ) => {


			patch_test_category.name = "category PATCH name";

patch_test_category.create_dt = "category PATCH create_dt";

patch_test_category.use_yn = "category PATCH use_yn";

			http.patch<Category>( `/api/categorys/${patch_test_category.id}`, patch_test_category ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/categorys/category_id`,
		( done ) => {

			http.delete<Category>( `/api/categorys/${delete_test_category.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );