package Programa.Visao.Cliente;

import Programa.Modelo.Cliente;
import Programa.Visao.Publisher;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class ClienteForm extends JPanel implements Publisher<Cliente>, ActionListener {

  JFormattedTextField cpfField;
  JFormattedTextField cepField;
  JTextField nomeField;
  JTextField emailField;
  JFormattedTextField nascField;
  JLabel statusLabel;

  JButton saveButton;

  public ClienteForm() {
    super();
    setLayout(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;

    setupScreenFields();
    placeScreenFields(gbc);
  }

  void setupScreenFields() {
    MaskFormatter cepFormatter;
    MaskFormatter cpfFormatter;
    MaskFormatter dateFormatter;

    try {
      cepFormatter = new MaskFormatter("#####-###");
      cpfFormatter = new MaskFormatter("###.###.###-##");
      dateFormatter = new MaskFormatter("##/##/####");
      
      cepFormatter.setPlaceholderCharacter('_');
      cpfFormatter.setPlaceholderCharacter('_');
      dateFormatter.setPlaceholderCharacter('_');
      
      
      cpfField = new JFormattedTextField(cpfFormatter);
      cepField = new JFormattedTextField(cepFormatter);

      nascField = new JFormattedTextField(dateFormatter);
      nascField.setToolTipText("Formato: dd/MM/yyyy");
    } catch (Exception e) {
    }
    nomeField = new JTextField();
    emailField = new JTextField();


    saveButton = new JButton("Salvar");
    saveButton.addActionListener(this);

    statusLabel = new JLabel("Editando");
  }

  void placeScreenFields(GridBagConstraints gbc) {
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;

    gbc.gridx = 0;
    gbc.gridy = 0;
    add(nomeField, gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    add(cpfField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    add(emailField, gbc);

    gbc.gridx = 1;
    gbc.gridy = 1;
    add(cepField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    add(nascField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    add(statusLabel, gbc);

    gbc.gridx = 1;
    gbc.gridy = 3;
    add(saveButton, gbc);
  }

  public void createClienteFromForm() {
    String nome = nomeField.getText().trim();
    String cpf = cpfField.getText().replaceAll("[^0-9]", "");
    String email = emailField.getText().trim();
    String cep = cepField.getText().replaceAll("[^0-9]", "");
    Date nasc;
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      nasc = new Date(sdf.parse(nascField.getText()).getTime());
    } catch (Exception e) {
      System.err.println("Erro ao converter data: " + e.getMessage() + nascField.getText());
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    createClienteFromForm();
    if (e.getSource() == saveButton) {
    }
  }

}
