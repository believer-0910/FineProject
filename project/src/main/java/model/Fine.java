package model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class Fine {

    @SerializedName("u_id")
    private long userId;

    @SerializedName("c_id")
    private long carId;

    @SerializedName("amount")
    private double amount;

    @SerializedName("c_date")
    private String createdDate;

    @SerializedName("p_date")
    private Date payDate;

    @SerializedName("is_payed")
    private String isPaid;
}
