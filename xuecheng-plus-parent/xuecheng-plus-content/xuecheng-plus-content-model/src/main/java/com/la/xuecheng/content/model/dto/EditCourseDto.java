package com.la.xuecheng.content.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author LA
 * @createDate 2024-06-22-16:07
 * @description
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EditCourseDto extends AddCourseDto{

    @ApiModelProperty(value = "课程id",required = true)
    private Long id;
}
