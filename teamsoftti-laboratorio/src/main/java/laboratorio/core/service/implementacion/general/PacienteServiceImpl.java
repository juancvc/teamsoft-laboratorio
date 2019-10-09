package laboratorio.core.service.implementacion.general;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import laboratorio.core.bean.general.PacienteBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.general.PacienteDAO;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.general.PacienteService;


@Service("pacienteService")
@Transactional (readOnly = true)
public class PacienteServiceImpl implements PacienteService {

	@Autowired
	private PacienteDAO PacienteDAO; 
	
	public PacienteDAO getPacienteDAO() {
		return PacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO PacienteDAO) {
		this.PacienteDAO = PacienteDAO;
	}

	@Override
	public boolean insertar(PacienteBean PacienteBean) throws ServiceException {
		Boolean sw = false;
		try {
			sw =  PacienteDAO.insertar(PacienteBean);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean actualizar(PacienteBean t) throws ServiceException {
		Boolean sw = false;
		try {
			sw =  PacienteDAO.actualizar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean eliminar(PacienteBean t) throws ServiceException { 
		return false;
	}

	@Override
	public PacienteBean getBuscarPorObjecto(PacienteBean t)
			throws ServiceException { 
		return null;
	}

	@Override
	public List<PacienteBean> getBuscarPorFiltros(PacienteBean t)
			throws ServiceException { 
		return null;
	}

	@Override
	public boolean existe(PacienteBean t) throws ServiceException { 
		return false;
	}
  

}
