package com.finalproject.eshop.config;

import com.finalproject.eshop.entity.Country;
import com.finalproject.eshop.entity.Product;
import com.finalproject.eshop.entity.ProductCategory;
import com.finalproject.eshop.entity.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] disabledActions = {HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.POST};

        //disabling HTTP methods for our Products: PUT, Post, Delete
        disableHttpMethods(Product.class, config, disabledActions);
        disableHttpMethods(ProductCategory.class, config, disabledActions);
        disableHttpMethods(Country.class, config, disabledActions);
        disableHttpMethods(State.class, config, disabledActions);

        exposeIds(config);
    }

    private static void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] disabledActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(disabledActions))
                .withCollectionExposure((metdata, HttpMethods) -> HttpMethods.disable(disabledActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {

        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        List<Class> entityClasses = new ArrayList<>();

        for (EntityType tempEntityType : entities){
            entityClasses.add(tempEntityType.getJavaType());

            Class[] domainTypes = entityClasses.toArray(new Class[0]);
            config.exposeIdsFor(domainTypes);
        }
    }
}
