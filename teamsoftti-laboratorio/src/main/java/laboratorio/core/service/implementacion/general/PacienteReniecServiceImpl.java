package laboratorio.core.service.implementacion.general;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import laboratorio.base.service.ServiceException;
import laboratorio.core.entity.general.PacienteReniec;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.general.PacienteReniecDAO;
import laboratorio.core.service.interfaces.general.PacienteReniecService; 

@Service("pacienteReniecService")
public class PacienteReniecServiceImpl implements PacienteReniecService {
	
	
	@Autowired
	private PacienteReniecDAO pacienteDAO; 

	public PacienteReniecServiceImpl() {
	}





	public PacienteReniecDAO getPacienteDAO() {
		return pacienteDAO;
	}





	public void setPacienteDAO(PacienteReniecDAO pacienteDAO) {
		this.pacienteDAO = pacienteDAO;
	}





	@Override
	public boolean delete(PacienteReniec arg0) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

  


	@Override
	public boolean insert(PacienteReniec arg0) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}





	@Override
	public boolean update(PacienteReniec arg0) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}





	@Override
	public List<PacienteReniec> pacienteReniecVerificacion(
			PacienteReniec pacienteReniec) 
					
					throws laboratorio.core.service.exception.ServiceException {
		List<PacienteReniec> lstPaciente=null;
		try {
			lstPaciente=this.getPacienteDAO().pacienteReniecVerificacion(pacienteReniec);
		} catch (DAOException e) {
			throw new laboratorio.core.service.exception.ServiceException(e);
		}
		return lstPaciente;
	}





	@Override
	public PacienteReniec getByObject(PacienteReniec t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public List<PacienteReniec> getByLikeObject(PacienteReniec t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public boolean exist(PacienteReniec t) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}





}
