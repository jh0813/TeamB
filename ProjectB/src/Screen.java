import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Timer;
import java.util.TimerTask;

import javazoom.jl.player.Player;

public class Screen extends Canvas implements ComponentListener, KeyListener {
	
	private Graphics bg;
	private Image offScreen;
	private Dimension dim;
	private Character ryu = new Character();
	private Character ryu2 = new Character();
	private StageTitle stageTitle = new StageTitle();
	private int countNumber = 0;
	private int stage = 0;
	
	public Screen() {
		ryu2.setPosition(450, 450, true);
		addComponentListener(this);
		
		addKeyListener(ryu);
		addKeyListener(ryu2);
		addKeyListener(this);
		
		setFocusable(true);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				repaint();
				counting();
			}
		}, 0, 1);
		
		//bgplay();
	}
	public void counting() {
		this.countNumber++;
	}
	
	public int getCount() {
		return this.countNumber;
	}
	
	private void initBuffer() {
		this.dim = getSize();
		this.offScreen = createImage(dim.width, dim.height);
		this.bg = this.offScreen.getGraphics();
	}
	
	@Override
	public void paint(Graphics g) {
		bg.clearRect(0, 0, dim.width, dim.height);
		//~~~~
		if(stage == 0) {
			stageTitle.draw(bg, this);
		}
		else if(stage == 1) {
			ryu.draw(bg, this);
			ryu2.draw(bg, this);
		}
		
		g.drawImage(offScreen, 0, 0, this);
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		initBuffer();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void bgplay() {
		Player jlPlayer = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("res/RyuTheme.mp3");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            jlPlayer = new Player(bufferedInputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        final Player player = jlPlayer;
        new Thread() {
            public void run() {
                try {
                player.play();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }.start();

	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(stage==0) {
			if(stageTitle.getStageEnd())
				stage = 1;
		}
		else if(stage==1) {
			
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
