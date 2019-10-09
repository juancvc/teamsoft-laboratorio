package laboratorio.core.service.interfaces.asistencial.banco;
 
 
import laboratorio.core.bean.asistencial.banco.DonanteBean;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.BaseService;

public interface DonanteService extends BaseService<DonanteBean>{
 
	public DonanteBean buscarUltimaDonacion(DonanteBean DonanteBean);
	
	public boolean insertarVoluntario(DonanteBean t) throws  ServiceException;
}
