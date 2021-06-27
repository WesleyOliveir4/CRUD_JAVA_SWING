/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicavendas;

/**
 *
 * @author wesley
 */
public class Clientes {

    
    private int CodCli;
    private String Nome;
    private String Ender;
    private String Bairro;
    private String Cidade;
    private String CEP;
    private String UF;
    private String Email;
    private String Fone;
    private String Celular;

    public Clientes(int CodCli, String Nome, String Ender, String Bairro, String Cidade, String CEP, String UF, String Email, String Fone, String Celular) {
        this.CodCli = CodCli;
        this.Nome = Nome;
        this.Ender = Ender;
        this.Bairro = Bairro;
        this.Cidade = Cidade;
        this.CEP = CEP;
        this.UF = UF;
        this.Email = Email;
        this.Fone = Fone;
        this.Celular = Celular;
    }

    public Clientes(int CodCli) {
        this.CodCli = CodCli;    
    }

    public int getCodCli() {
        return this.CodCli;
    }

    public String getNome() {
        return this.Nome;
    }

    public String getEnder() {
        return this.Ender;
    }

    public String getBairro() {
        return this.Bairro;
    }

    public String getCidade() {
        return this.Cidade;
    }

    public String getCEP() {
        return this.CEP;
    }

    public String getUF() {
        return this.UF;
    }

    public String getEmail() {
        return this.Email;
    }

    public String getFone() {
        return this.Fone;
    }

    public String getCelular() {
        return this.Celular;
    }

    public void setCodCli(int CodCli) {
        this.CodCli = CodCli;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public void setEnder(String Ender) {
        this.Ender = Ender;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setFone(String Fone) {
        this.Fone = Fone;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }
    
    
}
