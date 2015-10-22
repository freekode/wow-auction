package org.freekode.wowauction.models;

import javax.persistence.*;

@Entity
@Table(name = "catalogs")
public class CatalogEntity {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer codeInteger;

    private String codeString;

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

    public Integer getCodeInteger() {
        return codeInteger;
    }

    public void setCodeInteger(Integer codeInteger) {
        this.codeInteger = codeInteger;
    }

    public String getCodeString() {
        return codeString;
    }

    public void setCodeString(String codeString) {
        this.codeString = codeString;
    }
}
