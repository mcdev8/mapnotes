package org.launchcode.devops.mapnotesapi.repositories;

import org.launchcode.devops.mapnotesapi.models.Feature.NoteFeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeaturesRepository extends JpaRepository<NoteFeatureEntity, Long> {
}
