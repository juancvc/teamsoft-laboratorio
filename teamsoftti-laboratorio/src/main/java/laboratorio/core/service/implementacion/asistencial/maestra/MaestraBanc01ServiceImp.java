package laboratorio.core.service.implementacion.asistencial.maestra;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import laboratorio.core.bean.general.TablaBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.asistencial.maestra.MaestraBanc01DAO;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.asistencial.maestra.MaestraBanc01Service;


@Service("MaestraBanc01Service")
@Transactional (readOnly = true)
public class MaestraBanc01ServiceImp implements MaestraBanc01Service {

	@Autowired
	MaestraBanc01DAO MaestraBanc01DAO;
	@Override
	public boolean insertar(TablaBean t) throws ServiceException {
		
		Boolean sw = false;
		try {
			sw =  MaestraBanc01DAO.insertar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean actualizar(TablaBean t) throws ServiceException {
		
		Boolean sw = false;
		try {
			sw =  MaestraBanc01DAO.actualizar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean eliminar(TablaBean t) throws ServiceException {
		
		Boolean sw = false;
		try {
			sw =  MaestraBanc01DAO.eliminar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public TablaBean getBuscarPorObjecto(TablaBean t)
			throws ServiceException {
		
		return null;
	}

	@Override
	public List<TablaBean> getBuscarPorFiltros(TablaBean t)
			throws ServiceException {
		
		List<TablaBean> lstBean = null;
		try {
			lstBean =  MaestraBanc01DAO.getBuscarPorFiltros(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return lstBean;
	}

	@Override
	public boolean existe(TablaBean t) throws ServiceException {
		
		return false;
	}

	@Override
	public List<TablaBean> listarPorCodigoTabla(String codTabla, int tipo) throws ServiceException {
		List<TablaBean> lstTablaBean=null;
		try { 
			lstTablaBean=(List<TablaBean>) MaestraBanc01DAO.listarPorCodigoTabla(codTabla,tipo);
		} catch (Exception e) {
			 
		}  
		return lstTablaBean;
	}
 

}