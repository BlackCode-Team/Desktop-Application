package entities;

public class Badge {

  
    private int idbadge;
    private String typebadge; /// enum
    private int nbrepoint;
    private int idUser;
    
    public Badge(){
        
    }

    public Badge(int nbrepoint, int idUser) {
        this.nbrepoint = nbrepoint;
        this.idUser = idUser;
    }

    public Badge(String typebadge, int nbrepoint, int idUser) {
        this.typebadge = typebadge;
        this.nbrepoint = nbrepoint;
        this.idUser = idUser;
    }

    public Badge(int idbadge) {
        this.idbadge = idbadge;
    }
    
    
    public Badge(int idbadge, String typebadge, int nbrepoint) {
        this.idbadge = idbadge;
        this.typebadge = typebadge;
        this.nbrepoint = nbrepoint;
    }

    public Badge(String string, String string0, int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdbadge() {
        return idbadge;
    }

//    public void setIdbadge(int idbadge) {
//        this.idbadge = idbadge;
//    }

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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Badge{" + "idbadge=" + idbadge + ", typebadge=" + typebadge + ", nbrepoint=" + nbrepoint + ", idUser=" + idUser + '}';
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
