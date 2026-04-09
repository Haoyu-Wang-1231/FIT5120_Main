package fit5120.monash.edu.features.searchingService;

import fit5120.monash.edu.api.ApiPaths;
import fit5120.monash.edu.entity.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LocationMapper {

    Location selectLocationById(@Param("id") Integer id);


    List<Location> getAllServicesLocation();

    Location selectLocationByDistance(@Param("latitude") Double latitude, @Param("longitude") Double longitude);


}
