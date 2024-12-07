package me.ticketing_system;

public interface JsonService<T> {
    public void saveToJson(T object) throws Exception;
    public T readFromJson() throws Exception;
}