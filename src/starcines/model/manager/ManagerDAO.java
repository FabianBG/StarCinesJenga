package starcines.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import starcines.model.entities.Usuario;



public class ManagerDAO {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerDAO() {
		if(emf==null)
			emf=Persistence.createEntityManagerFactory("StarCines");
		if(em==null)
			em=emf.createEntityManager();
	}

	//USUARIOS

		//Listar Todos los Usuarios
		@SuppressWarnings("unchecked")
		public List<Usuario> findAllUsuarios(){
		 List<Usuario> listado;
		 Query q;
		 em.getTransaction().begin();
		 q=em.createQuery("SELECT u FROM Usuario u ORDER BY u.idUsuario");
		 listado= q.getResultList();
		 em.getTransaction().commit();
		 return listado;
		 
		}
		
		//metodo ingresar Usuario
			 public void crearUsuario (String usu,String pass){
				 em.getTransaction().begin();
				 Usuario u = new Usuario();
				 u.setUsuNick(usu);
				 u.setUsuPass(pass);
				 em.persist(u);
				 em.getTransaction().commit();
				 
			 }
			 
		//metodo para buscar un usuario por id
			 public Usuario findByIdUsuario(String idUsuario){
				 em.getTransaction().begin();
				 Usuario u =em.find(Usuario.class, idUsuario);
				 em.getTransaction().commit();
				 return u;
			 }

		//metodo para actualizar un Usuario:
			 public void actualizarUsuario(String usu,String pass){
				 //buscamos el objeto que debe ser actualizado:
				 Usuario u = findByIdUsuario(usu);
				 em.getTransaction().begin();
				 // no se actualiza la clave primaria, en este caso solo la descripcion
				 u.setUsuNick(usu);
				 u.setUsuPass(pass);
				 em.merge(u);
				 em.getTransaction().commit();
			 }
			 
		
		//metodo para buscar por nombre
			 public Usuario findByNombre(String nombre){
					List<Usuario> listado;
					Usuario u=null;
					listado =findAllUsuarios();
					em.getTransaction().begin();
					for (Usuario us:listado){
						if (us.getUsuNick().equals(nombre)){
							u=us;
						}
					}
					em.getTransaction().commit();
					return u;
				}
			 
}