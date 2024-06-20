package com.la.xuecheng.content.model.dto;

import com.la.xuecheng.content.model.po.CourseCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author LA
 * @createDate 2024-06-17-11:02
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseCategoryTreeDto extends CourseCategory implements Serializable {


    private static final long serialVersionUID = 7734389946419864071L;

    List<CourseCategoryTreeDto> childrenTreeNodes;
}
