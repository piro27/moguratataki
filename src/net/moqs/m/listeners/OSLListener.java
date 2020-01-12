package net.moqs.m.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import lombok.AllArgsConstructor;
import net.moqs.m.Main;

/**
 * Created by moqs.net on 2019/11/05.
 */
@AllArgsConstructor
public class OSLListener implements ActionListener {

	private Main instance;

	@Override
	public void actionPerformed(ActionEvent event) {
		JOptionPane.showMessageDialog(this.instance.getJFrame(), "Copyright (c) 2019 moqs.net\n"
				+ "\n"
				+ "GitHub: https://s.moqs.net/mog\n"
				+ "License: The MIT License\n"
				+ "\n"
				+ "\n"
				+ "Lombok: (https://github.com/rzwitserloot/lombok/blob/master/LICENSE)\n"
				+ "- Copyright (C) 2009-2015 The Project Lombok Authors.\n"
				+ "- The MIT License\n"
				+ "\n"
				+ "commons-io (https://github.com/apache/commons-io)\n"
				+ "- Copyright 2002-2019 The Apache Software Foundation\n"
				+ "- Apache License 2.0");
	}

}
