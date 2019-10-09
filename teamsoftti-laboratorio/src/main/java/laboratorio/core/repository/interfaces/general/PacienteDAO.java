package laboratorio.core.repository.interfaces.general;

import laboratorio.core.bean.general.PacienteBean;
import laboratorio.core.bean.general.PersonaBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.BaseDAO;





public interface PacienteDAO  extends BaseDAO<PacienteBean>  {
	
	public PacienteBean buscarxTipoDocumentoNumeroDocumento(PacienteBean pacienteBean)throws DAOException;
	
	public PacienteBean buscarxPersona(PersonaBean personaBean)throws DAOException;
	 
	public boolean actualizardatosuser(PacienteBean personaBean)throws DAOException;
	
	public boolean actualizardatosfotouser(PacienteBean personaBean)throws DAOException;
}
