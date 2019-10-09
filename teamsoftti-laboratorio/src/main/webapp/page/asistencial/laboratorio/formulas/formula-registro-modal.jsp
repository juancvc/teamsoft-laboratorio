<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
* {
	box-sizing: border-box;
}

body {
font-family: Cambria;
font-size: 13px;  
}

/*the container must be positioned relative:*/
.autocomplete {
	/*position: relative;*/
	display: inline-block;
}

input {
	border: 1px solid transparent; 
	font-size: 13px;
}

input[type=text] {
	width: 100%;
}

.autocomplete-items {
	position: absolute;
	border: 1px solid #d4d4d4;
	border-bottom: none;
	border-top: none;
	z-index: 99;
	/*position the autocomplete items to be the same width as the container:*/
	top: 100%;
	left: 0;
	right: 0;
}

.autocomplete-items div {
	padding: 10px;
	cursor: pointer;
	background-color: #fff;
	border-bottom: 1px solid #d4d4d4;
}

/*when hovering an item:*/
.autocomplete-items div:hover {
	background-color: #e9e9e9;
}

/*when navigating through the items using the arrow keys:*/
.autocomplete-active {
	background-color: DodgerBlue !important;
	color: #ffffff;
}
</style>
	
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/datepicker.css">	

	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/estilo.css">	
<div class="modal-dialog modal-lg" role="document">
	<div class="modal-content">
		<div class="modal-header">
			<label class="modal-title text-text-bold-600" id="myModalLabel35"><b>REGISTRO
					DE FORMULA</b></label>
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<f:form id="frmGuardarFormula" name="fo" class="form-horizontal" role="form " 
			method="post" action="" onsubmit="return false">
			<input id="contextPath" type="hidden"
				value="${pageContext.request.contextPath}">

			  
				
			<div class="modal-body" id="buscaFormula" onload="init();">
			
			<div class="row">

					<div class="form-group col-md-4 mb-2">
						<label for="nombreCompleto" class="label_control">EXAMEN PARA FORMULAR
							<span class="required">*</span>
						</label>
						<div class="controls">
							<f:input type="text" class="form-control" required="required"
								
								id="codigoAnalisisFormula" path="codigo" />

						</div>
					</div>
					
					<div class="form-group col-md-4 mb-2">
						<label for="exampleInputName" class="label_control">INGRESE
							ANALISIS A LA CALCULADORA <span class="required">*</span>
						</label>
						<div class="position-relative has-icon-left"> 
							<input id="contextPath" type="hidden">
							<div class="controls">
								<f:input type="text" class="form-control" 
									maxlength="20" id="codigoAnalisisAsociado" path="codigo"
									/>
							</div>
						</div>
					</div> 
				</div>	
			
	<div class="row" >		
			
	<table class="calculadora" >
<tr>
  <td colspan="4" ><input type="text" name="resultado" id="resultado" maxlength="15" onkeypress="return solonumeros(event)"></td>
</tr>
<tr>
  <td><button id="siete" value="7">7</button></td>
  <td><button id="ocho" value="8"  onClick="retornar(value)">8</button></td>
  <td><button id="nueve" value="9" onClick="retornar(value)">9</button>
  </td><td><button id="division" value="/" onclick="comprobar(value)">/</button></td>
</tr>
<tr>
  <td><button id="cuatro" value="4" onClick="retornar(value)">4</button></td>
  <td><button id="cinco" value="5"  onClick="retornar(value)">5</button></td>
  <td><button id="seis" value="6"   onClick="retornar(value)">6</button></td>
  <td><button id="multiplicacion" value="*" onclick="comprobar(value)">*</button></td>
</tr>
<tr>
  <td><button id="uno" value="1" onClick="retornar(value)">1</button></td>
  <td><button id="dos" value="2" onClick="retornar(value)">2</button></td>
  <td><button id="tres" value="3" onClick="retornar(value)">3</button></td>
  <td><button id="resta" value="-" onclick="comprobar(value)">-</button></td>
</tr>
<tr>
  <td><button id="igual">=</button></td>
  <td><button id="reset">C</button></td>
  <td><button id="cero" value="0" onClick="retornar(value)">0</button></td>
  <td><button id="suma" value="+" onclick="comprobar(value)">+</button></td>
</tr>

</table>

</div>	
			
<div class="label_title">
								VALORES REFERENCIALES Y UNIDADES <span class="required">*</span>:
							</div>
							<div id="panelCEX" class="panel_style col-md-12">
								<div class="row">
									<div class="col-md-12 text-right " style="margin-bottom: 20px;">
										<button id="btn-save-reg" type="button" class="btn btn-info"
											onclick="llenarDetalleTarifario()">
											<i class="fa fa-plus"></i> AGREGAR
										</button>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12" id="tblListaDetalle">
										<div class="table-responsive_">
											<table id ="tabla"  class="table table-bordered">
												<thead class="tabla_th">
													<tr> 
														<th>UNIDAD</th>
														<th>OBSERVACION</th>
														<th>VALOR INICIAL</th>
														<th>VALOR FINAL</th>
														<th width="60">ACCION</th>
													</tr>
												</thead>
												<tbody id="idbodyCIEXref" class="label_control">
													<c:forEach var="tabla" items="${lstTarifarioDetalleBean}"
														varStatus="loop">
														<f:input type="hidden"    value="${tabla.codigo}" path="lstTarifarioDetalleBean[${loop.index}].codigo" />
														<tr id='anterior${loop.index}'> 
															<td><div class="controls"> 
																	<f:input type="hidden" min="1" maxlength="20"  required="required"
																		class="form-control" value="${tabla.unidades}"
																		id="tarifarioUnidades${loop.index}" path="lstTarifarioDetalleBean[${loop.index}].unidades" />

																</div></td>
															<td><div class="controls">
																	<f:input type="text" min="1" maxlength="20"
																		class="form-control" value="${tabla.observacion}"
																		id="tarifarioUnidades${loop.index}" path="lstTarifarioDetalleBean[${loop.index}].observacion" />

																</div></td>
															<td><div class="controls">
																	<f:input type="text" maxlength="30"
																		class="form-control" required="required" value="${tabla.valoresRefIni}"
																		id="tarifarioValorInicial${loop.index}" path="lstTarifarioDetalleBean[${loop.index}].valoresRefIni" />

																</div></td>
															<td><div class="controls">
																	<f:input type="text" maxlength="30"
																		class="form-control" required="required" value="${tabla.valoresRefFin}"
																		id="tarifarioValorFinal${loop.index}" path="lstTarifarioDetalleBean[${loop.index}].valoresRefFin" />

																</div></td> 
																	<td><button type='button'
																			class='btn btn-outline-danger btn-sm'
																			data-toggle='tooltip' data-placement='top'
																			title='Eliminar'
																			onclick='confirmar_eliminar(1,${loop.index})'
																			data-original-title='Eliminar' id='eliminarDX'>
																			<i class='icon-trash'></i>
																		</button></td> 
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>			
			
			

			
				
				
			</div>
			<div class="modal-footer">
				<button type="button" id="btnCerrarModalPaciente"
					class="btn btn-secondary" data-dismiss="modal">
					<i class="fa fa-close"></i> CERRAR
				</button>

				<button type="button" onclick="limpiar()" class="btn btn-default">
					<i class="fa fa-eraser"></i> LIMPIAR
				</button>

				<button type="submit" onclick="grabarPersona()"
					class="btn btn-primary">
					<i class="fa fa-floppy-o"></i> GRABAR
				</button>
			</div>



		</f:form>
	</div>
		<script
			src="${pageContext.request.contextPath}/assets/js/page/util/datepicker.js"
			type="text/javascript" charset="utf-8"></script>
			
		<script
			src="${pageContext.request.contextPath}/assets/js/page/util/datepicker.es.min.js"
			type="text/javascript" charset="utf-8"></script>	
			
		<script
			src="${pageContext.request.contextPath}/assets/js/page/util/utilitarios.js"
			type="text/javascript" charset="utf-8"></script>
			
		<!-- 	<script
			src="${pageContext.request.contextPath}/assets/js/page/asistencial/laboratorio/funcionalidad.js"
			type="text/javascript" charset="utf-8"></script>	 -->	
			
	<script> 
	$(document).ready(function(){
		console.log("log");
		init();
	
	});		
			
	function limpiar(){
		  resultado.textContent = "";
		}
	
	function resolver(){
		  var res = 0;
		  switch(operacion){
		    case "+":
		      res = parseFloat(operandoa) + parseFloat(operandob);
		      break;
		    case "-":
		        res = parseFloat(operandoa) - parseFloat(operandob);
		        break;
		    case "*":
		      res = parseFloat(operandoa) * parseFloat(operandob);
		      break;
		    case "/":
		      res = parseFloat(operandoa) / parseFloat(operandob);
		      break;
		  }
		  resetear();
		  resultado.textContent = res;
		}
	
	function resetear(){
	  resultado.textContent = "";
	  operandoa = 0;
	  operandob = 0;
	  operacion = "";
	}
	
	function init(){
		console.log("init"); 
		  var resultado = document.getElementById('resultado');
		  var reset = document.getElementById('reset');
		  var suma = document.getElementById('suma');
		  var resta = document.getElementById('resta');
		  var multiplicacion = document.getElementById('multiplicacion');
		  var division = document.getElementById('division');
		  var igual = document.getElementById('igual');
		  var uno = document.getElementById('uno');
		  var dos = document.getElementById('dos');
		  var tres = document.getElementById('tres');
		  var cuatro = document.getElementById('cuatro');
		  var cinco = document.getElementById('cinco');
		  var seis = document.getElementById('seis');
		  var siete = document.getElementById('siete');
		  var ocho = document.getElementById('ocho');
		  var nueve = document.getElementById('nueve');
		  var cero = document.getElementById('cero');
		  console.log("init22"); 
		  uno.onclick = function(e){
		      resultado.textContent = resultado.textContent  + "1";
		  }
		  dos.onclick = function(e){
		      resultado.textContent = resultado.textContent  + "2";
		  }
		  tres.onclick = function(e){
		      resultado.textContent = resultado.textContent  + "3";
		  }
		  cuatro.onclick = function(e){
		      resultado.textContent = resultado.textContent  + "4";
		  }
		  cinco.onclick = function(e){
		      resultado.textContent = resultado.textContent  + "5";
		  }
		  seis.onclick = function(e){
		      resultado.textContent = resultado.textContent  + "6";
		  }
		  siete.onclick = function(e){
		      resultado.textContent = resultado.textContent  + "7";
		  }
		  ocho.onclick = function(e){
		      resultado.textContent = resultado.textContent  + "8";
		  }
		  nueve.onclick = function(e){
		      resultado.textContent = resultado.textContent  + "9";
		  }
		  cero.onclick = function(e){
		      resultado.textContent = resultado.textContent  + "0";
		  }
		  reset.onclick = function(e){
		      resetear();
		  }
		  
		  suma.onclick = function(e){
		      operandoa = resultado.textContent;
		      resultado.textContent = resultado.textContent  + "+";
		      operacion = "+";
	//	      limpiar();
		  }
		  resta.onclick = function(e){
		      operandoa = resultado.textContent;
		      resultado.textContent = resultado.textContent  + "-";
		      operacion = "-";
	//	      limpiar();
		  }
		  multiplicacion.onclick = function(e){
		      operandoa = resultado.textContent;
		      resultado.textContent = resultado.textContent  + "x";
		      operacion = "*";
	//	      limpiar();
		  }
		  division.onclick = function(e){
		      operandoa = resultado.textContent;
		      resultado.textContent = resultado.textContent  + "/";
		      operacion = "/";
	//	      limpiar();
		  }
		  igual.onclick = function(e){
		      operandob = resultado.textContent;
		      resolver();
		  } 
		}
	function comprobar(num){ 
		console.log("init33");
		//var anterior = document.fo.valores.value;
		var anterior = document.fo.resultado.value;
		if(anterior==""){
		document.fo.resultado.value="";
		}else{    
		    var anterior = document.fo.resultado.value;
		    document.getElementById("resultado").value=anterior+num;
		    esto=document.fo.resultado.value;

		    record=0; 
		    igual=1; 
		    var letraRecord 
		    var b=0 
		    var letra="" 

		    for (a=1;a<esto.length;a++){      
		    if (esto.charAt(a)=="+" || esto.charAt(a)=="-" || esto.charAt(a)=="*" || esto.charAt(a)=="/" || esto.charAt(a)=="."){ 
		    igual=igual+1; 
		    letra=esto.charAt(a);
		    }else{ 
		         if(igual>record){record=igual;letraRecord=letra} 
		            igual=1 
		         } 
		         b=a 
		    }
		    
		    if(igual>record){
		      record=igual;
		      letraRecord=letra;
		    } 

		    if (record>2){
		        var anterior = esto;
		        var nuevovalor = anterior.substring(0, anterior.length-1);
		        document.getElementById("resultado").value=nuevovalor;
		        record=0;b=0;igual=1;letra="";letraRecord="";
		    }     
		} 

		}
	
	function solonumeros(e){
		console.log("init4444");
	    key=e.keyCode || e.which;

	    teclado=String.fromCharCode(key);

	    numeros="0123456789";

	    especiales="8-37-39-46";

	    tecla_especial=false;

	    for(var i in especiales){
	        if(key==especiales[i]){
	            tecla_especial=true;
	        }
	    }

	    if(numeros.indexOf(teclado)==-1 && !tecla_especial){
	        return false;
	    }

	}
	
	function retornar(num){
		console.log("retornar");
	    var anterior=document.fo.resultado.value;

	    document.getElementById("resultado").value=anterior+num;

	}
	
	</script>
	
	
	<script type="text/javascript">
	var borrar = false;
	function deletecarac(){
		var caja2 = document.f1.txtcaja2.value;
            if (caja2 == "" || caja2 == "0" || caja2.length == 1 && blocdel!=true){
            	document.f1.txtcaja2.value = "0";
            }
            else if(blocdel!=true){
             	var res = caja2.substring(0,caja2.length-1);
             	document.f1.txtcaja2.value = res;
            }
	}
   function escribir(n){
		var caja2 = document.f1.txtcaja2.value;
		if (borrar) {
			//alert("se borro");
			document.f1.txtcaja2.value="";
			borrar = false;
			document.f1.txtcaja2.value = n;
		}
		else if (caja2 == "0" && n != "."){
			cajao = caja2.replace("0", "")
			document.f1.txtcaja2.value = cajao + n;
		}
		else{
			document.f1.txtcaja2.value = caja2 + n;
		}
	}
	function raiz(){
		var caja1 = document.f1.txtcaja1.value;
		var caja2 = document.f1.txtcaja2.value;
		document.f1.txtcaja1.value = "Math.sqrt("+ caja2 + caja1 +")";
		document.f1.txtcaja2.value = "";
	}
	function arit(o){
		var caja1 = document.f1.txtcaja1.value;
		var caja2 = document.f1.txtcaja2.value;
		var unum = caja1.substring(caja1.length-1);
		calcular()
		if (unum == "+" || unum == "-" || unum=="*" || unum=="/") {
			unum = unum.replace(unum,o);
			var res = caja1.substring(0,caja1.length-1);
			document.f1.txtcaja1.value = res+unum;
		}
		if (caja1 == "" && caja2 != ""){
			document.f1.txtcaja1.value = caja2 + o;
		}
		else{
			document.f1.txtcaja1.value = caja1 + caja2 + o;
 		}
 		borrar = true;
	}
	function calcular(){
		var caja1 = document.f1.txtcaja1.value;
		var caja2 = document.f1.txtcaja2.value;
		document.f1.txtcaja2.value = eval(caja1 + caja2);
		document.f1.txtcaja1.value = "";
		borrar = true;
		blocdel = true;
	}
	function masmenos(){
		var caja2 = document.f1.txtcaja2.value;
		if (caja2 > 0){
			document.f1.txtcaja2.value = "(-" + caja2 + ")";
		}
		else{
			cajaplus = caja2.replace(/[-|(|)]/g, "");
			document.f1.txtcaja2.value = cajaplus;
		}
	}
	
	//para  validar los signos

	
</script>
	
	
   
  
	 
</div>


