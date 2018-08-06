import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { Item } from './model/item'; 



describe( "Item REST test", function() {

	let http: HttpClient;

	let get_test_item: Item;
	let put_test_item: Item;
	let patch_test_item: Item;
	let delete_test_item: Item;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/items`,
			( done ) => {
				
				http.head( `/api/items` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/items`,
		( done ) => {
			
			http.options( `/api/items` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/items`,
		( done ) => {
			http.get<Item>( `/api/items` ).subscribe( R => {


				get_test_item = R._embedded.items[0];
				put_test_item = R._embedded.items[1];
				patch_test_item = R._embedded.items[2];
				delete_test_item = R._embedded.items[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/items`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new Item()
			
			
			params.image_mode= "item POST image_mode";
params.image= "item POST image";
params.size_measure_mode= "item POST size_measure_mode";
params.size_measure_image= "item POST size_measure_image";
params.name= "item POST name";
params.code= "item POST code";
params.price= "item POST price";
params.madein_builder= "item POST madein_builder";
params.madein_date= "item POST madein_date";
params.laundry_yn= "item POST laundry_yn";
params.drycleaning_yn= "item POST drycleaning_yn";
params.ironing_yn= "item POST ironing_yn";
params.drymethod_yn= "item POST drymethod_yn";
params.bleach_yn= "item POST bleach_yn";
params.size_link_yn= "item POST size_link_yn";
params.create_dt= "item POST create_dt";
params.use_yn= "item POST use_yn";



			const req = http.post<Item>( `/api/items`, params, httpOptions )
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
	
	

	it( `HEAD /api/items/item_id`,
			( done ) => {
				
				http.head( `/api/items/${get_test_item.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/items/item_id`,
		( done ) => {
			
			http.options( `/api/items/${get_test_item.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/items/item_id`,
		( done ) => {
			http.get<Item>( `/api/items/${get_test_item.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/items/item_id`,
		( done ) => {
		
			put_test_item.image_mode = "item PUT image_mode";

put_test_item.image = "item PUT image";

put_test_item.size_measure_mode = "item PUT size_measure_mode";

put_test_item.size_measure_image = "item PUT size_measure_image";

put_test_item.name = "item PUT name";

put_test_item.code = "item PUT code";

put_test_item.price = "item PUT price";

put_test_item.madein_builder = "item PUT madein_builder";

put_test_item.madein_date = "item PUT madein_date";

put_test_item.laundry_yn = "item PUT laundry_yn";

put_test_item.drycleaning_yn = "item PUT drycleaning_yn";

put_test_item.ironing_yn = "item PUT ironing_yn";

put_test_item.drymethod_yn = "item PUT drymethod_yn";

put_test_item.bleach_yn = "item PUT bleach_yn";

put_test_item.size_link_yn = "item PUT size_link_yn";

put_test_item.create_dt = "item PUT create_dt";

put_test_item.use_yn = "item PUT use_yn";

			http.put<Item>( `/api/items/${put_test_item.id}`, put_test_item ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/items/item_id`,
		( done ) => {


			patch_test_item.image_mode = "item PATCH image_mode";

patch_test_item.image = "item PATCH image";

patch_test_item.size_measure_mode = "item PATCH size_measure_mode";

patch_test_item.size_measure_image = "item PATCH size_measure_image";

patch_test_item.name = "item PATCH name";

patch_test_item.code = "item PATCH code";

patch_test_item.price = "item PATCH price";

patch_test_item.madein_builder = "item PATCH madein_builder";

patch_test_item.madein_date = "item PATCH madein_date";

patch_test_item.laundry_yn = "item PATCH laundry_yn";

patch_test_item.drycleaning_yn = "item PATCH drycleaning_yn";

patch_test_item.ironing_yn = "item PATCH ironing_yn";

patch_test_item.drymethod_yn = "item PATCH drymethod_yn";

patch_test_item.bleach_yn = "item PATCH bleach_yn";

patch_test_item.size_link_yn = "item PATCH size_link_yn";

patch_test_item.create_dt = "item PATCH create_dt";

patch_test_item.use_yn = "item PATCH use_yn";

			http.patch<Item>( `/api/items/${patch_test_item.id}`, patch_test_item ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/items/item_id`,
		( done ) => {

			http.delete<Item>( `/api/items/${delete_test_item.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );