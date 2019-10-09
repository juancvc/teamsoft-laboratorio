package laboratorio.core.repository.interfaces.seguridad;

import java.util.List;

import laboratorio.core.bean.seguridad.PerfilBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.BaseDAO;



public interface PerfilDAO extends BaseDAO<PerfilBean> {

	List<PerfilBean> listado () throws DAOException; 
}
