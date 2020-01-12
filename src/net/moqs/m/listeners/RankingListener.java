package net.moqs.m.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import lombok.AllArgsConstructor;
import net.moqs.m.Main;
import net.moqs.m.utils.RankingSystem;
import net.moqs.m.utils.RankingSystem.UserData;

/**
 * Created by moqs.net on 2019/11/05.
 */
@AllArgsConstructor
public class RankingListener implements ActionListener {

	private Main instance;

	@Override
	public void actionPerformed(ActionEvent event) {
		RankingSystem rankingSystem = this.instance.getRankingSystem();

		String rank =  "ランキング\n"
				+ "\n";
				/* + "1位: " + ranking1.getName() + " さん - " + ranking1.getShortTime() + "秒\n" // TODO: ランキング
				+ "2位: " + rankingSystem.getRanking(2) + "\n"
				+ "3位: " + rankingSystem.getRanking(3); */

		UserData ranking1 = rankingSystem.getRanking(0);
		if (ranking1 != null) {
			rank += "1位: " + ranking1.getName() + " さん - " + ranking1.getShortTime() + "秒\n";
		}

		UserData ranking2 = rankingSystem.getRanking(1);
		if (ranking2 != null) {
			rank += "2位: " + ranking2.getName() + " さん - " + ranking2.getShortTime() + "秒\n";
		}

		UserData ranking3 = rankingSystem.getRanking(2);
		if (ranking3 != null) {
			rank += "3位: " + ranking3.getName() + " さん - " + ranking3.getShortTime() + "秒";
		}

		JOptionPane.showMessageDialog(this.instance.getJFrame(), rank);
	}

	public void highlight(long time) {
		RankingSystem rankingSystem = this.instance.getRankingSystem();

		String rank =  "ランキング\n"
				+ "\n";
				/* + "1位: " + ranking1.getName() + " さん - " + ranking1.getShortTime() + "秒\n" // TODO: ランキング
				+ "2位: " + rankingSystem.getRanking(2) + "\n"
				+ "3位: " + rankingSystem.getRanking(3); */

		UserData ranking1 = rankingSystem.getRanking(0);
		if (ranking1 != null) {
			rank += "1位: " + ranking1.getName() + " さん - " + ranking1.getShortTime() + "秒";
			if (ranking1.getMilliSecond() == time) {
				rank += " <- あなたのスコア！";
			}
			rank += "\n";
		}

		UserData ranking2 = rankingSystem.getRanking(1);
		if (ranking2 != null) {
			rank += "2位: " + ranking2.getName() + " さん - " + ranking2.getShortTime() + "秒";
			if (ranking2.getMilliSecond() == time) {
				rank += " <- あなたのスコア！";
			}
			rank += "\n";
		}

		UserData ranking3 = rankingSystem.getRanking(2);
		if (ranking3 != null) {
			rank += "3位: " + ranking3.getName() + " さん - " + ranking3.getShortTime() + "秒";
			if (ranking3.getMilliSecond() == time) {
				rank += " <- あなたのスコア！";
			}
			rank += "\n";
		}

		JOptionPane.showMessageDialog(this.instance.getJFrame(), rank);
	}

}
