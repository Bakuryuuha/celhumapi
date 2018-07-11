package com.api.celhum.model;

import java.util.Date;

public class KredivoConfirmPurchase {
    String status;
    String amount;
    String payment_type;
    String transaction_status;
    String order_id;
    String message;

    Date transaction_time;
    String transaction_id;
    String signature_key;
    ship_address shipping_address;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getTransaction_status() {
        return transaction_status;
    }

    public void setTransaction_status(String transaction_status) {
        this.transaction_status = transaction_status;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTransaction_time() {
        return transaction_time;
    }

    public void setTransaction_time(Date transaction_time) {
        this.transaction_time = transaction_time;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getSignature_key() {
        return signature_key;
    }

    public void setSignature_key(String signature_key) {
        this.signature_key = signature_key;
    }

    public ship_address getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(ship_address shipping_address) {
        this.shipping_address = shipping_address;
    }

    public static class ship_address{
        String city;
        String first_name;
        String last_name;
        String countrycode;
        String creation_code;
        String phone;
        String state;
        int transaction;
        String postcode;
        String location_details;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getCountrycode() {
            return countrycode;
        }

        public void setCountrycode(String countrycode) {
            this.countrycode = countrycode;
        }

        public String getCreation_code() {
            return creation_code;
        }

        public void setCreation_code(String creation_code) {
            this.creation_code = creation_code;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getTransaction() {
            return transaction;
        }

        public void setTransaction(int transaction) {
            this.transaction = transaction;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getLocation_details() {
            return location_details;
        }

        public void setLocation_details(String location_details) {
            this.location_details = location_details;
        }
    }
}
