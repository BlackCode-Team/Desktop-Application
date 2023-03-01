package services;

import java.util.List;

public interface IServices<T>{

    public boolean addEntity(T t);
    public boolean updateEntity(T t);
    public boolean deleteEntity(T t);
    public List<T> findAllEntity();
}
