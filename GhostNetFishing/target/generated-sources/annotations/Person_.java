import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-02-06T21:14:07", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile SingularAttribute<Person, String> passwort;
    public static volatile SingularAttribute<Person, String> name;
    public static volatile ListAttribute<Person, Geisternetz> geisternetze;
    public static volatile SingularAttribute<Person, String> telnr;
    public static volatile SingularAttribute<Person, String> status;

}