package julianocelestino.usersservices.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    Iterable<User> findByUsername(String username);

    Iterable<User> findByName(String name);

    Iterable<User> findByEmail(String email);
}
