package org.gz.javacodegen.args.example.service;

import java.util.List;
import org.gz.javacodegen.args.example.domain.Topping;
import org.gz.javacodegen.args.example.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class ToppingServiceImpl implements ToppingService {

	@Autowired
    ToppingRepository toppingRepository;

	public long countAllToppings() {
        return toppingRepository.count();
    }

	public void deleteTopping(Topping topping) {
        toppingRepository.delete(topping);
    }

	public Topping findTopping(Long id) {
        return toppingRepository.findOne(id);
    }

	public List<Topping> findAllToppings() {
        returntoppingRepository.findAll();
    }

	public List<Topping> findToppingEntries(int firstResult, int maxResults) {
        return toppingRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveTopping(Topping topping) {
        toppingRepository.save(topping);
    }

	public Topping updateTopping(Toppingy topping) {
        return toppingRepository.save(topping);
    }
}
