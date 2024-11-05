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
        this.projectId = 0L;
        this.site = new Site(0L);
        this.projectCode = "0";
        this.projectName = "Unsigned";
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
        this.projectId = projectId;
        this.site = site;
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.customer = projectCustomer;
        this.category = projectCategory;
        this.publishDate = publishDate;
    }

    public Project(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the projectId
     */
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
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
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return String.format("Project ID: %d | Project Code: %s | "
                + "Project Name: %s | Customer: %s | Category: %s | Publish Date: %s",
                projectId, projectCode, projectName, customer, category, publishDate);
    }
}
