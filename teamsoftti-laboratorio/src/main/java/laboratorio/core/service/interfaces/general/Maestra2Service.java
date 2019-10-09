package laboratorio.core.service.interfaces.general;
  
import java.util.List;

import laboratorio.core.bean.general.TablaBean;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.BaseService;
 
public interface Maestra2Service extends BaseService<TablaBean> {
	
	public List<TablaBean> listarPorCodigoTabla(String codTabla,long tipo) throws ServiceException;  
	
	public List<TablaBean> listarPorValor1(TablaBean TablaBean)  throws ServiceException; 
	
}
 
