package pl.lukaszsuma.app.crudapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.lukaszsuma.app.crudapp.dto.PatientDto;
import pl.lukaszsuma.app.crudapp.model.Patient;
import pl.lukaszsuma.app.crudapp.repository.PatientsDataToDB;
import pl.lukaszsuma.app.crudapp.service.PatientService;
import pl.lukaszsuma.app.crudapp.validation.FirstAndLastNameValidator;
import pl.lukaszsuma.app.crudapp.validation.PeselValidator;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final Logger log = LoggerFactory.getLogger(PatientController.class);
    private PatientService patientService;
    private PeselValidator peselValidator;
    private FirstAndLastNameValidator firstAndLastNameValidator;
    private PatientsDataToDB patientsDataToDB;

    public PatientController(PatientService patientService , PeselValidator peselValidator , FirstAndLastNameValidator firstAndLastNameValidator,
                             PatientsDataToDB patientsDataToDB) {
        this.patientService = patientService;
        this.peselValidator = peselValidator;
        this.firstAndLastNameValidator = firstAndLastNameValidator;
        this.patientsDataToDB = patientsDataToDB;

        patientsDataToDB.getPatientDtoList().forEach(p -> {
            patientService.addPatient(p.getId() , p.getFirstName() , p.getLastName() , p.getPesel());
        });
    }

    @GetMapping
    public List<Patient> getAll() {
        log.info(this.toString());
        return patientService.getAllPatients();
    }

    @PostMapping
    public void addPatient(@RequestBody PatientDto patientDto) {
        validate(patientDto);
        String first = firstAndLastNameValidator.changeFirstAndLastNameFormat(patientDto.getFirstName().trim());
        String last = firstAndLastNameValidator.changeFirstAndLastNameFormat(patientDto.getLastName().trim());
        patientService.addPatient(patientDto.getId() , first , last , patientDto.getPesel());
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }

    /*
    * od klienta trafiają dane, które odpowiadają modelowi patientDto oraz jego id
    * pobieramy z bazy dane pacjenta, do którego pasuje id
    * edytujemy dane, które przyszły z zapytania i zapisujemy w bazie
    */

    @PutMapping("/edit/{id}")
    public void editPatient(@RequestBody PatientDto patientDto , @PathVariable Long id){
        validate(patientDto);
        String first = firstAndLastNameValidator.changeFirstAndLastNameFormat(patientDto.getFirstName().trim());
        String last = firstAndLastNameValidator.changeFirstAndLastNameFormat(patientDto.getLastName().trim());
        patientService.editPatient(first , last , patientDto.getPesel() , id);
    }

    private void validate(PatientDto patientDto) {
        if (!peselValidator.isValid(patientDto.getPesel())){
            throw new RuntimeException("Podano nieprawidłowy numer PESEL");
        } else if (!firstAndLastNameValidator.isValid(patientDto.getFirstName() , patientDto.getLastName())){
            throw new RuntimeException("Nie podano imienia lub nazwiska");
        }
    }
}
