package com.medical.service; 
 
import com.medical.model.*; 
import javafx.collections.*; 
import java.time.*; 
 
public class DataService { 
    private static DataService inst; 
    private ObservableList<Patient> patients = FXCollections.observableArrayList(); 
    private ObservableList<Consultation> consultations = FXCollections.observableArrayList(); 
 
    private DataService() { loadDemoData(); } 
 
    public static DataService getInstance() { 
        if (inst == null) inst = new DataService(); 
        return inst; 
    } 
 
    public ObservableList<Patient> getPatients() { return patients; } 
    public ObservableList<Consultation> getConsultations() { return consultations; } 
 
    public void addPatient(Patient p) { patients.add(p); } 
    public void removePatient(Patient p) { 
        consultations.removeIf(c -> c.getPatient().equals(p)); 
        patients.remove(p); 
    } 
 
    public void addConsultation(Consultation c) { consultations.add(c); } 
    public void removeConsultation(Consultation c) { consultations.remove(c); } 
 
    private void loadDemoData() { 
        Patient p1 = new Patient("Achraf", "Baabou", LocalDate.of(1980, 5, 15), "0123456789", "baabou@gmail.com"); 
        Patient p2 = new Patient("Youssef", "Diouani", LocalDate.of(1990, 8, 22), "0987654321", "youssef.diouani@gmail.com"); 
        patients.addAll(p1, p2); 
 
        Consultation c1 = new Consultation(p1, LocalDate.now().plusDays(1), LocalTime.of(10, 0), "Controle"); 
        Consultation c2 = new Consultation(p2, LocalDate.now().plusDays(2), LocalTime.of(14, 30), "Suivi"); 
        consultations.addAll(c1, c2); 
    } 
} 
