package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CheckfeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Checkfee and its DTO CheckfeeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CheckfeeMapper extends EntityMapper <CheckfeeDTO, Checkfee> {
    
    

}
