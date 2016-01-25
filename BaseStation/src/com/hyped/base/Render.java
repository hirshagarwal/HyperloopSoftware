/**
 * 
 */
package com.hyped.base;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

/**
 * @author Hirsh Agarwal
 *
 */


public class Render implements GLEventListener{
	
	private static GraphicsEnvironment graphicsEnvironment;
	private static boolean isFullScreen = false;
	public static DisplayMode dm, dm_old;
	private static Dimension xgraphic;
	public static Point point = new Point(0,0);
	
	private GLU glu = new GLU();
	
	@Override
	public void display(GLAutoDrawable drawable) {

	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		
	}
	
	public static void main(String[] args){
		//Setup OpenGL
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		
		//Canvas
		GLCanvas glCanvas = new GLCanvas(caps);
		Render r = new Render();
		glCanvas.addGLEventListener(r);
		glCanvas.setSize(800, 800);
		
		final FPSAnimator animator = new FPSAnimator(glCanvas, 300, true);
		
		final JFrame frame = new JFrame("HypED");
		
		frame.getContentPane().add(glCanvas);
		
		//Shutdown
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				if(animator.isStarted())
					animator.stop();
				System.exit(0);
			}
		});
		
		frame.setSize(frame.getContentPane().getPreferredSize());
		
		//Center Screen on Startup
		graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		GraphicsDevice[] devices = graphicsEnvironment.getScreenDevices();
		
		dm_old = devices[0].getDisplayMode();
		dm = dm_old;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int windowX = Math.max(0, (screenSize.width - frame.getWidth())/2);
		int windowY = Math.max(0, (screenSize.height - frame.getHeight())/2);
		
		frame.setLocation(windowX, windowY);
		
		frame.setVisible(true);
		
		//Button Control
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(0,0));
		frame.add(p, BorderLayout.SOUTH);
		
		keyBindings(p, frame, r);
		animator.start();
	}
	
	private static void keyBindings(JPanel p, JFrame frame, Render r){
		ActionMap actionMap = p.getActionMap();
		InputMap inputMap = p.getInputMap();
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "F1");
		actionMap.put("F1", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fullScreen(frame);
				
			}
			
		});
		
	}

	protected static void fullScreen(JFrame f) {
		if(!isFullScreen){
			f.dispose();
			f.setUndecorated(true);
			f.setVisible(true);
			f.setResizable(false);
			xgraphic = f.getSize();
			point = f.getLocation();
			f.setLocation(0, 0);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			f.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
			isFullScreen = true;
		} else {
			f.dispose();
			f.setUndecorated(false);
			f.setResizable(true);
			f.setLocation(point);
			f.setSize(xgraphic);
			f.setVisible(true);
			isFullScreen = false;
		}
		
	}
	
}
