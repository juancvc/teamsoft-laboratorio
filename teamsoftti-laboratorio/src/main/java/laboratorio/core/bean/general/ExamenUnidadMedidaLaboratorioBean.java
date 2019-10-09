package laboratorio.core.bean.general;


import laboratorio.base.bean.BaseBean;

public class ExamenUnidadMedidaLaboratorioBean extends BaseBean{

 

	private String descripcion;
	private String abrev;
	private String observacion;

	
	public ExamenUnidadMedidaLaboratorioBean() {
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

	

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
	

	

}
