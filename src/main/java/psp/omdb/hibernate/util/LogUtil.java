package psp.omdb.hibernate.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Utilidad de Logging</h1>
 *
 * Centraliza la creación de loggers para evitar duplicación
 * y desacoplar las clases de la implementación concreta de logging.
 *
 * Permite cambiar la estrategia de logging sin tocar
 * el resto del código de la aplicación.
 *
 * @author Jaime Pérez Roget Blanco
 * @since 27/01/2026
 */
public final class LogUtil {

    private LogUtil() {
        // Evita instanciación
    }

    /**
     * Devuelve un logger asociado a la clase indicada.
     *
     * @param clase clase que solicita el logger
     * @return Logger SLF4J
     */
    public static Logger getLogger(Class<?> clase) {
        return LoggerFactory.getLogger(clase);
    }

}
