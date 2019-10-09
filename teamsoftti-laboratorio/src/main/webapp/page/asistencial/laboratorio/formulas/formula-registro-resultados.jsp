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
					DE RESULTADOS</b></label>
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<f:form id="frmActualizarResultadoOrdenItem" name="fo"
			class="form-horizontal" role="form " method="post" action=""
			onsubmit="return false">
			<input id="contextPath" type="hidden"
				value="${pageContext.request.contextPath}">



			<div class="modal-body" id="buscaFormula" onload="init();">

				<div class="form-group row">

					<div class="col-md-4 mb-2">
						<label for="nombreCompleto" class="label_control">EXAMEN <span
							class="required">*</span>
						</label>
						<div class="controls">
							<f:input type="text" class="form-control" disabled="true"
								id="nombreExamen" path="examen.descripcion" />

						</div>
					</div>  
				</div>

<div class="form-group row">
	<div class="col-md-4 mb-2">
		<div class="label_title">
						INGRESE LOS RESULTADOS <span class="required">*</span>:
					</div>
	</div>
</div>
<div class="form-group row">
				<div class="col-md-12">
				<div id="panelCEX" class="panel_style col-md-12"> 
					
					
						<div class="col-md-12">
							<div class="table-responsive">
								<table class="table table-bordered">
									<thead class="tabla_th">
										<tr>
											<th width="30">ITEM</th>
											<th>DESCRIPCION</th>
											<th>TIPO</th>
											<th>UNIDAD MEDIDA</th>
											<th>RESULTADO</th>
											<th>IMPRESION</th>
										</tr>
									</thead>
									<tbody id="idbodyCIEXref" class="label_control">
										<c:forEach var="orden"
											items="${ordenDetalleBean.lstOrdenDetalleItemBean}"
											varStatus="loop">
											<f:input type="hidden" id="codigo" value="${codigo}"
												path="lstOrdenDetalleItemBean[${loop.index}].codigo" />

											<f:input type="hidden" id="numeroVersion"
												value="${orden.numeroVersion}"
												path="lstOrdenDetalleItemBean[${loop.index}].numeroVersion" />
											<f:input type="hidden" id="numeroPeriodo"
												value="${orden.numeroPeriodo}"
												path="lstOrdenDetalleItemBean[${loop.index}].numeroPeriodo" />
											<f:input type="hidden" id="codigoOrganizacion"
												value="${orden.codigoOrganizacion}"
												path="lstOrdenDetalleItemBean[${loop.index}].codigoOrganizacion" />
											<f:input type="hidden" id="codigoSede"
												value="${orden.codigoSede}"
												path="lstOrdenDetalleItemBean[${loop.index}].codigoSede" />
											<f:input type="hidden" id="codigoInstitucion"
												value="${orden.codigoInstitucion}"
												path="lstOrdenDetalleItemBean[${loop.index}].codigoInstitucion" />
											<tr>
												<td>${loop.count}</td>
												<td>${orden.examenesLaboratorioBean.descripcion}</td>
												<td>${orden.examenesLaboratorioBean.tipoExamenAsoc}</td>
												<td><a
													title="${orden.examenesLaboratorioBean.examenUnidadMedidaLaboratorioBean.descripcion}">${orden.examenesLaboratorioBean.examenUnidadMedidaLaboratorioBean.abrev}</a></td>


												<td><c:choose>
														<c:when
															test="${orden.examenesLaboratorioBean.tipoExamenAsoc=='000001'}">

															<f:input type="text" min="1" maxlength="20"
																class="form-control" required="required"
																onkeypress="return NumCheck(event, this)"
																id="tarifarioUnidades"
																path="lstOrdenDetalleItemBean[${loop.index}].resultado"
																autocomplete="off" />


														</c:when>
														<c:otherwise>
															<f:input type="text" min="1" maxlength="20"
																class="form-control" disabled="true"
																id="tarifarioUnidades"
																path="lstOrdenDetalleItemBean[${loop.index}].resultado" />
														</c:otherwise>
													</c:choose></td>

												<td><div class="custom-control custom-checkbox">
														<input type="checkbox" class="custom-control-input"
															id="customCheck1" checked> <label
															class="custom-control-label" for="customCheck1"></label>
													</div></td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>  
					</div>  
				 </div> 
			  </div> 
			</div>
			<div class="modal-footer">
				<button type="button" id="btnCerrarModalOrdenItem"
					class="btn btn-secondary" data-dismiss="modal">
					<i class="fa fa-close"></i> CERRAR
				</button>

				<button type="button" onclick="limpiar()" class="btn btn-default">
					<i class="fa fa-eraser"></i> LIMPIAR
				</button>

				<button type="submit"
					onclick="actualizarResultadoOrdenDetalleItem()"
					class="btn btn-primary">
					<i class="fa fa-floppy-o"></i> GRABAR
				</button>

				<button type="submit" class="btn btn-primary">
					<i class="fa fa-print"></i> IMPRIMIR
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

	<script
		src="${pageContext.request.contextPath}/assets/js/page/asistencial/laboratorio.js"
		type="text/javascript" charset="utf-8"></script>

	<script>
		$(document).ready(function() {
			console.log("log");

		});

		function retornar(num) {
			console.log("retornar");
			var anterior = document.fo.resultado.value;

			document.getElementById("resultado").value = anterior + num;

		}
	</script>


	<script language="JavaScript">
		function NumCheck(e, field) {
			key = e.keyCode ? e.keyCode : e.which
			// backspace
			if (key == 8)
				return true
				// 0-9
			if (key > 47 && key < 58) {
				if (field.value == "")
					return true
				regexp = /.[0-9]{4}$/
				return !(regexp.test(field.value))
			}
			// .
			if (key == 46) {
				if (field.value == "")
					return false
				regexp = /^[0-9]+$/
				return regexp.test(field.value)
			}
			// other key
			return false

		}
	</script>



</div>


