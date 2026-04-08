package fit5120.monash.edu.common.exception;

import fit5120.monash.edu.common.result.RespEnum;
import lombok.Getter;

@Getter
public class ResourcesNotFoundException extends RuntimeException {

    private final int code;

    public ResourcesNotFoundException(RespEnum respEnum) {
        super(respEnum.getMessage());
        this.code = respEnum.getCode();
    }

}
