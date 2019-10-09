package laboratorio.core.repository.interfaces.general;

import java.util.List;

import laboratorio.core.bean.general.UbigeoBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.BaseDAO;



public interface UbigeoDAO extends BaseDAO<UbigeoBean>{

	public List<UbigeoBean> listarRegion() throws DAOException;
	public List<UbigeoBean> listarProvincia(UbigeoBean ubigeoBean) throws DAOException;
	public List<UbigeoBean> listarDistrito(UbigeoBean ubigeoBean)throws DAOException;
	public UbigeoBean buscarEquivalenteReniecMinsa(UbigeoBean ubigeoBean);
}
