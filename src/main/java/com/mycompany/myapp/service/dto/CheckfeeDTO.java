package com.mycompany.myapp.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the Checkfee entity.
 */
public class CheckfeeDTO implements Serializable {

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

    public void setFeeamount(Integer feeamount) {
        this.feeamount = feeamount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
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

        CheckfeeDTO checkfeeDTO = (CheckfeeDTO) o;
        if(checkfeeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), checkfeeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CheckfeeDTO{" +
            "id=" + getId() +
            ", feeamount='" + getFeeamount() + "'" +
            ", reason='" + getReason() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
