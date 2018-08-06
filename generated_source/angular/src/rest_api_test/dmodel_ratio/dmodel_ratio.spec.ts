import { TestBed, async, inject, getTestBed } from '@angular/core/testing';
import { HttpClientModule, HttpClient, HttpClientJsonpModule, HttpBackend, JsonpClientBackend, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Jsonp } from '@angular/http';
import { DmodelRatio } from './model/dmodel_ratio'; 



describe( "DmodelRatio REST test", function() {

	let http: HttpClient;

	let get_test_dmodel_ratio: DmodelRatio;
	let put_test_dmodel_ratio: DmodelRatio;
	let patch_test_dmodel_ratio: DmodelRatio;
	let delete_test_dmodel_ratio: DmodelRatio;



	beforeEach(() => {
		TestBed.configureTestingModule( {
			imports: [HttpClientModule]
		} );

		let injector = getTestBed();
		http = injector.get( HttpClient );

	} );


	afterEach( function() {
	} );

	

	it( `HEAD /api/dmodel_ratios`,
			( done ) => {
				
				http.head( `/api/dmodel_ratios` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/dmodel_ratios`,
		( done ) => {
			
			http.options( `/api/dmodel_ratios` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);


	it( `GET /api/dmodel_ratios`,
		( done ) => {
			http.get<DmodelRatio>( `/api/dmodel_ratios` ).subscribe( R => {


				get_test_dmodel_ratio = R._embedded.dmodel_ratios[0];
				put_test_dmodel_ratio = R._embedded.dmodel_ratios[1];
				patch_test_dmodel_ratio = R._embedded.dmodel_ratios[2];
				delete_test_dmodel_ratio = R._embedded.dmodel_ratios[3];

				console.dir( R );
				done();
			} );
		}
	);


	it( `POST /api/dmodel_ratios`,
		( done ) => {

			const httpOptions = {
				headers: new HttpHeaders( {
				} )
			};

			const params = new DmodelRatio()
			
			
			params.default= "dmodel_ratio POST default";
params.min_value= "dmodel_ratio POST min_value";
params.max_value= "dmodel_ratio POST max_value";
params.ratio_value= "dmodel_ratio POST ratio_value";
params.use_yn= "dmodel_ratio POST use_yn";



			const req = http.post<DmodelRatio>( `/api/dmodel_ratios`, params, httpOptions )
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
	
	

	it( `HEAD /api/dmodel_ratios/dmodel_ratio_id`,
			( done ) => {
				
				http.head( `/api/dmodel_ratios/${get_test_dmodel_ratio.id}` ).subscribe( R => {
					console.dir( R );
					done();
				} );
				
			}
		);
	
	it( `OPTIONS /api/dmodel_ratios/dmodel_ratio_id`,
		( done ) => {
			
			http.options( `/api/dmodel_ratios/${get_test_dmodel_ratio.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
			
		}
	);



	it( `GET /api/dmodel_ratios/dmodel_ratio_id`,
		( done ) => {
			http.get<DmodelRatio>( `/api/dmodel_ratios/${get_test_dmodel_ratio.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);



	it( `PUT /api/dmodel_ratios/dmodel_ratio_id`,
		( done ) => {
		
			put_test_dmodel_ratio.default = "dmodel_ratio PUT default";

put_test_dmodel_ratio.min_value = "dmodel_ratio PUT min_value";

put_test_dmodel_ratio.max_value = "dmodel_ratio PUT max_value";

put_test_dmodel_ratio.ratio_value = "dmodel_ratio PUT ratio_value";

put_test_dmodel_ratio.use_yn = "dmodel_ratio PUT use_yn";

			http.put<DmodelRatio>( `/api/dmodel_ratios/${put_test_dmodel_ratio.id}`, put_test_dmodel_ratio ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	it( `PATCH /api/dmodel_ratios/dmodel_ratio_id`,
		( done ) => {


			patch_test_dmodel_ratio.default = "dmodel_ratio PATCH default";

patch_test_dmodel_ratio.min_value = "dmodel_ratio PATCH min_value";

patch_test_dmodel_ratio.max_value = "dmodel_ratio PATCH max_value";

patch_test_dmodel_ratio.ratio_value = "dmodel_ratio PATCH ratio_value";

patch_test_dmodel_ratio.use_yn = "dmodel_ratio PATCH use_yn";

			http.patch<DmodelRatio>( `/api/dmodel_ratios/${patch_test_dmodel_ratio.id}`, patch_test_dmodel_ratio ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);


	it( `DELETE /api/dmodel_ratios/dmodel_ratio_id`,
		( done ) => {

			http.delete<DmodelRatio>( `/api/dmodel_ratios/${delete_test_dmodel_ratio.id}` ).subscribe( R => {
				console.dir( R );
				done();
			} );
		}
	);

	
	
	
} );