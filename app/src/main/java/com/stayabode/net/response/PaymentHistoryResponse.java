package com.stayabode.net.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by VarunBhalla on 16/12/16.
 */

public class PaymentHistoryResponse extends BaseResponse {
    private  List<Data> data;
    @SerializedName("has_existing_payments")
    private String hasExistingPayments;


    public String getHasExistingPayments() {
        return hasExistingPayments;
    }

    public void setHasExistingPayments(String hasExistingPayments) {
        this.hasExistingPayments = hasExistingPayments;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("payment_date")
        private String paymentdate;



        @SerializedName("payment_history_list")
        private List<PaymentHistory> paymentHistory;

        @SerializedName("payment_amount")
        private String paymentAmount;



        public String getPaymentAmount() {
            return paymentAmount;
        }

        public void setPaymentAmount(String paymentAmount) {
            this.paymentAmount = paymentAmount;
        }

        public String getPaymentdate() {
            return paymentdate;
        }

        public void setPaymentdate(String paymentdate) {
            this.paymentdate = paymentdate;
        }

        public List<PaymentHistory> getPaymentHistory() {
            return paymentHistory;
        }

        public void setPaymentHistory(List<PaymentHistory> paymentHistory) {
            this.paymentHistory = paymentHistory;
        }
    }


    public static class PaymentHistory {
        @SerializedName("year")
        private String year;
        @SerializedName("payment_type")
        private String paymentType;
        @SerializedName("paid_amount")
        private String paidAmount;
        @SerializedName("month")
        private String month;


        public PaymentHistory() {

        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }


        public String getPaidAmount() {
            return paidAmount;
        }

        public void setPaidAmount(String paidAmount) {
            this.paidAmount = paidAmount;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }
    }

}
