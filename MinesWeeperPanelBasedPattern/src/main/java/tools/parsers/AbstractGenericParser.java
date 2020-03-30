package tools.parsers;

public abstract class AbstractGenericParser<T extends Object,V> {

	public abstract V unParse(T obj) throws IllegalArgumentException, IllegalAccessException;

	public abstract T parse(V obj) throws InstantiationException, IllegalAccessException;

}
