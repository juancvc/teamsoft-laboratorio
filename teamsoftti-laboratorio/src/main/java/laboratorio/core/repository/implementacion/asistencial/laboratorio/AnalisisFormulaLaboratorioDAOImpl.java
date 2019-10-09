package laboratorio.core.repository.implementacion.asistencial.laboratorio;
 

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import laboratorio.core.bean.asistencial.laboratorio.AnalisisFormulaLaboratorioBean;
import laboratorio.core.bean.general.PersonaBean;
import laboratorio.core.entity.asistencial.laboratorio.Analisis_formula_laboratorio;
import laboratorio.core.entity.general.Persona;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.asistencial.laboratorio.AnalisisFormulaLaboratorioDAO;



@Transactional
@Repository("AnalisisFormulaLaboratorioDAO")
public class AnalisisFormulaLaboratorioDAOImpl implements AnalisisFormulaLaboratorioDAO {

	@PersistenceContext//(unitName="emf")
	private EntityManager em;
	
	@Override
	public boolean insertar(AnalisisFormulaLaboratorioBean Tarifario) throws DAOException {
  
		Object idFormula= null; 
		Object nroPeriodo= null; 
		
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("analisis_formula_laboratorio.registro");
		
		
			spq.setParameter("CODORGAN", Tarifario.getCodigoOrganizacion()); 
			spq.setParameter("CODINSTI", Tarifario.getCodigoInstitucion()); 
			spq.setParameter("CODSEDEI", Tarifario.getCodigoSede()); 
			spq.setParameter("NROVERSI", Tarifario.getNumeroVersion()); 
			spq.setParameter("COORGPAF", Tarifario.getExamenesLaboratorioFormulaBean().getCodigoOrganizacion()); 
			spq.setParameter("COINSPAF", Tarifario.getExamenesLaboratorioFormulaBean().getCodigoInstitucion()); 
			spq.setParameter("COSEDPAF", Tarifario.getExamenesLaboratorioFormulaBean().getCodigoSede()); 
			spq.setParameter("CODPROAF", Tarifario.getExamenesLaboratorioFormulaBean().getCodigo()); 
			spq.setParameter("COPROAS1", Tarifario.getExamenesLaboratorioBean1().getCodigo()); 
			spq.setParameter("COPROAS2", Tarifario.getExamenesLaboratorioBean2().getCodigo()); 
			spq.setParameter("COPROAS3", Tarifario.getExamenesLaboratorioBean3().getCodigo()); 
			spq.setParameter("COPROAS4", Tarifario.getExamenesLaboratorioBean4().getCodigo()); 
			spq.setParameter("COPROAS5", Tarifario.getExamenesLaboratorioBean5().getCodigo()); 
			spq.setParameter("COPROAS6", Tarifario.getExamenesLaboratorioBean6().getCodigo()); 
			spq.setParameter("COPROAS7", Tarifario.getExamenesLaboratorioBean7().getCodigo()); 
			
			spq.setParameter("VALOR1",   Tarifario.getValor1()); 
			spq.setParameter("VALOR2",   Tarifario.getValor2()); 
			spq.setParameter("VALOR3",   Tarifario.getValor3()); 
			spq.setParameter("VALOR4",   Tarifario.getValor4()); 
			spq.setParameter("VALOR5",   Tarifario.getValor5()); 
			spq.setParameter("VALOR6",   Tarifario.getValor6()); 
			spq.setParameter("VALOR7",   Tarifario.getValor7()); 
			spq.setParameter("VALORFOR", Tarifario.getCadenaFormula()); 
			spq.setParameter("AUDUSUCR", Tarifario.getCodigoUsuarioCreacion()); 
			spq.setParameter("AUPCIPCR", Tarifario.getIpCreacion());  
			spq.setParameter("OBSERVAC", Tarifario.getObservacion());  
			
			spq.execute();
			
			idFormula = spq.getOutputParameterValue(5);
		//	nroPeriodo = spq.getOutputParameterValue(16);
			if (idFormula != null) {
				Tarifario.setCodigo(idFormula.toString());
			//	Tarifario.setNumeroPeriodo(nroPeriodo.toString());
				sw=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			sw=false; 
		}finally{
			em.close();
		}
		return sw;
	}

	@Override
	public boolean actualizar(AnalisisFormulaLaboratorioBean Tarifario) throws DAOException {
		Object idTarifario= null; 
	//	Object nroPeriodo= null; 
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("analisis_formula_laboratorio.actualizar");

			
			spq.setParameter("CODORGAN", Tarifario.getCodigoOrganizacion()); 
			spq.setParameter("CODINSTI", Tarifario.getCodigoInstitucion()); 
			spq.setParameter("CODSEDEI", Tarifario.getCodigoSede()); 
			spq.setParameter("CODFORMU", Tarifario.getCodigo()); 
			spq.setParameter("CODPROAF", Tarifario.getExamenesLaboratorioFormulaBean().getCodigo()); 
			spq.setParameter("COPROAS1", Tarifario.getExamenesLaboratorioBean1().getCodigo()); 
			spq.setParameter("COPROAS2", Tarifario.getExamenesLaboratorioBean2().getCodigo()); 
			spq.setParameter("COPROAS3", Tarifario.getExamenesLaboratorioBean3().getCodigo()); 
			spq.setParameter("COPROAS4", Tarifario.getExamenesLaboratorioBean4().getCodigo()); 
			spq.setParameter("COPROAS5", Tarifario.getExamenesLaboratorioBean5().getCodigo()); 
			spq.setParameter("COPROAS6", Tarifario.getExamenesLaboratorioBean6().getCodigo()); 
			spq.setParameter("COPROAS7", Tarifario.getExamenesLaboratorioBean7().getCodigo()); 
			
			spq.setParameter("VALOR1",   Tarifario.getValor1()); 
			spq.setParameter("VALOR2",   Tarifario.getValor2()); 
			spq.setParameter("VALOR3",   Tarifario.getValor3()); 
			spq.setParameter("VALOR4",   Tarifario.getValor4()); 
			spq.setParameter("VALOR5",   Tarifario.getValor5()); 
			spq.setParameter("VALOR6",   Tarifario.getValor6()); 
			spq.setParameter("VALOR7",   Tarifario.getValor7()); 
			spq.setParameter("VALORFOR", Tarifario.getCadenaFormula()); 
			spq.setParameter("AUDUSUCR", Tarifario.getCodigoUsuarioCreacion()); 
			spq.setParameter("AUPCIPCR", Tarifario.getIpCreacion());  
			spq.setParameter("OBSERVAC", Tarifario.getObservacion());  
			
			spq.execute();
			
		//	idTarifario = spq.getOutputParameterValue(1);
		//	nroPeriodo = spq.getOutputParameterValue(16);
			//if (idTarifario != null) {
				//Tarifario.setCodigo(idTarifario.toString());
			//	Tarifario.setNumeroPeriodo(nroPeriodo.toString());
				sw=true;
			//}
		} catch (Exception e) {
			e.printStackTrace();
			sw=false; 
		}finally{
			em.close();
		}
		return sw;
	}

	@Override
	public boolean eliminar(AnalisisFormulaLaboratorioBean Tarifario) throws DAOException {
		

		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("tarifario.eliminar");
			
			spq.setParameter("CODTARIF", Tarifario.getCodigo());  
			spq.setParameter("AUCDUSMO", Tarifario.getCodigoUsuarioModificacion());
			spq.setParameter("AUPCIPMO", Tarifario.getIpModificacion());

			
			spq.execute();
			
			sw=true;
		} catch (Exception e) {
			e.printStackTrace();
			sw=false; 
		}finally{
			em.close();
		}
		return sw;
	}

	@Override
	public AnalisisFormulaLaboratorioBean getBuscarPorObjecto(AnalisisFormulaLaboratorioBean t) throws DAOException {
		
		return null;
	}

	@Override
	public List<AnalisisFormulaLaboratorioBean> getBuscarPorFiltros(AnalisisFormulaLaboratorioBean analisisFormulaLaboratorioBean)
			throws DAOException {
		
		List<Analisis_formula_laboratorio> lstMaestra = null;	
		List<AnalisisFormulaLaboratorioBean> lstTablaBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("analisis_formula_laboratorio.listarPorFiltros");
			spq.setParameter("CODPROCE", analisisFormulaLaboratorioBean); 
			spq.setParameter("TIPO", 	 ""); 
			spq.setParameter("COPROASO", ""); 
			if (spq.execute()) {
				lstMaestra =  spq.getResultList(); 
			} 
			if (lstMaestra != null && lstMaestra.size() > 0) {
				
				lstTablaBean = deListaTarifariolaboratorioAListaAnalisisFormulaLaboratorioBean(lstMaestra);
			}
			em.close();
			
		   
		return lstTablaBean;
	}

	@Override
	public boolean existe(AnalisisFormulaLaboratorioBean t) throws DAOException {
		
		return false;
	}
 
	private List<AnalisisFormulaLaboratorioBean> deListaTarifariolaboratorioAListaAnalisisFormulaLaboratorioBean(List<Analisis_formula_laboratorio> lstAnalisis_formula_laboratorio) {
		
		List<AnalisisFormulaLaboratorioBean> lstlstAnalisisFormulaLaboratorioBean = null;
		
		if (lstAnalisis_formula_laboratorio != null && lstAnalisis_formula_laboratorio.size() > 0) {
			
			lstlstAnalisisFormulaLaboratorioBean = new ArrayList<AnalisisFormulaLaboratorioBean>();
			
			for (int i = 0; i < lstAnalisis_formula_laboratorio.size(); i++) { 
				Analisis_formula_laboratorio entity = lstAnalisis_formula_laboratorio.get(i);
				AnalisisFormulaLaboratorioBean bean = deTarifarioAAnalisisFormulaLaboratorioBean(entity);
				
				lstlstAnalisisFormulaLaboratorioBean.add(bean);
			}
		}
		
		return lstlstAnalisisFormulaLaboratorioBean;
	}
	private AnalisisFormulaLaboratorioBean deTarifarioAAnalisisFormulaLaboratorioBean(Analisis_formula_laboratorio entity) {
		
		AnalisisFormulaLaboratorioBean bean = null;
		
		if (entity != null) {
			
			bean = new AnalisisFormulaLaboratorioBean(); 
			bean.setCodigo(entity.getCodigoFormulaLaboratorio());	
			bean.setCodigoOrganizacion(entity.getCodigoOrganizacion());
			bean.setCodigoInstitucion(entity.getCodigoInstitucion());
			bean.setCodigoSede(entity.getCodigoSede());
			bean.setValor1(entity.getValor1());
			bean.setValor2(entity.getValor2());
			bean.setValor3(entity.getValor3());
			bean.setValor4(entity.getValor4());
			bean.setValor5(entity.getValor5());
			bean.setValor6(entity.getValor6());
			bean.setValor7(entity.getValor7());
			bean.setObservacion(entity.getObservacion());
			bean.getExamenesLaboratorioBean1().setCodigo(entity.getCodigoAnalisisAsociado());
			bean.getExamenesLaboratorioBean2().setCodigo(entity.getCodigoAnalisisAsociado2());
			bean.getExamenesLaboratorioBean3().setCodigo(entity.getCodigoAnalisisAsociado3());
			bean.getExamenesLaboratorioBean4().setCodigo(entity.getCodigoAnalisisAsociado4());
			bean.getExamenesLaboratorioBean5().setCodigo(entity.getCodigoAnalisisAsociado5());
			bean.getExamenesLaboratorioBean6().setCodigo(entity.getCodigoAnalisisAsociado6());
			bean.getExamenesLaboratorioBean7().setCodigo(entity.getCodigoAnalisisAsociado7());
			bean.setResultado(entity.getResultado());
		

		}
		
		return bean;
	}
 
	 private static String getTwoDecimals(double value){
	      DecimalFormat df = new DecimalFormat("0.00"); 
	      return df.format(value);
	    }

	@Override
	public AnalisisFormulaLaboratorioBean formularResultado(
			AnalisisFormulaLaboratorioBean analisisFormulaLaboratorioBean) throws DAOException {
		List<Analisis_formula_laboratorio> lstAnalisisFormulaLaboratorio = null;	
		AnalisisFormulaLaboratorioBean oAnalisisFormulaLaboratorioBean = null;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("analisis_formula_laboratorio.formularResultados");
			spq.setParameter("VALOR1", analisisFormulaLaboratorioBean.getVal01());
			spq.setParameter("VALOR2", analisisFormulaLaboratorioBean.getVal02()); 
			spq.setParameter("VALOR3", analisisFormulaLaboratorioBean.getVal03()); 
			spq.setParameter("VALOR4", analisisFormulaLaboratorioBean.getVal04()); 
			spq.setParameter("VALOR5", analisisFormulaLaboratorioBean.getVal05()); 
			spq.setParameter("VALOR6", analisisFormulaLaboratorioBean.getVal06()); 
			spq.setParameter("VALOR7", analisisFormulaLaboratorioBean.getVal07()); 
			spq.setParameter("CODORDDE", analisisFormulaLaboratorioBean.getOrdenDetalleBean().getCodigo()); 
			spq.setParameter("COORPRAS", analisisFormulaLaboratorioBean.getExamenesLaboratorioFormulaBean().getCodigoOrganizacion()); 
			spq.setParameter("COINPRAS", analisisFormulaLaboratorioBean.getExamenesLaboratorioFormulaBean().getCodigoInstitucion()); 
			spq.setParameter("COSEPRAS", analisisFormulaLaboratorioBean.getExamenesLaboratorioFormulaBean().getCodigoSede()); 
			spq.setParameter("PROCEASO", analisisFormulaLaboratorioBean.getExamenesLaboratorioFormulaBean().getCodigo()); 
			spq.setParameter("ABREV", analisisFormulaLaboratorioBean.getAbreviat1()); 
			spq.setParameter("ABREV2", analisisFormulaLaboratorioBean.getAbreviat2()); 
			spq.setParameter("ABREV3", analisisFormulaLaboratorioBean.getAbreviat3()); 
			spq.setParameter("ABREV4", analisisFormulaLaboratorioBean.getAbreviat4()); 
			spq.setParameter("ABREV5", analisisFormulaLaboratorioBean.getAbreviat5()); 
			spq.setParameter("ABREV6", analisisFormulaLaboratorioBean.getAbreviat6()); 
			spq.setParameter("ABREV7", analisisFormulaLaboratorioBean.getAbreviat7()); 
			
			if (spq.execute()) {
				lstAnalisisFormulaLaboratorio =  spq.getResultList(); 
			} 
			if (lstAnalisisFormulaLaboratorio != null && lstAnalisisFormulaLaboratorio.size() > 0) {
				
				oAnalisisFormulaLaboratorioBean = deTarifarioAAnalisisFormulaLaboratorioBean(lstAnalisisFormulaLaboratorio.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			em.close();
		} 
		return oAnalisisFormulaLaboratorioBean;
	}
}
