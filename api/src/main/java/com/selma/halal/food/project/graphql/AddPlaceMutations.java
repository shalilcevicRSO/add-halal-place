package com.selma.halal.food.project.graphql;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import com.selma.halal.food.project.lib.AddPlaceMetadata;
import com.selma.halal.food.project.services.beans.AddPlaceBean;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@GraphQLClass
@ApplicationScoped
public class AddPlaceMutations {
    @Inject
    private AddPlaceBean addPlaceBean;

    @GraphQLMutation
    public AddPlaceMetadata addAddPlaceMetadata(@GraphQLArgument(name = "imageMetadata") AddPlaceMetadata addPlaceMetadata) {
        addPlaceBean.createAddPlaceMetadata(addPlaceMetadata);
        return addPlaceMetadata;
    }

    @GraphQLMutation
    public DeleteResponse deleteAddPlaceMetadata(@GraphQLArgument(name = "id") Integer id) {
        return new DeleteResponse(addPlaceBean.deleteAddPlaceMetadata(id));
    }
}
