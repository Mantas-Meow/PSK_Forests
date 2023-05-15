package lt.vu.psk.mybatis.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Forest {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.FOREST.ID
     *
     * @mbg.generated Mon May 15 02:02:29 EEST 2023
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.FOREST.FOREST_NAME
     *
     * @mbg.generated Mon May 15 02:02:29 EEST 2023
     */
    private String forestName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.FOREST.ID
     *
     * @return the value of PUBLIC.FOREST.ID
     *
     * @mbg.generated Mon May 15 02:02:29 EEST 2023
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.FOREST.ID
     *
     * @param id the value for PUBLIC.FOREST.ID
     *
     * @mbg.generated Mon May 15 02:02:29 EEST 2023
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.FOREST.FOREST_NAME
     *
     * @return the value of PUBLIC.FOREST.FOREST_NAME
     *
     * @mbg.generated Mon May 15 02:02:29 EEST 2023
     */
    public String getForestName() {
        return forestName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.FOREST.FOREST_NAME
     *
     * @param forestName the value for PUBLIC.FOREST.FOREST_NAME
     *
     * @mbg.generated Mon May 15 02:02:29 EEST 2023
     */
    public void setForestName(String forestName) {
        this.forestName = forestName;
    }
}