/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.carngo.entities;

/**
 *
 * @author ychaa
 */
public class Personne {
    private int id;
    private String nom;
    private int age;

    public Personne() {
    }

    public Personne(int id, String nom, int age) {
        this.id = id;
        this.nom = nom;
        this.age = age;
    }

    public Personne(String nom, int age) {
        this.nom = nom;
        this.age = age;
    }

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", nom=" + nom + ", age=" + age + '}';
    }
    
    
    
    
    
    
    
}

