package net.moqs.m.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;

import lombok.AllArgsConstructor;
import net.moqs.m.Main;
import net.moqs.m.adapters.ButtonComponentListener;

/**
 * Created by moqs.net on 2019/11/05.
 */
@AllArgsConstructor
public class AnimalListener implements ActionListener {

	private Main instance;

	@Override
	public void actionPerformed(ActionEvent event) {
		File animals = new File("animals");
		animals.mkdir();
		File animalsClash = new File("animals_crash");
		animalsClash.mkdir();

		File[] files = animals.listFiles();

		if (files.length == 0) {
			JOptionPane.showMessageDialog(this.instance.getJFrame(), "animals フォルダ内にファイルを入れてください。");
			return;
		}

		String values[] = new String[files.length];
		int size = 0;
		for (File file : animals.listFiles()) {
			String name = FilenameUtils.removeExtension(file.getName());
			if (!name.equals("_c")) {
				values[size] = name;
				size++;
			}
		}
		int item = JOptionPane.showOptionDialog(this.instance.getJFrame(), "叩きたい動物を選択してください。",
				"動物選択",  JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, values, values[0]);

		if (item != JOptionPane.CLOSED_OPTION) {
			File file = files[item];
			System.out.println("Selected: " + file.getPath());

			BufferedImage bufferedImage;
			try {
				bufferedImage = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			this.instance.setMainImage(bufferedImage);

			BufferedImage clashImage;
			try {
				clashImage = ImageIO.read(new File(animalsClash, file.getName()));
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			this.instance.setClashImage(clashImage);

			for (JButton jButton : this.instance.getButtons()) {
				jButton.setIcon(new ImageIcon(bufferedImage));
				jButton.addComponentListener(new ButtonComponentListener(this.instance));
				this.instance.getButtonComponentListener().update(jButton);
			}
		}
	}
}
