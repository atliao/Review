package com.la.xuecheng.content.service;

import com.la.xuecheng.content.model.dto.CourseCategoryTreeDto;

import java.util.List;

/**
 * @author LA
 * @createDate 2024-06-17-14:06
 * @description
 */
public interface CourseCategoryService {

    List<CourseCategoryTreeDto> queryCourseCategoryTree(String id);
}
