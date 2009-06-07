package br.iav.ac.telas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PainelApresentacao extends JPanel {
	
	public PainelApresentacao() {
		this.setSize(549, 553);
		this.setLayout(null);
		ImageIcon imgSMA = new ImageIcon("res/blue-ford-mustang-gt.jpg");
		JLabel imagemSMA = new JLabel(imgSMA);
		imagemSMA.setBounds(0, 0, 549, 400);
		JLabel tituloSMAsigla = new JLabel("SMA");
		tituloSMAsigla.setFont(new Font("Tahoma", Font.BOLD, 25));
		tituloSMAsigla.setForeground(Color.BLUE);
		tituloSMAsigla.setBounds(245, 380, 200, 100);
		JLabel tituloSMAextenso = new JLabel("Sistema de Manutenção de Automóveis");
		tituloSMAextenso.setFont(new Font("Tahoma", Font.BOLD, 20));
		tituloSMAextenso.setForeground(Color.BLUE);
		tituloSMAextenso.setBounds(80, 415, 400, 100);
		JLabel tituloAutores = new JLabel("Este programa foi desenvolvido pela TD - Triple Damage Softwares ® 2009");
		tituloAutores.setBounds(95, 490, 400, 100);
		this.add(imagemSMA);
		this.add(tituloSMAsigla);
		this.add(tituloSMAextenso);
		this.add(tituloAutores);
	}

}