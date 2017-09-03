package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Checkfee;

import com.mycompany.myapp.service.dto.CheckfeeDTO;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2017-08-26T11:17:25+0530",

    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"

)

@Component

public class CheckfeeMapperImpl implements CheckfeeMapper {

    @Override

    public Checkfee toEntity(CheckfeeDTO dto) {

        if ( dto == null ) {

            return null;
        }

        Checkfee checkfee = new Checkfee();

        checkfee.setId( dto.getId() );

        checkfee.setFeeamount( dto.getFeeamount() );

        checkfee.setReason( dto.getReason() );

        checkfee.setStatus( dto.getStatus() );

        return checkfee;
    }

    @Override

    public CheckfeeDTO toDto(Checkfee entity) {

        if ( entity == null ) {

            return null;
        }

        CheckfeeDTO checkfeeDTO = new CheckfeeDTO();

        checkfeeDTO.setId( entity.getId() );

        checkfeeDTO.setFeeamount( entity.getFeeamount() );

        checkfeeDTO.setReason( entity.getReason() );

        checkfeeDTO.setStatus( entity.getStatus() );

        return checkfeeDTO;
    }

    @Override

    public List<Checkfee> toEntity(List<CheckfeeDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<Checkfee> list = new ArrayList<Checkfee>();

        for ( CheckfeeDTO checkfeeDTO : dtoList ) {

            list.add( toEntity( checkfeeDTO ) );
        }

        return list;
    }

    @Override

    public List<CheckfeeDTO> toDto(List<Checkfee> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<CheckfeeDTO> list = new ArrayList<CheckfeeDTO>();

        for ( Checkfee checkfee : entityList ) {

            list.add( toDto( checkfee ) );
        }

        return list;
    }
}

