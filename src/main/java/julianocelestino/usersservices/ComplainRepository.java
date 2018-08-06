package julianocelestino.usersservices;

import org.springframework.data.repository.CrudRepository;


public interface ComplainRepository extends CrudRepository<Complain, Long> {

    Iterable<Complain> findByCompanyAndLocale(String company, String locale);

}
