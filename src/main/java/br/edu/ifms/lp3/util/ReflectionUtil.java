package br.edu.ifms.lp3.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Classe com utilidades de reflexão de classes e métodos.
 *
 * @author Sidney Sousa
 */
public class ReflectionUtil {

    /**
     * Construtor privado
     */
    private ReflectionUtil(){}

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
    private static Class<?> getGenericClass(Class<?> clazz, int index) {
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

}