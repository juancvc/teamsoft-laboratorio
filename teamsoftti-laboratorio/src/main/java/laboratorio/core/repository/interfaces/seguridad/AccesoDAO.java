package laboratorio.core.repository.interfaces.seguridad;

import laboratorio.core.bean.seguridad.AccesoBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.BaseDAO;

public interface AccesoDAO extends BaseDAO<AccesoBean> {

	public boolean asignar(AccesoBean bean) throws DAOException;
	
}
