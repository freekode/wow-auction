package org.freekode.wowauction.dao.interfaces;

import org.freekode.wowauction.transfer.CatalogEntry;

import java.util.List;

public interface CatalogDAO {
    List<CatalogEntry> getTypes(int headId);

    CatalogEntry getByCodeInt(int headId, int codeInteger);
}
