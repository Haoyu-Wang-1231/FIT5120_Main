package fit5120.monash.edu.backend.entity;


import lombok.Data;

@Data
public class Activity {

    private Integer activity_id;
    private String activity_name;
    private String activity_description;
    private String activity_type;
    private String schedule_text;
    private String start_time;
    private String end_time;
    private Integer spots_available;
    private String join_method;

}
