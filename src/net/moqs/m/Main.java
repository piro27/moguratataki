package net.moqs.m;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import lombok.Data;
import net.moqs.m.adapters.ButtonComponentListener;
import net.moqs.m.listeners.AnimalListener;
import net.moqs.m.listeners.ButtonListener;
import net.moqs.m.listeners.DifficultyListener;
import net.moqs.m.listeners.OSLListener;
import net.moqs.m.listeners.RankingListener;
import net.moqs.m.utils.RankingSystem;

/**
 * Created by moqs.net on 2019/10/08.
 */
@Data
public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private String name;

	private int size = 4;
	private int delay = 1000;

	private JFrame jFrame;

	private List<JButton> buttons = new ArrayList<>();
	private List<JButton> removeButtons = new ArrayList<>();

	private ExecutorService threadPool = Executors.newFixedThreadPool(32);

	private Date date;

	private BufferedImage mainImage;
	private BufferedImage clashImage;

	private ButtonComponentListener buttonComponentListener;

	private RankingSystem rankingSystem;
	private RankingListener rankingListener;

	public void run() {
		System.out.println("初期化中...");

		this.jFrame = new JFrame("もぐら叩き");
		this.jFrame.setSize(720, 720);
		this.jFrame.setLocationRelativeTo(null);
		this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.jFrame.setLayout(new GridLayout(this.size, this.size));

		String value = JOptionPane.showInputDialog(this.jFrame, "名前を入力してください。\nOK を押すと自動的にゲームを開始します。");
		if (value != null) {
			this.name = value;
		} else {
			this.name = "Guest";
		}
		if (this.name.length() == 0) {
			this.name = "Guest";
		}
		System.out.println("名前: " + this.name);

		System.out.println("メニューバーを設定中...");

		JMenuBar jMenuBar = new JMenuBar();

		JMenu jMenu = new JMenu("ゲーム");

		JMenuItem difficulty = new JMenuItem("難易度");
		difficulty.addActionListener(new DifficultyListener(this));
		jMenu.add(difficulty);

		JMenuItem animal = new JMenuItem("動物");
		animal.addActionListener(new AnimalListener(this));
		jMenu.add(animal);

		JMenuItem ranking = new JMenuItem("ランキング");
		ranking.addActionListener(this.rankingListener = new RankingListener(this));
		jMenu.add(difficulty);

		jMenu.add(ranking);

		JMenu help = new JMenu("ヘルプ");

		JMenuItem osl = new JMenuItem("オープンソースライセンス");
		osl.addActionListener(new OSLListener(this));
		help.add(osl);

		jMenuBar.add(jMenu);
		jMenuBar.add(help);

		System.out.println("メニューバーを設定しました。");

		this.jFrame.setJMenuBar(jMenuBar);

		this.date = new Date();

		this.buttonComponentListener = new ButtonComponentListener(this);
		this.rankingSystem = new RankingSystem(this);

		System.out.println("画像ファイルを読み込んでいます...");

		final BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("1.png"));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		this.mainImage = bufferedImage;

		BufferedImage clashImage;
		try {
			clashImage = ImageIO.read(new File("2.png"));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		this.clashImage = clashImage;

		System.out.println("画像ファイルを設定しました。");

		System.out.println("ボタンデータの初期化中...");

		for (int integer = 1; integer <= this.size * this.size; integer++) {
			JButton jButton = new JButton(new ImageIcon("1.png"));
			jButton.addComponentListener(this.buttonComponentListener);
			jButton.setEnabled(false);
			this.buttons.add(jButton);
			jButton.addActionListener(new ButtonListener(this));
			this.jFrame.add(jButton);
		}

		this.jFrame.setVisible(true);

		this.threadPool.execute(() -> {
			while (true) {
				List<JButton> copyList = new ArrayList<>();
				copyList.addAll(this.buttons);

				Collections.shuffle(copyList);
				if (copyList.size() > 0) {
					JButton button = copyList.get(0);

					button.setEnabled(true);

					sleep(this.delay);

					button.setEnabled(false);
				}
			}
		});

		System.out.println("ボタンデータの初期化が完了しました。");

		System.out.println("全ての初期化しました。");
	}

	public void sleep(int thread) {
		try {
			Thread.sleep(thread);
		} catch (Exception e) {
		}
	}

}
