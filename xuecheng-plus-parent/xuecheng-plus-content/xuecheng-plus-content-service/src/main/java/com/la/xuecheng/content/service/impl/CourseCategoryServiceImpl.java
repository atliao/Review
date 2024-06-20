package com.la.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.la.xuecheng.content.mapper.CourseCategoryMapper;
import com.la.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.la.xuecheng.content.model.po.CourseCategory;
import com.la.xuecheng.content.service.CourseCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author LA
 * @createDate 2024-06-17-13:42
 * @description
 */
@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory> implements CourseCategoryService {

    @Override
    public List<CourseCategoryTreeDto> queryCourseCategoryTree(String id) {
        List<CourseCategory> categoryList = this.list();
        //******************************************************************
        HashMap<String, CourseCategoryTreeDto> hashMap = new HashMap<>();
        List<CourseCategoryTreeDto> result = new ArrayList<>();
        for(CourseCategory category : categoryList){
            CourseCategoryTreeDto dto = new CourseCategoryTreeDto();
            BeanUtils.copyProperties(category, dto);
            hashMap.put(category.getId(), dto);
            if(category.getParentid().equals("1")){
                result.add(dto);
            }
        }
        for(CourseCategory category : categoryList){
            if(category.getParentid().equals("0")){
                continue;
            }
            CourseCategoryTreeDto categoryTreeDto = hashMap.get(category.getParentid());
            if(categoryTreeDto.getChildrenTreeNodes() == null){
                categoryTreeDto.setChildrenTreeNodes(new ArrayList<>());
            }
            categoryTreeDto.getChildrenTreeNodes().add(hashMap.get(category.getId()));
        }
        return result;
        //******************************************************************
        //return treeBuild(id, categoryList);
    }

    public List<CourseCategoryTreeDto> treeBuild(String rootId, List<CourseCategory> categoryList){
        List<CourseCategoryTreeDto> list = new ArrayList<>();
        for(CourseCategory c : categoryList){
            if(rootId.equals(c.getParentid())){
                CourseCategoryTreeDto dto = new CourseCategoryTreeDto();
                BeanUtils.copyProperties(c, dto);
                dto.setChildrenTreeNodes(treeBuild(c.getId(), categoryList));
                list.add(dto);
            }
        }
        return list.size() == 0 ? null : list;
    }
}
