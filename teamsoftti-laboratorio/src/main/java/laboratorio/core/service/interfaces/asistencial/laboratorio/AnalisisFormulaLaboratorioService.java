package laboratorio.core.service.interfaces.asistencial.laboratorio;

import laboratorio.core.bean.asistencial.laboratorio.AnalisisFormulaLaboratorioBean;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.BaseService;
public interface AnalisisFormulaLaboratorioService extends BaseService<AnalisisFormulaLaboratorioBean>{ 
	public AnalisisFormulaLaboratorioBean formularResultado(AnalisisFormulaLaboratorioBean analisisFormulaLaboratorioBean)throws ServiceException;
}
