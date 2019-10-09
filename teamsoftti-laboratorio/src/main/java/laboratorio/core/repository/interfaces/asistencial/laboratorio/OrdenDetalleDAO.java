package laboratorio.core.repository.interfaces.asistencial.laboratorio;
  
 
import java.util.List;

import laboratorio.core.bean.asistencial.laboratorio.OrdenDetalleBean;
import laboratorio.core.bean.general.PersonaBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.BaseDAO;

public interface OrdenDetalleDAO  extends BaseDAO<OrdenDetalleBean> {
	public boolean resultadoModificar(OrdenDetalleBean ordenDetalleBean)throws DAOException;
	public List<OrdenDetalleBean > getBuscarPorFiltrosReporte(OrdenDetalleBean ordenDetalleBean)throws DAOException;
  
}
