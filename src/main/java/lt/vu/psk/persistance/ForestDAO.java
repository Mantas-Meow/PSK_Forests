package lt.vu.psk.persistance;

import lombok.Setter;
import lt.vu.psk.entities.Forest;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class ForestDAO {

    @Setter
    @PersistenceContext
    private EntityManager em;

    public List<Forest> loadAll() {
        return em.createNamedQuery("Forest.findAll", Forest.class).getResultList();
    }

    public void persist(Forest forest) {
        this.em.persist(forest);
    }

    public Forest findOne(Integer id) {
        return em.find(Forest.class, id);
    }

}
