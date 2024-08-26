//package br.edu.ifpb.padroes.biblioteca.gerenciador.models;
//
//import jakarta.persistence.*;
//
//import java.io.Serializable;
//
//@Entity
//@Table(name = "cargo")
//public class UsuarioCargo implements Serializable {
//    @Id
//    private Long id;
//
//    private String tipo;
//
//    public UsuarioCargo() {}
//
//    public UsuarioCargo(Long id, String tipo) {
//        this.id = id;
//        this.tipo = tipo;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTipo() {
//        return tipo;
//    }
//
//    public void setTipo(String tipo) {
//        this.tipo = tipo;
//    }
//
//    public enum Enum {
//        ADMIN(1L, "admin"),
//        COMUM(2L, "comum");
//
//
//        Enum(Long id, String tipo) {
//            this.id = id;
//            this.tipo = tipo;
//        }
//
//        private Long id;
//        private String tipo;
//        public Long getId() {
//            return id;
//        }
//
//        public String getTipo() {
//            return tipo;
//        }
//
//
//        public static UsuarioCargo get(Long id) {
//            return (id == 1 ? Enum.ADMIN : Enum.COMUM).toUsuarioCargo();
//        }
//        public static Enum fromId(Long id) {
//            for (Enum cargoEnum : Enum.values()) {
//                if (cargoEnum.getId().equals(id)) {
//                    return cargoEnum;
//                }
//            }
//            throw new IllegalArgumentException("Nenhum id encontrado, id: " + id);
//        }
//
//        public UsuarioCargo toUsuarioCargo() {
//            return new UsuarioCargo(id, tipo);
//        }
//    }
//}