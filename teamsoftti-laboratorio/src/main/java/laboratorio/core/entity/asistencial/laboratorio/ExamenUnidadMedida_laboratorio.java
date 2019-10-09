package laboratorio.core.entity.asistencial.laboratorio;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;



@NamedStoredProcedureQueries(
		{ 
			@NamedStoredProcedureQuery(
					name="examenUnidadMedida_laboratorio.listarPorFiltros", 
					procedureName="[LABO].[UNIMEDI_LISTAR_X_FILTROS]",
					resultClasses= ExamenUnidadMedida_laboratorio.class,
					parameters={ 
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="ABREVIATURA", type = String.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="DESCRIPCION", type = String.class) 
					}),
			@NamedStoredProcedureQuery(
					name="examenes_laboratorio.registro", 
					procedureName="[LABO].[USP_PROCEASO_INSERT]",
					resultClasses= ExamenUnidadMedida_laboratorio.class,
					parameters={ 
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORGAN", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODINSTI", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODSEDEI", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "NROVERSI", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.OUT, name ="COPROASO", type = 	String.class),			
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODTARIF", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "TIPO",  	  type =	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "DESCORTA", type = 	String.class),						
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "ABREVIAT", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODUNIME", type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUDUSUCR", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUPCIPCR", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "OBSERVAC", type = 	String.class)
					}),
			@NamedStoredProcedureQuery(
					name="examenes_laboratorio.actualizar", 
					procedureName="[LABO].[TARIFARIO_ITEM_UPDATE]",
					resultClasses= ExamenUnidadMedida_laboratorio.class,
					parameters={ 
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="CODTARIF", type = 	String.class),			
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "TG1TIPOT", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "TIPO",  	  type =	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "SUBTIPO",  type = 	String.class),	
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "NOMPRODU", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "PRECIO",   type =    Float.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "TIPOUNID", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALREFIN", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALREFFI", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUCDUSCR", type = 	String.class) 
					}),
			
			@NamedStoredProcedureQuery(
					name="examenes_laboratorio.eliminar", 
					procedureName="[LABO].[TARIFARIO_ITEM_DELETE]",
					resultClasses= ExamenUnidadMedida_laboratorio.class,
					parameters={ 
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODTARIF", type = 	String.class),			
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUCDUSMO", type = 	String.class), 
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUPCIPMO", type = 	String.class) 
					}),
		}
		)
@Entity 
public class ExamenUnidadMedida_laboratorio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CODUNIME")
	private String codigoUnidadMedida;

	@Column(name="AUCDUSCR")
	private String usuarioCreacion;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@Column(name="ABREVIATURA")
	private String abrev;
	
	@Column(name="CODORGAN")
	private String codigoOrganizacion;
	
	@Column(name="CODINSTI")
	private Float codigoInstitucion;

	@Column(name="CODSEDEI")
	private String codigoSede;

	@Column(name="AUFECHCR")
	private Timestamp aufechcr;


	@Column(name="AUPCIPCR")
	private String aupcipcr;


	@Column(name="NROVERSI")
	private String NROVERSI;


	@Column(name="SITUACRG")
	private String situacrg;
	
	@Column(name="OBSERVAC")
	private String observacion;



	public ExamenUnidadMedida_laboratorio() {
	}



	public String getCodigoUnidadMedida() {
		return codigoUnidadMedida;
	}



	public void setCodigoUnidadMedida(String codigoUnidadMedida) {
		this.codigoUnidadMedida = codigoUnidadMedida;
	}



	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}



	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public String getAbrev() {
		return abrev;
	}



	public void setAbrev(String abrev) {
		this.abrev = abrev;
	}



	public String getCodigoOrganizacion() {
		return codigoOrganizacion;
	}



	public void setCodigoOrganizacion(String codigoOrganizacion) {
		this.codigoOrganizacion = codigoOrganizacion;
	}



	public Float getCodigoInstitucion() {
		return codigoInstitucion;
	}



	public void setCodigoInstitucion(Float codigoInstitucion) {
		this.codigoInstitucion = codigoInstitucion;
	}



	public String getCodigoSede() {
		return codigoSede;
	}



	public void setCodigoSede(String codigoSede) {
		this.codigoSede = codigoSede;
	}



	public Timestamp getAufechcr() {
		return aufechcr;
	}



	public void setAufechcr(Timestamp aufechcr) {
		this.aufechcr = aufechcr;
	}



	public String getAupcipcr() {
		return aupcipcr;
	}



	public void setAupcipcr(String aupcipcr) {
		this.aupcipcr = aupcipcr;
	}



	public String getNROVERSI() {
		return NROVERSI;
	}



	public void setNROVERSI(String nROVERSI) {
		NROVERSI = nROVERSI;
	}



	public String getSituacrg() {
		return situacrg;
	}



	public void setSituacrg(String situacrg) {
		this.situacrg = situacrg;
	}



	public String getObservacion() {
		return observacion;
	}



	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}



	
}