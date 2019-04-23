package pl.lukaszsuma.app.crudapp.validation;

import org.springframework.stereotype.Component;

@Component
public class FirstAndLastNameValidator {

    public boolean isValid (String firstName , String lastName){
        if (firstName == null || firstName.trim().equals("")){
            return false;
        } else if(lastName == null || lastName.trim().equals("")){
            return false;
        }
        return true;
    }

    public String changeFirstAndLastNameFormat(String name){
        String formattedName = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        return formattedName;
    }
}
