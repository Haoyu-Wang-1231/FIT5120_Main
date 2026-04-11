package fit5120.monash.edu.features.searchingService;


import fit5120.monash.edu.api.ApiPaths;
import fit5120.monash.edu.common.result.Resp;
import fit5120.monash.edu.common.result.RespEnum;
import fit5120.monash.edu.features.searchingService.dto.request.AccessibilityServicesRequire;
import fit5120.monash.edu.features.searchingService.dto.request.SearchingAllLocationRequire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.Service.BASE)
public class ServiceSearchingController {

//    @Autowired
//    private ServiceSearchingService serviceSearchingService;

    @Autowired
    private LocationService locationService;

    @GetMapping("/hello")
    public Resp<?> hello(String hello){
        try{
            return Resp.success("hello");
        }catch (Exception e){
            return Resp.error(RespEnum.NOT_FOUND);
        }
    }

    @PostMapping(ApiPaths.Service.getAllServiceLocation)
    public Resp<?> getAllLocation(@RequestBody SearchingAllLocationRequire searchingAllLocationRequire){
        return Resp.success(locationService.getAllLocations(searchingAllLocationRequire));
    }


    @PostMapping(ApiPaths.Service.getAccessibilityOptionService)
    public Resp<?> getAccessibilityOptionService(@RequestBody AccessibilityServicesRequire accessibilityServicesRequire){
        return Resp.success(locationService.getAccessibilityOptionService(accessibilityServicesRequire));
    }

    @GetMapping(ApiPaths.Service.getServiceDetail)
    public Resp<?> getServiceDetailById(@PathVariable Integer id){
        return Resp.success(locationService.getServiceDetail(id));
    }







//    @PostMapping(ApiPaths.Location.GETALLSERVICESLOCATION)
//    public Resp<?> getAllServicesLocation(@RequestBody SearchingAllServiceRequire sasr){
//        return Resp.success(serviceSearchingService.getAllServicesLocation(sasr));
//
//    }
//
//    @PostMapping(ApiPaths.Location.GETLOCATIONDETAILS)
//    public Resp<?> getLocationDetails(@RequestBody SearchingServiceRequire searchingServiceRequire){
//        return Resp.success(serviceSearchingService.getLocationDetails(searchingServiceRequire));
//    }
//
//    @PostMapping(ApiPaths.Location.GETSERVICELOCATIONBYID)
//    public Resp<?> getServiceLocation(@RequestBody SearchingServiceRequire searchingServiceRequire){
//        return Resp.success(serviceSearchingService.getServiceLocation(searchingServiceRequire));
//    }


}
