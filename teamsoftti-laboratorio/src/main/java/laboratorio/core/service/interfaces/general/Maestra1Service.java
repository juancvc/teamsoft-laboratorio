
package laboratorio.core.service.interfaces.general;
  
import java.util.List;

import laboratorio.core.bean.general.TablaBean;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.BaseService;
  

public interface Maestra1Service extends BaseService<TablaBean> {
	
	public List<TablaBean> listarPorCodigoTabla(String codTabla,int tipo) throws ServiceException;  
	
	public List<TablaBean> listarComboGeneral(String codTabla) throws ServiceException;

	public TablaBean getBuscarPorTablaYRegistro(TablaBean TablaBean) throws ServiceException; 
}
 
