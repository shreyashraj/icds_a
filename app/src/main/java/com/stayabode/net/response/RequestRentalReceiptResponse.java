package com.stayabode.net.response;


/**
 * Created by Arpit on 2/13/17.
 */
public class RequestRentalReceiptResponse extends BaseResponse {

    private RentalDetailsReponse.Data data;

    public RentalDetailsReponse.Data getData() {
        return data;
    }

    public void setData(RentalDetailsReponse.Data data) {
        this.data = data;
    }

}
