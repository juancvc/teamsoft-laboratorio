package laboratorio.core.entity.asistencial.laboratorio;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "orden.insertar", procedureName = "LABO.USP_ORDEN_INSERTAR",
				resultClasses = Orden_laboratorio.class,
				parameters = {
				
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORGAN", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODINSTI", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODSEDEI", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.OUT, name = "CODORDEN", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODPERSO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CANT_ITEMS", type = int.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CADENA_CODTARIF", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CADENA_CANTIDAD", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.OUT, name = "NROPERIO", type = String.class),  
		    @StoredProcedureParameter(mode = ParameterMode.IN, name = "AUCDUSCR", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUPCIPCR", type = String.class), 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "PORCDESC", type = int.class)
 }),
@NamedStoredProcedureQuery(name = "orden.modificar", procedureName = "SIGEHOV2BANC.BANC.CAMPANIA_UPDATE",
resultClasses = Orden_laboratorio.class,
parameters = {
				
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORGAN", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODINSTI", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODSEDEI", type = String.class),

			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODCAMPA", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "NROPERIO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "NOMCAMPA", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "LUGCAMPA", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHAINI", type = String.class), 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHAFIN", type = String.class),
		    @StoredProcedureParameter(mode = ParameterMode.IN, name = "SITUACRG", type = String.class),
		    @StoredProcedureParameter(mode = ParameterMode.IN, name = "AUCDUSMO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUPCIPMO", type = String.class),
 }),
@NamedStoredProcedureQuery(name = "orden.eliminar", procedureName = "LABO.USP_ORDEN_ANULAR", 
resultClasses = Orden_laboratorio.class,
parameters = {
			
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
@NamedStoredProcedureQuery(name = "orden.buscarPorFiltros", procedureName = "LABO.USP_ORDEN_LISTAR_X_FILTROS", 
resultClasses = Orden_laboratorio.class,
parameters = {
		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECDESDE", type = String.class) ,
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHASTA", type = String.class) ,
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "SITUACRG", type = String.class) 
 }),

@NamedStoredProcedureQuery(name = "orden.reporteCantidadAnualOrdenSituacion", procedureName = "LABO.USP_REPORTE_CANTIDAD_ANUAL_ORDEN_SITUACION",
resultClasses = Orden_laboratorio.class,
parameters = {		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "ANIO", type = String.class) ,
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "SITUACRG", type = String.class)
}),

@NamedStoredProcedureQuery(name = "orden.reporteCantidadMensualOrdenSituacion", procedureName = "LABO.USP_REPORTE_CANTIDAD_MENSUAL_ORDEN_SITUACION",
resultClasses = Orden_laboratorio.class,
parameters = {		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "MES", 	 type = 	String.class) ,
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "ANIO", 	  type = 	String.class) ,
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "SITUACRG", type = 	String.class)
}),

@NamedStoredProcedureQuery(name = "orden.reporteCantidadDiarioOrdenSituacion", procedureName = "LABO.USP_REPORTE_CANTIDAD_ORDEN_SITUACION",
resultClasses = Orden_laboratorio.class,
parameters = {		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHA", 	 type = 	String.class) ,
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "SITUACRG", type = 	String.class)
}),

@NamedStoredProcedureQuery(name = "orden.reporteCantidadSemanalOrdenSituacion", procedureName = "LABO.USP_REPORTE_CANTIDAD_SEMANAL_ORDEN_SITUACION",
resultClasses = Orden_laboratorio.class,
parameters = {		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHA", 	 type = 	String.class) ,
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "SITUACRG", type = 	String.class)
}),

@NamedStoredProcedureQuery(name = "orden.reporteVentaAnual", procedureName = "LABO.USP_REPORTE_VENTA_ANUAL",
resultClasses = Orden_laboratorio.class,
parameters = {		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "ANIO", 	 type = 	String.class) 
}),

@NamedStoredProcedureQuery(name = "orden.reporteVentaDiaria", procedureName = "LABO.USP_REPORTE_VENTA_DIARIA",
resultClasses = Orden_laboratorio.class,
parameters = {		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHA", 	 type = 	String.class) 
}),

@NamedStoredProcedureQuery(name = "orden.reporteVentaMensual", procedureName = "LABO.USP_REPORTE_VENTA_MENSUAL_PERIODO ",
resultClasses = Orden_laboratorio.class,
parameters = {	
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "MES", 	 type = 	String.class), 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "ANIO", 	 type = 	String.class) 
}),

@NamedStoredProcedureQuery(name = "orden.reporteVentaSemanal", procedureName = "LABO.USP_REPORTE_VENTA_SEMANAL",
resultClasses = Orden_laboratorio.class,
parameters = {		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHA", 	 type = 	String.class) 
}),

@NamedStoredProcedureQuery(name = "orden.reporteDetalleOrdenVentaAnual", procedureName = "LABO.USP_REPORTE_VENTA_ANUAL_ORDEN",
resultClasses = Orden_laboratorio.class,
parameters = {		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "ANIO", 	 type = 	String.class) 
}),

@NamedStoredProcedureQuery(name = "orden.reporteDetalleOrdenVentaDiaria", procedureName = "LABO.USP_REPORTE_VENTA_DIARIA_ORDEN",
resultClasses = Orden_laboratorio.class,
parameters = {		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHA", 	 type = 	String.class) 
}),

@NamedStoredProcedureQuery(name = "orden.reporteDetalleOrdenVentaMensual", procedureName = "LABO.USP_REPORTE_VENTA_MENSUAL_ORDEN",
resultClasses = Orden_laboratorio.class,
parameters = {		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "MES", 	 type = 	String.class), 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "ANIO", 	 type = 	String.class)  
}),

@NamedStoredProcedureQuery(name = "orden.reporteDetalleOrdenVentaSemanal", procedureName = "LABO.USP_REPORTE_VENTA_SEMANAL_ORDEN",
resultClasses = Orden_laboratorio.class,
parameters = {		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHA", 	 type = 	String.class) 
}),

@NamedStoredProcedureQuery(name = "orden.reporteVentaMensualPeriodoArea", procedureName = "LABO.USP_REPORTE_VENTA_MENSUAL_PERIODO_AREA",
resultClasses = Orden_laboratorio.class,
parameters = {		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "MES", 	 type = 	String.class), 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "ANIO", 	 type = 	String.class)  
}),

@NamedStoredProcedureQuery(name = "orden.reporteVentaMensualPeriodoBarra", procedureName = "LABO.USP_REPORTE_VENTA_MENSUAL_PERIODO_BARRA",
resultClasses = Orden_laboratorio.class,
parameters = {		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "ANIO", 	 type = 	String.class)  
}),


@NamedStoredProcedureQuery(name = "orden.reporteDetalleDiariaOrdenSituacion", procedureName = "LABO.USP_REPORTE_DIARIA_ORDEN_SITUACION",
resultClasses = Orden_laboratorio.class,
parameters = {		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHA", 	 type = 	String.class) ,
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "SITUACRG", type = 	String.class)
}),

@NamedStoredProcedureQuery(name = "orden.reporteDetalleSemanalOrdenSituacion", procedureName = "LABO.USP_REPORTE_SEMANAL_ORDEN_SITUACION",
resultClasses = Orden_laboratorio.class,
parameters = {		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHA", 	 type = 	String.class) ,
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "SITUACRG", type = 	String.class)
}),

@NamedStoredProcedureQuery(name = "orden.reporteDetalleMensualOrdenSituacion", procedureName = "LABO.USP_REPORTE_MENSUAL_ORDEN_SITUACION",
resultClasses = Orden_laboratorio.class,
parameters = {		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "MES", 	 type = 	String.class), 
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "ANIO", 	 type = 	String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "SITUACRG", type = 	String.class)
}),

@NamedStoredProcedureQuery(name = "orden.reporteDetalleAnualOrdenSituacion", procedureName = "LABO.USP_REPORTE_ANUAL_ORDEN_SITUACION",
resultClasses = Orden_laboratorio.class,
parameters = {		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "ANIO", 	 type = 	String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "SITUACRG", type = 	String.class)
}),

@NamedStoredProcedureQuery(name = "orden.reporteCantidadAnualExamenesSolicitados", procedureName = "LABO.USP_REPORTE_CANTIDAD_EXAMENES_MAS_SOLICITADOS_ANUAL",
resultClasses = Orden_laboratorio.class,
parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "ANIO", type = String.class)
}),


@NamedStoredProcedureQuery(name = "orden.reporteCantidadAnualExamenesSolicitadosTotal", procedureName = "LABO.USP_REPORTE_CANTIDAD_EXAMENES_MAS_SOLICITADOS_ANUAL_TOTAL",
resultClasses = Orden_laboratorio.class,
parameters = {		
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "ANIO", type = String.class)
}),

@NamedStoredProcedureQuery(name = "orden.reporteCantidadDiarioExamenesSolicitados", procedureName = "LABO.USP_REPORTE_CANTIDAD_EXAMENES_MAS_SOLICITADOS_DIARIO",
resultClasses = Orden_laboratorio.class,
parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHA", type = String.class)
}),

@NamedStoredProcedureQuery(name = "orden.reporteCantidadDiarioExamenesSolicitadosTotal", procedureName = "LABO.USP_REPORTE_CANTIDAD_EXAMENES_MAS_SOLICITADOS_DIARIO_TOTAL",
resultClasses = Orden_laboratorio.class,
parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHA", type = String.class)
}),

@NamedStoredProcedureQuery(name = "orden.reporteCantidadMensualExamenesSolicitados", procedureName = "LABO.USP_REPORTE_CANTIDAD_EXAMENES_MAS_SOLICITADOS_MENSUAL_PERIODO",
resultClasses = Orden_laboratorio.class,
parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "MES", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "ANIO", type = String.class)
}),

@NamedStoredProcedureQuery(name = "orden.reporteCantidadMensualExamenesSolicitadosTotal", procedureName = "LABO.USP_REPORTE_CANTIDAD_EXAMENES_MAS_SOLICITADOS_MENSUAL_PERIODO_TOTAL",
resultClasses = Orden_laboratorio.class,
parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "MES", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "ANIO", type = String.class)
}),

@NamedStoredProcedureQuery(name = "orden.reporteCantidadSemanalExamenesSolicitados", procedureName = "LABO.USP_REPORTE_CANTIDAD_EXAMENES_MAS_SOLICITADOS_SEMANAL",
resultClasses = Orden_laboratorio.class,
parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHA", type = String.class)
}),

@NamedStoredProcedureQuery(name = "orden.reporteCantidadSemanalExamenesSolicitadosTotal", procedureName = "LABO.USP_REPORTE_CANTIDAD_EXAMENES_MAS_SOLICITADOS_SEMANAL_TOTAL",
resultClasses = Orden_laboratorio.class,
parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "FECHA", type = String.class)
}),

@NamedStoredProcedureQuery(name = "orden.buscarPorObjeto", procedureName = "SIGEHOV2BANC.BANC.POSTULANTE_CAMPANIA_LISTA_X_OBJETO", resultClasses = Orden_laboratorio.class, parameters = {
		
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORGAN", type = String.class) ,
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODINSTI", type = String.class) ,
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODSEDEI", type = String.class) ,
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "NROPERIO", type = String.class) ,
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "NROPOST", type = String.class) 
		
}),
 

})
 

@Entity
public class Orden_laboratorio implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	 private Orden_laboratorioPK id;
   
	@Column(name = "SITUACRG")
	private String situacRg;
	
	@Column(name = "AUFECHCR")
	private Timestamp aufechcr;

	@Column(name = "CODPERSO")
	private String codPerso;
	
	@Column(name = "NROORDEN")
	private String nroOrden;
	
	@Column(name="IMPTOTAL")
	private Float impTotal;
	
	@Column(name="DESCIMPO")
	private String desImpTotal;
	
	@Column(name="SFECHORD")
	private String strFechaOrd;
	
	@Column(name="HORAORD")
	private String hora;
	
	@Column(name="AUCDUSCR")
	private String aucduscr;
	
	@Column(name = "AUFECHMO")
	private Timestamp aufechmo;

	private String APEPATER;
	private String APEMATER;
	private String PRINOMBR;
	private String SEGNOMBR;
	private String DETALLED;
	private String NOMUSUAR;
	private String TG1SEXOP;
	private String EDAD;
	private String DIRECCIO;
	private String NOMBSITU;
	private String CORREOPE;
	
	private String CANTIDAD_ORDEN;
	private String CANTIDAD;
	private String TOTAL_VENTA;
	private String EXAMEN;
	private String MES;
	public String getNOMBSITU() {
		return NOMBSITU;
	}

	public void setNOMBSITU(String nOMBSITU) {
		NOMBSITU = nOMBSITU;
	}

	public String getTG1SEXOP() {
		return TG1SEXOP;
	}

	public void setTG1SEXOP(String tG1SEXOP) {
		TG1SEXOP = tG1SEXOP;
	}

	public String getEDAD() {
		return EDAD;
	}

	public void setEDAD(String eDAD) {
		EDAD = eDAD;
	}

	public String getDIRECCIO() {
		return DIRECCIO;
	}

	public void setDIRECCIO(String dIRECCIO) {
		DIRECCIO = dIRECCIO;
	}

	
	public String getNOMUSUAR() {
		return NOMUSUAR;
	}

	public void setNOMUSUAR(String nOMUSUAR) {
		NOMUSUAR = nOMUSUAR;
	}

	public Orden_laboratorioPK getId() {
		return id;
	}

	public Float getImpTotal() {
		
		return impTotal;
	}

	public void setImpTotal(Float impTotal) {
		this.impTotal = impTotal;
	}

	public String getDesImpTotal() {
		return desImpTotal;
	}

	public void setDesImpTotal(String desImpTotal) {
		this.desImpTotal = desImpTotal;
	}

 
	public String getStrFechaOrd() {
		return strFechaOrd;
	}

	public void setStrFechaOrd(String strFechaOrd) {
		this.strFechaOrd = strFechaOrd;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getAPEPATER() {
		return APEPATER;
	}

	public void setAPEPATER(String aPEPATER) {
		APEPATER = aPEPATER;
	}

	public String getAPEMATER() {
		return APEMATER;
	}

	public void setAPEMATER(String aPEMATER) {
		APEMATER = aPEMATER;
	}

	public String getPRINOMBR() {
		return PRINOMBR;
	}

	public void setPRINOMBR(String pRINOMBR) {
		PRINOMBR = pRINOMBR;
	}

	public String getSEGNOMBR() {
		return SEGNOMBR;
	}

	public void setSEGNOMBR(String sEGNOMBR) {
		SEGNOMBR = sEGNOMBR;
	}

	public String getDETALLED() {
		return DETALLED;
	}

	public void setDETALLED(String dETALLED) {
		DETALLED = dETALLED;
	}

	public void setId(Orden_laboratorioPK id) {
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

	public String getCodPerso() {
		return codPerso;
	}

	public void setCodPerso(String codPerso) {
		this.codPerso = codPerso;
	}

	public String getAucduscr() {
		return aucduscr;
	}

	public void setAucduscr(String aucduscr) {
		this.aucduscr = aucduscr;
	}

	public Timestamp getAufechmo() {
		return aufechmo;
	}

	public void setAufechmo(Timestamp aufechmo) {
		this.aufechmo = aufechmo;
	}

	public String getNroOrden() {
		return nroOrden;
	}

	public void setNroOrden(String nroOrden) {
		this.nroOrden = nroOrden;
	}

	public String getCORREOPE() {
		return CORREOPE;
	}

	public void setCORREOPE(String cORREOPE) {
		CORREOPE = cORREOPE;
	}

	public String getCANTIDAD_ORDEN() {
		return CANTIDAD_ORDEN;
	}

	public void setCANTIDAD_ORDEN(String cANTIDAD_ORDEN) {
		CANTIDAD_ORDEN = cANTIDAD_ORDEN;
	}

	public String getCANTIDAD() {
		return CANTIDAD;
	}

	public void setCANTIDAD(String cANTIDAD) {
		CANTIDAD = cANTIDAD;
	}

	public String getEXAMEN() {
		return EXAMEN;
	}

	public void setEXAMEN(String eXAMEN) {
		EXAMEN = eXAMEN;
	}

	public String getTOTAL_VENTA() {
		return TOTAL_VENTA;
	}

	public void setTOTAL_VENTA(String tOTAL_VENTA) {
		TOTAL_VENTA = tOTAL_VENTA;
	}

	public String getMES() {
		return MES;
	}

	public void setMES(String mES) {
		MES = mES;
	}
	
	
	
	
	  
}