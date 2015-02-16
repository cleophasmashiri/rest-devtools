package org.talang.rest.devtools.converter;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class DTOEntityConversionTest {


    AnEntityDTOMapper entityDTOMapper;


    @Before
    public void setUp(){
        entityDTOMapper = new AnEntityDTOMapper();
    }

    @Test
    public void validateMappingTest(){
        entityDTOMapper.modelMapper.validate();
    }


    @Test
    public void convertAnEntityToDTO(){
        AnEntity entity = new AnEntity("name","description","entityOnly");
        ADTO dto = entityDTOMapper.toDTO(entity);
        assertEquals("name", dto.getName());
        assertEquals("description", dto.getText());
        assertEquals(null, dto.getDtoOnlyProperty());
    }


    @Test
    public void convertADTOToEntity(){
        ADTO adto = new ADTO("name","text","dtoOnly");
        AnEntity entity = entityDTOMapper.toEntity(adto);
        assertEquals("name", entity.getName());
        assertEquals("text", entity.getDescription());
        assertEquals(null, entity.getEntityOnlyProperty());
    }

    @Test
    public void convertAListOfEntitiesToDTOs(){
        List<AnEntity> entities = Arrays.asList(
                new AnEntity("name1","description1","entityOnly1"),
                new AnEntity("name2","description2","entityOnly2"));
        List<ADTO> dtos = entityDTOMapper.toDTOs(entities);
        assertThat(dtos.size(),is(2));
        assertEquals("name1", dtos.get(0).getName());
        assertEquals("description1", dtos.get(0).getText());
        assertEquals(null, dtos.get(0).getDtoOnlyProperty());

        assertEquals("name2", dtos.get(1).getName());
        assertEquals("description2", dtos.get(1).getText());
        assertEquals(null, dtos.get(1).getDtoOnlyProperty());
    }

    @Test
    public void convertAListOfDTOsToEntities(){
        List<ADTO> dtos = Arrays.asList(
                new ADTO("name1","text1","dtoOnly1"),
                new ADTO("name2","text2","dtoOnly2"));
        //new ParametrizedTypeI

        List<AnEntity> entities = entityDTOMapper.toEntities(dtos);
        assertThat(entities.size(),is(2));
        assertEquals("name1", entities.get(0).getName());
        assertEquals("text1",  entities.get(0).getDescription());
        assertEquals(null,  entities.get(0).getEntityOnlyProperty());
        assertEquals("name2",  entities.get(1).getName());
        assertEquals("text2",  entities.get(1).getDescription());
        assertEquals(null, entities.get(1).getEntityOnlyProperty());


    }


}
