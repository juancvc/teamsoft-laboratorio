package laboratorio.core.bean.seguridad;

import laboratorio.base.bean.BaseBean;
import laboratorio.core.bean.general.RenaesBean; 

public class UsuarioRenaesBean extends BaseBean {

	private UsuarioBean usuario;

	private RenaesBean renaes;

	public UsuarioRenaesBean() {
		super();
	}

	public UsuarioBean getUsuario() {
		if (usuario == null) {
			usuario =  new UsuarioBean();
		}
		return usuario;
	}

	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}

	public RenaesBean getRenaes() {
		if (renaes == null) {
			renaes = new RenaesBean();
		}
		return renaes;
	}

	public void setRenaes(RenaesBean renaes) {
		this.renaes = renaes;
	}

}
