package com.la.xuecheng.content;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.la.xuecheng.base.model.PageParams;
import com.la.xuecheng.base.model.PageResult;
import com.la.xuecheng.content.mapper.CourseBaseMapper;
import com.la.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.la.xuecheng.content.model.po.CourseBase;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author LA
 * @createDate 2024-06-16-13:51
 * @description
 */
@SpringBootTest
public class CourseBaseMapperTest {

    @Resource
    CourseBaseMapper courseBaseMapper;

    @Test
    public void testCourseBaseMapper(){
        QueryCourseParamsDto queryCourseParamsDto = new QueryCourseParamsDto();
        queryCourseParamsDto.setCourseName("java");

        PageParams pageParams = new PageParams();
        pageParams.setPageNo(3L);
        pageParams.setPageSize(2L);

        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()), CourseBase::getName, queryCourseParamsDto.getCourseName());
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()), CourseBase::getAuditStatus, queryCourseParamsDto.getAuditStatus());
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getPublishStatus()), CourseBase::getStatus, queryCourseParamsDto.getPublishStatus());

        Page<CourseBase> page = new Page<>();
        page.setCurrent(pageParams.getPageNo());
        page.setSize(pageParams.getPageSize());

        Page<CourseBase> result = courseBaseMapper.selectPage(page, queryWrapper);

        PageResult<CourseBase> pageResult = new PageResult<>();
        pageResult.setCount(result.getTotal());
        pageResult.setPageNo(result.getCurrent());
        pageResult.setPageSize(result.getSize());
        pageResult.setItems(result.getRecords());

        System.out.println(pageResult);
    }
}
