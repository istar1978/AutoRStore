package com.zhaozhy.autorstore.entity;



/**
 * AbstractItemMateId entity provides the base persistence definition of the ItemMateId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractItemMateId  implements java.io.Serializable {


    // Fields    

     private String repId;
     private String matId;


    // Constructors

    /** default constructor */
    public AbstractItemMateId() {
    }

    
    /** full constructor */
    public AbstractItemMateId(String repId, String matId) {
        this.repId = repId;
        this.matId = matId;
    }

   
    // Property accessors

    public String getRepId() {
        return this.repId;
    }
    
    public void setRepId(String repId) {
        this.repId = repId;
    }

    public String getMatId() {
        return this.matId;
    }
    
    public void setMatId(String matId) {
        this.matId = matId;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AbstractItemMateId) ) return false;
		 AbstractItemMateId castOther = ( AbstractItemMateId ) other; 
         
		 return ( (this.getRepId()==castOther.getRepId()) || ( this.getRepId()!=null && castOther.getRepId()!=null && this.getRepId().equals(castOther.getRepId()) ) )
 && ( (this.getMatId()==castOther.getMatId()) || ( this.getMatId()!=null && castOther.getMatId()!=null && this.getMatId().equals(castOther.getMatId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getRepId() == null ? 0 : this.getRepId().hashCode() );
         result = 37 * result + ( getMatId() == null ? 0 : this.getMatId().hashCode() );
         return result;
   }   





}