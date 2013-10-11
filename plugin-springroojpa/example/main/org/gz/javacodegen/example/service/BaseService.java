package org.gz.javacodegen.args.example.service;

import java.util.List;
import org.gz.javacodegen.args.example.domain.Base;
import org.gz.javacodegen.args.example.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class BaseServiceImpl implements BaseService {

	@Autowired
    BaseRepository baseRepository;

	public long countAllBases() {
        return baseRepository.count();
    }

	public void deleteBase(Base base) {
        baseRepository.delete(base);
    }

	public Base findBase(Long id) {
        return baseRepository.findOne(id);
    }

	public List<Base> findAllBases() {
        returnbaseRepository.findAll();
    }

	public List<Base> findBaseEntries(int firstResult, int maxResults) {
        return baseRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

	public void saveBase(Base base) {
        baseRepository.save(base);
    }

	public Base updateBase(Basey base) {
        return baseRepository.save(base);
    }
}
