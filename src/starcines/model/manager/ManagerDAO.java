package starcines.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import starcines.model.entities.Cartelera;
import starcines.model.entities.Genero;
import starcines.model.entities.Usuario;

public class ManagerDAO {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerDAO() {
		if (emf == null)
			emf = Persistence.createEntityManagerFactory("StarCines");
		if (em == null)
			em = emf.createEntityManager();
	}

	// Usuarios

	/**
	 * finder para la busqueda de todos los usuarios dentro de la base de datos.
	 * 
	 * @return Listado resultante de usuarios encontrados.
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> findAllUsuarios() {
		List<Usuario> listado;
		Query q;
		em.getTransaction().begin();
		q = em.createQuery("SELECT u FROM Usuario u ORDER BY u.usu_nick");
		listado = q.getResultList();
		em.getTransaction().commit();
		return listado;

	}

	// metodo ingresar Usuario
	public void crearUsuario(String usu, String pass) {
		em.getTransaction().begin();
		Usuario u = new Usuario();
		u.setUsuNick(usu);
		u.setUsuPass(pass);
		em.persist(u);
		em.getTransaction().commit();

	}

	// metodo para buscar un usuario por id
	public Usuario findByIdUsuario(String idUsuario) {
		em.getTransaction().begin();
		Usuario u = em.find(Usuario.class, idUsuario);
		em.getTransaction().commit();
		return u;
	}

	// metodo para actualizar un Usuario:
	public void actualizarUsuario(String usu, String pass) {
		// buscamos el objeto que debe ser actualizado:
		Usuario u = findByIdUsuario(usu);
		em.getTransaction().begin();
		// no se actualiza la clave primaria, en este caso solo la descripcion
		u.setUsuNick(usu);
		u.setUsuPass(pass);
		em.merge(u);
		em.getTransaction().commit();
	}

	// metodo para buscar por nombre
	public Usuario findByNombre(String nombre) {
		List<Usuario> listado;
		Usuario u = null;
		listado = findAllUsuarios();
		em.getTransaction().begin();
		for (Usuario us : listado) {
			if (us.getUsuNick().equals(nombre)) {
				u = us;
			}
		}
		em.getTransaction().commit();
		return u;
	}

	// Generos

	// Listar Todos los generos
	@SuppressWarnings("unchecked")
	public List<Genero> findAllGeneros() {
		List<Genero> listado;
		Query q;
		em.getTransaction().begin();
		q = em.createQuery("SELECT u FROM Genero u ORDER BY u.gen_id");
		listado = q.getResultList();
		em.getTransaction().commit();
		return listado;

	}

	// metodo ingresar genero
	public void crearGenero(Integer id, String tipo) {
		em.getTransaction().begin();
		Genero g = new Genero();
		g.setGenId(id);
		g.setGenTipo(tipo);
		em.persist(g);
		em.getTransaction().commit();

	}

	// metodo para buscar un genero por id
	public Genero findByIdGenero(Integer id) {
		em.getTransaction().begin();
		Genero g = em.find(Genero.class, id);
		em.getTransaction().commit();
		return g;
	}

	// metodo para actualizar un genero:
	public void actualizarGenero(Integer id, String tipo) {
		// buscamos el objeto que debe ser actualizado:
		Genero g = findByIdGenero(id);
		em.getTransaction().begin();
		// no se actualiza la clave primaria, en este caso solo la descripcion
		g.setGenId(id);
		g.setGenTipo(tipo);
		em.merge(g);
		em.getTransaction().commit();
	}

	// metodo para buscar por nombre
	public Genero findByTipo(String tipo) {
		List<Genero> listado;
		Genero u = null;
		listado = findAllGeneros();
		em.getTransaction().begin();
		for (Genero us : listado) {
			if (us.getGenTipo().equals(tipo)) {
				u = us;
			}
		}
		em.getTransaction().commit();
		return u;
	}

	// METODOS PARA EL SERVICIO MOBIL
	/**
	 * finder de todos los datos de la base de datos para el servicio mobil. HAce uso de la API de 
	 * Google GSON para transformar los objetos a JSON.
	 * 
	 * @param orderBy
	 *            Expresion que indica la propiedad de la entidad por la que se
	 *            desea ordenar la consulta. Debe utilizar el alias "o" para
	 *            nombrar a la(s) propiedad(es) por la que se va a ordenar. 
	 *            Puede aceptar null o una cadena vacia, en este caso no
	 *            ordenara el resultado.
	 * @return Cadena JSON con los resultadoa obtenidos.
	 */
	
	public String getFullData() throws Exception
	{
		
		//Obtener datos de la BD.
		Query q;
		if (!em.getTransaction().isActive())
			em.getTransaction().begin();
		q = em.createQuery("SELECT p FROM "+Cartelera.class.getSimpleName()+" p");
		if (em.getTransaction().isActive())
			em.getTransaction().commit();
		List<Cartelera> lst = (List<Cartelera>) q.getResultList();
		
		//API de google para generar JSON a partir de objetos java
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String JSON = gson.toJson(lst);
        System.out.println(JSON);
        return JSON;
	}

}