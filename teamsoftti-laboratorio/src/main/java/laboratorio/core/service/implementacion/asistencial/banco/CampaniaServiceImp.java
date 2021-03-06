package laboratorio.core.service.implementacion.asistencial.banco;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import laboratorio.core.bean.asistencial.banco.CampaniaBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.asistencial.banco.CampaniaDAO;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.asistencial.banco.CampaniaService;

@Service("campaniaService")
@Transactional(readOnly = true)
public class CampaniaServiceImp implements CampaniaService {

	@Autowired
    CampaniaDAO CampaniaDAO;

	@Override
	public boolean insertar(CampaniaBean t) throws ServiceException {
		Boolean sw = false;
		try {
			sw =  CampaniaDAO.insertar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean actualizar(CampaniaBean t) throws ServiceException {
	
		Boolean sw = false;
		try {
			sw =  CampaniaDAO.actualizar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean eliminar(CampaniaBean t) throws ServiceException {
	
		Boolean sw = false;
		try {
			sw =  CampaniaDAO.eliminar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public CampaniaBean getBuscarPorObjecto(CampaniaBean t) throws ServiceException {
	
		return null;
	}

	@Override
	public List<CampaniaBean> getBuscarPorFiltros(CampaniaBean t) throws ServiceException {
		List<CampaniaBean> lstCampaniaBean = null;
		try {
			lstCampaniaBean = CampaniaDAO.getBuscarPorFiltros(t);
		} catch (Exception e) {

		}
		return lstCampaniaBean;
	}

	@Override
	public boolean existe(CampaniaBean t) throws ServiceException {
	
		return false;
	}
 

}
