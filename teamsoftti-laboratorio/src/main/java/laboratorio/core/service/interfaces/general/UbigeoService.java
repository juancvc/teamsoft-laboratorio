package laboratorio.core.service.interfaces.general;
import java.util.List;

import laboratorio.core.bean.general.UbigeoBean;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.BaseService;




public interface UbigeoService extends BaseService<UbigeoBean>{
	public List<UbigeoBean> listarRegion() throws ServiceException;
	public List<UbigeoBean> listarProvincia(UbigeoBean ubigeoBean) throws ServiceException;
	public List<UbigeoBean> listarDistrito(UbigeoBean ubigeoBean)throws ServiceException;
	public UbigeoBean buscarEquivalenteReniecMinsa(UbigeoBean ubigeoBean);
}
