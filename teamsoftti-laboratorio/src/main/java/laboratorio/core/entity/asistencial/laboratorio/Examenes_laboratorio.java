package laboratorio.core.entity.asistencial.laboratorio;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;



@NamedStoredProcedureQueries(
		{ 
			@NamedStoredProcedureQuery(
					name="examenes_laboratorio.listarPorFiltros", 
					procedureName="[LABO].[USP_PROCEASO_LISTAR_X_FILTROS]",
					resultClasses= Examenes_laboratorio.class,
					parameters={ 
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="CODPROCE", type = String.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="TIPO", 	 type = String.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="COPROASO", type = String.class) 
					}),
			@NamedStoredProcedureQuery(
					name="examenes_laboratorio.registro", 
					procedureName="[LABO].[USP_PROCEASO_INSERT]",
					resultClasses= Examenes_laboratorio.class,
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
					resultClasses= Examenes_laboratorio.class,
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
					resultClasses= Examenes_laboratorio.class,
					parameters={ 
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODTARIF", type = 	String.class),			
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUCDUSMO", type = 	String.class), 
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUPCIPMO", type = 	String.class) 
					}),
		}
		)
@Entity 
public class Examenes_laboratorio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COPROASO")
	private String codigoExamenAsociado;

	@Column(name="NOMUSUAR")
	private String usuarioCreacion;
	

	@Column(name="CODORGAN")
	private String codigoOrganizacion;
	
	@Column(name="CODINSTI")
	private String codigoInstitucion;

	@Column(name="CODSEDEI")
	private String codigoSede;

	@Column(name="AUFECHCR")
	private Timestamp fechaCreacion;


	@Column(name="AUPCIPCR")
	private String ipCreacion;


	@Column(name="NROVERSI")
	private String nroVersion;


	@Column(name="SITUACRG")
	private String situacion;
	
	@Column(name="CODTARIF")
	private String codidoTarifario;
	
	@Column(name="NRPPROCE")
	private String periodoTarifario;
	
	@Column(name="TIPO")
	private String tipo;
	
	@Column(name="DESCORTA")
	private String descripcionCorta;
	
	@Column(name="ABREVIAT")
	private String abreviatura;
	
	@Column(name="CODUNIME")
	private String codigoUnidadMedida;
	
	@Column(name="DESCRIPCION")
	private String descripcionUnidadMedida;
	
	@Column(name="ABREVIATURA")
	private String abreviaturaUnidadMedida;
	
	@Column(name="TIPOLAB")
	private String tipoLaboratorio;
	
	@Column(name="NOMPRODU")
	private String nombreExamen;
	




	public Examenes_laboratorio() {
	}





	public String getCodigoExamenAsociado() {
		return codigoExamenAsociado;
	}





	public void setCodigoExamenAsociado(String codigoExamenAsociado) {
		this.codigoExamenAsociado = codigoExamenAsociado;
	}





	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}





	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}





	public String getCodigoOrganizacion() {
		return codigoOrganizacion;
	}





	public void setCodigoOrganizacion(String codigoOrganizacion) {
		this.codigoOrganizacion = codigoOrganizacion;
	}





	public String getCodigoInstitucion() {
		return codigoInstitucion;
	}





	public void setCodigoInstitucion(String codigoInstitucion) {
		this.codigoInstitucion = codigoInstitucion;
	}





	public String getCodigoSede() {
		return codigoSede;
	}





	public void setCodigoSede(String codigoSede) {
		this.codigoSede = codigoSede;
	}





	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}





	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}





	public String getIpCreacion() {
		return ipCreacion;
	}





	public void setIpCreacion(String ipCreacion) {
		this.ipCreacion = ipCreacion;
	}





	public String getNroVersion() {
		return nroVersion;
	}





	public void setNroVersion(String nroVersion) {
		this.nroVersion = nroVersion;
	}





	public String getSituacion() {
		return situacion;
	}





	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}





	public String getCodidoTarifario() {
		return codidoTarifario;
	}





	public void setCodidoTarifario(String codidoTarifario) {
		this.codidoTarifario = codidoTarifario;
	}





	public String getPeriodoTarifario() {
		return periodoTarifario;
	}





	public void setPeriodoTarifario(String periodoTarifario) {
		this.periodoTarifario = periodoTarifario;
	}





	public String getTipo() {
		return tipo;
	}





	public void setTipo(String tipo) {
		this.tipo = tipo;
	}





	public String getDescripcionCorta() {
		return descripcionCorta;
	}





	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}





	public String getAbreviatura() {
		return abreviatura;
	}





	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}





	public String getCodigoUnidadMedida() {
		return codigoUnidadMedida;
	}





	public void setCodigoUnidadMedida(String codigoUnidadMedida) {
		this.codigoUnidadMedida = codigoUnidadMedida;
	}





	public String getDescripcionUnidadMedida() {
		return descripcionUnidadMedida;
	}





	public void setDescripcionUnidadMedida(String descripcionUnidadMedida) {
		this.descripcionUnidadMedida = descripcionUnidadMedida;
	}





	public String getAbreviaturaUnidadMedida() {
		return abreviaturaUnidadMedida;
	}





	public void setAbreviaturaUnidadMedida(String abreviaturaUnidadMedida) {
		this.abreviaturaUnidadMedida = abreviaturaUnidadMedida;
	}





	public String getTipoLaboratorio() {
		return tipoLaboratorio;
	}





	public void setTipoLaboratorio(String tipoLaboratorio) {
		this.tipoLaboratorio = tipoLaboratorio;
	}





	public String getNombreExamen() {
		return nombreExamen;
	}





	public void setNombreExamen(String nombreExamen) {
		this.nombreExamen = nombreExamen;
	}



	



	


	
}