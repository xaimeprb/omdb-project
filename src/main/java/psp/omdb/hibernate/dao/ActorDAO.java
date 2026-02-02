package psp.omdb.hibernate.dao;

import java.util.List;
import psp.omdb.hibernate.entity.Actor;

/**
 * <h1>DAO de Actor</h1>
 *
 * Define las operaciones de acceso a datos para la entidad {@link Actor}.
 * <p>
 * Esta interfaz abstrae la lógica de persistencia, permitiendo desacoplar
 * la capa de negocio de la implementación concreta de Hibernate.
 * </p>
 *
 * @author Jaime Pérez Roget Blanco
 * @since 27/01/2026
 */
public interface ActorDAO {

    /**
     * Guarda o actualiza un actor en la base de datos.
     *
     * @param actor entidad Actor a persistir
     */
    void save(Actor actor);

    /**
     * Busca un actor por su identificador.
     *
     * @param id identificador del actor
     * @return actor encontrado o null si no existe
     */
    Actor findById(int id);

    /**
     * Busca un actor por su nombre.
     *
     * @param name nombre del actor
     * @return actor encontrado o null si no existe
     */
    Actor findByName(String name);

    /**
     * Recupera todos los actores almacenados.
     *
     * @return lista de actores
     */
    List<Actor> findAll();

}
