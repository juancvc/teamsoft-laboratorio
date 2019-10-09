package laboratorio.core.service.interfaces.seguridad;

import laboratorio.core.bean.seguridad.UsuarioPerfilBean;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.BaseService;




public interface UsuarioPerfilService extends BaseService<UsuarioPerfilBean>{
	
	public UsuarioPerfilBean buscarXcodigoUsu(UsuarioPerfilBean usuarioPerfilBean) throws ServiceException;
}
