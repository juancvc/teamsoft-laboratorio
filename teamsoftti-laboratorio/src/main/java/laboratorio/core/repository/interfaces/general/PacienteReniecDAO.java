package laboratorio.core.repository.interfaces.general;

import java.util.List;

import laboratorio.base.repository.interfaces.GenericDAO;
import laboratorio.core.entity.general.PacienteReniec;
import laboratorio.core.repository.DAOException;

 
public interface PacienteReniecDAO extends GenericDAO<PacienteReniec> {
	
	

	public List<PacienteReniec>  pacienteReniecVerificacion(PacienteReniec pacienteReniec) throws DAOException;
}
	

	

