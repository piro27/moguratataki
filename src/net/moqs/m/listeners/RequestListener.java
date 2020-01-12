package net.moqs.m.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import javax.swing.JOptionPane;

import lombok.AllArgsConstructor;
import net.moqs.m.Main;

/**
 * Created by moqs.net on 2019/11/26.
 */
@AllArgsConstructor
public class RequestListener implements ActionListener {

	public static void main(String[] args) {
		new RequestListener(null).sendMessage("test");
	}

	private Main instance;

	@Override
	public void actionPerformed(ActionEvent event) {
		String value = JOptionPane.showInputDialog(this.instance.getJFrame(), "ご要望があれば入力してください。");
		if (value.trim().length() == 0) {
			JOptionPane.showMessageDialog(this.instance.getJFrame(), "内容を入力してください。 ");
			return;
		}

		new Thread(() -> {
			boolean bool = this.sendMessage(value);
			if (bool) {
				JOptionPane.showMessageDialog(this.instance.getJFrame(), "内容を送信しました。");
			} else {
				JOptionPane.showMessageDialog(this.instance.getJFrame(), "内容を送信できませんでした。");
			}
		}).start();
	}

	public boolean sendMessage(String message) {
		try {
			URL url = new URL("http://api.adogawa.moqs.net/moguratataki/request.php?message=" + message + "&nocache=" + UUID.randomUUID().toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3576.0 Safari/537.36");
			connection.setConnectTimeout(2500);
			connection.connect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
