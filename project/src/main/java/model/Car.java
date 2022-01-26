package model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class Car {
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("number")
    private String carNumber;

    @SerializedName("c_date")
    private Date createdDate;

    @SerializedName("u_id")
    private long userId;

}
