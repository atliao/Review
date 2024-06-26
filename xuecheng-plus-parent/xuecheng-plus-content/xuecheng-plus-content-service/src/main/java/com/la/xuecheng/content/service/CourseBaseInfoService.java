package com.la.xuecheng.content.service;

import com.la.xuecheng.base.model.PageParams;
import com.la.xuecheng.base.model.PageResult;
import com.la.xuecheng.content.model.dto.AddCourseDto;
import com.la.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.la.xuecheng.content.model.dto.EditCourseDto;
import com.la.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.la.xuecheng.content.model.po.CourseBase;

/**
 * @author LA
 * @createDate 2024-06-16-20:57
 * @description
 */
public interface CourseBaseInfoService {
    //课程分页查询
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);

    //新增课程
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);

    //查询课程详细信息
    CourseBaseInfoDto getCourseBaseInfo(Long courseId);

    //id查询课程
    public CourseBaseInfoDto QueryCourseBaseById(Long id);

    //修改课程
    CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto editCourseDto);
}
