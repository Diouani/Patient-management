package com.medical.controller; 
 
import com.medical.model.*; 
import com.medical.service.*; 
import javafx.fxml.*; 
import javafx.scene.control.*; 
import javafx.scene.control.cell.*; 
import java.time.*; 
 
public class MainController implements Initializable { 
 
    @FXML private TextField nomTxt, prenomTxt, telTxt, mailTxt, motifTxt; 
    @FXML private DatePicker dateNaissDP, dateConsultDP; 
    @FXML private Spinner<Integer> hSpinner, mSpinner; 
    @FXML private ComboBox<Patient> patientCB; 
    @FXML private ComboBox<String> statutCB; 
    @FXML private TextArea notesTxt; 
    @FXML private TableView<Patient> patientTable; 
    @FXML private TableColumn<Patient, String> nomCol, prenomCol, telCol; 
    @FXML private TableView<Consultation> consultTable; 
    @FXML private TableColumn<Consultation, String> patientConsultCol, motifCol, statutCol; 
    @FXML private TableColumn<Consultation, LocalDate> dateCol; 
    @FXML private TableColumn<Consultation, LocalTime> heureCol; 
 
    private DataService ds = DataService.getInstance(); 
    private Patient selectedP; 
    private Consultation selectedC; 
 
    @Override 
    public void initialize(java.net.URL url, java.util.ResourceBundle rb) { 
        setupPatientTable(); 
        setupConsultTable(); 
        setupComponents(); 
    } 
 
    private void setupPatientTable() { 
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom")); 
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom")); 
        telCol.setCellValueFactory(new PropertyValueFactory<>("tel")); 
        patientTable.setItems(ds.getPatients()); 
        patientTable.getSelectionModel().selectedItemProperty().addListener((obs, old, val) -> selectPatient(val)); 
    } 
 
    private void setupConsultTable() { 
        patientConsultCol.setCellValueFactory(c -> c.getValue().getPatient().nomProperty().concat(" ").concat(c.getValue().getPatient().prenomProperty())); 
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date")); 
        heureCol.setCellValueFactory(new PropertyValueFactory<>("heure")); 
        motifCol.setCellValueFactory(new PropertyValueFactory<>("motif")); 
        statutCol.setCellValueFactory(new PropertyValueFactory<>("statut")); 
        consultTable.setItems(ds.getConsultations()); 
        consultTable.getSelectionModel().selectedItemProperty().addListener((obs, old, val) -> selectConsult(val)); 
    } 
 
    private void setupComponents() { 
        hSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 18, 9)); 
        mSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0, 15)); 
        patientCB.setItems(ds.getPatients()); 
        statutCB.getItems().addAll("Programmee", "En cours", "Terminee", "Annulee"); 
        statutCB.setValue("Programmee"); 
    } 
 
    @FXML 
    private void addPatient() { 
        if (nomTxt.getText().trim().isEmpty()) return; 
        Patient p = new Patient(nomTxt.getText(), prenomTxt.getText(), dateNaissDP.getValue(), telTxt.getText(), mailTxt.getText()); 
        ds.addPatient(p); 
        clearPatientForm(); 
    } 
 
    @FXML 
    private void updatePatient() { 
        if (selectedP == null) return; 
        selectedP.setNom(nomTxt.getText()); 
        selectedP.setPrenom(prenomTxt.getText()); 
        selectedP.setDateNaissance(dateNaissDP.getValue()); 
        selectedP.setTel(telTxt.getText()); 
        selectedP.setMail(mailTxt.getText()); 
        patientTable.refresh(); 
    } 
 
    @FXML 
    private void deletePatient() { 
        if (selectedP != null) { 
            ds.removePatient(selectedP); 
            clearPatientForm(); 
        } 
    } 
 
    @FXML 
    private void addConsult() { 
        if (patientCB.getValue() == null || dateConsultDP.getValue() == null) return; 
        LocalTime time = LocalTime.of(hSpinner.getValue(), mSpinner.getValue()); 
        Consultation c = new Consultation(patientCB.getValue(), dateConsultDP.getValue(), time, motifTxt.getText()); 
        c.setStatut(statutCB.getValue()); 
        c.setNotes(notesTxt.getText()); 
        ds.addConsultation(c); 
        clearConsultForm(); 
    } 
 
    @FXML 
    private void updateConsult() { 
        if (selectedC == null) return; 
        selectedC.setPatient(patientCB.getValue()); 
        selectedC.setDate(dateConsultDP.getValue()); 
        selectedC.setHeure(LocalTime.of(hSpinner.getValue(), mSpinner.getValue())); 
        selectedC.setMotif(motifTxt.getText()); 
        selectedC.setStatut(statutCB.getValue()); 
        selectedC.setNotes(notesTxt.getText()); 
        consultTable.refresh(); 
    } 
 
    @FXML 
    private void deleteConsult() { 
        if (selectedC != null) { 
            ds.removeConsultation(selectedC); 
            clearConsultForm(); 
        } 
    } 
 
    private void selectPatient(Patient p) { 
        selectedP = p; 
        if (p != null) { 
            nomTxt.setText(p.getNom()); 
            prenomTxt.setText(p.getPrenom()); 
            dateNaissDP.setValue(p.getDateNaissance()); 
            telTxt.setText(p.getTel()); 
            mailTxt.setText(p.getMail()); 
        } 
    } 
 
    private void selectConsult(Consultation c) { 
        selectedC = c; 
        if (c != null) { 
            patientCB.setValue(c.getPatient()); 
            dateConsultDP.setValue(c.getDate()); 
            hSpinner.getValueFactory().setValue(c.getHeure().getHour()); 
            mSpinner.getValueFactory().setValue(c.getHeure().getMinute()); 
            motifTxt.setText(c.getMotif()); 
            statutCB.setValue(c.getStatut()); 
            notesTxt.setText(c.getNotes()); 
        } 
    } 
 
    private void clearPatientForm() { 
        nomTxt.clear(); prenomTxt.clear(); telTxt.clear(); mailTxt.clear(); 
        dateNaissDP.setValue(null); 
        selectedP = null; 
        patientTable.getSelectionModel().clearSelection(); 
    } 
 
    private void clearConsultForm() { 
        patientCB.setValue(null); dateConsultDP.setValue(null); motifTxt.clear(); notesTxt.clear(); 
        hSpinner.getValueFactory().setValue(9); mSpinner.getValueFactory().setValue(0); 
        statutCB.setValue("Programmee"); 
        selectedC = null; 
        consultTable.getSelectionModel().clearSelection(); 
    } 
} 
