package laboratorio.core.service.interfaces.seguridad;

import laboratorio.core.bean.seguridad.UsuarioBean;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.BaseService;


public interface UsuarioService extends BaseService<UsuarioBean>{

	public UsuarioBean autenticar(UsuarioBean prmUsuarioBean) throws ServiceException;
	
	public Integer cambiarPassword(UsuarioBean prmUsuarioBean) throws ServiceException;
	
	public UsuarioBean buscarxcodigousua(UsuarioBean prmUsuarioBean) throws ServiceException;
	
	public UsuarioBean buscarxcodperso(UsuarioBean prmUsuarioBean) throws ServiceException;
}
