/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author eduar
 */
public class main {
    
    public static void main(String[] args) {
        
        /*Project project = new Project(Long.MAX_VALUE + 1);
        System.out.println(project.getProjectId());
        */
        Site site = new Site(200000213693002L);
        System.out.println(site.hashCode());
        Site siteTwo = new Site(200000213138002L);
        System.out.println(siteTwo.hashCode());
        Site siteThree = new Site(200000213693002L);
        System.out.println(siteThree.hashCode());
        Set hashSet = new HashSet();
        hashSet.add(site);
        hashSet.add(siteTwo);
        hashSet.add(siteThree);
        for(Object siteI : hashSet) {
            Site otherSite = (Site) siteI; 
            System.out.println(otherSite.getSiteId() + " " + otherSite.hashCode());
        }
        
        Project project = new Project();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd hh:mm:ss");
        
        
        
        System.out.println(project.getPublishDate());
    }
}
