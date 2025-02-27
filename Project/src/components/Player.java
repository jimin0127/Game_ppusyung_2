package components;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import util.Util;

public class Player {
	private JPanel mainPanel;
	private int x;
	private int y;
	private int distance = 200; // 캐릭터가 이동한 총 거리
	private int hp = 1000; // 체력
	private int status; // 캐릭터가 바라보는 방향 : 1=오른쪽, 2=왼쪽
	private int hit_status=1; // 공격상태 : 1=총, 2=활, 3=근접
	private int shot_range = 800; // 이미지 투명도
	private int invincibility = 255; // 이미지 투명도
	private int score = 0; // 점수
	private Image image;
	private ArrayList<Shot> shots = new ArrayList<Shot>(); // 플레이어가 쏜 총알들이 담기는 리스트
	private int cnt = 0; // 현재 캐릭터 이미지 설정하는 계수            
	private boolean fall = false; // 낙하 여부
	private boolean jump = false; // 점프 여부
	private boolean hit = false; // 점프 여부
	private int stage;
	private int countJump = 0;
	private int field = 900;
	
	private ImageIcon backImg = new ImageIcon("images/랭킹1.png");
	private Image back = backImg.getImage();
	
	private Dimension view = Toolkit.getDefaultToolkit().getScreenSize();
	
	private Image images[] = {new ImageIcon("images/Player/3학년/3학년.png").getImage()
			,new ImageIcon("images/Player/3학년/3학년.png").getImage()
			,new ImageIcon("images/Player/3학년/3학년2.png").getImage()
			,new ImageIcon("images/Player/3학년/3학년2.png").getImage()
			,new ImageIcon("images/Player/3학년/3학년.png").getImage()
			,new ImageIcon("images/Player/3학년/3학년.png").getImage()
			,new ImageIcon("images/Player/3학년/3학년2.png").getImage()
			,new ImageIcon("images/Player/3학년/3학년2.png").getImage()
			,new ImageIcon("images/Player/3학년/3학년.png").getImage()
			,new ImageIcon("images/Player/3학년/3학년.png").getImage()
			,new ImageIcon("images/Player/3학년/3학년2.png").getImage()
			,new ImageIcon("images/Player/3학년/3학년2.png").getImage()};

	
	private Image imagesLeft[] = {new ImageIcon("images/Player/신입생/신입생_left.png").getImage()
			,new ImageIcon("images/Player/신입생/신입생_left.png").getImage()
			,new ImageIcon("images/Player/신입생/신입생2_left.png").getImage()
			,new ImageIcon("images/Player/신입생/신입생2_left.png").getImage()
			,new ImageIcon("images/Player/신입생/신입생_left.png").getImage()
			,new ImageIcon("images/Player/신입생/신입생_left.png").getImage()
			,new ImageIcon("images/Player/신입생/신입생2_left.png").getImage()
			,new ImageIcon("images/Player/신입생/신입생2_left.png").getImage()
			,new ImageIcon("images/Player/신입생/신입생2_left.png").getImage()
			,new ImageIcon("images/Player/신입생/신입생_left.png").getImage()
			,new ImageIcon("images/Player/신입생/신입생_left.png").getImage()
			,new ImageIcon("images/Player/신입생/신입생2_left.png").getImage()};
	
	private ImageIcon jumpImg[] = {new ImageIcon("images/Player/신입생/신입생_점프.png"),
			new ImageIcon("images/Player/신입생/신입생_점프_left.png")};
	
	private ImageIcon hitImg[][] = {
			{new ImageIcon("images/Player/신입생/신입생총.png"), new ImageIcon("images/Player/신입생/신입생총_left.png")},
			{new ImageIcon("images/Player/신입생/신입생_화살당기기.png"), new ImageIcon("images/Player/신입생/신입생_화살당기기_left.png")},
			{new ImageIcon("images/Player/신입생/신입생_단거리 공격.png"), new ImageIcon("images/Player/신입생/신입생_단거리 공격_left.png")}};
	
	private ImageIcon afterHitImg[] = {new ImageIcon("images/Player/신입생/신입생_화살쏘기.png"),
			new ImageIcon("images/Player/신입생/신입생_화살쏘기_left.png")};
	
	public boolean isFall() {
		return fall;
	}
	
	public void setFall(boolean fall) {
		this.fall = fall;
	}
	
	public int getField() {
		return field;
	}
	
	public void setField(int field) {
		this.field = field;
	}
	
	public boolean isJump() {
		return jump;
	}
	
	public void setJump(boolean jump) {
		this.jump = jump;
	}
	
	public boolean isHit() {
		return hit;
	}
	
	public void setHit(boolean hit) {
		this.hit = hit;
	}
	
	public int getCountJump() {
		return countJump;
	}
	public int getShot_range() {
		return shot_range;
	}

	public void setShot_range(int shot_range) {
		this.shot_range = shot_range;
	}

	public void setCountJump(int countJump) {
		this.countJump = countJump;
	}
	
	public ArrayList<Shot> getShots() {
		return shots;
	}
	
	public void setShots(ArrayList<Shot> shots) {
		this.shots = shots;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getInvincibility() {
		return invincibility;
	}
	
	public void setInvincibility(int invincibility) {
		this.invincibility = invincibility;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getHit_status() {
		return hit_status;
	}
	
	public void setHit_status(int hit_status) {
		if(hit_status==1) {
			setShot_range(800);
		}else if(hit_status==2) {
			setShot_range(1400);
		}
		this.hit_status = hit_status;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image imageIcon) {
		this.image = imageIcon;
	}
	
	public void setImages(int stage) {
		this.stage = stage;
		switch (stage) {
		case 1:
			Image images[] = {new ImageIcon("images/Player/신입생/신입생2.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생2.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생2.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생2.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생2.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생2.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생2.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생2.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생2.png").getImage()};
			this.images = images;
			
			Image imagesLeft[] = {new ImageIcon("images/Player/신입생/신입생2_left.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생_left.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생_left.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생2_left.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생2_left.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생2_left.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생_left.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생_left.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생2_left.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생2_left.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생2_left.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생_left.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생_left.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생2_left.png").getImage()
					,new ImageIcon("images/Player/신입생/신입생2_left.png").getImage()};
			this.imagesLeft = imagesLeft;
			
			jumpImg[0] = new ImageIcon("images/Player/신입생/신입생_점프.png");
			jumpImg[1] = new ImageIcon("images/Player/신입생/신입생_점프_left.png");
			
			ImageIcon hitImg[][] = {
					{new ImageIcon("images/Player/신입생/신입생총.png"), new ImageIcon("images/Player/신입생/신입생총_left.png")},
					{new ImageIcon("images/Player/신입생/신입생_화살당기기.png"), new ImageIcon("images/Player/신입생/신입생_화살당기기_left.png")},
					{new ImageIcon("images/Player/신입생/신입생_단거리 공격.png"), new ImageIcon("images/Player/신입생/신입생_단거리 공격_left.png")}};
			this.hitImg = hitImg;
			
			afterHitImg[0] = new ImageIcon("images/Player/신입생/신입생_화살쏘기.png");
			afterHitImg[1] = new ImageIcon("images/Player/신입생/신입생_화살쏘기_left.png");
			break;
		case 2:
			Image images2[] = {new ImageIcon("images/Player/3학년/3학년.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년2.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년2.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년2.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년2.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년2.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년2.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년.png").getImage()};
			this.images = images2;
			
			Image imagesLeft2[] = {new ImageIcon("images/Player/3학년/3학년_left.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년2_left.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년2_left.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년_left.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년_left.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년_left.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년2_left.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년2_left.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년_left.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년_left.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년_left.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년2_left.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년2_left.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년_left.png").getImage()
					,new ImageIcon("images/Player/3학년/3학년_left.png").getImage()};
			this.imagesLeft = imagesLeft2;
			
			jumpImg[0] = new ImageIcon("images/Player/3학년/3학년_점프.png");
			jumpImg[1] = new ImageIcon("images/Player/3학년/3학년_점프_left.png");
			
			ImageIcon hitImg2[][] = {
					{new ImageIcon("images/Player/3학년/3학년_총쏘기.png"), new ImageIcon("images/Player/3학년/3학년_총쏘기_left.png")},
					{new ImageIcon("images/Player/3학년/3학년_활당기기.png"), new ImageIcon("images/Player/3학년/3학년_활당기기_left.png")},
					{new ImageIcon("images/Player/3학년/3학년_단거리 공격.png"), new ImageIcon("images/Player/3학년/3학년_단거리 공격_left.png")}};
			this.hitImg = hitImg2;
			
			afterHitImg[0] = new ImageIcon("images/Player/3학년/3학년_활쏘기.png");
			afterHitImg[1] = new ImageIcon("images/Player/3학년/3학년_활쏘기_left.png");
			break;
		case 3:
			Image images3[] = {new ImageIcon("images/Player/직장인/직장인.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인2.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인2.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인2.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인2.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인2.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인2.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인.png").getImage()};
			this.images = images3;
			
			Image imagesLeft3[] = {new ImageIcon("images/Player/직장인/직장인_left.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인2_left.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인2_left.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인_left.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인_left.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인_left.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인2_left.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인2_left.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인_left.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인_left.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인_left.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인2_left.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인2_left.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인_left.png").getImage()
					,new ImageIcon("images/Player/직장인/직장인_left.png").getImage()};
			this.imagesLeft = imagesLeft3;
			
			jumpImg[0] = new ImageIcon("images/Player/직장인/직장인_점프.png");
			jumpImg[1] = new ImageIcon("images/Player/직장인/직장인_점프_left.png");
			
			ImageIcon hitImg3[][] = {
					{new ImageIcon("images/Player/직장인/직장인_총쏘기.png"), new ImageIcon("images/Player/직장인/직장인_총쏘기_left.png")},
					{new ImageIcon("images/Player/직장인/직장인_활당기기.png"), new ImageIcon("images/Player/직장인/직장인_활당기기_left.png")},
					{new ImageIcon("images/Player/직장인/직장인_단거리 공격.png"), new ImageIcon("images/Player/직장인/직장인_단거리 공격_left.png")}};
			this.hitImg = hitImg3;
			
			afterHitImg[0] = new ImageIcon("images/Player/직장인/직장인_활쏘기.png");
			afterHitImg[1] = new ImageIcon("images/Player/직장인/직장인_활쏘기_left.png");
			break;
		default:
			break;
		}
	}
	
	//player 왼쪽으로 이동 (key)
	public void p_moveLeft() {
		setStatus(2);
		if(cnt == imagesLeft.length) {
			cnt = 0;
		}
		if(!isHit()) {
			if(isJump()||isFall()) {
				setImage(jumpImg[1].getImage());
			}else {
				setImage(imagesLeft[cnt]);	
			}
		}
		cnt++;
		if(x > 0) {
			x -= 15;
			distance -= 15;
		}else {
			x = 0;
		}
	}
	
	//player 오른쪽으로 이동 (key)
	public void p_moveRight() {
		setStatus(1);
		if(cnt == images.length) {
			cnt = 0;
		}
		if(!isHit()) {
			if(isJump()||isFall()) {
				setImage(jumpImg[0].getImage());
			}else {
				setImage(images[cnt]);
			}
		}
		cnt++;
		if(distance < back.getWidth(null)-130) {
			x += 15;
			distance += 15;
		}	
	}
	
	// 화면 중간에서 오른쪽 이동(key)
	public void p_moveRight(int num) {
		setStatus(1);
		if(cnt == images.length) {
			cnt = 0;
		}
		if(!isHit()) {
			if(isJump()||isFall()) {
				setImage(jumpImg[0].getImage());
				
			}else {
				setImage(images[cnt]);	
			}
		}
		cnt++;
		distance += 10;
	}
	
	// 멈출때 이미지 변겅
	public void stop() {
		cnt = 0;
		if(!isHit()) {
			if(isJump()||isFall()) {
				setImage(jumpImg[status-1].getImage());
			}else {
				if (status == 1) {
					setImage(images[cnt]);	
				}else if (status == 2) {
					setImage(imagesLeft[cnt]);
				}		
			}
		}
	}
	
	// 총알 발사
	public void p_hit() {
		switch (hit_status) {
		case 1:
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						setHit(true);
						setImage(hitImg[hit_status-1][status-1].getImage());
						shots.add(new Shot(mainPanel, x+50, y+15, status,1));
						Thread.sleep(100);
						if(status==1) {
							setImage(images[0]);
						}else {
							setImage(imagesLeft[0]);
						}
						Thread.sleep(100);
						setHit(false);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
			break;
		case 2:
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						setHit(true);
						setImage(hitImg[hit_status-1][status-1].getImage());
						Thread.sleep(200);
						shots.add(new Shot(mainPanel, x+50, y+15, status,2));
						setImage(afterHitImg[status-1].getImage());
						Thread.sleep(100);
						if(status==1) {
							setImage(images[0]);
						}else {
							setImage(imagesLeft[0]);
						}
						Thread.sleep(100);
						setHit(false);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
			break;
		case 3:
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						setHit(true);
						setImage(hitImg[hit_status-1][status-1].getImage());
						Thread.sleep(200);
						if(status==1) {
							setImage(images[0]);
						}else {
							setImage(imagesLeft[0]);
						}
						Thread.sleep(200);
						setHit(false);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
			break;
		default:
			break;
		}
		
	}
	
	// 데미지 받을 때 
	public void damaged(int damage) {
		if(invincibility == 255) { //투명도가 255일때
			Sound("music/hitSound.wav", false);
			Sound("music/ouchSound.wav", false);
			invincibility = 80;
			this.hp -= damage;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						//투명도를 바꾸어준다. 
						Thread.sleep(500);
						invincibility=254;
						Thread.sleep(500);
						invincibility=80;
						Thread.sleep(500);
						invincibility=254;
						Thread.sleep(500);
						invincibility=80;
						Thread.sleep(500);
						invincibility=255;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	
	public void deleteShot() { // 화면 밖으로 나간 총알을 없애는 메서드
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					for(int i = 0; i < shots.size(); i++) {
						try {
							if(shots.get(i).getX()>view.getWidth()-100 || shots.get(i).getX()<0
									||shots.get(i).getX()>getX()+getShot_range()||shots.get(i).getX()<getX()-getShot_range()) {
								shots.remove(i); //맞은 총알 삭제
							}
						}catch (IndexOutOfBoundsException e) {
							
						}catch (NullPointerException e) {
							e.printStackTrace();
						}
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();	
	}
	
	// 필드로 떨어지는 메서드 
	public void fall() {
		new Thread(new Runnable() {
			@Override
			public void run() {

				while (true) {
					int foot = getY() + image.getHeight(null); // 캐릭터 발 위치 재스캔

					// 발바닥이 발판보다 위에 있으면 작동
					if (foot < field // 공중에 있으며
							&& !isJump() // 점프 중이 아니며
							&& !isFall()) { // 떨어지는 중이 아닐 때
						
						setFall(true); // 떨어지는 중으로 전환

						long t1 = Util.getTime(); // 현재시간을 가져온다
						long t2;
						int set = 1; // 처음 낙하량 (0~10) 까지 테스트해보자
						while (foot < field) { // 발이 발판에 닿기 전까지 반복
							if(foot > field-10) {
								setFall(false);
								switch (status) {
								case 1:
									setImage(images[0]);
									break;
								case 2:
									setImage(imagesLeft[0]);
									break;
								default:
									break;
								}
							}
							t2 = Util.getTime() - t1; // 지금 시간에서 t1을 뺀다
							int fallY = set + (int) ((t2) / 40); // 낙하량을 늘린다.
							foot = getY() + image.getHeight(null); // 캐릭터 발 위치 재스캔
							if (foot + fallY >= field) { // 발바닥+낙하량 위치가 발판보다 낮다면 낙하량을 조정한다.
								fallY = field - foot;
							}
							setY(getY() + fallY); // Y좌표에 낙하량을 더한다
							if (isJump()) { // 떨어지다가 점프를 하면 낙하중지
								break;
							}
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						setFall(false);
						
						if (!isJump()) { // 발이 땅에 닿고 점프 중이 아닐 때 더블점프 카운트를 0으로 변경
							setCountJump(0);
							
						}
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	// 점프 동작
	public void jump() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				setCountJump(getCountJump() + 1); // 점프 횟수 증가
				int nowJump = getCountJump(); // 이번점프가 점프인지 더블점프인지 저장
				setJump(true); // 점프중으로 변경
				long t1 = Util.getTime(); // 현재시간을 가져온다
				long t2;
				int set = 12; // 점프 계수 설정(0~20) 등으로 바꿔보자
				int jumpY = 1; // 1이상으로만 설정하면 된다.(while문 조건 때문)
				setImage(jumpImg[status-1].getImage());
				setImage(jumpImg[status-1].getImage());
				
				while (jumpY >= 0) { // 상승 높이가 0일때까지 반복
					
					t2 = Util.getTime() - t1; // 지금 시간에서 t1을 뺀다
					jumpY = set - (int) ((t2) / 40); // jumpY 를 세팅한다.
					setY(getY() - jumpY); // Y값을 변경한다.
					if (nowJump != getCountJump()) { // 점프가 한번 더되면 첫번째 점프는 멈춘다.
						break;
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (nowJump == getCountJump()) { // 점프가 진짜 끝났을 때를 확인
					setJump(false); // 점프상태를 false로 변경
				}
			}
		}).start();
	}
	
	public void Sound(String file, boolean Loop){
		//사운드재생용메소드
		//사운드파일을받아들여해당사운드를재생시킨다.
		Clip clip;
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			if (Loop) clip.loop(-1);
			//Loop 값이true면 사운드재생을무한반복시킵니다.
			//false면 한번만재생시킵니다.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Player(JPanel main, int stage){
		this.mainPanel = main;
		setX(200);
		setY(100);
		setDistance(200);
		setScore(0);
		setInvincibility(255);
		setStatus(1);
		shots.clear();
		setImages(stage);
		setImage(images[0]);
	}
}