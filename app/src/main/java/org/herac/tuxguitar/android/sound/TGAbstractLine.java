package org.herac.tuxguitar.android.sound;

import org.herac.tuxguitar.javax.sound.sampled.Control;
import org.herac.tuxguitar.javax.sound.sampled.Line;
import org.herac.tuxguitar.javax.sound.sampled.LineEvent;
import org.herac.tuxguitar.javax.sound.sampled.LineListener;
import org.herac.tuxguitar.javax.sound.sampled.LineUnavailableException;

import java.util.ArrayList;
import java.util.List;



public abstract class TGAbstractLine implements Line {
	
	private boolean open;
	private Info info;
	private Control[] controls;
	private List<LineListener> listeners;
	
	public TGAbstractLine(Info info) {
		this.info = info;
		this.listeners = new ArrayList<LineListener>();
	}
	
	public Info getLineInfo() {
		return this.info;
	}
	
	public void open() throws LineUnavailableException {
		this.open = true;
	}
	
	public void close() {
		this.open = false;
	}

	public boolean isOpen() {
		return this.open;
	}

	public void getControls(Control[] controls) {
		this.controls = controls;
	}
	
	public Control[] getControls() {
		return this.controls;
	}
	
	public Control getControl(Control.Type type) {
		if( this.controls != null ) {
			for(Control control : this.controls) {
				if( control.getType().toString().equals(type.toString()) ) {
					return control;
				}
			}
		}
		return null;
	}
	
	public boolean isControlSupported(Control.Type type) {
		return (this.getControl(type) != null);
	}
	
	public void addLineListener(LineListener listener) {
		if(!this.listeners.contains(listener)) {
			this.listeners.add(listener);
		}
	}

	public void removeLineListener(LineListener listener) {
		if( this.listeners.contains(listener)) {
			this.listeners.remove(listener);
		}
	}

	public void fireLineUpdateEvent(LineEvent event) {
		for(LineListener lineListener : this.listeners) {
			lineListener.update(event);
		}
	}
}
