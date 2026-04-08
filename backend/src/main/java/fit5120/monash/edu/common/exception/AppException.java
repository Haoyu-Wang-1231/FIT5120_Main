package fit5120.monash.edu.common.exception;

import fit5120.monash.edu.common.result.RespEnum;
import lombok.Getter;


@Getter
public class AppException extends RuntimeException{

    private int code = 500;
    private String message = "server error";

    public AppException(RespEnum respEnum){
        super();
        this.code = respEnum.getCode();
        this.message = respEnum.getMessage();
    }

    public AppException(int code, String message){
        super();
        this.code = code;
        this.message = message;
    }

}
