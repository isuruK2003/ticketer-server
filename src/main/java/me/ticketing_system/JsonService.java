package me.ticketing_system;

public interface JsonService<T> {
    void saveToJson(T object) throws Exception;
    T readFromJson() throws Exception;
}