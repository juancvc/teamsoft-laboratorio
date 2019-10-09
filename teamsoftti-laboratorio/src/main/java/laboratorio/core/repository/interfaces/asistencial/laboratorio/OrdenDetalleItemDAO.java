package laboratorio.core.repository.interfaces.asistencial.laboratorio;
  
 

import java.util.List;

import laboratorio.core.bean.asistencial.laboratorio.OrdenBean;
import laboratorio.core.bean.asistencial.laboratorio.OrdenDetalleItemBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.BaseDAO;

public interface OrdenDetalleItemDAO  extends BaseDAO<OrdenDetalleItemBean> {
	public List<OrdenDetalleItemBean> listarAnalisisResultados(OrdenDetalleItemBean ordenDetalleItemBean) throws DAOException;
	public List<OrdenDetalleItemBean> listarAnalisisResultadosPorOrden(OrdenBean OrdenBean) throws DAOException;
}
