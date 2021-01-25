package net.sample.portfolio.portfolio_sample.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.sample.portfolio.portfolio_sample.repository.ProjectRepository;
import net.sample.portfolio.portfolio_sample.model.Projects;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api")
public class ProjectController {

  @Autowired
  ProjectRepository projectRepository;

  @GetMapping("/projects")
  public ResponseEntity<List<Projects>> getAllProjectss() {
    try {
      List<Projects> projects = new ArrayList<Projects>();

      
        projectRepository.findAll().forEach(projects::add);
     

      if (projects.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      //System.out.println(projects.get(0));

      return new ResponseEntity<>(projects, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/projects/{id}")
  public ResponseEntity<Projects> getProjectById(@PathVariable("id") int id) {
    Optional<Projects> projectsData = projectRepository.findById(id);

    if (projectsData.isPresent()) {
      return new ResponseEntity<>(projectsData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/projects")
  public ResponseEntity<Projects> createProjects(@RequestBody Projects projects) {
    try {
      Projects _projects= projectRepository
          .save(new Projects(projects.getProjectName()));
      return new ResponseEntity<>(_projects, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/projects/{id}")
  public ResponseEntity<HttpStatus> deleteProjects(@PathVariable("id") int id) {
    try {
      projectRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/projects/{id}")
  public ResponseEntity<Projects> updateProjects(@PathVariable("id") int id, @RequestBody Projects projects) {
    Optional<Projects> projectsData = projectRepository.findById(id);

    if (projectsData.isPresent()) {
      Projects _projects = projectsData.get();
      _projects.setProjectName(projects.getProjectName());
      return new ResponseEntity<>(projectRepository.save(_projects), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }


}
