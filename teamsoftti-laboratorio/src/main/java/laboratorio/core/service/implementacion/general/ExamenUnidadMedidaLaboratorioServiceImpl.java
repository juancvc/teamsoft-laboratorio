package laboratorio.core.service.implementacion.general;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import laboratorio.core.bean.general.ExamenUnidadMedidaLaboratorioBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.general.ExamenUnidadMedidaLaboratorioDAO;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.general.ExamenUnidadMedidaLaboratorioService;

@Service("ExamenUnidadMedidaLaboratorioService")
@Transactional (readOnly = true)
public class ExamenUnidadMedidaLaboratorioServiceImpl implements ExamenUnidadMedidaLaboratorioService {
	
	
	@Autowired
	 private ExamenUnidadMedidaLaboratorioDAO examenUnidadMedidaLaboratorioDAO;
	
	@Override
	public boolean insertar(ExamenUnidadMedidaLaboratorioBean t) throws ServiceException {
		Boolean sw = false;
		try {
			sw =  examenUnidadMedidaLaboratorioDAO.insertar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean actualizar(ExamenUnidadMedidaLaboratorioBean t) throws ServiceException {
		Boolean sw = false;
		try {
			sw =  examenUnidadMedidaLaboratorioDAO.actualizar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean eliminar(ExamenUnidadMedidaLaboratorioBean t) throws ServiceException {
		Boolean sw = false;
		try {
			sw =  examenUnidadMedidaLaboratorioDAO.eliminar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public ExamenUnidadMedidaLaboratorioBean getBuscarPorObjecto(ExamenUnidadMedidaLaboratorioBean t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExamenUnidadMedidaLaboratorioBean> getBuscarPorFiltros(ExamenUnidadMedidaLaboratorioBean t)
			throws ServiceException {
		List<ExamenUnidadMedidaLaboratorioBean> lstExamenUnidadMedidaLaboratorioBean =  null;
		try {
			lstExamenUnidadMedidaLaboratorioBean = examenUnidadMedidaLaboratorioDAO.getBuscarPorFiltros(t);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return lstExamenUnidadMedidaLaboratorioBean;
	}

	@Override
	public boolean existe(ExamenUnidadMedidaLaboratorioBean t) throws ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	 
	 
}
