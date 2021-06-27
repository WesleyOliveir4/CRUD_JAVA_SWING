/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicavendas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author wesley
 */
public class GuiCadastroClientes extends JFrame {

    JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label13, lblData;
    JButton btGravar, btAlterar, btExcluir, btNovo, btLocalizar, btCancelar, btSair;
    JButton btPrim, btAnt, btProx, btUlt;
    JPanel painel, painelBotoes, painelREG;
    JFrame janela;
    static JTextField tfCodCli, tfNome, tfEnder, tfBairro, tfCidade, tfCEP, tfUF, tfEmail, tfFone, tfCelular;
    private ClientesDAO Clientes;
    private ResultSet resultSet;
     

    public static void main(String args[]) {
        JFrame janela = new GuiCadastroClientes();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }

    public GuiCadastroClientes() {
        inicializacomponentes();
        definirEventos();

    }

    public void inicializacomponentes() {
        setLayout(new BorderLayout());     //define layout da janela

        painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.PAGE_AXIS));    //define layout do painel
        this.add(painel, BorderLayout.NORTH);

        painelBotoes = new JPanel(new FlowLayout()); //define layout do painelBotoes    
        this.add(painelBotoes, BorderLayout.CENTER);

        painelREG = new JPanel(new FlowLayout());  //define layout do painelREG
        this.add(painelREG, BorderLayout.SOUTH);

        setTitle("Cadastro de alunos");
        setBounds(300, 600, 750, 250);
        label1 = new JLabel("Codigo do cliente : ");
        label2 = new JLabel("Nome: ");
        label3 = new JLabel("Endereço : ");
        label4 = new JLabel("Bairro: ");
        label5 = new JLabel("Cidade : ");
        label6 = new JLabel("CEP: ");
        label7 = new JLabel("UF : ");
        label8 = new JLabel("Email: ");
        label9 = new JLabel("Telefone : ");
        label10 = new JLabel("Celular : ");

        lblData = new JLabel("Data Nascto:");
        label13 = new JLabel("Movimentação de Registros");
        tfCodCli = new JTextField(4);
        tfNome = new JTextField(35);
        tfEnder = new JTextField(45);
        tfBairro = new JTextField(20);
        tfCidade = new JTextField(20);
        tfCEP = new JTextField(9);
        tfUF = new JTextField(2);
        tfEmail = new JTextField(60);
        tfFone = new JTextField(8);
        tfCelular = new JTextField(11);


        btGravar = new JButton("Gravar");
        btAlterar = new JButton("Alterar");
        btExcluir = new JButton("Excluir");
        btLocalizar = new JButton("Localizar");
        btNovo = new JButton("Novo");
        btCancelar = new JButton("Cancelar");
      
        btSair = new JButton("Sair");
        btPrim = new JButton("<<");
        btPrim.setToolTipText("Primeiro");
        btAnt = new JButton("<");
        btAnt.setToolTipText("Anterior");
        btProx = new JButton(">");
        btProx.setToolTipText("Próximo");
        btUlt = new JButton(">>");
        btUlt.setToolTipText("Ultimo");

        painel.add(label1);
        painel.add(tfCodCli);
        painel.add(label2);
        painel.add(tfNome);
        painel.add(label3);
        painel.add(tfEnder);
        painel.add(label4);
        painel.add(tfBairro);
        painel.add(label5);
        painel.add(tfCidade);
        painel.add(label6);
        painel.add(tfCEP);
        painel.add(label7);
        painel.add(tfUF);
        painel.add(label8);
        painel.add(tfEmail);
        painel.add(label9);
        painel.add(tfFone);
        painel.add(label10);
        painel.add(tfCelular);

        painelBotoes.add(btNovo);
        painelBotoes.add(btLocalizar);
        painelBotoes.add(btGravar);
        painelBotoes.add(btAlterar);
        painelBotoes.add(btExcluir);
        painelBotoes.add(btCancelar);
       // painelBotoes.add(btCons);
        painelBotoes.add(btSair);
        painelREG.add(label13);
        painelREG.add(btPrim);
        painelREG.add(btAnt);
        painelREG.add(btProx);
        painelREG.add(btUlt);
        setResizable(true);

        setBotoes(true, true, false, false, false, false);
        Clientes = new ClientesDAO();
        if (!Clientes.bd.getConnection()) {
            JOptionPane.showMessageDialog(null, "Falha na conexão!");
            System.exit(0);
        }

        tabelaClientes();
        
        carregaDados();
    }

    public void setBotoes(boolean bNovo, boolean bLocalizar, boolean bGravar,
            boolean bAlterar, boolean bExcluir, boolean bCancelar) {

        btNovo.setEnabled(bNovo);
        btLocalizar.setEnabled(bLocalizar);
        btGravar.setEnabled(bGravar);
        btAlterar.setEnabled(bAlterar);
        btExcluir.setEnabled(bExcluir);
        btCancelar.setEnabled(bCancelar);
    }

    public void definirEventos() {
        btSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Clientes.bd.close();
                System.exit(0);
            }
        });

        btProx.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    resultSet.next();
                    carregaDados();
                } catch (SQLException erro) {

                }
            }
        });

        btAnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    resultSet.previous();
                    carregaDados();
                } catch (SQLException erro) {

                }
            }
        });

        btPrim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    resultSet.first();
                    carregaDados();
                } catch (SQLException erro) {

                }
            }
        });

        btUlt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    resultSet.last();
                    carregaDados();
                } catch (SQLException erro) {

                }
            }
        });

        btNovo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                limparcampos();

                setBotoes(false, false, true, false, false, true);
                tfCodCli.requestFocus();
            }
        });

        btCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                limparcampos();

            }
        });

        btGravar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if (tfNome.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O nome não pode ser vazio!");
                    tfNome.requestFocus();
                    return;
                }
                if (tfEnder.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O Endereço não pode ser vazio!");
                    tfEnder.requestFocus();
                    return;
                }
                if (tfBairro.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O Bairro não pode ser vazio!");
                    tfBairro.requestFocus();
                    return;
                }
                if (tfCidade.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O Cidade não pode ser vazio!");
                    tfCidade.requestFocus();
                    return;
                }
                if (tfCEP.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O CEP não pode ser vazio!");
                    tfCEP.requestFocus();
                    return;
                }
                if (tfUF.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O UF não pode ser vazio!");
                    tfUF.requestFocus();
                    return;
                }
                if (tfFone.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O Fone não pode ser vazio!");
                    tfFone.requestFocus();
                    return;
                }
                if (tfCelular.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O Celular não pode ser vazio!");
                    tfCelular.requestFocus();
                    return;
                }

                
                Clientes.clientes.setNome(tfNome.getText());
                Clientes.clientes.setEnder(tfEnder.getText());
                Clientes.clientes.setBairro(tfBairro.getText());
                Clientes.clientes.setCidade(tfCidade.getText());
                Clientes.clientes.setCEP(tfCEP.getText());
                Clientes.clientes.setUF(tfUF.getText());
                Clientes.clientes.setEmail(tfEmail.getText());
                Clientes.clientes.setFone(tfFone.getText());
                Clientes.clientes.setCelular(tfCelular.getText());

                JOptionPane.showMessageDialog(null, Clientes.atualizarCliente(ClientesDAO.INCLUSAO));
                limparcampos();
                tabelaClientes();

            }
        });

        btAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tfNome.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O nome não pode ser vazio!");
                    tfNome.requestFocus();
                    return;
                }
                if (tfEnder.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O Endereço não pode ser vazio!");
                    tfEnder.requestFocus();
                    return;
                }
                if (tfBairro.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O Bairro não pode ser vazio!");
                    tfBairro.requestFocus();
                    return;
                }
                if (tfCidade.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O Cidade não pode ser vazio!");
                    tfCidade.requestFocus();
                    return;
                }
                if (tfCEP.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O CEP não pode ser vazio!");
                    tfCEP.requestFocus();
                    return;
                }
                if (tfUF.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O UF não pode ser vazio!");
                    tfUF.requestFocus();
                    return;
                }
                if (tfFone.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O Fone não pode ser vazio!");
                    tfFone.requestFocus();
                    return;
                }
                if (tfCelular.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "O Celular não pode ser vazio!");
                    tfCelular.requestFocus();
                    return;
                }
                
                
                Clientes.clientes.setNome(tfNome.getText());
                Clientes.clientes.setEnder(tfEnder.getText());
                Clientes.clientes.setBairro(tfBairro.getText());
                Clientes.clientes.setCidade(tfCidade.getText());
                Clientes.clientes.setCEP(tfCEP.getText());
                Clientes.clientes.setUF(tfUF.getText());
                Clientes.clientes.setEmail(tfEmail.getText());
                Clientes.clientes.setFone(tfFone.getText());
                Clientes.clientes.setCelular(tfCelular.getText());

              
                JOptionPane.showMessageDialog(null, Clientes.atualizarCliente(ClientesDAO.ALTERACAO));
                limparcampos();
                tabelaClientes();
    
            }
        });

        btExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Clientes.clientes.setCodCli(Integer.parseInt(tfCodCli.getText()));
                Clientes.localizarCliente();
                int n = JOptionPane.showConfirmDialog(null, Clientes.clientes.getNome(),
                        "Excluir o Cliente?", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, Clientes.atualizarCliente(ClientesDAO.EXCLUSAO));
                    limparcampos();
                    tabelaClientes();
                }
            }
        });

        btLocalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarCampos();

            }
        });

    }

    public void limparcampos() {
        
        tfNome.setText("");
        tfEnder.setText("");
        tfBairro.setText("");
        tfCidade.setText("");
        tfCEP.setText("");
        tfUF.setText("");
        tfEmail.setText("");
        tfFone.setText("");
        tfCelular.setText("");

        
        setBotoes(true, true, false, false, false, false);
    }

    public void atualizarCampos() {
        Clientes.clientes.setCodCli(Integer.parseInt(tfCodCli.getText()));
        if (Clientes.localizarCliente()) {
            tfCodCli.setText(Integer.toString(Clientes.clientes.getCodCli()));
            tfNome.setText(Clientes.clientes.getNome());
            tfEnder.setText(Clientes.clientes.getEnder());
            tfBairro.setText(Clientes.clientes.getBairro());
            tfCidade.setText(Clientes.clientes.getCidade());
            tfCEP.setText(Clientes.clientes.getCEP());
            tfUF.setText(Clientes.clientes.getUF());
            tfEmail.setText(Clientes.clientes.getEmail());
            tfFone.setText(Clientes.clientes.getFone());
            tfCelular.setText(Clientes.clientes.getCelular());

                            
            setBotoes(true, true, false, true, true, true);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            limparcampos();
        }
    }

    public void tabelaClientes() {
        try {
            String sql = "Select * from Clientes";
            PreparedStatement statement = Clientes.bd.connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Problemas na conexão!\n" + erro.toString());
        }

    }

    public void carregaDados() {
        try {
            String vazio = " ";
            if (resultSet.isAfterLast()) {
                resultSet.last();
            }
            if (resultSet.isBeforeFirst()) {
                resultSet.first();
            }

            tfCodCli.setText(resultSet.getString("CodCli"));
            tfNome.setText(resultSet.getString("Nome"));
            tfEnder.setText(resultSet.getString("Ender"));
            tfBairro.setText(resultSet.getString("Bairro"));
            tfCidade.setText(resultSet.getString("Cidade"));
            tfCEP.setText(resultSet.getString("CEP"));
            tfUF.setText(resultSet.getString("UF"));
            tfEmail.setText(resultSet.getString("Email"));
            tfFone.setText(resultSet.getString("Fone"));
            tfCelular.setText(resultSet.getString("Celular"));

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Problemas na conexão!\n" + erro.toString());
        }
    }

   
}
