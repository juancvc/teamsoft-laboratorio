//package pe.gob.procalidad.natigu.core.service.service.factory.interfaces;
package laboratorio.core.service.factory.interfaces;
 
import laboratorio.core.service.interfaces.general.Maestra1Service;
import laboratorio.core.service.interfaces.general.Maestra2Service;
import laboratorio.core.service.interfaces.general.PersonaService;
import laboratorio.core.service.interfaces.general.PersonalService;
import laboratorio.core.service.interfaces.general.UbigeoService;
import laboratorio.core.service.interfaces.seguridad.AccesoService;
import laboratorio.core.service.interfaces.seguridad.AuditoriaService;
import laboratorio.core.service.interfaces.seguridad.PerfilService;
import laboratorio.core.service.interfaces.seguridad.UsuarioPerfilService;
import laboratorio.core.service.interfaces.seguridad.UsuarioService;




public interface FactoryService {
	/** ADMINISTRATIVO **/
	/** ASISTENCIAL **/ 

 

	/** GENERAL **/
	
 	public Maestra1Service getMaestra1Service();
	public Maestra2Service getMaestra2Service();
 	public PersonalService getPersonalService();
 	public PersonaService getPersonaService();
 	public UbigeoService getUbigeoService(); 
	

	/** SEGURIDAD **/
	public AccesoService getAccesoService();
	public PerfilService getPerfilService();
	public UsuarioPerfilService getUsuarioPerfilService();
	public UsuarioService getUsuarioService();
	public AuditoriaService getAuditoriaService(); 

}