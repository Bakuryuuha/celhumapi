package com.api.celhum.model;

public class KredivoCheckout {
    String server_key;
    String payment_type;
    String push_uri;
    String back_to_store_uri;

    public static class transactiondetails{
        String amount;
        String order_id;

        public static class items{
            String id;
            String name;
            String price;
            String type;
            String url;
            int quantity;
        }

    }

    public static class sellers{

    }

    public static class customer_details{

    }

    public static class billing_address{

    }

    public static class shipping_address{

    }
}
