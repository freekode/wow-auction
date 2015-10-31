package org.freekode.wowauction.transfer;

import org.freekode.wowauction.models.CatalogEntryEntity;
import org.freekode.wowauction.models.CatalogHeadEntity;

import java.util.Set;

public class CatalogEntry extends BaseTransfer<CatalogEntryEntity> implements Initializable {
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
