package laboratorio.core.repository.interfaces.asistencial.banco;
  
  
import java.util.List;

import laboratorio.core.bean.asistencial.banco.PostulanteBean;
import laboratorio.core.bean.asistencial.banco.PreDonanteBean;
import laboratorio.core.entity.asistencial.banco.InterUAL;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.BaseDAO;

public interface PreDonanteDAO  extends BaseDAO<PreDonanteBean> {
	
	public List<PreDonanteBean> litarResultadoFisicoFlebo(PreDonanteBean PostulanteBean);
	
	public boolean insertarIterUal (InterUAL interual);
  
}
