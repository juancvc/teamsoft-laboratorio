package laboratorio.core.repository.interfaces.asistencial.laboratorio;
  

import java.util.List;

import laboratorio.core.bean.asistencial.laboratorio.OrdenBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.BaseDAO;

public interface OrdenDAO  extends BaseDAO<OrdenBean> {
	
	public OrdenBean reporteCantidadAnualOrdenSituacion(OrdenBean ordenBean)throws DAOException;
	public OrdenBean reporteCantidadMensualOrdenSituacion(OrdenBean ordenBean)throws DAOException;
	public OrdenBean reporteCantidadDiarioOrdenSituacion(OrdenBean ordenBean)throws DAOException;
	public OrdenBean reporteCantidadSemanalOrdenSituacion(OrdenBean ordenBean)throws DAOException;
	
	public OrdenBean reporteVentaAnual(OrdenBean ordenBean)throws DAOException;
	public OrdenBean reporteVentaMensual(OrdenBean ordenBean)throws DAOException;
	public OrdenBean reporteVentaDiaria(OrdenBean ordenBean)throws DAOException;
	public OrdenBean reporteVentaSemanal(OrdenBean ordenBean)throws DAOException;
	
	public List<OrdenBean> reporteDetalleOrdenVentaAnual(OrdenBean ordenBean) throws DAOException;
	public List<OrdenBean> reporteDetalleOrdenVentaMensual(OrdenBean ordenBean) throws DAOException;
	public List<OrdenBean> reporteDetalleOrdenVentaDiaria(OrdenBean ordenBean) throws DAOException;
	public List<OrdenBean> reporteDetalleOrdenVentaSemanal(OrdenBean ordenBean) throws DAOException;
	
	public List<OrdenBean> reporteVentaMensualPeriodoArea(OrdenBean ordenBean) throws DAOException;
	public List<OrdenBean> reporteVentaMensualPeriodoBarra(OrdenBean ordenBean) throws DAOException;
	
	public List<OrdenBean> reporteDetalleAnualOrdenSituacion(OrdenBean ordenBean) throws DAOException;
	public List<OrdenBean> reporteDetalleMensualOrdenSituacion(OrdenBean ordenBean) throws DAOException;
	public List<OrdenBean> reporteDetalleDiariaOrdenSituacion(OrdenBean ordenBean) throws DAOException;
	public List<OrdenBean> reporteDetalleSemanalOrdenSituacion(OrdenBean ordenBean) throws DAOException;
	
	public List<OrdenBean> reporteCantidadAnualExamenesSolicitados(OrdenBean ordenBean) throws DAOException;
	public List<OrdenBean> reporteCantidadAnualExamenesSolicitadosTotal(OrdenBean ordenBean) throws DAOException;
	public List<OrdenBean> reporteCantidadMensualExamenesSolicitados(OrdenBean ordenBean) throws DAOException;
	public List<OrdenBean> reporteCantidadMensualExamenesSolicitadosTotal(OrdenBean ordenBean) throws DAOException;
	public List<OrdenBean> reporteCantidadDiarioExamenesSolicitados(OrdenBean ordenBean) throws DAOException;
	public List<OrdenBean> reporteCantidadDiarioExamenesSolicitadosTotal(OrdenBean ordenBean) throws DAOException;
	public List<OrdenBean> reporteCantidadSemanalExamenesSolicitados(OrdenBean ordenBean) throws DAOException;
	public List<OrdenBean> reporteCantidadSemanalExamenesSolicitadosTotal(OrdenBean ordenBean) throws DAOException;
	
  
}
