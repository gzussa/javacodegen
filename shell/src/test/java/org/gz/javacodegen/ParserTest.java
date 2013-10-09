package org.gz.javacodegen;

import org.gz.javacodegen.args.Parser;
import org.junit.Test;

import junit.framework.TestCase;
/**
 * 
 * @author gzussa
 *
 */
public class ParserTest extends TestCase {
	
	@Test
	public void testPluginUsageDetail(){
		Parser param = new Parser();
		String args[] = {
			"-x", "-h", "-p", "plugin"	
		};
		assertTrue(param.loadAndCheckArgs(args) == 0);
		assertTrue(param.getInputs() == null);
		assertTrue(param.getOutput() == null);
		assertTrue(param.getPlugin().equals("plugin"));
		assertTrue(param.isDebug() == true);
		assertTrue(param.isHelp() == true);
		assertTrue(param.isList() == false);
		assertTrue(param.isVersion() == false);
		
		param = new Parser();
		String args2[] = {
			"-p", "plugin", "-h"	
		};
		assertTrue(param.loadAndCheckArgs(args2) == 0);
		assertTrue(param.getInputs() == null);
		assertTrue(param.getOutput() == null);
		assertTrue(param.getPlugin().equals("plugin"));
		assertTrue(param.isDebug() == false);
		assertTrue(param.isHelp() == true);
		assertTrue(param.isList() == false);
		assertTrue(param.isVersion() == false);
	}
	
	@Test
	public void testToolUsageDetail(){
		Parser param = new Parser();
		String args[] = {
			"-x", "-h"	
		};
		assertTrue(param.loadAndCheckArgs(args) == 1);
		assertTrue(param.getInputs() == null);
		assertTrue(param.getOutput() == null);
		assertTrue(param.getPlugin() == null);
		assertTrue(param.isDebug() == true);
		assertTrue(param.isHelp() == true);
		assertTrue(param.isList() == false);
		assertTrue(param.isVersion() == false);
		
		param = new Parser();
		String args2[] = {
			"-h"	
		};
		assertTrue(param.loadAndCheckArgs(args2) == 1);
		assertTrue(param.getInputs() == null);
		assertTrue(param.getOutput() == null);
		assertTrue(param.getPlugin() == null);
		assertTrue(param.isDebug() == false);
		assertTrue(param.isHelp() == true);
		assertTrue(param.isList() == false);
		assertTrue(param.isVersion() == false);
	}
	
	@Test
	public void testPluginVersionDetail(){
		Parser param = new Parser();
		String args[] = {
			"-x", "-v", "-p", "plugin"	
		};
		assertTrue(param.loadAndCheckArgs(args) == 2);
		assertTrue(param.getInputs() == null);
		assertTrue(param.getOutput() == null);
		assertTrue(param.getPlugin().equals("plugin"));
		assertTrue(param.isDebug() == true);
		assertTrue(param.isHelp() == false);
		assertTrue(param.isList() == false);
		assertTrue(param.isVersion() == true);
		
		param = new Parser();
		String args2[] = {
			"-p", "plugin", "-v"	
		};
		assertTrue(param.loadAndCheckArgs(args2) == 2);
		assertTrue(param.getInputs() == null);
		assertTrue(param.getOutput() == null);
		assertTrue(param.getPlugin().equals("plugin"));
		assertTrue(param.isDebug() == false);
		assertTrue(param.isHelp() == false);
		assertTrue(param.isList() == false);
		assertTrue(param.isVersion() == true);
	}
	
	@Test
	public void testToolVersionDetail(){
		Parser param = new Parser();
		String args[] = {
			"-x", "-v"	
		};
		assertTrue(param.loadAndCheckArgs(args) == 3);
		assertTrue(param.getInputs() == null);
		assertTrue(param.getOutput() == null);
		assertTrue(param.getPlugin() == null);
		assertTrue(param.isDebug() == true);
		assertTrue(param.isHelp() == false);
		assertTrue(param.isList() == false);
		assertTrue(param.isVersion() == true);
		
		param = new Parser();
		String args2[] = {
			"-v"	
		};
		assertTrue(param.loadAndCheckArgs(args2) == 3);
	}
	
	@Test
	public void testPluginListDetail(){
		Parser param = new Parser();
		String args[] = {
			"-x", "-l"	
		};
		assertTrue(param.loadAndCheckArgs(args) == 4);
		assertTrue(param.getInputs() == null);
		assertTrue(param.getOutput() == null);
		assertTrue(param.getPlugin() == null);
		assertTrue(param.isDebug() == true);
		assertTrue(param.isHelp() == false);
		assertTrue(param.isList() == true);
		assertTrue(param.isVersion() == false);
		
		param = new Parser();
		String args2[] = {
			"-l"	
		};
		assertTrue(param.loadAndCheckArgs(args2) == 4);
		assertTrue(param.getInputs() == null);
		assertTrue(param.getOutput() == null);
		assertTrue(param.getPlugin() == null);
		assertTrue(param.isDebug() == false);
		assertTrue(param.isHelp() == false);
		assertTrue(param.isList() == true);
		assertTrue(param.isVersion() == false);
	}
	
	@Test
	public void testPluginExecution(){
		Parser param = new Parser();
		String args[] = {
			"-x", "-p", "plugin", "output", "file"	
		};
		assertTrue(param.loadAndCheckArgs(args) == 5);
		assertTrue(param.getInputs().get(0).equals("file"));
		assertTrue(param.getOutput().equals("output"));
		assertTrue(param.getPlugin().equals("plugin"));
		assertTrue(param.isDebug() == true);
		assertTrue(param.isHelp() == false);
		assertTrue(param.isList() == false);
		assertTrue(param.isVersion() == false);
		
		param = new Parser();
		String args2[] = {
				"output", "-p", "plugin", "file"	
		};
		assertTrue(param.loadAndCheckArgs(args2) == 5);
		assertTrue(param.getInputs().get(0).equals("file"));
		assertTrue(param.getOutput().equals("output"));
		assertTrue(param.getPlugin().equals("plugin"));
		assertTrue(param.isDebug() == false);
		assertTrue(param.isHelp() == false);
		assertTrue(param.isList() == false);
		assertTrue(param.isVersion() == false);
		
		param = new Parser();
		String args3[] = {
			"-x", "-p", "plugin", "output", "file", "file2"	
		};
		assertTrue(param.loadAndCheckArgs(args3) == 5);
		assertTrue(param.getInputs().get(0).equals("file"));
		assertTrue(param.getInputs().get(1).equals("file2"));
		assertTrue(param.getOutput().equals("output"));
		assertTrue(param.getPlugin().equals("plugin"));
		assertTrue(param.isDebug() == true);
		assertTrue(param.isHelp() == false);
		assertTrue(param.isList() == false);
		assertTrue(param.isVersion() == false);
		
		param = new Parser();
		String args4[] = {
			"output", "-p", "plugin", "file", "file2"	
		};
		assertTrue(param.loadAndCheckArgs(args4) == 5);
		assertTrue(param.getInputs().get(0).equals("file"));
		assertTrue(param.getInputs().get(1).equals("file2"));
		assertTrue(param.getOutput().equals("output"));
		assertTrue(param.getPlugin().equals("plugin"));
		assertTrue(param.isDebug() == false);
		assertTrue(param.isHelp() == false);
		assertTrue(param.isList() == false);
		assertTrue(param.isVersion() == false);
		
		param = new Parser();
		String args5[] = {
			"output", "file", "file2", "-p", "plugin", 
		};
		assertTrue(param.loadAndCheckArgs(args5) == 5);
		assertTrue(param.getInputs().get(0).equals("file"));
		assertTrue(param.getInputs().get(1).equals("file2"));
		assertTrue(param.getOutput().equals("output"));
		assertTrue(param.getPlugin().equals("plugin"));
		assertTrue(param.isDebug() == false);
		assertTrue(param.isHelp() == false);
		assertTrue(param.isList() == false);
		assertTrue(param.isVersion() == false);
		
		param = new Parser();
		String args6[] = {
			"output", "file", "-x", "file2", "-p", "plugin", 
		};
		assertTrue(param.loadAndCheckArgs(args6) == 5);
		assertTrue(param.getInputs().get(0).equals("file"));
		assertTrue(param.getInputs().get(1).equals("file2"));
		assertTrue(param.getOutput().equals("output"));
		assertTrue(param.getPlugin().equals("plugin"));
		assertTrue(param.isDebug() == true);
		assertTrue(param.isHelp() == false);
		assertTrue(param.isList() == false);
		assertTrue(param.isVersion() == false);
	}
	
	@Test
	public void testInvalidArgs(){
		Parser param = new Parser();
		String args[] = {
			"-p", "plugin"	
		};
		assertTrue(param.loadAndCheckArgs(args) == -1);
		
		param = new Parser();
		String args1[] = {
			"-x"	
		};
		assertTrue(param.loadAndCheckArgs(args1) == -1);
		
		param = new Parser();
		String args2[] = {
			"output"	
		};
		assertTrue(param.loadAndCheckArgs(args2) == -1);
		
		String args3[] = {
			"-p"	
		};
		assertTrue(param.loadAndCheckArgs(args3) == -1);
		
		String args4[] = {
			"-p", "plugin", "output"	
		};
		assertTrue(param.loadAndCheckArgs(args4) == -1);
		
		String args5[] = {
			"-v", "-l"	
		};
		assertTrue(param.loadAndCheckArgs(args5) == -1);
		
		String args6[] = {
			"-v", "-h"	
		};
		assertTrue(param.loadAndCheckArgs(args6) == -1);
		
		String args7[] = {
			"-h", "-l"	
		};
		assertTrue(param.loadAndCheckArgs(args7) == -1);
	}

}
