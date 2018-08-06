import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { SubCategory } from './model/sub_category'; 



describe( "SubCategory REST test", function() {

	let http: HttpClient;

	let get_test_sub_category: SubCategory;
	let put_test_sub_category: SubCategory;
	let patch_test_sub_category: SubCategory;
	let delete_test_sub_category: SubCategory;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/sub_categorys`,
			( done ) => {
				
				http.head( `/api/sub_categorys` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/sub_categorys`,
		( done ) => {
			
			http.options( `/api/sub_categorys` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/sub_categorys`,
		( done ) => {
			http.get<SubCategory>( `/api/sub_categorys` ).subscribe( R => {


				get_test_sub_category = R._embedded.sub_categorys[0];
				put_test_sub_category = R._embedded.sub_categorys[1];
				patch_test_sub_category = R._embedded.sub_categorys[2];
				delete_test_sub_category = R._embedded.sub_categorys[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/sub_categorys`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new SubCategory()
			
			
			params.name= "sub_category POST name";
params.item_image= "sub_category POST item_image";
params.cloth_image= "sub_category POST cloth_image";
params.laundry_yn= "sub_category POST laundry_yn";
params.drycleaning_yn= "sub_category POST drycleaning_yn";
params.ironing_yn= "sub_category POST ironing_yn";
params.drymethod_yn= "sub_category POST drymethod_yn";
params.bleach_yn= "sub_category POST bleach_yn";
params.create_dt= "sub_category POST create_dt";
params.use_yn= "sub_category POST use_yn";



			const req = http.post<SubCategory>( `/api/sub_categorys`, params, httpOptions )
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
	
	

	it( `HEAD /api/sub_categorys/sub_category_id`,
			( done ) => {
				
				http.head( `/api/sub_categorys/${get_test_sub_category.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/sub_categorys/sub_category_id`,
		( done ) => {
			
			http.options( `/api/sub_categorys/${get_test_sub_category.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/sub_categorys/sub_category_id`,
		( done ) => {
			http.get<SubCategory>( `/api/sub_categorys/${get_test_sub_category.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/sub_categorys/sub_category_id`,
		( done ) => {
		
			put_test_sub_category.name = "sub_category PUT name";

put_test_sub_category.item_image = "sub_category PUT item_image";

put_test_sub_category.cloth_image = "sub_category PUT cloth_image";

put_test_sub_category.laundry_yn = "sub_category PUT laundry_yn";

put_test_sub_category.drycleaning_yn = "sub_category PUT drycleaning_yn";

put_test_sub_category.ironing_yn = "sub_category PUT ironing_yn";

put_test_sub_category.drymethod_yn = "sub_category PUT drymethod_yn";

put_test_sub_category.bleach_yn = "sub_category PUT bleach_yn";

put_test_sub_category.create_dt = "sub_category PUT create_dt";

put_test_sub_category.use_yn = "sub_category PUT use_yn";

			http.put<SubCategory>( `/api/sub_categorys/${put_test_sub_category.id}`, put_test_sub_category ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/sub_categorys/sub_category_id`,
		( done ) => {


			patch_test_sub_category.name = "sub_category PATCH name";

patch_test_sub_category.item_image = "sub_category PATCH item_image";

patch_test_sub_category.cloth_image = "sub_category PATCH cloth_image";

patch_test_sub_category.laundry_yn = "sub_category PATCH laundry_yn";

patch_test_sub_category.drycleaning_yn = "sub_category PATCH drycleaning_yn";

patch_test_sub_category.ironing_yn = "sub_category PATCH ironing_yn";

patch_test_sub_category.drymethod_yn = "sub_category PATCH drymethod_yn";

patch_test_sub_category.bleach_yn = "sub_category PATCH bleach_yn";

patch_test_sub_category.create_dt = "sub_category PATCH create_dt";

patch_test_sub_category.use_yn = "sub_category PATCH use_yn";

			http.patch<SubCategory>( `/api/sub_categorys/${patch_test_sub_category.id}`, patch_test_sub_category ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/sub_categorys/sub_category_id`,
		( done ) => {

			http.delete<SubCategory>( `/api/sub_categorys/${delete_test_sub_category.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );