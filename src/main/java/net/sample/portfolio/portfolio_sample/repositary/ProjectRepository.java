package net.sample.portfolio.portfolio_sample.repository;

import net.sample.portfolio.portfolio_sample.model.Projects;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepository extends JpaRepository<Projects, Integer> {
  List<Projects> findByProjectName(String projectName);
}