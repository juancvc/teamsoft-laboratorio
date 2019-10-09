package laboratorio.core.service.implementacion.asistencial.laboratorio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import laboratorio.core.bean.asistencial.laboratorio.OrdenBean;
import laboratorio.core.bean.asistencial.laboratorio.OrdenDetalleItemBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.asistencial.laboratorio.OrdenDetalleItemDAO;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.asistencial.laboratorio.OrdenDetalleItemService;


@Service("OrdenDetalleItemService")
@Transactional(readOnly = true)
public class OrdenDetalleItemServiceImp implements OrdenDetalleItemService {

	@Autowired
	OrdenDetalleItemDAO ordenDetalleItemDAO;

	@Override
	public boolean insertar(OrdenDetalleItemBean t) throws ServiceException {
		Boolean sw = false;
		try {
			sw =  ordenDetalleItemDAO.insertar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean actualizar(OrdenDetalleItemBean t) throws ServiceException {
	
		Boolean sw = false;
		try {
			sw =  ordenDetalleItemDAO.actualizar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean eliminar(OrdenDetalleItemBean t) throws ServiceException {
	
		Boolean sw = false;
		try {
			sw =  ordenDetalleItemDAO.eliminar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public OrdenDetalleItemBean getBuscarPorObjecto(OrdenDetalleItemBean t) throws ServiceException {
	
		return null;
	}

	@Override
	public List<OrdenDetalleItemBean> getBuscarPorFiltros(OrdenDetalleItemBean t) throws ServiceException {
		List<OrdenDetalleItemBean> lstOrdenDetalleItemBean = null;
		try {
			lstOrdenDetalleItemBean = ordenDetalleItemDAO.getBuscarPorFiltros(t);
		} catch (Exception e) {

		}
		return lstOrdenDetalleItemBean;
	}

	@Override
	public boolean existe(OrdenDetalleItemBean t) throws ServiceException {
	
		return false;
	}

	@Override
	public List<OrdenDetalleItemBean> listarAnalisisResultados(OrdenDetalleItemBean ordenDetalleItemBean)
			throws ServiceException {
		List<OrdenDetalleItemBean> lstOrdenDetalleItemBean = null;
		try {
			lstOrdenDetalleItemBean = ordenDetalleItemDAO.listarAnalisisResultados(ordenDetalleItemBean);
		} catch (Exception e) {

		}
		return lstOrdenDetalleItemBean;
	}

	@Override
	public List<OrdenDetalleItemBean> listarAnalisisResultadosPorOrden(OrdenBean OrdenBean) throws ServiceException {
		List<OrdenDetalleItemBean> lstOrdenDetalleItemBean = null;
		try {
			lstOrdenDetalleItemBean = ordenDetalleItemDAO.listarAnalisisResultadosPorOrden(OrdenBean);
		} catch (Exception e) {

		}
		return lstOrdenDetalleItemBean;
	}
	
 

}
