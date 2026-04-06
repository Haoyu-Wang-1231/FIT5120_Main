package fit5120.monash.edu.backend.features.searchingService;


import fit5120.monash.edu.backend.common.exception.ResourcesNotFoundException;
import fit5120.monash.edu.backend.common.result.Resp;
import fit5120.monash.edu.backend.common.result.RespEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {


    /**
     * This controller is used only for test if the backend could run.
      * @param hello
     * @return
     */
    @GetMapping("/test")
    public Resp<?> hello(String hello){
        try{
            return Resp.success("hello");
        }catch (Exception e){
            e.printStackTrace();
            return Resp.error(RespEnum.NOT_FOUND);
        }
    }

    @GetMapping("/exception")
    public Resp<?> raiseException(String hello){
        throw new ResourcesNotFoundException(RespEnum.NOT_FOUND);
    }



}
