package br.edu.ifpb.padroes.biblioteca.gerenciador.models;

public enum UsuarioCargo {
    ADMIN("admin"),
    USER("user");

    private final String role;

    UsuarioCargo(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }


}