package laboratorio.core.repository.interfaces.asistencial.banco;
  
 
import laboratorio.core.bean.asistencial.banco.DonanteBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.BaseDAO;

public interface DonanteDAO  extends BaseDAO<DonanteBean> {
	
	public DonanteBean buscarUltimaDonacion(DonanteBean DonanteBean); 
	
	public boolean insertarVoluntario(DonanteBean t) throws DAOException;
  
}
