/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.project;

import com.mx.grupogateway.site.Site;
import java.time.LocalDateTime;
import java.util.Objects;
import static com.mx.grupogateway.exception.IllegalArgumentExceptionTypeMessage.*;

/**
 *
 * @author eduar
 */
public class Project {

    private static final String DEFAULT_PROJECT_CODE = "No Project Code";
    private static final String DEFAULT_PROJECT_NAME = "No Project Name";
    private static final String DEFAULT_CUSTOMER = "No Customer Info";
    private static final String DEFAULT_CATEGORY = "No Category Info";
    private Long id;
    private Site site;
    private String projectCode;
    private String projectName;
    private String customer;
    private String category;
    private LocalDateTime publishDate;

    public Project() {
        site = new Site();
        projectCode = DEFAULT_PROJECT_CODE;
        projectName = DEFAULT_PROJECT_NAME;
        category = DEFAULT_CATEGORY;
        publishDate = LocalDateTime.now();
    }

    public Project(Long projectId) {
        this();
        validateId(projectId);
        this.id = projectId;
    }

    /**
     * Constructor para la creación de un Proyecto.
     *
     * @param id
     * @param site
     * @param projectCode
     * @param projectName
     * @param projectCustomer
     * @param projectCategory
     * @param publishDate
     */
    public Project(Long id, Site site, String projectCode, String projectName,
            String projectCustomer, String projectCategory, LocalDateTime publishDate) {
        validateProject(id, site, projectCode, projectName,
                projectCustomer, projectCategory, publishDate);
        this.id = id;
        this.site = site;
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.customer = projectCustomer;
        this.category = projectCategory;
        this.publishDate = publishDate;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        validateId(id);
        this.id = id;
    }

    /**
     * @return the site
     */
    public Site getSite() {
        return site;
    }

    /**
     * @param site the site to set
     */
    public void setSite(Site site) {
        validateProjectSite(site);
        this.site = site;
    }

    /**
     * @return the projectCode
     */
    public String getProjectCode() {
        return projectCode;
    }

    /**
     * @param projectCode the projectCode to set
     */
    public void setProjectCode(String projectCode) {
        validateProjectCode(projectCode);
        this.projectCode = projectCode;
    }

    /**
     * @return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the projectName to set
     */
    public void setProjectName(String projectName) {
        validateProjectName(projectName);
        this.projectName = projectName;
    }

    /**
     * @return the customer
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(String customer) {
        validateCustomer(customer);
        this.customer = customer;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        validateCategory(category);
        this.category = category;
    }

    /**
     * @return the publishDate
     */
    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    /**
     * @param publishDate the publishDate to set
     */
    public void setPublishDate(LocalDateTime publishDate) {
        validatePublishDate(publishDate);
        this.publishDate = publishDate;
    }

    private void validateId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
        if (id <= 0 || id > Long.MAX_VALUE) {
            throw new IllegalArgumentException(LESS_THAN_ZERO_OR_MAX_EXCEDED_MESSAGE.toString());
        }
    }

    private void validateProjectSite(Site site) {
        if (site == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
    }

    private void validateProjectCode(String projectCode) {
        if (projectCode == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
        if (projectCode.isEmpty()) {
            this.projectCode = DEFAULT_PROJECT_CODE;
        }
    }

    private void validateProjectName(String projectName) {
        if (projectName == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
        if (projectName.isEmpty()) {
            this.projectName = DEFAULT_PROJECT_NAME;
        }
    }

    private void validateCustomer(String customer) {
        if (customer == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
        if (customer.isEmpty()) {
            this.customer = DEFAULT_CUSTOMER;
        }
    }

    private void validateCategory(String category) {
        if (category == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
        if (category.isEmpty()) {
            this.category = DEFAULT_CATEGORY;
        }
    }

    private void validatePublishDate(LocalDateTime publishDate) {
        if (publishDate == null) {
            throw new IllegalArgumentException(NULL_VALUE_MESSAGE.toString());
        }
    }

    private void validateProject(Long projectId, Site site, String projectCode,
            String projectName, String customer, String category,
            LocalDateTime publishDate) {
        validateId(projectId);
        validateProjectSite(site);
        validateProjectCode(projectCode);
        validateProjectName(projectName);
        validateCustomer(customer);
        validateCategory(category);
        validatePublishDate(publishDate);
    }

    @Override
    public String toString() {
        return String.format("Project ID: %d | Project Code: %s | "
                + "Project Name: %s | Customer: %s | Category: %s | Publish Date: %s",
                id, projectCode, projectName, customer, category, publishDate);
    }

    /**
     * Comparación por referencia, nombre de clase e identificador único.
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Project otherProject = (Project) object;
        return Objects.equals(id, otherProject.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
