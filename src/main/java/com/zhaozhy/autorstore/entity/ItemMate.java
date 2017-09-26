package com.zhaozhy.autorstore.entity;



/**
 * ItemMate entity. @author MyEclipse Persistence Tools
 */
public class ItemMate extends AbstractItemMate implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public ItemMate() {
    }

	/** minimal constructor */
    public ItemMate(ItemMateId id) {
        super(id);        
    }
    
    /** full constructor */
    public ItemMate(ItemMateId id, String imUnit, Integer imNum) {
        super(id, imUnit, imNum);        
    }
   
}
