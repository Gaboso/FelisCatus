package br.edu.ifms.lp3.util;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Classe com utilidades de reflexão de classes e métodos.
 *
 * @author Sidney Sousa
 */
public class ReflectionUtil {

    /**
     * Retorna a classe genérica apontada pelo índice <code>index</code> do
     * objeto <code>obj</code>.
     *
     * @param obj   - Objeto o qual se quer obter sua classe genérica.
     * @param index - Índice da classe genérica do objeto.
     * @return A classe genérica do objeto no índice indicado.
     */
    public static Class<?> getGenericClass(Object obj, int index) {
        return getGenericClass(obj.getClass(), index);
    }

    /**
     * Retorna a classe genérica apontada pelo índice <code>index</code> da
     * classe <code>clazz</code>.
     *
     * @param clazz - Classe a qual se quer obter sua classe genérica.
     * @param index - Índice da classe genérica da classe consultada.
     * @return A classe genérica do classe consultada no índice indicado.
     */
    public static Class<?> getGenericClass(Class<?> clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (genType instanceof ParameterizedType) {
            ParameterizedType pramType = (ParameterizedType) genType;
            Type[] params = pramType.getActualTypeArguments();
            if ((params != null) && (params.length > index)) {
                return (Class<?>) params[index];
            }
        }
        return null;
    }

    /**
     * Retorna o método getter do campo <code>fieldName</code> da classe
     * <code>clazz</code>.
     *
     * @param clazz     - Classe do método getter a ser retornado.
     * @param fieldName - Nome do campo referente ao método getter.
     * @return O método getter referente.
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    public static Method getGetterMethod(Class<?> clazz, String fieldName)
            throws NoSuchMethodException, SecurityException {
        String name = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        return clazz.getMethod(name);
    }

    /**
     * Retorna o método setter do campo <code>fieldName</code> da classe
     * <code>clazz</code>, o qual recebe os parâmetros indicados em
     * <code>paramTypes</code>.
     *
     * @param clazz      - Classe do método setter a ser retornado.
     * @param fieldName  - Nome do campo referente ao método setter.
     * @param paramTypes - Os tipos de parâmetros o qual o método setter recebe.
     * @return O método setter referente.
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    public static Method getSetterMethod(Class<?> clazz, String fieldName,
                                         Class<?>... paramTypes) throws NoSuchMethodException, SecurityException {
        String name = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        return clazz.getMethod(name, paramTypes);
    }

}