package com.mycompany.myapp.domain;

import com.datastax.driver.mapping.annotations.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A Checkfee.
 */
@Table(name = "checkfee")
public class Checkfee implements Serializable {

    private static final long serialVersionUID = 1L;
    @PartitionKey
    private UUID id;

    private Integer feeamount;

    private String reason;

    private String status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getFeeamount() {
        return feeamount;
    }

    public Checkfee feeamount(Integer feeamount) {
        this.feeamount = feeamount;
        return this;
    }

    public void setFeeamount(Integer feeamount) {
        this.feeamount = feeamount;
    }

    public String getReason() {
        return reason;
    }

    public Checkfee reason(String reason) {
        this.reason = reason;
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public Checkfee status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Checkfee checkfee = (Checkfee) o;
        if (checkfee.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), checkfee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Checkfee{" +
            "id=" + getId() +
            ", feeamount='" + getFeeamount() + "'" +
            ", reason='" + getReason() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
