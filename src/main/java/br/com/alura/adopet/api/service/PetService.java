package br.com.alura.adopet.api.service;

import java.util.List;

import br.com.alura.adopet.api.dto.CadastroPetDTO;
import br.com.alura.adopet.api.dto.PetDTO;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;

public class PetService {

	private PetRepository petRepository;
	
	public List<PetDTO> buscarPetsDisponiveis() {
        return petRepository.findAllByAdotadoFalse()
        		.stream()
        		.map(PetDTO::new)
        		.toList();
	}
	
	public void cadastrarPet(Abrigo abrigo, CadastroPetDTO dto) {
		petRepository.save(new Pet(dto, abrigo));
	}
	
}
