package lt.vu.psk.persistance;

import lombok.Setter;
import lt.vu.psk.entities.Municipality;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class MunicipalityDAO {

    @Setter
    @PersistenceContext
    private EntityManager em;

    public List<Municipality> loadAll() {return em.createNamedQuery("Municipality.findAll", Municipality.class). getResultList();}

    public void persist(Municipality municipality) {this.em.persist(municipality);}

    public Municipality findOne(Integer id) {return em.find(Municipality.class, id);}

    public void setEm(EntityManager em) {this.em = em;}

    public Municipality findByMunicipality(String first_name, String last_name) {
        try
        {
            return em.createNamedQuery("Municipality.findOne", Municipality.class)
                    .setParameter("municipality_name", first_name)
                    .setParameter("country_name", last_name)
                    .getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }
}
