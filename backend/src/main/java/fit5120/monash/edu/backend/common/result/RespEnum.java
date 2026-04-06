package fit5120.monash.edu.backend.common.result;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum RespEnum {
    SUCCESS(200, "success"),
    NOT_FOUND(404, "resources not found"),
    SERVICE_ERROR(500, "Service have problem"),

    ;

    private Integer code;
    private String message;

}
