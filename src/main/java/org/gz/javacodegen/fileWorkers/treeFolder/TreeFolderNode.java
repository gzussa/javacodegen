package org.gz.javacodegen.fileWorkers.treeFolder;

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
