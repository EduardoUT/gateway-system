/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

import java.time.LocalDateTime;

/**
 *
 * @author eduar
 */
public class Project {

    private Long projectId;
    private Site site;
    private String projectCode;
    private String projectName;
    private String customer;
    private String category;
    private LocalDateTime publishDate;

    public Project() {
        this.projectId = Long.MAX_VALUE;
        this.site = new Site();
        this.projectCode = "0";
        this.projectName = "No Name";
        this.category = "None";
        this.publishDate = LocalDateTime.now();
    }

    /**
     * Constructor para la creación de un Proyecto.
     *
     * @param projectId
     * @param site
     * @param projectCode
     * @param projectName
     * @param projectCustomer
     * @param projectCategory
     * @param publishDate
     */
    public Project(Long projectId, Site site, String projectCode, String projectName,
            String projectCustomer, String projectCategory, LocalDateTime publishDate) {
        validateProject(projectId, site, projectCode, projectName,
                projectCustomer, projectCategory, publishDate);
        this.projectId = projectId;
        this.site = site;
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.customer = projectCustomer;
        this.category = projectCategory;
        this.publishDate = publishDate;
    }

    public Project(Long projectId) {
        validateProjectId(projectId);
        this.projectId = projectId;
    }

    /**
     * @return the projectId
     */
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        validateProjectId(projectId);
        this.projectId = projectId;
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

    private void validateProjectId(Long projectId) {
        if (projectId == null) {
            throw new NullPointerException("projectId no puede ser null.");
        }
        if (projectId <= 0 || projectId > Long.MAX_VALUE) {
            throw new IllegalArgumentException("id menor o igual a 0, o mayor "
                    + "al limite permitido.");
        }
    }

    private void validateProjectSite(Site site) {
        if (site == null) {
            throw new NullPointerException("Site no puede ser null.");
        }
    }

    private void validateProjectCode(String projectCode) {
        if (projectCode == null) {
            throw new NullPointerException("projectCode no puede ser null.");
        }
    }

    private void validateProjectName(String projectName) {
        if (projectName == null) {
            throw new NullPointerException("projectName no puede ser null.");
        }
    }

    private void validateCustomer(String projectCustomer) {
        if (projectCustomer == null) {
            throw new NullPointerException("projectCustomer no puede ser null.");
        }
    }

    private void validateCategory(String projectCategory) {
        if (projectCategory == null) {
            throw new NullPointerException("projectCategory no puede ser null.");
        }
    }

    private void validatePublishDate(LocalDateTime publishDate) {
        if (publishDate == null) {
            throw new NullPointerException("publishDate no puede ser null.");
        }
    }

    private void validateProject(Long projectId, Site site, String projectCode,
            String projectName, String customer, String category,
            LocalDateTime publishDate) {
        validateProjectId(projectId);
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
                projectId, projectCode, projectName, customer, category, publishDate);
    }
}
