package laboratorio.core.service.interfaces.general;

import java.util.List;

import laboratorio.base.service.interfaces.GenericService;
import laboratorio.core.entity.general.PacienteReniec;
import laboratorio.core.service.exception.ServiceException;

public interface PacienteReniecService extends GenericService<PacienteReniec> {
	

	

	public List<PacienteReniec>  pacienteReniecVerificacion(PacienteReniec acienteReniec) throws ServiceException;
	
}
