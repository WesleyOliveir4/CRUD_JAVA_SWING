/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicavendas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author wesley
 */
public class ClientesDAO {
    public Clientes clientes;
    public BD bd;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String men, sql;
    private int numero;
    public static final byte INCLUSAO=1;
    public static final byte ALTERACAO=2;
    public static final byte EXCLUSAO=3;
    
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
    
    public ClientesDAO(){
        bd = new BD();
        clientes = new Clientes(CodCli,Nome, Ender,  Bairro,  Cidade,  CEP, UF,  Email,  Fone, Celular);
    }
    
    
    
    
    public boolean localizarCliente(){
        sql = "select * from Clientes where CodCli = ?" ;
        try{
            statement = bd.connection.prepareStatement(sql);
            statement.setInt(1,clientes.getCodCli());
            resultSet = statement.executeQuery();
            resultSet.next();
            clientes.setCodCli(Integer.parseInt(resultSet.getString(1)));
            clientes.setNome(resultSet.getString(2));
            clientes.setEnder(resultSet.getString(3));
            clientes.setBairro(resultSet.getString(4));
            clientes.setCidade(resultSet.getString(5));
            clientes.setCEP(resultSet.getString(6));
            clientes.setUF(resultSet.getString(7));
            clientes.setEmail(resultSet.getString(8));
            clientes.setFone(resultSet.getString(9));
            clientes.setCelular(resultSet.getString(10));
            return true;
              
        }
        catch (SQLException erro)
        {
            return false;
        }
        
    }
    public String atualizarCliente(int operacao)
    {
        men="Operação realizada com sucesso!!!";
        try{
            if (operacao==INCLUSAO){
                sql = "Insert into Clientes values (?,?,?,?,?,?,?,?,?,?)";
                statement = bd.connection.prepareStatement(sql);
                statement.setInt(1,(clientes.getCodCli()));
                statement.setString(2,clientes.getNome());
                statement.setString(3,clientes.getEnder());
                statement.setString(4,clientes.getBairro());
                statement.setString(5,clientes.getCidade());
                statement.setString(6,clientes.getCEP());
                statement.setString(7,clientes.getUF());
                statement.setString(8,clientes.getEmail());
                statement.setString(9,clientes.getFone());
                statement.setString(10,clientes.getCelular());
                
            }
            
            else if (operacao==ALTERACAO){
                sql ="update Clientes set Nome = ? , Ender = ? , Bairro = ? , Cidade= ? , CEP = ? , UF = ? , Email = ?, Fone = ? , Celular = ? where CodCli = ? ";
                statement = bd.connection.prepareStatement(sql);
                statement.setInt(1,clientes.getCodCli());
                statement.setString(2,clientes.getNome());
                statement.setString(3,clientes.getEnder());
                statement.setString(4,clientes.getBairro());
                statement.setString(5,clientes.getCidade());
                statement.setString(6,clientes.getCEP());
                statement.setString(7,clientes.getUF());
                statement.setString(8,clientes.getEmail());
                statement.setString(9,clientes.getFone());
                statement.setString(10,clientes.getCelular());
                
               // statement.execute();
            }
            else if (operacao==EXCLUSAO){
                sql ="delete from Clientes where CodCli = ?";
                statement = bd.connection.prepareStatement(sql);
                statement.setString(1,Integer.toString(clientes.getCodCli()));
                
            }
            if (statement.executeUpdate()==0){
                 men="Falha na operação 1 !!";
            }
        }catch (SQLException erro){
               numero = erro.getErrorCode();
               if (numero==1062)
                   men="Este CodCli já existe!";
               else
               men="Falha na operação 2 "+ erro.toString();
        }
        return men;
    }}

