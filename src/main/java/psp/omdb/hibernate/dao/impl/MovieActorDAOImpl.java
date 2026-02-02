package psp.omdb.hibernate.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;

import psp.omdb.hibernate.dao.MovieActorDAO;
import psp.omdb.hibernate.entity.MovieActor;
import psp.omdb.hibernate.util.HibernateUtil;
import psp.omdb.hibernate.util.LogUtil;

/**
 * <h1>DAO Hibernate de MovieActor</h1>
 *
 * Gestiona la tabla intermedia entre {@link Movie} y {@link Actor},
 * permitiendo consultas y persistencia de relaciones N:M.
 *
 * @author Jaime Pérez Roget Blanco
 * @since 27/01/2026
 */
public class MovieActorDAOImpl implements MovieActorDAO {

    private static final Logger logger =
            LogUtil.getLogger(MovieActorDAOImpl.class);

    /**
     * Guarda una relación Movie-Actor.
     *
     * @param movieActor relación MovieActor
     */
    @Override
    public void save(MovieActor movieActor) {

        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            session.persist(movieActor);
            tx.commit();

            logger.info("Relación Movie-Actor guardada");

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }

            logger.error("Error al guardar MovieActor", e);
            throw e;
        }
    }

    /**
     * Recupera todas las relaciones MovieActor.
     *
     * @return lista de relaciones MovieActor
     */
    @Override
    public List<MovieActor> findAll() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            logger.debug("Recuperando todas las relaciones MovieActor");

            return session.createQuery(
                    "FROM MovieActor",
                    MovieActor.class
            ).getResultList();
        }
    }

    /**
     * Consulta las películas con sus actores asociados
     * utilizando JOIN en HQL.
     *
     * @return lista de resultados Object[] (título, actor)
     */
    @Override
    public List<Object[]> findMoviesWithActors() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            logger.debug("Ejecutando consulta JOIN Movie-Actor");

            return session.createQuery(
                """
                select m.title, a.name
                from MovieActor ma
                join ma.movie m
                join ma.actor a
                order by m.title, a.name
                """,
                Object[].class
            ).getResultList();
        }
    }
}
