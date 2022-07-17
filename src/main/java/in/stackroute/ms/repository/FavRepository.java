package in.stackroute.ms.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.stackroute.ms.model.Favourites;

@Repository
@Transactional
public interface FavRepository extends JpaRepository<Favourites, Integer>{

}
