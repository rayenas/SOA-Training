package webservices;

import entities.UniteEnseignement;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;
import entities.Module;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ue")
public class UEResources {
    // instance => object
    UniteEnseignementBusiness helper = new UniteEnseignementBusiness();
    //ws: list UEs

//1) Création d’une nouvelle unité d’enseignement
     @POST
     @Consumes(MediaType.APPLICATION_XML)
     @Produces(MediaType.APPLICATION_XML)
     public Response createUE(UniteEnseignement ue) {

         helper.addUniteEnseignement(ue);
         return Response.status(Response.Status.CREATED).entity(ue).build();
}
//2)Récupération de la liste de toutes les unités d’enseignements

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getlist(){
        return Response
                .status(200)
                .entity(this.helper.getListeUE())
                .build();
    }


//3) Récupération de la liste des unités d’enseignements d’un semestre spécifique
    @GET
    @Path("/semestre={semestre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getlistsemester(@PathParam("semestre") int semestre){
        return Response
                .status(200)
                .entity(this.helper.getUEBySemestre(semestre))
                .build();
    }
//4) Suppression d’une unité d’enseignement ayant un identifiant spécifique
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUE(@PathParam("id") int id){
        return Response
                .status(200)
                .entity(this.helper.deleteUniteEnseignement(id))
                .build();
    }
//5) Modification d’une unité d’enseignement ayant un identifiant spécifique
    @PUT
    @Path("/UE/{code}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response updateUE(@PathParam("code") int code, UniteEnseignement updatedUE) {
       boolean updated = helper.updateUniteEnseignement(code, updatedUE);

      if (updated) {
          return Response.status(200).entity(updatedUE).build();
      } else {
          return Response.status(404).build();
      }
    }
//6) Récupération d’une unité d’enseignement ayant un code donné
    @GET
    @Path("/code={code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response UEByCode(@PathParam("code") int code){
        return Response
                .status(200)
                .entity(this.helper.getUEByCode(code))
                .build();
    }

//B. Ressource Module

    ModuleBusiness MB = new ModuleBusiness();

//1) Création d’un nouveau Module
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addModule(Module module){
        MB.addModule(module);
        return Response
                .status(201)
                .entity(module)
                .build();
    }
//2) Récupération de la liste de tous le modules
    @Path("/modules")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getmodules(){
        return Response
                .status(200)
                .entity(this.MB.getAllModules())
                .build();
    }

//3) Récupération d’unité d’enseignement ayant un identifiant spécifique
    @GET
    @Path("/Matricule={Matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response UEByCode(@PathParam("Matricule") String Matricule){
        return Response
                .status(200)
                .entity(this.MB.getModuleByMatricule(Matricule))
                .build();
    }


//4) Suppression d’un module ayant une matricule spécifique
    @DELETE
    @Path("/modules/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMO(@PathParam("matricule") String matricule){
        return Response
                .status(200)
                .entity(this.MB.deleteModule(matricule))
                .build();
    }
//5) Modification d’un module ayant une matricule spécifique
    @PUT
    @Path("/modules/{matricule}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateModule(@PathParam("matricule") String matricule, Module updatedModule) {
        boolean updated = MB.updateModule(matricule, updatedModule);

        if (updated) {
            return Response.status(200).entity(updatedModule).build();
        } else {
            return Response.status(404).build();
        }
    }
//6) Récupérer tous les modules pédagogiques associés à une Unité d'Enseignement (UE) spécifique,
//identifiée par son code (codeUE).
    @GET
    @Path("/modules/UE")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByUE(@QueryParam("codeUE") int codeUE){
        // Crée un objet UniteEnseignement avec juste le code
        UniteEnseignement ue = new UniteEnseignement();
        ue.setCode(codeUE);  // tu remplis le code seulement

        // Appelle ta méthode existante
        return Response
                .status(200)
                .entity(this.MB.getModulesByUE(ue))
                .build();
    }













}
