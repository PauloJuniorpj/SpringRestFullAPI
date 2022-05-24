package curso.api.rest.cursospringrestapi.repository;

import curso.api.rest.cursospringrestapi.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
