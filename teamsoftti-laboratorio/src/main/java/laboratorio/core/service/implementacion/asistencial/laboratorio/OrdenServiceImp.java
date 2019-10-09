package laboratorio.core.service.implementacion.asistencial.laboratorio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import laboratorio.core.bean.asistencial.laboratorio.OrdenBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.asistencial.laboratorio.OrdenDAO;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.asistencial.laboratorio.OrdenService; 

@Service("OrdenService")
@Transactional(readOnly = true)
public class OrdenServiceImp implements OrdenService {

	@Autowired
    OrdenDAO OrdenDAO;

	@Override
	public boolean insertar(OrdenBean t) throws ServiceException {
		Boolean sw = false;
		try {
			sw =  OrdenDAO.insertar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean actualizar(OrdenBean t) throws ServiceException {
	
		Boolean sw = false;
		try {
			sw =  OrdenDAO.actualizar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public boolean eliminar(OrdenBean t) throws ServiceException {
	
		Boolean sw = false;
		try {
			sw =  OrdenDAO.eliminar(t);
			
		} catch (DAOException e) { 
			e.printStackTrace();
		}
		return sw;
	}

	@Override
	public OrdenBean getBuscarPorObjecto(OrdenBean t) throws ServiceException {
	
		return null;
	}

	@Override
	public List<OrdenBean> getBuscarPorFiltros(OrdenBean t) throws ServiceException {
		List<OrdenBean> lstOrdenBean = null;
		try {
			lstOrdenBean = OrdenDAO.getBuscarPorFiltros(t);
		} catch (Exception e) {

		}
		return lstOrdenBean;
	}

	@Override
	public boolean existe(OrdenBean t) throws ServiceException {
	
		return false;
	}

	@Override
	public OrdenBean reporteCantidadAnualOrdenSituacion(OrdenBean ordenBean) throws ServiceException {
		
		OrdenBean oOrdenBean = null;
			try {
			
				oOrdenBean= OrdenDAO.reporteCantidadAnualOrdenSituacion(ordenBean);
			} catch (Exception e) {
				 
			} 
			 
			return oOrdenBean;
	}

	@Override
	public OrdenBean reporteCantidadMensualOrdenSituacion(OrdenBean ordenBean) throws ServiceException {
		
		OrdenBean oOrdenBean = null;
			try {
			
				oOrdenBean= OrdenDAO.reporteCantidadMensualOrdenSituacion(ordenBean);
			} catch (Exception e) {
				 
			} 
			 
			return oOrdenBean;
	}

	@Override
	public OrdenBean reporteCantidadDiarioOrdenSituacion(OrdenBean ordenBean) throws ServiceException {
		OrdenBean oOrdenBean = null;
		try {
		
			oOrdenBean= OrdenDAO.reporteCantidadDiarioOrdenSituacion(ordenBean);
		} catch (Exception e) {
			 
		} 
		 
		return oOrdenBean;
	}

	@Override
	public OrdenBean reporteCantidadSemanalOrdenSituacion(OrdenBean ordenBean) throws ServiceException {
		OrdenBean oOrdenBean = null;
		try {
		
			oOrdenBean= OrdenDAO.reporteCantidadSemanalOrdenSituacion(ordenBean);
		} catch (Exception e) {
			 
		} 
		 
		return oOrdenBean;
	}

	@Override
	public OrdenBean reporteVentaAnual(OrdenBean ordenBean) throws ServiceException {
		OrdenBean oOrdenBean = null;
		try {
		
			oOrdenBean= OrdenDAO.reporteVentaAnual(ordenBean);
		} catch (Exception e) {
			 
		} 
		 
		return oOrdenBean;
	}

	@Override
	public OrdenBean reporteVentaMensual(OrdenBean ordenBean) throws ServiceException {
		OrdenBean oOrdenBean = null;
		try {
		
			oOrdenBean= OrdenDAO.reporteVentaMensual(ordenBean);
		} catch (Exception e) {
			 
		} 
		 
		return oOrdenBean;
	}

	@Override
	public OrdenBean reporteVentaDiaria(OrdenBean ordenBean) throws ServiceException {
		OrdenBean oOrdenBean = null;
		try {
		
			oOrdenBean= OrdenDAO.reporteVentaDiaria(ordenBean);
		} catch (Exception e) {
			 
		} 
		 
		return oOrdenBean;
	}

	@Override
	public OrdenBean reporteVentaSemanal(OrdenBean ordenBean) throws ServiceException {
		OrdenBean oOrdenBean = null;
		try {
		
			oOrdenBean= OrdenDAO.reporteVentaSemanal(ordenBean);
		} catch (Exception e) {
			 
		} 
		 
		return oOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleOrdenVentaAnual(OrdenBean ordenBean) throws ServiceException {
		List<OrdenBean> lstOrdenBean = null;
		try {
			lstOrdenBean = OrdenDAO.reporteDetalleOrdenVentaAnual(ordenBean);
		} catch (Exception e) {

		}
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleOrdenVentaMensual(OrdenBean ordenBean) throws ServiceException {
		List<OrdenBean> lstOrdenBean = null;
		try {
			lstOrdenBean = OrdenDAO.reporteDetalleOrdenVentaMensual(ordenBean);
		} catch (Exception e) {

		}
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleOrdenVentaDiaria(OrdenBean ordenBean) throws ServiceException {
		List<OrdenBean> lstOrdenBean = null;
		try {
			lstOrdenBean = OrdenDAO.reporteDetalleOrdenVentaDiaria(ordenBean);
		} catch (Exception e) {

		}
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleOrdenVentaSemanal(OrdenBean ordenBean) throws ServiceException {
		List<OrdenBean> lstOrdenBean = null;
		try {
			lstOrdenBean = OrdenDAO.reporteDetalleOrdenVentaSemanal(ordenBean);
		} catch (Exception e) {

		}
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteVentaMensualPeriodoArea(OrdenBean ordenBean) throws ServiceException {
		List<OrdenBean> lstOrdenBean = null;
		try {
			lstOrdenBean = OrdenDAO.reporteVentaMensualPeriodoArea(ordenBean);
		} catch (Exception e) {

		}
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteVentaMensualPeriodoBarra(OrdenBean ordenBean) throws ServiceException {
		List<OrdenBean> lstOrdenBean = null;
		try {
			lstOrdenBean = OrdenDAO.reporteVentaMensualPeriodoBarra(ordenBean);
		} catch (Exception e) {

		}
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleAnualOrdenSituacion(OrdenBean ordenBean) throws ServiceException {
		List<OrdenBean> lstOrdenBean = null;
		try {
			lstOrdenBean = OrdenDAO.reporteDetalleAnualOrdenSituacion(ordenBean);
		} catch (Exception e) {

		}
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleMensualOrdenSituacion(OrdenBean ordenBean) throws ServiceException {
		List<OrdenBean> lstOrdenBean = null;
		try {
			lstOrdenBean = OrdenDAO.reporteDetalleMensualOrdenSituacion(ordenBean);
		} catch (Exception e) {

		}
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleDiariaOrdenSituacion(OrdenBean ordenBean) throws ServiceException {
		List<OrdenBean> lstOrdenBean = null;
		try {
			lstOrdenBean = OrdenDAO.reporteDetalleDiariaOrdenSituacion(ordenBean);
		} catch (Exception e) {

		}
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleSemanalOrdenSituacion(OrdenBean ordenBean) throws ServiceException {
		List<OrdenBean> lstOrdenBean = null;
		try {
			lstOrdenBean = OrdenDAO.reporteDetalleSemanalOrdenSituacion(ordenBean);
		} catch (Exception e) {

		}
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadAnualExamenesSolicitados(OrdenBean ordenBean) throws ServiceException {
		List<OrdenBean> lstOrdenBean = null;
		try {
			lstOrdenBean = OrdenDAO.reporteCantidadAnualExamenesSolicitados(ordenBean);
		} catch (Exception e) {

		}
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadAnualExamenesSolicitadosTotal(OrdenBean ordenBean) throws ServiceException {
		List<OrdenBean> lstOrdenBean = null;
		try {
			lstOrdenBean = OrdenDAO.reporteCantidadAnualExamenesSolicitadosTotal(ordenBean);
		} catch (Exception e) {

		}
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadMensualExamenesSolicitados(OrdenBean ordenBean) throws ServiceException {
		List<OrdenBean> lstOrdenBean = null;
		try {
			lstOrdenBean = OrdenDAO.reporteCantidadMensualExamenesSolicitados(ordenBean);
		} catch (Exception e) {

		}
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadMensualExamenesSolicitadosTotal(OrdenBean ordenBean) throws ServiceException {
		List<OrdenBean> lstOrdenBean = null;
		try {
			lstOrdenBean = OrdenDAO.reporteCantidadMensualExamenesSolicitadosTotal(ordenBean);
		} catch (Exception e) {

		}
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadDiarioExamenesSolicitados(OrdenBean ordenBean) throws ServiceException {
		List<OrdenBean> lstOrdenBean = null;
		try {
			lstOrdenBean = OrdenDAO.reporteCantidadDiarioExamenesSolicitados(ordenBean);
		} catch (Exception e) {

		}
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadDiarioExamenesSolicitadosTotal(OrdenBean ordenBean) throws ServiceException {
		List<OrdenBean> lstOrdenBean = null;
		try {
			lstOrdenBean = OrdenDAO.reporteCantidadDiarioExamenesSolicitadosTotal(ordenBean);
		} catch (Exception e) {

		}
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadSemanalExamenesSolicitados(OrdenBean ordenBean) throws ServiceException {
		List<OrdenBean> lstOrdenBean = null;
		try {
			lstOrdenBean = OrdenDAO.reporteCantidadSemanalExamenesSolicitados(ordenBean);
		} catch (Exception e) {

		}
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadSemanalExamenesSolicitadosTotal(OrdenBean ordenBean) throws ServiceException {
		List<OrdenBean> lstOrdenBean = null;
		try {
			lstOrdenBean = OrdenDAO.reporteCantidadSemanalExamenesSolicitadosTotal(ordenBean);
		} catch (Exception e) {

		}
		return lstOrdenBean;
	}
 

}
