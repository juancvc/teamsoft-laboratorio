package laboratorio.core.repository.interfaces.general;

import java.util.List;

import laboratorio.core.bean.general.TablaBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.BaseDAO; 




public interface Maestra2DAO  extends BaseDAO<TablaBean>  {
	 
	public List<TablaBean> listarPorCodigoTabla(String codTabla,long tipo) throws DAOException;
	
	public List<TablaBean> listarPorValor1(TablaBean TablaBean) throws DAOException;
	
}
