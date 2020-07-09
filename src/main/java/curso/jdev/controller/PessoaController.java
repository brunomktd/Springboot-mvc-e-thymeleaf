package curso.jdev.controller;

import curso.jdev.model.Pessoa;
import curso.jdev.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
    public ModelAndView inicio(){
        ModelAndView modelAndView = new ModelAndView("/cadastro/cadastropessoa");
        modelAndView.addObject("pessoas", pessoaRepository.findAll());
        modelAndView.addObject("pessoaobj", new Pessoa());
        return modelAndView;
    }

    @PostMapping("**/salvarpessoa")
    public ModelAndView salvar(Pessoa pessoa){
        pessoaRepository.save(pessoa);
        ModelAndView modelAndView = new ModelAndView("/cadastro/cadastropessoa");
        Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
        modelAndView.addObject("pessoas", pessoasIt);
        modelAndView.addObject("pessoaobj", new Pessoa());
        return modelAndView;
    }

    @GetMapping("/listapessoas")
    public ModelAndView pessoas(){
        ModelAndView modelAndView = new ModelAndView("/cadastro/cadastropessoa");
        Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
        modelAndView.addObject("pessoas", pessoasIt);
        modelAndView.addObject("pessoaobj", new Pessoa());
        return modelAndView;
    }

    @GetMapping("/editarpessoa/{idpessoa}")
    public ModelAndView editar(@PathVariable("idpessoa") Long idPessoa){
        Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
        ModelAndView modelAndView = new ModelAndView("/cadastro/cadastropessoa");
        modelAndView.addObject("pessoas", pessoaRepository.findAll());
        modelAndView.addObject("pessoaobj", pessoa.get());
        return modelAndView;
    }

    @GetMapping("/removerpessoa/{idpessoa}")
    public ModelAndView excluir(@PathVariable("idpessoa") Long idPessoa){
        pessoaRepository.deleteById(idPessoa);
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        modelAndView.addObject("pessoas", pessoaRepository.findAll());
        modelAndView.addObject("pessoaobj", new Pessoa());
        return modelAndView;
    }

    @PostMapping("**/pesquisarpessoa")
    public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomePesquisa){
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        modelAndView.addObject("pessoas", pessoaRepository.findAllByNomeContaining(nomePesquisa));
        modelAndView.addObject("pessoaobj", new Pessoa());
        return modelAndView;
    }

    @GetMapping("/telefones/{idpessoa}")
    public ModelAndView telefones(@PathVariable("idpessoa") Long idPessoa){
        Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
        ModelAndView modelAndView = new ModelAndView("/cadastro/telefones");
        modelAndView.addObject("pessoaobj", pessoa.get());
        return modelAndView;
    }
}
