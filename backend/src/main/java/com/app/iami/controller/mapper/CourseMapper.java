package com.app.iami.controller.mapper;

import com.app.iami.controller.dto.CourseDto;
import com.app.iami.model.Course;
import com.app.iami.controller.dto.TeacherDto;

import java.util.List;
import java.util.stream.Collectors;

public class CourseMapper {

    public static List<CourseDto> mapToCourseDtos(List<Course> courses){
        return courses.stream().map(course -> mapToCourseDto(course)).collect(Collectors.toList());
    }

    public static CourseDto mapToCourseDto(Course course){

        TeacherDto teacher = TeacherDto.builder()
                .id(course.getTeacher().getId())
                .name(course.getTeacher().getName())
                .surname(course.getTeacher().getSurname())
                .build();

        return CourseDto.builder()
                .id(course.getId())
                .name(course.getName())
                .teacher(teacher)
                .subject(course.getSubject())
                .students(course.getStudents())
                .checkingForms(course.getCheckingForms())
                .day(course.getDay())
                .build();
    }

}
