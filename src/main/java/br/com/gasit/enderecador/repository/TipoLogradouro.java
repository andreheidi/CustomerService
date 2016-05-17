package br.com.gasit.enderecador.repository;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public enum TipoLogradouro {

	AEROPORTO("AER", "Aeroporto"),
	ALAMEDA("AL", "Alameda"),
	AREA("A", "Área"),
	AVENIDA("AV", "Avenida"),
	CAMPO("CPO", "Campo"),
	CHACARA("CH", "Chácara"),
	COLONIA("COL", "Colônia"),
	CONDOMINIO("CON", "Condomínio"),
	CONJUNTO("CJ", "Conjunto"),
	DISTRITO("DT", "Distrito"),
	ESPLANADA("ESP", "Esplanada"),
	ESTACAO("ETC", "Estação"),
	ESTRADA("EST", "Estrada"),
	FAVELA("FAV", "Favela"),
	FAZENDA("FAZ", "Fazenda"),
	FEIRA("FRA", "Feira"),
	JARDIM("JD", "Jardim"),
	LADEIRA("LD", "Ladeira"),
	LAGO("LG", "Lago"),
	LAGOA("LGA", "Lagoa"),
	LARGO("LRG", "Largo"),
	LOTEAMENTO("LOT", "Loteamento"),
	MORRO("MRO", "Morro"),
	NUCLEO("NUC", "Núcleo"),
	OUTROS("O", "Outros"),
	PARQUE("PRQ", "Parque"),
	PASSARELA("PSA", "Passarela"),
	PATIO("PAT", "Pátio"),
	PRACA("PC", "Praça"),
	QUADRA("Q", "Quadra"),
	RECANTO("REC", "Recanto"),
	RESIDENCIAL("RES", "Residencial"),
	RODOVIA("ROD", "Rodovia"),
	RUA("R", "Rua"),
	SETOR("ST", "Setor"),
	SITIO("SIT", "Sítio"),
	TRAVESSA("TV", "Travessa"),
	TRECHO("TR", "Trecho"),
	TREVO("TRV", "Trevo"),
	VALE("VLE", "Vale"),
	VEREDA("VER", "Vereda"),
	VIA("V", "Via"),
	VIADUTO("VD", "Viaduto"),
	VIELA("VLA", "Viela"),
	VILA("VL", "Vila");
	
	private static final Map<String, TipoLogradouro> map;
	
	private String abreviatura;
	private String descricao;
	
	private TipoLogradouro(String abreviatura, String descricao) {
		this.abreviatura = abreviatura;
		this.descricao = descricao;
	}

	static {
		Builder<String, TipoLogradouro> builder = ImmutableMap.builder();
		for(TipoLogradouro tipos : values())
			builder.put(tipos.getDescricao(), tipos);
		map = builder.build();
	}

	public static TipoLogradouro fromType(String descricao) {			
		return map.get(descricao);
	}
	
	public String getDescricao() {
		return descricao;
	}	

	public String getAbreviatura() {
		return abreviatura;
	}
}
