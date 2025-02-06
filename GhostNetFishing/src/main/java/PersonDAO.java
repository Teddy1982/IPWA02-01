import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PersonDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostnetfishing");

    public void savePerson(Person person) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();      
        t.begin();
        em.persist(person);
        t.commit();
    }
    
    public ArrayList<Person> findAllPersons() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select a from Person a");
        List<Person> list = q.getResultList();
        ArrayList<Person> returnList = new ArrayList<>(list);
        em.close();
        return returnList;
    }
      
    public void mergeNetzForPerson(Geisternetz netz, Person person) {      
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(netz);
        em.merge(person);
        t.commit();
    }

    public void persistNetzForPerson(Geisternetz netz, Person person) {
        person.getGeisternetze().add(netz);            
        
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(netz);
        em.merge(person);
        t.commit();
    }
}
