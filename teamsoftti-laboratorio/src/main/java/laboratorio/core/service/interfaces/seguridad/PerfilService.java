package laboratorio.core.service.interfaces.seguridad;

import java.util.List;

import laboratorio.core.bean.seguridad.PerfilBean;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.BaseService;

public interface PerfilService extends BaseService<PerfilBean>{
	
	List<PerfilBean> listado () throws ServiceException;

}
