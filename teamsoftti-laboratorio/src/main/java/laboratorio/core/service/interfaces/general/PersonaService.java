
package laboratorio.core.service.interfaces.general;

import laboratorio.core.bean.general.PersonaBean;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.BaseService;

/*
import pe.gob.procalidad.natigu.core.bean.bean.generico.PersonaBean;
import pe.gob.procalidad.natigu.core.service.exception.ServiceException;
import pe.gob.procalidad.natigu.core.service.service.interfaces.BaseService;
*/

public interface PersonaService extends BaseService<PersonaBean> {
	  
	public PersonaBean buscarxTipoDocumentoNumeroDocumento(PersonaBean personaBean)throws ServiceException;
	
	public PersonaBean buscarxTipoDocumentoNumeroDocumentoSigeho(PersonaBean personaBean)throws ServiceException;
	
	public boolean actualizardatosuser(PersonaBean personaBean)throws ServiceException;
	
	public boolean actualizardatosfotouser(PersonaBean personaBean)throws ServiceException;
	
	public boolean insertarPersonaLaboratorio(PersonaBean persona) throws  ServiceException;
	
	public boolean actualizarPersonaLaboratorio(PersonaBean persona) throws ServiceException;
	
}
 
