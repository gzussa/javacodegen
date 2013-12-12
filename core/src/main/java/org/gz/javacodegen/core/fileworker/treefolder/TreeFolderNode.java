/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 Gregory
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package org.gz.javacodegen.core.fileworker.treefolder;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeFolderNode {
	
	private List<TreeFolderNode> childs;
    private List<TreeFolderNode> leafs;
    private String value;
    private String incrementalPath;

    public TreeFolderNode(String value, String incrementalePath){
    	childs = new ArrayList<TreeFolderNode>();
    	leafs = new ArrayList<TreeFolderNode>();
    	this.value = value;
    	this.incrementalPath = incrementalePath;
    }
    
    public boolean isLeaf(){
    	return childs.isEmpty() && leafs.isEmpty();
    }
    
    public TreeFolderNode addElement(String currentPath, String[] path){
    	TreeFolderNode currentChild = new TreeFolderNode(path[0], incrementalPath+File.separator+path[0]);
    	
    	if(path.length == 1){
    		leafs.add(currentChild);
    		return currentChild;
    	} else {
    		int index = childs.indexOf(currentChild);
    		if(index == -1){
    			childs.add(currentChild);
    			return currentChild.addElement(currentChild.getIncrementalPath(), Arrays.copyOfRange(path, 1, path.length));	
    		} else {
    			TreeFolderNode nextChild = childs.get(index);
    			return nextChild.addElement(currentChild.getIncrementalPath(), Arrays.copyOfRange(path, 1, path.length));
    		}
    	}
    }
    
    public List<TreeFolderNode> getChilds(){
    	return childs;
    }
    
    public List<TreeFolderNode> getLeafs(){
    	return leafs;
    }
    
    public String getValue(){
    	return value;
    }
    
    public String getIncrementalPath(){
    	return incrementalPath;
    }
    
    @Override
    public boolean equals(Object obj){
    	TreeFolderNode toCmp = (TreeFolderNode)obj;
    	return incrementalPath.equals(toCmp.incrementalPath) && value.equals(toCmp.value);
    }
    
    public String printPretty(String indent, boolean last)
    {
    	String out = indent;
        if (last)
        {
        	out += "\\-";
            indent += "  ";
        }
        else
        {
        	out += "|-";
            indent += "| ";
        }
        out += value + "\n";

        for (int i = 0; i < childs.size(); i++)
            out += childs.get(i).printPretty(indent, i == childs.size() - 1);
        for (int i = 0; i < leafs.size(); i++)
            out += leafs.get(i).printPretty(indent, i == leafs.size() - 1);
        
        return out;
    }
        
    @Override
    public String toString(){
    	return value;
    }
}
