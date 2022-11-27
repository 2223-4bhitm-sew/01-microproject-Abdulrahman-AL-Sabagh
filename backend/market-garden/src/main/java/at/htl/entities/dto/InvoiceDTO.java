package at.htl.entities.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InvoiceDTO {
    private double totalcost;

    public InvoiceDTO(double totalcost) {
        this.totalcost = totalcost;
    }

    public InvoiceDTO() {
    }

    public double getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(double totalcost) {
        this.totalcost = totalcost;
    }

    @Override
    public String toString() {
        return "InvoiceDTO{" +
                "totalcost=" + totalcost +
                '}';
    }
}

