
package laboratorio.web.controller.inicio.vista;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import laboratorio.core.bean.asistencial.laboratorio.OrdenBean;
import laboratorio.core.bean.seguridad.AuditoriaAccesoBean;
import laboratorio.core.bean.seguridad.AuditoriaBean;


public class InicioForm {
	
	private OrdenBean ordenBean = new OrdenBean();
	private String sFecha;
	private String periodo;
	private int nroSemana;
	
	public OrdenBean getOrdenBean() {
		return ordenBean;
	}

	public void setOrdenBean(OrdenBean ordenBean) {
		this.ordenBean = ordenBean;
	}

	public String getsFecha() {
		if (sFecha == null) {
			Date fecha  = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sFecha = sdf.format(fecha);
			
			
		}
		return sFecha;
	}

	public void setsFecha(String sFecha) {
		this.sFecha = sFecha;
	}

	public String getPeriodo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date fecha  = new Date();
		if (periodo == null) {
			periodo = sdf.format(fecha);
		}
		return periodo;
	}

	public void setPeriodo(String periodo) { 
		this.periodo = periodo;
	}

	public int getNroSemana() {
		if (nroSemana == 0) {
			Calendar calendar = Calendar.getInstance();
			calendar.setFirstDayOfWeek( Calendar.MONDAY);
			calendar.setMinimalDaysInFirstWeek( 4 );
			calendar.setTime(new Date());
			nroSemana = calendar.get(Calendar.WEEK_OF_YEAR) - 1;
		}
		return nroSemana;
	}

	public void setNroSemana(int nroSemana) {
		this.nroSemana = nroSemana;
	} 

 

}
