package com.la.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.la.xuecheng.base.exception.CommonError;
import com.la.xuecheng.base.exception.XueChengPlusException;
import com.la.xuecheng.base.model.PageParams;
import com.la.xuecheng.base.model.PageResult;
import com.la.xuecheng.content.mapper.CourseBaseMapper;
import com.la.xuecheng.content.mapper.CourseCategoryMapper;
import com.la.xuecheng.content.mapper.CourseMarketMapper;
import com.la.xuecheng.content.model.dto.AddCourseDto;
import com.la.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.la.xuecheng.content.model.dto.EditCourseDto;
import com.la.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.la.xuecheng.content.model.po.CourseBase;
import com.la.xuecheng.content.model.po.CourseMarket;
import com.la.xuecheng.content.service.CourseBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

import static jdk.nashorn.internal.runtime.Debug.id;

/**
 * @author LA
 * @createDate 2024-06-16-20:57
 * @description
 */
@Slf4j
@Service
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {

    @Resource
    CourseBaseMapper courseBaseMapper;
    @Resource
    CourseMarketMapper courseMarketMapper;
    @Resource
    CourseCategoryMapper courseCategoryMapper;

    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto) {
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

        return pageResult;
    }

    @Transactional
    @Override
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto) {
        CourseBase courseBase = new CourseBase();
        BeanUtils.copyProperties(addCourseDto, courseBase);
        courseBase.setCompanyId(companyId);
        courseBase.setCompanyName("LA GOOD!");
        courseBase.setCreateDate(LocalDateTime.now());
        courseBase.setCreatePeople("LA");
        courseBase.setChangeDate(LocalDateTime.now());
        courseBase.setChangePeople("LA");
        courseBase.setAuditStatus("202002");
        courseBase.setStatus("203001");

        int res = courseBaseMapper.insert(courseBase);
        if(res <= 0){
            XueChengPlusException.cast("插入失败");
        }

        Long id = courseBase.getId();
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(addCourseDto, courseMarket);
        courseMarket.setId(id);
        saveCourseMarket(courseMarket);

        return getCourseBaseInfo(id);
    }

    @Override
    //查询课程详细信息
    public CourseBaseInfoDto getCourseBaseInfo(Long courseId){

        //从课程基本信息表查询
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if(courseBase == null){
            return null;
        }

        //从课程营销表查询
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);

        //组装在一起
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtils.copyProperties(courseBase, courseBaseInfoDto);
        if(courseMarket != null){
            BeanUtils.copyProperties(courseMarket, courseBaseInfoDto);
        }
        //根据分类表查分类名称
        String mtName = courseCategoryMapper.selectById(courseBase.getMt()).getName();
        String stName = courseCategoryMapper.selectById(courseBase.getSt()).getName();
        courseBaseInfoDto.setMtName(mtName);
        courseBaseInfoDto.setStName(stName);
        return courseBaseInfoDto;
    }


    public void saveCourseMarket(CourseMarket courseMarket){

        String charge = courseMarket.getCharge();
        if(charge.isEmpty()){
            XueChengPlusException.cast("收费规则为空");
        }
        //如果收费
        if(charge.equals("201001")){
            if(courseMarket.getPrice() == null || courseMarket.getPrice() <= 0){
                XueChengPlusException.cast("价格参数错误");
            }
        }
        CourseMarket oldCourseMarket = courseMarketMapper.selectById(courseMarket.getId());
        if(oldCourseMarket == null){
            int res = courseMarketMapper.insert(courseMarket);
            if(res <= 0){
                XueChengPlusException.cast("插入market失败");
            }
        }else {
            BeanUtils.copyProperties(courseMarket, oldCourseMarket);
            int res = courseMarketMapper.updateById(oldCourseMarket);
            if(res <= 0){
                XueChengPlusException.cast("更新market失败");
            }
        }
    }

    @Override
    public CourseBaseInfoDto QueryCourseBaseById(Long id){
        return getCourseBaseInfo(id);
    }

    @Transactional
    @Override
    public CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto editCourseDto){
        Long id = editCourseDto.getId();
        CourseBase courseBase = courseBaseMapper.selectById(id);
        if(courseBase == null){
            XueChengPlusException.cast("课程不存在");
        }

        if(!courseBase.getCompanyId().equals(companyId)){
            XueChengPlusException.cast("非本机构不能修改");
        }

        BeanUtils.copyProperties(editCourseDto, courseBase);
        courseBase.setChangeDate(LocalDateTime.now());
        courseBase.setChangePeople("liao");

        int res1 = courseBaseMapper.updateById(courseBase);
        if(res1 <= 0){
            XueChengPlusException.cast("更新课程信息失败");
        }

        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(editCourseDto, courseMarket);
        int res2 = courseMarketMapper.updateById(courseMarket);
        if(res2 <= 0){
            XueChengPlusException.cast("更新营销信息失败");
        }

        return getCourseBaseInfo(id);
    }
}
