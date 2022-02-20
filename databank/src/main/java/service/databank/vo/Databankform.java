package service.databank.vo;


import lombok.Data;

@Data
public class Databankform {
    private String driverlicense;
    private String deviceid;

    public String getDriverlicense() {
        return driverlicense;
    }

    public void setDriverlicense(String driverlicense) {
        this.driverlicense = driverlicense;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }
}
