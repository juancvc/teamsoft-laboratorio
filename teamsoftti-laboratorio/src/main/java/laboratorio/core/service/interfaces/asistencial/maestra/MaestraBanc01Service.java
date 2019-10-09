
package laboratorio.core.service.interfaces.asistencial.maestra;
  
import java.util.List;

import laboratorio.core.bean.general.TablaBean;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.BaseService;
  

public interface MaestraBanc01Service extends BaseService<TablaBean> {
	
	public List<TablaBean> listarPorCodigoTabla(String codTabla,int tipo) throws ServiceException;  
}
 
