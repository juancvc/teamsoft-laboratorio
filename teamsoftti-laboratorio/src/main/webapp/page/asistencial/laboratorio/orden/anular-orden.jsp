<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<div class="modal-dialog modal-sm" role="document">
	<div class="modal-content">
		<f:form id="frmAnularOrden" class="form-horizontal" role="form"
			enctype="multipart/form-data" method="post" action=""
			onsubmit="return false">
			<input id="contextPath" type="hidden"
				value="${pageContext.request.contextPath}">
			<div class="modal-header">
				<label class="modal-title text-text-bold-600" id="myModalLabel35"><b>ANULAR
						ORDEN</b></label>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<label for="situacion" class="label_control">MOTIVO <span
							class="required">*</span>
						</label>
						<div class="controls">
							<f:select id="documento" path="motivoAnular.codReg"
								required="required" class="form-control">
								<f:option value="" label="Seleccionar" selected="true"
									disabled="disabled" />
								<f:options items="${lstMotivos}" itemValue="codReg"
									itemLabel="nombreCorto" />
							</f:select>
						</div>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-12">
						<label for="situacion" class="label_control">DETALLE
							MOTIVO DE ANULACIÓN <span class="required">*</span>
						</label>
						<f:input type="hidden" id="codigo" class="form-control"
							path="codigo" required="required" />
						<f:input type="hidden" id="codigoOrganizacion"
							class="form-control" path="codigoOrganizacion"
							required="required" />
						<f:input type="hidden" id="codigoInstitucion" class="form-control"
							path="codigoInstitucion" required="required" />
						<f:input type="hidden" id="codigoSede" class="form-control"
							path="codigoSede" required="required" />
						<f:input type="hidden" id="numeroPeriodo" class="form-control"
							path="numeroPeriodo" required="required" />
						<f:input type="hidden" id="numeroVersion" class="form-control"
							path="numeroVersion" required="required" />
						<div class="position-relative has-icon-left">
							<f:textarea id="descripcion" rows="3" class="form-control"
								path="detalleMotivoAnular" required="required"></f:textarea>

						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="idBtnCerrarDescartar"
					class="btn grey btn-outline-secondary" data-dismiss="modal">
					<i class="fa fa-close"></i>CERRAR
				</button>
				<button id="btnConfirmarGeneric" type="submit"
					class="btn btn-danger" onclick="anularOrden()"><i class="fa fa-trash"> ANULAR</i></button>
			</div>
		</f:form>
		<f:form id="frmRegistroReferencia" class="form-horizontal" role="form"
			enctype="multipart/form-data" method="POST"
			action="${pageContext.request.contextPath}/ordenController/listado">
			<button type="submit" style="display: none" id="btnListado">Click
				me</button>
		</f:form>
	</div>
</div>


