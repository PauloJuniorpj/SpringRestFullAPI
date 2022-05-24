package curso.api.rest.cursospringrestapi.controller;

import curso.api.rest.cursospringrestapi.model.Usuario;
import curso.api.rest.cursospringrestapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController//Arquitetura Rest
@RequestMapping(value = "/usuario")
public class IndexController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping(value = "/{id}/relatoriopdf", produces = "application/json")
    public ResponseEntity<Usuario> relatorio(@PathVariable(value = "id")Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        //Retorno seria um relatorio
        return ResponseEntity.ok(usuario.get());
    }

    // Serviço RestFull
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Usuario> init(@PathVariable(value = "id")Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        return ResponseEntity.ok(usuario.get());
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Usuario>> usuario(){

        List<Usuario> list = (List<Usuario>) usuarioRepository.findAll();

        return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
    }

    //Cadastrar
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){
        for(int pos = 0; pos < usuario.getTelefones().size(); pos ++){
            usuario.getTelefones().get(pos).setUsuario(usuario);
        }
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
    }

    //Atualizar
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario){

        for(int pos = 0; pos < usuario.getTelefones().size(); pos ++){
            usuario.getTelefones().get(pos).setUsuario(usuario);
        }
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id}", produces = "application/text")
    public String deletarporId(@PathVariable("id") Long id){

        usuarioRepository.deleteById(id);

        return "ok" ;
    }


}
