package net.moqs.m.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import lombok.AllArgsConstructor;
import net.moqs.m.Main;
import net.moqs.m.utils.Time;

/**
 * Created by moqs.net on 2019/11/05.
 */
@AllArgsConstructor
public class ButtonListener implements ActionListener {

	private Main instance;

	@Override
	public void actionPerformed(ActionEvent event) {
		JButton jButton = (JButton) event.getSource();

		jButton.setEnabled(false);
		if (this.instance.getClashImage() != null) {
			jButton.setIcon(new ImageIcon(this.instance.getClashImage()));
		} else {
			jButton.setIcon(new ImageIcon("2.png"));
		}

		this.instance.getButtons().remove(jButton);

		this.instance.getButtonComponentListener().update(jButton);

		JOptionPane.showMessageDialog(this.instance.getJFrame(), "イテッ");


		if (this.instance.getButtons().size() == 0) {
			Time time = Time.getTimeFromDate(this.instance.getDate());

			this.instance.getRankingSystem().save(this.instance.getDate(), time.getDate());

			long millisecond = time.getDate().getTime() - this.instance.getDate().getTime();

			JOptionPane.showMessageDialog(this.instance.getJFrame(), "もぐらをすべて叩きました！\n\nタイム: " + time.toString());

			this.instance.getRankingListener().highlight(millisecond);
		}
	}
}
