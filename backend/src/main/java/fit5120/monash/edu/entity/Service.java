package fit5120.monash.edu.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
public class Service {

    private Integer id;
    private String serviceName;
    private String placeName;
    private String contactNumber;
    private String website;
    private String rating;
    private Integer ratingNumber;

    private Boolean wheelchairPark;
    private Boolean wheelchairEntrance;
    private Boolean wheelchairRestroom;
    private Boolean wheelchairSeating;

    private String weekdayDescription;

    private LocalDateTime updateTime;

    //foreign key:
    private Integer locationId;
    private Integer serviceTypeId;

}
