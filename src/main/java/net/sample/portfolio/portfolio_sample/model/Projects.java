package net.sample.portfolio.portfolio_sample.model;

import javax.persistence.*;

@Entity
@Table(name = "projects")
public class Projects
{
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

    @Column(name = "projectName")
    private String projectName;
    
    public Projects() {

	}

	public Projects(String projectName) {
		this.projectName = projectName;

	}

	public int getId() {
		return id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String name) {
		this.projectName = name;
    }
    
    public String toString() {
		return "Project ID = " + id + ", Name = " + projectName;
	}
}