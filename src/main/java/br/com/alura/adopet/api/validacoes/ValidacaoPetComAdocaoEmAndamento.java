package br.com.alura.adopet.api.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.excpetion.ValidacaoExcpetion;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;

@Component
public class ValidacaoPetComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

	@Autowired
	private AdocaoRepository adocaoRepository;

	public void validar(SolicitacaoAdocaoDTO dto) {
		boolean petTemAdocaoEmAndamento = adocaoRepository.existsByPetIdAndStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO);
		if (petTemAdocaoEmAndamento) {
			throw new ValidacaoExcpetion("Pet já está aguardando avaliação para ser adotado!");
		}
	}
}
