package laboratorio.core.service.interfaces.seguridad;

import java.util.List;

import laboratorio.core.bean.seguridad.AuditoriaAccesoBean;
import laboratorio.core.bean.seguridad.AuditoriaBean;
import laboratorio.core.bean.seguridad.AuditoriaDetalleBean;
import laboratorio.core.bean.seguridad.AuditoriaTablaBean;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.BaseService;


public interface AuditoriaService extends BaseService<AuditoriaBean>{
	
	public List<AuditoriaTablaBean> listarTablas(AuditoriaTablaBean prmAuditoriaTablaBean) throws ServiceException;
	
	public List<AuditoriaDetalleBean> listarAuditoriaDetalle(AuditoriaDetalleBean prmAuditoriaDetalleBean) throws ServiceException;
	
	public boolean insertarAuditoriaAcceso(AuditoriaAccesoBean prmAuditoriaAccesoBean) throws ServiceException;
	
	public List<AuditoriaAccesoBean> listarAuditoriaAcceso(AuditoriaAccesoBean prmAuditoriaAccesoBean) throws ServiceException;
	
}