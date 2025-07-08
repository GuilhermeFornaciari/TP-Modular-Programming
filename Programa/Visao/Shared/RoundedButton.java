package Programa.Visao.Shared;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Ellipse2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class RoundedButton extends JButton {

  private int radius, width, height;
  private Color focusBgColor = new Color(0x193CB8);
  private Color defaultBgColor = Color.WHITE;

  private Color focusFgColor = Color.WHITE;
  private Color defaultFgColor = Color.DARK_GRAY;

  public RoundedButton(String label, int radius, int width, int height) {
    super(label);
    this.radius = radius;
    this.width = width;
    this.height = height;
    setupButtonAppearance();
  }

  public RoundedButton(String label, int radius, int width, int height, Color focusBgColor, Color defaultBgColor, Color focusFgColor, Color defaultFgColor) {
    super(label);
    this.radius = radius;
    this.width = width;
    this.height = height;
    this.focusBgColor = focusBgColor;
    this.focusFgColor = focusFgColor;
    this.defaultBgColor = defaultBgColor;
    this.defaultFgColor = defaultFgColor;
    setupButtonAppearance();
  }

  private void setupButtonAppearance() {
    setContentAreaFilled(false);
    setFocusPainted(false);
    setBorderPainted(false);
    setOpaque(false);

    setPreferredSize(new java.awt.Dimension(width, height));

    setHorizontalAlignment(SwingConstants.CENTER);
    setVerticalAlignment(SwingConstants.CENTER);
    setHorizontalTextPosition(SwingConstants.CENTER);
    setVerticalTextPosition(SwingConstants.CENTER);

    if (isFocusOwner()) {
      setBackground(focusBgColor);
      setForeground(focusFgColor);
    } else {
      setBackground(defaultBgColor);
      setForeground(defaultFgColor);
    }
    setBorder(BorderFactory.createLineBorder(getBackground(), 10));

    addFocusListener(focusListener);
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    if (getModel().isArmed()) {
      g2.setColor(getBackground().darker());
    } else {
      g2.setColor(getBackground());
    }
    g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
    super.paintComponent(g);
    g2.dispose();
  }

  @Override
  public boolean contains(int x, int y) {
    Ellipse2D shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
    return shape.contains(x, y);
  }


  private FocusListener focusListener = new FocusListener() {
      
    public void focusGained(FocusEvent e) {
      setBackground(focusBgColor);
      setForeground(focusFgColor);
      setBorder(BorderFactory.createLineBorder(getBackground(), 10));
    }

    public void focusLost(FocusEvent e) {
      setBackground(defaultBgColor);
      setForeground(defaultFgColor);
      setBorder(BorderFactory.createLineBorder(getBackground(), 10));
    }
  };

  public void setFocusBgColor(Color focusBgColor) {
    this.focusBgColor = focusBgColor;
  }
  public void setDefaultBgColor(Color defaultBgColor) {
    this.defaultBgColor = defaultBgColor;
  }

  public void setFocusFgColor(Color focusFgColor) {
    this.focusFgColor = focusFgColor;
  }
  public void setDefaultFgColor(Color defaultBgColor) {
    this.defaultBgColor = defaultBgColor;
  }

  public void redraw() {
    setupButtonAppearance();
  }

};
