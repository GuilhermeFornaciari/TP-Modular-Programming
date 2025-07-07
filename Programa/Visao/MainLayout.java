package Programa.Visao;

import Programa.Persistencia.BancoDeDados;
import Programa.Visao.Screens.Cliente.ClienteScreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainLayout extends  JFrame {

  private int width;
  private int height;
  private BancoDeDados db;

  private JPanel mainContentPanel;

  public MainLayout(BancoDeDados db) {
    super("GERENCIADOR DE TRAN**ÇÕES 2000");

    this.db = db;

    height = 720;
    width = 1280;
    setResizable(false);
    setSize(width, height);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new GridBagLayout());
    

    mainContentPanel = new JPanel(new BorderLayout());
    mainContentPanel.setBackground(Color.WHITE);
    mainContentPanel.add(new ClienteScreen(db));

    Sidebar sidebar = new Sidebar(db);
    sidebar.setOnMenuItemClick(this::changeContentPanel);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    
    
    JPanel panel1 = new JPanel();
    panel1.setBackground(Color.BLUE);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0.1;
    gbc.weighty = 0.1;
    this.add(panel1, gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 0.1;
    gbc.weighty = 0.9;
    this.add(sidebar, gbc);
    
    JPanel panel3 = new JPanel();
    panel3.setBackground(Color.GREEN);
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.weightx = 0.9;
    gbc.weighty = 0.1;
    this.add(panel3, gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.weightx = 0.9;
    gbc.weighty = 0.9;
    this.add(mainContentPanel, gbc);
    
  }

  public void changeContentPanel(JPanel panel) {
    mainContentPanel.removeAll();
    mainContentPanel.add(panel, BorderLayout.CENTER);
    mainContentPanel.revalidate();
    mainContentPanel.repaint();
  }
}
