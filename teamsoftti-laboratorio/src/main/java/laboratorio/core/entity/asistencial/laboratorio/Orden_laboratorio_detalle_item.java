package laboratorio.core.entity.asistencial.laboratorio;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "ordenDetalleItem.insertar",
				procedureName = "[LABO].[ORDET_ASOC_ITEMS_INSERT]", resultClasses = Orden_laboratorio_detalle_item.class, parameters = {
			
						
						
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORGAN", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODINSTI", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODSEDEI", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.OUT, name = "CODORDAS", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORDDE", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "NRVEORDE", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "NROPORDE", type = String.class),
 
		    @StoredProcedureParameter(mode = ParameterMode.IN, name = "AUCDUSCR", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUPCIPCR", type = String.class),
 }),
@NamedStoredProcedureQuery(name = "ordenDetalleItem.modificar",
			procedureName = "[LABO].[USP_ORDEN_DETALLE_ITEM_RESULTADOS_UPDATE]",
			resultClasses = Orden_laboratorio_detalle_item.class, parameters = {
				
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORGAN", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODINSTI", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODSEDEI", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORDAS", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORDEN", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "NROPERIO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "RESULTADO",type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "TIPO", 	  type = String.class),
		    @StoredProcedureParameter(mode = ParameterMode.IN, name = "AUCDUSMO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUPCIPMO", type = String.class),
 }),
@NamedStoredProcedureQuery(name = "ordenDetalleItem.eliminar", procedureName = "[LABO].[USP_ORDEN_ANULAR]", resultClasses = Orden_laboratorio_detalle_item.class, parameters = {
			
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORGAN", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODINSTI", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODSEDEI", type = String.class),  
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "NROPERIO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORDEN", type = String.class),
			
		    @StoredProcedureParameter(mode = ParameterMode.IN, name = "TG1TPANU", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "MOTIVOAN", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUCDUSMO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUPCIPMO", type = String.class),
			
 }),
@NamedStoredProcedureQuery(name = "ordenDetalleItem.buscarPorFiltros", procedureName = "LABO.USP_ORDEN_LISTAR_X_FILTROS", resultClasses = Orden_laboratorio_detalle_item.class, parameters = {
		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECDESDE", type = String.class) ,
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHASTA", type = String.class) ,
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "SITUACRG", type = String.class) 
 }),

@NamedStoredProcedureQuery(name = "ordenDetalleItem.buscarPorCodigoDetalle",

		procedureName = "[LABO].[USP_ORDET_ITEM_LISTAR_X_CODDETALLE]",
		
	    resultClasses = Orden_laboratorio_detalle_item.class, parameters = {
		
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORGAN", type = String.class) ,
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODINSTI", type = String.class) ,
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODSEDEI", type = String.class) ,
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORDEN", type = String.class) 
}),

@NamedStoredProcedureQuery(name = "ordenDetalleItem.buscarPorCodigoOrden",

		procedureName = "[LABO].[USP_ORDET_ITEM_LISTAR_X_CODORDEN]",
		
		resultClasses = Orden_laboratorio_detalle_item.class, parameters = {
		
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORGAN", type = String.class) ,
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODINSTI", type = String.class) ,
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODSEDEI", type = String.class) ,
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORDEN", type = String.class) 
}),

@NamedStoredProcedureQuery(name = "ordenDetalleItem.buscarPorObjeto", procedureName = "[SIGEHOV2BANC].[BANC].[POSTULANTE_CAMPANIA_LISTA_X_OBJETO]", resultClasses = Orden_laboratorio_detalle_item.class, parameters = {
		
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORGAN", type = String.class) ,
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODINSTI", type = String.class) ,
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODSEDEI", type = String.class) ,
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "NROPERIO", type = String.class) ,
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "NROPOST", type = String.class) 
		
}),
 

})
 

@Entity
public class Orden_laboratorio_detalle_item implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	 private Orden_laboratorio_detalle_itemPK id;
   
	@Column(name = "SITUACRG")
	private String situacRg;
	
	@Column(name = "AUFECHCR")
	private Timestamp aufechcr;

	@Column(name = "CODORDDE")
	private String codigoOrdenDetalle;
	
	@Column(name = "NRVEORDE")
	private String nroVersionOrdenDetalle;
	
	@Column(name = "NROPORDE")
	private String nroPeriodoOrdenDetalle;
	

	@Column(name="RESULTADO")
	private String resultado;
	
	@Column(name="RESULTADO_FORMULA")
	private double resultadoFormula;
	
	@Column(name="HORAORD")
	private String hora;
	
	@Column(name="AUCDUSCR")
	private String usuarioCreacion;
	
	@Column(name = "AUFECHMO")
	private Timestamp fechaModificacion;

	@Column(name = "AUPCIPCR")
	private String ipRegistro;

	@Column(name = "AUPCIPMO")
	private String ipModificacion;
	
	@Column(name = "COPROASO")
	private String codigoExamenLaboratorio;
	
	@Column(name = "NRVEPRAS")
	private String nroVersionExamenLaboratorio;
	
	@Column(name = "NROPPRAS")
	private String nroPeriodoExamenLaboratorio;
	
	@Column(name = "DESCORTA")
	private String descripcionExamenAsociado;
	
	@Column(name = "ABREVIAT")
	private String abrevExamenAsociado;
	
	@Column(name = "DESCRIPCION")
	private String descripcionUnidadMedida;
	
	@Column(name = "ABREVIATURA")
	private String abrevUnidadMedida;
	
	@Column(name = "TIPO")
	private String tipo;
	
	@Column(name = "FLAG")
	private boolean imprimirItem;

	private String NOMPRODU;
	
	private String OBSERVACION;
	
	public Orden_laboratorio_detalle_item() {
		
		// TODO Auto-generated constructor stub
	}

	public Orden_laboratorio_detalle_itemPK getId() {
		return id;
	}

	public void setId(Orden_laboratorio_detalle_itemPK id) {
		this.id = id;
	}

	public String getSituacRg() {
		return situacRg;
	}

	public void setSituacRg(String situacRg) {
		this.situacRg = situacRg;
	}

	public Timestamp getAufechcr() {
		return aufechcr;
	}

	public void setAufechcr(Timestamp aufechcr) {
		this.aufechcr = aufechcr;
	}

	public String getCodigoOrdenDetalle() {
		return codigoOrdenDetalle;
	}

	public void setCodigoOrdenDetalle(String codigoOrdenDetalle) {
		this.codigoOrdenDetalle = codigoOrdenDetalle;
	}

	public String getNroVersionOrdenDetalle() {
		return nroVersionOrdenDetalle;
	}

	public void setNroVersionOrdenDetalle(String nroVersionOrdenDetalle) {
		this.nroVersionOrdenDetalle = nroVersionOrdenDetalle;
	}

	public String getNroPeriodoOrdenDetalle() {
		return nroPeriodoOrdenDetalle;
	}

	public void setNroPeriodoOrdenDetalle(String nroPeriodoOrdenDetalle) {
		this.nroPeriodoOrdenDetalle = nroPeriodoOrdenDetalle;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getIpRegistro() {
		return ipRegistro;
	}

	public void setIpRegistro(String ipRegistro) {
		this.ipRegistro = ipRegistro;
	}

	public String getIpModificacion() {
		return ipModificacion;
	}

	public void setIpModificacion(String ipModificacion) {
		this.ipModificacion = ipModificacion;
	}

	public String getCodigoExamenLaboratorio() {
		return codigoExamenLaboratorio;
	}

	public void setCodigoExamenLaboratorio(String codigoExamenLaboratorio) {
		this.codigoExamenLaboratorio = codigoExamenLaboratorio;
	}

	public String getNroVersionExamenLaboratorio() {
		return nroVersionExamenLaboratorio;
	}

	public void setNroVersionExamenLaboratorio(String nroVersionExamenLaboratorio) {
		this.nroVersionExamenLaboratorio = nroVersionExamenLaboratorio;
	}

	public String getNroPeriodoExamenLaboratorio() {
		return nroPeriodoExamenLaboratorio;
	}

	public void setNroPeriodoExamenLaboratorio(String nroPeriodoExamenLaboratorio) {
		this.nroPeriodoExamenLaboratorio = nroPeriodoExamenLaboratorio;
	}
	
	
	
	

	public double getResultadoFormula() {
		return resultadoFormula;
	}

	public void setResultadoFormula(double resultadoFormula) {
		this.resultadoFormula = resultadoFormula;
	}
	
	
	
	

	public String getDescripcionExamenAsociado() {
		return descripcionExamenAsociado;
	}

	public void setDescripcionExamenAsociado(String descripcionExamenAsociado) {
		this.descripcionExamenAsociado = descripcionExamenAsociado;
	}

	public String getAbrevExamenAsociado() {
		return abrevExamenAsociado;
	}

	public void setAbrevExamenAsociado(String abrevExamenAsociado) {
		this.abrevExamenAsociado = abrevExamenAsociado;
	}

	public String getDescripcionUnidadMedida() {
		return descripcionUnidadMedida;
	}

	public void setDescripcionUnidadMedida(String descripcionUnidadMedida) {
		this.descripcionUnidadMedida = descripcionUnidadMedida;
	}

	public String getAbrevUnidadMedida() {
		return abrevUnidadMedida;
	}

	public void setAbrevUnidadMedida(String abrevUnidadMedida) {
		this.abrevUnidadMedida = abrevUnidadMedida;
	}
	

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	 
	public String getNOMPRODU() {
		return NOMPRODU;
	}

	public void setNOMPRODU(String nOMPRODU) {
		NOMPRODU = nOMPRODU;
	}

	public boolean isImprimirItem() {
		return imprimirItem;
	}

	public void setImprimirItem(boolean imprimirItem) {
		this.imprimirItem = imprimirItem;
	}
	
	

	public String getOBSERVACION() {
		return OBSERVACION;
	}

	public void setOBSERVACION(String oBSERVACION) {
		OBSERVACION = oBSERVACION;
	}

	@Override
	public String toString() {
		return "Orden_laboratorio_detalle_item [id=" + id + ", situacRg=" + situacRg + ", aufechcr=" + aufechcr
				+ ", codigoOrdenDetalle=" + codigoOrdenDetalle + ", nroVersionOrdenDetalle=" + nroVersionOrdenDetalle
				+ ", nroPeriodoOrdenDetalle=" + nroPeriodoOrdenDetalle + ", resultado=" + resultado + ", hora=" + hora
				+ ", usuarioCreacion=" + usuarioCreacion + ", fechaModificacion=" + fechaModificacion + ", ipRegistro="
				+ ipRegistro + ", ipModificacion=" + ipModificacion + ", codigoExamenLaboratorio="
				+ codigoExamenLaboratorio + ", nroVersionExamenLaboratorio=" + nroVersionExamenLaboratorio
				+ ", nroPeriodoExamenLaboratorio=" + nroPeriodoExamenLaboratorio + "]";
	}
	
	
	
	  
}