/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.prueba.servicios;

import com.edu.prueba.entidades.alumno;
import com.edu.prueba.facades.alumnoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Toshiba
 */
@Path("alumnos")
public class alumnoREST {
    
    @EJB
    private alumnoFacade alumnoEJB;
    
    private EntityManager em;
    
    private EntityManagerFactory emf;
    
    protected EntityManager getEntityManager(){
        return em;
    }
	
    /**
     *
     * @return
     */
    @GET
    @Path("prueba")
    public List<alumno> findAll()
    {
        return alumnoEJB.findAll();
    }
    
    @POST
    @Path("pruebaapi")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<alumno> pruebaAPI(DtoPrueba dtoPrueba) {   
         
        emf = Persistence.createEntityManagerFactory("com.edu.prueba_Backend_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT a FROM alumno a WHERE a.id = :id")
                .setParameter("id", dtoPrueba.id);
        List<alumno> lista = query.getResultList();
	return lista;
    }
    
    @POST
    @Path("pruebaapi2")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<alumno> pruebaapi2( ) {   
         
        emf = Persistence.createEntityManagerFactory("com.edu.prueba_Backend_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT a FROM alumno a");
        List<alumno> lista = query.getResultList();
	return lista;
    }
    
    @POST
    @Path("pruebaapi3")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<alumno> pruebaapi3( ) {   
         
        cargarEntityManager();
        
        TypedQuery<alumno> query = em.createNamedQuery("Alumno.findAll", alumno.class);
            List<alumno> results = query.getResultList();
	return results;
    }
    
    public void cargarEntityManager(){
        emf = Persistence.createEntityManagerFactory("com.edu.prueba_Backend_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }
    
}

