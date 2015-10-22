package org.freekode.wowauction.tools;

import org.freekode.wowauction.transfer.Initializable;

import java.util.Collection;
import java.util.Set;

public class Utils {
    /**
     * Initialize lazy collections
     */
    public static void initCollection(Collection coll, Set options) {
        for (Object aobj : coll) {
            if (aobj instanceof Initializable)
                ((Initializable) aobj).init(options);
        }
    }
}
