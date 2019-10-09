<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Atender Orden</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/app-assets/bower_components/bootstrap/dist/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/app-assets/bower_components/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/app-assets/bower_components/Ionicons/css/ionicons.min.css">
<!-- jvectormap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/app-assets/bower_components/jvectormap/jquery-jvectormap.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/app-assets/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/app-assets/dist/css/skins/_all-skins.min.css">

<!-- DataTables -->

<link
	href="${pageContext.request.contextPath}/app-assets/css/estilos.css"
	rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/vendors/css/extensions/toastr.css">

<!-- Google Font -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

</head>


<style>
 

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


<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<jsp:include
			page="${pageContext.request.contextPath}/../layout/head-nav-view.jsp" />


		<jsp:include
			page="${pageContext.request.contextPath}/../layout/menu-view.jsp" />

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">GENERAR ORDEN LABORATORIO</section>

			<!-- Main content -->
			<section class="content">
				<div class="box box-primary">
					<!-- /.box-header -->
					<!-- form start -->
					<f:form id="frmActualizarResultadoOrden" class="form-horizontal" role="form"
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
						<f:input type="hidden" id="personaCodigo"
							path="pacienteBean.persona.codigo" />

						<!-- Begin Page Content -->
						<div class="container-fluid">
							<div class="card shadow mb-2">
								<div class="collapse show" id="collapseCardExample">
									<div class="card-body">
										<div class="row">
											</br>
											<div class="col-xs-6 mb-3">
												<div class="label_title">DATOS DEL SOLICITANTE :</div>
											</div>

										</div>
										</br>
										<div class="form-group row">
											<div class=" col-xs-3">
												<label for="situacion" class="label_control">TIPO
													DOCUMENTO <span class="required">*</span>
												</label>
												<div class="controls">
													<f:select id="tipoDocumentoPaciente"
														path="pacienteBean.persona.tipoDocumento.codReg"
														required="required" class="form-control"
														onchange="limpiarPorTipo()">
														<f:options items="${lstDocumento}" itemValue="codReg"
															itemLabel="nombreCorto" />
													</f:select>
												</div>
											</div>
											<div class=" col-xs-3">
												<label for="exampleInputName" class="label_control">N°
													DOCUMENTO <span class="required">*</span>
												</label>
												<div class="position-relative has-icon-left">
													<input id="contextPath" type="hidden"
														value="${pageContext.request.contextPath}">
													<div class="controls">
														<f:input type="text" class="form-control"
															required="required" maxlength="12"
															id="nroDocumentoPaciente" autocomplete="off"
															path="pacienteBean.persona.nroDocumento"
															onkeypress="return runScript(event)" />
													</div>
												</div>
											</div>

											<div class="col-md-6 mb-1">
												<label for="nombreCompleto" class="label_control">APELLIDOS
													Y NOMBRES </label>
												<div class="controls">
													<f:input type="text" class="form-control"
														required="required"
														onkeyup="javascript:this.value=this.value.toUpperCase();"
														id="personaApellidoPaterno" disabled="true"
														path="pacienteBean.persona.nombreCompleto" />

												</div>
											</div>
										</div>
										<div class="form-group row"> 
											<div class="col-xs-2 mb-1">
												<label for="situacion" class="label_control">SEXO </label>
												<div class="controls">
													<f:select id="sexoPaciente"
														path="pacienteBean.persona.sexo.codReg" disabled="true"
														class="form-control" required="required">
														<f:option value="" label="Seleccionar" selected="true"
															disabled="disabled" />
														<f:options items="${lstSexo}" itemValue="codReg"
															itemLabel="nombreCorto" />
													</f:select>
												</div>
											</div>
											<div class="col-xs-2 mb-1">
												<label for="nombreCompleto" class="label_control">EDAD
												</label>
												<div class="controls">
													<f:input type="text" class="form-control"
														required="required" disabled="true" id="edadPersona"
														path="pacienteBean.persona.edad" />

												</div>
											</div>
											<div class="col-xs-3 mb-2">
												<label for="nombreCompleto" class="label_control">DIRECCIÓN
												</label>
												<div class="controls">
													<f:input type="text" class="form-control"
														required="required" disabled="true" id="personaDireccion"
														path="pacienteBean.persona.direccion" />

												</div>
											</div>
										</div>

										<div class="label_title">
											ORDEN(ES) <span class="required">*</span>:
										</div>
										<div id="panelCEX" class="panel_style col-md-12">
						
								<div class="row">
									<div class="col-md-12">
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead class="tabla_th">
													<tr>
														<th width="30">ITEM</th>
														<th>DESCRIPCION</th>
														<th>TIPO</th>
														<th width="45">CANTIDAD</th>
														<th>PRECIO UNITARIO</th>
														<th>IMPORTE</th>
														<th>RESULTADO</th>
														<th>ACCION</th>
													</tr>
												</thead>
												<tbody id="idbodyCIEXref" class="label_control">
													<c:forEach var="orden" items="${lstOrdenDetalleBean}"
														varStatus="loop">
					<f:input type="hidden" id="codigo" value="${orden.codigo}" path="lstOrdenDetalleBean[${loop.index}].codigo" />
					
					<f:input type="hidden" id="numeroVersion" value="${orden.numeroVersion}" path="lstOrdenDetalleBean[${loop.index}].numeroVersion" />
					<f:input type="hidden" id="numeroPeriodo" value="${orden.numeroPeriodo}" path="lstOrdenDetalleBean[${loop.index}].numeroPeriodo" />
					<f:input type="hidden" id="codigoOrganizacion" value="${orden.codigoOrganizacion}" path="lstOrdenDetalleBean[${loop.index}].codigoOrganizacion" />
					<f:input type="hidden" id="codigoSede" value="${orden.codigoSede}" path="lstOrdenDetalleBean[${loop.index}].codigoSede" />
					<f:input type="hidden" id="codigoInstitucion" value="${orden.codigoInstitucion}" path="lstOrdenDetalleBean[${loop.index}].codigoInstitucion" />
														<tr>
															<td>${loop.count}</td>
															<td>${orden.examen.descripcion}</td>
															<td>${orden.examen.tipo.nombreCorto}</td>
															<td>${orden.cantidad}</td>
															<td>${orden.examen.sPrecio}</td>
															<td>${orden.sImporte}</td>
															<td>   
															<c:choose>
															<c:when test="${orden.resultado==null || orden.resultado==''}">
															
																	<f:input type="text" min="1" maxlength="50"  autocomplete="off"
																		class="form-control" required="required"
																		id="tarifarioUnidades" path="lstOrdenDetalleBean[${loop.index}].resultado" />
																
																
																</c:when> 
															<c:when test="${orden.codigoTarifProc==null || orden.codigoTarifProc==''}">
															
																	<f:input type="text" value="${orden.resultado}" min="1" maxlength="50"
																		class="form-control" required="required" disabled="true"
																		id="codigoTarifProc" path="lstOrdenDetalleBean[${loop.index}].resultado" />
																
																
																</c:when> 	
																
																<c:otherwise>
																${orden.resultado}
																</c:otherwise>
																</c:choose>
															</td> 
															<td>
																<button type='button'
																	class='btn btn-success btn-sm'
																	data-toggle='tooltip' data-placement='top'
																	title='Agregar Resultado'
																	onclick='cargarResultadoAnalisisModal(${loop.index})'
																	data-original-title='Agregar' id='agregarResultado'>
																	<i class='fa fa-plus-circle'></i>
																</button> 
															</td>  
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									
								</div>
							
								<div class="form-group row">
									<div class=" col-md-9 text-right"
										style="margin-top: 2px;"></div>
									<div class="form-group col-md-3 text-right"
										style="margin-top: 2px;">
										<label for="nombreCompleto" class="label_control">IMPORTE
											TOTAL S/. </label>
										<f:input type="text" style="text-align:right"  path="sImporteTotal" class="form-control"
											id="txtCajaImporteTotal" readonly="true" maxlength="10" />

										<f:input type="hidden" path="importeTotal"
											id="txtCajaImporteTotalHidden" />
									</div>
								</div>
								
							</div>
									</div>
									<div class=" row">
										 <div class="form-group col-md-12 text-right" 
								style="margin-top: 15px;">
										<button type="submit" onclick="actualizarResultadoOrdenDetalle()"
										<c:choose>
										   <c:when test="${ordenBean.situacion.codReg=='000001'}"> 
										</c:when>
										<c:otherwise>
											disabled ="true"
										</c:otherwise>
									</c:choose>
									class="btn btn-primary">
									<i class="fa fa-floppy-o"></i> GUARDAR RESULTADOS
								</button>
								<a
									href="${pageContext.request.contextPath}/ordenController/nuevo"
									class="btn btn-info" title=""> <i class="fa fa-file"></i>
									NUEVO
								</a>
									<a
									href="#" onclick="cargarEmailModal()"
									class="btn bg-navy" title=""> <i class="fa fa-envelope-open-o"></i>
									CORREO
								</a>
								
									<a
									href="${pageContext.request.contextPath}/ordenController/rptFichaREsultados"
									class="btn bg-navy" title=""> <i class="fa fa-print"></i>
									IMPRIMIR
								</a>
							

							
							</div>
									</div>
								</div>
							</div>

							<!-- /.box-body -->
						</div>

					</f:form>

				</div>
			</section>
			<!-- /.content -->

		</div>
		<jsp:include
			page="${pageContext.request.contextPath}/../layout/footer-view.jsp" />
	</div>
	<input type="hidden" id="txtIndexExamen" />

	<f:form id="frmRegistro" class="form-horizontal" role="form"
		method="POST"
		action="${pageContext.request.contextPath}/ordenController/listado">
		<button type="submit" style="display: none" id="btnListado">Click
			me</button>
	</f:form>
		<div class="modal fade text-xs-left" id="modalAnalisis" tabindex="-2"
				role="dialog" aria-labelledby="myModalLabel35" data-dismiss="modal"
				aria-hidden="true" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content" id="modalAnalisisContent"></div>
				</div>
			</div>

			<div class="modal fade text-xs-left" id="modalTarifario"
				tabindex="-1" role="dialog" aria-labelledby="myModalLabel35"
				data-dismiss="modal" aria-hidden="true" aria-hidden="true">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content" id="modalEstablecimientoContent"></div>
				</div>
			</div>

			<div class="modal fade text-xs-left" id="modalPersona" tabindex="-2"
				role="dialog" aria-labelledby="myModalLabel35" data-dismiss="modal"
				aria-hidden="true" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content" id="modalPersonaContent"></div>
				</div>
			</div>
				<div class="modal fade text-xs-left" id="modalEmail" tabindex="-2"
				role="dialog" aria-labelledby="myModalLabel35" data-dismiss="modal"
				aria-hidden="true" aria-hidden="true">
				<div class="modal-dialog-centered" role="document">
					<div class="modal-content" id="modalEmailContent"></div>
				</div>
			</div>
					<div class="modal fade text-xs-left" id="modalResuladoAnalisis" tabindex="-2"
				role="dialog" aria-labelledby="myModalLabel35" data-dismiss="modal"
				aria-hidden="true" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content" id="modalResuladoAnalisisContent"></div>
				</div>
			</div>

	<!-- /.content-wrapper -->


	<!-- Control Sidebar -->

	<jsp:include
		page="${pageContext.request.contextPath}/../layout/confirmacion-modal-view.jsp" />

	<!-- ./wrapper -->
	<!-- jQuery 3 -->
	<script
		src="${pageContext.request.contextPath}/app-assets/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script
		src="${pageContext.request.contextPath}/app-assets/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script
		src="${pageContext.request.contextPath}/app-assets/bower_components/fastclick/lib/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script
		src="${pageContext.request.contextPath}/app-assets/dist/js/adminlte.min.js"></script>
	<!-- Sparkline -->
	<!-- SlimScroll -->
	<!-- ChartJS -->
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<!-- AdminLTE for demo purposes -->
	<script
		src="${pageContext.request.contextPath}/app-assets/dist/js/demo.js"></script>
	<!-- FastClick -->
	<script
		src="${pageContext.request.contextPath}/assets/js/page/util/block.js"
		type="text/javascript" charset="utf-8"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/page/util/utilitarios.js"
		type="text/javascript" charset="utf-8"></script>
	<script
		src="${pageContext.request.contextPath}/app-assets/vendors/js/extensions/toastr.min.js"
		type="text/javascript"></script>

	<script
		src="${pageContext.request.contextPath}/app-assets/vendors/js/extensions/sweetalert.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/app-assets/js/scripts/extensions/sweet-alerts.js"
		type="text/javascript"></script>

	<script src="${pageContext.request.contextPath}/assets/js/scripts.js"
		type="text/javascript"></script> 
 	<script
			src="${pageContext.request.contextPath}/assets/js/page/asistencial/laboratorio.js"
			type="text/javascript" charset="utf-8"></script>
	    <script
			src="${pageContext.request.contextPath}/assets/js/page/asistencial/formulas.js"
			type="text/javascript" charset="utf-8"></script>   
</body>
</html>
