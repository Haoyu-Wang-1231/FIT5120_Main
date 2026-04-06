package fit5120.monash.edu.backend.features.searchingService;


import fit5120.monash.edu.backend.common.result.Resp;
import fit5120.monash.edu.backend.common.result.RespEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/serviceSearch")
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

    @PostMapping("/getService")
    public Resp<?> getService(){
        try{
            return Resp.success("hello");
        }catch (Exception e){
            return Resp.error(RespEnum.NOT_FOUND);
        }
    }


}
