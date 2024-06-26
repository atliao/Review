package com.la.xuecheng.content.api;

import com.la.xuecheng.base.exception.ValidationGroups;
import com.la.xuecheng.base.model.PageParams;
import com.la.xuecheng.base.model.PageResult;
import com.la.xuecheng.content.model.dto.AddCourseDto;
import com.la.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.la.xuecheng.content.model.dto.EditCourseDto;
import com.la.xuecheng.content.model.po.CourseBase;
import com.la.xuecheng.content.service.CourseBaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author LA
 * @createDate 2024-06-16-20:30
 * @description
 */
@Api(value = "课程信息管理接口", tags = "课程信息管理接口")
@RestController
public class CourseBaseInfoController {

    @Resource
    CourseBaseInfoService courseBaseInfoService;

    @ApiOperation("课程分页查询")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody(required = false) com.la.xuecheng.content.model.dto.QueryCourseParamsDto queryCourseParamsDto){
        PageResult<CourseBase> pageResult = courseBaseInfoService.queryCourseBaseList(pageParams, queryCourseParamsDto);
        return pageResult;
    }

    @ApiOperation("新增课程")
    @PostMapping("/course")
    public CourseBaseInfoDto createCourseBase(@RequestBody @Validated(/*ValidationGroups.Insert.class*/) AddCourseDto addCourseDto){
        Long companyId = 15184335457L;
        return courseBaseInfoService.createCourseBase(companyId, addCourseDto);
    }

    @ApiOperation("id查询课程")
    @GetMapping("/course/{id}")
    public CourseBaseInfoDto updateCourseBase(@PathVariable("id") Long id){

        return courseBaseInfoService.QueryCourseBaseById(id);
    }

    @ApiOperation("修改课程")
    @PutMapping("/course")
    public CourseBaseInfoDto updateCourseBase(@RequestBody @Validated(/*ValidationGroups.Update.class*/) EditCourseDto editCourseDto){
        Long companyId = 15184335457L;
        return courseBaseInfoService.updateCourseBase(companyId, editCourseDto);
    }

}
