package com.api.celhum.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Document(collection = "booklist")
public class Booking {

    public class ContactDetail{
        String title;
        String first_name;
        String last_name;
        String email;
        Date dob;
        String identity_type;
        String identity_number;
        String address;
        String postalcode;
        String handphonenumber;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Date getDob() {
            return dob;
        }

        public void setDob(Date dob) {
            this.dob = dob;
        }

        public String getIdentity_type() {
            return identity_type;
        }

        public void setIdentity_type(String identity_type) {
            this.identity_type = identity_type;
        }

        public String getIdentity_number() {
            return identity_number;
        }

        public void setIdentity_number(String identity_number) {
            this.identity_number = identity_number;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPostalcode() {
            return postalcode;
        }

        public void setPostalcode(String postalcode) {
            this.postalcode = postalcode;
        }

        public String getHandphonenumber() {
            return handphonenumber;
        }

        public void setHandphonenumber(String handphonenumber) {
            this.handphonenumber = handphonenumber;
        }
    }

    public static class PassengerDetail{

        String first_name;
        String last_name;
        String identity_type;
        String identity_number;
        Date dob;
        String passenger_type;
        boolean passenger_addon;

        public PassengerDetail(){}

        @JsonCreator
        public PassengerDetail(@JsonProperty("first_name")String first_name,@JsonProperty("last_name") String last_name,@JsonProperty("identity_type") String identity_type,@JsonProperty("identity_number") String identity_number,@JsonProperty("dob2") Date dob, @JsonProperty("passenger_type") String passenger_type,@JsonProperty("passenger_addon") boolean passenger_addon) {
            this.first_name = first_name;
            this.last_name = last_name;
            this.identity_type = identity_type;
            this.identity_number = identity_number;
            this.dob = dob;
            this.passenger_type = passenger_type;
            this.passenger_addon = passenger_addon;
        }

        public boolean isPassenger_addon() {
            return passenger_addon;
        }

        public void setPassenger_addon(boolean passenger_addon) {
            this.passenger_addon = passenger_addon;
        }

        public String getPassenger_type() {
            return passenger_type;
        }

        public void setPassenger_type(String passenger_type) {
            this.passenger_type = passenger_type;
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

        public String getIdentity_type() {
            return identity_type;
        }

        public void setIdentity_type(String identity_type) {
            this.identity_type = identity_type;
        }

        public String getIdentity_number() {
            return identity_number;
        }

        public void setIdentity_number(String identity_number) {
            this.identity_number = identity_number;
        }

        public Date getDob() {
            return dob;
        }

        public void setDob(Date dob) {
            this.dob = dob;
        }
    }

    public static class Kredivopayment{
        String transaction_id;
        String signature_key;

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
    }

    public static class Midtrans{

    }
    @Id
    String id;
    String tourid;

    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Date departdate;
    ContactDetail contactdetail;
    List<PassengerDetail> passengerdetail;
    String totalpayment;
    String bookstatus;
    PassengerDetail passengersolo;
    String owner;
    String email;
    String status_order;
    String passenger_count;
    String adult;
    String child;
    String extrabed;
    String singlesup;
    TourList.Departure departure;
    Date starttransdate;
    Date endtransdate;
    String payment_method;


    Kredivopayment kredivopayment;
    Midtrans midtrans;

    public Kredivopayment getKredivopayment() {
        return kredivopayment;
    }

    public void setKredivopayment(Kredivopayment kredivopayment) {
        this.kredivopayment = kredivopayment;
    }

    public Midtrans getMidtrans() {
        return midtrans;
    }

    public void setMidtrans(Midtrans midtrans) {
        this.midtrans = midtrans;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public Date getStarttransdate() {
        return starttransdate;
    }

    public void setStarttransdate(Date starttransdate) {
        this.starttransdate = starttransdate;
    }

    public Date getEndtransdate() {
        return endtransdate;
    }

    public void setEndtransdate(Date endtransdate) {
        this.endtransdate = endtransdate;
    }

    public TourList.Departure getDeparture() {
        return departure;
    }

    public void setDeparture(TourList.Departure departure) {
        this.departure = departure;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getExtrabed() {
        return extrabed;
    }

    public void setExtrabed(String extrabed) {
        this.extrabed = extrabed;
    }

    public String getSinglesup() {
        return singlesup;
    }

    public void setSinglesup(String singlesup) {
        this.singlesup = singlesup;
    }

    public String getPassenger_count() {
        return passenger_count;
    }

    public void setPassenger_count(String passenger_count) {
        this.passenger_count = passenger_count;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus_order() {
        return status_order;
    }

    public void setStatus_order(String status_order) {
        this.status_order = status_order;
    }

    public PassengerDetail getPassengersolo() {
        return passengersolo;
    }

    public void setPassengersolo(PassengerDetail passengersolo) {
        this.passengersolo = passengersolo;
    }

    public String getBookstatus() {
        return bookstatus;
    }

    public void setBookstatus(String bookstatus) {
        this.bookstatus = bookstatus;
    }

    public String getTotalpayment() {
        return totalpayment;
    }

    public void setTotalpayment(String totalpayment) {
        this.totalpayment = totalpayment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTourid() {
        return tourid;
    }

    public void setTourid(String tourid) {
        this.tourid = tourid;
    }

    public Date getDepartdate() {
        return departdate;
    }

    public void setDepartdate(Date departdate) {
        this.departdate = departdate;
    }

    public ContactDetail getContactdetail() {
        return contactdetail;
    }

    public void setContactdetail(ContactDetail contactdetail) {
        this.contactdetail = contactdetail;
    }

    public List<PassengerDetail> getPassengerdetail() {
        return passengerdetail;
    }

    public void setPassengerdetail(List<PassengerDetail> passengerdetail) {
        this.passengerdetail = passengerdetail;
    }
}
