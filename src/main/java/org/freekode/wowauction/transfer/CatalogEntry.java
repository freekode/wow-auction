package org.freekode.wowauction.transfer;

import org.freekode.wowauction.models.CatalogEntryEntity;

import java.util.Set;

public class CatalogEntry extends BaseTransfer<CatalogEntryEntity> implements Initializable {
    private CatalogHead head;


    public CatalogEntry() {
        super();
        entity = new CatalogEntryEntity();
    }

    public CatalogEntry(CatalogEntryEntity entity) {
        super(entity);
    }

    @Override
    public void init(Set options) {
    }

    public Integer getId() {
        return entity.getId();
    }

    public String getValue() {
        return entity.getValue();
    }

    public void setValue(String value) {
        entity.setValue(value);
    }

    public CatalogHead getHead() {
        return head;
    }

    public void setHead(CatalogHead head) {
        this.head = head;
    }

    public Integer getCodeInteger() {
        return entity.getCodeInteger();
    }

    public void setCodeInteger(Integer codeInteger) {
        entity.setCodeInteger(codeInteger);
    }

    public String getCodeString() {
        return entity.getCodeString();
    }

    public void setCodeString(String codeString) {
        entity.setCodeString(codeString);
    }
}
