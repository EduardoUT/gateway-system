/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.system.controller;

import com.mx.grupogateway.system.dao.SiteDAO;
import com.mx.grupogateway.system.factory.ConnectionFactory;
import com.mx.grupogateway.system.modelo.Site;
import java.util.List;

/**
 *
 * @author eduar
 */
public class SiteController {

    private final SiteDAO siteDAO;

    public SiteController() {
        this.siteDAO = new SiteDAO(new ConnectionFactory().realizarConexion());
    }

    /**
     * Realiza el guardado de un nuevo Site.
     *
     * @param site
     */
    public void guardar(Site site) {
        this.siteDAO.guardar(site);
    }

    /**
     * Consulta el listado de identificadores de Site, de acuerdo al mismo
     * identificador.
     *
     * @param idSite
     * @return
     */
    public List<Long> listarSiteIdentifiers(Long idSite) {
        return this.siteDAO.listarSiteIdentifiers(idSite);
    }
}
