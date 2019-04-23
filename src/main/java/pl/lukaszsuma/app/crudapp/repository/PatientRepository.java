package pl.lukaszsuma.app.crudapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.lukaszsuma.app.crudapp.model.Patient;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient , Long> {

    @Query("SELECT p FROM Patient p")
    List<Patient> findAllPatients ();

    @Modifying
    @Transactional
    @Query(value = "UPDATE Patient p SET p.first_Name = ?1 , p.last_Name = ?2," +
            "p.pesel = ?3 WHERE p.id = ?4" , nativeQuery = true)
    void editPatientData (String firstName , String lastName, Long pesel , Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Patient WHERE id = ?1")
    void deleteById(Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Patient (id , first_Name , last_Name , pesel) " +
            "VALUES (?1 , ?2 , ?3 , ?4)" , nativeQuery = true)
    void addPatient(Long id , String firstName , String lastName , Long pesel);
}
