
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class DatenController implements Serializable {
    private static int netzeIndex = 0;
    private ArrayList<Geisternetz> netzeListe;
    private static Geisternetz selectedNetz = new Geisternetz();
    
    private static Boolean isMeldung = false;
    private static Boolean isBergen = false;
    private static Boolean isGeborgen = false;
    private static Boolean isVerschollen = false;

    private GeisternetzDAO geisternetzDao;
    private PersonDAO personDao;
    
    public DatenController() {
        geisternetzDao = new GeisternetzDAO();
        personDao = new PersonDAO();
    }
    
    public List<Geisternetz> getNetzeListe() {
        if (netzeListe.isEmpty() == false) {
            selectedNetz = netzeListe.get(netzeIndex);        
        } else {
            setSelectedNetz(new Geisternetz());
        }
        return netzeListe;
    }

    public void saveNetz() {
        getSelectedNetz().setNetzStatus(NetzStatus.GEMELDET);
        personDao.persistNetzForPerson(getSelectedNetz(), SelectedPerson.selectedPerson);        
        resetValues();
    }
    
    public void saveVerschollenNetz() {
        getSelectedNetz().setNetzStatus(NetzStatus.VERSCHOLLEN);
        personDao.persistNetzForPerson(getSelectedNetz(), SelectedPerson.selectedPerson);        
        resetValues();
    }

    public void netzBergen() {      
        getSelectedNetz().setNetzStatus(NetzStatus.BERGUNG);
        personDao.mergeNetzForPerson(getSelectedNetz(), SelectedPerson.selectedPerson);
        resetValues();
    }
    
    public void netzGeborgen() {      
        getSelectedNetz().setNetzStatus(NetzStatus.GEBORGEN);
        personDao.mergeNetzForPerson(getSelectedNetz(), SelectedPerson.selectedPerson);
        resetValues();
    }   
    
    public String onMeldungClick() {
        resetValues();
        
        setIsMeldung(true);
        setSelectedNetz(new Geisternetz());
        
        return "uebersicht";
    }
    
    public String onVerschollenClick() {
        resetValues();
        
        setIsVerschollen(true);
        setSelectedNetz(new Geisternetz());
        
        return "uebersicht";
    }

    public String onBergungClick() {
        resetValues();
        setIsBergen(true);
        netzeListe = geisternetzDao.findAll();
        
        for(int i = netzeListe.size() - 1; i >= 0; i--) {
            Geisternetz netz = netzeListe.get(i);
            if(netz.getNetzStatus().equals(NetzStatus.BERGUNG.name()) || netz.getNetzStatus().equals(NetzStatus.GEBORGEN.name())) {
                netzeListe.remove(i);
            }
        }
        return "uebersicht";
    }

    public String onGeborgenClick() {
        resetValues();
        setIsGeborgen(true);      
        netzeListe = geisternetzDao.findAll();

        for(int i = netzeListe.size() - 1; i >= 0; i--) {
            Geisternetz netz = netzeListe.get(i);
            if(netz.getNetzStatus().equals(NetzStatus.GEBORGEN.name())) {
                netzeListe.remove(i);
            }
        }
        return "uebersicht";
    }

    public void onBergenChange() {
        setSelectedNetz(netzeListe.get(netzeIndex));
    }
    
    public void onGeborgenChange() {
        setSelectedNetz(netzeListe.get(netzeIndex));
    }

    public static void resetValues() {
        netzeIndex = 0;
        isMeldung = false;
        isBergen = false;
        isGeborgen = false;
        isVerschollen = false;          
    }

    public Boolean getIsTelnr() {
        return !((SelectedPerson.selectedPerson.getTelnr() == null) || (SelectedPerson.selectedPerson.getTelnr().isBlank()));
    }
    
    public String getNetzStandort() {
        return getSelectedNetz().getStandort();
    }
    
    public void setNetzStandort(String standort) {
        getSelectedNetz().setStandort(standort);
    }
    
    public String getNetzGroesse() {
        return getSelectedNetz().getGroesse();
    }

    public void setNetzGroesse(String groesse) {
        getSelectedNetz().setGroesse(groesse);
    }

    public String getNetzStatus() {
        return getSelectedNetz().getNetzStatus();
    }

    public void setNetzStatus(NetzStatus status) {
        getSelectedNetz().setNetzStatus(status);
    }

    public Geisternetz getSelectedNetz() {
        return this.selectedNetz;
    }
    
    public void setSelectedNetz(Geisternetz selectedNetz) {
        this.selectedNetz = selectedNetz;
    }

    public int getNetzeIndex() {
        return netzeIndex;
    }

    public void setNetzeIndex(int index) {
        this.netzeIndex = index;
    }  
     
    public Boolean getIsMeldung() {
        return isMeldung;
    }
    
    public void setIsMeldung(Boolean value) {
        isMeldung = value;
    }

    public Boolean getIsBergen() {
        return isBergen;
    }

    public void setIsBergen(Boolean value) {
        isBergen = value;
    }

    public Boolean getIsGeborgen() {
        return isGeborgen;
    }

    public void setIsGeborgen(Boolean value) {
        isGeborgen = value;
    }

    public Boolean getIsVerschollen() {
        return isVerschollen;
    }

    public void setIsVerschollen(Boolean value) {
        isVerschollen = value;
    }
}
