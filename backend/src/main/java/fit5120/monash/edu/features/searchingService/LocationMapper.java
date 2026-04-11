package fit5120.monash.edu.features.searchingService;

import fit5120.monash.edu.api.ApiPaths;
import fit5120.monash.edu.entity.Location;
import fit5120.monash.edu.entity.Service;
import fit5120.monash.edu.features.searchingService.dto.model.AccessibilityOptionsModel;
import fit5120.monash.edu.features.searchingService.dto.model.AllServicesModel;
import fit5120.monash.edu.features.searchingService.dto.model.ServiceDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LocationMapper {

    List<Location> getAllLocations();

    List<AccessibilityOptionsModel> getAccessibilityOptionService(
            @Param("serviceType") String serviceType,
            @Param("wheelchairPark") Boolean wheelchairPark,
            @Param("wheelchairEntrance") Boolean wheelchairEntrance,
            @Param("wheelchairRestroom") Boolean wheelchairRestroom,
            @Param("wheelchairSeating") Boolean wheelchairSeating
    );

    ServiceDetail getServiceDetailById(
        @Param("id") Integer id
    );

    List<AllServicesModel> getAllServices(
            @Param("serviceType") String serviceType
    );




    int addNewLocation(Location location);

    Location selectLocationById(@Param("id") Integer id);


    Location selectLocationByDistance(@Param("latitude") Double latitude, @Param("longitude") Double longitude);


}
