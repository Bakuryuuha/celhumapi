package com.api.celhum.model;

import java.util.List;

public class KredivoCheckout {
    String server_key;
    String payment_type;
    String push_uri;
    String back_to_store_uri;

    Transactiondetails transaction_details;
    List<Sellers> sellers;
    Customer_details customer_details;
    Billing_address billing_address;
    Shipping_address shipping_address;

    public String getServer_key() {
        return server_key;
    }

    public void setServer_key(String server_key) {
        this.server_key = server_key;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getPush_uri() {
        return push_uri;
    }

    public void setPush_uri(String push_uri) {
        this.push_uri = push_uri;
    }

    public String getBack_to_store_uri() {
        return back_to_store_uri;
    }

    public void setBack_to_store_uri(String back_to_store_uri) {
        this.back_to_store_uri = back_to_store_uri;
    }

    public Transactiondetails getTransaction_details() {
        return transaction_details;
    }

    public void setTransaction_details(Transactiondetails transaction_details) {
        this.transaction_details = transaction_details;
    }

    public List<Sellers> getSellers() {
        return sellers;
    }

    public void setSellers(List<Sellers> sellers) {
        this.sellers = sellers;
    }

    public Customer_details getCustomer_details() {
        return customer_details;
    }

    public void setCustomer_details(Customer_details customer_details) {
        this.customer_details = customer_details;
    }

    public Billing_address getBilling_address() {
        return billing_address;
    }

    public void setBilling_address(Billing_address billing_address) {
        this.billing_address = billing_address;
    }

    public Shipping_address getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(Shipping_address shipping_address) {
        this.shipping_address = shipping_address;
    }

    public static class Transactiondetails{
        String amount;
        String order_id;
        List<Items> items;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public List<Items> getItems() {
            return items;
        }

        public void setItems(List<Items> items) {
            this.items = items;
        }

        public static class Items{
            String id;
            String name;
            String price;
            String type;
            String url;
            int quantity;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }
        }

    }

    public static class Sellers{
        String id;
        String name;
        String email;
        Address address;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public class Address{
            String first_name;
            String last_name;
            String address;
            String city;
            String postal_code;
            String phone;
            String country_code;

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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPostal_code() {
                return postal_code;
            }

            public void setPostal_code(String postal_code) {
                this.postal_code = postal_code;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getCountry_code() {
                return country_code;
            }

            public void setCountry_code(String country_code) {
                this.country_code = country_code;
            }
        }
    }

    public static class Customer_details{
        String first_name;
        String last_name;
        String email;
        String phone;

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

    public static class Billing_address{
        String first_name;
        String last_name;
        String address;
        String city;
        String postal_code;
        String phone;
        String country_code;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPostal_code() {
            return postal_code;
        }

        public void setPostal_code(String postal_code) {
            this.postal_code = postal_code;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCountry_code() {
            return country_code;
        }

        public void setCountry_code(String country_code) {
            this.country_code = country_code;
        }
    }

    public static class Shipping_address{
        String first_name;
        String last_name;
        String address;
        String city;
        String postal_code;
        String phone;
        String country_code;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPostal_code() {
            return postal_code;
        }

        public void setPostal_code(String postal_code) {
            this.postal_code = postal_code;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCountry_code() {
            return country_code;
        }

        public void setCountry_code(String country_code) {
            this.country_code = country_code;
        }
    }
}
