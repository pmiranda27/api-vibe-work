package br.com.fiap.beans.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoTrabalho {
    PRESENCIAL("Presencial"),
    REMOTO("Remoto"),
    FOLGA("Folga");

    private final String description;
    TipoTrabalho(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static TipoTrabalho transformarStringEmEnumTrabalho(String trabalho) {
        for (TipoTrabalho tipo : TipoTrabalho.values()) {
            if (tipo.getDescription().equalsIgnoreCase(trabalho)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de trabalho inválido: " + trabalho);
    }

    @JsonCreator
    public static TipoTrabalho toJson(String value) {
        for (TipoTrabalho tipo : TipoTrabalho.values()) {
            if (tipo.getDescription().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo inválido: " + value);
    }

}
