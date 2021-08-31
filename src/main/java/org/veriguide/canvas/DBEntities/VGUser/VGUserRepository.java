package org.veriguide.canvas.DBEntities.VGUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VGUserRepository extends JpaRepository<VGUser, Long> {
    Optional<VGUser> findVGUserById(Long id);
    Optional<VGUser> findVGUserByUserId(String userId);
    Optional<VGUser> findVGUserByUserName(String userName);

    @Override
    List<VGUser> findAll();
}
