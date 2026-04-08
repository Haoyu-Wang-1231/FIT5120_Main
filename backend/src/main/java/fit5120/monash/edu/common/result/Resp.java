package fit5120.monash.edu.common.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class Resp<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    public Resp(){}

    public Resp(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Resp(Integer code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }



    public static <T> Resp<T> success(){
        return new Resp<>(RespEnum.SUCCESS.getCode(), RespEnum.SUCCESS.getMessage());
    }


    public static <T> Resp<T> success(T data){
        return new Resp<>(RespEnum.SUCCESS.getCode(), RespEnum.SUCCESS.getMessage(), data);
    }

    public static <T> Resp<T> success(String message, T data){
        return new Resp<>(RespEnum.SUCCESS.getCode(), message, data);
    }

    public static Resp<?> error(String message){
        return new Resp<>(RespEnum.NOT_FOUND.getCode(), message);
    }

    public static Resp<?> error(int code, String message){
        return new Resp<>(code, message);
    }

    public static Resp<?> error(RespEnum respEnum){
        return new Resp<>(respEnum.getCode(), respEnum.getMessage(), null);
    }

//    public static Result<?> failed() {
//        return new Result<>(ResultEnum.COMMON_FAILED.getCode(),
//                ResultEnum.COMMON_FAILED.getMessage(), null);
//    }
//
//    public static Result<?> failed(String message) {
//        return new Result<>(ResultEnum.COMMON_FAILED.getCode(), message, null);
//    }
//
//    public static Result<?> failed(IResult errorResult) {
//        return new Result<>(errorResult.getCode(), errorResult.getMessage(), null);
//    }









}
