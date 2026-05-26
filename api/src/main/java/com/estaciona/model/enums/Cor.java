package com.estaciona.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Cor {
  BRANCO(1, "Branco", "#ffffff"),
  PRETO(2, "Preto", "#000000"),
  PRATA(3, "Prata", "#c0c0c0"),
  VERMELHO(4, "Vermelho", "#ff0000"),
  AZUL(5, "Azul", "#0000ff"),
  CINZA(6, "Cinza", "#808080"),
  VERDE(7, "Verde", "#00ff00"),
  AMARELO(8, "Amarelo", "#ffff00"),
  ROSA(9, "Rosa", "#ffc0cb"),
  LARANJA(10, "Laranja", "#ffa500"),
  MARROM(11, "Marrom", "#8b4513"),
  BEGE(12, "Bege", "#f5f5dc"),
  DOURADO(13, "Dourado", "#ffd700"),
  CHAMPAGNE(14, "Champagne", "#f7e7ce"),
  GRAFITE(15, "Grafite", "#383838"),
  VINHO(16, "Vinho", "#722f37"),
  TURQUESA(17, "Turquesa", "#40e0d0"),
  ROXO(18, "Roxo", "#800080"),
  CINZA_CHUMBO(19, "Cinza Chumbo", "#36454f"),
  AZUL_MARINHO(20, "Azul Marinho", "#000080");

  public final int id;
  public final String nome;
  public final String hex;

  Cor(int id, String nome, String hex) {
    this.id = id;
    this.nome = nome;
    this.hex = hex;
  }

  @JsonCreator
  public static Cor fromJson(Map<String, Object> value) {
    Object id = value.get("id");
    if (id == null) throw new IllegalArgumentException("ID da cor é obrigatório");
    return fromId(((Number) id).intValue());
  }

  private static final Map<Integer, Cor> COLORS_BY_ID = new HashMap<>();

  static {
    for (Cor cor : values()) COLORS_BY_ID.put(cor.id, cor);
  }

  public static Cor fromId(Integer id) {
    Cor c = COLORS_BY_ID.get(id);
    if (c != null) return c;
    throw new IllegalArgumentException("Cor inválida: " + id);
  }
}
