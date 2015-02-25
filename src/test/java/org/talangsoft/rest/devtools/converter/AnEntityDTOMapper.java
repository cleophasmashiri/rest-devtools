package org.talangsoft.rest.devtools.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class AnEntityDTOMapper{

    ModelMapper modelMapper;

    public AnEntityDTOMapper() {
        modelMapper = new ModelMapper();
        modelMapper.addMappings(new ToDTOMap());
        modelMapper.addMappings(new ToEntityMap());
    }

    class ToDTOMap extends PropertyMap<AnEntity,ADTO> {
        protected void configure() {
            map().setText(source.getDescription());
            skip().setDtoOnlyProperty(null);
        }
    }

    class ToEntityMap extends PropertyMap<ADTO,AnEntity> {
        protected void configure() {
            map().setDescription(source.getText());
            skip().setEntityOnlyProperty(null);
        }
    }

    public ADTO toDTO(AnEntity entity){
        ADTO dto = modelMapper.map(entity, ADTO.class);
        return dto;
    }
    public AnEntity toEntity(ADTO dto){
        AnEntity entity = modelMapper.map(dto, AnEntity.class);
        return entity;
    }


    public List<ADTO> toDTOs(List<AnEntity> entities){
        Type listType = new TypeToken<List<ADTO>>() {}.getType();
        List<ADTO> dtos = modelMapper.map(entities, listType);
        return dtos;
    }

    public List<AnEntity> toEntities(List<ADTO> dtos){
        Type listType = new TypeToken<List<AnEntity>>() {}.getType();
        List<AnEntity> entities = modelMapper.map(dtos, listType);
        return entities;
    }



}
