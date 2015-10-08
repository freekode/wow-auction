package org.freekode.wowauction.persistence.models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "catalog_heads")
public class CatalogHeadEntity {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String value;

    @OneToMany(mappedBy = "head")
    private List<CatalogEntity> catalogs = new ArrayList<>();


    public CatalogHeadEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<CatalogEntity> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(List<CatalogEntity> catalogs) {
        this.catalogs = catalogs;
    }
}
