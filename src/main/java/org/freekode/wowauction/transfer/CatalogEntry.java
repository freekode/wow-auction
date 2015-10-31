package org.freekode.wowauction.transfer;

import org.freekode.wowauction.models.CatalogEntryEntity;
import org.freekode.wowauction.models.CatalogHeadEntity;

import java.util.Set;

public class CatalogEntry extends BaseTransfer<CatalogEntryEntity> implements Initializable {
    public static final int ITEM_QUALITY = 1;
    public static final int ITEM_CLASS = 2;
    public static final int ITEM_SUBCLASS = 3;
    public static final int ITEM_INVENTOTY_SLOT = 4;


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
}
