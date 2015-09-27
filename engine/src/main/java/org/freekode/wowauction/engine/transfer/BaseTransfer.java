package org.freekode.wowauction.engine.transfer;

import java.util.Set;

public abstract class BaseTransfer<E> {
    protected E entity;

    public BaseTransfer() {
        super();
    }

    public BaseTransfer(E entity) {
        super();
        this.entity = entity;
    }

    public E getEntity() {
        return entity;
    }

    public abstract void init(Set options);

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BaseTransfer)) {
            return false;
        }

        BaseTransfer<E> other = (BaseTransfer<E>) obj;

        if (entity != null && other.entity != null) {
            return entity.equals(other.entity);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return entity != null ? entity.hashCode() : 0;
    }

    public Boolean equalsContent(E entity) {
        return false;
    }
}
