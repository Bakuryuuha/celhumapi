package com.api.celhum.model;

import java.util.List;

public class KredivoPayments {
    String server_key;
    String amount;
    List<items> items;

    public String getServer_key() {
        return server_key;
    }

    public void setServer_key(String server_key) {
        this.server_key = server_key;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public List<KredivoPayments.items> getItems() {
        return items;
    }

    public void setItems(List<KredivoPayments.items> items) {
        this.items = items;
    }

    public static class items{
        String id;
        String name;
        String price;
        String url;
        String image_url;
        String type;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}

