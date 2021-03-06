package org.freekode.wowauction.transfer;

import org.freekode.wowauction.models.BidEntity;
import org.freekode.wowauction.models.ItemEntity;
import org.freekode.wowauction.tools.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Item extends BaseTransfer<ItemEntity> implements Initializable {
    private ItemInfo itemInfo;

    private List<Bid> closedBids;

    private List<Bid> openBids;


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
            closedBids = new ArrayList<>();
            openBids = new ArrayList<>();

            Utils.initCollection(entity.getBids(), options);
            for (BidEntity bidEntity : entity.getBids()) {
                Bid bid = new Bid(bidEntity);
                bid.init(options);
                if (bid.getClosed()) {
                    closedBids.add(bid);
                } else {
                    openBids.add(bid);
                }
            }
        }
    }

    public Integer getId() {
        return entity.getId();
    }

    public String getIdentifier() {
        return entity.getIdentifier();
    }

    public String getRand() {
        return entity.getRand();
    }

    public String getSeed() {
        return entity.getSeed();
    }

    public String getContext() {
        return entity.getContext();
    }

    public Date getCreatedAt() {
        return entity.getCreatedAt();
    }

    public ItemInfo getItemInfo() {
        return itemInfo;
    }

    public List<Bid> getClosedBids() {
        return closedBids;
    }

    public void setClosedBids(List<Bid> closedBids) {
        this.closedBids = closedBids;
    }

    public List<Bid> getOpenBids() {
        return openBids;
    }

    public void setOpenBids(List<Bid> openBids) {
        this.openBids = openBids;
    }

    public enum Options {
        INIT_ITEM_BIDS
    }
}
