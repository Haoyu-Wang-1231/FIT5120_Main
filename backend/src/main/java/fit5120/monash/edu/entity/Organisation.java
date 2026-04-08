package fit5120.monash.edu.entity;

import lombok.Data;

@Data
public class Organisation {

    private Integer organisation_id;
    private String organisation_name;
    private String organisation_type;
    private String contact_phone;
    private String website_url;
    private String organisation_description;
}
