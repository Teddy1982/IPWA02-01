
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Geisternetz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String standort;
    private String groesse;
    private String netzStatus;
    
    public Geisternetz() {};
    
    public Geisternetz(Integer id, String standort, String groesse, NetzStatus netzStatus) {
        setId(id);
        setStandort(standort);
        setGroesse(groesse);
        setNetzStatus(netzStatus);        
    }
       
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getStandort() {
        return standort;
    }
    
    public void setStandort(String standort) {
        this.standort = standort;
    }

    public String getGroesse() {
        return groesse;
    }
    
    public void setGroesse(String groesse) {
        this.groesse = groesse;
    }
    
    public String getNetzStatus() {
        return netzStatus;
    }
    
    public void setNetzStatus(NetzStatus netzStatus) {
        this.netzStatus = netzStatus.name();
    }
}
