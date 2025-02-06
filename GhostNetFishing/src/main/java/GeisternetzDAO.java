
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class GeisternetzDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostnetfishing");
    
    public ArrayList<Geisternetz> findAll() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select a from Geisternetz a");
        List<Geisternetz> list = q.getResultList();
        ArrayList<Geisternetz> netze = new ArrayList<>(list);
        em.close();
        return new ArrayList<>(netze);
    }
  
    public void saveNetz(Geisternetz netz) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();      
        t.begin();
        em.persist(netz);
        t.commit();
    }
}
