package laboratorio.core.repository.implementacion.asistencial.laboratorio;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import laboratorio.core.bean.asistencial.laboratorio.OrdenBean;
import laboratorio.core.bean.asistencial.laboratorio.OrdenDetalleItemBean;
import laboratorio.core.entity.asistencial.laboratorio.Orden_laboratorio_detalle_item;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.asistencial.laboratorio.OrdenDetalleItemDAO; 

@Transactional
@Repository("OrdenDetalleItemDAO")
public class OrdenDetalleItemDAOImp implements OrdenDetalleItemDAO {

	@PersistenceContext//(unitName="emf")
	private EntityManager em; 
	
	
	public OrdenDetalleItemBean getOrdenDetalleItemBean(Object object){
		return (OrdenDetalleItemBean)object;
	}
	
	@Override
	public boolean insertar(OrdenDetalleItemBean ordenDetalleItemBean) throws DAOException {

		Object id= null;   
		Object nroPeriodo= null;   
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("ordenDetalleItem.insertar"); 
			spq.setParameter("p_CODORGAN", ordenDetalleItemBean.getCodigoOrganizacion());
			spq.setParameter("p_CODINSTI", ordenDetalleItemBean.getCodigoInstitucion());
			spq.setParameter("p_CODSEDEI", ordenDetalleItemBean.getCodigoSede()); 
			
			spq.setParameter("p_CODORDDE", ordenDetalleItemBean.getOrdenDetalleBean().getCodigo()); 
			spq.setParameter("p_NRVEORDE", ordenDetalleItemBean.getOrdenDetalleBean().getNumeroVersion()); 
			spq.setParameter("p_NROPORDE", ordenDetalleItemBean.getOrdenDetalleBean().getNumeroPeriodo()); 
			spq.setParameter("p_AUCDUSCR", ordenDetalleItemBean.getCodigoUsuarioCreacion());
			spq.setParameter("p_AUPCIPCR", ordenDetalleItemBean.getIpCreacion());
			 
			
			
			spq.execute();
			
			id = spq.getOutputParameterValue(4); 
			//nroPeriodo = spq.getOutputParameterValue(9); 
			if (id != null) {
				ordenDetalleItemBean.setCodigo(id.toString());
				//ordenDetalleItemBean.setNumeroPeriodo(nroPeriodo.toString()); 
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
	public boolean actualizar(OrdenDetalleItemBean ordenDetalleItemBean) throws DAOException {

		boolean sw=false;
		
		
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("ordenDetalleItem.modificar"); 
			spq.setParameter("p_CODORGAN", ordenDetalleItemBean.getCodigoOrganizacion());
			spq.setParameter("p_CODINSTI", ordenDetalleItemBean.getCodigoInstitucion());
			spq.setParameter("p_CODSEDEI", ordenDetalleItemBean.getCodigoSede()); 
			spq.setParameter("p_CODORDAS", ordenDetalleItemBean.getCodigo());
			spq.setParameter("p_CODORDEN", ordenDetalleItemBean.getOrdenDetalleBean().getCodigo());
			spq.setParameter("p_NROPERIO", ordenDetalleItemBean.getNumeroPeriodo()); 
			spq.setParameter("p_RESULTADO", ordenDetalleItemBean.getResultado()); 
			spq.setParameter("p_TIPO",      ordenDetalleItemBean.getExamenesLaboratorioBean().getTipoExamenAsoc()); 
			spq.setParameter("p_AUCDUSMO", ordenDetalleItemBean.getCodigoUsuarioModificacion());
			spq.setParameter("p_AUPCIPMO", ordenDetalleItemBean.getIpModificacion()); 
			
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
	public boolean eliminar(OrdenDetalleItemBean ordenDetalleItemBean) throws DAOException {

		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("ordenDetalleItem.eliminar"); 
			spq.setParameter("p_CODORGAN", ordenDetalleItemBean.getCodigoOrganizacion());
			spq.setParameter("p_CODINSTI", ordenDetalleItemBean.getCodigoInstitucion());
			spq.setParameter("p_CODSEDEI", ordenDetalleItemBean.getCodigoSede()); 
			spq.setParameter("p_NROPERIO", ordenDetalleItemBean.getNumeroPeriodo()); 
			spq.setParameter("p_CODORDEN", ordenDetalleItemBean.getCodigo());
		
			spq.setParameter("p_TG1TPANU", ordenDetalleItemBean.getMotivoAnular().getCodReg()); 
			spq.setParameter("p_MOTIVOAN", ordenDetalleItemBean.getDetalleMotivoAnular()); 
			spq.setParameter("p_AUCDUSMO", ordenDetalleItemBean.getCodigoUsuarioModificacion());
			spq.setParameter("p_AUPCIPMO", ordenDetalleItemBean.getIpModificacion()); 
			
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
	public OrdenDetalleItemBean getBuscarPorObjecto(OrdenDetalleItemBean t) throws DAOException {

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenDetalleItemBean> getBuscarPorFiltros(OrdenDetalleItemBean OrdenDetalleItemBean) throws DAOException {
		List<Orden_laboratorio_detalle_item> lstOrden = null;	
		List<OrdenDetalleItemBean> lstOrdenDetalleItemBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("ordenDetalleItem.buscarPorFiltros");   
			spq.setParameter("p_FECDESDE", OrdenDetalleItemBean.getFechaDesde()); 
			spq.setParameter("p_FECHASTA", OrdenDetalleItemBean.getFechaHasta()); 
			spq.setParameter("p_SITUACRG", OrdenDetalleItemBean.getSituacion().getCodReg()); 
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenDetalleItemBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenDetalleItemBean;
	}
	
	 

private List<OrdenDetalleItemBean> deListaObjetoAListaObjetoBean(List<Orden_laboratorio_detalle_item> lstOrden) {
		
		List<OrdenDetalleItemBean> lstOrdenDetalleItemBean = null;
		
		if (lstOrden != null && lstOrden.size() > 0) {
			
			lstOrdenDetalleItemBean = new ArrayList<OrdenDetalleItemBean>();
			
			for (int i = 0; i < lstOrden.size(); i++) { 
				Orden_laboratorio_detalle_item entity = lstOrden.get(i);
				OrdenDetalleItemBean bean = deObjetoAObjetoBean(entity);
				
				lstOrdenDetalleItemBean.add(bean);
			}
		}
		
		return lstOrdenDetalleItemBean;
	}
	
	private OrdenDetalleItemBean deObjetoAObjetoBean(Orden_laboratorio_detalle_item entity) {
		
		OrdenDetalleItemBean bean = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (entity != null) {
			
			bean = new OrdenDetalleItemBean();
			bean.setCodigo(entity.getId().getCodOrddetalleItem());  
			bean.setCodigoInstitucion(entity.getId().getCodinsti());  
			bean.setCodigoSede(entity.getId().getCodsedei());  
			bean.setCodigoOrganizacion(entity.getId().getCodorgan());
			bean.setNumeroVersion(entity.getId().getNroversi());
			bean.setNumeroPeriodo(entity.getId().getNroperio()); 
			
			bean.getSituacion().setCodReg(entity.getSituacRg()); 
			bean.setResultado(entity.getResultado());
			bean.getExamenesLaboratorioBean().setCodigo(entity.getCodigoExamenLaboratorio());
			bean.getExamenesLaboratorioBean().setNumeroPeriodo(entity.getNroPeriodoExamenLaboratorio());
			bean.getExamenesLaboratorioBean().setNumeroVersion(entity.getNroVersionExamenLaboratorio());
			
			bean.getOrdenDetalleBean().setCodigo(entity.getCodigoOrdenDetalle());
			bean.getOrdenDetalleBean().setNumeroPeriodo(entity.getNroPeriodoOrdenDetalle());
			bean.getOrdenDetalleBean().setNumeroVersion(entity.getNroVersionOrdenDetalle());
			bean.getOrdenDetalleBean().getExamen().setDescripcion(entity.getNOMPRODU());
	    //	bean.getOrdenDetalleBean().getExamen().getTarifarioDetalleBean().setObservacion(entity.getOBSERVACION());
	    	bean.getOrdenDetalleBean().getExamen().setObservacion(entity.getOBSERVACION());
			bean.setResultadoFormula(entity.getResultadoFormula());
			bean.setCodigoUsuarioCreacion(entity.getUsuarioCreacion());
			bean.setFechaCreacion(entity.getAufechcr());
			bean.getExamenesLaboratorioBean().setDescripcion(entity.getDescripcionExamenAsociado());
			bean.getExamenesLaboratorioBean().setAbrev(entity.getAbrevExamenAsociado());
			bean.getExamenesLaboratorioBean().setTipoExamenAsoc(entity.getTipo());
			bean.getExamenesLaboratorioBean().getExamenUnidadMedidaLaboratorioBean().setDescripcion(entity.getDescripcionUnidadMedida());
			bean.getExamenesLaboratorioBean().getExamenUnidadMedidaLaboratorioBean().setAbrev(entity.getAbrevUnidadMedida());
			
	 	}
		
		return bean;
	}

	
	
	@Override
	public boolean existe(OrdenDetalleItemBean t) throws DAOException {

		return false;
	}

	private static String getTwoDecimals(double value){
	      DecimalFormat df = new DecimalFormat("0.00"); 
	      return df.format(value);
	    }

	@Override
	public List<OrdenDetalleItemBean> listarAnalisisResultados(OrdenDetalleItemBean ordenDetalleItemBean)
			throws DAOException {
		
		List<Orden_laboratorio_detalle_item> lstOrden = null;	
		List<OrdenDetalleItemBean> lstOrdenDetalleItemBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("ordenDetalleItem.buscarPorCodigoDetalle");   
			spq.setParameter("p_CODORGAN", ordenDetalleItemBean.getCodigoOrganizacion()); 
			spq.setParameter("p_CODINSTI", ordenDetalleItemBean.getCodigoInstitucion()); 
			spq.setParameter("p_CODSEDEI", ordenDetalleItemBean.getCodigoSede()); 
			spq.setParameter("p_CODORDEN", ordenDetalleItemBean.getOrdenDetalleBean().getCodigo()); 
							  
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenDetalleItemBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenDetalleItemBean;
	}

	@Override
	public List<OrdenDetalleItemBean> listarAnalisisResultadosPorOrden(OrdenBean OrdenBean) throws DAOException {
		List<Orden_laboratorio_detalle_item> lstOrden = null;	
		List<OrdenDetalleItemBean> lstOrdenDetalleItemBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("ordenDetalleItem.buscarPorCodigoOrden");   
			spq.setParameter("p_CODORGAN", OrdenBean.getCodigoOrganizacion()); 
			spq.setParameter("p_CODINSTI", OrdenBean.getCodigoInstitucion()); 
			spq.setParameter("p_CODSEDEI", OrdenBean.getCodigoSede()); 
			spq.setParameter("p_CODORDEN", OrdenBean.getCodigo()); 
							  
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenDetalleItemBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenDetalleItemBean;
	}

}
