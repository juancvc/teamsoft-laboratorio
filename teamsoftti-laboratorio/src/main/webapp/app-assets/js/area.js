function area(arreglo){

	var arrayMenus = [];
	var arrayDias = [];
	for (i = 0; i < arreglo.length; i++) {
		arrayMenus.push(arreglo[i].cantidadVentas);
		arrayDias.push(arreglo[i].strFechaOrden);
		
		console.log("arreglo "+ arreglo[i].cantidadVentas);
	}
//	arrayMenus = arreglo;
	//debugger;
	Chart.defaults.global.defaultFontFamily='-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif',Chart.defaults.global.defaultFontColor="#292b2c";
	var array = new Array();
	var contextPath = $('#contextPath').val();
	var ctx=document.getElementById("Areaportada");
	
	console.log("arrayMenus "+arrayMenus);
	console.log("arrayMenus "+arrayMenus.length);
	//debugger;
//	var area=document.getElementById("lstArea"),
	
	myLineChart=new Chart(ctx,{type:"line",
		data:{labels:arrayDias
			//["Mar 1","Mar 2","Mar 3","Mar 4","Mar 5","Mar 6","Mar 7","Mar 8","Mar 9","Mar 10","Mar 11","Mar 12","Mar 13"]
	,
		datasets:[{label:"Monto S/.",lineTension:.3,backgroundColor:"rgba(2,117,216,0.2)",
		borderColor:"rgba(2,117,216,1)",pointRadius:5,pointBackgroundColor:"rgba(2,117,216,1)",
		pointBorderColor:"rgba(255,255,255,0.8)",
		pointHoverRadius:5,
		pointHoverBackgroundColor:"rgba(2,117,216,1)",
		pointHitRadius:20,
		pointBorderWidth:2,
		
		data:arrayMenus//[1e4,30162,26263,18394,18287,28682,31274,33259,25849,24159,32651,31984,38451]
		}]},
		options:{scales:{xAxes:[{time:{unit:"date"},gridLines:{display:!1},ticks:{maxTicksLimit:7}}],
		yAxes:[{ticks:{min:0,max:4e3,maxTicksLimit:9},gridLines:{color:"rgba(0, 0, 0, .125)"}}]},
		legend:{display:!1}}}),ctx=document.getElementById("myBarChart");
		
 /*
		ctx=document.getElementById("circuloPortada"),
		myPieChart=new Chart(ctx,
		{type:"pie",data:{labels:["Blue","Red","Yellow","Green"],
		datasets:[{data:[12.21,15.58,11.25,8.32],
		backgroundColor:["#007bff","#dc3545","#ffc107","#28a745"]}]}});*/
}

function barra(arreglo,arregloCirculo){

	var arrayMenus = [];
	var arrayMeses = [];
	for (i = 0; i < arreglo.length; i++) {
		arrayMenus.push(arreglo[i].cantidadVentas);
		arrayMeses.push(arreglo[i].nombreMes);
		
		console.log("arreglo barra"+ arreglo[i].cantidadVentas);
	}
	
	var arrayExamen = [];
	var arrayantidad = [];
	for (i = 0; i < arregloCirculo.length; i++) {
		arrayExamen.push(arregloCirculo[i].nombreExamen);
		arrayantidad.push(arregloCirculo[i].cantidadExamenesSolicitados);
		
		console.log("arreglo circulo"+ arregloCirculo[i].cantidadExamenesSolicitados);
	}
	
//	arrayMenus = arreglo;
	//debugger;
	Chart.defaults.global.defaultFontFamily='-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif',Chart.defaults.global.defaultFontColor="#292b2c";
	var array = new Array();
	var contextPath = $('#contextPath').val();
	var ctx=document.getElementById("barraPortada");
 
		myLineChart=new Chart(ctx,{type:"bar",
		data:{labels: arrayMeses, //["January","February","March","April","May","June"],
		datasets:[{label:"Monto S/.",backgroundColor:"rgba(2,117,216,1)",borderColor:"rgba(2,117,216,1)",
		data: arrayMenus// [4215,5312,6251,7841,9821,14984]
		
		}]},
		options:{scales:{xAxes:[{time:{unit:"month"},
		gridLines:{display:!1},
		ticks:{maxTicksLimit:6}}],
		yAxes:[{ticks:{min:0,max:70e3,maxTicksLimit:8},gridLines:{display:!0}}]},legend:{display:!1}}})
		
	ctx=document.getElementById("circuloPortada"),
	myPieChart=new Chart(ctx,{type:"pie",
		data:{labels:arrayExamen,//["Blue","Red","Yellow","Green"],
			datasets:[{data: arrayantidad,//[12.21,15.58,11.25,8.32],
				backgroundColor:["#9dc6f2","#f299a1","#f0da99","#9fedb1"]}]}});
}




