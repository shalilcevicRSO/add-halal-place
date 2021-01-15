package com.selma.halal.food.project.graphql;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import com.kumuluz.ee.graphql.classes.Filter;
import com.kumuluz.ee.graphql.classes.Pagination;
import com.kumuluz.ee.graphql.classes.PaginationWrapper;
import com.kumuluz.ee.graphql.classes.Sort;
import com.kumuluz.ee.graphql.utils.GraphQLUtils;
import com.selma.halal.food.project.lib.AddPlaceMetadata;
import com.selma.halal.food.project.services.beans.AddPlaceBean;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@GraphQLClass
@ApplicationScoped
public class AddPlaceQueries {


        @Inject
        private AddPlaceBean addPlaceBean;

        @GraphQLQuery
        public PaginationWrapper<AddPlaceMetadata> allAddPlaceMetadata(@GraphQLArgument(name = "pagination") Pagination pagination,
                                                                    @GraphQLArgument(name = "sort") Sort sort,
                                                                    @GraphQLArgument(name = "filter") Filter filter) {

            return GraphQLUtils.process(addPlaceBean.getAddPlaceMetadata(), pagination, sort, filter);
        }

        @GraphQLQuery
        public AddPlaceMetadata geAddPlaceMetadata(@GraphQLArgument(name = "id") Integer id) {
            return addPlaceBean.getAddPlaceMetadata(id);
        }

    }
