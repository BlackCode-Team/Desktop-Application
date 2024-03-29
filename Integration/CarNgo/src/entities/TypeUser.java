package entities;

public enum  TypeUser {
    admin,agent,client;
    
    public static TypeUser fromString(String text) {
    for (TypeUser type : TypeUser.values()) {
        if (type.name().equalsIgnoreCase(text)) {
            return type;
        }
    }
    return null;
}
//    La méthode "equalsIgnoreCase()" est utilisée dans la méthode "fromString()" pour vérifier si 
//    une chaîne de caractères donnée correspond à l'un des noms de valeur de l'énumération "TypeVehicule".
}