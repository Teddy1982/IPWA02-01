
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;





@Entity
public class Person {
    @Id
    private String name;
    private String telnr;
    private String passwort;
    private String status;

    @OneToMany
    private List<Geisternetz> geisternetze;
    
    public Person() {};
    
    public Person(String name, String telnr, String passwort, String status) {
        setName(name);
        setTelnr(telnr);
        setPasswort(passwort);
        setStatus(status);
    }
     
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getTelnr() {
        return telnr;
    }
    
    public void setTelnr(String telnr) {
        this.telnr = telnr;
    }
    
    public String getPasswort() {
        return passwort;
    }
    
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public List<Geisternetz> getGeisternetze() {
        return geisternetze;
    }
}
