package com.la.xuecheng.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author LA
 * @createDate 2024-06-15-11:31
 * @description 分页查询结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageResult<T> implements Serializable {

    //数据列表
    private List<T> items;

    //查询总数
    private Long count;

    //当前页数
    private Long pageNo;

    //每页大小
    private Long pageSize;
}
