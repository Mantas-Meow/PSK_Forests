package lt.vu.psk.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Forest.findAll", query = "select s from Forest as s")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "FOREST")
public class Forest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "FOREST_NAME", nullable = false, unique = true)
    private String forest_name;

    @OneToMany(mappedBy = "forest", fetch = FetchType.EAGER)
    private List<Tree> trees = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Forest forest = (Forest) o;
        return id.equals(forest.id) && forest_name.equals(forest.forest_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, forest_name);
    }
}
