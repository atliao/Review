package com.la.xuecheng.content.model.dto;

import com.la.xuecheng.content.model.po.Teachplan;
import com.la.xuecheng.content.model.po.TeachplanMedia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author LA
 * @createDate 2024-06-29-16:18
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SaveTeachPlanDto {

    /***
     * 教学计划id
     */
    private Long id;

    /**
     * 课程计划名称
     */
    private String pname;

    /**
     * 课程计划父级Id
     */
    private Long parentid;

    /**
     * 层级，分为1、2、3级
     */
    private Integer grade;

    /**
     * 课程类型:1视频、2文档
     */
    private String mediaType;


    /**
     * 课程标识
     */
    private Long courseId;

    /**
     * 课程发布标识
     */
    private Long coursePubId;


    /**
     * 是否支持试学或预览（试看）
     */
    private String isPreview;
}
