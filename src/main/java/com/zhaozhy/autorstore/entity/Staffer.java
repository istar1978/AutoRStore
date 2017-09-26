package com.zhaozhy.autorstore.entity;

import java.util.Date;


/**
 * Staffer entity. @author MyEclipse Persistence Tools
 */
public class Staffer extends AbstractStaffer implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Staffer() {
    }

	/** minimal constructor */
    public Staffer(String staId, String depId, String braId, String staStat) {
        super(staId, depId, braId, staStat);        
    }
    
    /** full constructor */
    public Staffer(String staId, String staName, String staPwd, String staPosition, String staLevel, String depId, String braId, Date staCdate, String staStat) {
        super(staId, staName, staPwd, staPosition, staLevel, depId, braId, staCdate, staStat);        
    }
   
}
