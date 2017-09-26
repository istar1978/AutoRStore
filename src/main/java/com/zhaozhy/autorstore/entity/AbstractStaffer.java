package com.zhaozhy.autorstore.entity;

import java.util.Date;


/**
 * AbstractStaffer entity provides the base persistence definition of the Staffer entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractStaffer  implements java.io.Serializable {


    // Fields    

     private String staId;
     private String staName;
     private String staPwd;
     private String staPosition;
     private String staLevel;
     private String depId;
     private String braId;
     private Date staCdate;
     private String staStat;


    // Constructors

    /** default constructor */
    public AbstractStaffer() {
    }

	/** minimal constructor */
    public AbstractStaffer(String staId, String depId, String braId, String staStat) {
        this.staId = staId;
        this.depId = depId;
        this.braId = braId;
        this.staStat = staStat;
    }
    
    /** full constructor */
    public AbstractStaffer(String staId, String staName, String staPwd, String staPosition, String staLevel, String depId, String braId, Date staCdate, String staStat) {
        this.staId = staId;
        this.staName = staName;
        this.staPwd = staPwd;
        this.staPosition = staPosition;
        this.staLevel = staLevel;
        this.depId = depId;
        this.braId = braId;
        this.staCdate = staCdate;
        this.staStat = staStat;
    }

   
    // Property accessors

    public String getStaId() {
        return this.staId;
    }
    
    public void setStaId(String staId) {
        this.staId = staId;
    }

    public String getStaName() {
        return this.staName;
    }
    
    public void setStaName(String staName) {
        this.staName = staName;
    }

    public String getStaPwd() {
        return this.staPwd;
    }
    
    public void setStaPwd(String staPwd) {
        this.staPwd = staPwd;
    }

    public String getStaPosition() {
        return this.staPosition;
    }
    
    public void setStaPosition(String staPosition) {
        this.staPosition = staPosition;
    }

    public String getStaLevel() {
        return this.staLevel;
    }
    
    public void setStaLevel(String staLevel) {
        this.staLevel = staLevel;
    }

    public String getDepId() {
        return this.depId;
    }
    
    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getBraId() {
        return this.braId;
    }
    
    public void setBraId(String braId) {
        this.braId = braId;
    }

    public Date getStaCdate() {
        return this.staCdate;
    }
    
    public void setStaCdate(Date staCdate) {
        this.staCdate = staCdate;
    }

    public String getStaStat() {
        return this.staStat;
    }
    
    public void setStaStat(String staStat) {
        this.staStat = staStat;
    }
   








}