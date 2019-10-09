package laboratorio.core.repository.interfaces.asistencial.laboratorio;

import laboratorio.core.bean.asistencial.laboratorio.AnalisisFormulaLaboratorioBean;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.BaseDAO;
 
 
public interface AnalisisFormulaLaboratorioDAO extends BaseDAO<AnalisisFormulaLaboratorioBean>{
	
	public AnalisisFormulaLaboratorioBean formularResultado(AnalisisFormulaLaboratorioBean analisisFormulaLaboratorioBean)throws DAOException;
 
}
