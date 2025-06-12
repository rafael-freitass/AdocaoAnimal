package br.edu.ifpr.service;

import br.edu.ifpr.dao.AnimalDAO;
import br.edu.ifpr.model.AnimalModel;
import java.util.List;

public class AnimalService {
    private final AnimalDAO animalDAO = new AnimalDAO();

    public void cadastrarAnimal(AnimalModel animal) {
        // Validações básicas
        if (animal.getNome() == null || animal.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do animal é obrigatório");
        }
        if (animal.getIdade() < 0) {
            throw new IllegalArgumentException("Idade deve ser um valor positivo");
        }
        if (animal.getRaca() == null || animal.getRaca().trim().isEmpty()) {
            throw new IllegalArgumentException("Raça é obrigatória");
        }
        if (animal.getSexo() != 'M' && animal.getSexo() != 'F') {
            throw new IllegalArgumentException("Sexo deve ser M ou F");
        }

        animalDAO.salvar(animal);
    }

    public void atualizarAnimal(AnimalModel animal) {
        if (animal.getID() <= 0) {
            throw new IllegalArgumentException("ID do animal é inválido");
        }
        animalDAO.atualizar(animal);
    }

    public void excluirAnimal(int id) {
        AnimalModel animal = animalDAO.buscarPorId(id);
        if (animal == null) {
            throw new IllegalArgumentException("Animal não encontrado");
        }
        animalDAO.excluir(id);
    }

    public AnimalModel buscarPorId(int id) {
        return animalDAO.buscarPorId(id);
    }

    public List<AnimalModel> listarTodos() {
        return animalDAO.buscarTodos();
    }

    public List<AnimalModel> listarDisponiveis() {
        return animalDAO.buscarDisponiveis();
    }

    public List<AnimalModel> buscarPorTipo(String tipo) {
        return animalDAO.buscarPorTipo(tipo);
    }

    public void marcarComoAdotado(int idAnimal) {
        AnimalModel animal = animalDAO.buscarPorId(idAnimal);
        if (animal == null) {
            throw new IllegalArgumentException("Animal não encontrado");
        }
        animal.setDisponivelAdocao(false);
        animalDAO.atualizar(animal);
    }

    public void marcarComoDisponivel(int idAnimal) {
        AnimalModel animal = animalDAO.buscarPorId(idAnimal);
        if (animal == null) {
            throw new IllegalArgumentException("Animal não encontrado");
        }
        animal.setDisponivelAdocao(true);
        animalDAO.atualizar(animal);
    }
}