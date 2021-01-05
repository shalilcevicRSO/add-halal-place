package com.selma.halal.food.project.models.converters;

import com.selma.halal.food.project.lib.AddPlaceMetadata;
import com.selma.halal.food.project.models.entities.AddPlaceMetadataEntity;

public class AddPlaceMetadataConverter {

    public static AddPlaceMetadata toDto(AddPlaceMetadataEntity entity) {

        AddPlaceMetadata dto = new AddPlaceMetadata();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setStreetName(entity.getStreetName());
        dto.setStreetNumber(entity.getStreetNumber());
        dto.setCity(entity.getCity());
        dto.setCountry(entity.getCountry());
        dto.setZipCode(entity.getZipCode());
        dto.setType(entity.getType());
        dto.setDescription(entity.getDescription());
        dto.setUri(entity.getUri());
        dto.setDateCreated(entity.getDateCreated());

        return dto;
    }

    public static AddPlaceMetadataEntity toEntity(AddPlaceMetadata dto) {
        AddPlaceMetadataEntity entity = new AddPlaceMetadataEntity();
        entity.setName(dto.getName());
        entity.setStreetName(dto.getStreetName());
        entity.setStreetNumber(dto.getStreetNumber());
        entity.setCity(dto.getCity());
        entity.setCountry(dto.getCountry());
        entity.setZipCode(dto.getZipCode());
        entity.setType(dto.getType());
        entity.setDescription(dto.getDescription());
        entity.setUri(dto.getUri());
        entity.setDateCreated(dto.getDateCreated());

        return entity;
    }
}
