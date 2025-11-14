package io.codeforall.bootcamp.javabank.model;

import javax.persistence.*;

/**
 * A generic model entity to be used as a base for concrete types of models
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractModel implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer version;

    /**
     * @see Model#getId()
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * @see Model#setId(Integer)
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @see Model#getVersion()
     */
    @Override
    public Integer getVersion() {
        return version;
    }

    /**
     * @see Model#setVersion(Integer)
     */
    @Override
    public void setVersion(Integer version) {
        this.version = version;
    }
}
