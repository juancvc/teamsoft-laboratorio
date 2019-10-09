package laboratorio.core.entity.asistencial.laboratorio;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;



@NamedStoredProcedureQueries(
		{ 
			@NamedStoredProcedureQuery(
					name="analisis_formula_laboratorio.listarPorFiltros", 
					procedureName="[LABO].[USP_PROCEASO_LISTAR_X_FILTROS]",
					resultClasses= Analisis_formula_laboratorio.class,
					parameters={ 
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="CODPROCE", type = String.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="TIPO", 	 type = String.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="COPROASO", type = String.class) 
					}),
			
			@NamedStoredProcedureQuery(
					name="analisis_formula_laboratorio.formularResultados", 
					procedureName="[LABO].[USP_RESULTADOS_ANALISIS_FORMULA]",
					resultClasses= Analisis_formula_laboratorio.class,
					parameters={ 
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="VALOR1", 		 type = Float.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="VALOR2", 		 type = Float.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="VALOR3", 		 type = Float.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="VALOR4", 	 	 type = Float.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="VALOR5", 	  	 type = Float.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="VALOR6", 		 type = Float.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="VALOR7", 		 type = Float.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="CODORDDE", 	 type = String.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="COORPRAS", 	 type = String.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="COINPRAS", 	 type = String.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="COSEPRAS", 	 type = String.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="PROCEASO", 	 type = String.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="ABREV", 	 	 type = String.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="ABREV2", 	 	 type = String.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="ABREV3", 	 	 type = String.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="ABREV4", 	 	 type = String.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="ABREV5", 	 	 type = String.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="ABREV6", 	 	 type = String.class) ,
							@StoredProcedureParameter(mode = ParameterMode.IN, name ="ABREV7", 	 	 type = String.class) 
					}),
			
			
			@NamedStoredProcedureQuery(
					name="analisis_formula_laboratorio.registro", 
					procedureName="[LABO].[USP_ANALIFOR_INSERT]",
					resultClasses= Analisis_formula_laboratorio.class,
					parameters={ 
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORGAN", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODINSTI", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODSEDEI", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "NROVERSI", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.OUT,name = "CODFORMU", type = 	String.class),			
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "COORGPAF", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "COINSPAF", type =	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "COSEDPAF", type = 	String.class),						
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODPROAF", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "COPROAS1", type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "COPROAS2", type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "COPROAS3", type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "COPROAS4", type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "COPROAS5", type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "COPROAS6", type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "COPROAS7", type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALOR1",   type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALOR2",   type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALOR3",   type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALOR4",   type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALOR5",   type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALOR6",   type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALOR7",   type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALORFOR", type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUDUSUCR", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUPCIPCR", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "OBSERVAC", type = 	String.class)
					}),
			@NamedStoredProcedureQuery(
					name="analisis_formula_laboratorio.actualizar", 
					procedureName="[LABO].[USP_ANALIFOR_UPDATE]",
					resultClasses= Analisis_formula_laboratorio.class,
					parameters={ 
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODORGAN", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODINSTI", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODSEDEI", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN,name = "CODFORMU", type = 	String.class),								
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODPROAF", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "COPROAS1", type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "COPROAS2", type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "COPROAS3", type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "COPROAS4", type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "COPROAS5", type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "COPROAS6", type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "COPROAS7", type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALOR1",   type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALOR2",   type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALOR3",   type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALOR4",   type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALOR5",   type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALOR6",   type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALOR7",   type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALORFOR", type =    String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUCDUSMO", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUPCIPMO", type = 	String.class),
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "OBSERVAC", type = 	String.class)
					}),
			
			@NamedStoredProcedureQuery(
					name="analisis_formula_laboratorio.eliminar", 
					procedureName="[LABO].[TARIFARIO_ITEM_DELETE]",
					resultClasses= Analisis_formula_laboratorio.class,
					parameters={ 
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODTARIF", type = 	String.class),			
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUCDUSMO", type = 	String.class), 
							@StoredProcedureParameter(mode = ParameterMode.IN, name = "AUPCIPMO", type = 	String.class) 
					}),
		}
		)
@Entity 
public class Analisis_formula_laboratorio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CODFORMU")
	private String codigoFormulaLaboratorio;

	@Column(name="AUCDUSCR")
	private String usuarioCreacion;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@Column(name="COORGPAF")
	private String codigoOrganizacionAnalisisFormula;
	
	@Column(name="COINSPAF")
	private String codigoIsntitucionAnalisisFormula;
	
	@Column(name="COSEDPAF")
	private String codigoSedeAnalisisFormula;
	
	@Column(name="CODPROAF")
	private String codigoAnalisisFormula;
	
	@Column(name="VALOR1")
	private String valor1;
	
	@Column(name="VALOR2")
	private String valor2;
	
	@Column(name="VALOR3")
	private String valor3;
	
	@Column(name="VALOR4")
	private String valor4;
	
	@Column(name="VALOR5")
	private String valor5;
	
	@Column(name="VALOR6")
	private String valor6;
	
	@Column(name="VALOR7")
	private String valor7;
	
	@Column(name="COPROAS1")
	private String codigoAnalisisAsociado;
	
	@Column(name="COPROAS2")
	private String codigoAnalisisAsociado2;
	
	@Column(name="COPROAS3")
	private String codigoAnalisisAsociado3;
	
	@Column(name="COPROAS4")
	private String codigoAnalisisAsociado4;
	
	@Column(name="COPROAS5")
	private String codigoAnalisisAsociado5;
	
	@Column(name="COPROAS6")
	private String codigoAnalisisAsociado6;
	
	@Column(name="COPROAS7")
	private String codigoAnalisisAsociado7;
	
	@Column(name="CODORGAN")
	private String codigoOrganizacion;
	
	@Column(name="CODINSTI")
	private String codigoInstitucion;

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
	
	@Column(name="RESULTADO")
	private float resultado;

	public Analisis_formula_laboratorio() {
	}

	public String getCodigoFormulaLaboratorio() {
		return codigoFormulaLaboratorio;
	}

	public void setCodigoFormulaLaboratorio(String codigoFormulaLaboratorio) {
		this.codigoFormulaLaboratorio = codigoFormulaLaboratorio;
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

	public String getCodigoOrganizacionAnalisisFormula() {
		return codigoOrganizacionAnalisisFormula;
	}

	public void setCodigoOrganizacionAnalisisFormula(String codigoOrganizacionAnalisisFormula) {
		this.codigoOrganizacionAnalisisFormula = codigoOrganizacionAnalisisFormula;
	}

	public String getCodigoIsntitucionAnalisisFormula() {
		return codigoIsntitucionAnalisisFormula;
	}

	public void setCodigoIsntitucionAnalisisFormula(String codigoIsntitucionAnalisisFormula) {
		this.codigoIsntitucionAnalisisFormula = codigoIsntitucionAnalisisFormula;
	}

	public String getCodigoSedeAnalisisFormula() {
		return codigoSedeAnalisisFormula;
	}

	public void setCodigoSedeAnalisisFormula(String codigoSedeAnalisisFormula) {
		this.codigoSedeAnalisisFormula = codigoSedeAnalisisFormula;
	}

	public String getCodigoAnalisisFormula() {
		return codigoAnalisisFormula;
	}

	public void setCodigoAnalisisFormula(String codigoAnalisisFormula) {
		this.codigoAnalisisFormula = codigoAnalisisFormula;
	}

	public String getValor1() {
		return valor1;
	}

	public void setValor1(String valor1) {
		this.valor1 = valor1;
	}

	public String getValor2() {
		return valor2;
	}

	public void setValor2(String valor2) {
		this.valor2 = valor2;
	}

	public String getValor3() {
		return valor3;
	}

	public void setValor3(String valor3) {
		this.valor3 = valor3;
	}

	public String getValor4() {
		return valor4;
	}

	public void setValor4(String valor4) {
		this.valor4 = valor4;
	}

	public String getValor5() {
		return valor5;
	}

	public void setValor5(String valor5) {
		this.valor5 = valor5;
	}

	public String getValor6() {
		return valor6;
	}

	public void setValor6(String valor6) {
		this.valor6 = valor6;
	}

	public String getValor7() {
		return valor7;
	}

	public void setValor7(String valor7) {
		this.valor7 = valor7;
	}

	public String getCodigoAnalisisAsociado() {
		return codigoAnalisisAsociado;
	}

	public void setCodigoAnalisisAsociado(String codigoAnalisisAsociado) {
		this.codigoAnalisisAsociado = codigoAnalisisAsociado;
	}

	public String getCodigoAnalisisAsociado2() {
		return codigoAnalisisAsociado2;
	}

	public void setCodigoAnalisisAsociado2(String codigoAnalisisAsociado2) {
		this.codigoAnalisisAsociado2 = codigoAnalisisAsociado2;
	}

	public String getCodigoAnalisisAsociado3() {
		return codigoAnalisisAsociado3;
	}

	public void setCodigoAnalisisAsociado3(String codigoAnalisisAsociado3) {
		this.codigoAnalisisAsociado3 = codigoAnalisisAsociado3;
	}

	public String getCodigoAnalisisAsociado4() {
		return codigoAnalisisAsociado4;
	}

	public void setCodigoAnalisisAsociado4(String codigoAnalisisAsociado4) {
		this.codigoAnalisisAsociado4 = codigoAnalisisAsociado4;
	}

	public String getCodigoAnalisisAsociado5() {
		return codigoAnalisisAsociado5;
	}

	public void setCodigoAnalisisAsociado5(String codigoAnalisisAsociado5) {
		this.codigoAnalisisAsociado5 = codigoAnalisisAsociado5;
	}

	public String getCodigoAnalisisAsociado6() {
		return codigoAnalisisAsociado6;
	}

	public void setCodigoAnalisisAsociado6(String codigoAnalisisAsociado6) {
		this.codigoAnalisisAsociado6 = codigoAnalisisAsociado6;
	}

	public String getCodigoAnalisisAsociado7() {
		return codigoAnalisisAsociado7;
	}

	public void setCodigoAnalisisAsociado7(String codigoAnalisisAsociado7) {
		this.codigoAnalisisAsociado7 = codigoAnalisisAsociado7;
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

	public float getResultado() {
		return resultado;
	}

	public void setResultado(float resultado) {
		this.resultado = resultado;
	}
	
	
	


	
}