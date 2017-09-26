package com.zhaozhy.autorstore.entity;



/**
 * AbstractItemMate entity provides the base persistence definition of the ItemMate entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractItemMate  implements java.io.Serializable {


    // Fields    

     private ItemMateId id;
     private String imUnit;
     private Integer imNum;


    // Constructors

    /** default constructor */
    public AbstractItemMate() {
    }

	/** minimal constructor */
    public AbstractItemMate(ItemMateId id) {
        this.id = id;
    }
    
    /** full constructor */
    public AbstractItemMate(ItemMateId id, String imUnit, Integer imNum) {
        this.id = id;
        this.imUnit = imUnit;
        this.imNum = imNum;
    }

   
    // Property accessors

    public ItemMateId getId() {
        return this.id;
    }
    
    public void setId(ItemMateId id) {
        this.id = id;
    }

    public String getImUnit() {
        return this.imUnit;
    }
    
    public void setImUnit(String imUnit) {
        this.imUnit = imUnit;
    }

    public Integer getImNum() {
        return this.imNum;
    }
    
    public void setImNum(Integer imNum) {
        this.imNum = imNum;
    }
   








}