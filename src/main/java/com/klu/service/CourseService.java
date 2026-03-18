package com.klu.service;

import com.klu.model.Course;
import com.klu.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    public Course addCourse(Course course) {
        return repo.save(course);
    }

    public List<Course> getAllCourses() {
        return repo.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return repo.findById(id);
    }

    public Optional<Course> updateCourse(Long id, Course courseDetails) {

        Optional<Course> courseOpt = repo.findById(id);
        if (courseOpt.isEmpty()) {
            return Optional.empty();
        }

        Course course = courseOpt.get();

        course.setTitle(courseDetails.getTitle());
        course.setDuration(courseDetails.getDuration());
        course.setFee(courseDetails.getFee());

        return Optional.of(repo.save(course));
    }

    public boolean deleteCourse(Long id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }

    public List<Course> searchByTitle(String title) {
        return repo.findByTitleContainingIgnoreCase(title == null ? "" : title.trim());
    }
}