package org.gz.javacodegen.example.domain;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.gz.javacodegen.example.service.Pizza2Service;
import org.gz.javacodegen.example.repository.PizzaRepository;

@Component
@Configurable
public class PizzaOrderDataOnDemand; {

    private Random rnd = new SecureRandom();

    private List<PizzaOrder> data;

    @Autowired
    Pizza2Service pizza2Service;

    @Autowired
    PizzaRepository pizzaRepository;

    public PizzaOrder getNewTransientPizzaOrder(int index) {
        PizzaOrder obj = new PizzaOrder();
        setName(obj, index);
        setAdresse(obj, index);
        setTotal(obj, index);
        setDeliveryDate(obj, index);
        return obj;
    }

    public PizzaOrder getSpecificPizzaOrder(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        PizzaOrder obj = data.get(index);
        Long id = obj.getId();
        return pizza2Service.findPizzaOrder(id);
    }

    public PizzaOrder getRandomPizzaOrder() {
        init();
        PizzaOrder obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return pizza2Service.findPizzaOrder(id);
    }

    public boolean modifyPizzaOrder(PizzaOrder obj) {
        return false;
    }

    public void init() {
        int from = 0;
        int to = 10;
        data = pizza2Service.findPizzaOrderEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'PizzaOrder' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<PizzaOrder>();
        for (int i = 0; i < 10; i++) {
            PizzaOrder obj = getNewTransientPizzaOrder(i);
            try {
		        pizza2Service.savePizzaOrder(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
	            pizzaRepository.flush();
            data.add(obj);
        }
    }
}