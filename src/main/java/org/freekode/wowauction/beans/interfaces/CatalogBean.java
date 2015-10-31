package org.freekode.wowauction.beans.interfaces;


import org.freekode.wowauction.transfer.CatalogEntry;

import java.util.List;

public interface CatalogBean {
    List<CatalogEntry> getQualityTypes();

    CatalogEntry getQualityType(int codeInteger);

    List<CatalogEntry> getItemClassTypes();

    CatalogEntry getItemClassType(int codeInteger);

    List<CatalogEntry> getItemSubclassTypes();

    CatalogEntry getItemSubclassType(int codeInteger);

    List<CatalogEntry> getItemInventorySlotTypes();

    CatalogEntry getItemInventorySlotType(int codeInteger);
}
