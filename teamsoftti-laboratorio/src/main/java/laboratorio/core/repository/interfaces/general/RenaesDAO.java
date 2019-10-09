package laboratorio.core.repository.interfaces.general;
  
import laboratorio.core.bean.general.RenaesBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.BaseDAO;


public interface RenaesDAO  extends BaseDAO<RenaesBean>  {
	
	public boolean insertarDestinoRenaes(RenaesBean personaBean)throws DAOException;
}
