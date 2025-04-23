package com.test.project.infrastructure;

import com.test.project.infrastructure.persist.CogImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CogJpaRepository extends JpaRepository<CogImageData, String> {

    @Query("SELECT c FROM CogImageData c WHERE c.width = :width")
    List<CogImageData> findAllDataByWidth(@Param("width") int width);

    @Query("SELECT c FROM CogImageData c WHERE c.height = :height")
    List<CogImageData> findAllDataByHeight(@Param("height") int height);

    @Query("SELECT c FROM CogImageData c WHERE c.bandCount = :bandCount")
    List<CogImageData> findAllDataByBandCount(@Param("bandCount") int bandCount);

    @Query("SELECT c FROM CogImageData c WHERE c.width = :width AND c.height = :height")
    List<CogImageData> findAllDataByWidthAndHeight(@Param("width") int width,@Param("height") int height);

    @Query("SELECT c FROM CogImageData c WHERE c.compressType = :compressType")
    List<CogImageData> findAllDataByCompressType(@Param("compressType") String compressType);

    @Query("SELECT c FROM CogImageData c ORDER BY c.createdAt DESC")
    List<CogImageData> findAllDataLatest();

}
