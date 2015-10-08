package org.freekode.wowauction.persistence.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "catalogs")
public class CatalogEntity {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer code;

    private String value;

    @ManyToOne
    @JoinColumn(name = "headId")
    private CatalogHeadEntity head;


    public CatalogEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CatalogHeadEntity getHead() {
        return head;
    }

    public void setHead(CatalogHeadEntity head) {
        this.head = head;
    }
}
