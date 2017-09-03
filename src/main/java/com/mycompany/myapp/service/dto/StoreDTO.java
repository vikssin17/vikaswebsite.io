package com.mycompany.myapp.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Store entity.
 */
public class StoreDTO implements Serializable {

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

    public void setFeeamount(Integer feeamount) {
        this.feeamount = feeamount;
    }

    public String getComment() {
        return comment;
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

        StoreDTO storeDTO = (StoreDTO) o;
        if(storeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), storeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StoreDTO{" +
            "id=" + getId() +
            ", feeamount='" + getFeeamount() + "'" +
            ", comment='" + getComment() + "'" +
            "}";
    }
}
