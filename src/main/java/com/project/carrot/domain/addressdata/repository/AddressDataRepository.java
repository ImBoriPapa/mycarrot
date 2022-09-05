package com.project.carrot.domain.addressdata.repository;

import com.project.carrot.domain.addressdata.entity.AddressData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressDataRepository extends JpaRepository<AddressData,Long> {

    @Query("SELECT a FROM AddressData a where a.town like  %:town%")
    List<AddressData> findByTown(@Param("town") String town);


}
