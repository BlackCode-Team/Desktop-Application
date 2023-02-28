package entities;

public class Badge {

  
    private int idbadge;
    private String typebadge;
    private int nbrepoint;
    
    public Badge(){
        
    }

    public Badge(String typebadge, int nbrepoint) {
        this.typebadge = typebadge;
        this.nbrepoint = nbrepoint;
    }

    public Badge(int idbadge, String typebadge, int nbrepoint) {
        this.idbadge = idbadge;
        this.typebadge = typebadge;
        this.nbrepoint = nbrepoint;
    }
    

    
    public int getIdbadge() {
        return idbadge;
    }

    public void setIdbadge(int idbadge) {
        this.idbadge = idbadge;
    }

    public String getTypebadge() {
        return typebadge;
    }

    public void setTypebadge(String typebadge) {
        this.typebadge = typebadge;
    }

    public int getNbrepoint() {
        return nbrepoint;
    }

    public void setNbrepoint(int nbrepoint) {
        this.nbrepoint = nbrepoint;
    }

    @Override
    public String toString() {
        return "Badge{" + "idbadge=" + idbadge + ", typebadge=" + typebadge + ", nbrepoint=" + nbrepoint +  '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Badge other = (Badge) obj;
        if (this.idbadge != other.idbadge) {
            return false;
        }
        return true;
    }

  

  
    
  
    
    
    
    
    
    
    
    
    
    
    
}
