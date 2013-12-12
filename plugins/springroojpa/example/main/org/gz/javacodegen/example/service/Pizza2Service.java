package org.gz.javacodegen.args.example.service;

import java.util.List;
import org.gz.javacodegen.args.example.domain.PizzaOrder;
import org.gz.javacodegen.args.example.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class Pizza2ServiceImpl implements Pizza2Service {

	@Autowired
    PizzaRepository pizzaRepository;

	public long countAllPizzaOrders() {
        return pizzaRepository.count();
    }

	public void deletePizzaOrder(PizzaOrder pizzaOrder) {
        pizzaRepository.delete(pizzaOrder);
    }

	public PizzaOrder findPizzaOrder(Long id) {
        return pizzaRepository.findOne(id);
    }

	public List<PizzaOrder> findAllPizzaOrders() {
        returnpizzaRepository.findAll();
    }

	public List<PizzaOrder> findPizzaOrderEntries(int firstResult, int maxResults) {
        return pizzaRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void savePizzaOrder(PizzaOrder pizzaOrder) {
        pizzaRepository.save(pizzaOrder);
    }

	public PizzaOrder updatePizzaOrder(PizzaOrdery pizzaOrder) {
        return pizzaRepository.save(pizzaOrder);
    }
}
