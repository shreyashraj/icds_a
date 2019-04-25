package com.stayabode.net.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by VarunBhalla on 16/12/16.
 */

public class RentalDetailsReponse extends BaseResponse {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("dues")
        private RentalDues RentalDues;

        @SerializedName("bank_details")
        private BankDetails bankDetails;

        @SerializedName("paid_months")
        private List<RequestRentalMonths> RequestRentalMonthsList;

        public List<RequestRentalMonths> getRequestRentalMonthsList() {
            return RequestRentalMonthsList;
        }

        public void setRequestRentalMonthsList(List<RequestRentalMonths> requestRentalMonthsList) {
            RequestRentalMonthsList = requestRentalMonthsList;
        }

        public RentalDetailsReponse.RentalDues getRentalDues() {
            return RentalDues;
        }

        public void setRentalDues(RentalDetailsReponse.RentalDues rentalDues) {
            RentalDues = rentalDues;
        }

        public BankDetails getBankDetails() {
            return bankDetails;
        }

        public void setBankDetails(BankDetails bankDetails) {
            this.bankDetails = bankDetails;
        }
    }



    public static class RequestRentalMonths{
        @SerializedName("month")
        private String month;
        @SerializedName("year")
        private String year;

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

    }

    public static class RentalDues implements Parcelable {
        @SerializedName("other_charges_json")
        private OtherChargesJson otherCharges;

        @SerializedName("late_payment_json")
        private LatePaymentJson latePayment;

        @SerializedName("rent_json")
        private RentJson rent;

        @SerializedName("total")
        private String totalAmount;

        @SerializedName("deposit_due")
        private String depositDue;


        protected RentalDues(Parcel in) {
            otherCharges = in.readParcelable(OtherChargesJson.class.getClassLoader());
            latePayment = in.readParcelable(LatePaymentJson.class.getClassLoader());
            rent = in.readParcelable(RentJson.class.getClassLoader());
            totalAmount = in.readString();
            depositDue = in.readString();
        }

        public static final Creator<RentalDues> CREATOR = new Creator<RentalDues>() {
            @Override
            public RentalDues createFromParcel(Parcel in) {
                return new RentalDues(in);
            }

            @Override
            public RentalDues[] newArray(int size) {
                return new RentalDues[size];
            }
        };

        public OtherChargesJson getOtherCharges() {
            return otherCharges;
        }

        public void setOtherCharges(OtherChargesJson otherCharges) {
            this.otherCharges = otherCharges;
        }

        public String getDepositDue() {
            return depositDue;
        }

        public void setDepositDue(String depositDue) {
            this.depositDue = depositDue;
        }

        public LatePaymentJson getLatePayment() {
            return latePayment;
        }

        public void setLatePayment(LatePaymentJson latePayment) {
            this.latePayment = latePayment;
        }

        public RentJson getRent() {
            return rent;
        }

        public void setRent(RentJson rent) {
            this.rent = rent;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(otherCharges, i);
            parcel.writeParcelable(latePayment, i);
            parcel.writeParcelable(rent, i);
            parcel.writeString(totalAmount);
            parcel.writeString(depositDue);
        }
    }

    public static class BankDetails{
        @SerializedName("account_number")
        private String accountNumber;
        @SerializedName("ifsc")
        private String ifsc;
        @SerializedName("bank_name")
        private String bankName;

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getIfsc() {
            return ifsc;
        }

        public void setIfsc(String ifsc) {
            this.ifsc = ifsc;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }
    }


    public static class OtherChargesJson implements Parcelable{
        @SerializedName("other_charges")
        private List<YearMonthAmount> otherChargesList;
        @SerializedName("total_other_charges")
        private String totalOtherCharges;

        protected OtherChargesJson(Parcel in) {
            otherChargesList = in.createTypedArrayList(YearMonthAmount.CREATOR);
            totalOtherCharges = in.readString();
        }

        public static final Creator<OtherChargesJson> CREATOR = new Creator<OtherChargesJson>() {
            @Override
            public OtherChargesJson createFromParcel(Parcel in) {
                return new OtherChargesJson(in);
            }

            @Override
            public OtherChargesJson[] newArray(int size) {
                return new OtherChargesJson[size];
            }
        };

        public List<YearMonthAmount> getOtherChargesList() {
            return otherChargesList;
        }

        public void setOtherChargesList(List<YearMonthAmount> otherChargesList) {
            this.otherChargesList = otherChargesList;
        }

        public String getTotalOtherCharges() {
            return totalOtherCharges;
        }

        public void setTotalOtherCharges(String totalOtherCharges) {
            this.totalOtherCharges = totalOtherCharges;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeTypedList(otherChargesList);
            parcel.writeString(totalOtherCharges);
        }
    }


    public static class YearMonthAmount implements  Parcelable{

        @SerializedName("year")
        private String year;
        @SerializedName("amount")
        private String dueAmount;
        @SerializedName("id")
        private String id;
        @SerializedName("month")
        private String month;

        protected YearMonthAmount(Parcel in) {
            year = in.readString();
            dueAmount = in.readString();
            id = in.readString();
            month = in.readString();
        }

        public static final Creator<YearMonthAmount> CREATOR = new Creator<YearMonthAmount>() {
            @Override
            public YearMonthAmount createFromParcel(Parcel in) {
                return new YearMonthAmount(in);
            }

            @Override
            public YearMonthAmount[] newArray(int size) {
                return new YearMonthAmount[size];
            }
        };

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getDueAmount() {
            return dueAmount;
        }

        public void setDueAmount(String dueAmount) {
            this.dueAmount = dueAmount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(year);
            parcel.writeString(dueAmount);
            parcel.writeString(id);
            parcel.writeString(month);
        }
    }


    public static class LatePaymentJson implements Parcelable{
        @SerializedName("late_payment")
        private List<YearMonthAmount> latePaymentList;
        @SerializedName("total_late_payment")
        private String totalLatePayment;

        protected LatePaymentJson(Parcel in) {
            latePaymentList = in.createTypedArrayList(YearMonthAmount.CREATOR);
            totalLatePayment = in.readString();
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeTypedList(latePaymentList);
            parcel.writeString(totalLatePayment);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<LatePaymentJson> CREATOR = new Creator<LatePaymentJson>() {
            @Override
            public LatePaymentJson createFromParcel(Parcel in) {
                return new LatePaymentJson(in);
            }

            @Override
            public LatePaymentJson[] newArray(int size) {
                return new LatePaymentJson[size];
            }
        };


        public List<YearMonthAmount> getLatePaymentList() {
            return latePaymentList;
        }

        public void setLatePaymentList(List<YearMonthAmount> latePaymentList) {
            this.latePaymentList = latePaymentList;
        }

        public String getTotalLatePayment() {
            return totalLatePayment;
        }

        public void setTotalLatePayment(String totalLatePayment) {
            this.totalLatePayment = totalLatePayment;
        }




    }


    public static class RentJson implements Parcelable{
        @SerializedName("rent_list")
        private List<YearMonthAmount> rentList;
        @SerializedName("total_rent")
        private String totalRent;

        protected RentJson(Parcel in) {
            rentList = in.createTypedArrayList(YearMonthAmount.CREATOR);
            totalRent = in.readString();
        }

        public static final Creator<RentJson> CREATOR = new Creator<RentJson>() {
            @Override
            public RentJson createFromParcel(Parcel in) {
                return new RentJson(in);
            }

            @Override
            public RentJson[] newArray(int size) {
                return new RentJson[size];
            }
        };

        public List<YearMonthAmount> getRentList() {
            return rentList;
        }

        public void setRentList(List<YearMonthAmount> rentList) {
            this.rentList = rentList;
        }

        public String getTotalRent() {
            return totalRent;
        }

        public void setTotalRent(String totalRent) {
            this.totalRent = totalRent;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeTypedList(rentList);
            parcel.writeString(totalRent);
        }
    }

}
