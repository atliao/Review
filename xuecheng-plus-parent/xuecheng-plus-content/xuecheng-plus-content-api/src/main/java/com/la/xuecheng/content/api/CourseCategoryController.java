package com.la.xuecheng.content.api;

import com.la.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.la.xuecheng.content.service.CourseCategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author LA
 * @createDate 2024-06-17-11:57
 * @description
 */
@Slf4j
@RestController
public class CourseCategoryController {

    @Resource
    CourseCategoryService courseCategoryService;

    @ApiOperation("课程树")
    @GetMapping("/course-category/tree-nodes")
    public List<CourseCategoryTreeDto> queryTreeNode(){
        List<CourseCategoryTreeDto> list = courseCategoryService.queryCourseCategoryTree("1");
        return list;
    }
}
