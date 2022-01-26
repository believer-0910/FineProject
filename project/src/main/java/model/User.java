package model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("p_seriya")
    private String passportSerial;

    @SerializedName("c_date")
    private Date createdDate;
}
