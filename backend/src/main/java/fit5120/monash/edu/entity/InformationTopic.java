package fit5120.monash.edu.entity;

import lombok.Data;

@Data
public class InformationTopic {

    private Integer topic_id;
    private String topic_title;
    private String topic_summary;
    private String content_type;
    private String external_link;
}
