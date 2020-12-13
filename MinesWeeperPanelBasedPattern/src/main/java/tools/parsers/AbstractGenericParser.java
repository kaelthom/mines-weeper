package tools.parsers;

import java.lang.reflect.InvocationTargetException;

public interface AbstractGenericParser<T, V> {

    V unParse(T obj) throws IllegalAccessException;

    T parse(V obj) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;

}
