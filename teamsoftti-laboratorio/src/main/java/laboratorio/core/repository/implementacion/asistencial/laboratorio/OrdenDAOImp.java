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

import laboratorio.core.bean.asistencial.laboratorio.AnalisisFormulaLaboratorioBean;
import laboratorio.core.bean.asistencial.laboratorio.OrdenBean;
import laboratorio.core.bean.general.PersonaBean;
import laboratorio.core.entity.asistencial.laboratorio.Analisis_formula_laboratorio;
import laboratorio.core.entity.asistencial.laboratorio.Orden_laboratorio;
import laboratorio.core.repository.DAOException;
import laboratorio.core.repository.interfaces.asistencial.laboratorio.OrdenDAO; 

@Transactional
@Repository("OrdenDAOImp")
public class OrdenDAOImp implements OrdenDAO {

	@PersistenceContext//(unitName="emf")
	private EntityManager em; 
	
	
	public OrdenBean getOrdenBean(Object object){
		return (OrdenBean)object;
	}
	
	@Override
	public boolean insertar(OrdenBean OrdenBean) throws DAOException {

		Object id= null;   
		Object nroPeriodo= null;   
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.insertar");  
			System.out.println(" OrdenBean.getPorcentajeDescuento()" +  OrdenBean.getPorcentajeDescuento());
			spq.setParameter("p_CODORGAN", OrdenBean.getCodigoOrganizacion());
			spq.setParameter("p_CODINSTI", OrdenBean.getCodigoInstitucion());
			spq.setParameter("p_CODSEDEI", OrdenBean.getCodigoSede()); 
			spq.setParameter("p_CODPERSO", OrdenBean.getPacienteBean().getPersona().getCodigo());
			spq.setParameter("p_CANT_ITEMS", OrdenBean.getCantidadItems());
			spq.setParameter("p_CADENA_CODTARIF", OrdenBean.getCadenaCodigoTarifario());
			spq.setParameter("p_CADENA_CANTIDAD", OrdenBean.getCadenaCantidad());
			
			spq.setParameter("p_AUCDUSCR", OrdenBean.getCodigoUsuarioCreacion());
			spq.setParameter("p_AUPCIPCR", OrdenBean.getIpCreacion());
			spq.setParameter("p_PORCDESC", OrdenBean.getPorcentajeDescuento());
			
			
			spq.execute();
			
			id = spq.getOutputParameterValue(4); 
			nroPeriodo = spq.getOutputParameterValue(9); 
			if (id != null) {
				OrdenBean.setCodigo(id.toString());
				OrdenBean.setNumeroPeriodo(nroPeriodo.toString()); 
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
	public boolean actualizar(OrdenBean OrdenBean) throws DAOException {

		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("Orden.modificar"); 
			spq.setParameter("CODORGAN", OrdenBean.getCodigoOrganizacion());
			spq.setParameter("CODINSTI", OrdenBean.getCodigoInstitucion());
			spq.setParameter("CODSEDEI", OrdenBean.getCodigoSede()); 
			
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
	public boolean eliminar(OrdenBean OrdenBean) throws DAOException {

		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.eliminar"); 
			spq.setParameter("p_CODORGAN", OrdenBean.getCodigoOrganizacion());
			spq.setParameter("p_CODINSTI", OrdenBean.getCodigoInstitucion());
			spq.setParameter("p_CODSEDEI", OrdenBean.getCodigoSede()); 
			spq.setParameter("p_NROPERIO", OrdenBean.getNumeroPeriodo()); 
			spq.setParameter("p_CODORDEN", OrdenBean.getCodigo());
		
			spq.setParameter("p_TG1TPANU", OrdenBean.getMotivoAnular().getCodReg()); 
			spq.setParameter("p_MOTIVOAN", OrdenBean.getDetalleMotivoAnular()); 
			spq.setParameter("p_AUCDUSMO", OrdenBean.getCodigoUsuarioModificacion());
			spq.setParameter("p_AUPCIPMO", OrdenBean.getIpModificacion()); 
			
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
	public OrdenBean getBuscarPorObjecto(OrdenBean t) throws DAOException {

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenBean> getBuscarPorFiltros(OrdenBean OrdenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.buscarPorFiltros");   
			spq.setParameter("p_FECDESDE", OrdenBean.getFechaDesde()); 
			spq.setParameter("p_FECHASTA", OrdenBean.getFechaHasta()); 
			spq.setParameter("p_SITUACRG", OrdenBean.getSituacion().getCodReg()); 
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}
	
	 

private List<OrdenBean> deListaObjetoAListaObjetoBean(List<Orden_laboratorio> lstOrden) {
		
		List<OrdenBean> lstOrdenBean = null;
		
		if (lstOrden != null && lstOrden.size() > 0) {
			
			lstOrdenBean = new ArrayList<OrdenBean>();
			
			for (int i = 0; i < lstOrden.size(); i++) { 
				Orden_laboratorio entity = lstOrden.get(i);
				OrdenBean bean = deObjetoAObjetoBean(entity);
				
				lstOrdenBean.add(bean);
			}
		}
		
		return lstOrdenBean;
	}
	
	private OrdenBean deObjetoAObjetoBean(Orden_laboratorio entity) {
		
		OrdenBean bean = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (entity != null) {
			
			bean = new OrdenBean();
			bean.setCodigo(entity.getId().getCodOrden());  
			bean.setCodigoInstitucion(entity.getId().getCodinsti());  
			bean.setCodigoSede(entity.getId().getCodsedei());  
			bean.setCodigoOrganizacion(entity.getId().getCodorgan());
			bean.setNumeroVersion(entity.getId().getNroversi());
			bean.setNumeroPeriodo(entity.getId().getNroperio()); 
			bean.getSituacion().setCodReg(entity.getSituacRg()); 
			bean.getSituacion().setNombreCorto(entity.getNOMBSITU()); 
			bean.setNroOrden(entity.getNroOrden());  
			bean.setImporteTotal(entity.getImpTotal());
			System.out.println("bean.setimportetotal"+bean.getImporteTotal());
			if(entity.getImpTotal()!=null) {
				bean.setsImporteTotal((getTwoDecimals(entity.getImpTotal()).replace(",", ".")));
			}
			
			
			bean.setStrFechaOrden(entity.getStrFechaOrd()); 
			bean.setHoraOrden(entity.getHora()); 
			bean.getPacienteBean().setPersona(new PersonaBean());
			bean.getPacienteBean().getPersona().setCodigo(entity.getCodPerso()); 
			bean.getPacienteBean().getPersona().setApellidoPaterno(entity.getAPEPATER()); 
			bean.getPacienteBean().getPersona().setApellidoMaterno(entity.getAPEMATER()); 
			bean.getPacienteBean().getPersona().setPrimerNombre(entity.getPRINOMBR()); 
			bean.getPacienteBean().getPersona().setSegundoNombre(entity.getSEGNOMBR()); 
			bean.getPacienteBean().getPersona().setNroDocumento(entity.getDETALLED()); 
			bean.getPacienteBean().getPersona().setEdad(entity.getEDAD());
			bean.getPacienteBean().getPersona().setCorreo(entity.getCORREOPE());
			bean.getPacienteBean().getPersona().setDireccion(entity.getDIRECCIO());
			bean.getPacienteBean().getPersona().getSexo().setCodReg(entity.getTG1SEXOP());
			bean.setNombreUsuarioCreacion(entity.getNOMUSUAR());
			bean.setCantidadOrdenes(entity.getCANTIDAD_ORDEN());
			bean.setCantidadVentas(entity.getTOTAL_VENTA());
			bean.setNombreExamen(entity.getEXAMEN());
			bean.setCantidadExamenesSolicitados(entity.getCANTIDAD());
			bean.setNombreMes(entity.getMES());
	 	}
		
		return bean;
	}

	
	
	@Override
	public boolean existe(OrdenBean t) throws DAOException {

		return false;
	}

	private static String getTwoDecimals(double value){
	      DecimalFormat df = new DecimalFormat("0.00"); 
	      return df.format(value);
	    }

	@Override
	public OrdenBean reporteCantidadAnualOrdenSituacion(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrdenlaboratorio = null;	
		OrdenBean oOrdenBean = null;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadAnualOrdenSituacion");
			spq.setParameter("p_ANIO", ordenBean.getNumeroPeriodo());
			spq.setParameter("p_SITUACRG", ordenBean.getSituacion().getCodReg()); 
				
			if (spq.execute()) {
				lstOrdenlaboratorio =  spq.getResultList(); 
			} 
			if (lstOrdenlaboratorio != null && lstOrdenlaboratorio.size() > 0) {
				
				oOrdenBean = deObjetoAObjetoBean(lstOrdenlaboratorio.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			em.close();
		} 
		return oOrdenBean;
	}

	@Override
	public OrdenBean reporteCantidadMensualOrdenSituacion(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrdenlaboratorio = null;	
		OrdenBean oOrdenBean = null;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadMensualOrdenSituacion");
			spq.setParameter("p_MES", 	 ordenBean.getNroMes());
			spq.setParameter("p_ANIO", 	 ordenBean.getNumeroPeriodo());
			spq.setParameter("p_SITUACRG", ordenBean.getSituacion().getCodReg()); 
				
			if (spq.execute()) {
				lstOrdenlaboratorio =  spq.getResultList(); 
			} 
			if (lstOrdenlaboratorio != null && lstOrdenlaboratorio.size() > 0) {
				
				oOrdenBean = deObjetoAObjetoBean(lstOrdenlaboratorio.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			em.close();
		} 
		return oOrdenBean;
	}

	@Override
	public OrdenBean reporteCantidadDiarioOrdenSituacion(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrdenlaboratorio = null;	
		OrdenBean oOrdenBean = null;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadDiarioOrdenSituacion");
			spq.setParameter("p_fecha",    ordenBean.getFecha());
			spq.setParameter("p_situacrg", ordenBean.getSituacion().getCodReg()); 
				
			if (spq.execute()) {
				lstOrdenlaboratorio =  spq.getResultList(); 
			} 
			if (lstOrdenlaboratorio != null && lstOrdenlaboratorio.size() > 0) {
				
				oOrdenBean = deObjetoAObjetoBean(lstOrdenlaboratorio.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			em.close();
		} 
		return oOrdenBean;
	}

	@Override
	public OrdenBean reporteCantidadSemanalOrdenSituacion(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrdenlaboratorio = null;	
		OrdenBean oOrdenBean = null;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadSemanalOrdenSituacion");
			spq.setParameter("p_FECHA", 	 ordenBean.getFecha());
			spq.setParameter("p_SITUACRG", ordenBean.getSituacion().getCodReg()); 
				
			if (spq.execute()) {
				lstOrdenlaboratorio =  spq.getResultList(); 
			} 
			if (lstOrdenlaboratorio != null && lstOrdenlaboratorio.size() > 0) {
				
				oOrdenBean = deObjetoAObjetoBean(lstOrdenlaboratorio.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			em.close();
		} 
		return oOrdenBean;
	}

	@Override
	public OrdenBean reporteVentaAnual(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrdenlaboratorio = null;	
		OrdenBean oOrdenBean = null;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteVentaAnual");
			spq.setParameter("p_ANIO", 	 ordenBean.getNumeroPeriodo());				
			if (spq.execute()) {
				lstOrdenlaboratorio =  spq.getResultList(); 
			} 
			if (lstOrdenlaboratorio != null && lstOrdenlaboratorio.size() > 0) {
				
				oOrdenBean = deObjetoAObjetoBean(lstOrdenlaboratorio.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			em.close();
		} 
		return oOrdenBean;
	}

	@Override
	public OrdenBean reporteVentaMensual(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrdenlaboratorio = null;	
		OrdenBean oOrdenBean = null;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteVentaMensual");
			spq.setParameter("p_MES", 	 ordenBean.getNroMes());	
			spq.setParameter("p_ANIO", 	 ordenBean.getNumeroPeriodo());				
			if (spq.execute()) {
				lstOrdenlaboratorio =  spq.getResultList(); 
			} 
			if (lstOrdenlaboratorio != null && lstOrdenlaboratorio.size() > 0) {
				
				oOrdenBean = deObjetoAObjetoBean(lstOrdenlaboratorio.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			em.close();
		} 
		return oOrdenBean;
	}

	@Override
	public OrdenBean reporteVentaDiaria(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrdenlaboratorio = null;	
		OrdenBean oOrdenBean = null;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteVentaDiaria");
			spq.setParameter("P_FECHA", 	 ordenBean.getFecha());			
			if (spq.execute()) {
				lstOrdenlaboratorio =  spq.getResultList(); 
			} 
			if (lstOrdenlaboratorio != null && lstOrdenlaboratorio.size() > 0) {
				
				oOrdenBean = deObjetoAObjetoBean(lstOrdenlaboratorio.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			em.close();
		} 
		return oOrdenBean;
	}

	@Override
	public OrdenBean reporteVentaSemanal(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrdenlaboratorio = null;	
		OrdenBean oOrdenBean = null;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteVentaSemanal");
			spq.setParameter("p_FECHA", 	 ordenBean.getFecha());			
			if (spq.execute()) {
				lstOrdenlaboratorio =  spq.getResultList(); 
			} 
			if (lstOrdenlaboratorio != null && lstOrdenlaboratorio.size() > 0) {
				
				oOrdenBean = deObjetoAObjetoBean(lstOrdenlaboratorio.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}finally{
			em.close();
		} 
		return oOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleOrdenVentaAnual(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteDetalleOrdenVentaAnual");   
			spq.setParameter("p_ANIO", 	 ordenBean.getNumeroPeriodo());	
			
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}
	

	@Override
	public List<OrdenBean> reporteDetalleOrdenVentaMensual(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteDetalleOrdenVentaMensual"); 
			spq.setParameter("p_MES", 	 ordenBean.getNroMes());	
			spq.setParameter("p_ANIO", 	 ordenBean.getNumeroPeriodo());	
			
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleOrdenVentaDiaria(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteDetalleOrdenVentaDiaria"); 
			spq.setParameter("p_FECHA", 	 ordenBean.getFecha());	
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleOrdenVentaSemanal(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteDetalleOrdenVentaSemanal"); 
			spq.setParameter("p_FECHA", 	 ordenBean.getFecha());	
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteVentaMensualPeriodoArea(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteVentaMensualPeriodoArea"); 
			spq.setParameter("p_MES", 	 ordenBean.getNroMes());	
			spq.setParameter("p_ANIO", 	 ordenBean.getNumeroPeriodo());		
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteVentaMensualPeriodoBarra(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteVentaMensualPeriodoBarra"); 
			spq.setParameter("p_ANIO", 	 ordenBean.getNumeroPeriodo());		
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleAnualOrdenSituacion(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteDetalleAnualOrdenSituacion"); 
			spq.setParameter("p_ANIO", 	 ordenBean.getNumeroPeriodo());		
			spq.setParameter("p_SITUACRG", ordenBean.getSituacion().getCodReg()); 
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleMensualOrdenSituacion(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteDetalleMensualOrdenSituacion");
			spq.setParameter("p_MES", 	 ordenBean.getNroMes());	
			spq.setParameter("p_ANIO", 	 ordenBean.getNumeroPeriodo());		
			spq.setParameter("p_SITUACRG", ordenBean.getSituacion().getCodReg()); 
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleDiariaOrdenSituacion(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteDetalleDiariaOrdenSituacion");
			spq.setParameter("p_FECHA", 	 ordenBean.getFecha());		
			spq.setParameter("p_SITUACRG", ordenBean.getSituacion().getCodReg()); 
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteDetalleSemanalOrdenSituacion(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteDetalleSemanalOrdenSituacion");
			spq.setParameter("p_FECHA", 	 ordenBean.getFecha());		
			spq.setParameter("p_SITUACRG", ordenBean.getSituacion().getCodReg()); 
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadAnualExamenesSolicitados(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadAnualExamenesSolicitados");
			spq.setParameter("p_ANIO", 	 ordenBean.getNumeroPeriodo());	
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadAnualExamenesSolicitadosTotal(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadAnualExamenesSolicitadosTotal");
			spq.setParameter("p_ANIO", 	 ordenBean.getNumeroPeriodo());	
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadMensualExamenesSolicitados(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadMensualExamenesSolicitados");
			spq.setParameter("p_MES", 	 ordenBean.getNroMes());	
			spq.setParameter("p_ANIO", 	 ordenBean.getNumeroPeriodo());	
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadMensualExamenesSolicitadosTotal(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadMensualExamenesSolicitadosTotal");
			spq.setParameter("p_MES", 	 ordenBean.getNroMes());	
			spq.setParameter("p_ANIO", 	 ordenBean.getNumeroPeriodo());	
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadDiarioExamenesSolicitados(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadDiarioExamenesSolicitados");
			spq.setParameter("p_FECHA", 	 ordenBean.getFecha());	
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadDiarioExamenesSolicitadosTotal(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadDiarioExamenesSolicitadosTotal");
			spq.setParameter("p_FECHA", 	 ordenBean.getFecha());	
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadSemanalExamenesSolicitados(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadSemanalExamenesSolicitados");
			spq.setParameter("p_FECHA", 	 ordenBean.getFecha());	
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

	@Override
	public List<OrdenBean> reporteCantidadSemanalExamenesSolicitadosTotal(OrdenBean ordenBean) throws DAOException {
		List<Orden_laboratorio> lstOrden = null;	
		List<OrdenBean> lstOrdenBean = null;
		
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("orden.reporteCantidadSemanalExamenesSolicitadosTotal");
			spq.setParameter("p_FECHA", 	 ordenBean.getFecha());	
			 if (spq.execute()) {
				 lstOrden =  spq.getResultList(); 
			 }
			 
			if (lstOrden != null && lstOrden.size() > 0) {
				lstOrdenBean = deListaObjetoAListaObjetoBean(lstOrden);
			 }
			
			em.close();
			
		   
		return lstOrdenBean;
	}

}
