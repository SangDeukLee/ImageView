
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.lang.StringBuffer;



import java.io.*;

public class ImageView extends JFrame {
	
	ImageView I;			//나를 가리키는 참조변수
	
	JButton button1;		//기본
	JButton button2;		//확대
	JButton button3;		//축소
	JButton button4;		//상하
	JButton button5;		//좌우
	
	
	
	Menubar menu;			//메뉴클래스
	
	JMenuItem file_open;	//열기
	JMenuItem file_save;	//저장	
	
	Image img;				//이미지 참조변수
	Image img1;				//이미지 사이즈 알아내는 참조변수
	
	Button btn;				//버튼클래스 참조변수
	
	Panel2 panel2;			//패널(화면 나오는 구간)
	
	BufferedImage newImage;	//이미지 저장을 위한 참조변수
	
	Mouse wheel = new Mouse();	//마우스 휠
	
	
	static int message;
	
	int mouseX=0;		//마우스 좌표값 x
	int mouseY=0;		//마우스 좌표값 y
	int wheelck=0;		//마우스 휠
	int dragX = 0;
	int dragY = 0;
	int rX=0;
	int rY=0;
	int mouseX1=0;
	int mouseY1=0;
	int a;
	int b;
	int offX;
	int X=0;
	int Y=0;
	double drawX = 1.0;
	double drawY = 1.0;
	double size=1.0;
	
	
	String filename="";	//파일 경로와 이름
	String fileway="";
	String file = "";

	public ImageView(){
		
		I = this;
		
		
		System.out.println("생성자");
		menu = new Menubar();
		btn = new Button();
		
		JMenuBar mb = new JMenuBar();
		
		JMenu file = new JMenu("File");
		file_open = new JMenuItem("Open");
		file_save = new JMenuItem("Svae");

		file.add(file_open);
		file.add(file_save);
	
		file_open.addActionListener(menu);
		file_save.addActionListener(menu);
		
		
		mb.add(file);
		setJMenuBar(mb);
		
		
		
		setTitle("ImageView");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel2 = new Panel2();	//위
		
		JPanel panel3 = new JPanel();	//아래
		
		
		
		button1 = new JButton("기본");
		button1.setBounds(100, 605, 50, 25);
		button1.setMargin(new Insets(0, 0, 0, 0));
		panel3.add(button1);
		button1.addActionListener(btn);
		
		button2 = new JButton("확대");
		button2.setBounds(300, 605, 50, 25);
		button2.setMargin(new Insets(0, 0, 0, 0));
		panel3.add(button2);
		button2.addActionListener(btn);
		
		button3 = new JButton("축소");
		button3.setBounds(500, 605, 50, 25);
		button3.setMargin(new Insets(0, 0, 0, 0));
		panel3.add(button3);
		button3.addActionListener(btn);
		
		button4 = new JButton("상하");
		button4.setBounds(700, 605, 50, 25);
		button4.setMargin(new Insets(0, 0, 0, 0));
		panel3.add(button4);
		button4.addActionListener(btn);
		
		button5 = new JButton("좌우");
		button5.setBounds(900, 605, 50, 25);
		button5.setMargin(new Insets(0, 0, 0, 0));
		panel3.add(button5);
		button5.addActionListener(btn);
		
	
		setBackground(Color.white);
		panel3.setBackground(Color.pink);
		
		add("Center",panel2);
		add("South",panel3);
		panel2.addMouseWheelListener(wheel);
		panel2.addMouseMotionListener(wheel);
		panel2.addMouseListener(wheel);
		setVisible(true);
		setSize(500,500);
	}

	public class Mouse implements  MouseListener, MouseMotionListener, MouseWheelListener{
		public void mouseMoved(MouseEvent e){
			// TODO Auto-generated method stub
		}
		
		public void mouseWheelMoved(MouseWheelEvent e){
	
			System.out.println(e.getWheelRotation());
			if(e.getWheelRotation() == -1){
				System.out.println("휠위로");
				message = 2;
				size+=0.1;
			}
			else if(e.getWheelRotation() == 1){
				System.out.println("휠아래로");
				message = 3;
				size-=0.1;
			}
			panel2.repaint();
		}
	
		
		@Override
		public void mouseDragged(MouseEvent e) {
			dragX = e.getX();
			dragY = e.getY();
			message = 6;
			panel2.repaint();
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			
			
			mouseX =e.getX()+X;
			mouseY =e.getY()+Y;
			System.out.println("클릭 좌표"+" "+mouseX +"  " +mouseY);
		
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class Button implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == button1){
				message = 1;
				size = 1.0;
			}
			else if(e.getSource() == button2){
				message = 2;
				size += 0.1;
			}
			else if(e.getSource() == button3){
				message = 3;
				size -= 0.1;
			}
			else if(e.getSource() == button4){
				message = 4;
				size = 1.0;
			}
			else if(e.getSource() == button5){
				message = 5;
				size = 1.0;
			}
			panel2.repaint();
		}
	}
	
	public class Panel2 extends JPanel {
		public void paint(Graphics g){
			switch(message){
			case 1 :	//기본
				X=0;
				Y=0;
				dragX=0;
				dragY=0;
				mouseX1=0;
				mouseY1=0;
				System.out.println("1");
				g.drawImage(img, 
							0, 0, 
							this);
				break;
			
			case 2 :	//확대
				X=0;
				Y=0;
				dragX=0;
				dragY=0;
				mouseX1=0;
				mouseY1=0;
				System.out.println("2");
				g.clearRect(0, 0, img1.getWidth(null), img1.getHeight(null));
				g.drawImage(img, 
					      (int) (img.getWidth(this) - img.getWidth(this) * size), 
					      (int) (img.getHeight(this) - img.getHeight(this) * size), //그림의 시작위치를 계산 
					      (int) (img.getWidth(this) * size), 
					      (int) (img.getHeight(this) * size), 
					      
					      0, 0, img.getWidth(this), img.getHeight(this), 
					      this);
			
				break;
				
			case 3 :	//축소			
				
				System.out.println("3");
				if((int) (img.getWidth(this) - img.getWidth(this) * size) < (int) (img.getWidth(this) * size)){
					
					g.clearRect(0, 0, img1.getWidth(null), img1.getHeight(null));
					g.drawImage(img, 
							(int) (img.getWidth(this) - img.getWidth(this) * size), 
							(int) (img.getHeight(this) - img.getHeight(this) * size), //그림의 시작위치를 계산 
							(int) (img.getWidth(this) * size), 
							(int) (img.getHeight(this) * size), 
							
							0, 0, img.getWidth(this), img.getHeight(this), 
							this);
				}
				if((int) (img.getWidth(this) - img.getWidth(this) * size) > (int) (img.getWidth(this) * size)){
					size+=0.1;
				}
				
				break;
				
			case 4 :	//상하
				
				System.out.println("4");
				
			
				g.drawImage(img,
						    0, 
						    img.getHeight(this),  
						    img.getWidth(this), 
						    0,
						    
						    0, 0, img.getWidth(this), img.getHeight(this),
						    this);
				// Image resizeImage = img.getScaledInstance(img.getHeight(this), img.getWidth(this), Image.SCALE_SMOOTH);
				// img = resizeImage;
				break;
				
			case 5 :	//좌우
				System.out.println("5");
				g.drawImage(img,
					    	img.getWidth(this), 0, 0, img.getHeight(this),
					    	
					    	0, 0, img.getWidth(this), img.getHeight(this), 
					    	this);
				break;
			
			case 6 : 	//클릭 후드래그 이동
			
				
				dragX=mouseX-dragX;
				dragY=mouseY-dragY;
							
				
				mouseX1  = (int) (img.getWidth(this) - img.getWidth(this) * size);
				mouseY1 = (int) (img.getHeight(this) - img.getHeight(this) * size);
				
				g.clearRect(0, 0, img.getWidth(null), img.getHeight(null));
				g.drawImage(img, 
							mouseX1-dragX, 
							mouseY1-dragY, 
							(int) (img.getWidth(this) * size)-dragX, 
						    (int) (img.getHeight(this) * size)-dragY, 
						      
						    0, 0, img.getWidth(this), img.getHeight(this), 
						    this);
				System.out.println(dragX + " " +dragY );
			
				X = dragX;
				Y = dragY;
					
				
			//System.out.println((int) (img.getWidth(this) - img.getWidth(this) * size)+dragX + " === " +  
			//(int)(img.getHeight(this) - img.getHeight(this) * size)+dragY);
			}
			
		}
	}
	
	
	public class Menubar implements ActionListener, ImageObserver{
		public void actionPerformed(ActionEvent e){
			
			if(e.getSource() == file_open){
				FileDialog open = new FileDialog(I,"Open",FileDialog.LOAD);
				open.setVisible(true);
				filename = open.getFile();
				fileway = open.getDirectory();
				file = fileway + filename;
				file = file.replace("\\","/");
				img = Toolkit.getDefaultToolkit().getImage(file);
				img1 = new ImageIcon(file).getImage();
				setSize(img1.getWidth(null),img1.getHeight(null));
				//System.out.println(img1.getWidth(null));
				//System.out.println(img1.getHeight(null));
			}
			else if(e.getSource() == file_save){
				
				FileDialog save = new FileDialog(I,"Save",FileDialog.SAVE);
				save.setVisible(true);
				filename = save.getFile();
				fileway = save.getDirectory();
				file = save.getDirectory() + save.getFile();
				file = file.replace("\\","/");
				try{
					//System.out.println("이미지 저장");
					newImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_BGR);
					
					if(message == 1){
						X=0;
						Y=0;
						dragX=0;
						dragY=0;
						mouseX1=0;
						mouseY1=0;
						newImage.createGraphics().drawImage(img, 0, 0, this);
					}
					else if(message == 2){
						X=0;
						Y=0;
						dragX=0;
						dragY=0;
						mouseX1=0;
						mouseY1=0;
						newImage.createGraphics().drawImage(img, 
							      (int) (img.getWidth(this) - img.getWidth(this) * size)+(int)drawX, 
							      (int) (img.getHeight(this) - img.getHeight(this) * size)+(int)drawY, //그림의 시작위치를 계산 
							      (int) (img.getWidth(this) * size), 
							      (int) (img.getHeight(this) * size), 
							      0, 0, img.getWidth(this), img.getHeight(this), 
							      this);
					}
					else if(message == 3){
						X=0;
						Y=0;
						dragX=0;
						dragY=0;
						mouseX1=0;
						mouseY1=0;
						if((int) (img.getWidth(this) - img.getWidth(this) * size) < (int) (img.getWidth(this) * size)){
							newImage.createGraphics().drawImage(img, 
									(int) (img.getWidth(this) - img.getWidth(this) * size), 
									(int) (img.getHeight(this) - img.getHeight(this) * size), //그림의 시작위치를 계산 
									(int) (img.getWidth(this) * size), 
									(int) (img.getHeight(this) * size), 
									0, 0, img.getWidth(this), img.getHeight(this), 
									this);
						}
						if((int) (img.getWidth(this) - img.getWidth(this) * size) > (int) (img.getWidth(this) * size)){
							size+=0.1;
						}
					}
					else if(message == 4){
						newImage.createGraphics().drawImage(img,
															0, img.getHeight(this),  img.getWidth(this), 0,
															0, 0, img.getWidth(this), img.getHeight(this), 
															this);
					}
					else if(message == 5){
						newImage.createGraphics().drawImage(img,
						    								img.getWidth(this), 0, 0, img.getHeight(this),
						    								0, 0, img.getWidth(this), img.getHeight(this), 
						    								this);
					}
					else if(message == 6){
						dragX=mouseX-dragX;
						dragY=mouseY-dragY;
									
						
						mouseX1  = (int) (img.getWidth(this) - img.getWidth(this) * size);
						mouseY1 = (int) (img.getHeight(this) - img.getHeight(this) * size);
						
						newImage.createGraphics().drawImage(img, 
								mouseX1-dragX, 
								mouseY1-dragY, 
								(int) (img.getWidth(this) * size)-dragX, 
							    (int) (img.getHeight(this) * size)-dragY, 
							      
							    0, 0, img.getWidth(this), img.getHeight(this), 
							    this);
				
					X = dragX;
					Y = dragY;
					}
					
					
					
					ImageIO.write(newImage, "jpg", new File(file+".jpg"));
					
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
				
				
			}
		}

		@Override
		public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
			// TODO Auto-generated method stub
			return false;
		}
	}
	
	
	public static void main(String [] args){
		
		ImageView I = new ImageView();
		
	}
}

