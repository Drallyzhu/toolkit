package com.drally.toolkit.domain.gateway.log;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName LogModel
 * @Description TODO
 * @Author gm
 * @Date 2019/3/22 15:38
 **/
@Data
public class LogModel implements Serializable {
    private static final long serialVersionUID = -9011750804345256025L;

    private String method;

    private String path;

    private String ip;

    private String params;

    private String body;

    private String requestTime;

    private String outBody;

}
