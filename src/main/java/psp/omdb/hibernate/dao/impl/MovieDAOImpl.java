package psp.omdb.hibernate.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;

import psp.omdb.hibernate.dao.MovieDAO;
import psp.omdb.hibernate.entity.Movie;
import psp.omdb.hibernate.util.HibernateUtil;
import psp.omdb.hibernate.util.LogUtil;

/**
 * <h1>DAO Hibernate de Movie</h1>
 *
 * Gestiona la persistencia de la entidad {@link Movie}
 * mediante Hibernate.
 *
 * @author Jaime Pérez Roget Blanco
 * @since 27/01/2026
 */
public class MovieDAOImpl implements MovieDAO {

    private static final Logger logger =
            LogUtil.getLogger(MovieDAOImpl.class);

    /**
     * Guarda o actualiza una película.
     *
     * @param movie entidad Movie
     */
    @Override
    public void save(Movie movie) {

        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            session.merge(movie);
            tx.commit();

            logger.info("Película guardada: {}", movie.getTitle());

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }

            logger.error("Error al guardar película", e);
            throw e;
        }
    }

    /**
     * Busca una película por su identificador IMDb.
     *
     * @param imdbId identificador IMDb
     * @return Movie encontrada o null
     */
    @Override
    public Movie findByImdbId(String imdbId) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            logger.debug("Buscando película imdbId={}", imdbId);
            return session.get(Movie.class, imdbId);
        }
    }

    /**
     * Recupera todas las películas almacenadas.
     *
     * @return lista de películas
     */
    @Override
    public List<Movie> findAll() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            logger.debug("Recuperando todas las películas");

            return session.createQuery(
                    "FROM Movie",
                    Movie.class
            ).getResultList();
        }
    }
}
