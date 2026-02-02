package psp.omdb.hibernate.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;

import psp.omdb.hibernate.dao.ActorDAO;
import psp.omdb.hibernate.entity.Actor;
import psp.omdb.hibernate.util.HibernateUtil;
import psp.omdb.hibernate.util.LogUtil;

/**
 * <h1>DAO Hibernate de Actor</h1>
 *
 * Implementación de {@link ActorDAO} utilizando Hibernate como
 * proveedor de persistencia.
 *
 * <p>
 * Esta clase encapsula toda la lógica de acceso a datos relacionada
 * con la entidad {@link Actor}, gestionando sesiones, transacciones
 * y logging de forma centralizada.
 * </p>
 *
 * @author Jaime Pérez Roget Blanco
 * @since 27/01/2026
 */
public class ActorDAOImpl implements ActorDAO {

    private static final Logger logger =
            LogUtil.getLogger(ActorDAOImpl.class);

    /**
     * Guarda o actualiza un actor en la base de datos.
     *
     * @param actor entidad Actor a persistir
     */
    @Override
    public void save(Actor actor) {

        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            session.merge(actor);
            tx.commit();

            logger.info("Actor guardado: {}", actor.getName());

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }

            logger.error("Error al guardar actor", e);
            throw e;
        }
    }

    /**
     * Busca un actor por su identificador.
     *
     * @param id identificador del actor
     * @return Actor encontrado o null si no existe
     */
    @Override
    public Actor findById(int id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            logger.debug("Buscando actor por ID: {}", id);
            return session.get(Actor.class, id);
        }
    }

    /**
     * Busca un actor por su nombre.
     *
     * @param name nombre del actor
     * @return Actor encontrado o null si no existe
     */
    @Override
    public Actor findByName(String name) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            logger.debug("Buscando actor por nombre: {}", name);

            return session.createQuery(
                    "FROM Actor a WHERE a.name = :name",
                    Actor.class
            )
            .setParameter("name", name)
            .uniqueResult();
        }
    }

    /**
     * Recupera todos los actores almacenados en la base de datos.
     *
     * @return lista de actores
     */
    @Override
    public List<Actor> findAll() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            logger.debug("Recuperando todos los actores");

            return session.createQuery(
                    "FROM Actor",
                    Actor.class
            ).getResultList();
        }
    }
}
