package services;

import entities.Vehicule;

import java.util.List;

public interface IService <T>{

    public boolean addEntity(T t);
    public boolean updateEntity(T t);
    public boolean deleteEntity(T t);
    public List<T> findAllEntity();
}
