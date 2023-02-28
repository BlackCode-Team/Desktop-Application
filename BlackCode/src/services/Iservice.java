package services;


import java.util.List;

public interface Iservice <T>{
        public boolean ajouterentite(T t);
        //public boolean modifierentite(T t);
       // public boolean supprimerentite(T t);
        public List<T> afficherentite();

}

