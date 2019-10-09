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

		<f:form id="frmGuardarFormula" class="form-horizontal" role="form " 
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
			
	<div class="row">		
			
<h1>Calculadora para formular</h1>
<form name="f1"><br>
<input type="text" name="txtcaja1">
<br>
<input type="text" name="txtcaja2" value="0">
<br>
<input type="button" class="arit" onClick="arit('%')" value="%">
<input type="button" onClick="raiz()" value="√">
<input type="button" onClick="escribir('7')" value="x²">
<input type="button" onClick="escribir('7')" value="¹/×">
<br>
<input type="button" class="clear" onClick="document.f1.txtcaja2.value='0'" value="CE">
<input type="button" class="clear" onClick="document.f1.reset(); blocdel = false;" value="C">
<input type="button" class="clear" onClick="deletecarac()" value="◄">
 
<input type="button" class="arit" onClick="arit('/')" value="÷">
<br>
<input type="button" onclick="escribir(this.value)" value="7">
<input type="button" onclick="escribir(this.value)" value="8">
<input type="button" onclick="escribir(this.value)" value="9">
<input type="button" class="arit" onClick="arit('*')" value="×">
<br>
<input type="button" onclick="escribir(this.value)" value="4">
<input type="button" onclick="escribir(this.value)" value="5">
<input type="button" onclick="escribir(this.value)" value="6">
<input type="button" class="arit" onClick="arit('-')" value="-">
<br>
<input type="button" onclick="escribir(this.value)" value="1">
<input type="button" onclick="escribir(this.value)" value="2">
<input type="button" onclick="escribir(this.value)" value="3">
<input type="button" class="arit" onClick="arit('+')" value="+">
<br>
<input type="button" class="arit" onClick="masmenos()" value="±">
<input type="button" onclick="escribir(this.value)" value="0">
<input type="button" onClick="escribir('.')" value=".">
<input type="button" class="igual" onClick="calcular()" value="=">
<br>
</form>

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
</script>
	
	
   
  
	 
</div>


