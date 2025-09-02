package com.medical.model; 
 
import javafx.beans.property.*; 
import java.time.LocalDate; 
import java.time.LocalTime; 
 
public class Consultation { 
    private static int cnt = 1; 
    private IntegerProperty id = new SimpleIntegerProperty(); 
    private ObjectProperty<Patient> patient = new SimpleObjectProperty<>(); 
    private ObjectProperty<LocalDate> date = new SimpleObjectProperty<>(); 
    private ObjectProperty<LocalTime> heure = new SimpleObjectProperty<>(); 
    private StringProperty motif = new SimpleStringProperty(); 
    private StringProperty statut = new SimpleStringProperty("Programmee"); 
    private StringProperty notes = new SimpleStringProperty(""); 
 
    public Consultation() { this.id.set(cnt++); } 
 
    public Consultation(Patient p, LocalDate d, LocalTime h, String m) { 
        this(); 
        setPatient(p); setDate(d); setHeure(h); setMotif(m); 
    } 
 
    public int getId() { return id.get(); } 
    public IntegerProperty idProperty() { return id; } 
 
    public Patient getPatient() { return patient.get(); } 
    public ObjectProperty<Patient> patientProperty() { return patient; } 
    public void setPatient(Patient patient) { this.patient.set(patient); } 
 
    public LocalDate getDate() { return date.get(); } 
    public ObjectProperty<LocalDate> dateProperty() { return date; } 
    public void setDate(LocalDate date) { this.date.set(date); } 
 
    public LocalTime getHeure() { return heure.get(); } 
    public ObjectProperty<LocalTime> heureProperty() { return heure; } 
    public void setHeure(LocalTime heure) { this.heure.set(heure); } 
 
    public String getMotif() { return motif.get(); } 
    public StringProperty motifProperty() { return motif; } 
    public void setMotif(String motif) { this.motif.set(motif); } 
 
    public String getStatut() { return statut.get(); } 
    public StringProperty statutProperty() { return statut; } 
    public void setStatut(String statut) { this.statut.set(statut); } 
 
    public String getNotes() { return notes.get(); } 
    public StringProperty notesProperty() { return notes; } 
    public void setNotes(String notes) { this.notes.set(notes); } 
 
    @Override 
    public String toString() { 
        return getDate() + " " + getHeure() + " - " + getPatient() + " [" + getStatut() + "]"; 
    } 
} 
