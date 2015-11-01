package org.freekode.wowauction.controllers.data;

import org.freekode.wowauction.transfer.CatalogEntry;

public class CatalogEntryData {
    private String value;
    private Integer codeInteger;
    private String codeString;


    public CatalogEntryData() {
    }

    public CatalogEntryData(CatalogEntry catalogEntry) {
        value = catalogEntry.getValue();
        codeInteger = catalogEntry.getCodeInteger();
        codeString = catalogEntry.getCodeString();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
