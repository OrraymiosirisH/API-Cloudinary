package guevara.alvarez.demo.repositories;


import guevara.alvarez.demo.models.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Actor, Long> {


}
