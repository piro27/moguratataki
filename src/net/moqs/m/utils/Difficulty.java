package net.moqs.m.utils;

import lombok.Getter;

/**
 * Created by moqs.net on 2019/11/05.
 */
@Getter
public enum Difficulty {

	Easy("簡単", 1000), Normal("普通", 500), Hard("難しい", 250), Crazy("裏", 10);

	private String mode;
	private int speed;

	Difficulty(String mode, int speed) {
		this.mode = mode;
		this.speed = speed;
	}

}
