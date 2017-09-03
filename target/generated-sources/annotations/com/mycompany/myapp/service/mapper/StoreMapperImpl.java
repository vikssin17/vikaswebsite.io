package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Store;

import com.mycompany.myapp.service.dto.StoreDTO;

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

public class StoreMapperImpl implements StoreMapper {

    @Override

    public Store toEntity(StoreDTO dto) {

        if ( dto == null ) {

            return null;
        }

        Store store = new Store();

        store.setId( dto.getId() );

        store.setFeeamount( dto.getFeeamount() );

        store.setComment( dto.getComment() );

        return store;
    }

    @Override

    public StoreDTO toDto(Store entity) {

        if ( entity == null ) {

            return null;
        }

        StoreDTO storeDTO = new StoreDTO();

        storeDTO.setId( entity.getId() );

        storeDTO.setFeeamount( entity.getFeeamount() );

        storeDTO.setComment( entity.getComment() );

        return storeDTO;
    }

    @Override

    public List<Store> toEntity(List<StoreDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<Store> list = new ArrayList<Store>();

        for ( StoreDTO storeDTO : dtoList ) {

            list.add( toEntity( storeDTO ) );
        }

        return list;
    }

    @Override

    public List<StoreDTO> toDto(List<Store> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<StoreDTO> list = new ArrayList<StoreDTO>();

        for ( Store store : entityList ) {

            list.add( toDto( store ) );
        }

        return list;
    }
}

