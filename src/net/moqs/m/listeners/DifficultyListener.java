package net.moqs.m.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import lombok.AllArgsConstructor;
import net.moqs.m.Main;
import net.moqs.m.utils.Difficulty;

/**
 * Created by moqs.net on 2019/11/05.
 */
@AllArgsConstructor
public class DifficultyListener implements ActionListener {

	private Main instance;

	@Override
	public void actionPerformed(ActionEvent event) {
		String values[] = new String[Difficulty.values().length];
		int size = 0;
		for (Difficulty difficulty : Difficulty.values()) {
			values[size] = difficulty.name() + " (" + difficulty.getMode() + ")";
			size++;
		}
		int item = JOptionPane.showOptionDialog(this.instance.getJFrame(), "難易度を選択してください。", "難易度",
				JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, values, values[0]);

		if (item != JOptionPane.CLOSED_OPTION) {
			Difficulty difficulty = Difficulty.values()[item];
			System.out.println("Selected: " + difficulty.getMode() + ", Speed: " + difficulty.getSpeed());

			JOptionPane.showMessageDialog(this.instance.getJFrame(), "OK を押すと選択した難易度でゲームを開始します。");

			this.instance.setDelay(difficulty.getSpeed());
			this.instance.setDate(new Date());
		}
	}

}
