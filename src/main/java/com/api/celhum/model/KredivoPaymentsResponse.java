package com.api.celhum.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KredivoPaymentsResponse {
    String status;
    String message;
    List<payments> payments;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<KredivoPaymentsResponse.payments> getPayments() {
        return payments;
    }

    public void setPayments(List<KredivoPaymentsResponse.payments> payments) {
        this.payments = payments;
    }

    public static class payments{
        String down_payment;
        String name;
        double amount;
        double installment_amount;
        double rate;
        double monthly_installment;
        double discounted_monthly_installment;
        int tenure;
        String id;

        public String getDown_payment() {
            return down_payment;
        }

        public void setDown_payment(String down_payment) {
            this.down_payment = down_payment;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getInstallment_amount() {
            return installment_amount;
        }

        public void setInstallment_amount(double installment_amount) {
            this.installment_amount = installment_amount;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public double getMonthly_installment() {
            return monthly_installment;
        }

        public void setMonthly_installment(double monthly_installment) {
            this.monthly_installment = monthly_installment;
        }

        public double getDiscounted_monthly_installment() {
            return discounted_monthly_installment;
        }

        public void setDiscounted_monthly_installment(double discounted_monthly_installment) {
            this.discounted_monthly_installment = discounted_monthly_installment;
        }

        public int getTenure() {
            return tenure;
        }

        public void setTenure(int tenure) {
            this.tenure = tenure;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
