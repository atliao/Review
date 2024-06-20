package com.la.xuecheng.base.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author LA
 * @createDate 2024-06-20-19:44
 * @description
 */
@Data
@AllArgsConstructor
public class RestErrorResponse implements Serializable {

    private static final long serialVersionUID = 3996586710595401605L;

    private String errMessage;

}
