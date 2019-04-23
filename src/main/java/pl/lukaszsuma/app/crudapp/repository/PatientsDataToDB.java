package pl.lukaszsuma.app.crudapp.repository;

import org.springframework.stereotype.Component;
import pl.lukaszsuma.app.crudapp.dto.PatientDto;

import java.util.Arrays;
import java.util.List;

@Component
public class PatientsDataToDB {
    private List<PatientDto> patientDtoList = Arrays.asList(new PatientDto(1L , "Jacek" , "Jackowski" , 55031783237L) ,
            new PatientDto(2L , "Zdzisław" , "Wolski" , 82021729391L),
            new PatientDto(3L , "Tomek" , "Nowak" , 89102841555L),
            new PatientDto(4L , "Kuba" , "Nowak" , 79032045578L),
            new PatientDto(5L , "Mieczysław" , "Kowalski" , 67120248675L),
            new PatientDto(6L , "Henryk" , "Malinowski" , 47101773154L),
            new PatientDto(7L , "Michał" , "Brzęczyszczykiewicz" , 84042954554L),
            new PatientDto(8L , "Brajan" , "Dżesikowski" , 51020353775L),
            new PatientDto(9L , "Piotr" , "Adamczyk" , 60072843239L),
            new PatientDto(10L , "Robert" , "Kluska" , 45083033756L),
            new PatientDto(11L , "Wiola" , "Małocięta" , 66040887441L),
            new PatientDto(12L , "Dżesika" , "Brajanowska" , 80100954465L),
            new PatientDto(13L , "Małgorzata" , "Mocnospięta" , 66060842149L),
            new PatientDto(14L , "Alicja" , "Drągowska" , 70080624225L),
            new PatientDto(15L , "Kinga" , "Matuszczyk" , 63022187485L),
            new PatientDto(16L , "Mieczysława" , "Lipowska" , 69050115724L),
            new PatientDto(17L , "Genowefa" , "Bułą" , 99013198646L),
            new PatientDto(18L , "Bronisława" , "Zalana" , 57101961985L),
            new PatientDto(19L , "Elżbieta" , "Pomocna" , 71072767863L),
            new PatientDto(20L , "Julia" , "Piotrowska" , 67061433824L));

    public List<PatientDto> getPatientDtoList() {
        return patientDtoList;
    }
}
