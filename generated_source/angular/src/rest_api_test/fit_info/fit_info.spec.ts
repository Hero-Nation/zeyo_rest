import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { FitInfo } from './model/fit_info'; 



describe( "FitInfo REST test", function() {

	let http: HttpClient;

	let get_test_fit_info: FitInfo;
	let put_test_fit_info: FitInfo;
	let patch_test_fit_info: FitInfo;
	let delete_test_fit_info: FitInfo;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/fit_infos`,
			( done ) => {
				
				http.head( `/api/fit_infos` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/fit_infos`,
		( done ) => {
			
			http.options( `/api/fit_infos` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/fit_infos`,
		( done ) => {
			http.get<FitInfo>( `/api/fit_infos` ).subscribe( R => {


				get_test_fit_info = R._embedded.fit_infos[0];
				put_test_fit_info = R._embedded.fit_infos[1];
				patch_test_fit_info = R._embedded.fit_infos[2];
				delete_test_fit_info = R._embedded.fit_infos[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/fit_infos`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new FitInfo()
			
			
			params.name= "fit_info POST name";
params.meta_desc= "fit_info POST meta_desc";
params.create_dt= "fit_info POST create_dt";
params.use_yn= "fit_info POST use_yn";



			const req = http.post<FitInfo>( `/api/fit_infos`, params, httpOptions )
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
	
	

	it( `HEAD /api/fit_infos/fit_info_id`,
			( done ) => {
				
				http.head( `/api/fit_infos/${get_test_fit_info.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/fit_infos/fit_info_id`,
		( done ) => {
			
			http.options( `/api/fit_infos/${get_test_fit_info.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/fit_infos/fit_info_id`,
		( done ) => {
			http.get<FitInfo>( `/api/fit_infos/${get_test_fit_info.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/fit_infos/fit_info_id`,
		( done ) => {
		
			put_test_fit_info.name = "fit_info PUT name";

put_test_fit_info.meta_desc = "fit_info PUT meta_desc";

put_test_fit_info.create_dt = "fit_info PUT create_dt";

put_test_fit_info.use_yn = "fit_info PUT use_yn";

			http.put<FitInfo>( `/api/fit_infos/${put_test_fit_info.id}`, put_test_fit_info ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/fit_infos/fit_info_id`,
		( done ) => {


			patch_test_fit_info.name = "fit_info PATCH name";

patch_test_fit_info.meta_desc = "fit_info PATCH meta_desc";

patch_test_fit_info.create_dt = "fit_info PATCH create_dt";

patch_test_fit_info.use_yn = "fit_info PATCH use_yn";

			http.patch<FitInfo>( `/api/fit_infos/${patch_test_fit_info.id}`, patch_test_fit_info ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/fit_infos/fit_info_id`,
		( done ) => {

			http.delete<FitInfo>( `/api/fit_infos/${delete_test_fit_info.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );