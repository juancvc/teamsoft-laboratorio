package laboratorio.core.bean.general.antecedentes;

import java.util.Date;

import laboratorio.base.bean.BaseBean;
import laboratorio.core.bean.general.TablaBean;



public class EnfermedadPrenatalBean extends BaseBean{
	private TablaBean ciex;
	private AntecedentesPrenatalesBean antecedPrenatales;
	private Date fechReg;
	
	public EnfermedadPrenatalBean(){
		super();
	}

	public TablaBean getCiex() {
		return ciex;
	}

	public void setCiex(TablaBean ciex) {
		this.ciex = ciex;
	}

	public AntecedentesPrenatalesBean getAntecedPrenatales() {
		return antecedPrenatales;
	}

	public void setAntecedPrenatales(AntecedentesPrenatalesBean antecedPrenatales) {
		this.antecedPrenatales = antecedPrenatales;
	}

	public Date getFechReg() {
		return fechReg;
	}

	public void setFechReg(Date fechReg) {
		this.fechReg = fechReg;
	}
	
	
}
