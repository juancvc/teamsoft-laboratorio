package laboratorio.core.service.implementacion.general;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import laboratorio.core.bean.general.ExamenesLaboratorioBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.general.ExamenesLaboratorioDAO;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.general.ExamenesLaboratorioService;

@Service("ExamenesLaboratorioService")
@Transactional (readOnly = true)
public class ExamenesLaboratorioServiceImpl implements ExamenesLaboratorioService {
	
	
	@Autowired
	 private ExamenesLaboratorioDAO examenesLaboratorioDAO;
	
	@Override
	public boolean insertar(ExamenesLaboratorioBean t) throws ServiceException {
		Boolean sw = false;
		try {
			sw =  examenesLaboratorioDAO.insertar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean actualizar(ExamenesLaboratorioBean t) throws ServiceException {
		Boolean sw = false;
		try {
			sw =  examenesLaboratorioDAO.actualizar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean eliminar(ExamenesLaboratorioBean t) throws ServiceException {
		Boolean sw = false;
		try {
			sw =  examenesLaboratorioDAO.eliminar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public ExamenesLaboratorioBean getBuscarPorObjecto(ExamenesLaboratorioBean t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExamenesLaboratorioBean> getBuscarPorFiltros(ExamenesLaboratorioBean t)
			throws ServiceException {
		List<ExamenesLaboratorioBean> lstExamenesLaboratorioBean =  null;
		try {
			lstExamenesLaboratorioBean = examenesLaboratorioDAO.getBuscarPorFiltros(t);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return lstExamenesLaboratorioBean;
	}

	@Override
	public boolean existe(ExamenesLaboratorioBean t) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	 
	 
}
