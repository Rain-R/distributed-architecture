package com.framework.samples.orderapp.base;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/9/8 17:49
 * @since JDK 1.8
 */
import lombok.Data;

@Data
public class ResponseBase {

    private Integer rtnCode;
    private String msg;
    private Object data;

    public ResponseBase() {

    }

    public ResponseBase(Integer rtnCode, String msg, Object data) {
        super();
        this.rtnCode = rtnCode;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseBase [rtnCode=" + rtnCode + ", msg=" + msg + ", data=" + data + "]";
    }

}