package laboratorio.core.bean.general;


import laboratorio.base.bean.BaseBean;

public class ExamenesLaboratorioBean extends BaseBean{

 

	private String descripcion;
	private String abrev;
	private String observacion;
	private TarifarioBean tarifarioBean;
	private TablaBean tipoExamenLab;
	private ExamenUnidadMedidaLaboratorioBean examenUnidadMedidaLaboratorioBean;
	private TablaBean unidadMedida;
	private String valoresReferencial;
	private String tipoExamenAsoc;
	
	public ExamenesLaboratorioBean() {
		super();
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
     
	
	public TarifarioBean getTarifarioBean() {
		if (tarifarioBean == null) {
			tarifarioBean = new TarifarioBean();
		}
		return tarifarioBean;
	}

	public void setTarifarioBean(TarifarioBean tarifarioBean) {
		this.tarifarioBean = tarifarioBean;
	}

	public TablaBean getTipoExamenLab() {
		if (tipoExamenLab == null) {
			tipoExamenLab = new TablaBean();
		}
		return tipoExamenLab;
	}

	public void setTipoExamenLab(TablaBean tipoExamenLab) {
		this.tipoExamenLab = tipoExamenLab;
	}

	public TablaBean getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(TablaBean unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getValoresReferencial() {
		return valoresReferencial;
	}

	public void setValoresReferencial(String valoresReferencial) {
		this.valoresReferencial = valoresReferencial;
	}

	public String getTipoExamenAsoc() {
		return tipoExamenAsoc;
	}

	public void setTipoExamenAsoc(String tipoExamenAsoc) {
		this.tipoExamenAsoc = tipoExamenAsoc;
	}

	public ExamenUnidadMedidaLaboratorioBean getExamenUnidadMedidaLaboratorioBean() {
		if (examenUnidadMedidaLaboratorioBean == null) {
			examenUnidadMedidaLaboratorioBean = new ExamenUnidadMedidaLaboratorioBean();
		}
		return examenUnidadMedidaLaboratorioBean;
	}

	public void setExamenUnidadMedidaLaboratorioBean(ExamenUnidadMedidaLaboratorioBean examenUnidadMedidaLaboratorioBean) {
		this.examenUnidadMedidaLaboratorioBean = examenUnidadMedidaLaboratorioBean;
	}


	

}
