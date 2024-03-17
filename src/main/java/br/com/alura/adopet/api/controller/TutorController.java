package br.com.alura.adopet.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDTO;
import br.com.alura.adopet.api.dto.CadastroTutorDTO;
import br.com.alura.adopet.api.excpetion.ValidacaoExcpetion;
import br.com.alura.adopet.api.service.TutorService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastroTutorDTO dto) {
       try {
    	   tutorService.cadastrar(dto);
    	   return ResponseEntity.ok().build();
       } catch (ValidationException exception) {
    	   return ResponseEntity.badRequest().body(exception.getMessage());
       }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid AtualizacaoTutorDTO dto) {
        try {
            tutorService.atualizar(dto);
            return ResponseEntity.ok().build();
        } catch (ValidacaoExcpetion exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

}
