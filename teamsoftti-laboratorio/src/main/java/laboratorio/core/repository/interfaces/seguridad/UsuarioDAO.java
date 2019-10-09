package laboratorio.core.repository.interfaces.seguridad;

import laboratorio.core.bean.seguridad.UsuarioBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.BaseDAO;

public interface UsuarioDAO extends BaseDAO<UsuarioBean> {

	public UsuarioBean autenticar(UsuarioBean prmUsuarioBean) throws DAOException;
	
	public Integer cambiarPassword(UsuarioBean prmUsuarioBean) throws DAOException;
	
	public UsuarioBean buscarxcodigousua(UsuarioBean prmUsuarioBean) throws DAOException;
	
	public UsuarioBean buscarxcodperso(UsuarioBean prmUsuarioBean) throws DAOException;
}
