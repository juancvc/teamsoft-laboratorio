//package pe.gob.procalidad.natigu.core.repository.repository.interfaces;
package laboratorio.core.repository.interfaces;
import java.util.List;

import laboratorio.core.repository.DAOException;



public interface BaseDAO<T> {

	public boolean insertar(T t) throws DAOException;

	public boolean actualizar(T t) throws DAOException;
	
	public boolean eliminar(T t) throws DAOException;
	
	public T getBuscarPorObjecto(T t) throws DAOException;

	public List<T> getBuscarPorFiltros(T t)throws DAOException;

	public boolean existe(T t) throws DAOException;
	
	//public boolean actualizarOrden(T t) throws DAOException;

}
