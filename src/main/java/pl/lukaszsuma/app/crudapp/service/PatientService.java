package pl.lukaszsuma.app.crudapp.service;

import org.springframework.stereotype.Service;
import pl.lukaszsuma.app.crudapp.model.Patient;
import pl.lukaszsuma.app.crudapp.repository.PatientRepository;

import java.util.List;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void addPatient(Long id , String firstName , String lastName , Long pesel){
        patientRepository.addPatient(id, firstName , lastName , pesel);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAllPatients();
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public void editPatient(String firstName , String lastName, Long pesel , Long id){
        patientRepository.editPatientData(firstName , lastName , pesel , id);
    }
}
