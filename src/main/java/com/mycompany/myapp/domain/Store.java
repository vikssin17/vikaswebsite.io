package com.mycompany.myapp.domain;

import com.datastax.driver.mapping.annotations.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Store.
 */
@Table(name = "store")
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;
    @PartitionKey
    private UUID id;

    private Integer feeamount;

    private String comment;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getFeeamount() {
        return feeamount;
    }

    public Store feeamount(Integer feeamount) {
        this.feeamount = feeamount;
        return this;
    }

    public void setFeeamount(Integer feeamount) {
        this.feeamount = feeamount;
    }

    public String getComment() {
        return comment;
    }

    public Store comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Store store = (Store) o;
        if (store.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), store.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Store{" +
            "id=" + getId() +
            ", feeamount='" + getFeeamount() + "'" +
            ", comment='" + getComment() + "'" +
            "}";
    }
}
