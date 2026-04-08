package fit5120.monash.edu.features.searchingService;


import fit5120.monash.edu.api.ApiPaths;
import fit5120.monash.edu.common.result.Resp;
import fit5120.monash.edu.common.result.RespEnum;
import fit5120.monash.edu.features.searchingService.request.SearchingAllServiceRequire;
import fit5120.monash.edu.features.searchingService.request.SearchingServiceRequire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.Location.BASE)
public class ServiceSearchingController {

    @Autowired
    private ServiceSearchingService serviceSearchingService;

    @GetMapping("/hello")
    public Resp<?> hello(String hello){
        try{
            return Resp.success("hello");
        }catch (Exception e){
            return Resp.error(RespEnum.NOT_FOUND);
        }
    }

    @PostMapping(ApiPaths.Location.GETALLSERVICESLOCATION)
    public Resp<?> getAllServicesLocation(@RequestBody SearchingAllServiceRequire sasr){
        return Resp.success(serviceSearchingService.getAllServicesLocation(sasr));

    }

    @PostMapping(ApiPaths.Location.GETSERVICELOCATIONBYID)
    public Resp<?> getServiceLocation(@RequestBody SearchingServiceRequire searchingServiceRequire){
        //
        return Resp.success(serviceSearchingService.getServiceLocation(searchingServiceRequire));
    }


}
