package org.talangsoft.rest.devtools.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;

import java.lang.reflect.Type;
import java.util.List;

public class AnEntityDTOMapper{
    ModelMapper modelMapper = new ModelMapper();


    public AnEntityDTOMapper() {
        modelMapper.addMappings(new PropertyMap<AnEntity, ADTO>() {
            protected void configure() {
                map().setText(source.getDescription());
                skip().setDtoOnlyProperty(null);
            }
        });
        modelMapper.addMappings(new PropertyMap<ADTO, AnEntity>() {
            protected void configure() {
                map().setDescription(source.getText());
                skip().setEntityOnlyProperty(null);
            }
        });
    }

    public ADTO toDTO(AnEntity entity){
        return modelMapper.map(entity, ADTO.class);
    }
    public AnEntity toEntity(ADTO dto){
        return modelMapper.map(dto, AnEntity.class);
    }

    public List<ADTO> toDTOs(List<AnEntity> entities){
        return modelMapper.map(entities,  new TypeToken<List<ADTO>>() {}.getType());
    }

    public List<AnEntity> toEntities(List<ADTO> dtos){
        return modelMapper.map(dtos, new TypeToken<List<AnEntity>>() {}.getType());
    }

}
