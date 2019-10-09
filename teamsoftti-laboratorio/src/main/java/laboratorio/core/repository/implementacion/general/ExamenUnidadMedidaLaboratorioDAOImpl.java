package laboratorio.core.repository.implementacion.general;
 
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import laboratorio.core.bean.general.ExamenUnidadMedidaLaboratorioBean;
import laboratorio.core.bean.general.TablaBean;
import laboratorio.core.entity.asistencial.laboratorio.ExamenUnidadMedida_laboratorio;
import laboratorio.core.entity.general.Tgemae02;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.general.ExamenUnidadMedidaLaboratorioDAO;



@Transactional
@Repository("ExamenUnidadMedidaLaboratorioDAO")
public class ExamenUnidadMedidaLaboratorioDAOImpl implements ExamenUnidadMedidaLaboratorioDAO {

	@PersistenceContext//(unitName="emf")
	private EntityManager em;
	
	@Override
	public boolean insertar(ExamenUnidadMedidaLaboratorioBean Tarifario) throws DAOException {
  
		Object idExamenAsociado= null; 
		Object nroPeriodo= null; 
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("examenes_laboratorio.registro");
		
		
			spq.setParameter("CODORGAN", Tarifario.getCodigoOrganizacion()); 
			spq.setParameter("CODINSTI", Tarifario.getCodigoInstitucion()); 
			spq.setParameter("CODSEDEI", Tarifario.getCodigoSede()); 
			spq.setParameter("NROVERSI", Tarifario.getNumeroVersion()); 
			spq.setParameter("DESCORTA", Tarifario.getDescripcion()); 
			spq.setParameter("ABREVIAT", Tarifario.getAbrev()); 
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
	public boolean actualizar(ExamenUnidadMedidaLaboratorioBean Tarifario) throws DAOException {
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
	public boolean eliminar(ExamenUnidadMedidaLaboratorioBean Tarifario) throws DAOException {
		

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
	public ExamenUnidadMedidaLaboratorioBean getBuscarPorObjecto(ExamenUnidadMedidaLaboratorioBean t) throws DAOException {
		
		return null;
	}

	@Override
	public List<ExamenUnidadMedidaLaboratorioBean> getBuscarPorFiltros(ExamenUnidadMedidaLaboratorioBean ExamenUnidadMedidaLaboratorioBean)
			throws DAOException {
		
		List<ExamenUnidadMedida_laboratorio> lstMaestra = null;	
		List<ExamenUnidadMedidaLaboratorioBean> lstTablaBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("examenUnidadMedida_laboratorio.listarPorFiltros");
			spq.setParameter("ABREVIATURA", ExamenUnidadMedidaLaboratorioBean.getAbrev()); 
			spq.setParameter("DESCRIPCION", ExamenUnidadMedidaLaboratorioBean.getDescripcion()); 
			if (spq.execute()) {
				lstMaestra =  spq.getResultList(); 
			} 
			if (lstMaestra != null && lstMaestra.size() > 0) {
				
				lstTablaBean = deListaTarifariolaboratorioAListaExamenUnidadMedidaLaboratorioBean(lstMaestra);
			}
			em.close();
			
		   
		return lstTablaBean;
	}

	@Override
	public boolean existe(ExamenUnidadMedidaLaboratorioBean t) throws DAOException {
		
		return false;
	}
 
	private List<ExamenUnidadMedidaLaboratorioBean> deListaTarifariolaboratorioAListaExamenUnidadMedidaLaboratorioBean(List<ExamenUnidadMedida_laboratorio> lstExamenUnidadMedida_laboratorio) {
		
		List<ExamenUnidadMedidaLaboratorioBean> lstlstExamenUnidadMedidaLaboratorioBean = null;
		
		if (lstExamenUnidadMedida_laboratorio != null && lstExamenUnidadMedida_laboratorio.size() > 0) {
			
			lstlstExamenUnidadMedidaLaboratorioBean = new ArrayList<ExamenUnidadMedidaLaboratorioBean>();
			
			for (int i = 0; i < lstExamenUnidadMedida_laboratorio.size(); i++) { 
				ExamenUnidadMedida_laboratorio entity = lstExamenUnidadMedida_laboratorio.get(i);
				ExamenUnidadMedidaLaboratorioBean bean = deEXamenUnidadMedidaAExamenUnidadMedidaLaboratorioBean(entity);
				
				lstlstExamenUnidadMedidaLaboratorioBean.add(bean);
			}
		}
		
		return lstlstExamenUnidadMedidaLaboratorioBean;
	}
	private ExamenUnidadMedidaLaboratorioBean deEXamenUnidadMedidaAExamenUnidadMedidaLaboratorioBean(ExamenUnidadMedida_laboratorio entity) {
		
		ExamenUnidadMedidaLaboratorioBean bean = null;
		
		if (entity != null) {
			
			bean = new ExamenUnidadMedidaLaboratorioBean(); 
			bean.setCodigo(entity.getCodigoUnidadMedida());	
			bean.setDescripcion(entity.getDescripcion());
			bean.setAbrev(entity.getAbrev());
			bean.setObservacion(entity.getObservacion());

		}
		
		return bean;
	}
 
	 private static String getTwoDecimals(double value){
	      DecimalFormat df = new DecimalFormat("0.00"); 
	      return df.format(value);
	    }
}
