	var center_x = 10;
	var center_y = 10;

	var zparser = (function() {
		
		var path_data = [];

		
		var svg_parser = {};
		
			svg_parser.parse = function(){
				$('#stroke').children().find("path").each(function( index ) {
					
					var path_data = $( this ).attr("d").replace(/[\r\n\s]/gi, ""); 
					var path_number_data = path_data.split(/[a-zA-Z]/ig); 
					var arr_data = svg_parser.d_cutter(path_data,path_number_data,0,[]);

				});				
				
				
				$('svg').children().find("line").each(function( index ) {
 

				});		
				
				$('svg').children().find("circle").each(function( index ) {
					 

				});		
				
				$('svg').children().find("text").each(function( index ) {
					 

				});		
				
			};
			
			svg_parser.show_path_data = function(){
				console.dir(path_data);
			};
			
			svg_parser.d_cutter = function(p_path_data,p_path_number_data,p_index,p_R){
				
				if(p_index == p_path_number_data.length){
					console.log(p_index + " FINISH " +p_path_data);
					console.dir(p_R);
					path_data.push(p_R);
					return p_R;
				}
				
				var spliter = $.trim(p_path_number_data[p_index]);
				console.log(p_index + " -------- " + spliter + " ------ "+spliter.length);
				
				if(spliter == ""){
					console.log(p_index + " is empty " +p_path_data);
					svg_parser.d_cutter(p_path_data,p_path_number_data,p_index + 1,p_R);
					
				}else{
					
					if(p_path_data){
						
						var data_type = p_path_data.substring(0, 1); 
						console.log(p_index + " is not empty -- DATA exist -- " +p_path_data);  
						p_R.push(data_type+ "|"+ spliter);
						var next_path_data = p_path_data.substring(spliter.length+1 , p_path_data.length);
						
						svg_parser.d_cutter(next_path_data,p_path_number_data,p_index + 1,p_R);
						
					}else{
						console.log(p_index + " is not empty -- DATA NOT exist -- " +p_path_data);
						console.dir(p_R); 
						return p_R;
					}
					
				}
				
			};
			
			svg_parser.draw_path_m_point = function(){
				
				
				var draw = SVG('drawing').size(800, 800);
				
				for(var path_data_index in path_data){
					
					console.dir(path_data[path_data_index]);
					
					var start_point = path_data[path_data_index][0].split("|")[1].split(",");
					start_point[0] = Number(start_point[0]);
					start_point[1] = Number(start_point[1]);
					
					console.log("* start_point");
					console.log(start_point);
					
					
					
					var path_str_data = "";
					for(var path_index in path_data[path_data_index]){ 
							
 							var path_item_data = path_data[path_data_index][path_index].split("|");
							var path_item_number_data = path_item_data[1].split(",").join(" ");
							
							
							if(path_item_data[0] == "l" || path_item_data[0] == "L"){
								
								
								
								if(path_item_data[1].indexOf(",") == -1){
									
									var second_number_start_index = path_item_data[1].indexOf("-",1);
									
									var first_number  = Number(path_item_data[1].substring(0,second_number_start_index));
									var second_number = Number(path_item_data[1].substring(second_number_start_index,path_item_data[1].length));
									
									start_point[0] += first_number;
									start_point[1] += second_number;
									
									
								}else{
									var path_item_number_data_point = path_item_data[1].split(",");
									
									start_point[0] += Number(path_item_number_data_point[0]);
									start_point[1] += Number(path_item_number_data_point[1]);
								}
								
								console.log(path_index +" start_point");
								console.log(start_point);
								
								
								var circle = draw.circle(3).fill('black').move(Math.round(start_point[0]), Math.round(start_point[1]));
								
							}
							 
							path_str_data += " ";
							path_str_data += path_item_data[0]; 
							path_str_data += path_item_number_data;
							
					}
					path_str_data += " z";
					
					console.log(path_str_data);
					var path = draw.path(path_str_data);
					path.fill('none');
					path.stroke({ color: 'green', width: 2, linecap: 'round', linejoin: 'round' })
					
					console.log(draw.svg());
				}
				
				
			};
			

		return svg_parser;

	}());