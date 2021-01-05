package com.selma.halal.food.project.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import com.selma.halal.food.project.lib.AddPlaceMetadata;
import com.selma.halal.food.project.models.converters.AddPlaceMetadataConverter;
import com.selma.halal.food.project.models.entities.AddPlaceMetadataEntity;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
public class AddPlaceBean {

    private Logger log = Logger.getLogger(AddPlaceBean.class.getName());

    @PersistenceContext(unitName = "add-place-metadata-jpa")
    private EntityManager em;

    public List<AddPlaceMetadata> getAddPlaceMetadata() {

        TypedQuery<AddPlaceMetadataEntity> query = em.createNamedQuery(
                "AddPlaceMetadataEntity.getAll", AddPlaceMetadataEntity.class);

        List<AddPlaceMetadataEntity> resultList = query.getResultList();
        return resultList.stream().map(AddPlaceMetadataConverter::toDto).collect(Collectors.toList());

    }

    public List<AddPlaceMetadata> getAddPlaceMetadataFilter(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, AddPlaceMetadataEntity.class, queryParameters).stream()
                .map(AddPlaceMetadataConverter::toDto).collect(Collectors.toList());
    }

    public AddPlaceMetadata getAddPlaceMetadata(Integer id) {

        AddPlaceMetadataEntity addPlaceMetadataEntity = em.find(AddPlaceMetadataEntity.class, id);

        if (addPlaceMetadataEntity == null) {
            throw new NotFoundException();
        }

        AddPlaceMetadata addPlaceMetadata = AddPlaceMetadataConverter.toDto(addPlaceMetadataEntity);
        return addPlaceMetadata;
    }

    public AddPlaceMetadata createAddPlaceMetadata(AddPlaceMetadata addPlaceMetadata) {

        AddPlaceMetadataEntity addPlaceMetadataEntity = AddPlaceMetadataConverter.toEntity(addPlaceMetadata);

        try {
            beginTx();
            em.persist(addPlaceMetadataEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (addPlaceMetadataEntity.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return AddPlaceMetadataConverter.toDto(addPlaceMetadataEntity);
    }

    public AddPlaceMetadata putAddPlaceMetadata(Integer id, AddPlaceMetadata addPlaceMetadata) {

        AddPlaceMetadataEntity c = em.find(AddPlaceMetadataEntity.class, id);

        if (c == null) {
            return null;
        }

        AddPlaceMetadataEntity updateAddPlaceMetadataEntity = AddPlaceMetadataConverter.toEntity(addPlaceMetadata);

        try {
            beginTx();
            updateAddPlaceMetadataEntity.setId(c.getId());
            updateAddPlaceMetadataEntity = em.merge(updateAddPlaceMetadataEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return AddPlaceMetadataConverter.toDto(updateAddPlaceMetadataEntity);
    }

    public boolean deleteAddPlaceMetadata(Integer id) {

        AddPlaceMetadataEntity  addPlaceMetadataEntity= em.find(AddPlaceMetadataEntity.class, id);

        if (addPlaceMetadataEntity != null) {
            try {
                beginTx();
                em.remove(addPlaceMetadataEntity);
                commitTx();
            }
            catch (Exception e) {
                rollbackTx();
            }
        }
        else {
            return  false;
        }

        return true;
    }


    private void beginTx() {
        if(!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }

}
