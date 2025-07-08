package Programa.Visao;

import Programa.Persistencia.BancoDeDados;
import Programa.Visao.Screens.Cliente.ClienteScreen;
import Programa.Visao.Shared.RoundedButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainLayout extends  JFrame {

  private int width;
  private int height;
  private BancoDeDados db;

  private JPanel mainContentPanel;

  public MainLayout(BancoDeDados db) {
    super("SISTEMA FINANCEIRO SIMPLIFICADO");

    this.db = db;

    height = 720;
    width = 1280;
    setResizable(true);
    setSize(width, height);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new GridBagLayout());
    

    mainContentPanel = new JPanel(new BorderLayout());
    mainContentPanel.setBackground(Color.WHITE);
    mainContentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    mainContentPanel.add(new ClienteScreen(db));

    Sidebar sidebar = new Sidebar(db);
    sidebar.setOnMenuItemClick(this::changeContentPanel);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    
    
    JPanel panel1 = new JPanel(new BorderLayout());
    panel1.setBackground(Color.WHITE);
    panel1.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK),
      BorderFactory.createEmptyBorder(8, 10, 10, 10)
    ));
    panel1.add(new JLabel("GERENCIADOR DE TRANSAÇÕES", SwingConstants.CENTER), BorderLayout.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0.1;
    gbc.weighty = 0.1;
    this.add(panel1, gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 0.1;
    gbc.weighty = 0.9;
    add(sidebar, gbc);
    
    JLabel iconLabel = null;
    try {
      ImageIcon icon = new ImageIcon(getClass().getResource("/resources/assets/sign-out-icon.png"));
      Image scaledImage = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
      ImageIcon scaledIcon = new ImageIcon(scaledImage);
      iconLabel = new JLabel(scaledIcon);
      iconLabel.setSize(new Dimension(60, 10));
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    JPanel panel3 = new JPanel(new BorderLayout());
    panel3.setBackground(Color.WHITE);
    panel3.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK),
      BorderFactory.createEmptyBorder(8, 10, 10, 10)
    ));
    // if (iconLabel != null) {
    //   panel3.add(iconLabel, BorderLayout.EAST, SwingConstants.CENTER);
    //   panel3.setAlignmentX(CENTER_ALIGNMENT);
    //   panel3.setAlignmentY(CENTER_ALIGNMENT);
    // } else {
    //   RoundedButton btn = new RoundedButton("Sair",  10, 60, 10, Color.WHITE, Color.WHITE, Color.GRAY, Color.DARK_GRAY);
    //   panel3.add(btn, BorderLayout.EAST);
    // }

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
