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
        @NamedQuery(name = "Municipality.findAll", query = "select r from Municipality as r"),
        @NamedQuery(name = "Municipality.findOne", query = "select r from Municipality as r where r.municipality_name = :municipality_name and r.country_name = :country_name")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "MUNICIPALITY")
public class Municipality implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "MUNICIPALITY_NAME", nullable = false)
    private String municipality_name;

    @Size(max = 50)
    @Column(name = "MUNICIPALITY_COUNTRY_NAME", nullable = false)
    private String country_name;

    @Column
    @ManyToMany
    @JoinTable(name = "TREES_MUNICIPALITIES",
            joinColumns = @JoinColumn(name = "MUNICIPALITY_ID"),
            inverseJoinColumns = @JoinColumn(name = "TREE_ID"))
    List<Tree> treeList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Municipality municipality = (Municipality) o;
        return id.equals(municipality.id) && municipality_name.equals(municipality.municipality_name) && country_name.equals(municipality.country_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, municipality_name, country_name);
    }
}
