package org.metrodataacademy.finalproject.clientapp.controllers;

import org.metrodataacademy.finalproject.clientapp.models.dtos.responses.CourseDetailsResponse;
import org.metrodataacademy.finalproject.clientapp.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CourseControllerThymeleaf {

    @Autowired
    private CourseService courseService;

    @GetMapping(
        path = "/course/details/{title}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String courseDetailsView(@PathVariable String title, Model model) {
        CourseDetailsResponse courseDetailsResponse = courseService.courseDetails(title);
        model.addAttribute("course", courseDetailsResponse);
        return "pages/user/course-details";
    }

    @GetMapping(
        path = "/my-course",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String myCourseView(Model model){
        model.addAttribute("isActive", "course");
        return "pages/user/my-course";
    }
    
}