package laboratorio.core.service.interfaces.asistencial.laboratorio;
 
 
import java.util.List;

import laboratorio.core.bean.asistencial.laboratorio.OrdenDetalleBean;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.BaseService;
public interface OrdenDetalleService extends BaseService<OrdenDetalleBean>{
 
	public boolean resultadoModificar(OrdenDetalleBean ordenDetalleBean)throws ServiceException;
	public List<OrdenDetalleBean > getBuscarPorFiltrosReporte(OrdenDetalleBean ordenDetalleBean)throws ServiceException;
}
