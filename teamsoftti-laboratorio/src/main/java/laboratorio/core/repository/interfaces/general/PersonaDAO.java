package laboratorio.core.repository.interfaces.general;

import laboratorio.core.bean.general.PersonaBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.BaseDAO;





public interface PersonaDAO  extends BaseDAO<PersonaBean>  {
	
	public PersonaBean buscarxTipoDocumentoNumeroDocumento(PersonaBean personaBean)throws DAOException;
	
	public PersonaBean buscarxTipoDocumentoNumeroDocumentoSigeho(PersonaBean personaBean)throws DAOException;
	 
	public boolean actualizardatosuser(PersonaBean personaBean)throws DAOException;
	
	public boolean actualizardatosfotouser(PersonaBean personaBean)throws DAOException;
	
	public boolean insertarPersonaLaboratorio(PersonaBean persona) throws DAOException ;
	
	public boolean actualizarPersonaLaboratorio(PersonaBean persona) throws DAOException ;
	
}
