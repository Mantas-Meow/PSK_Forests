package lt.vu.psk.persistance;

import lombok.Setter;
import lt.vu.psk.entities.Tree;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class TreeDAO {

    @Setter
    @PersistenceContext
    private EntityManager em;

    public List<Tree> loadAll() {return em.createNamedQuery("Tree.findAll", Tree.class).getResultList();}

    public void persist(Tree tree) {this.em.persist(tree);}

    public Tree findOne(Integer id) {return em.find(Tree.class, id);}

    public void setEm(EntityManager em) {this.em = em;}
}
