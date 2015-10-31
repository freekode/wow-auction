package org.freekode.wowauction.transfer;

import org.freekode.wowauction.models.CatalogHeadEntity;

import java.util.Set;

public class CatalogHead extends BaseTransfer<CatalogHeadEntity> implements Initializable {
    public static final int ITEM_QUALITY = 1;
    public static final int ITEM_CLASS = 2;
    public static final int ITEM_SUBCLASS = 3;
    public static final int ITEM_INVENTORY_SLOT = 4;


    public CatalogHead() {
        super();
        entity = new CatalogHeadEntity();
    }

    public CatalogHead(CatalogHeadEntity entity) {
        super(entity);
    }

    @Override
    public void init(Set options) {
    }
}
