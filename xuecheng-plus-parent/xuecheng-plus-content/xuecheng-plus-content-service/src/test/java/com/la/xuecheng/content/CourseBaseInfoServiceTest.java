package com.la.xuecheng.content;

import com.la.xuecheng.base.model.PageParams;
import com.la.xuecheng.base.model.PageResult;
import com.la.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.la.xuecheng.content.model.po.CourseBase;
import com.la.xuecheng.content.service.CourseBaseInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author LA
 * @createDate 2024-06-16-13:51
 * @description
 */
@SpringBootTest
public class CourseBaseInfoServiceTest {

    @Resource
    CourseBaseInfoService courseBaseInfoService;

    @Test
    public void testCourseBaseMapper(){
        QueryCourseParamsDto queryCourseParamsDto = new QueryCourseParamsDto();
        queryCourseParamsDto.setCourseName("java");

        PageParams pageParams = new PageParams();
        pageParams.setPageNo(3L);
        pageParams.setPageSize(2L);

        PageResult<CourseBase> pageResult = courseBaseInfoService.queryCourseBaseList(pageParams, queryCourseParamsDto);

        System.out.println(pageResult);
    }
}
