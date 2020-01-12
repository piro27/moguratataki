package net.moqs.m.adapters;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import lombok.AllArgsConstructor;
import net.moqs.m.Main;

/**
 * Created by moqs.net on 2019/11/05.
 */
@AllArgsConstructor
public class ButtonComponentListener extends ComponentAdapter {

	private Main instance;

	@Override
	public void componentResized(ComponentEvent event) {
		JButton button = (JButton) event.getComponent();
		this.update(button);
	}

	public void update(JButton jButton) {
		Dimension dimension = jButton.getSize();
		Insets insets = jButton.getInsets();

		dimension.width -= insets.left + insets.right;
		dimension.height -= insets.top + insets.bottom;
		if (dimension.width > dimension.height) {
			dimension.width = -1;
		} else {
			dimension.height = -1;
		}

		Image image;
		if (this.instance.getButtons().contains(jButton)) {
			image = this.instance.getMainImage().getScaledInstance(dimension.width, dimension.height, java.awt.Image.SCALE_SMOOTH);
		} else {
			image = this.instance.getClashImage().getScaledInstance(dimension.width, dimension.height, java.awt.Image.SCALE_SMOOTH);
		}

		jButton.setIcon(new ImageIcon(image));
	}

}
