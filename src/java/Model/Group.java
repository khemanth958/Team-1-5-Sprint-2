/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Hemanth
 */
public class Group implements Serializable {
    private int groupID;
    private String groupName;
    private String groupDescription;
    private int numberOfGroupMembers; 

    
    public Group(int groupID,String groupName,String groupDescription,int numberOfGroupMembers){
        this.groupID = groupID;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.numberOfGroupMembers = numberOfGroupMembers;
    }

    public Group() {
        super();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /**
     * @return the groupID
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * @param groupID the groupID to set
     */
    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @return the groupDescription
     */
    public String getGroupDescription() {
        return groupDescription;
    }

    /**
     * @param groupDescription the groupDescription to set
     */
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    /**
     * @return the numberOfGroupMembers
     */
    public int getNumberOfGroupMembers() {
        return numberOfGroupMembers;
    }

    /**
     * @param numberOfGroupMembers the numberOfGroup to set
     */
    public void setNumberOfGroupMembers(int numberOfGroupMembers) {
        this.numberOfGroupMembers = numberOfGroupMembers;
    }
}
