package fit5120.monash.edu.features.searchingService.dto.request;

import lombok.Data;

@Data
public class SearchingAllLocationRequire {
    private Integer limit;

    private Double latitude;
    private Double longitude;
}
