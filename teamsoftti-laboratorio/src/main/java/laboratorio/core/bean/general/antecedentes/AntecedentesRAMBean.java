package laboratorio.core.bean.general.antecedentes;


import laboratorio.base.bean.BaseBean;
import laboratorio.core.bean.general.TablaBean;

public class AntecedentesRAMBean extends BaseBean {
	private TablaBean anteRam;
	private AntecedentesPacienteBean antecedentesPaciente;
	private boolean angioEdema=false;
	
	public TablaBean getAnteRam() {
		return anteRam;
	}
	public void setAnteRam(TablaBean anteRam) {
		this.anteRam = anteRam;
	}
	public AntecedentesPacienteBean getAntecedentesPaciente() {
		return antecedentesPaciente;
	}
	public void setAntecedentesPaciente(AntecedentesPacienteBean antecedentesPaciente) {
		this.antecedentesPaciente = antecedentesPaciente;
	}
	
	public boolean isAngioEdema() {
		return angioEdema;
	}
	public void setAngioEdema(boolean angioEdema) {
		this.angioEdema = angioEdema;
	}

	
	
}
