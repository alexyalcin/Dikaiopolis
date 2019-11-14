/**
 * Drawable.java 1.0 Nov 11, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package engine;

import java.awt.Image;

/**
 * @author Alex
 *
 */
public interface Drawable extends Comparable<Drawable>{
	public default int getPriority() {
		return 10;
	}
	public Image getImage();
	
	public default int compareTo(Drawable other) {
		if (other.getPriority() > this.getPriority()) {
			return -1;
		}
		if (other.getPriority() < this.getPriority()) {
			return 1;
		}
		return (1);
	}
}
