package laboratorio.core.repository.interfaces.asistencial.maestra; 
import java.util.List;

import laboratorio.core.bean.general.TablaBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.BaseDAO;

 
public interface MaestraAsis06DAO  extends BaseDAO<TablaBean>  {
	  
	public List<TablaBean> listarPorCodigoTabla(String codTabla,int tipo) throws DAOException;
	
	public List<TablaBean> buscar(TablaBean tabla) throws DAOException;
	
}
