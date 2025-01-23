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
        siteImpl = new SiteImpl(new ConnectionFactory().realizarConexion());
    }

    /**
     * Realiza el guardado de un nuevo Site.
     *
     * @param site
     */
    public void create(Site site) {
        if (isSiteNotStoredInDatabase(site)) {
            siteImpl.create(site);
        }
    }

    /**
     * Consulta el listado de identificadores de Site, de acuerdo al mismo
     * identificador.
     *
     * @param idSite
     * @return
     */
    public List<Long> getAllById(Long idSite) {
        return siteImpl.getAllById(idSite);
    }

    /**
     * Realiza la consulta de la existencia del identificador de Site en la Base
     * de Datos con el fin de evitar duplicidad antes de ser guardado.
     *
     * @param site
     * @return
     */
    private boolean isSiteNotStoredInDatabase(Site site) {
        List<Long> siteIdentifiers = siteImpl.getAllById(site.getSiteId());
        return siteIdentifiers.isEmpty();
    }
}
