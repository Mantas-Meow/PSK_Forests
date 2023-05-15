package lt.vu.psk.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Tree.findAll", query = "select d from Tree as d")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "TREE")
public class Tree implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "TREE_NAME", nullable = false)
    private String tree_name;

    @ManyToOne
    @JoinColumn(name = "FOREST_ID")
    private Forest forest;

    @ManyToMany(mappedBy = "treeList")
    List<Municipality> municipalityList;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tree tree = (Tree) o;
        return id.equals(tree.id) && tree_name.equals(tree.tree_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tree_name);
    }

}
