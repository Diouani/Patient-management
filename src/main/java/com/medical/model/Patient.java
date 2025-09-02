package com.medical.model; 
 
import javafx.beans.property.*; 
import java.time.LocalDate; 
 
public class Patient { 
    private static int cnt = 1; 
    private IntegerProperty id = new SimpleIntegerProperty(); 
    private StringProperty nom = new SimpleStringProperty(); 
    private StringProperty prenom = new SimpleStringProperty(); 
    private ObjectProperty<LocalDate> dateNaissance = new SimpleObjectProperty<>(); 
    private StringProperty tel = new SimpleStringProperty(); 
    private StringProperty mail = new SimpleStringProperty(); 
 
    public Patient() { this.id.set(cnt++); } 
 
    public Patient(String n, String p, LocalDate d, String t, String m) { 
        this(); 
        setNom(n); setPrenom(p); setDateNaissance(d); setTel(t); setMail(m); 
    } 
 
    public int getId() { return id.get(); } 
    public IntegerProperty idProperty() { return id; } 
    public void setId(int id) { this.id.set(id); } 
 
    public String getNom() { return nom.get(); } 
    public StringProperty nomProperty() { return nom; } 
    public void setNom(String nom) { this.nom.set(nom); } 
 
    public String getPrenom() { return prenom.get(); } 
    public StringProperty prenomProperty() { return prenom; } 
    public void setPrenom(String prenom) { this.prenom.set(prenom); } 
 
    public LocalDate getDateNaissance() { return dateNaissance.get(); } 
    public ObjectProperty<LocalDate> dateNaissanceProperty() { return dateNaissance; } 
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance.set(dateNaissance); } 
 
    public String getTel() { return tel.get(); } 
    public StringProperty telProperty() { return tel; } 
    public void setTel(String tel) { this.tel.set(tel); } 
 
    public String getMail() { return mail.get(); } 
    public StringProperty mailProperty() { return mail; } 
    public void setMail(String mail) { this.mail.set(mail); } 
 
    @Override 
    public String toString() { 
        return getNom() + " " + getPrenom(); 
    } 
} 
