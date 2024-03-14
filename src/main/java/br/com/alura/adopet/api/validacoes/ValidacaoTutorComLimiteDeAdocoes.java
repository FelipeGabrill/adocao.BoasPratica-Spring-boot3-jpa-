package br.com.alura.adopet.api.validacoes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.excpetion.ValidacaoExcpetion;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;

public class ValidacaoTutorComLimiteDeAdocoes {

	@Autowired
	private AdocaoRepository adocaoRepository;

	@Autowired
	private TutorRepository tutorRepository;

	public void validar(SolicitacaoAdocaoDTO dto) {
		List<Adocao> adocoes = adocaoRepository.findAll();
		Tutor tutor = tutorRepository.getReferenceById(dto.idTutor());

		for (Adocao a : adocoes) {
			int contador = 0;
			if (a.getTutor() == tutor && a.getStatus() == StatusAdocao.APROVADO) {
				contador = contador + 1;
			}
			if (contador == 5) {
				throw new ValidacaoExcpetion("Tutor chegou ao limite máximo de 5 adoções!");
			}
		}
	}
}
