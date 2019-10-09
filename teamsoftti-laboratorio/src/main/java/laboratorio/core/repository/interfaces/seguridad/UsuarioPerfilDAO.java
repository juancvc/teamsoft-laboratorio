package laboratorio.core.repository.interfaces.seguridad;

import laboratorio.core.bean.seguridad.UsuarioPerfilBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.BaseDAO;




public interface UsuarioPerfilDAO extends BaseDAO<UsuarioPerfilBean> {
	public UsuarioPerfilBean buscarXcodigoUsu(UsuarioPerfilBean usuarioPerfilBean) throws DAOException;
}
