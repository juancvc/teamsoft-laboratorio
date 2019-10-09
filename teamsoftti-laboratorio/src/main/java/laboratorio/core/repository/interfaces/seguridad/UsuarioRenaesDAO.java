package laboratorio.core.repository.interfaces.seguridad;

import java.util.List;

import laboratorio.core.bean.seguridad.UsuarioBean;
import laboratorio.core.bean.seguridad.UsuarioRenaesBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.BaseDAO;

public interface UsuarioRenaesDAO extends BaseDAO<UsuarioRenaesBean> {
 
	public List<UsuarioRenaesBean> buscarxcodigousua(UsuarioBean prmUsuarioBean) throws DAOException;
	 
}
