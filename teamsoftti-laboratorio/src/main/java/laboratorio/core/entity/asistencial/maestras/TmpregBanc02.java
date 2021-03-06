package laboratorio.core.entity.asistencial.maestras;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

 
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "tmpregBanc02.listarPorCodigoTabla", procedureName = "[SIGEHOV2BANC].[GENE].[USP_TPREGU02_LISTAR_X_CODTABLA]", 
			resultClasses = TmpregBanc02.class, parameters = {					
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "CODTABLA", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "TIPO", type = int.class) 
		}),
		
		@NamedStoredProcedureQuery(name = "tmpregBanc02.listarPreguntasPorSexo", procedureName = "[SIGEHOV2BANC].[GENE].[ENTREVISTA_RESPUESTA_SEXO]", 
		resultClasses = TmpregBanc02.class, parameters = {					
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "VALGEN01", type = String.class)
	})
		  
})

  
@Entity  
public class TmpregBanc02 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AUFECHCR")
	private Timestamp aufechcr;

	@Column(name="AUFECHMO")
	private Timestamp aufechmo;

	@Column(name="CODCATEG")
	private String codcateg;

	@Id
	@Column(name="CODREGIS")
	private String codregis;

	@Column(name="CODTABLA")
	private String codtabla;

	@Column(name="ESTADORG")
	private String estadorg;

	@Column(name="NOMCORTO")
	private String nomcorto;

	@Column(name="NOMLARGO")
	private String nomlargo;

	@Column(name="NROORDEN")
	private short nroorden;

	@Column(name="SWMIGRAD")
	private short swmigrad;

	@Column(name="VALGEN01")
	private String valgen01;

	@Column(name="VALGEN02")
	private String valgen02;

	@Column(name="VALGEN03")
	private String valgen03;

	@Column(name="VALGEN04")
	private String valgen04;

	@Column(name="VALGEN05")
	private String valgen05;

	@Column(name="VALGEN06")
	private String valgen06;

	@Column(name="VALGEN07")
	private String valgen07;

	@Column(name="VALGEN08")
	private String valgen08;

	@Column(name="VALGEN09")
	private String valgen09;

	@Column(name="VALGEN10")
	private String valgen10;

	@Column(name="VALGEN11")
	private String valgen11;

	@Column(name="VALGEN12")
	private String valgen12;

	public TmpregBanc02() {
	}

	public Timestamp getAufechcr() {
		return this.aufechcr;
	}

	public void setAufechcr(Timestamp aufechcr) {
		this.aufechcr = aufechcr;
	}

	public Timestamp getAufechmo() {
		return this.aufechmo;
	}

	public void setAufechmo(Timestamp aufechmo) {
		this.aufechmo = aufechmo;
	}

	public String getCodcateg() {
		return this.codcateg;
	}

	public void setCodcateg(String codcateg) {
		this.codcateg = codcateg;
	}

	public String getCodregis() {
		return this.codregis;
	}

	public void setCodregis(String codregis) {
		this.codregis = codregis;
	}

	public String getCodtabla() {
		return this.codtabla;
	}

	public void setCodtabla(String codtabla) {
		this.codtabla = codtabla;
	}

	public String getEstadorg() {
		return this.estadorg;
	}

	public void setEstadorg(String estadorg) {
		this.estadorg = estadorg;
	}

	public String getNomcorto() {
		return this.nomcorto;
	}

	public void setNomcorto(String nomcorto) {
		this.nomcorto = nomcorto;
	}

	public String getNomlargo() {
		return this.nomlargo;
	}

	public void setNomlargo(String nomlargo) {
		this.nomlargo = nomlargo;
	}

	public short getNroorden() {
		return this.nroorden;
	}

	public void setNroorden(short nroorden) {
		this.nroorden = nroorden;
	}

	public short getSwmigrad() {
		return this.swmigrad;
	}

	public void setSwmigrad(short swmigrad) {
		this.swmigrad = swmigrad;
	}

	public String getValgen01() {
		return this.valgen01;
	}

	public void setValgen01(String valgen01) {
		this.valgen01 = valgen01;
	}

	public String getValgen02() {
		return this.valgen02;
	}

	public void setValgen02(String valgen02) {
		this.valgen02 = valgen02;
	}

	public String getValgen03() {
		return this.valgen03;
	}

	public void setValgen03(String valgen03) {
		this.valgen03 = valgen03;
	}

	public String getValgen04() {
		return this.valgen04;
	}

	public void setValgen04(String valgen04) {
		this.valgen04 = valgen04;
	}

	public String getValgen05() {
		return this.valgen05;
	}

	public void setValgen05(String valgen05) {
		this.valgen05 = valgen05;
	}

	public String getValgen06() {
		return this.valgen06;
	}

	public void setValgen06(String valgen06) {
		this.valgen06 = valgen06;
	}

	public String getValgen07() {
		return this.valgen07;
	}

	public void setValgen07(String valgen07) {
		this.valgen07 = valgen07;
	}

	public String getValgen08() {
		return this.valgen08;
	}

	public void setValgen08(String valgen08) {
		this.valgen08 = valgen08;
	}

	public String getValgen09() {
		return this.valgen09;
	}

	public void setValgen09(String valgen09) {
		this.valgen09 = valgen09;
	}

	public String getValgen10() {
		return this.valgen10;
	}

	public void setValgen10(String valgen10) {
		this.valgen10 = valgen10;
	}

	public String getValgen11() {
		return this.valgen11;
	}

	public void setValgen11(String valgen11) {
		this.valgen11 = valgen11;
	}

	public String getValgen12() {
		return this.valgen12;
	}

	public void setValgen12(String valgen12) {
		this.valgen12 = valgen12;
	}

}