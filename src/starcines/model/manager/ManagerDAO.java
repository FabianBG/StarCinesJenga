package starcines.model.manager;

import java.util.List;




import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class ManagerDAO {

	private static EntityManagerFactory factory;
	private static EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerDAO() {
		if (factory == null)
			factory = Persistence.createEntityManagerFactory("StarCinesApp");
		if (em == null)
			em = factory.createEntityManager();
	}

	/**
	 * finder Generico que devuelve todas las entidades de una tabla.
	 * 
	 * @param clase
	 *            La clase que se desea consultar. Por ejemplo:
	 *            <ul>
	 *            <li>Usuario.class</li>
	 *            </ul>
	 * @param orderBy
	 *            Expresion que indica la propiedad de la entidad por la que se
	 *            desea ordenar la consulta. Debe utilizar el alias "o" para
	 *            nombrar a la(s) propiedad(es) por la que se va a ordenar. por
	 *            ejemplo:
	 *            <ul>
	 *            <li>o.nombre</li>
	 *            <li>o.codigo,o.nombre</li>
	 *            </ul>
	 *            Puede aceptar null o una cadena vacia, en este caso no
	 *            ordenara el resultado.
	 * @return Listado resultante.
	 */
	@SuppressWarnings("rawtypes")
	public List findAll(Class clase, String orderBy) {
		Query q;
		List listado;
		String sentenciaSQL;
		if (orderBy == null || orderBy.length() == 0)
			sentenciaSQL = "SELECT o FROM " + clase.getSimpleName() + " o";
		else
			sentenciaSQL = "SELECT o FROM " + clase.getSimpleName()
					+ " o ORDER BY " + orderBy;
		q = em.createQuery(sentenciaSQL);
		listado = q.getResultList();
		return listado;
	}
	
	/**
	 * Finder generico para buscar un objeto especifico.
	 * 
	 * @param clase
	 *            La clase sobre la que se desea consultar.
	 * @param pID
	 *            Identificador que permitira la busqueda.
	 * @return El objeto solicitado (si existiera).
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object findById(Class clase, Object pID) throws Exception {
		if (pID == null)
			throw new Exception(
					"Debe especificar el codigo para buscar el dato.");
		Object o;
		try {
			if(!em.getTransaction().isActive())
				em.getTransaction().begin();
			o = em.find(clase, pID);
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception("No se encontro la informacion especificada: "
					+ e.getMessage());
		}
		return o;
	}

	/**
	 * Finder generico para buscar un objeto especifico por una columna especificada.
	 * 
	 * @param clase
	 *            La clase sobre la que se desea consultar.
	 * @param param
	 *            columna de busqueda.
	 * @param value
	 *            valor de parametro de busqueda.
	 *@param orderBy
	 *            Expresion que indica la propiedad de la entidad por la que se
	 *            desea ordenar la consulta. Debe utilizar el alias "o" para
	 *            nombrar a la(s) propiedad(es) por la que se va a ordenar.
	 *            Puede aceptar null o una cadena vacia, en este caso no
	 *            ordenara el resultado.
	 * @return Lista de objetos solicitados (si existieran).
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	public List findByParam(Class clase, String param, Object value, String orderBy) throws Exception {
		Query q;
		List listado;
		String sentenciaSQL;
		if(!em.getTransaction().isActive())
			em.getTransaction().begin();
		if (orderBy == null || orderBy.length() == 0)
			sentenciaSQL = "SELECT o FROM " + clase.getSimpleName() + " o WHERE " + param + "=:value1";
		else
			sentenciaSQL = "SELECT o FROM " + clase.getSimpleName()
					+ " o WHERE " + param + "=:value1" + " ORDER BY " + orderBy;
		q = em.createQuery(sentenciaSQL).setParameter("value1", value);
		listado = q.getResultList();
		return listado;
	}
	/**
	 * Almacena un objeto en la persistencia.
	 * 
	 * @param pObjeto
	 *            El objeto a insertar.
	 * @throws Exception
	 */
	public void insertar(Object pObjeto) throws Exception {
		if (!em.getTransaction().isActive()){
			em.getTransaction().begin();
		}
		try {
			em.persist(pObjeto);
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception("No se pudo insertar el objeto especificado: "
					+ e.getMessage());
		}
		em.getTransaction().commit();
	}

	/**
	 * Elimina un objeto de la persistencia.
	 * 
	 * @param clase
	 *            La clase correspondiente al objeto que se desea eliminar.
	 * @param pID
	 *            El identificador del objeto que se desea eliminar.
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void eliminar(Class clase, Object pID) throws Exception {
		if (pID == null) {
			throw new Exception(
					"Debe especificar un identificador para eliminar el dato solicitado.");
		}
		Object o = findById(clase, pID);
		try {
			if(!em.getTransaction().isActive())
				em.getTransaction().begin();
			em.remove(o);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception("No se pudo eliminar el dato: "
					+ e.getMessage());
		}
	}

	/**
	 * Actualiza la informacion de un objeto en la persistencia.
	 * 
	 * @param pObjeto
	 *            Objeto que contiene la informacion que se debe actualizar.
	 * @throws Exception
	 */
	public void actualizar(Object pObjeto) throws Exception {
		if (pObjeto == null)
			throw new Exception("No se puede actualizar un dato null");
		try {
			if(!em.getTransaction().isActive())
				em.getTransaction().begin();
			em.merge(pObjeto);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			throw new Exception("No se pudo actualizar el dato: "
					+ e.getMessage());
			
		}
	}
}