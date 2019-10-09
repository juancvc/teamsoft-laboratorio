package laboratorio.core.bean.asistencial.laboratorio;
 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import laboratorio.base.bean.BaseBean;
import laboratorio.core.bean.general.PacienteBean;
import laboratorio.core.bean.general.TablaBean;

public class OrdenBean extends BaseBean{
	
	private PacienteBean pacienteBean;
	private Date fechaOrden;
	private String horaOrden;
	private String nroOrden;  
	private String strFechaOrden;  
	private String observacion; 
	private int porcentajeDescuento;
	private Float subTotal;
	private Float importeTotal;
	private Float descuentoTotal;
	
	private String sImporteTotal;
	private String descripcionImporte;
	
	private List<OrdenDetalleItemBean> lstOrdenDetalleItemBean;
	private List<OrdenDetalleBean> lstOrdenDetalleBean;
	
	private int cantidadItems;  
	private String cadenaCodigoTarifario;  
	private String cadenaCantidad;  
	
	private String fechaDesde;  
	private String fechaHasta;  
	private TablaBean motivoAnular;
	private String detalleMotivoAnular; 
	
	/***reportes**/
	private String cantidadOrdenes;
	private String cantidadVentas;
	private String cantidadExamenesSolicitados;
	private String nroMes;
	private String nombreMes;
	private String nombreExamen;
	private String fecha;
 
	private Boolean swDiario;
	private Boolean swMensual;
	private Boolean swSemanal;
	private Boolean swAnual;
	
	
	
	public int getPorcentajeDescuento() { 
		return porcentajeDescuento;
	}
	public void setPorcentajeDescuento(int porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	public TablaBean getMotivoAnular() {
		if (motivoAnular == null) {
			motivoAnular = new TablaBean();
		}
		return motivoAnular;
	}
	public void setMotivoAnular(TablaBean motivoAnular) {
		this.motivoAnular = motivoAnular;
	}
	public String getDetalleMotivoAnular() {
		return detalleMotivoAnular;
	}
	public void setDetalleMotivoAnular(String detalleMotivoAnular) {
		this.detalleMotivoAnular = detalleMotivoAnular;
	}
	public String getNroOrden() {
		return nroOrden;
	}
	public void setNroOrden(String nroOrden) {
		this.nroOrden = nroOrden;
	}
	public String getFechaDesde() {
		if (fechaDesde == null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			String strHora = sdf.format(new Date());
			fechaDesde = strHora;
		} 
		return fechaDesde; 
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		if (fechaHasta == null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			String strHora = sdf.format(new Date());
			fechaHasta = strHora;
		} 
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public int getCantidadItems() {
		return cantidadItems;
	}
	public void setCantidadItems(int cantidadItems) {
		this.cantidadItems = cantidadItems;
	}
	public String getCadenaCodigoTarifario() {
		return cadenaCodigoTarifario;
	}
	public void setCadenaCodigoTarifario(String cadenaCodigoTarifario) {
		this.cadenaCodigoTarifario = cadenaCodigoTarifario;
	}
	public String getCadenaCantidad() {
		return cadenaCantidad;
	}
	public void setCadenaCantidad(String cadenaCantidad) {
		this.cadenaCantidad = cadenaCantidad;
	}
	public String getsImporteTotal() {
		return sImporteTotal;
	}
	public void setsImporteTotal(String sImporteTotal) {
		this.sImporteTotal = sImporteTotal;
	}
	public List<OrdenDetalleBean> getLstOrdenDetalleBean() {
		return lstOrdenDetalleBean;
	}
	public void setLstOrdenDetalleBean(List<OrdenDetalleBean> lstOrdenDetalleBean) {
		this.lstOrdenDetalleBean = lstOrdenDetalleBean;
	}
	public PacienteBean getPacienteBean() {
		if (pacienteBean == null ) {
			pacienteBean = new PacienteBean();
		}
		return pacienteBean;
	}
	public void setPacienteBean(PacienteBean pacienteBean) {
		this.pacienteBean = pacienteBean;
	}
	public Date getFechaOrden() { 
		return fechaOrden;
	}
	public void setFechaOrden(Date fechaOrden) {
		this.fechaOrden = fechaOrden;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Float getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(Float importeTotal) {
		this.importeTotal = importeTotal;
	}
	public String getDescripcionImporte() {
		return descripcionImporte;
	}
	public void setDescripcionImporte(String descripcionImporte) {
		this.descripcionImporte = descripcionImporte;
	}  
	
	public void calcularImporteTotal(){
		Float tmpImporte = (float) 0.00;
		for (OrdenDetalleBean ordenDetalleBean : lstOrdenDetalleBean) {
			tmpImporte += ordenDetalleBean.getImporte();
		}
		System.out.println("tmpImporte " + tmpImporte);
		this.setImporteTotal(tmpImporte);
	}
	public String getStrFechaOrden() {
		return strFechaOrden;
	}
	public void setStrFechaOrden(String strFechaOrden) {
		this.strFechaOrden = strFechaOrden;
	}
	public String getHoraOrden() {
		return horaOrden;
	}
	public void setHoraOrden(String horaOrden) {
		this.horaOrden = horaOrden;
	}
	public String getCantidadOrdenes() {
		return cantidadOrdenes;
	}
	public void setCantidadOrdenes(String cantidadOrdenes) {
		this.cantidadOrdenes = cantidadOrdenes;
	}
	public String getCantidadVentas() {
		return cantidadVentas;
	}
	public void setCantidadVentas(String cantidadVentas) {
		this.cantidadVentas = cantidadVentas;
	}
	public String getNombreExamen() {
		return nombreExamen;
	}
	public void setNombreExamen(String nombreExamen) {
		this.nombreExamen = nombreExamen;
	}
	public String getNroMes() {
		return nroMes;
	}
	public void setNroMes(String nroMes) {
		this.nroMes = nroMes;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Boolean getSwDiario() {
		return swDiario;
	}
	public void setSwDiario(Boolean swDiario) {
		this.swDiario = swDiario;
	}
	public Boolean getSwMensual() {
		return swMensual;
	}
	public void setSwMensual(Boolean swMensual) {
		this.swMensual = swMensual;
	}
	public Boolean getSwSemanal() {
		return swSemanal;
	}
	public void setSwSemanal(Boolean swSemanal) {
		this.swSemanal = swSemanal;
	}
	public Boolean getSwAnual() {
		return swAnual;
	}
	public void setSwAnual(Boolean swAnual) {
		this.swAnual = swAnual;
	}
	public String getNombreMes() {
		return nombreMes;
	}
	public void setNombreMes(String nombreMes) {
		this.nombreMes = nombreMes;
	}
	public String getCantidadExamenesSolicitados() {
		return cantidadExamenesSolicitados;
	}
	public void setCantidadExamenesSolicitados(String cantidadExamenesSolicitados) {
		this.cantidadExamenesSolicitados = cantidadExamenesSolicitados;
	}
	public Float getDescuentoTotal() {
		return descuentoTotal;
	}
	public void setDescuentoTotal(Float descuentoTotal) {
		this.descuentoTotal = descuentoTotal;
	}
	public List<OrdenDetalleItemBean> getLstOrdenDetalleItemBean() {
		return lstOrdenDetalleItemBean;
	}
	public void setLstOrdenDetalleItemBean(List<OrdenDetalleItemBean> lstOrdenDetalleItemBean) {
		this.lstOrdenDetalleItemBean = lstOrdenDetalleItemBean;
	}
	public Float getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Float subTotal) {
		this.subTotal = subTotal;
	}

 
}
