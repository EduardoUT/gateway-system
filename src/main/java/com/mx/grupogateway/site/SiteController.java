/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mx.grupogateway.site;

import com.mx.grupogateway.factory.ConnectionFactory;
import java.util.List;

/**
 *
 * @author eduar
 */
public class SiteController {

    private final SiteImpl siteImpl;

    public SiteController() {
        this.siteImpl = new SiteImpl(new ConnectionFactory().realizarConexion());
    }

    /**
     * Realiza el guardado de un nuevo Site.
     *
     * @param site
     */
    public void guardar(Site site) {
        this.siteImpl.create(site);
    }

    /**
     * Consulta el listado de identificadores de Site, de acuerdo al mismo
     * identificador.
     *
     * @param idSite
     * @return
     */
    public List<Long> listarSiteIdentifiers(Long idSite) {
        return this.siteImpl.getAllById(idSite);
    }
}
