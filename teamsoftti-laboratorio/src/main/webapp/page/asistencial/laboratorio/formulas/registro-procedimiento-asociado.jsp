<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="es" data-textdirection="ltr" class="loading">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description"
	content="Stack admin is super flexible, powerful, clean &amp; modern responsive bootstrap 4 admin template with unlimited possibilities.">
<meta name="author" content="">
<title>Mantenimiento de formulas</title>
<!-- Bootstrap core CSS-->

<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i%7COpen+Sans:300,300i,400,400i,600,600i,700,700i"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/fonts/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/css/plugins/forms/validation/form-validation.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/css/plugins/forms/switch.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/vendors/css/extensions/toastr.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/css/plugins/forms/extended/form-extended.css">
<link
	href="${pageContext.request.contextPath}/app-assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath}/app-assets/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Page level plugin CSS-->
<!-- Custom styles for this template-->
<link
	href="${pageContext.request.contextPath}/app-assets/css/sb-admin.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/app-assets/css/estilos-cayetano.css"
	rel="stylesheet">

<link rel="shortcut icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/app-assets/images/ico/logoLabico.ico">
<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath}/app-assets/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Page level plugin CSS-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/vendors/css/forms/selects/select2.min.css">
<!-- Custom styles for this template-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/fonts/simple-line-icons/style.min.css">


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/fonts/feather/style.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/fonts/font-awesome/css/font-awesome.min.css">


</head>

<style>
body {
	font-family: Cambria;
	font-size: 13px;
}

#datepicker {
	width: 180px;
	margin: 0 20px 20px 20px;
}

#datepicker>span:hover {
	cursor: pointer;
}

#txtCajaImporteTotal:disabled {
	font-color: #0A0A0A;
	font-weight: plain;
	font-family: Cambria;
	font-size: 16px;
	background-color: #DCE8EC;
	text-align: right;
	valign: center;
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

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		id="mainNav">
		<a class="navbar-brand label_control_hade" href="#"> LABMED </a>
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>


		<div class="collapse navbar-collapse" id="navbarResponsive">
			<jsp:include
				page="${pageContext.request.contextPath}/../layout/menu-view.jsp" />
		</div>
	</nav>

	<div class="content-wrapper">
		<div class="container-fluid">
			<div class="tituloForm">MANTENIMIENTO DE FORMULAS</div>
			<div class="card mb-3">
				<f:form id="frmGuardarOrden" class="form-horizontal" role="form"
					method="post" action="" onsubmit="return false">
					<input id="contextPath" type="hidden"
						value="${pageContext.request.contextPath}">
					<f:input type="hidden" id="codigo" path="codigo" />
					<f:input type="hidden" id="numeroVersion" path="numeroVersion" />
					<f:input type="hidden" id="numeroPeriodo" path="numeroPeriodo" />
					<f:input type="hidden" id="codigoOrganizacion"
						path="codigoOrganizacion" />
					<f:input type="hidden" id="codigoSede" path="codigoSede" />
					<f:input type="hidden" id="codigoInstitucion"
						path="codigoInstitucion" />

					<div class="card-body">
						<div class="form-group">





							<div class="label_title">
								EXÁMEN(ES) <span class="required">*</span>:
							</div>
							<div id="panelCEX" class="panel_style col-md-12">

								<div class="row">
									<div class="col-md-8 mb-3">
										<label for="nombreCompleto" class="label_control">BUSCAR
											EXAMEN </label>
										<div class="controls">
											<div class="autocomplete" style="width: 100%;">
												<input type="text" value="" placeholder="Buscar..."
													class="form-control" id="txtExamenNombre"
													name="txtExamenNombre" />
											</div>
										</div>
									</div>

								</div>




							</div>
						</div>

						<div class="label_title">
							INGRESAR EXÁMENES ASOCIADOS <span class="required">*</span>:
						</div>
						<div id="panelCEX" class="panel_style col-md-12">
							<f:form id="frmGuardarAsociados" class="form-horizontal"
								role="form" method="post" action="" onsubmit="return false">
								<div class="row">
									<div class="col-md-12 text-right " style="margin-bottom: 20px;">
										<button id="btn-save-reg" type="button" class="btn btn-info"
											onclick="llenarDetalleExamenAsociado()">
											<i class="fa fa-plus"></i> AGREGAR
										</button>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12" id="tblListaDetalle">
										<div class="table-responsive_">
											<table id="tablaExamenAsociado" class="table table-bordered">
												<thead class="tabla_th">
													<tr>
														<th>EXAMEN ASOC.</th>
														<th width="100">ABREV</th>
														<th>UNIDAD DE MEDIDA</th>
														<th width="60">ACCION</th>
													</tr>
												</thead>
												<tbody id="idbodyAsociado" class="label_control">
													<c:forEach var="tabla" items="${lstTarifarioDetalleBean}"
														varStatus="loop">
														<f:input type="hidden" value="${tabla.codigo}"
															path="lstTarifarioDetalleBean[${loop.index}].codigo" />
														<tr id='anterior${loop.index}'>
															<td><div class="controls">
																	<f:input type="hidden" min="1" maxlength="20"
																		required="required" class="form-control"
																		value="${tabla.unidades}"
																		id="examenAsociado${loop.index}"
																		path="lstTarifarioDetalleBean[${loop.index}].unidades" />

																</div></td>
															<td><div class="controls">
																	<f:input type="text" min="1" maxlength="20"
																		class="form-control" value="${tabla.observacion}"
																		id="abreviatura${loop.index}"
																		path="lstTarifarioDetalleBean[${loop.index}].observacion" />

																</div></td>
															<td><div class="controls">
																	<f:input type="text" maxlength="30"
																		class="form-control" required="required"
																		value="${tabla.valoresRefIni}"
																		id="unidadMedida${loop.index}"
																		path="lstTarifarioDetalleBean[${loop.index}].valoresRefIni" />

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
								<div class="row">
									<div class="form-group col-md-12 text-right"
										style="margin-top: 15px;">
										<button type="submit" onclick="grabarAsociados()"
											<c:choose>
									<c:when test="${ordenBean.codigo==null || ordenBean.codigo==''}"> 
									</c:when>
									<c:otherwise>
										disabled ="true"
									</c:otherwise>
								</c:choose>
											class="btn btn-primary">
											<i class="fa fa-floppy-o"></i> GUARDAR
										</button>
									</div>
								</div>
							</f:form>
						</div>

						<div class="label_title">
							INGRESAR EXÁMENES PARA FORMULAR <span class="required">*</span>:
						</div>

						<div id="panelCEX" class="panel_style col-md-12">
							<div class="row">
								<div class="col-md-12 text-right " style="margin-bottom: 20px;">
									<button id="btn-save-reg" type="button" class="btn btn-info"
										onclick="llenarDetalleExamenFormula()">
										<i class="fa fa-plus"></i> AGREGAR
									</button>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12" id="tblListaDetalle">
									<div class="table-responsive_">
										<table id="tablaFormulas" class="table table-bordered">
											<thead class="tabla_th">
												<tr>
													<th>EXAMEN.</th>
													<th width="100">ABREV</th>
													<th>UNIDAD DE MEDIDA</th>
													<th width="60">ACCION</th>
												</tr>
											</thead>
											<tbody id="idbodyFormulas" class="label_control">
												<c:forEach var="tabla" items="${lstTarifarioDetalleBean}"
													varStatus="loop">
													<f:input type="hidden" value="${tabla.codigo}"
														path="lstTarifarioDetalleBean[${loop.index}].codigo" />
													<tr id='anterior${loop.index}'>
														<td><div class="controls">
																<f:input type="hidden" min="1" maxlength="20"
																	required="required" class="form-control"
																	value="${tabla.unidades}"
																	id="tarifarioUnidades${loop.index}"
																	path="lstTarifarioDetalleBean[${loop.index}].unidades" />

															</div></td>
														<td><div class="controls">
																<f:input type="text" min="1" maxlength="20"
																	class="form-control" value="${tabla.observacion}"
																	id="tarifarioUnidades${loop.index}"
																	path="lstTarifarioDetalleBean[${loop.index}].observacion" />

															</div></td>
														<td><div class="controls">
																<f:input type="text" maxlength="30" class="form-control"
																	required="required" value="${tabla.valoresRefIni}"
																	id="tarifarioValorInicial${loop.index}"
																	path="lstTarifarioDetalleBean[${loop.index}].valoresRefIni" />

															</div></td>
														<td><button type='button'
																class='btn btn-outline-danger btn-sm'
																data-toggle='tooltip' data-placement='top'
																title='Eliminar'
																onclick='confirmar_eliminar(1,${loop.index})'
																data-original-title='Eliminar' id='eliminarDX'>
																<i class='icon-trash'></i>
															</button></td>
														<td><button type='button'
																class='btn btn-outline-danger btn-sm'
																data-toggle='tooltip' data-placement='top'
																title='Formular'
																onclick='confirmar_eliminar(1,${loop.index})'
																data-original-title='Formular' id='formular'>
																<i class='icon-trash'></i>
															</button></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-md-12 text-right"
									style="margin-top: 15px;">
									<button type="submit" onclick="grabarFormulas()"
										class="btn btn-primary">
										<i class="fa fa-floppy-o"></i> GUARDAR
									</button>
								</div>
							</div> 
						</div>
						<div class="row">
								<div class="form-group col-md-12 text-right"
									style="margin-top: 15px;">
									<a
									href="${pageContext.request.contextPath}/formulaController/nuevaFormula"
									class="btn btn-secondary" title=""> <i class="fa icon-plus"></i>
									NUEVO
								</a>
								</div>
							</div>
					</div>

					<input type="hidden" id="txtCodigoExamen" />

				</f:form>
				<f:form id="frmRegistro" class="form-horizontal" role="form"
					method="POST"
					action="${pageContext.request.contextPath}/formulaController/listado">
					<button type="submit" style="display: none" id="btnListado">Click
						me</button>
				</f:form>
			</div>

			<div class="modal fade text-xs-left" id="modalFormula" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel35" data-dismiss="modal"
				aria-hidden="true" aria-hidden="true">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content" id="modalFormulaContent"></div>
				</div>
			</div>

			<div class="modal fade text-xs-left" id="modalPersona" tabindex="-2"
				role="dialog" aria-labelledby="myModalLabel35" data-dismiss="modal"
				aria-hidden="true" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content" id="modalPersonaContent"></div>
				</div>
			</div>

			<div class="modal fade text-xs-left" id="md_confirmacion"
				tabindex="-1" role="dialog" aria-labelledby="myModalLabel19"
				aria-hidden="true">
				<div class="modal-dialog modal-sm" role="document">
					<div class="modal-content">
						<div class="label_title_modal modal-header">

							<h4 class="label_title" id="myModalLabel19">CONFIRMAR ACCION</h4>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<p id="txt_confir" class="label_control">¿ESTÁ SEGURO DE
								ELIMINAR REGISTRO SELECCIONADO?</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn grey btn-outline-secondary"
								data-dismiss="modal">Cerrar</button>
							<button id="btnConfirmarGeneric" type="button"
								class="btn btn-outline-primary">Confirmar</button>
						</div>
					</div>
				</div>
			</div>
			<!-- Example DataTables Card-->

		</div>
		<!-- /.container-fluid-->
		<!-- /.content-wrapper-->
		<jsp:include
			page="${pageContext.request.contextPath}/../layout/footer-view.jsp" />
		<!-- Scroll to Top Button-->
		<a class="scroll-to-top rounded" href="#page-top"> <i
			class="fa fa-angle-up"></i>
		</a>
		<!-- Page level plugin JavaScript-->
		<!-- Custom scripts for all pages-->

		<!-- Custom scripts for this page-->

		<!-- Core plugin JavaScript-->
		<script
			src="${pageContext.request.contextPath}/assets/js/page/util/utilitarios.js"
			type="text/javascript" charset="utf-8"></script>
		<!-- Page level plugin JavaScript-->
		<!-- Bootstrap core JavaScript-->
		<script
			src="${pageContext.request.contextPath}/app-assets/vendor/jquery/jquery.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/app-assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
		<!-- Core plugin JavaScript-->
		<script
			src="${pageContext.request.contextPath}/app-assets/vendor/jquery-easing/jquery.easing.min.js"></script>
		<!-- Page level plugin JavaScript-->
		<script
			src="${pageContext.request.contextPath}/app-assets/vendor/datatables/jquery.dataTables.js"></script>
		<script
			src="${pageContext.request.contextPath}/app-assets/vendor/datatables/dataTables.bootstrap4.js"></script>
		<!-- Custom scripts for all pages-->
		<script
			src="${pageContext.request.contextPath}/app-assets/js/sb-admin.min.js"></script>
		<!-- Custom scripts for this page-->
		<!-- Custom scripts for all pages-->
		<script src="http://malsup.github.io/jquery.blockUI.js"></script>

		<script
			src="${pageContext.request.contextPath}/app-assets/vendors/js/extensions/toastr.min.js"
			type="text/javascript"></script>

		<script
			src="${pageContext.request.contextPath}/app-assets/vendors/js/extensions/sweetalert.min.js"
			type="text/javascript"></script>
		<script
			src="${pageContext.request.contextPath}/app-assets/js/scripts/extensions/sweet-alerts.js"
			type="text/javascript"></script>
		<script
			src="${pageContext.request.contextPath}/assets/js/page/asistencial/formulas.js"
			type="text/javascript" charset="utf-8"></script>

		<script src="${pageContext.request.contextPath}/assets/js/scripts.js"
			type="text/javascript"></script>

		<!-- Include Date Range Picker -->

		<script type="text/javascript"
			src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>

		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/locales/bootstrap-datepicker.es.min.js"></script>



		<script type="text/javascript">  
  
function runIngresarExamen(e) {
	var index = $('#txtCodigoExamen').val();
	var examenNombre = $('#txtExamenNombre').val();
	console.log("examenNombre runIngresarExamen" + examenNombre);
	console.log("index runIngresarExamen" + index);
	console.log("e.keyCode:::" +e.keyCode);
	if (e.keyCode == 13) {
		if(examenNombre != ''){
			llenarExamenIndex(index);
			return false;
		} 
	}
}

function enviarIndex() { 
	var index = $('#txtCodigoExamen').val();
	var examenNombre = $('#txtExamenNombre').val();
	console.log("examenNombre" + examenNombre);
	console.log("index" + index);
		if(examenNombre != ''){
			llenarExamenIndex(index);
			return false;
		}  
}

	function runScript(e) {
		//See notes about 'which' and 'key'
		console.log(${ordenBean.codigo});
		if (e.keyCode == 13) {
			<c:if test="${ordenBean.codigo==null || ordenBean.codigo==''}">
			buscarPersonaNroDoc(); 
			
			</c:if>
			
			return false;
		}
	} 
		var  listadoExamenes= []; 
 </script>
		<script>
		function autocomplete(inp, arr) { 
			var currentFocus;
			var codigoRegistro; 
			inp
					.addEventListener(
							"input",
							function(e) {
								var a, b, i, val = this.value; 
								closeAllLists();
								if (!val) {
									return false;
								}
								currentFocus = -1; 
								a = document.createElement("DIV");
								a.setAttribute("id", this.id
										+ "autocomplete-list");
								a.setAttribute("class", "autocomplete-items"); 
								this.parentNode.appendChild(a); 
								for (i = 0; i < arr.length; i++) { 
									if ( arr[i].detalle
											.toUpperCase().includes(val.toUpperCase()) ) {  
										b = document.createElement("DIV"); 
										b.innerHTML = "<strong>"
												+ arr[i].detalle.substr(0, val.length)
												+ "</strong>";
										b.innerHTML += arr[i].detalle
												.substr(val.length); 
										b.innerHTML += "<input type='hidden' id='" + arr[i].codigoRegistro + "' value='" + arr[i].detalle + "'>";  
										b
												.addEventListener(
														"click",
														function(e) {
															console.log("codigo::" +(this
																	.getElementsByTagName("input")[0].id));	
															
															llamarDatos(this
															.getElementsByTagName("input")[0].id);
															
								  							inp.value = this
																	.getElementsByTagName("input")[0].value;
															$("#txtCodigoExamen").val(this
																	.getElementsByTagName("input")[0].id); 
															closeAllLists();
														});
										a.appendChild(b);
									}
								}
							}); 
			inp.addEventListener("keydown", function(e) {
				var x = document.getElementById(this.id + "autocomplete-list");
				if (x)
					x = x.getElementsByTagName("div");
				if (e.keyCode == 40) { 
					currentFocus++; 
					addActive(x);
				} else if (e.keyCode == 38) { //up 
					currentFocus--; 
					addActive(x);
				} else if (e.keyCode == 13) { 
					e.preventDefault();
					if (currentFocus > -1) { 
						if (x)
							x[currentFocus].click();
					}
				}
			});
			function addActive(x) { 
				if (!x)
					return false; 
				removeActive(x);
				if (currentFocus >= x.length)
					currentFocus = 0;
				if (currentFocus < 0)
					currentFocus = (x.length - 1); 
				x[currentFocus].classList.add("autocomplete-active");
			}
			function removeActive(x) { 
				for (var i = 0; i < x.length; i++) {
					x[i].classList.remove("autocomplete-active");
				}
			}
			function closeAllLists(elmnt) { 
				var x = document.getElementsByClassName("autocomplete-items");
				for (var i = 0; i < x.length; i++) {
					if (elmnt != x[i] && elmnt != inp) {
						x[i].parentNode.removeChild(x[i]);
					}
				}
			} 
			document.addEventListener("click", function(e) {
				closeAllLists(e.target);
			});
		}
		
		var arrayMenus = [];
		
		
		<c:forEach var="examen" items="${lstTarifarioBean}"
			varStatus="loop">
		var objUbigeo = {
				codigoRegistro : "",
				detalle		: ""
		  	};
			objUbigeo.codigoRegistro ='${examen.codigo}'; 
			objUbigeo.detalle ='${examen.descripcion}'; 
			  arrayMenus.push(objUbigeo);
		</c:forEach>
		 
		autocomplete(document.getElementById("txtExamenNombre"), arrayMenus);
	
		
		
		</script>
	</div>
</body>


</html>










