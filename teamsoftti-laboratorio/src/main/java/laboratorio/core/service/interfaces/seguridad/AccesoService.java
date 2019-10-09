
package laboratorio.core.service.interfaces.seguridad;

import laboratorio.core.bean.seguridad.AccesoBean;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.BaseService;


public interface AccesoService extends BaseService<AccesoBean>{

	public boolean asignar(AccesoBean bean) throws ServiceException;
	
}
