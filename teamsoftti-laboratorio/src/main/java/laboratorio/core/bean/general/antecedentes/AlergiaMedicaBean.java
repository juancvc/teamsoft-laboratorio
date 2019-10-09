package laboratorio.core.bean.general.antecedentes;



import laboratorio.base.bean.BaseBean;
import laboratorio.core.bean.general.PacienteBean;
import laboratorio.core.bean.general.TablaBean;


public
class AlergiaMedicaBean
extends BaseBean {
	
	private PacienteBean	paciente;
	private	TablaBean		alergiaMedica;
	private	TablaBean		ram;
	private String		observaciones;
	
	public PacienteBean getPaciente() {
		return paciente;
	}
	public void setPaciente(PacienteBean paciente) {
		this.paciente = paciente;
	}
	
	public TablaBean getAlergiaMedica() {
		return alergiaMedica;
	}
	public void setAlergiaMedica(TablaBean alergiaMedica) {
		this.alergiaMedica = alergiaMedica;
	}
	
	public TablaBean getRam() {
		return ram;
	}
	public void setRam(TablaBean ram) {
		this.ram = ram;
	}
	
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
}