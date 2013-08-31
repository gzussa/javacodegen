package org.junior.asamson.domain;

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
import org.junior.asamson.service.BaseService;
import org.junior.asamson.repository.BaseRepository;

@Component
@Configurable
public class BaseDataOnDemand; {

    private Random rnd = new SecureRandom();

    private List<Base> data;

    @Autowired
    BaseService baseService;

    @Autowired
    BaseRepository baseRepository;

    public Base getNewTransientBase(int index) {
        Base obj = new Base();
        setName(obj, index);
        return obj;
    }

    public Base getSpecificBase(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Base obj = data.get(index);
        Long id = obj.getId();
        return baseService.findBase(id);
    }

    public Base getRandomBase() {
        init();
        Base obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return baseService.findBase(id);
    }

    public boolean modifyBase(Base obj) {
        return false;
    }

    public void init() {
        int from = 0;
        int to = 10;
        data = baseService.findBaseEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Base' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Base>();
        for (int i = 0; i < 10; i++) {
            Base obj = getNewTransientBase(i);
            try {
		        baseService.saveBase(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
	            baseRepository.flush();
            data.add(obj);
        }
    }
}