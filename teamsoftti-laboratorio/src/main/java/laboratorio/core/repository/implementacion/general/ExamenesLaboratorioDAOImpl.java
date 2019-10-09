package laboratorio.core.repository.implementacion.general;
 
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import laboratorio.core.bean.general.ExamenesLaboratorioBean;
import laboratorio.core.bean.general.TablaBean;
import laboratorio.core.entity.asistencial.laboratorio.Examenes_laboratorio;
import laboratorio.core.entity.general.Tgemae02;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.general.ExamenesLaboratorioDAO;



@Transactional
@Repository("ExamenesLaboratorioDAO")
public class ExamenesLaboratorioDAOImpl implements ExamenesLaboratorioDAO {

	@PersistenceContext//(unitName="emf")
	private EntityManager em;
	
	@Override
	public boolean insertar(ExamenesLaboratorioBean Tarifario) throws DAOException {
  
		Object idExamenAsociado= null; 
		Object nroPeriodo= null; 
		
		System.out.println("Tarifario.getTarifarioBean().getCodigo() " + Tarifario.getTarifarioBean().getCodigo());
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("examenes_laboratorio.registro");
		
		
			spq.setParameter("CODORGAN", Tarifario.getCodigoOrganizacion()); 
			spq.setParameter("CODINSTI", Tarifario.getCodigoInstitucion()); 
			spq.setParameter("CODSEDEI", Tarifario.getCodigoSede()); 
			spq.setParameter("NROVERSI", Tarifario.getNumeroVersion()); 
			spq.setParameter("CODTARIF", Tarifario.getTarifarioBean().getCodigo()); 
			spq.setParameter("TIPO",     Tarifario.getTipoExamenLab().getCodReg()); 
			spq.setParameter("DESCORTA", Tarifario.getDescripcion()); 
			spq.setParameter("ABREVIAT", Tarifario.getAbrev()); 
			spq.setParameter("CODUNIME", Tarifario.getUnidadMedida().getCodReg()); 
			spq.setParameter("AUDUSUCR", Tarifario.getCodigoUsuarioCreacion()); 
			spq.setParameter("AUPCIPCR", Tarifario.getIpCreacion());  
			spq.setParameter("OBSERVAC", Tarifario.getObservacion());  
			
			spq.execute();
			
			idExamenAsociado = spq.getOutputParameterValue(5);
		//	nroPeriodo = spq.getOutputParameterValue(16);
			if (idExamenAsociado != null) {
				Tarifario.setCodigo(idExamenAsociado.toString());
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
	public boolean actualizar(ExamenesLaboratorioBean Tarifario) throws DAOException {
		Object idTarifario= null; 
	//	Object nroPeriodo= null; 
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("examenes_laboratorio.actualizar");
			spq.setParameter("CODTARIF", Tarifario.getCodigo()); 
			spq.setParameter("TIPO", ""); 

			spq.setParameter("NOMPRODU", Tarifario.getDescripcion()); 
			spq.setParameter("TIPOUNID", ""); 
			spq.setParameter("VALREFIN", ""); 
			spq.setParameter("VALREFFI", ""); 
			spq.setParameter("AUCDUSCR", Tarifario.getCodigoUsuarioCreacion());  
			
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
	public boolean eliminar(ExamenesLaboratorioBean Tarifario) throws DAOException {
		

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
	public ExamenesLaboratorioBean getBuscarPorObjecto(ExamenesLaboratorioBean t) throws DAOException {
		
		return null;
	}

	@Override
	public List<ExamenesLaboratorioBean> getBuscarPorFiltros(ExamenesLaboratorioBean ExamenesLaboratorioBean)
			throws DAOException {
		
		List<Examenes_laboratorio> lstMaestra = null;	
		List<ExamenesLaboratorioBean> lstTablaBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("examenes_laboratorio.listarPorFiltros");
			spq.setParameter("CODPROCE", ExamenesLaboratorioBean.getTarifarioBean().getCodigo()); 
			spq.setParameter("TIPO",     ExamenesLaboratorioBean.getTipoExamenAsoc()); 
			spq.setParameter("COPROASO", ExamenesLaboratorioBean.getCodigo()); 
			if (spq.execute()) {
				lstMaestra =  spq.getResultList(); 
			} 
			if (lstMaestra != null && lstMaestra.size() > 0) {
				
				lstTablaBean = deListaTarifariolaboratorioAListaExamenesLaboratorioBean(lstMaestra);
			}
			em.close();
			
		   
		return lstTablaBean;
	}

	@Override
	public boolean existe(ExamenesLaboratorioBean t) throws DAOException {
		
		return false;
	}
 
	private List<ExamenesLaboratorioBean> deListaTarifariolaboratorioAListaExamenesLaboratorioBean(List<Examenes_laboratorio> lstExamenes_laboratorio) {
		
		List<ExamenesLaboratorioBean> lstlstExamenesLaboratorioBean = null;
		
		if (lstExamenes_laboratorio != null && lstExamenes_laboratorio.size() > 0) {
			
			lstlstExamenesLaboratorioBean = new ArrayList<ExamenesLaboratorioBean>();
			
			for (int i = 0; i < lstExamenes_laboratorio.size(); i++) { 
				Examenes_laboratorio entity = lstExamenes_laboratorio.get(i);
				ExamenesLaboratorioBean bean = deTarifarioAExamenesLaboratorioBean(entity);
				
				lstlstExamenesLaboratorioBean.add(bean);
			}
		}
		
		return lstlstExamenesLaboratorioBean;
	}
	private ExamenesLaboratorioBean deTarifarioAExamenesLaboratorioBean(Examenes_laboratorio entity) {
		
		ExamenesLaboratorioBean bean = null;
		
		if (entity != null) {
			
			bean = new ExamenesLaboratorioBean(); 
			bean.setCodigo(entity.getCodigoExamenAsociado());	
			bean.setDescripcion(entity.getDescripcionCorta());
			bean.setAbrev(entity.getAbreviatura());
			bean.setTipoExamenAsoc(entity.getTipo());
			bean.getTipoExamenLab().setCodReg(entity.getTipo());
			bean.setCodigoOrganizacion(entity.getCodigoOrganizacion());
			bean.setCodigoInstitucion(entity.getCodigoInstitucion());
			bean.setCodigoSede(entity.getCodigoSede());
			bean.setNombreUsuarioCreacion(entity.getUsuarioCreacion());
			bean.setCodigoOrganizacion(entity.getCodigoOrganizacion());
			bean.getTarifarioBean().setDescripcion(entity.getNombreExamen());
			bean.getTarifarioBean().getTipo().setNombreCorto(entity.getTipoLaboratorio());
			bean.getTarifarioBean().setCodigo(entity.getCodidoTarifario());
			bean.getExamenUnidadMedidaLaboratorioBean().setAbrev(entity.getAbreviaturaUnidadMedida());
			bean.getExamenUnidadMedidaLaboratorioBean().setCodigo(entity.getCodigoUnidadMedida());
			bean.getExamenUnidadMedidaLaboratorioBean().setDescripcion(entity.getDescripcionUnidadMedida());
		}
		
		return bean;
	}
 
	 private static String getTwoDecimals(double value){
	      DecimalFormat df = new DecimalFormat("0.00"); 
	      return df.format(value);
	    }
}
