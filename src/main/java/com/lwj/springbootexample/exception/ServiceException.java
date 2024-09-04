package com.lwj.springbootexample.exception;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException{

    private Integer code;

    private String message;

    /** 服务器内部错误，无法完成请求 */
    public static final ServiceException INTERNAL_SERVER_ERROR;
    /** 服务器无法根据客户端的请求找到资源 */
    public static final ServiceException NOT_FOUND;
    /** 客户端请求的语法错误，服务器无法理解 */
    public static final ServiceException BAD_REQUEST;
    /** 请求要求用户的身份认证 */
    public static final ServiceException UNAUTHORIZED;
    /** 拒绝执行此请求 */
    public static final ServiceException FORBIDDEN;
    /** 拒绝执行此请求 */
    public static final ServiceException SERVICE_UNAVAILABLE;

    static {
        INTERNAL_SERVER_ERROR = new ServiceException(500, "service.exception.InternalServerError");
        NOT_FOUND = new ServiceException(404, "service.exception.NotFound");
        BAD_REQUEST = new ServiceException(400, "service.exception.BadRequest");
        UNAUTHORIZED = new ServiceException(401, "service.exception.Unauthorized");
        FORBIDDEN = new ServiceException(403, "service.exception.Forbidden");
        SERVICE_UNAVAILABLE = new ServiceException(503, "service.exception.ServiceUnavailable");
    }

    public ServiceException() {
        super();
    }

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
