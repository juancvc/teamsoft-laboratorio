package laboratorio.core.service.interfaces.asistencial.maestra;
  
import java.util.List;

import laboratorio.core.bean.general.TablaBean;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.BaseService;
 

public interface MaestraAsis14Service extends BaseService<TablaBean> {
	 
	public List<TablaBean> listarPorCodigoTabla(String codTabla,int tipo) throws ServiceException;  
	
	public List<TablaBean> listarTodasTablas(TablaBean tablaBean) throws ServiceException;  
	
}
 
