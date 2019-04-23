package pl.lukaszsuma.app.crudapp.validation;

import org.springframework.stereotype.Component;

@Component
public class PeselValidator {

    public boolean isValid(Long pesel) {
        String peselToCheck = String.valueOf(pesel);
        if (isPeselWrong(pesel)) {
            return false;
        }
        return (9 * Character.getNumericValue(peselToCheck.charAt(0)) +
                7 * Character.getNumericValue(peselToCheck.charAt(1)) +
                3 * Character.getNumericValue(peselToCheck.charAt(2)) +
                Character.getNumericValue(peselToCheck.charAt(3)) +
                9 * Character.getNumericValue(peselToCheck.charAt(4)) +
                7 * Character.getNumericValue(peselToCheck.charAt(5)) +
                3 * Character.getNumericValue(peselToCheck.charAt(6)) +
                Character.getNumericValue(peselToCheck.charAt(7)) +
                9 * Character.getNumericValue(peselToCheck.charAt(8)) +
                7 * Character.getNumericValue(peselToCheck.charAt(9))) % 10 == Character.getNumericValue(peselToCheck.charAt(10));
    }

    private boolean isPeselWrong(Long pesel) {
        String peselToCheck = String.valueOf(pesel);
        if (!peselToCheck.matches("\\d{11}")) {
            return true;
        }
        return false;
    }

}
