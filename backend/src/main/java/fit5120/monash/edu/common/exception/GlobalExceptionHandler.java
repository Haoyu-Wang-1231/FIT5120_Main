package fit5120.monash.edu.common.exception;

import fit5120.monash.edu.common.result.Resp;
import fit5120.monash.edu.common.result.RespEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourcesNotFoundException.class)
    public Resp<?> handleResourcesNotFoundException(ResourcesNotFoundException e){
        e.printStackTrace();
        return Resp.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler
    public Resp<?> handleException(Exception e){
        e.printStackTrace();
        return Resp.error(RespEnum.SERVICE_ERROR);
    }

}
