package org.freekode.wowauction.beans.impl;

import org.freekode.wowauction.beans.interfaces.CatalogBean;
import org.freekode.wowauction.dao.interfaces.CatalogDAO;
import org.freekode.wowauction.transfer.CatalogEntry;
import org.freekode.wowauction.transfer.CatalogHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CatalogBeanImpl implements CatalogBean {
    @Autowired
    private CatalogDAO catalogDAO;


    @Override
    public List<CatalogEntry> getQualityTypes() {
        return catalogDAO.getTypes(CatalogHead.ITEM_QUALITY);
    }

    @Override
    public CatalogEntry getQualityType(int codeInteger) {
        return catalogDAO.getByCodeInt(CatalogHead.ITEM_QUALITY, codeInteger);
    }

    @Override
    public List<CatalogEntry> getItemClassTypes() {
        return catalogDAO.getTypes(CatalogHead.ITEM_CLASS);
    }

    @Override
    public CatalogEntry getItemClassType(int codeInteger) {
        return catalogDAO.getByCodeInt(CatalogHead.ITEM_CLASS, codeInteger);
    }

    @Override
    public List<CatalogEntry> getItemSubclassTypes() {
        return catalogDAO.getTypes(CatalogHead.ITEM_SUBCLASS);
    }

    @Override
    public CatalogEntry getItemSubclassType(int codeInteger) {
        return catalogDAO.getByCodeInt(CatalogHead.ITEM_SUBCLASS, codeInteger);
    }

    @Override
    public List<CatalogEntry> getItemInventorySlotTypes() {
        return catalogDAO.getTypes(CatalogHead.ITEM_INVENTOTY_SLOT);
    }

    @Override
    public CatalogEntry getItemInventorySlotType(int codeInteger) {
        return catalogDAO.getByCodeInt(CatalogHead.ITEM_INVENTOTY_SLOT, codeInteger);
    }
}
