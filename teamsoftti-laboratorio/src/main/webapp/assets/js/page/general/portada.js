var indexRegistro = "";
var tipoLlamadoEliminar = "";
var reiterativo ="NO";

function confirmar_accion(tipo, index) {
	indexRegistro = index;
	tipoLlamadoEliminar = tipo;
	// alert(codigoReferencia);
	$('#md_confirmacion').modal('show');

}
function agregar_accion() {
	$('#md_reg_confirmacion').modal('show');
}

$(document).ready(function() {
	$("#btnConfirmarGeneric").click(function() {
		if (tipoLlamadoEliminar == "1") {
			eliminarLugarCampania(indexRegistro);
		} else if (tipoLlamadoEliminar == "2") {
			eliminarCampania(indexRegistro);
		} else if (tipoLlamadoEliminar == "3") {
			eliminarPostulante(indexRegistro);
		}

	});
});


function refrescarListadoPostulante() {
	var contextPath = $('#contextPath').val();
	$.ajax({
		url : contextPath + "/bancoController/listarPostulantes",
		type : 'GET',
	
		success : function(data) {
			//console.log("SUCCESS: ", data);
			$('#listadoDetallePostulante').html(data);
		},
		error : function() {
			//console.log("ERROR: ");
		}
	});
}
/*******
function refrescarListadoPostulante() {
	var contextPath = $('#contextPath').val();
	var item = 0;
	var htmlTabla = "";
	$
			.ajax({
				url : contextPath + "/bancoController/listarPostulantes",
				type : 'GET',

				success : function(data) {
					if (data != null) {

						for (var i = 0; i < data.length; i++) {
							var objeto = data[i];
							item = item + 1;
							htmlTabla += "<tr>" + "<td>"
									+ item
									+ "</td>"
									+ "<td>"
									+ objeto.persona.nombreCompleto
									+ "</td>"
									+ "<td>"
									+ objeto.campania.lugarCampaniaBean.nombre
									+ "</td>"
									+ "</td>"
									+ "<td>"
									+ formatDateHora(objeto.fechaCreacion)
									+ "</td>"
									+ "</td>"
									+ "<td>"
									+ objeto.nombreUsuarioCreacion
									+ "</td>"
									+ "<td>"
									+ "<a title='Modificar' data-placement='top'"
									+ "data-toggle='tooltip' class='btn btn-outline-success btn-sm'"
									+ "onclick=\"javascript:modificarElementoGenerico('/bancoController/modificarPostulante','"
									+ [ i ]
									+ "');\"'"
									+ "href='#'><i class='icon-pencil'></i></a>"

									+ "<button type='button'"
									+ " class='btn btn-outline-danger btn-sm' "
									+ " data-toggle='tooltip'  data-placement='top'  title='Eliminar'"
									+ " onclick=\"confirmar_accion(3,'" + [ i ]
									+ "');\""
									+ " data-original-title='Eliminar'"
									+ " id='eliminarReferencia'" + [ i ] + ">"
									+ "<i class='icon-trash'></i></button>"
									+ "</td>" + "</tr>";

						}
						// console.log(htmlTabla);
					}
					// console.log("SUCCESS: ", data);
					$('#idBodyListaPostulante').empty();
					$('#idBodyListaPostulante').html(htmlTabla);
				},
				error : function() {
					console.log("ERROR: ");
				}
			});
}
*/



function formatDate(date) {
	var d = new Date(date), 
	month = '' + (d.getMonth() + 1), 
	day = '' + d.getDate(), 
	year = d.getFullYear();

	if (month.length < 2)
		month = '0' + month;
	if (day.length < 2)
		day = '0' + day;

	return [ day, month, year ].join('/');
}


function formatDateHora(date) {
	var d = new Date(date), 
	month = '' + (d.getMonth() + 1), 
	day = '' + d.getDate(), 
	year = d.getFullYear() +' '+ d.getHours()+':'+ d.getMinutes();

	if (month.length < 2)
		month = '0' + month;
	if (day.length < 2)
		day = '0' + day;

	return [ day, month, year ].join('/');
}



function ubigeoCargarModal(tipoLlamado) {
	var contextPath = $('#contextPath').val();
	// var codigoLengua = $('#codigoLengua').val(); //
	// document.getElementById("codigoLengua").value;

	path = contextPath + "/ubigeoController/listadoUbigeoModal";
	// alert("path " + path)
	iniciarBloqueo();
	$.ajax({
		type : "POST",
		url : path,

		success : function(data) {
			// console.log("SUCCESS: ", data);
			$("#modalUbigeo").html(data);
			$('#tipoLlamado').val(tipoLlamado);
			$("#modalUbigeo").modal('show');
		},
		error : function(request, status, error) {
			console.log("ERROR: " + error);
		}
	});
	finBloqueo();
}



function mifuncion(index)

{

	'javascript:modificarElementoGenerico(/bancoController/modificarPostulante,'+ index + ')';

// alert("se ha realizado click" + index);

}
function agregarBoton(htmlID, texto, funcion) {
	var o=document.getElementById(htmlID);
	o.html+='<input type="button" value="'+texto+'" onclick="'+funcion+'">';
}
 



function calculaIMC(){  
	  //declaramos las variables
	  var peso, altura, imc , valorIMC , vts, MC, imcDescripcion; 
	  peso = $('#pesoPostulante').val();
	//  console.log("pesoPostulante " + document.getElementById("pesoPostulante").value );
	  altura =  $('#tallaPostulante').val();
	  //comprobamos que los campos
	  //no vengan vacíos
	  if(peso!=""){
		  vts = peso*10
		    $('#volumenSangrePostulante').val(vts); //document.getElementById("imcPostulante").value=imc.toFixed(2);
		    $('#regVolumenSangrePostulante').val(vts);
	  }else{ 
		    $('#volumenSangrePostulante').val(""); //document.getElementById("imcPostulante").value=imc.toFixed(2);
		    $('#regVolumenSangrePostulante').val("");
	  }
	  if(peso!="" && altura!=""){
	    //aplicamos la fórmula
		    altura = parseInt(altura)/100;
		    imc=peso/(altura*altura);
		    console.log("imc " + imc.toFixed(2));
		    MC = imc.toFixed(2);
			if(MC<18){
				imcDescripcion = "BAJO DE PESO";														
			}else if(MC>=18 && MC<=24.9){
				imcDescripcion = "NORMAL";			
			}else if(MC>=25 && MC<=26.9){
				imcDescripcion = "SOBREPESO";			
			}else if(MC>=27 && MC<=29.9){
				imcDescripcion = "OBESIDAD LEVE";			
			}else if(MC>=30 && MC<=39.9){
				imcDescripcion ="OBESIDAD MODERADA";			
			}else if(MC>=40){
				imcDescripcion = "OBESIDAD SEVERA";			
			}else{
				imcDescripcion = "DATOS NO CORRECTOS";		
			}
			
		    $('#imcPostulante').val(imcDescripcion); //document.getElementById("imcPostulante").value=imc.toFixed(2);
		    $('#codigoIMCPostulante').val(imcDescripcion);
	  }else{
		    $('#imcPostulante').val(""); //document.getElementById("imcPostulante").value=imc.toFixed(2);
		    $('#codigoIMCPostulante').val("");
	  }
	}





function vistaTicket() {
	var contextPath = $('#contextPath').val();
	var personaCodigo = $('#personaCodigo').val();
	var numeroDocumento = $('#nroDocumentoPaciente').val();
	
	path = contextPath + "/bancoController/imprimirTicket";
	// alert("path " + path) 
	$.ajax({
		type : "POST",
		url : path,

		success : function(data) {
			// console.log("SUCCESS: ", data);
			$("#modalTicket").html(data); 
			$("#modalTicket").modal('show');
		},
		error : function(request, status, error) {
			console.log("ERROR: " + error);
		}
	}); 
}

function verTicket(){
	var contextPath = $('#contextPath').val();
    var url = contextPath +'/bancoController/imprimirTicket';
   // localStorage.setItem("mfData", JSON.stringify(reg.opermanifcb));
   // localStorage.setItem("mfItemsData", JSON.stringify(reg.lstManifiestoPasajeros));
    $('#iframe-manifiesto').html('<div class="icon-spinner"><i class="fa fa-refresh fa-spin fa-3x fa-fw margin-bottom"></i></div>');
 //   $scope.showModalManifiesto = !$scope.showModalManifiesto;
    var $frame = $('<iframe id="iframe-bp" name="frmPrintManifiesto" scrolling="yes" frameborder="0" style="width:100%; height:450px;" src='+url+'></iframe>');
 //   document.getElementsByClassName("modal-hijo")[1].style.width = '850px';
    setTimeout(function(){ 
        $('#iframe-manifiesto').html($frame);
        $("<div class='center-element'><button type='button' onclick='imprimirManifiesto()'>IMPRIMIR</button><div>").appendTo('#iframe-manifiesto');
    },100);
};

function cargarVentaModal() {
	var contextPath = $('#contextPath').val();

	path = contextPath + "/inicioController/listaDetalleVentaModal";
	// alert("path " + path)
		$.ajax({
			type : "POST",
			url : path,

			success : function(data) {
				// console.log("SUCCESS: ", data);
				$("#modalVentaDiaria").html(data); 
				$("#modalVentaDiaria").modal('show'); 
				
			},
			error : function(request, status, error) {
				console.log("ERROR: " + error);
			}
		}); 
}


function refrescarListadoVentasSemanal() {
	console.log("refrescarListadoVentasSemanal");
	
	$("#spnSemanal").trigger("click");
	 document.getElementById("materialInline2").checked = true;
	 $( "#materialInline2" ).prop( "checked", true );
	/***
	var contextPath = $('#contextPath').val();
	
	path = contextPath + "/inicioController/portadaListarSemanal";
	$.ajax({
	//	url : contextPath + "/inicioController/portadaListarSemanal",
		type : 'POST',
	
		success : function(data) {
			//console.log("SUCCESS: ", data);
			$('#dataTable').html(data);
		},
		error : function() {
			//console.log("ERROR: ");
		}
	});*/
}

function refrescarListadoVentasDiario() {
	var contextPath = $('#contextPath').val();
	$("#spnDiario").trigger("click");
	 document.getElementById("materialInline1").checked = true;
	 
/**	path = contextPath + "/inicioController/portadaListarDiario";
	$.ajax({
	//	url : contextPath + "/inicioController/portadaListarDiario",
		type : 'GET',
	
		success : function(data) {
			//console.log("SUCCESS: ", data);
			$('#dataTable').html(data);
		},
		error : function() {
			//console.log("ERROR: ");
		}
	});*/
}

function refrescarListadoVentasMensual() {
	 
	$("#spnMensual").trigger("click");
	 document.getElementById("materialInline3").checked = true;
	 
	 console.log("aca mensual");
	 
	/***
	var contextPath = $('#contextPath').val();
	path = contextPath + "/inicioController/portadaListarMensual";
	$.ajax({
	//	url : contextPath + "/inicioController/portadaListarMensual",
		type : 'GET',
	
		success : function(data) {
			//console.log("SUCCESS: ", data);
			$('#dataTable').html(data);
		},
		error : function() {
			//console.log("ERROR: ");
		}
	});
	*/
}

function refrescarListadoVentasAnual() {
	$("#spnAnual").trigger("click");
	 document.getElementById("materialInline4").checked = true;
	 
	/**
	var contextPath = $('#contextPath').val();
	path = contextPath + "/inicioController/portadaListarAnual";
	$.ajax({
	//	url : contextPath + "/inicioController/portadaListarAnual",
		type : 'GET',
	
		success : function(data) {
			//console.log("SUCCESS: ", data);
			$('#dataTable').html(data);
		},
		error : function() {
			//console.log("ERROR: ");
		}
	});*/
}

var meses = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
var dias = ["Domingo","Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"];

function calculaSemana() {

  // obtenemos los valores de año y semana (asumimos que son valores válidos)
  var year = document.getElementById("year").value;
  var week = document.getElementById("week").value;

  // obtenemos el primer dia del año
  var primerdia = new Date(year, 0, 1);
  
  // obtenemos la corrección necesaria
  var correccion = 6 - primerdia.getDay();
  
  // validación para la semana
  if (week * 7 + correccion > 365) { alert("El valor para semana no es válido"); return false; }
  
  // obtenemos el lunes y domingo de la semana especificada
  var primer = new Date(year, 0, (week - 1) * 7 + 3 + correccion);
  var ultimo = new Date(year, 0, (week - 1) * 7 + 9 + correccion);

  // mostramos el resultado
  document.getElementById("resultado").innerHTML = 
	  "Del " + primer.getDate() + " de "+ meses[primer.getMonth()]  +
	 " al " + ultimo.getDate() + " de "+ meses[ultimo.getMonth()] ;
  /**  "El primer día de la " + week + "<sup>a</sup> semana de " + year + " es " + primer.getDate() + " de " + meses[primer.getMonth()] + " (" + dias[primer.getDay()] + ")<br/>" +
    "El último día de la " + week + "<sup>a</sup> semana de " + year + " es " + ultimo.getDate() + " de " + meses[ultimo.getMonth()] + " (" + dias[ultimo.getDay()] + ")"; */

}

