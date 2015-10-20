package org.freekode.wowauction.engine.transfer;

import org.freekode.wowauction.engine.services.Utils;
import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.ItemEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Item extends BaseTransfer<ItemEntity> implements Initializable {
    private ItemInfo itemInfo;

    private List<Bid> bids;


    public Item() {
        super();
        entity = new ItemEntity();
    }

    public Item(ItemEntity entity) {
        super(entity);

        if (entity.getItemInfo() != null) {
            itemInfo = new ItemInfo(entity.getItemInfo());
        }
    }

    @Override
    public void init(Set options) {
        if (options != null && options.contains(Options.INIT_ITEM_BIDS)) {
            bids = new ArrayList<>();

            Utils.initCollection(entity.getBids(), options);
            for (BidEntity bidEntity : entity.getBids()) {
                Bid bid = new Bid(bidEntity);
                bid.init(options);
                bids.add(bid);
            }
        }
    }

    public Integer getId() {
        return entity.getId();
    }

    public String getIdentifier() {
        return entity.getIdentifier();
    }

    public String getSuffixId() {
        return entity.getSuffixId();
    }

    public String getUniqueId() {
        return entity.getUniqueId();
    }

    public String getContext() {
        return entity.getContext();
    }

    public Date getCreatedAt() {
        return entity.getCreatedAt();
    }

    public List<Bid> getBids() {
        return bids;
    }

    public enum Options {
        INIT_ITEM_BIDS
    }
}
