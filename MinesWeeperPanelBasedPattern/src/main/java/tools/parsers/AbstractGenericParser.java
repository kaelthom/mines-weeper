package tools.parsers;

public interface AbstractGenericParser<T, V> {

    V unParse(T obj) throws IllegalAccessException;

    T parse(V obj) throws InstantiationException, IllegalAccessException;

}
