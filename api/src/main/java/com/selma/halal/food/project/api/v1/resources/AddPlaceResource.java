package com.selma.halal.food.project.api.v1.resources;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.selma.halal.food.project.lib.AddPlaceMetadata;
import com.selma.halal.food.project.services.beans.AddPlaceBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/addplace")
@CrossOrigin(supportedMethods = "GET, POST, HEAD, DELETE, OPTIONS")
public class AddPlaceResource {

    private Logger log = Logger.getLogger(AddPlaceResource.class.getName());

    @Inject
    private AddPlaceBean addPlaceBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getAddPlaceMetadata() {
        List<AddPlaceMetadata> addPlaceMetadata = addPlaceBean.getAddPlaceMetadataFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(addPlaceMetadata).build();

    }

    @GET
    @Path("/{id}")
    public Response getAddPlaceMetadata(@PathParam("id") Integer id) {

        AddPlaceMetadata addPlaceMetadata = addPlaceBean.getAddPlaceMetadata(id);

        if (addPlaceMetadata == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(addPlaceMetadata).build();
    }

    @POST
    public Response createAddPlaceMetadata(AddPlaceMetadata addPlaceMetadata) {

        if ((addPlaceMetadata.getName() == null || addPlaceMetadata.getCity() == null || addPlaceMetadata.getCountry() == null)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            addPlaceMetadata = addPlaceBean.createAddPlaceMetadata(addPlaceMetadata);
        }

        return Response.status(Response.Status.OK).entity(addPlaceMetadata).build();

    }

    @PUT
    @Path("/{id}")
    public Response putAddPlaceMetadata(@PathParam("id") Integer id,
                                          AddPlaceMetadata addPlaceMetadata) {

        addPlaceMetadata = addPlaceBean.putAddPlaceMetadata(id, addPlaceMetadata);

        if (addPlaceMetadata == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.NOT_MODIFIED).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAddPlaceMetadata(@PathParam("id") Integer id) {

        boolean deleted = addPlaceBean.deleteAddPlaceMetadata(id);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
