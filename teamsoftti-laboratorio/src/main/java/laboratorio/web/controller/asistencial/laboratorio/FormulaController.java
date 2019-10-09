package laboratorio.web.controller.asistencial.laboratorio;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import laboratorio.core.bean.asistencial.laboratorio.OrdenDetalleBean;
import laboratorio.core.bean.asistencial.laboratorio.OrdenDetalleItemBean;
import laboratorio.core.bean.general.ExamenUnidadMedidaLaboratorioBean;
import laboratorio.core.bean.general.ExamenesLaboratorioBean;
import laboratorio.core.bean.general.PersonaBean;
import laboratorio.core.bean.general.TablaBean;
import laboratorio.core.bean.general.TarifarioBean;
import laboratorio.core.bean.general.UbigeoBean;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.asistencial.laboratorio.OrdenDetalleItemService;
import laboratorio.core.service.interfaces.asistencial.maestra.MaestraAsis01Service;
import laboratorio.core.service.interfaces.general.ExamenUnidadMedidaLaboratorioService;
import laboratorio.core.service.interfaces.general.ExamenesLaboratorioService;
import laboratorio.core.service.interfaces.general.Maestra1Service;
import laboratorio.core.service.interfaces.general.TarifarioDetalleService;
import laboratorio.core.service.interfaces.general.TarifarioService;
import laboratorio.web.controller.base.BaseController;

@Controller
@RequestMapping(value = "formulaController")
public class FormulaController extends BaseController {

	List<TablaBean> lstSituacion = new ArrayList<TablaBean>();
	List<TablaBean> lstDocumento = new ArrayList<TablaBean>();
	List<TablaBean> lstSexo = new ArrayList<TablaBean>();
	List<TablaBean> lstEstadoCivil = new ArrayList<TablaBean>();
	List<TablaBean> lstOcupacion = new ArrayList<TablaBean>();
	List<TablaBean> lstNacionalidad = new ArrayList<TablaBean>();
	List<TablaBean> lstNivelInstrucion = new ArrayList<TablaBean>();
	List<UbigeoBean> lstUbigeoBean = new ArrayList<UbigeoBean>();
	List<TablaBean> lstTipoExamen = new ArrayList<TablaBean>();
	private List<OrdenDetalleItemBean> lstOrdenDetalleItemBean ; 
	private List<OrdenDetalleBean>      lstOrdenDetalleBean ; 

	List<TablaBean> lstAreasLab = new ArrayList<TablaBean>();

	PersonaBean personaBean = new PersonaBean();
	public UbigeoBean ubigeobean;
	TarifarioBean tarifarioBean = new TarifarioBean();
	OrdenDetalleItemBean ordenDetalleItemBean = new OrdenDetalleItemBean();
    OrdenDetalleBean ordenDetalleBean = new OrdenDetalleBean();

	private List<TarifarioBean> lstTarifarioBean;
	private List<ExamenUnidadMedidaLaboratorioBean> lstExamenUnidadMedidaLaboratorioBean;
	
	private List<ExamenesLaboratorioBean> lstExamenesLaboratorioBean;

	@Autowired
	private MaestraAsis01Service maestraAsis01Service;

	@Autowired
	private TarifarioService tarifarioService;

	@Autowired
	private ExamenesLaboratorioService examenesLaboratorioService;

	@Autowired
	private Maestra1Service maestraGene01Services;
	
	@Autowired
	private ExamenUnidadMedidaLaboratorioService examenUnidadMedidaLaboratorioService;
	
	@Autowired
	private OrdenDetalleItemService ordenDetalleItemService;

	@PostConstruct
	public void init() {

	}

	private void cargarCombos(ModelAndView mav) {
		try {
			
			lstAreasLab = maestraGene01Services.listarPorCodigoTabla("000063", 0);

		} catch (ServiceException e) {
			System.out.println("printStackTrace");
			e.printStackTrace();
		}
	
		mav.addObject("lstAreasLab", lstAreasLab);
	}

	@RequestMapping(value = "/nuevoTarifario", method = RequestMethod.GET)
	public ModelAndView nuevoRegistroTarifario(HttpServletRequest request) {
		tarifarioBean = new TarifarioBean();
		lstExamenesLaboratorioBean = new ArrayList<ExamenesLaboratorioBean>();
		// tarifarioBean.setLstExamenesLaboratorioBean(lstExamenesLaboratorioBean);
		ModelAndView mav = new ModelAndView("general/tarifario/registro-tarifario", "command", tarifarioBean);
		mav.addObject("tarifarioBean", tarifarioBean);
		this.cargarCombos(mav);
		return mav;
	}

	@RequestMapping(value = "/nuevaFormula", method = RequestMethod.GET)
	public ModelAndView nuevoRegistroFormula(HttpServletRequest request) {
		tarifarioBean = new TarifarioBean();
		// tarifarioBean.setLstExamenesLaboratorioBean(lstExamenesLaboratorioBean);
		ModelAndView mav = new ModelAndView("asistencial/laboratorio/formulas/registro-procedimiento-asociado",
				"command", tarifarioBean);
		TarifarioBean prmTarifarioBean = new TarifarioBean();
		prmTarifarioBean.setTipo(new TablaBean());
		prmTarifarioBean.getTipo().setCodReg("");
		prmTarifarioBean.setDescripcion("");
		lstTarifarioBean = new ArrayList<TarifarioBean>();
		lstExamenUnidadMedidaLaboratorioBean = new ArrayList<ExamenUnidadMedidaLaboratorioBean>();
		lstTipoExamen = new ArrayList<TablaBean>();
		try {
			lstTarifarioBean = tarifarioService.getBuscarPorFiltros(prmTarifarioBean);
			lstExamenUnidadMedidaLaboratorioBean = examenUnidadMedidaLaboratorioService.getBuscarPorFiltros(new ExamenUnidadMedidaLaboratorioBean());
			System.out.println("lstTarifarioBean.size() " + lstTarifarioBean.size());
			lstTipoExamen = maestraAsis01Service.listarPorCodigoTabla("000013", 1);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		mav.addObject("lstTarifarioBean", lstTarifarioBean);
		mav.addObject("lstTipoExamen", lstTipoExamen);
		mav.addObject("lstExamenUnidadMedidaLaboratorioBean", lstExamenUnidadMedidaLaboratorioBean);
		mav.addObject("tarifarioBean", tarifarioBean);
		this.cargarCombos(mav);
		return mav;
	}

	@RequestMapping(value = "/obtenerListaUnidades", method = RequestMethod.GET)
	public @ResponseBody List<ExamenUnidadMedidaLaboratorioBean> obtenerListaUnidades() throws Exception {
		lstExamenUnidadMedidaLaboratorioBean = new ArrayList<ExamenUnidadMedidaLaboratorioBean>();
		try {
			lstExamenUnidadMedidaLaboratorioBean = examenUnidadMedidaLaboratorioService.getBuscarPorFiltros(new ExamenUnidadMedidaLaboratorioBean());
			System.out.println("lstExamenUnidadMedidaLaboratorioBean " + lstExamenUnidadMedidaLaboratorioBean.size());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return lstExamenUnidadMedidaLaboratorioBean;
	}
	
	@RequestMapping(value = "/refrescarListaOrden", method = RequestMethod.GET)
	public @ResponseBody OrdenDetalleBean refrescarListaOrden(@RequestParam("index") int index) throws Exception {
		System.out.println("index " + index);
		OrdenDetalleBean objOrdenDetalleBean = new OrdenDetalleBean();
		objOrdenDetalleBean.setExamen(lstTarifarioBean.get(index));
		objOrdenDetalleBean.setPrecio(lstTarifarioBean.get(index).getPrecio());
		objOrdenDetalleBean.setCantidad(1);
		objOrdenDetalleBean.ejecutarImporte();
		DecimalFormat df = new DecimalFormat("0.00");
		objOrdenDetalleBean.setsImporte((df.format(objOrdenDetalleBean.getImporte()).replace(",", ".")));
		// lstOrdenDetalleBean.add(objOrdenDetalleBean);
		return objOrdenDetalleBean;
	}

	@RequestMapping(value = "/tarifarioRegModal", method = RequestMethod.POST)
	public ModelAndView nuevoTarifario() throws Exception {

		ModelAndView mav = new ModelAndView("general/tarifario/registro-tarifario", "command", new TarifarioBean());
		lstTarifarioBean = new ArrayList<TarifarioBean>();
		lstTipoExamen = new ArrayList<TablaBean>();
		try {
			lstTarifarioBean = tarifarioService.getBuscarPorFiltros(new TarifarioBean());
			lstTipoExamen = maestraAsis01Service.listarPorCodigoTabla("000013", 1);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		mav.addObject("lstTarifarioBean", lstTarifarioBean);
		mav.addObject("lstTipoExamen", lstTipoExamen);
		return mav;
	}
  
 

	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public @ResponseBody String doEliminar(@RequestParam("index") Integer index, HttpServletRequest request) {
		String resultado = "";
		TarifarioBean oTarifarioBean = new TarifarioBean();
		oTarifarioBean = this.lstTarifarioBean.get(index);

		System.out.println("oCampaniaBean.getCodigo " + oTarifarioBean.getCodigo());
		System.out.println("oCampaniaBean " + oTarifarioBean);
		try {
			this.setAuditoria(oTarifarioBean, request, false);
			if (tarifarioService.eliminar(oTarifarioBean)) {
				System.out.println("Se eliminó el registro ");
				resultado = "1";
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@RequestMapping(value = "/eliminarDetalle", method = RequestMethod.GET)
	public @ResponseBody String eliminarDetalle(@RequestParam("index") Integer index, HttpServletRequest request) {
		String resultado = "";
		ExamenesLaboratorioBean oTarifarioBean = new ExamenesLaboratorioBean();
		oTarifarioBean = this.lstExamenesLaboratorioBean.get(index);
		System.out.println("eliminarDetalle Se eliminó getCodigo " + oTarifarioBean.getCodigo());
		System.out.println(
				"eliminarDetalle Se eliminó getTarifarioBean " + oTarifarioBean.getTarifarioBean().getCodigo());
		try {
			this.setAuditoria(oTarifarioBean, request, false);
			if (examenesLaboratorioService.eliminar(oTarifarioBean)) {
				System.out.println("Se eliminó el registro ");
				resultado = "1";
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@RequestMapping(value = "/listarTarifario", method = RequestMethod.GET)
	public ModelAndView listarPostulantes() throws Exception {
		System.out.println("/listarTarifario");
		ModelAndView mav = new ModelAndView("general/tarifario/ajax/listado-tarifario");
		lstTarifarioBean = new ArrayList<TarifarioBean>();
		try {
			lstTarifarioBean = tarifarioService.getBuscarPorFiltros(new TarifarioBean());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		mav.addObject("lstTarifarioBean", lstTarifarioBean);
		return mav;
	}

	@RequestMapping(value = "/refrescarLista", method = RequestMethod.GET)
	public @ResponseBody List<TarifarioBean> refrescarLista() throws Exception {
		try {
			lstTarifarioBean = tarifarioService.getBuscarPorFiltros(new TarifarioBean());

		} catch (ServiceException e) {

			e.printStackTrace();
		}

		return lstTarifarioBean;
	}

	@RequestMapping(value = "/tarifarioModal", method = RequestMethod.POST)
	public ModelAndView tarifarioModal() throws Exception {

		ModelAndView mav = new ModelAndView("asistencial/laboratorio/listado-tarifario-modal", "command",
				new TarifarioBean());
		lstTarifarioBean = new ArrayList<TarifarioBean>();
		lstTipoExamen = new ArrayList<TablaBean>();
		try {
			lstTarifarioBean = tarifarioService.getBuscarPorFiltros(new TarifarioBean());
			lstTipoExamen = maestraAsis01Service.listarPorCodigoTabla("000013", 1);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		mav.addObject("lstTarifarioBean", lstTarifarioBean);
		mav.addObject("lstTipoExamen", lstTipoExamen);
		return mav;
	}

	@RequestMapping(value = "/listadoTarifario", method = RequestMethod.GET)
	public ModelAndView listadoTarifario() throws Exception {
		return listadoTarifarioPOST();
	}

	@RequestMapping(value = "/listadoTarifario", method = RequestMethod.POST)
	public ModelAndView listadoTarifarioPOST() throws Exception {

		ModelAndView mav = new ModelAndView("general/tarifario/listado-tarifario-general", "command",
				new TarifarioBean());
		lstTarifarioBean = new ArrayList<TarifarioBean>();
		lstTipoExamen = new ArrayList<TablaBean>();
		try {
			lstTarifarioBean = tarifarioService.getBuscarPorFiltros(new TarifarioBean());
			lstTipoExamen = maestraAsis01Service.listarPorCodigoTabla("000013", 1);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		mav.addObject("lstTarifarioBean", lstTarifarioBean);
		mav.addObject("lstTipoExamen", lstTipoExamen);
		cargarCombos(mav);
		return mav;
	}

	@RequestMapping(value = "/buscar", method = RequestMethod.POST)
	public ModelAndView buscarPOST(@ModelAttribute("tarifarioBean") TarifarioBean tarifarioBean,
			HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("general/tarifario/listado-tarifario-general", "command", tarifarioBean);
		lstTarifarioBean = new ArrayList<TarifarioBean>();
		try {
			lstTarifarioBean = tarifarioService.getBuscarPorFiltros(tarifarioBean);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		mav.addObject("lstTarifarioBean", lstTarifarioBean);
		mav.addObject("lstTipoExamen", lstTipoExamen);
		cargarCombos(mav);
		return mav;
	}

	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public ModelAndView buscarGET(@ModelAttribute("tarifarioBean") TarifarioBean tarifarioBean,
			HttpServletRequest request) throws Exception {
		return buscarPOST(tarifarioBean, request);
	}

	/**
	@RequestMapping(value = "/agregarDetalle", method = RequestMethod.GET)
	public ModelAndView agregarDetalle(@ModelAttribute("tarifarioBean") TarifarioBean tarifarioBean,
			HttpServletRequest request) throws Exception {
		
		
		for (ExamenesLaboratorioBean tablaBean : tarifarioBean.getLstExamenesLaboratorioBean()) {
			System.out.println("ExamenesLaboratorioBean " + tablaBean.getValoresRefFin());
		}

		System.out.println("getCodigo:::" + tarifarioBean.getCodigo());
		System.out.println(
				"tarifarioBean.getLstExamenesLaboratorioBean():::" + tarifarioBean.getLstExamenesLaboratorioBean().size());
		ExamenesLaboratorioBean objExamenesLaboratorioBean = new ExamenesLaboratorioBean();
		objExamenesLaboratorioBean.setCodigo("");
		objExamenesLaboratorioBean.setValoresRefFin("");
		objExamenesLaboratorioBean.setValoresRefIni("");
		objExamenesLaboratorioBean.setUnidades("");
		// lstExamenesLaboratorioBean.add(objExamenesLaboratorioBean);
		try {
			// tarifarioBean.getLstExamenesLaboratorioBean().add(objExamenesLaboratorioBean);
			tarifarioBean.getLstExamenesLaboratorioBean().add(objExamenesLaboratorioBean);
		} catch (Exception e) {
			// TODO: handle exception
		}
		for (ExamenesLaboratorioBean tablaBean : tarifarioBean.getLstExamenesLaboratorioBean()) {
			System.out.println("ExamenesLaboratorioBean " + tablaBean.getValoresRefFin());
		}
		ModelAndView mav = new ModelAndView("general/tarifario/ajax/listado-tarifario-detalle", "command",
				tarifarioBean);

		mav.addObject("lstExamenesLaboratorioBean", tarifarioBean.getLstExamenesLaboratorioBean());
		mav.addObject("tarifarioBean", tarifarioBean);
		return mav;
	}
*/
	@RequestMapping(value = "/grabarAsociados", method = RequestMethod.POST)
	public @ResponseBody String grabarAsociados( 
			@RequestBody ExamenesLaboratorioBean[] detetalleArray, HttpServletRequest request) throws Exception {
		String codigo = "1"; 
    
				for (ExamenesLaboratorioBean ExamenesLaboratorioBean : detetalleArray) {  
					System.out.println(
							"ExamenesLaboratorioBean.getTarifarioBean().getCodigo() " + ExamenesLaboratorioBean.getTarifarioBean().getCodigo());
					this.setAuditoria(ExamenesLaboratorioBean, request, true);
					examenesLaboratorioService.insertar(ExamenesLaboratorioBean);
				}
  
		return codigo;
	}
	
	@RequestMapping(value = "/formulaModal", method = RequestMethod.POST)
	public ModelAndView formulaModalPost(	HttpServletRequest request)
			throws Exception {
		lstTarifarioBean = new ArrayList<TarifarioBean>();
	

	
		System.out.println("modal formula");
		PersonaBean objPersona = new  PersonaBean();
		objPersona.getNacionalidad().setCodReg("000114");
		objPersona.getEstadoCivil().setCodReg("000006");

		
		ModelAndView mav = new ModelAndView("asistencial/laboratorio/formulas/formula-registro-modal", "command",  getUsuarioSesion(request));

		
		ubigeobean = new UbigeoBean();
		ubigeobean.setVariable("");
		ubigeobean.setInstitucion("000003");
		ubigeobean.setCategoria("000003");
		lstUbigeoBean = new ArrayList<UbigeoBean>();
		mav.addObject("lstTarifarioBean", lstTarifarioBean);
		try {
			
	//		lstUbigeoBean = ubigeoService.getBuscarPorFiltros(ubigeobean);
		} catch (Exception e) { 
		}
		mav.addObject("ubigeoBean", new UbigeoBean());
		mav.addObject("lstUbigeoBean", lstUbigeoBean);
		this.cargarCombos(mav);
		return mav;
	} 
	

	@RequestMapping(value = "/listaDetalleFormula", method = RequestMethod.GET)
	public @ResponseBody List<ExamenesLaboratorioBean> listaDetalleFormula(@RequestParam("codigo") String codigo) throws Exception {
		System.out.println("listaDetalleFormula codigo " + codigo);
		ExamenesLaboratorioBean examenesLaboratorioBean = new ExamenesLaboratorioBean();
		examenesLaboratorioBean.getTarifarioBean().setCodigo(codigo);
		try {
			lstExamenesLaboratorioBean = examenesLaboratorioService.getBuscarPorFiltros(examenesLaboratorioBean);

		} catch (ServiceException e) {

			e.printStackTrace();
		}

		return lstExamenesLaboratorioBean;
	}
	
	
	@RequestMapping(value = "/registroResultadoFormulaModal", method = RequestMethod.POST)
	public ModelAndView doListaOrdenesResultadoFormula(@RequestParam("index") Integer index, HttpServletRequest request) {
		 
		System.out.println("modificar codigo: " + index); 
		OrdenDetalleBean objOrdenBean = new OrdenDetalleBean(); 
		objOrdenBean = this.lstOrdenDetalleBean.get(index);
		System.out.println("modificar objOrdenBean: " + objOrdenBean.getCodigo());
	    setOrdenDetalleBean(objOrdenBean);
		ModelAndView mav = new ModelAndView("asistencial/laboratorio/formulas/formula-registro-resultados", "command", objOrdenBean); 
		OrdenDetalleItemBean objOrdenDetalle = new OrdenDetalleItemBean();
		objOrdenDetalle.setOrdenDetalleBean(objOrdenBean);
		
		/**insertamos tabla itemdetalle(solo si existe los registros) para luego consultar los registros****/
		insertOrdenDetalleItem(objOrdenDetalle, request);
		try {
			lstOrdenDetalleItemBean = ordenDetalleItemService.listarAnalisisResultados(objOrdenDetalle);
		
	//		setLstOrdenDetalleBean(lstOrdenDetalleBean);
	//		setLstOrdenDetalleBeanReporte(lstOrdenDetalleBeanReporte);
			for (OrdenDetalleBean ord : lstOrdenDetalleBean) {
			//	System.out.println("resultados: " + ord.getResultado());	

				System.out.println("codigo detalle: " + ord.getCodigo()); 
			}
		setOrdenDetalleBean(objOrdenBean);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		mav.addObject("lstOrdenDetalleItemBean", lstOrdenDetalleItemBean); 
		mav.addObject("ordenBean", objOrdenBean); 
		this.cargarCombos(mav);
		return mav;
	}
	
	
	
	/*
	  @RequestMapping(value = "/registroOrdenDetalleItem", method = RequestMethod.GET)
			public @ResponseBody String registroOrdenDetalleItem(
				@ModelAttribute("ordendetalleItemBean")OrdenDetalleItemBean ordenBean,HttpServletRequest request) throws Exception {
			   String resultados="";
	for (OrdenDetalleBean objOrdenDetalleBean :ordenBean.getLstOrdenDetalleBean()) {
		this.setAuditoria(objOrdenDetalleBean, request, true);
		ordenDetalleItemService.insertar(objOrdenDetalleBean);
		
	}
				//lstOrdenDetalleBean.add(objOrdenDetalleBean);
				return resultados;
			}*/
	    
	/***para insertar la tabla de detalleItem donde estarán los analisis para formular***/
	private Boolean insertOrdenDetalleItem(OrdenDetalleItemBean ordenDetalleItemBean,HttpServletRequest request) {
		
		OrdenDetalleItemBean uOrdenDetalleItemBean = new OrdenDetalleItemBean();
		uOrdenDetalleItemBean.setCodigoOrganizacion(ordenDetalleItemBean.getCodigoOrganizacion());
		uOrdenDetalleItemBean.setCodigoInstitucion(ordenDetalleItemBean.getCodigoInstitucion());
		uOrdenDetalleItemBean.setCodigoSede(ordenDetalleItemBean.getCodigoSede());
		OrdenDetalleBean ordenDetalleBean = new OrdenDetalleBean();
		ordenDetalleBean.setCodigo(ordenDetalleItemBean.getOrdenDetalleBean().getCodigo());
		ordenDetalleBean.setNumeroPeriodo(ordenDetalleItemBean.getOrdenDetalleBean().getNumeroPeriodo());
		ordenDetalleBean.setNumeroVersion(ordenDetalleItemBean.getOrdenDetalleBean().getNumeroVersion());
		uOrdenDetalleItemBean.setOrdenDetalleBean(ordenDetalleBean);
		this.setAuditoria(uOrdenDetalleItemBean, request, true);
		try {
			ordenDetalleItemService.insertar(uOrdenDetalleItemBean);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	
	
	
	
	public PersonaBean getPersonaBean() {
		return personaBean;
	}

	public void setPersonaBean(PersonaBean personaBean) {
		this.personaBean = personaBean;
	}

	public TarifarioBean getTarifarioBean() {
		return tarifarioBean;
	}

	public void setTarifarioBean(TarifarioBean tarifarioBean) {
		this.tarifarioBean = tarifarioBean;
	}

	public List<ExamenesLaboratorioBean> getLstExamenesLaboratorioBean() {
		return lstExamenesLaboratorioBean;
	}

	public void setLstExamenesLaboratorioBean(List<ExamenesLaboratorioBean> lstExamenesLaboratorioBean) {
		this.lstExamenesLaboratorioBean = lstExamenesLaboratorioBean;
	}
	public OrdenDetalleItemBean getOrdenDetalleItemBean() {
		return ordenDetalleItemBean;
	}

	public void setOrdenDetalleItemBean(OrdenDetalleItemBean ordenDetalleItemBean) {
		this.ordenDetalleItemBean = ordenDetalleItemBean;
	}

	public OrdenDetalleBean getOrdenDetalleBean() {
		return ordenDetalleBean;
	}

	public void setOrdenDetalleBean(OrdenDetalleBean ordenDetalleBean) {
		this.ordenDetalleBean = ordenDetalleBean;
	}
	

}
