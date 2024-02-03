package com.example.demo.repository;


import com.example.demo.entities.UserLocation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
  Optional<UserLocation> findByUserId(String userId);

  List<UserLocation> findTopNByUserIdOrderByTimeStampDesc(String userId);

  List<UserLocation> findAllByUserIdOrderByTimeStampDesc(String userId);

  boolean existsByUserId(String userId);
}
