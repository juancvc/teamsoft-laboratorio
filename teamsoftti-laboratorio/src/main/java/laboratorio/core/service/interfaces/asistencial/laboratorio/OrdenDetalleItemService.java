package laboratorio.core.service.interfaces.asistencial.laboratorio;
 
 
import java.util.List;

import laboratorio.core.bean.asistencial.laboratorio.OrdenBean;
import laboratorio.core.bean.asistencial.laboratorio.OrdenDetalleItemBean;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.BaseService;

public interface OrdenDetalleItemService extends BaseService<OrdenDetalleItemBean>{
	public List<OrdenDetalleItemBean> listarAnalisisResultados(OrdenDetalleItemBean ordenDetalleItemBean) throws ServiceException;
	public List<OrdenDetalleItemBean> listarAnalisisResultadosPorOrden(OrdenBean OrdenBean) throws ServiceException;
}
