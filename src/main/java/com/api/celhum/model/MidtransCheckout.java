package com.api.celhum.model;

public class MidtransCheckout {

    transactiondetails transaction_details;

    public static class transactiondetails{
        String order_id;
        String gross_amount;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getGross_amount() {
            return gross_amount;
        }

        public void setGross_amount(String gross_amount) {
            this.gross_amount = gross_amount;
        }
    }

    public static class itemdetails{

    }

    public static class customerdetails{

        public static class shippingaddress{

        }

        public static class billingaddress{

        }
    }

    public static class enabledpayments{

    }

}
