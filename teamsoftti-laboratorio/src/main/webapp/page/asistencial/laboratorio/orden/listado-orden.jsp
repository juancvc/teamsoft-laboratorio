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

<title>Listado Orden</title>
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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/app-assets/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">

<!-- Google Font -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/datepicker.css">
	
	
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/app-assets/vendors/css/extensions/toastr.css">	
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<jsp:include
			page="${pageContext.request.contextPath}/../layout/head-nav-view.jsp" />


		<jsp:include
			page="${pageContext.request.contextPath}/../layout/menu-view.jsp" />

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">LISTADO DE ARTICULOS</section>

			<!-- Main content -->
			<section class="content">
				<div class="box box-primary">
					<!-- /.box-header -->
					<!-- form start -->
					<f:form id="frmlistadoReferencia" role="form"
					action="${pageContext.request.contextPath}/ordenController/buscar">
						<!-- Begin Page Content -->
						<div class="container-fluid">
							<div class="card shadow mb-2">
								<div class="collapse show" id="collapseCardExample">
									<div class="card-body">
							</br>
							 <div class="form-group row">
								<div class="col-md-4">
									<label for="exampleInputName" class="label_control">FECHA
										ORDEN DESDE </label>
									<div class="position-relative has-icon-left">
										<input id="contextPath" type="hidden"
											value="${pageContext.request.contextPath}">
										<div class="controls">
											<f:input class="form-control" id="date" name="date"
												maxlength="10" placeholder="DD/MM/YYYY" type="text"
												value="${fechaDesde}" path="fechaDesde"
												onkeyup="this.value=formateafecha(this.value);" />

										</div>
									</div>
								</div>
								<div class="col-md-4">
									<label for="exampleInputName" class="label_control">FECHA
										ORDEN HASTA </label>
									<div class="controls">
										<f:input class="form-control" id="dateHasta" name="dateHasta"
											maxlength="10" placeholder="DD/MM/YYYY" type="text"
											value="${fechaDesde}" path="fechaHasta"
											onkeyup="this.value=formateafecha(this.value);" />
									</div>
								</div>
								<div class="col-md-4">
									<label for="situacion" class="label_control">SITUACION
									</label>
									<div class="controls">
										<f:select id="documento" path="situacion.codReg"
											class="form-control">
											<f:option value="" label="Seleccionar" selected="true"
												disabled="disabled" />
											<f:options items="${lstSituacion}" itemValue="codReg"
												itemLabel="nombreCorto" />
										</f:select>
									</div>
								</div>
							</div>
										<div class="form-group row">
											<div class="form-group col-md-12 text-right"
												style="margin-top: 15px;">
												<button id="btnBuscar" class="btn btn-success" type="submit">
													<i class="fa fa-search"> </i> BUSCAR
												</button>
												<button
													onclick="limpiarForm();$('#dataTable').dataTable().fnClearTable();"
													type="button" class="btn btn-default">
													<i class="fa fa-eraser"></i> LIMPIAR
												</button>
												<a
													href="${pageContext.request.contextPath}/ordenController/nuevo"
													class="btn btn-primary"> <i class="fa fa-file"></i> <span
													class="text"> NUEVO</span>
												</a>
											</div>
										</div>
									</div>
								</div>
							</div>

						 
								<!-- /.box-header -->
								<div class="box-body">
									<table id="example1" class="table table-bordered table-hover dataTable">
										<thead>
											<tr>
												<th width="30">ITEM</th>
												<th>NRO DE ORDEN</th>
												<th>PERSONA ORDEN</th>
												<th>FECHA ORDEN</th>
												<th>IMPORTE S/.</th>
												<th>SITUACIÓN</th>
												<th>USUARIO REGISTRO</th>
												<th>ACCIONES</th>
											</tr>
										</thead>
										<tbody id="idBodyListaPostulante">
											<c:forEach var="orden" items="${lstOrdenBean}"
												varStatus="loop">
												<tr>
													<td>${loop.count}</td>
													<td>${orden.nroOrden}</td>
													<td>${orden.pacienteBean.persona.nombreCompleto}</td>
													<td>${orden.strFechaOrden} ${orden.horaOrden}</td>
													<td>${orden.sImporteTotal}</td>
													<td>${orden.situacion.nombreCorto}</td>
													<td>${orden.nombreUsuarioCreacion}</td>
													<td><c:choose>
															<c:when test="${orden.situacion.codReg =='000003'}">
																<a title="Ver detalle" data-placement="top"
																	data-toggle="tooltip"
																	class="btn bg-purple btn-sm"
																	onclick="javascript:modificarElementoGenerico('/ordenController/modificar','${loop.index}')"
																	href="#"><i class="fa fa-pencil-square-o"> VER</i></a>
															</c:when>
															<c:otherwise>
														
																<a title="Ver detalle" data-placement="top"
																	data-toggle="tooltip"
																	class="btn bg-purple btn-sm"
																	onclick="javascript:modificarElementoGenerico('/ordenController/registroResultado','${loop.index}')"
																	href="#"><i class="fa fa-pencil-square-o"> VER</i></a>

																<button type="button"
																	class="btn btn-danger btn-sm"
																	data-toggle="tooltip" data-placement="top" title=""
																	onclick="anularCargarModal('${loop.index}')"
																	data-original-title="Anular"
																	id="eliminarPerfil${loop.index}">
																	<i class="fa fa-trash"> ANULAR</i>
																</button>
															</c:otherwise>
														</c:choose></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<!-- /.box-body -->
							</div>

					</f:form>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<jsp:include
			page="${pageContext.request.contextPath}/../layout/footer-view.jsp" />
		<!-- Control Sidebar -->
		
		<jsp:include
			page="${pageContext.request.contextPath}/../layout/confirmacion-modal-view.jsp" />
	
	<div class="modal fade text-xs-left" id="modalAnalisis" tabindex="-2"
				role="dialog" aria-labelledby="myModalLabel35" data-dismiss="modal"
				aria-hidden="true" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content" id="modalAnalisisContent"></div>
				</div>
			</div>
		<div class="modal fade text-xs-left" id="md_anular-orden" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel19" aria-hidden="true"></div>
			
		<!-- /.control-sidebar -->
		<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>

	</div>
	<!-- ./wrapper -->

	<!-- jQuery 3 -->
	<script
		src="${pageContext.request.contextPath}/app-assets/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script
		src="${pageContext.request.contextPath}/app-assets/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- FastClick --> 
	<!-- AdminLTE App -->
	<script
		src="${pageContext.request.contextPath}/app-assets/dist/js/adminlte.min.js"></script>
	<!-- Sparkline -->
	<script
		src="${pageContext.request.contextPath}/app-assets/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
	<!-- jvectormap  -->
	<script
		src="${pageContext.request.contextPath}/app-assets/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/app-assets/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<!-- SlimScroll -->
	<script
		src="${pageContext.request.contextPath}/app-assets/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<!-- ChartJS -->
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<!-- AdminLTE for demo purposes -->
	<script
		src="${pageContext.request.contextPath}/app-assets/dist/js/demo.js"></script>
	<script
		src="${pageContext.request.contextPath}/app-assets/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/app-assets/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/app-assets/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script
		src="${pageContext.request.contextPath}/app-assets/bower_components/fastclick/lib/fastclick.js"></script>

	<script
	src="${pageContext.request.contextPath}/assets/js/page/asistencial/laboratorio.js"
	type="text/javascript" charset="utf-8"></script>
<script
			src="${pageContext.request.contextPath}/assets/js/page/util/utilitarios.js"
			type="text/javascript" charset="utf-8"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/page/util/datepicker.js"
	type="text/javascript" charset="utf-8"></script>

<script
	src="${pageContext.request.contextPath}/assets/js/page/util/datepicker.es.min.js"
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
	<script>
		function limpiarForm() {
			//$("#txtNombreDonante").val("");
			//$("#cboSituacion").val("");

		}
	</script>				
<script>
		$(document).ready(
				function() {
					var date_input = $('input[id="date"]'); //our date input has the name "date"
					var container = $('.bootstrap-iso form').length > 0 ? $(
							'.bootstrap-iso form').parent() : "body";
					date_input.datepicker({
						format : 'dd/mm/yyyy',
						container : container,
						todayHighlight : true,
						autoclose : true,
						language : 'es'

					})
				})

		$(document)
				.ready(
						function() {
							var date_inputHasta = $('input[id="dateHasta"]'); //our date input has the name "date"
							var containerHasta = $('.bootstrap-iso form').length > 0 ? $(
									'.bootstrap-iso form').parent()
									: "body";
							date_inputHasta.datepicker({
								format : 'dd/mm/yyyy',
								container : containerHasta,
								todayHighlight : true,
								autoclose : true,
								language : 'es'

							})
						})
	</script>
	
	<script>
  $(function () {
    $('#example1').DataTable()
    $('#example2').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })
  })
</script>
</body>
</html>
