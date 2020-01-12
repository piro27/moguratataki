package net.moqs.m.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import lombok.Data;
import net.moqs.m.Main;

/**
 * Created by moqs.net on 2019/11/12.
 */
public class RankingSystem {

	private Main instance;
	private File folder;

	public RankingSystem(Main instance) {
		this.instance = instance;

		this.folder = new File("Ranking");
		this.folder.mkdir();
	}

	public void save(Date start, Date end) {
		this.save(end.getTime() - start.getTime());
	}

	public void save(long millisecond) {
		System.out.println("秒数: " + millisecond + " (" + (millisecond / 1000) + "秒)");

		File file = new File(this.folder, millisecond + ".moqs");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				FileWriter fileWriter = new FileWriter(file);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(this.instance.getName());
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public List<UserData> getRanking() {
		List<UserData> list = new ArrayList<>();

		File[] files = this.folder.listFiles();

		Arrays.sort(files);

		for (File file : this.folder.listFiles()) {
			String ms = FilenameUtils.removeExtension(file.getName());

			int millisecond;
			try {
				millisecond = Integer.valueOf(ms).intValue();
			} catch (Exception e) {
				continue;
			}

			try {
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);

				String name = bufferedReader.readLine();
				bufferedReader.close();

				UserData userData = new UserData();
				userData.name = name;
				userData.milliSecond = millisecond;

				list.add(userData);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public UserData getRanking(int i) {
		List<UserData> ranking = this.getRanking();
		if (ranking.size() > i) {
			return ranking.get(i);
		}
		return null;
	}

	@Data
	public class UserData {
		private String name;
		private int milliSecond;

		public int getShortTime() {
			return this.milliSecond / 1000;
		}
	}

}
