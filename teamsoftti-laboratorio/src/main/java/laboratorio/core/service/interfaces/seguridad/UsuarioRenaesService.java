package laboratorio.core.service.interfaces.seguridad;

import java.util.List;

import laboratorio.core.bean.seguridad.UsuarioBean;
import laboratorio.core.bean.seguridad.UsuarioRenaesBean;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.BaseService;


public interface UsuarioRenaesService extends BaseService<UsuarioRenaesBean>{
 
	
	public List<UsuarioRenaesBean> buscarxcodigousua(UsuarioBean prmUsuarioBean) throws ServiceException;
	 
}
