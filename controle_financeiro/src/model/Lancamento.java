package model;

import java.time.LocalDate;

public class Lancamento {
    private int id;
    private String descricao;
    private double valor;
    private LocalDate data;
    private TipoLancamento tipo;
    private Categoria categoria;

    // Construtor vazio, totalmente importante
    public Lancamento() {}

    // Getters e Setters 
    public int getId() { 
    	return id;}
    
    public void setId(int i) { 
    	this.id = i; }

    public String getDescricao() { 
    	return descricao; }
    
    public void setDescricao(String descricao) { 
    	this.descricao = descricao; }

    public double getValor() { 
    	return valor; }
    
    public void setValor(double valor) {  
    	this.valor = valor; }

    public LocalDate getData() { 
    	return data; }
    
    public void setData(LocalDate data) { 
    	this.data = data; }

    public TipoLancamento getTipo() { 
    	return tipo; }
    
    public void setTipo(TipoLancamento tipo) { 
    	this.tipo = tipo; }

    public Categoria getCategoria() { 
    	return categoria; }
    
    public void setCategoria(Categoria categoria) {
    	this.categoria = categoria; }
}