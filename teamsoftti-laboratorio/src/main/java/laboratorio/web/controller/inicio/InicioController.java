package laboratorio.web.controller.inicio;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import laboratorio.core.bean.asistencial.laboratorio.OrdenBean;
import laboratorio.core.bean.asistencial.laboratorio.OrdenDetalleBean;
import laboratorio.core.bean.general.TablaBean;
import laboratorio.core.bean.seguridad.AccesoBean;
import laboratorio.core.bean.seguridad.AuditoriaAccesoBean;
import laboratorio.core.bean.seguridad.PerfilBean;
import laboratorio.core.bean.seguridad.UsuarioBean;
import laboratorio.core.bean.seguridad.UsuarioPerfilBean;
import laboratorio.core.service.exception.ServiceException;
import laboratorio.core.service.interfaces.asistencial.laboratorio.OrdenService;
import laboratorio.core.service.interfaces.asistencial.maestra.MaestraAsis01Service;
import laboratorio.core.service.interfaces.seguridad.AccesoService;
import laboratorio.core.service.interfaces.seguridad.UsuarioPerfilService;
import laboratorio.core.service.interfaces.seguridad.UsuarioService;
import laboratorio.web.controller.base.BaseController;
import laboratorio.web.controller.inicio.vista.InicioForm;
import laboratorio.web.utilitarios.NetUtil;
import laboratorio.web.utilitarios.VO;
import laboratorio.web.utilitarios.acceso.AccesoMenuVo;
import laboratorio.web.utilitarios.acceso.LoginVo;
import laboratorio.web.utilitarios.acceso.PermisoVo;
import laboratorio.web.utilitarios.encrypt.Encrypt;
 
 
@Controller
@RequestMapping(value = "inicioController")
@SessionAttributes("usuarioSesion")
@Scope(value="session")
public class InicioController extends BaseController{
	
	@Autowired
	AccesoService accesoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private OrdenService ordenService;
	 
	@Autowired
	private UsuarioPerfilService usuarioPerfilService;
	
	@Autowired
	private MaestraAsis01Service maestraAsis14Service;
	 
	
	List<TablaBean> lstMaestra =(new ArrayList<TablaBean>()); ;
	List<TablaBean> lstSituacion = new ArrayList<TablaBean>();
	List<TablaBean> lstTipoPaciente = new ArrayList<TablaBean>(); 
	
	private OrdenBean uOrderBean;
	private OrdenBean uOrdenBeanVentadiaria;
	private OrdenBean uOrdenBeanVentaSemanal;
	private OrdenBean uOrdenBeanVentaMensual;
	private OrdenBean uOrdenBeanVentaAnual;
	private String diaSemana;
	private String nombreVenta;
	private int tipoReporte=1;
	List<OrdenBean> lstOrdenBean = new ArrayList<OrdenBean>(); 
	private List<OrdenBean> lstOrdenBeann ;  
	private List<OrdenBean> lstOrdenBeanArea  ;  
	private List<OrdenBean> lstOrdenBeanBarra ;  
	private List<OrdenBean> lstOrdenBeanCircular ;
	@PostConstruct
	public void init(){
		
		this.setLstMaestra(new ArrayList<TablaBean>()); 
	
	}
	
	
	@RequestMapping(value = "/controlAcceso", method = RequestMethod.GET)
	public ModelAndView loginGet(@ModelAttribute("login") LoginVo prmLogin,
		BindingResult result, SessionStatus status,
		HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		
		session.removeAttribute("usuarioSesion");
		session.invalidate();
		
		this.setUsuarioBean(new UsuarioBean());
		
		//Inicializando el Encrypt
		Encrypt.init("KEY_ENCRYPT_PASS");
		
		if (prmLogin != null) {
			UsuarioBean prmUsuario = new UsuarioBean(); 
			prmUsuario.setPasswordUsuario(Encrypt.encrypt(prmLogin.getContrasena())); 
			prmUsuario.setNombreUsuario(prmLogin.getNombreUsuario()); 
			UsuarioBean oUsuario = usuarioService.autenticar(prmUsuario);  
			if (oUsuario != null) { 
				System.out.println("NombreUsuario persona "+oUsuario.getPersona().getNombreCompleto());
				System.out.println("Codigo "+oUsuario.getCodigo()); 
					if (!oUsuario.getEstado().trim().equals("1")) {//Activo
						
						
			//		this.registrarAudAcceso(prmLogin.getNombreUsuario(), 5, request);
					ModelAndView mav = new ModelAndView("seguridad/login/login","command", prmLogin);
					mav.addObject("msgErrorLogin","Su usuario no se encuentra activo, "
							+ "por favor coordine con el administrador del sistema");
					return mav;
				}
				
				if(oUsuario.getFlgRestPass()!=null && "0".equals(oUsuario.getFlgRestPass())){
					System.out.println("oUsuario.getFlgRestPass()"+oUsuario.getFlgRestPass());
					request.getSession().setAttribute("usuarioSesion", oUsuario);
				//	oUsuario.setPasswordUsuario("");
					oUsuario.setNewPassword("");
					oUsuario.setOldPassword(oUsuario.getPasswordUsuario());
					
					ModelAndView mav = new ModelAndView("seguridad/usuario/cambiar-contrasenia-usuario", "command",oUsuario);
					mav.addObject("usuarioBean", oUsuario);
					return mav;
				}
				
				/** BUSCAR LOS PERFILES DEL USUARIO **/
				List<UsuarioPerfilBean> lstUsuarioPerfilBean = new ArrayList<UsuarioPerfilBean>();
				UsuarioPerfilBean us = new UsuarioPerfilBean();
				us.setCodigoUsuario(oUsuario.getCodigo());
				lstUsuarioPerfilBean.add(us); 
				 
				oUsuario.setCodPerfilUsuSelec(oUsuario.getPerfil().getCodigo());
				oUsuario.setLstUsuarioPerfil(lstUsuarioPerfilBean); 
				return this.getLista(oUsuario, request);
				
				
				
			} else {
				UsuarioBean tmpUsuario = new UsuarioBean();
				tmpUsuario.setNombreUsuario("NV");
				this.setUsuarioBean(tmpUsuario);
				this.registrarAudAcceso(prmLogin.getNombreUsuario(), "1", request);
				
				ModelAndView mav = new ModelAndView("seguridad/login/login", "command",prmLogin);
				mav.addObject("msgErrorLogin", "El usuario y/o contraseña no coinciden");
				mav.addObject("usuarioSesion", tmpUsuario);
				return mav;
			}
		}else{
			return  new ModelAndView("seguridad/login/login", "command",prmLogin);
		}
	}
	
	@RequestMapping(value = "/controlAcceso", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("login") LoginVo prmLogin,
		BindingResult result, SessionStatus status,
		HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		
		session.removeAttribute("usuarioSesion");
		session.invalidate();
		
		this.setUsuarioBean(new UsuarioBean());
		
		//Inicializando el Encrypt
		Encrypt.init("KEY_ENCRYPT_PASS");
		
		if (prmLogin != null) {
			UsuarioBean prmUsuario = new UsuarioBean();
			//Encriptando la clave ingresada
			System.out.println("prmLogin.getContrasena11()"+prmLogin.getContrasena());
			prmUsuario.setPasswordUsuario(Encrypt.encrypt(prmLogin.getContrasena()));
			//prmUsuario.setPasswordUsuario(prmLogin.getContrasena());
			prmUsuario.setNombreUsuario(prmLogin.getNombreUsuario());
			
			UsuarioBean oUsuario = usuarioService.autenticar(prmUsuario);
			System.out.println("11111");
			if (oUsuario != null) {
				System.out.println("NombreUsuario "+oUsuario.getNombreUsuario());
				System.out.println("NombreUsuario persona "+oUsuario.getPersona().getNombreCompleto());
				System.out.println("Codigo "+oUsuario.getCodigo()); 
					if (!oUsuario.getEstado().trim().equals("1")) {//Activo
						
						
			//		this.registrarAudAcceso(prmLogin.getNombreUsuario(), 5, request);
					ModelAndView mav = new ModelAndView("seguridad/login/login","command", prmLogin);
					mav.addObject("msgErrorLogin","Su usuario no se encuentra activo, "
							+ "por favor coordine con el administrador del sistema");
					return mav;
				}
				
				if(oUsuario.getFlgRestPass()!=null && "0".equals(oUsuario.getFlgRestPass())){
					System.out.println("oUsuario.getFlgRestPass()"+oUsuario.getFlgRestPass());
					request.getSession().setAttribute("usuarioSesion", oUsuario);
				//	oUsuario.setPasswordUsuario("");
					oUsuario.setNewPassword("");
					oUsuario.setOldPassword(oUsuario.getPasswordUsuario());
					
					ModelAndView mav = new ModelAndView("seguridad/usuario/cambiar-contrasenia-usuario", "command",oUsuario);
					mav.addObject("usuarioBean", oUsuario);
					return mav;
				}
				
				/** BUSCAR LOS PERFILES DEL USUARIO **/
				List<UsuarioPerfilBean> lstUsuarioPerfilBean = new ArrayList<UsuarioPerfilBean>();
				UsuarioPerfilBean us = new UsuarioPerfilBean();
				us.setCodigoUsuario(oUsuario.getCodigo());
				lstUsuarioPerfilBean.add(us); 
				 
				oUsuario.setCodPerfilUsuSelec(oUsuario.getPerfil().getCodigo());
				oUsuario.setLstUsuarioPerfil(lstUsuarioPerfilBean); 
				return this.getLista(oUsuario, request);
				
				
				
			} else {
				UsuarioBean tmpUsuario = new UsuarioBean();
				tmpUsuario.setNombreUsuario("NV");
				this.setUsuarioBean(tmpUsuario);
				this.registrarAudAcceso(prmLogin.getNombreUsuario(), "1", request);
				
				ModelAndView mav = new ModelAndView("seguridad/login/login", "command",prmLogin);
				mav.addObject("msgErrorLogin", "El usuario y/o contraseña no coinciden");
				mav.addObject("usuarioSesion", tmpUsuario);
				return mav;
			}
		}else{
			return  new ModelAndView("seguridad/login/login", "command",prmLogin);
		}
	}

	@RequestMapping(value = "/inicio", method = RequestMethod.POST)
	public ModelAndView incio(@ModelAttribute("usuarioBean") UsuarioBean usuarioBean,
		BindingResult result, SessionStatus status,
		HttpServletRequest request) throws Exception {
		
		UsuarioBean usuario = this.getUsuarioSesion(request);
		UsuarioBean oUsuarioBean = new UsuarioBean();
		/*HttpSession session = request.getSession();
		session.removeAttribute("usuarioSesion");
		session.invalidate();
		*/
		//this.setUsuarioBean(new UsuarioBean());
	
		//Inicializando el Encrypt
		Encrypt.init("KEY_ENCRYPT_PASS");
		
		String passActual = "";
		String passNew = "";
		
		if (usuarioBean != null) {
			UsuarioBean prmUsuario = new UsuarioBean();
			//Encriptando la clave ingresada
			passActual = Encrypt.encrypt(usuarioBean.getPasswordUsuario());
			passNew    = Encrypt.encrypt(usuarioBean.getNewPassword());
			
			prmUsuario.setCodigoUsuario(usuarioBean.getCodigoUsuario());
			prmUsuario.setPasswordUsuario(passActual);
			prmUsuario.setNewPassword(passNew);
			prmUsuario.setFlgRestPass("0");
			this.setAuditoria(prmUsuario, request, false);
			Integer resultI = usuarioService.cambiarPassword(prmUsuario);
			
			if (resultI != null && resultI>0) {
				UsuarioBean filtro = new UsuarioBean();
				filtro.setCodigoUsuario(usuarioBean.getCodigoUsuario());
				
			  oUsuarioBean = usuarioService.buscarxcodigousua(filtro);
				if(oUsuarioBean != null){
					usuario = oUsuarioBean;
				}
				usuario.setPasswordUsuario(passActual);
				usuario.setFlgRestPass("0");
				/** BUSCAR LOS PERFILES DEL USUARIO **/
				List<UsuarioPerfilBean> lstUsuarioPerfilBean = new ArrayList<UsuarioPerfilBean>();
				UsuarioPerfilBean filtroUsuarioPerfilBean = new UsuarioPerfilBean();
				filtroUsuarioPerfilBean.setUsuario(new UsuarioBean());
				filtroUsuarioPerfilBean.getUsuario().setCodigoUsuario(usuarioBean.getCodigo());
				lstUsuarioPerfilBean = usuarioPerfilService.getBuscarPorFiltros(filtroUsuarioPerfilBean);
				
				String codPerfilSelecc = oUsuarioBean.getPerfil().getCodigo();// "1";
				
				if( !VO.isEmptyList(lstUsuarioPerfilBean) ){
					UsuarioPerfilBean prmUP = lstUsuarioPerfilBean.get(0);
					codPerfilSelecc = prmUP.getPerfil().getCodigoPerfil();
				}
				System.out.println("codPerfilSelecc  " + codPerfilSelecc);
				usuario.setCodPerfilUsuSelec(codPerfilSelecc);
				usuario.setLstUsuarioPerfil(lstUsuarioPerfilBean);
						
				return this.getLista(usuario, request);
			} else {
				UsuarioBean filtro = new UsuarioBean();
				filtro.setCodigoUsuario(usuarioBean.getCodigoUsuario());
				 oUsuarioBean = usuarioService.buscarxcodigousua(filtro);
				
				if(oUsuarioBean != null){
					usuario = oUsuarioBean;
				}
				request.getSession().setAttribute("usuarioSesion", usuario);
				ModelAndView mav = new ModelAndView("seguridad/login/cambiar-contrasenia", "command",usuarioBean);
				mav.addObject("msgErrorLogin", "La contrase&nacute;a no coincide con la actual");
				mav.addObject("usuarioBean", usuarioBean);
				return mav;
			}
		}else{
			
			return  new ModelAndView("seguridad/login/login", "command",new LoginVo());
		}
	}
	 
	private ModelAndView getLista(UsuarioBean usuario, HttpServletRequest request) { 
		
		ModelAndView mav = new ModelAndView("portada", "command", new InicioForm());
		

		System.out.println("inicia sistema ");
		
		try {
			
			int totalPerfiles = 0;
			
			/** TRAER ACCESOS **/
			if(!VO.isEmptyList(usuario.getLstUsuarioPerfil())){
				System.out.println("pasa mi modulo");
				System.out.println("usuario.getCodPerfilUsuSelec() " + usuario.getCodPerfilUsuSelec()); 
				totalPerfiles = usuario.getLstUsuarioPerfil().size();
				
				AccesoBean filtroAccesoBean = new AccesoBean();
				filtroAccesoBean.setPerfil(new PerfilBean());
				filtroAccesoBean.getPerfil().setCodigo(usuario.getCodPerfilUsuSelec()); 
				
				 
				List<AccesoBean> lstAccesoBean =  accesoService.getBuscarPorFiltros(filtroAccesoBean);
				
				
				AccesoMenuVo accesoMenuVo = verificarAccesos(lstAccesoBean);
				request.getSession().setAttribute("accesoMenu", accesoMenuVo);
				mav.addObject("accesoMenu", accesoMenuVo);		
				usuario.setLstAcceso(lstAccesoBean);
				
				
			}
			
			try {
				lstSituacion = maestraAsis14Service.listarPorCodigoTabla("000012", 1);
				lstTipoPaciente = maestraAsis14Service.listarPorCodigoTabla("000007", 1);
			} catch (ServiceException e) {
				System.out.println("printStackTrace");
				e.printStackTrace();
			}
			this.setUsuarioBean(usuario);	
			request.getSession().setAttribute("totalPerfiles", totalPerfiles);
			mav.addObject("totalPerfiles",totalPerfiles); 
			mav.addObject("lstSituacion",lstSituacion); 
			mav.addObject("lstTipoPaciente",lstTipoPaciente);
			request.getSession().setAttribute("usuarioSesion", usuario);
			mav.addObject("usuarioSesion", usuario);
			this.cargarDatosResumenDiario(mav);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
	}
	
	
	 
	
	private void cargarCombos(ModelAndView mav){
		try {
			lstSituacion = maestraAsis14Service.listarPorCodigoTabla("000012", 1);
			lstTipoPaciente = maestraAsis14Service.listarPorCodigoTabla("000007", 1);
		} catch (ServiceException e) {
			System.out.println("printStackTrace");
			e.printStackTrace();
		}  
		mav.addObject("lstSituacion",lstSituacion); 
		mav.addObject("lstTipoPaciente",lstTipoPaciente);
	}
	
	private void cargarDatosResumenDiario(ModelAndView mav) {
      nombreVenta="VENTA DEL DIA";
      tipoReporte=1;
		/***mandamos datos del tablero de fecha actual por default a los reportes del día***/
		obtenerFechaHoraDia();
		uOrderBean = new OrdenBean();
		uOrdenBeanVentadiaria = new OrdenBean();
		
		OrdenBean uOrdenBean2 = new OrdenBean();
		OrdenBean uOrdenBean3 = new OrdenBean();
		
		OrdenBean uOrdenBeanArea  = new OrdenBean();
		OrdenBean uOrdenBeanBarra  = new OrdenBean();
		lstOrdenBeanArea = new ArrayList<OrdenBean>();
		lstOrdenBeanBarra = new ArrayList<OrdenBean>();
		lstOrdenBeanCircular = new ArrayList<OrdenBean>();
		
		OrdenBean prmOrdenBean1 = new OrdenBean();
		OrdenBean prmOrdenBean2 = new OrdenBean();
		OrdenBean prmOrdenBean3 = new OrdenBean();
		
		OrdenBean prmOrdenBeanArea  = new OrdenBean();
		OrdenBean prmOrdenBeanBarra  = new OrdenBean();
		
		OrdenBean prmOrdenBeanVentaDiaria = new OrdenBean();
		
		
		Date date = new Date();
		SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat mes = new SimpleDateFormat("MM");
		SimpleDateFormat anio = new SimpleDateFormat("yyyy");
		mes.format(date);
		anio.format(date);
		fecha.format(date);
		String strDate = fecha.format(date);
		System.out.println("strDate"+strDate);
		String strMes = mes.format(date);
		System.out.println("strMes"+strMes);
		String strAnio = anio.format(date);
		System.out.println("strAnio"+strAnio);
		try {
			Date dt = fecha.parse(strDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		prmOrdenBean1.setFecha(strDate);

		prmOrdenBean2.setFecha(strDate);
   
		prmOrdenBean3.setFecha(strDate);

		prmOrdenBeanVentaDiaria.setFecha(strDate);
		/**situacion 000001 pendiente 000002 atendido 000003 anulado ***/
		TablaBean tabla1 = new TablaBean();
		tabla1.setCodReg("000001");
		TablaBean tabla2 = new TablaBean();
		tabla2.setCodReg("000002");
		TablaBean tabla3 = new TablaBean();
		tabla3.setCodReg("000003");
		prmOrdenBean1.setSituacion(tabla1);
		prmOrdenBean2.setSituacion(tabla2);
		prmOrdenBean3.setSituacion(tabla3);
		prmOrdenBeanArea.setNroMes(strMes);
		prmOrdenBeanArea.setNumeroPeriodo(strAnio);
		prmOrdenBeanBarra.setNumeroPeriodo(strAnio);
		System.out.println("acaPortada");
		try { 
			uOrderBean = ordenService.reporteCantidadDiarioOrdenSituacion(prmOrdenBean1);
			uOrdenBean2 = ordenService.reporteCantidadDiarioOrdenSituacion(prmOrdenBean2);
			uOrdenBean3 = ordenService.reporteCantidadDiarioOrdenSituacion(prmOrdenBean3);
			uOrdenBeanVentadiaria = ordenService.reporteVentaDiaria(prmOrdenBeanVentaDiaria);		
			uOrdenBeanVentadiaria.setSwDiario(true);
//			uOrdenBeanArea = (OrdenBean) ordenService.reporteVentaMensualPeriodoArea(prmOrdenBeanArea);
			//	uOrdenBeanBarra = (OrdenBean) ordenService.reporteVentaMensualPeriodoArea(prmOrdenBeanBarra);
				lstOrdenBeanArea = ordenService.reporteVentaMensualPeriodoArea(prmOrdenBeanArea);
				lstOrdenBeanBarra = ordenService.reporteVentaMensualPeriodoBarra(prmOrdenBeanBarra);
				lstOrdenBeanCircular = ordenService.reporteCantidadDiarioExamenesSolicitados(prmOrdenBeanVentaDiaria);
		//		lstOrdenBeanCircular = ordenService.reporteCantidadDiarioExamenesSolicitadosTotal(prmOrdenBeanVentaDiaria);
				System.out.println("lstOrdenBeanArea"+lstOrdenBeanArea.size());
				System.out.println("lstOrdenBeanCircular"+lstOrdenBeanCircular.size());
			if(uOrderBean!=null){   
				
				
			}else{   
			} 
	} catch (Exception e) {  
	}
		
		mav.addObject("uOrderBean", 		   uOrderBean);
		mav.addObject("uOrdenBean2", 		   uOrdenBean2);
		mav.addObject("uOrdenBean3", 		   uOrdenBean3);
		mav.addObject("uOrdenBeanVentadiaria", uOrdenBeanVentadiaria);
		mav.addObject("lstOrdenBeanArea",	   lstOrdenBeanArea);
		mav.addObject("lstOrdenBeanBarra", 	   lstOrdenBeanBarra);
		mav.addObject("lstOrdenBeanCircular",  lstOrdenBeanCircular);
		mav.addObject("diaSemana", 	   		   diaSemana);
		mav.addObject("nombreVenta", 	   	   nombreVenta);
	}
	
	
	
	@RequestMapping(value = "/portada", method = RequestMethod.GET)
	public ModelAndView portada(@ModelAttribute("usuarioSesion") UsuarioBean usuario,
			HttpServletRequest request) throws Exception {
		ModelAndView mav =  new ModelAndView("portada", "command", new InicioForm());
		  tipoReporte=1;
		 nombreVenta="VENTA DEL DIA";
		
		/***mandamos datos del tablero de fecha actual por default a los reportes del día***/
		obtenerFechaHoraDia();
		uOrderBean = new OrdenBean();
		uOrdenBeanVentadiaria = new OrdenBean();
		
		OrdenBean uOrdenBean2 = new OrdenBean();
		OrdenBean uOrdenBean3 = new OrdenBean();
		
		OrdenBean uOrdenBeanArea  = new OrdenBean();
		OrdenBean uOrdenBeanBarra  = new OrdenBean();
		lstOrdenBeanArea = new ArrayList<OrdenBean>();
		lstOrdenBeanBarra = new ArrayList<OrdenBean>();
		lstOrdenBeanCircular = new ArrayList<OrdenBean>();
		
		OrdenBean prmOrdenBean1 = new OrdenBean();
		OrdenBean prmOrdenBean2 = new OrdenBean();
		OrdenBean prmOrdenBean3 = new OrdenBean();
		
		OrdenBean prmOrdenBeanArea  = new OrdenBean();
		OrdenBean prmOrdenBeanBarra  = new OrdenBean();
		
		OrdenBean prmOrdenBeanVentaDiaria = new OrdenBean();
		Date date = new Date();
		SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat mes = new SimpleDateFormat("MM");
		SimpleDateFormat anio = new SimpleDateFormat("yyyy");
		mes.format(date);
		anio.format(date);
		fecha.format(date);
		String strDate = fecha.format(date);
		System.out.println("strDate"+strDate);
		String strMes = mes.format(date);
		System.out.println("strMes"+strMes);
		String strAnio = anio.format(date);
		System.out.println("strAnio"+strAnio);
		Date dt = fecha.parse(strDate);
	//	prmOrdenBean1.setFechaOrden(dt);
		prmOrdenBean1.setFecha(strDate);
	//	prmOrdenBean2.setFechaOrden(dt);
		prmOrdenBean2.setFecha(strDate);
    //	prmOrdenBean3.setFechaOrden(dt);
		prmOrdenBean3.setFecha(strDate);
	//	prmOrdenBeanVentaDiaria.setFechaOrden(dt);
		prmOrdenBeanVentaDiaria.setFecha(strDate);
		/**situacion 000001 pendiente 000002 atendido 000003 anulado ***/
		TablaBean tabla1 = new TablaBean();
		tabla1.setCodReg("000001");
		TablaBean tabla2 = new TablaBean();
		tabla2.setCodReg("000002");
		TablaBean tabla3 = new TablaBean();
		tabla3.setCodReg("000003");
		prmOrdenBean1.setSituacion(tabla1);
		prmOrdenBean2.setSituacion(tabla2);
		prmOrdenBean3.setSituacion(tabla3);
		prmOrdenBeanArea.setNroMes(strMes);
		prmOrdenBeanArea.setNumeroPeriodo(strAnio);
		prmOrdenBeanBarra.setNumeroPeriodo(strAnio);
		System.out.println("acaPortada");
		try { 
			uOrderBean = ordenService.reporteCantidadDiarioOrdenSituacion(prmOrdenBean1);
			uOrdenBean2 = ordenService.reporteCantidadDiarioOrdenSituacion(prmOrdenBean2);
			uOrdenBean3 = ordenService.reporteCantidadDiarioOrdenSituacion(prmOrdenBean3);
			uOrdenBeanVentadiaria = ordenService.reporteVentaDiaria(prmOrdenBeanVentaDiaria);	
			uOrdenBeanVentadiaria.setSwDiario(true);
//			uOrdenBeanArea = (OrdenBean) ordenService.reporteVentaMensualPeriodoArea(prmOrdenBeanArea);
			//	uOrdenBeanBarra = (OrdenBean) ordenService.reporteVentaMensualPeriodoArea(prmOrdenBeanBarra);
				lstOrdenBeanArea = ordenService.reporteVentaMensualPeriodoArea(prmOrdenBeanArea);
				lstOrdenBeanBarra = ordenService.reporteVentaMensualPeriodoBarra(prmOrdenBeanBarra);
				lstOrdenBeanCircular = ordenService.reporteCantidadDiarioExamenesSolicitados(prmOrdenBeanVentaDiaria);
			
				System.out.println("lstOrdenBeanArea"+lstOrdenBeanArea.size());
				System.out.println("lstOrdenBeanCircular"+lstOrdenBeanCircular.size());
			if(uOrderBean!=null){   
				
				
			}else{   
			} 
	} catch (Exception e) {  
	}
		
		mav.addObject("uOrderBean", 		   uOrderBean);
		mav.addObject("uOrdenBean2", 		   uOrdenBean2);
		mav.addObject("uOrdenBean3", 		   uOrdenBean3);
		mav.addObject("uOrdenBeanVentadiaria", uOrdenBeanVentadiaria);
		mav.addObject("lstOrdenBeanArea",	   lstOrdenBeanArea);
		mav.addObject("lstOrdenBeanBarra", 	   lstOrdenBeanBarra);
		mav.addObject("lstOrdenBeanCircular",  lstOrdenBeanCircular);
		mav.addObject("diaSemana", 	   		   diaSemana);
		mav.addObject("nombreVenta", 	   	   nombreVenta);
		
		
		return mav;
	}
	
	
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ModelAndView listar(@ModelAttribute("usuarioSesion") UsuarioBean usuario,
			HttpServletRequest request) throws Exception {
	ModelAndView mav =  new ModelAndView("portada", "command", new InicioForm());
		 System.out.println("iniciamos sistema");
		 nombreVenta="VENTA DEL DIA";
		 tipoReporte=1;
		/***mandamos datos del tablero de fecha actual por default a los reportes del día***/
		obtenerFechaHoraDia();
		uOrderBean = new OrdenBean();
		uOrdenBeanVentadiaria = new OrdenBean();
		
		OrdenBean uOrdenBean2 = new OrdenBean();
		OrdenBean uOrdenBean3 = new OrdenBean();
		
		OrdenBean uOrdenBeanArea  = new OrdenBean();
		OrdenBean uOrdenBeanBarra  = new OrdenBean();
		lstOrdenBeanArea = new ArrayList<OrdenBean>();
		lstOrdenBeanBarra = new ArrayList<OrdenBean>();
		lstOrdenBeanCircular = new ArrayList<OrdenBean>();
		
		OrdenBean prmOrdenBean1 = new OrdenBean();
		OrdenBean prmOrdenBean2 = new OrdenBean();
		OrdenBean prmOrdenBean3 = new OrdenBean();
		
		OrdenBean prmOrdenBeanArea  = new OrdenBean();
		OrdenBean prmOrdenBeanBarra  = new OrdenBean();
		
		OrdenBean prmOrdenBeanVentaDiaria = new OrdenBean();
		Date date = new Date();
		SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat mes = new SimpleDateFormat("MM");
		SimpleDateFormat anio = new SimpleDateFormat("yyyy");
		mes.format(date);
		anio.format(date);
		fecha.format(date);
		String strDate = fecha.format(date);
		System.out.println("strDate"+strDate);
		String strMes = mes.format(date);
		System.out.println("strMes"+strMes);
		String strAnio = anio.format(date);
		System.out.println("strAnio"+strAnio);
		Date dt = fecha.parse(strDate);
	//	prmOrdenBean1.setFechaOrden(dt);
		prmOrdenBean1.setFecha(strDate);
	//	prmOrdenBean2.setFechaOrden(dt);
		prmOrdenBean2.setFecha(strDate);
    //	prmOrdenBean3.setFechaOrden(dt);
		prmOrdenBean3.setFecha(strDate);
	//	prmOrdenBeanVentaDiaria.setFechaOrden(dt);
		prmOrdenBeanVentaDiaria.setFecha(strDate);
		/**situacion 000001 pendiente 000002 atendido 000003 anulado ***/
		TablaBean tabla1 = new TablaBean();
		tabla1.setCodReg("000001");
		TablaBean tabla2 = new TablaBean();
		tabla2.setCodReg("000002");
		TablaBean tabla3 = new TablaBean();
		tabla3.setCodReg("000003");
		prmOrdenBean1.setSituacion(tabla1);
		prmOrdenBean2.setSituacion(tabla2);
		prmOrdenBean3.setSituacion(tabla3);
		prmOrdenBeanArea.setNroMes(strMes);
		prmOrdenBeanArea.setNumeroPeriodo(strAnio);
		prmOrdenBeanBarra.setNumeroPeriodo(strAnio);
		System.out.println("acaPortada");
		try { 
			uOrderBean = ordenService.reporteCantidadDiarioOrdenSituacion(prmOrdenBean1);
			uOrdenBean2 = ordenService.reporteCantidadDiarioOrdenSituacion(prmOrdenBean2);
			uOrdenBean3 = ordenService.reporteCantidadDiarioOrdenSituacion(prmOrdenBean3);
			uOrdenBeanVentadiaria = ordenService.reporteVentaDiaria(prmOrdenBeanVentaDiaria);			
//			uOrdenBeanArea = (OrdenBean) ordenService.reporteVentaMensualPeriodoArea(prmOrdenBeanArea);
			//	uOrdenBeanBarra = (OrdenBean) ordenService.reporteVentaMensualPeriodoArea(prmOrdenBeanBarra);
				lstOrdenBeanArea = ordenService.reporteVentaMensualPeriodoArea(prmOrdenBeanArea);
				lstOrdenBeanBarra = ordenService.reporteVentaMensualPeriodoBarra(prmOrdenBeanBarra);
				lstOrdenBeanCircular = ordenService.reporteCantidadDiarioExamenesSolicitados(prmOrdenBeanVentaDiaria);
			
				System.out.println("lstOrdenBeanArea"+lstOrdenBeanArea.size());
				System.out.println("lstOrdenBeanCircular"+lstOrdenBeanCircular.size());
			if(uOrderBean!=null){   
				
				
			}else{   
			} 
	} catch (Exception e) {  
	}
		
		mav.addObject("uOrderBean", 		   uOrderBean);
		mav.addObject("uOrdenBean2", 		   uOrdenBean2);
		mav.addObject("uOrdenBean3", 		   uOrdenBean3);
		mav.addObject("uOrdenBeanVentadiaria", uOrdenBeanVentadiaria);
		mav.addObject("lstOrdenBeanArea",	   lstOrdenBeanArea);
		mav.addObject("lstOrdenBeanBarra", 	   lstOrdenBeanBarra);
		mav.addObject("lstOrdenBeanCircular",  lstOrdenBeanCircular);
		mav.addObject("diaSemana", 	   		   diaSemana);
		mav.addObject("nombreVenta", 	   	   nombreVenta);
		
		
		return mav;
	//	return this.getLista(usuario, request);
	}
	
	@RequestMapping(value = "/obtenerAccesos", method = RequestMethod.GET)
	@ResponseBody
	public void obtenerAccesos(@RequestParam("codperfil") String codperfil,
			HttpServletRequest request) throws Exception {
		
		UsuarioBean usuario = this.getUsuarioSesion(request);
		usuario.setCodPerfilUsuSelec(codperfil);
		request.getSession().setAttribute("usuarioSesion", usuario);
		
	}
	
	
	/*
	@RequestMapping(value = "/reporteLenguas", method = RequestMethod.GET)
	@ResponseBody
	public List<InstitucionLenguaBean> getReporteLenguas(@RequestParam("codinst") String codinst,
			HttpServletRequest request) throws Exception {
		
		List<InstitucionLenguaBean> lista = new ArrayList<InstitucionLenguaBean>();
		
		try {
			InstitucionLenguaBean filtro = new InstitucionLenguaBean();
			filtro.getInstitucionBean().setCodigo(VO.toLong(codinst));
			lista = this.fs.getInstitucionLenguaService().reporteAlumLengInst(filtro);
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return lista;
	}
	
	*/
	
	/*
	
	@RequestMapping(value = "/reporteObjetos", method = RequestMethod.GET)
	public ModelAndView ReporteObjetos(@ModelAttribute("ordenBean") OrdenBean ordenBean,
			HttpServletRequest request) throws Exception {
		uOrderBean = new OrdenBean();
		OrdenBean prmOrdenBean = new OrdenBean();
		Date date = new Date();
		try { 
			uOrderBean = ordenService.reporteCantidadAnualOrdenSituacion(prmOrdenBean);
			if(uOrderBean!=null){   
				
				
			}else{   
			} 
	} catch (Exception e) {  
	}
		
		return this.getLista(usuario, request);
	}
	
	
	*/
	
	private AccesoMenuVo verificarAccesos(List<AccesoBean> listaAcceso) {
		System.out.println("listaAcceso " + listaAcceso.size());
		AccesoMenuVo accesoMenuVo = new AccesoMenuVo();
		
		if(!VO.isEmptyList(listaAcceso)){
			for (int i = 0; i < listaAcceso.size(); i++) {
				AccesoBean bean = listaAcceso.get(i);
				
				if(		!VO.isNull(bean.getComponente()) 
					&& 	!VO.isNull(bean.getComponente().getNombreComponente())
				  ){
					
					String nombreComponente = bean.getComponente().getNombreComponente();
					
					/** INDICADOR **/
					if (nombreComponente.equals("estadisticos")) {
						
						accesoMenuVo.setMenu_Estadisticos(!VO.isNull(bean.getFlgAsignado()) && bean.getFlgAsignado().equals("1") ? true : false);
						
					}
					
					/** REFERENCIA **/
					if (nombreComponente.equals("referencia")) {
						
						accesoMenuVo.setMenu_Referencia(!VO.isNull(bean.getFlgAsignado()) && bean.getFlgAsignado().equals("1") ? true : false);
						
					}
					
					/** CONTRAREFERENCIA **/
					if (nombreComponente.equals("contrareferencia")) {
						
						accesoMenuVo.setMenu_Contrareferencia(!VO.isNull(bean.getFlgAsignado()) && bean.getFlgAsignado().equals("1") ? true : false);
						
					}
					
					/** CITA **/
					if (nombreComponente.equals("citas")) {
						
						accesoMenuVo.setMenu_Citas(!VO.isNull(bean.getFlgAsignado()) && bean.getFlgAsignado().equals("1") ? true : false);
						
					} 
					
					/** BANCO DE SANGRE **/
					if (nombreComponente.equals("bancoSangre")) {
						
						accesoMenuVo.setMenu_BancoSangre(!VO.isNull(bean.getFlgAsignado()) && bean.getFlgAsignado().equals("1") ? true : false);
						
					} if (nombreComponente.equals("postulantes")) {
						accesoMenuVo.setSubMenu_banco_postulante(new PermisoVo());
						accesoMenuVo.getSubMenu_banco_postulante().setAsignado((!VO.isNull(bean.getFlgAsignado()) && bean.getFlgAsignado().equals("1") ? true : false));
						accesoMenuVo.getSubMenu_banco_postulante().setLectura(bean.isFlgRead() ? true : false);
						accesoMenuVo.getSubMenu_banco_postulante().setEscritura(bean.isFlgWrite() ? true : false);
						accesoMenuVo.getSubMenu_banco_postulante().setEliminacion(bean.isFlgDelete() ? true : false);
						accesoMenuVo.getSubMenu_banco_postulante().setExportacion(bean.isFlgExport() ? true : false);
						
					} if (nombreComponente.equals("estadoFisico")) {
						accesoMenuVo.setSubMenu_banco_estadoFisico(new PermisoVo());
						accesoMenuVo.getSubMenu_banco_estadoFisico().setAsignado((!VO.isNull(bean.getFlgAsignado()) && bean.getFlgAsignado().equals("1") ? true : false));
						accesoMenuVo.getSubMenu_banco_estadoFisico().setLectura(bean.isFlgRead() ? true : false);
						accesoMenuVo.getSubMenu_banco_estadoFisico().setEscritura(bean.isFlgWrite() ? true : false);
						accesoMenuVo.getSubMenu_banco_estadoFisico().setEliminacion(bean.isFlgDelete() ? true : false);
						accesoMenuVo.getSubMenu_banco_estadoFisico().setExportacion(bean.isFlgExport() ? true : false);
					
					} if (nombreComponente.equals("entrevista")) {
						accesoMenuVo.setSubMenu_banco_entrevista(new PermisoVo());
						accesoMenuVo.getSubMenu_banco_entrevista().setAsignado((!VO.isNull(bean.getFlgAsignado()) && bean.getFlgAsignado().equals("1") ? true : false));
						accesoMenuVo.getSubMenu_banco_entrevista().setLectura(bean.isFlgRead() ? true : false);
						accesoMenuVo.getSubMenu_banco_entrevista().setEscritura(bean.isFlgWrite() ? true : false);
						accesoMenuVo.getSubMenu_banco_entrevista().setEliminacion(bean.isFlgDelete() ? true : false);
						accesoMenuVo.getSubMenu_banco_entrevista().setExportacion(bean.isFlgExport() ? true : false); 
						
					} if (nombreComponente.equals("campania")) {
						accesoMenuVo.setSubMenu_banco_campania(new PermisoVo());
						accesoMenuVo.getSubMenu_banco_campania().setAsignado((!VO.isNull(bean.getFlgAsignado()) && bean.getFlgAsignado().equals("1") ? true : false));
						accesoMenuVo.getSubMenu_banco_campania().setLectura(bean.isFlgRead() ? true : false);
						accesoMenuVo.getSubMenu_banco_campania().setEscritura(bean.isFlgWrite() ? true : false);
						accesoMenuVo.getSubMenu_banco_campania().setEliminacion(bean.isFlgDelete() ? true : false);
						accesoMenuVo.getSubMenu_banco_campania().setExportacion(bean.isFlgExport() ? true : false); 
						
					}if (nombreComponente.equals("lugarCampania")) {
						accesoMenuVo.setSubMenu_banco_lugarCampania(new PermisoVo());
						accesoMenuVo.getSubMenu_banco_lugarCampania().setAsignado((!VO.isNull(bean.getFlgAsignado()) && bean.getFlgAsignado().equals("1") ? true : false));
						accesoMenuVo.getSubMenu_banco_lugarCampania().setLectura(bean.isFlgRead() ? true : false);
						accesoMenuVo.getSubMenu_banco_lugarCampania().setEscritura(bean.isFlgWrite() ? true : false);
						accesoMenuVo.getSubMenu_banco_lugarCampania().setEliminacion(bean.isFlgDelete() ? true : false);
						accesoMenuVo.getSubMenu_banco_lugarCampania().setExportacion(bean.isFlgExport() ? true : false); 
					}
					
					/** HERRAMIENTA **/
					if (nombreComponente.equals("herramientas")) {
						
						accesoMenuVo.setMenu_Herramientas(!VO.isNull(bean.getFlgAsignado()) && bean.getFlgAsignado().equals("1") ? true : false);
						
					} 
					
					/** SEGURIDAD **/
					if (nombreComponente.equals("seguridad")) {
						
						accesoMenuVo.setMenu_Seguridad(!VO.isNull(bean.getFlgAsignado()) && bean.getFlgAsignado().equals("1") ? true : false);
						
					}else if (nombreComponente.equals("usuario")) {
						accesoMenuVo.setSubMenu_seguridad_usuario(new PermisoVo());
						accesoMenuVo.getSubMenu_seguridad_usuario().setLectura(bean.isFlgRead() ? true : false);
						accesoMenuVo.getSubMenu_seguridad_usuario().setEscritura(bean.isFlgWrite() ? true : false);
						accesoMenuVo.getSubMenu_seguridad_usuario().setEliminacion(bean.isFlgDelete() ? true : false);
						accesoMenuVo.getSubMenu_seguridad_usuario().setExportacion(bean.isFlgExport() ? true : false);
						
					}else if (nombreComponente.equals("perfil")) {
						accesoMenuVo.setSubMenu_seguridad_perfil(new PermisoVo());
						accesoMenuVo.getSubMenu_seguridad_perfil().setLectura(bean.isFlgRead() ? true : false);
						accesoMenuVo.getSubMenu_seguridad_perfil().setEscritura(bean.isFlgWrite() ? true : false);
						accesoMenuVo.getSubMenu_seguridad_perfil().setEliminacion(bean.isFlgDelete() ? true : false);
						accesoMenuVo.getSubMenu_seguridad_perfil().setExportacion(bean.isFlgExport() ? true : false);
					
					}else if (nombreComponente.equals("acceso")) {
						accesoMenuVo.setSubMenu_seguridad_acceso(new PermisoVo());
						accesoMenuVo.getSubMenu_seguridad_acceso().setLectura(bean.isFlgRead() ? true : false);
						accesoMenuVo.getSubMenu_seguridad_acceso().setEscritura(bean.isFlgWrite() ? true : false);
						accesoMenuVo.getSubMenu_seguridad_acceso().setEliminacion(bean.isFlgDelete() ? true : false);
						accesoMenuVo.getSubMenu_seguridad_acceso().setExportacion(bean.isFlgExport() ? true : false); 
					} 
				}
				
			}
		}
	
		return accesoMenuVo;
	}
	
	
	@RequestMapping(value = "/listaDetalleVentaModal", method = RequestMethod.POST)
	public ModelAndView listaDetalleVentaModal( HttpServletRequest request)throws Exception  {
		String tipoVentaNombre ="";
		OrdenBean objOrdenBean ;
		System.out.println("tipoReporte"+tipoReporte);
		ModelAndView mav = null ;
		if (tipoReporte==1) {
			tipoVentaNombre="DETALLE DE VENTA DIARIA";
			System.out.println("listaDetalleVentaModalSemanal");
		    objOrdenBean = new OrdenBean(); 
			OrdenBean prmOrdenBean = new OrdenBean();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        Date date = new Date();
			prmOrdenBean.setFecha(dateFormat.format(date).toString());
		   
		    mav = new ModelAndView("asistencial/laboratorio/reporte/venta-diaria-modal", "command", objOrdenBean); 
			OrdenDetalleBean objOrdenDetalle = new OrdenDetalleBean();
			objOrdenDetalle.setOrdenBean(objOrdenBean);
			try {
				lstOrdenBeann = ordenService.reporteDetalleOrdenVentaDiaria(prmOrdenBean);
		
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			
			
		} else
		if (tipoReporte==2) {
			tipoVentaNombre="DETALLE DE VENTA SEMANAL";
			System.out.println("listaDetalleVentaModal");
	        objOrdenBean = new OrdenBean(); 
			OrdenBean prmOrdenBean = new OrdenBean();
			prmOrdenBean.setFecha("05-05-2019");
		   
	        mav = new ModelAndView("asistencial/laboratorio/reporte/venta-diaria-modal", "command", objOrdenBean); 
			OrdenDetalleBean objOrdenDetalle = new OrdenDetalleBean();
			objOrdenDetalle.setOrdenBean(objOrdenBean);
			try {
				lstOrdenBeann = ordenService.reporteDetalleOrdenVentaSemanal(prmOrdenBean);
		
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			

		} else
				if (tipoReporte==3) {
					tipoVentaNombre="DETALLE DE VENTA MENSUAL";
					System.out.println("listaDetalleVentaModalmensual");
					objOrdenBean = new OrdenBean(); 
					OrdenBean prmOrdenBean = new OrdenBean();
					prmOrdenBean.setFecha("05-05-2019");
					Date date = new Date();
					SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
					SimpleDateFormat mes = new SimpleDateFormat("MM");
					SimpleDateFormat anio = new SimpleDateFormat("yyyy");
					mes.format(date);
					anio.format(date);
					fecha.format(date);

					String strMes = mes.format(date);
					String strAnio = anio.format(date);


					prmOrdenBean.setNroMes(strMes);
					prmOrdenBean.setNumeroPeriodo(strAnio);

				
				   
					mav = new ModelAndView("asistencial/laboratorio/reporte/venta-diaria-modal", "command", objOrdenBean); 
					OrdenDetalleBean objOrdenDetalle = new OrdenDetalleBean();
					objOrdenDetalle.setOrdenBean(objOrdenBean);
					try {
						lstOrdenBeann = ordenService.reporteDetalleOrdenVentaMensual(prmOrdenBean);
				
					} catch (ServiceException e) {
						e.printStackTrace();
					}
					

				}else
					if (tipoReporte==4) {
						tipoVentaNombre="DETALLE DE VENTA ANUAL";
						System.out.println("listaDetalleVentaModalmensual");
						objOrdenBean = new OrdenBean(); 
						OrdenBean prmOrdenBean = new OrdenBean();
						prmOrdenBean.setFecha("05-05-2019");
						Date date = new Date();
						SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
						SimpleDateFormat mes = new SimpleDateFormat("MM");
						SimpleDateFormat anio = new SimpleDateFormat("yyyy");
						anio.format(date);
						fecha.format(date);

					
						String strAnio = anio.format(date);


					
						prmOrdenBean.setNumeroPeriodo(strAnio);

					
					   
						mav = new ModelAndView("asistencial/laboratorio/reporte/venta-diaria-modal", "command", objOrdenBean); 
						OrdenDetalleBean objOrdenDetalle = new OrdenDetalleBean();
						objOrdenDetalle.setOrdenBean(objOrdenBean);
						try {
							lstOrdenBeann = ordenService.reporteDetalleOrdenVentaAnual(prmOrdenBean);
					
						} catch (ServiceException e) {
							e.printStackTrace();
						}
						

					}
		
		
		

		
		
		mav.addObject("lstOrdenBean",    lstOrdenBeann);
		mav.addObject("tipoVentaNombre", tipoVentaNombre); 
		this.cargarCombos(mav);
		return mav;
	}
	
	
	  @RequestMapping(value = "/descargarExcelDiario", method = RequestMethod.GET, produces = "application/vnd.ms-excel")
	    public @ResponseBody void descargarExcelDiario(HttpServletResponse response) throws IOException {
	    	 try {
	    		 Workbook wb = generarExcelVentaDiaria();
	    		 response.setHeader("Content-disposition", "attachment; filename=reporteExcel.xls");
	    		 wb.write( response.getOutputStream() );
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
	    }
	  
	  @RequestMapping(value = "/descargarExcelSemanal", method = RequestMethod.GET, produces = "application/vnd.ms-excel")
	    public @ResponseBody void descargarExcelSemanal(HttpServletResponse response) throws IOException {
	    	 try {
	    		 Workbook wb = generarExcelVentaSemanal();
	    		 response.setHeader("Content-disposition", "attachment; filename=reporteExcel.xls");
	    		 wb.write( response.getOutputStream() );
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
	    }
	  
	  @RequestMapping(value = "/descargarExcelMensual", method = RequestMethod.GET, produces = "application/vnd.ms-excel")
	    public @ResponseBody void descargarExcelMensual(HttpServletResponse response) throws IOException {
	    	 try {
	    		 Workbook wb = generarExcelVentaMensual();
	    		 response.setHeader("Content-disposition", "attachment; filename=reporteExcel.xls");
	    		 wb.write( response.getOutputStream() );
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
	    }
	  
	  @RequestMapping(value = "/descargarExcelAnual", method = RequestMethod.GET, produces = "application/vnd.ms-excel")
	    public @ResponseBody void descargarExcelAnual(HttpServletResponse response) throws IOException {
	    	 try {
	    		 Workbook wb = generarExcelVentaAnual();
	    		 response.setHeader("Content-disposition", "attachment; filename=reporteExcel.xls");
	    		 wb.write( response.getOutputStream() );
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
	    }
	  
	  
	  @RequestMapping(value = "/descargarExcelTipo", method = RequestMethod.GET, produces = "application/vnd.ms-excel")
	    public @ResponseBody void descargarExcelTipo(HttpServletResponse response) throws IOException {
		  
		  		if (tipoReporte==1) {
		  			 try {
			    		 Workbook wb = generarExcelVentaDiaria();
			    		 response.setHeader("Content-disposition", "attachment; filename=reporteExcel.xls");
			    		 wb.write( response.getOutputStream() );
			         } catch (Exception e) {
			             e.printStackTrace();
			         }
					
				} else
				if (tipoReporte==2) {
					 try {
			    		 Workbook wb = generarExcelVentaSemanal();
			    		 response.setHeader("Content-disposition", "attachment; filename=reporteExcel.xls");
			    		 wb.write( response.getOutputStream() );
			         } catch (Exception e) {
			             e.printStackTrace();
			         }

				}
				 else
				if (tipoReporte==3) {
					 try {
			    		 Workbook wb = generarExcelVentaMensual();
			    		 response.setHeader("Content-disposition", "attachment; filename=reporteExcel.xls");
			    		 wb.write( response.getOutputStream() );
			         } catch (Exception e) {
			             e.printStackTrace();
			         }
					
				}
				else
				if (tipoReporte==4) {
					 try {
			    		 Workbook wb = generarExcelVentaAnual();
			    		 response.setHeader("Content-disposition", "attachment; filename=reporteExcel.xls");
			    		 wb.write( response.getOutputStream() );
			         } catch (Exception e) {
			             e.printStackTrace();
			         }
				}
	    	
	    }
	  
	  
	  
	  
	  
	   public HSSFWorkbook generarExcelVentaDiaria() {
		   
			OrdenBean objOrdenBean = new OrdenBean(); 
			OrdenBean prmOrdenBean = new OrdenBean();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        Date date = new Date();
			prmOrdenBean.setFecha(dateFormat.format(date).toString());
		   

			OrdenDetalleBean objOrdenDetalle = new OrdenDetalleBean();
			objOrdenDetalle.setOrdenBean(objOrdenBean);
			try {
				lstOrdenBeann = ordenService.reporteDetalleOrdenVentaDiaria(prmOrdenBean);
		
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		   
		   
	        try {
	            HSSFWorkbook workbook = new HSSFWorkbook();
	            //Hoja
	            HSSFSheet    sheet    = workbook.createSheet("SEGUIMIENTO DE ORDENES");
	            /**** color ***/
	            HSSFColor lightGray =  setColor(workbook,(byte) 0xE0, (byte)0xE0,(byte) 0xE0);
	            /**estilos**/
	            //estilo para el titulo
	            HSSFFont headerFont = workbook.createFont();
	            CellStyle titleStyle = workbook.createCellStyle();
	            //titleStyle.setFillForegroundColor(lightGray.getIndex());
	            titleStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
	            titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
	            titleStyle.setFont(headerFont);
	            //estilo para el cabecera
	            HSSFCellStyle headerStyle = workbook.createCellStyle();
	            headerStyle.setFillForegroundColor(lightGray.getIndex());
	            headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
	            headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	            headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	            headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	            headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	            headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	            //estilo para el cuerpo
	         	HSSFCellStyle bodyStyle = workbook.createCellStyle();
	         	//bodyStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	         	bodyStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
	         	bodyStyle.setAlignment(CellStyle.ALIGN_CENTER);
	         	bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	         	bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	         	bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	         	bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	            /*** tamaÃ¯Â¿Â½o de la columnas ***/
	            sheet.setColumnWidth(0, 2500); 
	            sheet.setColumnWidth(1, 2500);
	            sheet.setColumnWidth(2, 10000);
	            sheet.setColumnWidth(3, 10000); 
	            sheet.setColumnWidth(4, 10000);
	            sheet.setColumnWidth(5, 8000);
	            
	            sheet.setColumnWidth(6, 8000);
//	            sheet.setColumnWidth(7, 5000);
//	            sheet.setColumnWidth(8, 3000);
//	            sheet.setColumnWidth(9, 3000);
//	            sheet.setColumnWidth(7, 5000);
//	            sheet.setColumnWidth(8, 5000);
	            sheet.setColumnWidth(7, 5000);
	            /**** fuente ***/
	            //titulo
	            HSSFFont fontTitulo = workbook.createFont();
	            fontTitulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	            fontTitulo.setFontHeightInPoints((short) 14);
	            
	            titleStyle.setFont(fontTitulo);
	            //cabecera
	            HSSFFont fontCabecera = workbook.createFont();
	            fontCabecera.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	            fontCabecera.setFontHeightInPoints((short) 9);
	            
	            headerStyle.setFont(fontCabecera); 	
	            
	            /*** contenido del excel ***/
	            int rowIndex  = 0;
	            HSSFCell headerCell = null;
	            sheet.createRow( rowIndex++ );
	            sheet.createRow( rowIndex++ );
	            HSSFRow   headerRow    = sheet.createRow( rowIndex++ );
	            sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 7));
	            headerCell = headerRow.createCell(1);
	            headerCell.setCellValue("LISTADO DE VENTA DIARIA");
	            headerCell.setCellStyle(titleStyle);
	            sheet.createRow( rowIndex++ );
	            //Fila
	            HSSFRow      bodyRow    = sheet.createRow( rowIndex++ );
	            
	            
	           
	            
	            /************************* cabecera *****************************/
		            
	            headerCell = bodyRow.createCell(1);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("N°");
	            headerCell = bodyRow.createCell(2);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("PACIENTE");
	            headerCell = bodyRow.createCell(3);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("DNI");
	            headerCell = bodyRow.createCell(4);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("MONTO S/.");
	            headerCell = bodyRow.createCell(5);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("FECHA REG");
	            headerCell = bodyRow.createCell(6);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("HORA REG");
//	            headerCell = bodyRow.createCell(7);
//	            headerCell.setCellStyle(headerStyle);
//	            headerCell.setCellValue("NIVEL");
//	            headerCell = bodyRow.createCell(8);
//	            headerCell.setCellStyle(headerStyle);
//	            headerCell.setCellValue("CICLO");
//	            headerCell = bodyRow.createCell(9);
//	            headerCell.setCellStyle(headerStyle);
//	            headerCell.setCellValue("AÃ¯Â¿Â½O");
//	            headerCell = bodyRow.createCell(7);
//	            headerCell.setCellStyle(headerStyle);
//	            headerCell.setCellValue("FECHA INICIO");
//	            headerCell = bodyRow.createCell(8);
//	            headerCell.setCellStyle(headerStyle);
//	            headerCell.setCellValue("FECHA TERMINO");
	        
	            headerCell = bodyRow.createCell(7);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("SITUACION");
	            /******************* Contenido *************************/
	    		HSSFRow  contentRow  = null;
	    		HSSFCell contentCell = null;
	    		
	    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
	    	
	            for (int i = 0; i < lstOrdenBeann.size(); i++) {
	            	
//	            	personaBean personaBean = this.lstpersonaBean.get(i);
	            	OrdenBean ordenBean = lstOrdenBeann.get(i);
	            	contentRow = sheet.createRow( rowIndex++ );
	            	contentCell = contentRow.createCell(1);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(String.valueOf((i+1)));
	            	contentCell = contentRow.createCell(2);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getPacienteBean().getPersona().getNombreCompleto());
	            	contentCell = contentRow.createCell(3);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getPacienteBean().getPersona().getNroDocumento());
	            	contentCell = contentRow.createCell(4);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getImporteTotal());
	            	contentCell = contentRow.createCell(5);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getStrFechaOrden());
	            	contentCell = contentRow.createCell(6);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getHoraOrden());

	            	contentCell = contentRow.createCell(7);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getSituacion().getNombreCorto());
	            }
	            workbook.write(new FileOutputStream("reporteExcelVentaDiaria.xls"));

	            //return workbook.getBytes();
	            return workbook;
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	
	   public HSSFWorkbook generarExcelVentaSemanal() {
		   
			OrdenBean objOrdenBean = new OrdenBean(); 
			OrdenBean prmOrdenBean = new OrdenBean();
			prmOrdenBean.setFecha("05-05-2019");
		   
		
			OrdenDetalleBean objOrdenDetalle = new OrdenDetalleBean();
			objOrdenDetalle.setOrdenBean(objOrdenBean);
			try {
				lstOrdenBeann = ordenService.reporteDetalleOrdenVentaSemanal(prmOrdenBean);
		
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		   
		   
	        try {
	            HSSFWorkbook workbook = new HSSFWorkbook();
	            //Hoja
	            HSSFSheet    sheet    = workbook.createSheet("SEGUIMIENTO DE ORDENES");
	            /**** color ***/
	            HSSFColor lightGray =  setColor(workbook,(byte) 0xE0, (byte)0xE0,(byte) 0xE0);
	            /**estilos**/
	            //estilo para el titulo
	            HSSFFont headerFont = workbook.createFont();
	            CellStyle titleStyle = workbook.createCellStyle();
	            //titleStyle.setFillForegroundColor(lightGray.getIndex());
	            titleStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
	            titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
	            titleStyle.setFont(headerFont);
	            //estilo para el cabecera
	            HSSFCellStyle headerStyle = workbook.createCellStyle();
	            headerStyle.setFillForegroundColor(lightGray.getIndex());
	            headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
	            headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	            headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	            headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	            headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	            headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	            //estilo para el cuerpo
	         	HSSFCellStyle bodyStyle = workbook.createCellStyle();
	         	//bodyStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	         	bodyStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
	         	bodyStyle.setAlignment(CellStyle.ALIGN_CENTER);
	         	bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	         	bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	         	bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	         	bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	            /*** tamaÃ¯Â¿Â½o de la columnas ***/
	            sheet.setColumnWidth(0, 2500); 
	            sheet.setColumnWidth(1, 2500);
	            sheet.setColumnWidth(2, 10000);
	            sheet.setColumnWidth(3, 10000); 
	            sheet.setColumnWidth(4, 10000);
	            sheet.setColumnWidth(5, 8000);
	            
	            sheet.setColumnWidth(6, 8000);
//	            sheet.setColumnWidth(7, 5000);
//	            sheet.setColumnWidth(8, 3000);
//	            sheet.setColumnWidth(9, 3000);
//	            sheet.setColumnWidth(7, 5000);
//	            sheet.setColumnWidth(8, 5000);
	            sheet.setColumnWidth(7, 5000);
	            /**** fuente ***/
	            //titulo
	            HSSFFont fontTitulo = workbook.createFont();
	            fontTitulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	            fontTitulo.setFontHeightInPoints((short) 14);
	            
	            titleStyle.setFont(fontTitulo);
	            //cabecera
	            HSSFFont fontCabecera = workbook.createFont();
	            fontCabecera.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	            fontCabecera.setFontHeightInPoints((short) 9);
	            
	            headerStyle.setFont(fontCabecera); 	
	            
	            /*** contenido del excel ***/
	            int rowIndex  = 0;
	            HSSFCell headerCell = null;
	            sheet.createRow( rowIndex++ );
	            sheet.createRow( rowIndex++ );
	            HSSFRow   headerRow    = sheet.createRow( rowIndex++ );
	            sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 7));
	            headerCell = headerRow.createCell(1);
	            headerCell.setCellValue("LISTADO DE VENTA SEMANAL");
	            headerCell.setCellStyle(titleStyle);
	            sheet.createRow( rowIndex++ );
	            //Fila
	            HSSFRow      bodyRow    = sheet.createRow( rowIndex++ );
	            
	            
	           
	            
	            /************************* cabecera *****************************/
		            
	            headerCell = bodyRow.createCell(1);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("N°");
	            headerCell = bodyRow.createCell(2);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("PACIENTE");
	            headerCell = bodyRow.createCell(3);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("DNI");
	            headerCell = bodyRow.createCell(4);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("MONTO S/.");
	            headerCell = bodyRow.createCell(5);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("FECHA REG");
	            headerCell = bodyRow.createCell(6);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("HORA REG");

	            headerCell = bodyRow.createCell(7);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("SITUACION");
	            /******************* Contenido *************************/
	    		HSSFRow  contentRow  = null;
	    		HSSFCell contentCell = null;
	    		
	    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
	    	
	            for (int i = 0; i < lstOrdenBeann.size(); i++) {
	            	
//	            	personaBean personaBean = this.lstpersonaBean.get(i);
	            	OrdenBean ordenBean = lstOrdenBeann.get(i);
	            	contentRow = sheet.createRow( rowIndex++ );
	            	contentCell = contentRow.createCell(1);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(String.valueOf((i+1)));
	            	contentCell = contentRow.createCell(2);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getPacienteBean().getPersona().getNombreCompleto());
	            	contentCell = contentRow.createCell(3);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getPacienteBean().getPersona().getNroDocumento());
	            	contentCell = contentRow.createCell(4);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getImporteTotal());
	            	contentCell = contentRow.createCell(5);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getStrFechaOrden());
	            	contentCell = contentRow.createCell(6);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getHoraOrden());

	            	contentCell = contentRow.createCell(7);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getSituacion().getNombreCorto());
	            }
	            workbook.write(new FileOutputStream("reporteExcelVentaSemanal.xls"));

	            //return workbook.getBytes();
	            return workbook;
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	   
	   
	   public HSSFWorkbook generarExcelVentaMensual() {
		   
			OrdenBean objOrdenBean = new OrdenBean(); 
			OrdenBean prmOrdenBean = new OrdenBean();
			Date date = new Date();
			SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat mes = new SimpleDateFormat("MM");
			SimpleDateFormat anio = new SimpleDateFormat("yyyy");
			mes.format(date);
			anio.format(date);
			fecha.format(date);
	
			String strMes = mes.format(date);

			String strAnio = anio.format(date);
			

			prmOrdenBean.setNroMes(strMes);
			prmOrdenBean.setNumeroPeriodo(strAnio);
		   
		
			OrdenDetalleBean objOrdenDetalle = new OrdenDetalleBean();
			objOrdenDetalle.setOrdenBean(objOrdenBean);
			try {
				lstOrdenBeann = ordenService.reporteDetalleOrdenVentaMensual(prmOrdenBean);
		
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		   
		   
	        try {
	            HSSFWorkbook workbook = new HSSFWorkbook();
	            //Hoja
	            HSSFSheet    sheet    = workbook.createSheet("SEGUIMIENTO DE ORDENES");
	            /**** color ***/
	            HSSFColor lightGray =  setColor(workbook,(byte) 0xE0, (byte)0xE0,(byte) 0xE0);
	            /**estilos**/
	            //estilo para el titulo
	            HSSFFont headerFont = workbook.createFont();
	            CellStyle titleStyle = workbook.createCellStyle();
	            //titleStyle.setFillForegroundColor(lightGray.getIndex());
	            titleStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
	            titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
	            titleStyle.setFont(headerFont);
	            //estilo para el cabecera
	            HSSFCellStyle headerStyle = workbook.createCellStyle();
	            headerStyle.setFillForegroundColor(lightGray.getIndex());
	            headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
	            headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	            headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	            headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	            headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	            headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	            //estilo para el cuerpo
	         	HSSFCellStyle bodyStyle = workbook.createCellStyle();
	         	//bodyStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	         	bodyStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
	         	bodyStyle.setAlignment(CellStyle.ALIGN_CENTER);
	         	bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	         	bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	         	bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	         	bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	            /*** tamaÃ¯Â¿Â½o de la columnas ***/
	            sheet.setColumnWidth(0, 2500); 
	            sheet.setColumnWidth(1, 2500);
	            sheet.setColumnWidth(2, 10000);
	            sheet.setColumnWidth(3, 10000); 
	            sheet.setColumnWidth(4, 10000);
	            sheet.setColumnWidth(5, 8000);
	            
	            sheet.setColumnWidth(6, 8000);
//	            sheet.setColumnWidth(7, 5000);
//	            sheet.setColumnWidth(8, 3000);
//	            sheet.setColumnWidth(9, 3000);
//	            sheet.setColumnWidth(7, 5000);
//	            sheet.setColumnWidth(8, 5000);
	            sheet.setColumnWidth(7, 5000);
	            /**** fuente ***/
	            //titulo
	            HSSFFont fontTitulo = workbook.createFont();
	            fontTitulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	            fontTitulo.setFontHeightInPoints((short) 14);
	            
	            titleStyle.setFont(fontTitulo);
	            //cabecera
	            HSSFFont fontCabecera = workbook.createFont();
	            fontCabecera.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	            fontCabecera.setFontHeightInPoints((short) 9);
	            
	            headerStyle.setFont(fontCabecera); 	
	            
	            /*** contenido del excel ***/
	            int rowIndex  = 0;
	            HSSFCell headerCell = null;
	            sheet.createRow( rowIndex++ );
	            sheet.createRow( rowIndex++ );
	            HSSFRow   headerRow    = sheet.createRow( rowIndex++ );
	            sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 7));
	            headerCell = headerRow.createCell(1);
	            headerCell.setCellValue("LISTADO DE VENTA MENSUAL");
	            headerCell.setCellStyle(titleStyle);
	            sheet.createRow( rowIndex++ );
	            //Fila
	            HSSFRow      bodyRow    = sheet.createRow( rowIndex++ );
	            
	            
	           
	            
	            /************************* cabecera *****************************/
		            
	            headerCell = bodyRow.createCell(1);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("N°");
	            headerCell = bodyRow.createCell(2);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("PACIENTE");
	            headerCell = bodyRow.createCell(3);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("DNI");
	            headerCell = bodyRow.createCell(4);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("MONTO S/.");
	            headerCell = bodyRow.createCell(5);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("FECHA REG");
	            headerCell = bodyRow.createCell(6);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("HORA REG");
//	            headerCell = bodyRow.createCell(7);
//	            headerCell.setCellStyle(headerStyle);
//	            headerCell.setCellValue("NIVEL");
//	            headerCell = bodyRow.createCell(8);
//	            headerCell.setCellStyle(headerStyle);
//	            headerCell.setCellValue("CICLO");
//	            headerCell = bodyRow.createCell(9);
//	            headerCell.setCellStyle(headerStyle);
//	            headerCell.setCellValue("AÃ¯Â¿Â½O");
//	            headerCell = bodyRow.createCell(7);
//	            headerCell.setCellStyle(headerStyle);
//	            headerCell.setCellValue("FECHA INICIO");
//	            headerCell = bodyRow.createCell(8);
//	            headerCell.setCellStyle(headerStyle);
//	            headerCell.setCellValue("FECHA TERMINO");
	        
	            headerCell = bodyRow.createCell(7);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("SITUACION");
	            /******************* Contenido *************************/
	    		HSSFRow  contentRow  = null;
	    		HSSFCell contentCell = null;
	    		
	    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
	    	
	            for (int i = 0; i < lstOrdenBeann.size(); i++) {
	            	
//	            	personaBean personaBean = this.lstpersonaBean.get(i);
	            	OrdenBean ordenBean = lstOrdenBeann.get(i);
	            	contentRow = sheet.createRow( rowIndex++ );
	            	contentCell = contentRow.createCell(1);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(String.valueOf((i+1)));
	            	contentCell = contentRow.createCell(2);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getPacienteBean().getPersona().getNombreCompleto());
	            	contentCell = contentRow.createCell(3);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getPacienteBean().getPersona().getNroDocumento());
	            	contentCell = contentRow.createCell(4);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getImporteTotal());
	            	contentCell = contentRow.createCell(5);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getStrFechaOrden());
	            	contentCell = contentRow.createCell(6);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getHoraOrden());

	            	contentCell = contentRow.createCell(7);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getSituacion().getNombreCorto());
	            }
	            workbook.write(new FileOutputStream("reporteExcelVentaMensual.xls"));

	            //return workbook.getBytes();
	            return workbook;
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	   
	   public HSSFWorkbook generarExcelVentaAnual() {
		   
			OrdenBean objOrdenBean = new OrdenBean(); 
			OrdenBean prmOrdenBean = new OrdenBean();
			Date date = new Date();
			SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat mes = new SimpleDateFormat("MM");
			SimpleDateFormat anio = new SimpleDateFormat("yyyy");
			mes.format(date);
			anio.format(date);
			fecha.format(date);
			String strAnio = anio.format(date);
			prmOrdenBean.setNumeroPeriodo(strAnio);
		   

			OrdenDetalleBean objOrdenDetalle = new OrdenDetalleBean();
			objOrdenDetalle.setOrdenBean(objOrdenBean);
			try {
				lstOrdenBeann = ordenService.reporteDetalleOrdenVentaAnual(prmOrdenBean);
		
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		   
		   
	        try {
	            HSSFWorkbook workbook = new HSSFWorkbook();
	            //Hoja
	            HSSFSheet    sheet    = workbook.createSheet("SEGUIMIENTO DE ORDENES");
	            /**** color ***/
	            HSSFColor lightGray =  setColor(workbook,(byte) 0xE0, (byte)0xE0,(byte) 0xE0);
	            /**estilos**/
	            //estilo para el titulo
	            HSSFFont headerFont = workbook.createFont();
	            CellStyle titleStyle = workbook.createCellStyle();
	            //titleStyle.setFillForegroundColor(lightGray.getIndex());
	            titleStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
	            titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
	            titleStyle.setFont(headerFont);
	            //estilo para el cabecera
	            HSSFCellStyle headerStyle = workbook.createCellStyle();
	            headerStyle.setFillForegroundColor(lightGray.getIndex());
	            headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
	            headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	            headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	            headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	            headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	            headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	            //estilo para el cuerpo
	         	HSSFCellStyle bodyStyle = workbook.createCellStyle();
	         	//bodyStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	         	bodyStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
	         	bodyStyle.setAlignment(CellStyle.ALIGN_CENTER);
	         	bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	         	bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	         	bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	         	bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	            /*** tamaÃ¯Â¿Â½o de la columnas ***/
	            sheet.setColumnWidth(0, 2500); 
	            sheet.setColumnWidth(1, 2500);
	            sheet.setColumnWidth(2, 10000);
	            sheet.setColumnWidth(3, 10000); 
	            sheet.setColumnWidth(4, 10000);
	            sheet.setColumnWidth(5, 8000);
	            
	            sheet.setColumnWidth(6, 8000);
//	            sheet.setColumnWidth(7, 5000);
//	            sheet.setColumnWidth(8, 3000);
//	            sheet.setColumnWidth(9, 3000);
//	            sheet.setColumnWidth(7, 5000);
//	            sheet.setColumnWidth(8, 5000);
	            sheet.setColumnWidth(7, 5000);
	            /**** fuente ***/
	            //titulo
	            HSSFFont fontTitulo = workbook.createFont();
	            fontTitulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	            fontTitulo.setFontHeightInPoints((short) 14);
	            
	            titleStyle.setFont(fontTitulo);
	            //cabecera
	            HSSFFont fontCabecera = workbook.createFont();
	            fontCabecera.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	            fontCabecera.setFontHeightInPoints((short) 9);
	            
	            headerStyle.setFont(fontCabecera); 	
	            
	            /*** contenido del excel ***/
	            int rowIndex  = 0;
	            HSSFCell headerCell = null;
	            sheet.createRow( rowIndex++ );
	            sheet.createRow( rowIndex++ );
	            HSSFRow   headerRow    = sheet.createRow( rowIndex++ );
	            sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 7));
	            headerCell = headerRow.createCell(1);
	            headerCell.setCellValue("LISTADO DE VENTA ANUAL");
	            headerCell.setCellStyle(titleStyle);
	            sheet.createRow( rowIndex++ );
	            //Fila
	            HSSFRow      bodyRow    = sheet.createRow( rowIndex++ );
	            
	            
	           
	            
	            /************************* cabecera *****************************/
		            
	            headerCell = bodyRow.createCell(1);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("N°");
	            headerCell = bodyRow.createCell(2);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("PACIENTE");
	            headerCell = bodyRow.createCell(3);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("DNI");
	            headerCell = bodyRow.createCell(4);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("MONTO S/.");
	            headerCell = bodyRow.createCell(5);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("FECHA REG");
	            headerCell = bodyRow.createCell(6);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("HORA REG");

	        
	            headerCell = bodyRow.createCell(7);
	            headerCell.setCellStyle(headerStyle);
	            headerCell.setCellValue("SITUACION");
	            /******************* Contenido *************************/
	    		HSSFRow  contentRow  = null;
	    		HSSFCell contentCell = null;
	    		
	    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
	    	
	            for (int i = 0; i < lstOrdenBeann.size(); i++) {
	            	
//	            	personaBean personaBean = this.lstpersonaBean.get(i);
	            	OrdenBean ordenBean = lstOrdenBeann.get(i);
	            	contentRow = sheet.createRow( rowIndex++ );
	            	contentCell = contentRow.createCell(1);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(String.valueOf((i+1)));
	            	contentCell = contentRow.createCell(2);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getPacienteBean().getPersona().getNombreCompleto());
	            	contentCell = contentRow.createCell(3);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getPacienteBean().getPersona().getNroDocumento());
	            	contentCell = contentRow.createCell(4);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getImporteTotal());
	            	contentCell = contentRow.createCell(5);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getStrFechaOrden());
	            	contentCell = contentRow.createCell(6);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getHoraOrden());

	            	contentCell = contentRow.createCell(7);
	            	contentCell.setCellStyle(bodyStyle);
	            	contentCell.setCellValue(ordenBean.getSituacion().getNombreCorto());
	            }
	            workbook.write(new FileOutputStream("reporteExcelVentaAnual.xls"));

	            //return workbook.getBytes();
	            return workbook;
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	
	private void registrarAudAcceso(String nomUsuario, String tipoAccion, HttpServletRequest request){
		try {
			AuditoriaAccesoBean bean = new AuditoriaAccesoBean();
			bean.getTipoAccion().setCodReg(tipoAccion);
			bean.setNomUsuario(nomUsuario);
			bean.setIpCreacion(NetUtil.getClientIpAddr(request));
			bean.getTipoSistema().setCodReg("000001");
			
			if(!VO.isNull(bean.getIpCreacion()) && !bean.getIpCreacion().equals("0:0:0:0:0:0:0:1")){
				this.fs.getAuditoriaService().insertarAuditoriaAcceso(bean);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}




	public List<TablaBean> getLstMaestra() {
		return lstMaestra;
	}




	public void setLstMaestra(List<TablaBean> lstMaestra) {
		this.lstMaestra = lstMaestra;
	}
	
	
private void obtenerFechaHoraDia() {
	
	Calendar c = Calendar.getInstance();
	 String letraD="";
	int anio = 0,mes = 0,dia = 0;
	 c.set(anio,mes,dia); // vairables int
	  TimeZone timezone = TimeZone.getDefault();
      Calendar calendar = new GregorianCalendar(timezone);
	 int nD=calendar.get(Calendar.DAY_OF_WEEK);
	 switch (nD){
     case 1: letraD = "Domingo";
         break;
     case 2: letraD = "Lunes";
         break;
     case 3: letraD = "Martes";
         break;
     case 4: letraD = "Miércoles";
         break;
     case 5: letraD = "Jueves";
         break;
     case 6: letraD = "Viernes";
         break;
     case 7: letraD = "Sábado";
         break;
 }


	 
	
	
	DateFormat dateFormat = new SimpleDateFormat("HH:mm a");
	 Date date = new Date();
	  System.out.println("Hora actual: " + dateFormat.format(date));
	  diaSemana="Actualizado "+ letraD+" "+dateFormat.format(date) ;
}
	
public HSSFColor setColor(HSSFWorkbook workbook, byte r,byte g, byte b){
    HSSFPalette palette = workbook.getCustomPalette();
    HSSFColor hssfColor = null;
    try {
        hssfColor= palette.findColor(r, g, b); 
        if (hssfColor == null ){
            palette.setColorAtIndex(HSSFColor.LAVENDER.index, r, g,b);
            hssfColor = palette.getColor(HSSFColor.LAVENDER.index);
        }
    } catch (Exception e) {
    	e.printStackTrace();
    }

    return hssfColor;
}


@RequestMapping(value = "/portadaListarDiario", method = RequestMethod.GET)
public ModelAndView portadaListarDiario(HttpServletRequest request) throws Exception {
	
	ModelAndView mav =  new ModelAndView("portada", "command", new InicioForm());
	 tipoReporte=1;
	 nombreVenta="VENTA DEL DIA";
	
	/***mandamos datos del tablero de fecha actual por default a los reportes de la semana***/
	obtenerFechaHoraDia();
	uOrderBean = new OrdenBean();
	uOrdenBeanVentadiaria = new OrdenBean();
	
	OrdenBean uOrdenBean2 = new OrdenBean();
	OrdenBean uOrdenBean3 = new OrdenBean();
	
	OrdenBean uOrdenBeanArea  = new OrdenBean();
	OrdenBean uOrdenBeanBarra  = new OrdenBean();
	lstOrdenBeanArea = new ArrayList<OrdenBean>();
	lstOrdenBeanBarra = new ArrayList<OrdenBean>();
	lstOrdenBeanCircular = new ArrayList<OrdenBean>();
	
	OrdenBean prmOrdenBean1 = new OrdenBean();
	OrdenBean prmOrdenBean2 = new OrdenBean();
	OrdenBean prmOrdenBean3 = new OrdenBean();
	
	OrdenBean prmOrdenBeanArea  = new OrdenBean();
	OrdenBean prmOrdenBeanBarra  = new OrdenBean();
	
	OrdenBean prmOrdenBeanVentaDiaria = new OrdenBean();
	Date date = new Date();
	SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat mes = new SimpleDateFormat("MM");
	SimpleDateFormat anio = new SimpleDateFormat("yyyy");
	mes.format(date);
	anio.format(date);
	fecha.format(date);
	String strDate = fecha.format(date);
	System.out.println("strDate"+strDate);
	String strMes = mes.format(date);
	System.out.println("strMes"+strMes);
	String strAnio = anio.format(date);
	System.out.println("strAnio"+strAnio);
	Date dt = fecha.parse(strDate);
//	prmOrdenBean1.setFechaOrden(dt);
	prmOrdenBean1.setFecha(strDate);
//	prmOrdenBean2.setFechaOrden(dt);
	prmOrdenBean2.setFecha(strDate);
//	prmOrdenBean3.setFechaOrden(dt);
	prmOrdenBean3.setFecha(strDate);
//	prmOrdenBeanVentaDiaria.setFechaOrden(dt);
	prmOrdenBeanVentaDiaria.setFecha(strDate);
	/**situacion 000001 pendiente 000002 atendido 000003 anulado ***/
	TablaBean tabla1 = new TablaBean();
	tabla1.setCodReg("000001");
	TablaBean tabla2 = new TablaBean();
	tabla2.setCodReg("000002");
	TablaBean tabla3 = new TablaBean();
	tabla3.setCodReg("000003");
	prmOrdenBean1.setSituacion(tabla1);
	prmOrdenBean2.setSituacion(tabla2);
	prmOrdenBean3.setSituacion(tabla3);
	prmOrdenBeanArea.setNroMes(strMes);
	prmOrdenBeanArea.setNumeroPeriodo(strAnio);
	prmOrdenBeanBarra.setNumeroPeriodo(strAnio);
	System.out.println("acaPortada");
	try { 
		uOrderBean = ordenService.reporteCantidadDiarioOrdenSituacion(prmOrdenBean1);
	
		uOrdenBean2 = ordenService.reporteCantidadDiarioOrdenSituacion(prmOrdenBean2);
		
		uOrdenBean3 = ordenService.reporteCantidadDiarioOrdenSituacion(prmOrdenBean3);
		
		uOrdenBeanVentadiaria = ordenService.reporteVentaDiaria(prmOrdenBeanVentaDiaria);
		uOrdenBeanVentadiaria.setSwDiario(true);
//		uOrdenBeanArea = (OrdenBean) ordenService.reporteVentaMensualPeriodoArea(prmOrdenBeanArea);
			//	uOrdenBeanBarra = (OrdenBean) ordenService.reporteVentaMensualPeriodoArea(prmOrdenBeanBarra);
				lstOrdenBeanArea = ordenService.reporteVentaMensualPeriodoArea(prmOrdenBeanArea);
				lstOrdenBeanBarra = ordenService.reporteVentaMensualPeriodoBarra(prmOrdenBeanBarra);
				lstOrdenBeanCircular = ordenService.reporteCantidadDiarioExamenesSolicitados(prmOrdenBeanVentaDiaria);
		
				System.out.println("lstOrdenBeanArea"+lstOrdenBeanArea.size());
				System.out.println("lstOrdenBeanCircular"+lstOrdenBeanCircular.size());
		if(uOrderBean!=null){   
			
			
		}else{   
		} 
} catch (Exception e) {  
}
	
	mav.addObject("uOrderBean", 		   uOrderBean);
	mav.addObject("uOrdenBean2", 		   uOrdenBean2);
	mav.addObject("uOrdenBean3", 		   uOrdenBean3);
	mav.addObject("uOrdenBeanVentadiaria", uOrdenBeanVentadiaria);
	mav.addObject("lstOrdenBeanArea",	   lstOrdenBeanArea);
	mav.addObject("lstOrdenBeanBarra", 	   lstOrdenBeanBarra);
	mav.addObject("lstOrdenBeanCircular",  lstOrdenBeanCircular);
	mav.addObject("diaSemana", 	   		   diaSemana);
	mav.addObject("nombreVenta", 	   	   nombreVenta);
	
	return mav;	
	
	
};



@RequestMapping(value = "/portadaListarSemanal", method = RequestMethod.GET)
public ModelAndView portadaListarSemanal(HttpServletRequest request) throws Exception {
	System.out.println("VENTA DE LA SEMANA");
	ModelAndView mav =  new ModelAndView("portada", "command", new InicioForm());
	
	tipoReporte=2;
	System.out.println("VENTA DE LA SEMANA");
	nombreVenta="VENTA DE LA SEMANA";
	
	/***mandamos datos del tablero de fecha actual por default a los reportes de la semana***/
	obtenerFechaHoraDia();
	uOrderBean = new OrdenBean();
	uOrdenBeanVentaSemanal = new OrdenBean();
	
	OrdenBean uOrdenBean2 = new OrdenBean();
	OrdenBean uOrdenBean3 = new OrdenBean();
	
	
	lstOrdenBeanArea = new ArrayList<OrdenBean>();
	lstOrdenBeanBarra = new ArrayList<OrdenBean>();
	lstOrdenBeanCircular = new ArrayList<OrdenBean>();
	
	OrdenBean prmOrdenBean1 = new OrdenBean();
	OrdenBean prmOrdenBean2 = new OrdenBean();
	OrdenBean prmOrdenBean3 = new OrdenBean();
	
	OrdenBean prmOrdenBeanArea  = new OrdenBean();
	OrdenBean prmOrdenBeanBarra  = new OrdenBean();
	
	OrdenBean prmOrdenBeanVentaDiaria = new OrdenBean();
	Date date = new Date();
	SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat mes = new SimpleDateFormat("MM");
	SimpleDateFormat anio = new SimpleDateFormat("yyyy");
	mes.format(date);
	anio.format(date);
	fecha.format(date);
	String strDate = fecha.format(date);
	System.out.println("strDate"+strDate);
	String strMes = mes.format(date);
	System.out.println("strMes"+strMes);
	String strAnio = anio.format(date);
	System.out.println("strAnio"+strAnio);
	Date dt = fecha.parse(strDate);
//	prmOrdenBean1.setFechaOrden(dt);
	prmOrdenBean1.setFecha(strDate);
//	prmOrdenBean2.setFechaOrden(dt);
	prmOrdenBean2.setFecha(strDate);
//	prmOrdenBean3.setFechaOrden(dt);
	prmOrdenBean3.setFecha(strDate);
//	prmOrdenBeanVentaDiaria.setFechaOrden(dt);
	prmOrdenBeanVentaDiaria.setFecha(strDate);
	/**situacion 000001 pendiente 000002 atendido 000003 anulado ***/
	TablaBean tabla1 = new TablaBean();
	tabla1.setCodReg("000001");
	TablaBean tabla2 = new TablaBean();
	tabla2.setCodReg("000002");
	TablaBean tabla3 = new TablaBean();
	tabla3.setCodReg("000003");
	prmOrdenBean1.setSituacion(tabla1);
	prmOrdenBean2.setSituacion(tabla2);
	prmOrdenBean3.setSituacion(tabla3);
	prmOrdenBeanArea.setNroMes(strMes);
	prmOrdenBeanArea.setNumeroPeriodo(strAnio);
	prmOrdenBeanBarra.setNumeroPeriodo(strAnio);
	System.out.println("acaPortada");
	try { 

		uOrderBean = ordenService.reporteCantidadSemanalOrdenSituacion(prmOrdenBean1);

		uOrdenBean2 = ordenService.reporteCantidadSemanalOrdenSituacion(prmOrdenBean2);
	//	uOrdenBean3 = ordenService.reporteCantidadDiarioOrdenSituacion(prmOrdenBean3);
		uOrdenBean3 = ordenService.reporteCantidadSemanalOrdenSituacion(prmOrdenBean3);
	//	uOrdenBeanVentadiaria = ordenService.reporteVentaDiaria(prmOrdenBeanVentaDiaria);
		uOrdenBeanVentaSemanal = ordenService.reporteVentaSemanal(prmOrdenBeanVentaDiaria);	
		uOrdenBeanVentaSemanal.setSwSemanal(true);

				lstOrdenBeanArea = ordenService.reporteVentaMensualPeriodoArea(prmOrdenBeanArea);
				lstOrdenBeanBarra = ordenService.reporteVentaMensualPeriodoBarra(prmOrdenBeanBarra);
				lstOrdenBeanCircular = ordenService.reporteCantidadSemanalExamenesSolicitados(prmOrdenBeanVentaDiaria);
		
		System.out.println("lstOrdenBeanArea"+lstOrdenBeanArea.size());
		System.out.println("lstOrdenBeanCircular"+lstOrdenBeanCircular.size());
		if(uOrderBean!=null){   
			
			
		}else{   
		} 
} catch (Exception e) {  
}
	
	mav.addObject("uOrderBean", 		   uOrderBean);
	mav.addObject("uOrdenBean2", 		   uOrdenBean2);
	mav.addObject("uOrdenBean3", 		   uOrdenBean3);
	mav.addObject("uOrdenBeanVentadiaria", uOrdenBeanVentaSemanal);
	mav.addObject("lstOrdenBeanArea",	   lstOrdenBeanArea);
	mav.addObject("lstOrdenBeanBarra", 	   lstOrdenBeanBarra);
	mav.addObject("lstOrdenBeanCircular",  lstOrdenBeanCircular);
	mav.addObject("diaSemana", 	   		   diaSemana);
	mav.addObject("nombreVenta", 	   	   nombreVenta);
	
	return mav;	
	
	
};

@RequestMapping(value = "/portadaListarMensual", method = RequestMethod.GET)
public ModelAndView portadaListarMensual(HttpServletRequest request) throws Exception {
	
	ModelAndView mav =  new ModelAndView("portada", "command", new InicioForm());
	tipoReporte=3;
	 nombreVenta="VENTA DEL MES";
	
	/***mandamos datos del tablero de fecha actual por default a los reportes del mes**/
	obtenerFechaHoraDia();
	uOrderBean = new OrdenBean();
	uOrdenBeanVentaMensual = new OrdenBean();
	
	OrdenBean uOrdenBean2 = new OrdenBean();
	OrdenBean uOrdenBean3 = new OrdenBean();

	lstOrdenBeanArea = new ArrayList<OrdenBean>();
	lstOrdenBeanBarra = new ArrayList<OrdenBean>();
	lstOrdenBeanCircular = new ArrayList<OrdenBean>();
	OrdenBean prmOrdenBean1 = new OrdenBean();
	OrdenBean prmOrdenBean2 = new OrdenBean();
	OrdenBean prmOrdenBean3 = new OrdenBean();
	
	OrdenBean prmOrdenBeanArea  = new OrdenBean();
	OrdenBean prmOrdenBeanBarra  = new OrdenBean();
	
	OrdenBean prmOrdenBeanVentaMensual = new OrdenBean();
	Date date = new Date();
	SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat mes = new SimpleDateFormat("MM");
	SimpleDateFormat anio = new SimpleDateFormat("yyyy");
	mes.format(date);
	anio.format(date);
	fecha.format(date);
	String strDate = fecha.format(date);
	System.out.println("strDate"+strDate);
	String strMes = mes.format(date);
	System.out.println("strMes"+strMes);
	String strAnio = anio.format(date);
	System.out.println("strAnio"+strAnio);
	Date dt = fecha.parse(strDate);

	prmOrdenBean1.setNroMes(strMes);
	prmOrdenBean1.setNumeroPeriodo(strAnio);

	prmOrdenBean2.setNroMes(strMes);
	prmOrdenBean2.setNumeroPeriodo(strAnio);

	prmOrdenBean3.setNroMes(strMes);
	prmOrdenBean3.setNumeroPeriodo(strAnio);


	prmOrdenBeanVentaMensual.setNroMes(strMes);
	prmOrdenBeanVentaMensual.setNumeroPeriodo(strAnio);
	
	/**situacion 000001 pendiente 000002 atendido 000003 anulado ***/
	TablaBean tabla1 = new TablaBean();
	tabla1.setCodReg("000001");
	TablaBean tabla2 = new TablaBean();
	tabla2.setCodReg("000002");
	TablaBean tabla3 = new TablaBean();
	tabla3.setCodReg("000003");
	prmOrdenBean1.setSituacion(tabla1);
	prmOrdenBean2.setSituacion(tabla2);
	prmOrdenBean3.setSituacion(tabla3);
	prmOrdenBeanArea.setNroMes(strMes);
	prmOrdenBeanArea.setNumeroPeriodo(strAnio);
	prmOrdenBeanBarra.setNumeroPeriodo(strAnio);

	try { 

		uOrderBean = ordenService.reporteCantidadMensualOrdenSituacion(prmOrdenBean1);

		uOrdenBean2 = ordenService.reporteCantidadMensualOrdenSituacion(prmOrdenBean2);

		uOrdenBean3 = ordenService.reporteCantidadMensualOrdenSituacion(prmOrdenBean3);
	
		uOrdenBeanVentaMensual = ordenService.reporteVentaMensual(prmOrdenBeanVentaMensual);	
		uOrdenBeanVentaMensual.setSwMensual(true);

				lstOrdenBeanArea = ordenService.reporteVentaMensualPeriodoArea(prmOrdenBeanArea);
				lstOrdenBeanBarra = ordenService.reporteVentaMensualPeriodoBarra(prmOrdenBeanBarra);
				lstOrdenBeanCircular = ordenService.reporteCantidadMensualExamenesSolicitados(prmOrdenBeanVentaMensual);
		
				System.out.println("lstOrdenBeanArea"+lstOrdenBeanArea.size());
				System.out.println("lstOrdenBeanCircular"+lstOrdenBeanCircular.size());
		if(uOrderBean!=null){   
			
			
		}else{   
		} 
} catch (Exception e) {  
}
	
	mav.addObject("uOrderBean", 		   uOrderBean);
	mav.addObject("uOrdenBean2", 		   uOrdenBean2);
	mav.addObject("uOrdenBean3", 		   uOrdenBean3);
	mav.addObject("uOrdenBeanVentadiaria", uOrdenBeanVentaMensual);
	mav.addObject("lstOrdenBeanArea",	   lstOrdenBeanArea);
	mav.addObject("lstOrdenBeanBarra", 	   lstOrdenBeanBarra);
	mav.addObject("lstOrdenBeanCircular",  lstOrdenBeanCircular);
	mav.addObject("diaSemana", 	   		   diaSemana);
	mav.addObject("nombreVenta", 	   	   nombreVenta);
	
	return mav;	
	
	
};
	
@RequestMapping(value = "/portadaListarAnual", method = RequestMethod.GET)
public ModelAndView portadaListarAnual(HttpServletRequest request) throws Exception {
	
	ModelAndView mav =  new ModelAndView("portada", "command", new InicioForm());
	
	tipoReporte=4;
	 nombreVenta="VENTA DEL AÑO";
	/***mandamos datos del tablero de fecha actual por default a los reportes del año***/
	obtenerFechaHoraDia();
	uOrderBean = new OrdenBean();
	uOrdenBeanVentaAnual = new OrdenBean();
	
	OrdenBean uOrdenBean2 = new OrdenBean();
	OrdenBean uOrdenBean3 = new OrdenBean();
	
	OrdenBean uOrdenBeanArea  = new OrdenBean();
	OrdenBean uOrdenBeanBarra  = new OrdenBean();
	lstOrdenBeanArea = new ArrayList<OrdenBean>();
	lstOrdenBeanBarra = new ArrayList<OrdenBean>();
	lstOrdenBeanCircular = new ArrayList<OrdenBean>();
	
	OrdenBean prmOrdenBean1 = new OrdenBean();
	OrdenBean prmOrdenBean2 = new OrdenBean();
	OrdenBean prmOrdenBean3 = new OrdenBean();
	
	OrdenBean prmOrdenBeanArea  = new OrdenBean();
	OrdenBean prmOrdenBeanBarra  = new OrdenBean();
	
	OrdenBean prmOrdenBeanVentaAnual = new OrdenBean();
	Date date = new Date();
	SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat mes = new SimpleDateFormat("MM");
	SimpleDateFormat anio = new SimpleDateFormat("yyyy");
	mes.format(date);
	anio.format(date);
	fecha.format(date);
	String strDate = fecha.format(date);
	System.out.println("strDate"+strDate);
	String strMes = mes.format(date);
	System.out.println("strMes"+strMes);
	String strAnio = anio.format(date);
	System.out.println("strAnio"+strAnio);
	Date dt = fecha.parse(strDate);
	
	prmOrdenBean1.setNumeroPeriodo(strAnio);


	prmOrdenBean2.setNumeroPeriodo(strAnio);


	prmOrdenBean3.setNumeroPeriodo(strAnio);



	prmOrdenBeanVentaAnual.setNumeroPeriodo(strAnio);

	/**situacion 000001 pendiente 000002 atendido 000003 anulado ***/
	TablaBean tabla1 = new TablaBean();
	tabla1.setCodReg("000001");
	TablaBean tabla2 = new TablaBean();
	tabla2.setCodReg("000002");
	TablaBean tabla3 = new TablaBean();
	tabla3.setCodReg("000003");
	prmOrdenBean1.setSituacion(tabla1);
	prmOrdenBean2.setSituacion(tabla2);
	prmOrdenBean3.setSituacion(tabla3);
	prmOrdenBeanArea.setNroMes(strMes);
	prmOrdenBeanArea.setNumeroPeriodo(strAnio);
	prmOrdenBeanBarra.setNumeroPeriodo(strAnio);
	System.out.println("acaPortada");
	try { 

		uOrderBean = ordenService.reporteCantidadAnualOrdenSituacion(prmOrdenBean1);

		uOrdenBean2 = ordenService.reporteCantidadAnualOrdenSituacion(prmOrdenBean2);

		uOrdenBean3 = ordenService.reporteCantidadAnualOrdenSituacion(prmOrdenBean3);

		uOrdenBeanVentaAnual = ordenService.reporteVentaAnual(prmOrdenBeanVentaAnual);	
		uOrdenBeanVentaAnual.setSwAnual(true);

				lstOrdenBeanArea = ordenService.reporteVentaMensualPeriodoArea(prmOrdenBeanArea);
				lstOrdenBeanBarra = ordenService.reporteVentaMensualPeriodoBarra(prmOrdenBeanBarra);
				lstOrdenBeanCircular = ordenService.reporteCantidadAnualExamenesSolicitados(prmOrdenBeanVentaAnual);
		
				System.out.println("lstOrdenBeanArea"+lstOrdenBeanArea.size());
				System.out.println("lstOrdenBeanCircular"+lstOrdenBeanCircular.size());
		if(uOrderBean!=null){   
			
			
		}else{   
		} 
} catch (Exception e) {  
}
	
	mav.addObject("uOrderBean", 		   uOrderBean);
	mav.addObject("uOrdenBean2", 		   uOrdenBean2);
	mav.addObject("uOrdenBean3", 		   uOrdenBean3);
	mav.addObject("uOrdenBeanVentadiaria",  uOrdenBeanVentaAnual);
	mav.addObject("lstOrdenBeanArea",	   lstOrdenBeanArea);
	mav.addObject("lstOrdenBeanBarra", 	   lstOrdenBeanBarra);
	mav.addObject("lstOrdenBeanCircular",  lstOrdenBeanCircular);
	mav.addObject("diaSemana", 	   		   diaSemana);
	mav.addObject("nombreVenta", 	   	   nombreVenta);
	mav.addObject("swAnual","1");
	return mav;	
	
	
}; 	
}