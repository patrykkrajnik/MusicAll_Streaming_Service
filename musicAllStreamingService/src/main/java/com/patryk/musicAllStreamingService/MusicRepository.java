package com.patryk.musicAllStreamingService;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends CrudRepository<MusicEntity, Integer> {
    List<MusicEntity> findBySongName(String songName);
}
