package org.gz.javacodegen.plugin.springroojpa;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gz.javacodegen.core.AbstractGenerator;
import org.gz.javacodegen.core.fileworker.treefolder.MainTreeFolderMaker;
import org.gz.javacodegen.core.fileworker.treefolder.TestTreeFolderMaker;
import org.gz.javacodegen.plugin.springroojpa.wrapper.DodTest;
import org.gz.javacodegen.plugin.springroojpa.wrapper.Entity;
import org.gz.javacodegen.plugin.springroojpa.wrapper.IntegrationTest;
import org.gz.javacodegen.plugin.springroojpa.writer.DodTestWriter;
import org.gz.javacodegen.plugin.springroojpa.writer.EntityWriter;
import org.gz.javacodegen.plugin.springroojpa.writer.IntegrationTestWriter;
import org.gz.javacodegen.plugin.springroojpa.writer.RepositoryWriter;
import org.gz.javacodegen.plugin.springroojpa.writer.ServiceImplWriter;
import org.gz.javacodegen.plugin.springroojpa.writer.ServiceInterfaceWriter;

public class SpringRooJpaGenerator extends AbstractGenerator {
	
	static Logger logger = LogManager.getLogger(SpringRooJpaGenerator.class.getName());
	
	public SpringRooJpaGenerator(String srcRootPath, String testRootPath, String xmlConfigFile){
		super(srcRootPath, testRootPath, xmlConfigFile);
		TestTreeFolderMaker.getInstance().init(testRootPath);
		MainTreeFolderMaker.getInstance().init(srcRootPath);
	}

	@Override
	public void generate() {
		try {
			 
			File file = new File(getXmlConfigFile());
			JAXBContext jaxbContext = JAXBContext.newInstance(Entity.class);
	 
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Entity entity = (Entity) jaxbUnmarshaller.unmarshal(file);
			logger.debug(entity.toString());
			logger.info("\n"+MainTreeFolderMaker.getInstance().printTree());
			MainTreeFolderMaker.getInstance().writeOnDisk();
			EntityWriter ew = new EntityWriter(entity);
			ew.writeOnDisk("");
			if(entity.getRepository() != null){
				RepositoryWriter rw = new RepositoryWriter(entity.getRepository(), entity);
				rw.writeOnDisk("");
			}
			if(entity.getService() != null){
				ServiceInterfaceWriter sintw = new ServiceInterfaceWriter(entity.getService(), entity);
				sintw.writeOnDisk("");
				ServiceImplWriter simpw = new ServiceImplWriter(entity.getService(), entity);
				simpw.writeOnDisk("");
			}	
			
			DodTest dodWrapper = new DodTest(entity.getFileName()+"DataOnDemand", entity.getPackageName());
			logger.info("Generating Junit tests");
			logger.info("\n"+TestTreeFolderMaker.getInstance().printTree());
			TestTreeFolderMaker.getInstance().writeOnDisk();
			
			DodTestWriter dodw = new DodTestWriter(dodWrapper, entity);
			dodw.writeOnDisk("");
			
			IntegrationTest intWrapper = new IntegrationTest(entity.getFileName()+"IntegrationTest", entity.getPackageName());
			IntegrationTestWriter intw = new IntegrationTestWriter(intWrapper, entity);
			intw.writeOnDisk("");
		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
		
	}
	
}
