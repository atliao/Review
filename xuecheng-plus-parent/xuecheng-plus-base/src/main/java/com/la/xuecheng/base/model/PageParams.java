package com.la.xuecheng.base.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author LA
 * @createDate 2024-06-16-20:31
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageParams {

    @ApiModelProperty("分页页码")
    private Long pageNo = 1L;
    @ApiModelProperty("每页记录数")
    private Long pageSize = 30L;


}
