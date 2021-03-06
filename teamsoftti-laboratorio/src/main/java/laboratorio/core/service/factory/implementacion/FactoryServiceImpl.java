//package pe.gob.procalidad.natigu.core.service.service.factory.implementacion;
package laboratorio.core.service.factory.implementacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import laboratorio.core.service.factory.interfaces.FactoryService;
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
 
 

@Service("factoryService")
public class FactoryServiceImpl implements FactoryService {
	/** ADMINISTRATICO **/
	/** ASISTENCIAL **/
	
	/** ACADEMICO **/
	 
	
	/** GENERAL **/
	
	 
	@Autowired
	private Maestra1Service maestra1Service;
	@Autowired
	private Maestra2Service maestra2Service;
	@Autowired
	private PersonalService personalService;
	@Autowired
	private PersonaService personaService;
	@Autowired 
	private UbigeoService ubigeoService;
	 

	/** SEGURIDAD **/
	
	@Autowired
	private AccesoService accesoService;
	@Autowired
	private PerfilService perfilService;
	@Autowired
	private UsuarioPerfilService usuarioPerfilService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private AuditoriaService auditoriaService;
	
	 
	 
	@Override
	public Maestra1Service getMaestra1Service() {
		return maestra1Service;
	}
	@Override
	public Maestra2Service getMaestra2Service() {
		return maestra2Service;
	} 
	@Override
	public PersonalService getPersonalService() {
		return personalService;
	}
	 
	@Override
	public PersonaService getPersonaService() {
		return personaService;
	} 
	@Override
	public UbigeoService getUbigeoService() {
		return ubigeoService;
	} 
	
	
	
	@Override
	public AccesoService getAccesoService() {
		return accesoService;
	}
	@Override
	public PerfilService getPerfilService() {
		return perfilService;
	}
	@Override
	public UsuarioPerfilService getUsuarioPerfilService() {
		return usuarioPerfilService;
	}
	@Override
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}
	/*
	@Override
	public AuditoriaService getAuditoriaService() {
		// TODO Auto-generated method stub
		return auditoriaService;
	}*/
	
	 
	@Override
	public AuditoriaService getAuditoriaService() {
		return auditoriaService;
	}
	 

}