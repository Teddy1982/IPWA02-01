

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

@Named
@SessionScoped
public class RegistryController implements Serializable {
    private static String name = "";
    private static String telnr = "";
    private static String passwort1 = "";
    private static String passwort2 = "";
    private final ArrayList<PersonStatus> statusList = new ArrayList<>(Arrays.asList(PersonStatus.values()));
    private String status;
    private static PersonStatus personStatus = PersonStatus.MELDEND;
    private ArrayList<Person> personListe = new ArrayList<>();
    
    private Person selectedPerson;
    
    private PersonDAO personDao = new PersonDAO();
     
    public RegistryController() {
        personListe = personDao.findAllPersons();
        setStatus(personStatus);
    }
    
    public String registryForm() {
        SelectedPerson.selectedPerson = new Person();
        resetValues();
        return "registrierung";
    }
    
    public String loginForm() {
        SelectedPerson.selectedPerson = new Person();
        resetValues();
        return "login";        
    }

    public String abmelden() {
        SelectedPerson.selectedPerson = new Person();
        DatenController.resetValues();
        resetValues();
        return "index";        
    }

    public String fromRegistryToUebersichtView() {
        setSelectedPerson();
        SelectedPerson.selectedPerson = getSelectedPerson();
        personDao.savePerson(selectedPerson);
        
        return "uebersicht";
    }  
    
    public String fromLoginToUebersichtView() {
        return "uebersicht";
    }   

    public void postValidateName(ComponentSystemEvent ev) throws AbortProcessingException {
        UIInput temp = (UIInput)ev.getComponent();
        this.name = (String)temp.getValue();
    }
    
    public void validateLogin(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String passwort = (String) value;
        
        personListe = personDao.findAllPersons();
        for(int i = 0; i < personListe.size(); i++) {
            if(name.equals(personListe.get(i).getName())) {
                if(value.equals(personListe.get(i).getPasswort())) {
                    SelectedPerson.selectedPerson = personListe.get(i);
                    this.status = SelectedPerson.selectedPerson.getStatus();
                    return;                
                }
            }
        }
        throw new ValidatorException(new FacesMessage("Login Falsch!"));
    }
    
    public void postValidatePassword(ComponentSystemEvent ev) throws AbortProcessingException {
        UIInput temp = (UIInput)ev.getComponent();
        this.passwort1 = (String)temp.getValue();
    }

    public void validateRegistryComparePasswords(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String passwort2 = (String) value;
        
        if (!passwort1.equals(value)) {
            throw new ValidatorException(new FacesMessage("Passworteingaben stimmen nicht überein!"));
        }
    }

    public void postValidateTelNr(ComponentSystemEvent ev) throws AbortProcessingException {
        UIInput temp = (UIInput)ev.getComponent();
        this.telnr = (String)temp.getValue();
    }

    public void validateRegistryTelNr(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        PersonStatus status = (PersonStatus) value;
        
        if (status == PersonStatus.BERGEND && (this.telnr == null || this.telnr.isBlank())) {
            throw new ValidatorException(new FacesMessage("Telefonnummer fehlt!"));
        }
    }

    public void validateRegistryName(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String personName = (String) value;
        
        personListe = personDao.findAllPersons();
        for(int i = 0; i < personListe.size(); i++) {
            if (personName.equals(personListe.get(i).getName())) {
                throw new ValidatorException(new FacesMessage("Benutzername bereits vergeben!"));
            }
        }
    }

    public void onStatusChange() {
        setStatus(personStatus);
    } 
    
    public static void resetValues() {
        name = "";
        telnr = "";
        passwort1 = "";
        passwort2 = "";
        personStatus = PersonStatus.MELDEND;
    }

    public ArrayList<PersonStatus> getStatusList() {
        return statusList;
    }
      
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        RegistryController.name = name;
    }

    public String getTelnr() {
        return telnr;
    }
    
    public void setTelnr(String telnr) {
        RegistryController.telnr = telnr;
    }
    
    public String getPasswort1() {
        return passwort1;
    }
    
    public void setPasswort1(String passwort1) {
        RegistryController.passwort1 = passwort1;
    }

    public String getPasswort2() {
        return passwort2;
    }
    
    public void setPasswort2(String passwort2) {
        RegistryController.passwort2 = passwort2;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(PersonStatus status) {
        this.status = status.name();
    }
    
    public PersonStatus getPersonStatus() {
        return personStatus;
    }
    
    public void setPersonStatus(PersonStatus status) {
        RegistryController.personStatus = status;
    }

    public Person getSelectedPerson() {
        return selectedPerson;
    }
    
    public void setSelectedPerson() {
        setStatus(getPersonStatus());
        selectedPerson = new Person(getName(), getTelnr(), getPasswort1(), getStatus());
    }
}
