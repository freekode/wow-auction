package org.freekode.wowauction.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "catalog_heads")
public class CatalogHeadEntity {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String value;

    @OneToMany(mappedBy = "head")
    private List<CatalogEntryEntity> catalogs = new ArrayList<>();


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

    public List<CatalogEntryEntity> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(List<CatalogEntryEntity> catalogs) {
        this.catalogs = catalogs;
    }
}
