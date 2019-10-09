package laboratorio.core.service.interfaces.asistencial.banco;
 
 
import java.util.List;

import laboratorio.core.bean.asistencial.banco.PreDonanteBean;
import laboratorio.core.entity.asistencial.banco.InterUAL;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.BaseService;

public interface PreDonanteService extends BaseService<PreDonanteBean>{
	
	public List<PreDonanteBean> litarResultadoFisicoFlebo(PreDonanteBean PostulanteBean);
  
	public boolean insertarIterUal (InterUAL interual) ;
	
}
