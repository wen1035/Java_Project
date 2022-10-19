package br.ol.smb.entity;

import br.ol.smb.infra.Display;
import br.ol.smb.infra.Game;
import static br.ol.smb.infra.Game.GameState.*;
import br.ol.smb.infra.Input;
import br.ol.smb.infra.Map;
import br.ol.smb.infra.MathUtil;
import br.ol.smb.infra.Music;
import br.ol.smb.infra.Sound;
import br.ol.smb.infra.Tile;
import br.ol.smb.infra.Time;
import br.ol.smb.infra.Vec2;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 * Mario class.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 */
public class Mario extends Actor {
    private ImpactStrength impactStrength = ImpactStrength.WEAK;
    private static final int MAX_JUMPING_COUNT = 10;
    private boolean immortal;
    private double immortalStartTime;
    private boolean jumping;
    private int jumpingCount;
    private boolean killedEnemy;
    private int superMarioAnimationOffset;
    private int invincibleMarioAnimationOffset;
    private int fireMarioAnimationOffset;
    private double deadTime;
    private boolean killedByTimeLeft;
    private boolean pressingLeft;
    private boolean pressingRight;

    // additional states
    public static enum Transformation { NONE, NORMAL, SUPER, INVINCIBLE, FIRE }
    private Transformation transformation = Transformation.NONE;
    private boolean superMario;
    private boolean invincibleMario;
    private double invincibleMarioStartTime;
    private boolean fireMario;
    private double fireMarioStartTime;
    private double transformationFrame;
    private double transformationVelocity;
    
    // fireball cache
    private List<Fireball> fireballs = new ArrayList<Fireball>();
    private double lastFireTime; 
    
    // level cleared flag
    private Flag flag;
    private int time_kill=0;
	private int add_life=0;
	private int game_over=0;
    public Mario(Game game) {
        super(game);
        createFireballsCache();
        setUnremovable(true);
    }

    private void reset() {
        tileId = 4505;
        animation = null;
        animationTimeScale = 0.8;
        setColliderSize(16, 16);
        spriteHeight = 16;
        lastDirection = LastDirection.RIGHT;
        gravityScale = 1;
        minX = 0;
        
        impactStrength = ImpactStrength.WEAK;
        immortal = false;
        jumping = false;
        jumpingCount = 0;
        killedEnemy = false;
        superMarioAnimationOffset = 0;
        invincibleMarioAnimationOffset = 0;
        fireMarioAnimationOffset = 0;
        deadTime = 0;
        killedByTimeLeft = false;
        transformation = Transformation.NONE;
        superMario = false;
        invincibleMario = false;
        invincibleMarioStartTime = 0;
        fireMario = false;
        fireMarioStartTime = 0;
        transformationFrame = 0;
        transformationVelocity = 0;
    }
    
    private void createFireballsCache() {
        for (int i = 0; i < 20; i++) {
            fireballs.add(new Fireball(game));
        }
    }
    
    public void setImmortal() {//////////////////////////////////////////////////////////////////
        immortal = true;
        immortalStartTime = Time.getCurrentTime();
    }

    public boolean isImmortal() {
        return immortal;
    }

    public ImpactStrength getImpactStrength() {
        return impactStrength;
    }

    public boolean isSuperMario() {
        return superMario;
    }

    public boolean isInvincibleMario() {
        return invincibleMario;
    }

    public boolean isFireMario() {
        return fireMario;
    }

    @Override
    public void start() {
        super.start();
        flag = game.getEntity(Flag.class);
        setzOrder(5);
        debugColor = Color.BLUE;
    }

    public void convertToScreenPosition(Vec2 screenPosition) {
        screenPosition.set(position);
        screenPosition.sub(camera.getPosition());
    }


    @Override
    protected void updateMovement() {
        updateDebug();
        updateFireball();
        updateHorizontalMovement();
        updateJumping();
        updateAdditionalStates();
        //checkDiedFalling();
        updateTimeLeft();
    }    

    @Override
    public void fixedUpdatePlaying() {
        super.fixedUpdatePlaying();
        checkLevelCleared();
    }
    
    private void checkLevelCleared() {
        if (position.getX() >= game.getLevelClearedCol() * Map.TILE_SIZE + 4) {
            game.levelCleared();
        }
    }
    
   
	private void updateTimeLeft() {////////////////////////////////////
        game.updateTimeLeft();
		if (time_kill == 1) {
            killedByTimeLeft = true;
			time_kill=0;
            kill();
        }
        if (game.getTimeLeft() <= 0) {
            killedByTimeLeft = true;
            kill();
        }
		
    }
    
  
    
    private void updateFireball() {
        boolean canFire = isFireMario() 
                && Time.getCurrentTime() - lastFireTime > 0.1;
        
        if (Input.isKeyPressed(KeyEvent.VK_Z) && canFire) {
            fire();
        }
    }
    
    private void updateDebug() {
        if (Input.isKeyPressed(KeyEvent.VK_3)) {//////////////////////////////////////////
            Sound.play("coin");
        }

        if (Input.isKeyPressed(KeyEvent.VK_K)) {
            kill();
        }

        if (Input.isKeyPressed(KeyEvent.VK_E)) {
            Explosion explosion = new Explosion(game);
            explosion.spawn(position.getX(), position.getY());
        }

        if (Input.isKeyPressed(KeyEvent.VK_F)) {
            getPosition().setX(195 * Map.TILE_SIZE);
            camera.getPosition().setX(195 * Map.TILE_SIZE);
        }

        if (Input.isKeyPressed(KeyEvent.VK_1)) {
            flag.setPositionByCell(0, 1);
        }
        if (Input.isKeyPressed(KeyEvent.VK_2)) {
            flag.show();
        }
        if (Input.isKeyPressed(KeyEvent.VK_P)) {
            class game_op extends JFrame implements ActionListener, MouseListener{
                JLabel jlabel;
                JLabel image;
                JMenuItem start,restart;
                ImageIcon background_image;
                JButton bnt1;
                JLabel card_star;
                JPanel jPanel3;
                int count1=0;
                int randomnum;
                JLabel card;
                JLabel card1;
                public game_op()
                {
                    super("The game~");
                    Container c=getContentPane();
                    ImageIcon background_image1=new ImageIcon("star.jpg");
                    setIconImage(background_image1.getImage());
                    JLayeredPane layeredpane=new JLayeredPane();
                    background_image=new ImageIcon("background.jpg");
                    image=new JLabel(background_image);
                    image.setBounds(0, 0, this.getWidth(), this.getHeight());
                    JPanel imagePanel = new JPanel();
                    imagePanel.add(image);
                    JPanel jPanel1=new JPanel();
                    JPanel jPanel2=new JPanel();
                    jPanel3=new JPanel();
                    JPanel jPanel4=new JPanel();
                    JPanel jPanel5=new JPanel();
                    JPanel jPanel6=new JPanel();
					
                    jPanel1.setOpaque(false);
                    jPanel2.setOpaque(false);
                    jPanel4.setOpaque(false);
                    jPanel5.setOpaque(false);
                    jPanel6.setOpaque(false);
					
					
					
					
                    jlabel=new JLabel("click the button to start");
                    jlabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
                    jlabel.setBounds(0, 0, 800, 40);
                    jlabel.setForeground(Color.BLUE);
                    jlabel.setVisible(true);
                    jPanel1.add(jlabel);
                    bnt1=new JButton();
                    bnt1.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
                    bnt1.setBounds(0, 0, 150, 70);
                    bnt1.setVisible(false);
                    jPanel2.add(bnt1);
                    jPanel3.setBackground(Color.BLACK);
                    jPanel3.setVisible(false);
                    card_star=new JLabel(new ImageIcon("star.jpg"));
                    card_star.setVisible(false);
                    jPanel4.add(card_star);
                    card=new JLabel();
                    card.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
                    card.setForeground(Color.DARK_GRAY);
                    card.setVisible(false);
                    jPanel5.add(card);
                    card1=new JLabel(new ImageIcon("star_1.jpg"));
                    card1.setVisible(false);
                    jPanel6.add(card1);
                    layeredpane.add(imagePanel,new Integer(0));
                    layeredpane.add(jPanel1,new Integer(1));
                    layeredpane.add(jPanel2,new Integer(1));
                    layeredpane.add(jPanel3,new Integer(1));
                    layeredpane.add(jPanel4,new Integer(2));
                    layeredpane.add(jPanel5,new Integer(4));
                    layeredpane.add(jPanel6,new Integer(3));
					
					
					
                    c.add(layeredpane,BorderLayout.CENTER);
                    components();
                    addComponentListener(new ComponentAdapter()
                    {
                        public void componentResized(ComponentEvent e) {
                            imagePanel.setBounds(-10, -10, 1400, 960);
                            jPanel1.setBounds(70, 350, 1000, 60);
                            jPanel2.setBounds(425, 670, 250, 90);
                            jPanel3.setBounds(350, 100, 400, 550);
                            jPanel4.setBounds(375, 125, 350, 500);
                            jPanel5.setBounds(380, 325, 350, 550);
                            jPanel6.setBounds(375, 125, 350, 500);
							
						
							
                            image.setIcon(background_image);
                        }
                    });
                }
                public void components()
                {
                    JMenuBar jmenubar=new JMenuBar();
                    setJMenuBar(jmenubar);
                    JMenu jmenu=new JMenu("setting");
                    jmenu.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
                    ButtonGroup buttongroup=new ButtonGroup();
                    start=new JRadioButtonMenuItem("start");
                    restart=new JRadioButtonMenuItem("restart");					
                    start.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
                    restart.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
                    jmenu.add(start);
                    jmenu.add(restart);
                    buttongroup.add(start);
                    buttongroup.add(restart);
                    jmenubar.add(jmenu);
                    jlabel.addMouseListener(this);
                    start.addActionListener(this);
                    restart.addActionListener(this);
                    bnt1.addMouseListener(this);
                }
                public void actionPerformed(ActionEvent e)
                {   
                    //Button btn=(Button)e.getSource();
                    if(e.getSource()==start)
                    {
                        jlabel.setVisible(false);
                        count1++;
                        if(count1 == 1)
                        {
                            bnt1.setText("click");
                            bnt1.setVisible(true);
                            jPanel3.setVisible(true);
                            jPanel3.setOpaque(true);
                            card_star.setVisible(true);
                        }
                    }
                    else if(e.getSource()==restart)
                    {
                        jlabel.setVisible(true);
                        jlabel.setText("click the button to start");
                        count1=0;
                        bnt1.setVisible(false);
                        jPanel3.setVisible(false);
                        jPanel3.setOpaque(false);
                        card_star.setVisible(false);
                        card1.setVisible(false);
                        card.setVisible(false);
                    }
                    
                }
            
                public void mouseClicked(MouseEvent evt){
                    jlabel.setText(" ");
                    
                }
                public void mouseEntered(MouseEvent evt){
                    //jlabel.setText(" ");
                }   
                public void mouseExited(MouseEvent evt){
                    //jlabel.setText(" ");
                } 
                public void mousePressed(MouseEvent evt){
                    if(evt.getSource()==jlabel)
                    {
                        jlabel.setVisible(false);
                        count1++;
                    }
                    else if(evt.getSource()==bnt1)
                    {
                        randomnum=(int)System.currentTimeMillis();
                        Random r=new Random(randomnum);
                        int r_num=r.nextInt();
                        int r_num1=Math.abs(r_num)%220;/////////////
                        card1.setVisible(true);
						
						if(r_num1<=10)////////////////
                        {
                            card.setText("Time up!!");
                            card.setVisible(true);
                            game.setTimeLeft(400);
							time_kill=1;                    
                        }
                        else if(r_num1<=20)
                        {
                            card.setText("mushroom~");
                            card.setVisible(true);
                            transformToSuperMario(); 
                            Sound.play("power_up");
                        }
                        else if(r_num1<=40)
                        {
                            card.setText("small~");
                            card.setVisible(true);
                            transformToNormalMario(); 
                            Sound.play("warp");
                        }
                        else if(r_num1<=60)
                        {
                            card.setText("again~");
                            card.setVisible(true);
                            fireMario = false;
                            transformToNormalMario(); 
                            Sound.play("warp");
                        }
                        else if(r_num1<=80)
                        {
                            card.setText("super~");
                            card.setVisible(true);
                            transformToFireMario(); 
                            Sound.play("power_up");
                        }
                        else if(r_num1<=100)
                        {
                            card.setText("star!!");
                            card.setVisible(true);
                            transformToInvicibleMario();
                            Music.play("invincible");
                        }
						/*else if(r_num1<=120)
                        {
                           
							card.setText("Game over!!");
                            card.setVisible(true);
                            game_over=1;
							if(game_over==1)
							{
								game_over=0;
								kill();
							}
							
							
                        }*/
						else if(r_num1<=120)//////////////////////////
                        {
                            card.setText("time reset!!");
                            card.setVisible(true);
                            game.setTimeLeft(400);
							Sound.play("coin");
							
                        }
						else if(r_num1<=150)//////////////////////////
                        {
                            card.setText("Amulet of life!!");
                            card.setVisible(true);
                            add_life=1;
							Sound.play("coin");
                        }
						else if(r_num1<=170)//////////////////////
                        {
                           
							card.setText("add money!!");
                            card.setVisible(true);
                            game.addScore(300);
							game.addCoins(5);
							Sound.play("coin");
							
                        }
						else if(r_num1<=200)//////////////////////
                        {
                           
							card.setText("add life!!");
                            card.setVisible(true);
                            game.incLives();
							Sound.play("coin");
							
                        }
						else if(r_num1<=220)//////////////////////
                        {
                           
							card.setText("reduce score!!");
                            card.setVisible(true);
                            game.addScore(-1000);
							Sound.play("coin");
							
                        }
						
						
						
                    }
                    if(count1 == 1)
                    {
                        bnt1.setText("click");
                        bnt1.setVisible(true);
                        jPanel3.setVisible(true);
                        jPanel3.setOpaque(true);
                        card_star.setVisible(true);
                    }
                }
                public void mouseReleased(MouseEvent evt){
            
                }
            }
            game_op game1=new game_op();
            game1.setSize(1200, 860);
            game1.setDefaultCloseOperation(game1.HIDE_ON_CLOSE);
            game1.setVisible(true);
        }
    }
    
    private void updateHorizontalMovement() {
        pressingLeft = Input.isKeyDown(KeyEvent.VK_LEFT);
        pressingRight = Input.isKeyDown(KeyEvent.VK_RIGHT);
        double currentVx = velocity.getX();
        if (pressingLeft && canMoveLeft()) {
            moveLeft(currentVx);
        }
        else if (pressingRight && canMoveRight()) {
            moveRight(currentVx);
        }
        else {
            velocity.scale(0.95, 1);
        }
        // limit min x
        double cameraX = camera.getPosition().getX();
        minX = (int) (cameraX - Display.SCREEN_WIDTH / 2 + 8);
    }
    
    private void moveLeft(double currentVx) {
        double nvx = currentVx - 10 * Time.getFixedDeltaTime();
        nvx = MathUtil.clamp(nvx, -2, 2);
        velocity.setX(nvx);
    }
    
    private void moveRight(double currentVx) {
        double nvx = currentVx + 10 * Time.getFixedDeltaTime();
        nvx = MathUtil.clamp(nvx, -2, 2);
        velocity.setX(nvx);
    }

    private void updateJumping() {
        boolean collidingWithFloor = isCollidingWithFloor();
        if (!jumping && !collidingWithFloor) {
            return;
        }
        else if (!jumping && Input.isKeyPressed(KeyEvent.VK_UP)) {
            velocity.setY(-5);
            jumping = true;
            jumpingCount = 0;
            Sound.play("jump");
        }
        else if (collidingWithFloor) {
            jumping = false;
            if (Input.isKeyDown(KeyEvent.VK_UP)) {
                Input.keyDownConsumed[KeyEvent.VK_UP] = true;
            }
        }
        else if (isCollidingWithCeil()) {
            jumpingCount = MAX_JUMPING_COUNT;
        }
        else if (jumping && Input.isKeyDown(KeyEvent.VK_UP) 
                && jumpingCount < MAX_JUMPING_COUNT) {
            
            velocity.setY(-5);
            jumpingCount++;
        }
        else {
            jumpingCount = MAX_JUMPING_COUNT;
        }
    }

    @Override
    protected void leftCornerAdjust() {
        int hw = getWidth() / 2;
        int l = (int) (position.getX() - hw);
        int t = (int) (position.getY() - getHeight() - 2);
        Tile tile = map.getTileByWorld(l, t);
        int dif = (tile.getCol() + 1) * Map.TILE_SIZE - 1 - l;
        if (tile.isRigid() && dif < 5) {
            position.translate(dif + 1, 0);
        } 
    }

    @Override
    protected void rightCornerAdjust() {
        double margin = position.getX() - (camera.getPosition().getX() - Display.SCREEN_WIDTH / 2);
        if (margin < Map.TILE_SIZE) {
            return;
        }

        int hw = getWidth() / 2;
        int r = (int) (position.getX() + hw - 1);
        int t = (int) (position.getY() - getHeight() - 2);
        Tile tile = map.getTileByWorld(r, t);
        int dif = r - tile.getCol() * Map.TILE_SIZE;
        if (tile.isRigid() && dif < 5) {
            position.translate(-dif - 1, 0);
        } 
    }
    
    private void updateAdditionalStates() {
        // invecible update palette animation
        if (isInvincibleMario() && Time.getCurrentTime() - invincibleMarioStartTime < 10) {
            int frame = 3 + ((Time.getFixedFrames() / 3) % 4);
            invincibleMarioAnimationOffset = frame * (3 * spriteSheet.getCols());
        }
        else if (isInvincibleMario()) {
            invincibleMario = false;
            invincibleMarioAnimationOffset = 0;
            Music.stop();
            Music.play(game.getWorld());
        }
        
        // fire mario
        if (isFireMario()) {
            fireMarioAnimationOffset = 6 * spriteSheet.getCols();
        }
        else {
            fireMarioAnimationOffset = 0;
        }
        
//        if (isFireMario() && Time.getCurrentTime() - fireMarioStartTime < 10) {
//            fireMarioAnimationOffset = 6 * spriteSheet.getCols();
//        }
//        else if (isFireMario()) {
//            fireMario = false;
//            fireMarioAnimationOffset = 0;
//        }
        
        // if immortal, blink quickly
        if (immortal && Time.getCurrentTime() - immortalStartTime < 3) {
            setVisible(Time.getFixedFrames() % 3 != 0);
        }
        else {
            immortal = false;
            setVisible(true);
        }        
    }
    
    private int getAnimationOffset() {
        return superMarioAnimationOffset + invincibleMarioAnimationOffset 
                + fireMarioAnimationOffset;
    }
    
    @Override
    protected void updateAnimation() {
        flipSpriteAccordingToDirection = true;
        int animationOffset = getAnimationOffset();
        if (getActorState() != ActorState.ALIVE) {
            animation = null;
            tileId = 4511 + animationOffset;
        }
        // jumping
        else if (jumping) {
            animation = null;
            tileId = 4510 + animationOffset;
        }
        // idle
        else if (Math.abs(getVelocity().getX()) < 0.01) {
            animation = null;
            tileId = 4505 + animationOffset;
        }
        // slipping right
        else if (getVelocity().getX() > 0 && pressingLeft) {
            flipSpriteAccordingToDirection = false;
            flipSpriteHorizontal = true;
            animation = null;
            tileId = 4509 + animationOffset;
            if (Input.isKeyPressed(KeyEvent.VK_LEFT)) {
                Sound.play("skid");
            }
        }
        // slipping left
        else if (getVelocity().getX() < 0 && pressingRight) {
            flipSpriteAccordingToDirection = false;
            flipSpriteHorizontal = false;
            animation = null;
            tileId = 4509 + animationOffset;
            if (Input.isKeyPressed(KeyEvent.VK_RIGHT)) {
                Sound.play("skid");
            }
        }
        // walking
        else if (getVelocity().getX() != 0) {
            animationTimeScale = 10 * (0.5 + Math.abs(getVelocity().getX()));
            setAnimation(4506 + animationOffset, 4507 + animationOffset, 4508 + animationOffset);
        }
    }

    @Override
    protected void fixedUpdateMarioTransforming() {
        switch (transformation) {
            case NORMAL : updateTransformingToNormalMario(); break;
            case SUPER : updateTransformingToSuperMario(); break;  
            case INVINCIBLE : updateTransformingToInvincibleMario(); break;
            case FIRE : updateTransformingToFireMario(); break;
        }
    }
    
    private void updateTransformingToSuperMario() {
        transformationVelocity -= 4.5 * Time.getFixedDeltaTime();
        transformationFrame += transformationVelocity * Time.getFixedDeltaTime();
        if (((int) transformationFrame % 2) != 0 || transformationVelocity <= 11)  {
            transformToSuperMario();
        }
        else {
            transformToNormalMario();
        }
        updateAdditionalStates();
        updateAnimation();
        if (transformationVelocity <= 10) {
            game.setGameState(PLAYING);
        }
    }

    private void updateTransformingToNormalMario() {
        transformationVelocity -= 4 * Time.getFixedDeltaTime();
        transformationFrame += transformationVelocity * Time.getFixedDeltaTime();
        if (((int) transformationFrame % 2) != 0 || transformationVelocity <= 11)  {
            transformToNormalMario();
        }
        else {
            transformToSuperMario();
        }
        updateAdditionalStates();
        updateAnimation();
        if (transformationVelocity <= 10) {
            game.setGameState(PLAYING);
            setImmortal();
        }
    }
    
    private void updateTransformingToFireMario() {
        transformationVelocity -= 4 * Time.getFixedDeltaTime();
        transformationFrame += 0.75 * transformationVelocity * Time.getFixedDeltaTime();
        int frame = 3 + ((int) transformationFrame % 4);
        fireMarioAnimationOffset = frame * (3 * spriteSheet.getCols());
        updateAnimation();
        if (transformationVelocity <= 11) {
            transformToFireMario();
            game.setGameState(PLAYING);
        }
    }
    
    private void updateTransformingToInvincibleMario() {
        //transformationVelocity -= 4 * Time.getFixedDeltaTime();
        //transformationFrame += 0.75 * transformationVelocity * Time.getFixedDeltaTime();
        //int frame = 3 + ((int) transformationFrame % 4);
        //invincibleMarioAnimationOffset = frame * (3 * spriteSheet.getCols());
        //updateAnimation();
        //if (transformationVelocity <= 11) {
            Music.play("invincible");
            transformToInvicibleMario();
            game.setGameState(PLAYING);
        //}
    }
    
    @Override
    protected void checkImpactToTileWhenJumping() {
        int hw = getWidth() / 2;
        int px = (int) position.getX();
        int py = (int) (position.getY() - getHeight() - 3);
        boolean collided = false;
        if (map.getTileByWorld(px, py).isRigid()) {
            map.convertWorldToCell(px, py, cellPositionTmp);
            tilemap.applyImpactToTile(cellPositionTmp.x, cellPositionTmp.y, impactStrength);
            collided = true;
        }
        else if (map.getTileByWorld(px - hw, py).isRigid()) {
            map.convertWorldToCell(px - 8, py, cellPositionTmp);
            tilemap.applyImpactToTile(cellPositionTmp.x, cellPositionTmp.y, impactStrength);
            collided = true;
        }
        else if (map.getTileByWorld(px + hw - 1, py).isRigid()) {
            map.convertWorldToCell(px + hw - 1, py, cellPositionTmp);
            tilemap.applyImpactToTile(cellPositionTmp.x, cellPositionTmp.y, impactStrength);
            collided = true;
        }
        
        if (collided) {
            velocity.setY(0);
            jumping = false;
        }
    }

    @Override
    public void lateUpdatePlaying() {
        if (killedEnemy) {
            killedEnemy = false;
            getVelocity().setY(-5);
        }
    }
    
    @Override
    protected void updateDead() {
        if (Time.getCurrentTime() - deadTime < 3) {
            return;
        }

        if (killedByTimeLeft) {
            game.tryNextLifeByTimeLeft();
        }
        else {
            game.tryNextLife();
        }
    }
    
    public void onEnemyKilledByStump() {
        killedEnemy = true;
    }

    @Override
    public void applyDamage() {
        if (superMario) {
            transform(Transformation.NORMAL);
        }
        else {
			if(add_life!=1)
			{
				add_life=0;
				kill();
			}
				
        }
    }
    
    public void transform(Transformation transformation) {
        transformationFrame = 0;
        transformationVelocity = 15;
        this.transformation = transformation;
        switch (transformation) {
            case NORMAL : 
                fireMario = false;
                transformToNormalMario(); 
                Sound.play("warp");
                break;
            case SUPER : 
                transformToSuperMario(); 
                Sound.play("power_up");
                break;  
            case INVINCIBLE : 
                transformToInvicibleMario(); 
                Music.stop();
                Sound.play("power_up");
                break;  
            case FIRE : 
                transformToFireMario(); 
                Sound.play("power_up");
                break;  
        }
        game.setGameState(MARIO_TRANSFORMING);
        setVisible(true);
    }
    
    private void transformToNormalMario() {
        superMario = false;
        impactStrength = ImpactStrength.WEAK;
        superMarioAnimationOffset = 0;
        setColliderSize(16, 16);
        spriteHeight = 16;
    }

    private void transformToSuperMario() {
        superMario = true;
        impactStrength = ImpactStrength.STRONG;
        superMarioAnimationOffset = -spriteSheet.getCols();
        setColliderSize(16, 32);
        spriteHeight = 32;
    }
    
    private void transformToInvicibleMario() {
        invincibleMario = true;
        invincibleMarioStartTime = Time.getCurrentTime();
    }

    private void transformToFireMario() {
        fireMario = true;
        fireMarioStartTime = Time.getCurrentTime();
    }
    
    @Override
    public void kill() {
        if (isDead()) {
            return;
        }
        setActorState(ActorState.DYING);
        dyingTime = Time.getCurrentTime();
        velocity.setY(-7);
        gravityScale = 0.75;
        Music.stop();
        Sound.play("die");
    }    

    private void fire() {
        for (Fireball fireball : fireballs) {
            if (fireball.isFree()) {
                fireball.spawn(this);
                lastFireTime = Time.getCurrentTime();
                Sound.play("fireball");
                break;
            }
        }
    }

    // level cleared
    
    private int ip;
    private double waitTime;
    
    @Override
    protected void fixedUpdateLevelCleared() {
        switch (ip) {
            case 0:
                waitTime = Time.getCurrentTime();
                ip = 1;
            case 1:
                updateGoingDownGoalPole();
                break;
            case 2:
                if (Time.getCurrentTime() - waitTime < 0.25) {
                    return;
                }
                ip = 3;
            case 3:
                updateGoingNextLevelEntrance();
                break;
            case 4:
                setVisible(false);
                ip = 5;
            case 5:
                updateConvertTimeleftToScore();
                break;
            case 6:
                if (Time.getCurrentTime() - waitTime < 0.01) {
                    return;
                }
                ip = 5;
                break;
            case 7:
                if (Time.getCurrentTime() - waitTime < 3) {
                    return;
                }
                game.nextLevel();
                ip = 8;
            case 8:
        }
    }
    
    private void updateGoingDownGoalPole() {
        if (isCollidingWithFloor()) {
            position.translate(Map.TILE_SIZE / 2, 0);
            velocity.setY(-2);
            jumping = false;
            flipSpriteHorizontal = true;
            position.setX((game.getLevelClearedCol() + 1) * Map.TILE_SIZE - 4);
            waitTime = Time.getCurrentTime();
            ip = 2;
        }
        else {
            position.translate(0, 1);
            updateAnimationFrame();
        }
    }

    private void updateGoingNextLevelEntrance() {
        if (position.getX() >= (game.getNextLevelEntranceCol() + 0.5) * Map.TILE_SIZE) {
            ip = 4;
        }
        else {
            double currentVx = velocity.getX();
            moveRight(currentVx);
            updatePhysics();
            updateAnimation();
            updateAnimationFrame();
        }
    }
    
    private void updateConvertTimeleftToScore() {
        if ((int) game.getTimeLeft() > 0) {
            game.decTimeLeft();
            game.addScore(100);
            if ((int) game.getTimeLeft() % 3 == 0) {
                Sound.play("beep");
            }
            waitTime = Time.getCurrentTime();
            ip = 6;
        }
        else {
            game.setTimeLeft(0);
            game.getFlag().show();
            waitTime = Time.getCurrentTime();
            ip = 7;
        }
    }
    
    // game started or when start next life

    @Override
    protected void updateStartGame() {
        game.setGameState(LIVES_PRESENTATION);
    }

    @Override
    protected void updateStartNextLife() {
        game.setGameState(LIVES_PRESENTATION);
    }
    
    // move mario randomically in the title screen
    
    protected double direction = -1;
    private int nextWalkDuration = 60;
    private int nextJumping = 379;
    
    @Override
    protected void fixedUpdateTitle() {
        if (position.getX() <= 3 * Map.TILE_SIZE && direction < 0) {
            direction = 1;
        }
        else if (position.getX() >= Display.SCREEN_WIDTH - 3 * Map.TILE_SIZE && direction > 0) {
            direction = -1;
        }
        
        boolean keepWaking = (Time.getFixedFrames() % 317) < nextWalkDuration;
        if (!keepWaking) {
            nextWalkDuration = (int) (20 + 100 * Math.random());
        }
        pressingRight = direction > 0 && keepWaking;
        pressingLeft = direction < 0 && keepWaking;
        
        if (!jumping && (Time.getFixedFrames() % nextJumping) == 0) {
            nextJumping = (int) (60 + 300 * Math.random());
            velocity.translate(0, -7);
            jumping = true;
        }
        else if (jumping && isCollidingWithFloor()) {
            jumping = false;
        }
        
        double currentVx = velocity.getX();
        if (pressingLeft) {
            moveLeft(currentVx);
        }
        else if (pressingRight) {
            moveRight(currentVx);
        }
        else {
            velocity.scale(0.95, 1);
        } 
            
        updatePhysics();
        updateAnimation();
        updateAnimationFrame();
    }    
    
    @Override
    protected void onActorStateChanged(ActorState newState) {
        if (newState == ActorState.DEAD) {
            deadTime = Time.getCurrentTime();
        }
    }
    
    @Override
    public void onGameStateChanged(Game.GameState newGameState) {
        switch (newGameState) {
            case TITLE:
                setVisible(true);
                setActorState(ActorState.ALIVE);
                double wx = 3 * Map.TILE_SIZE;
                double wy = 13 * Map.TILE_SIZE;
                position.set(wx, wy);
                velocity.set(0, 0);
                reset();
                break;
            case START_GAME:
                setActorState(ActorState.ALIVE);
                wx = game.getLastCheckpoint().getCol() * Map.TILE_SIZE;
                wy = game.getLastCheckpoint().getRow() * Map.TILE_SIZE;
                position.set(wx, wy);
                velocity.set(0, 0);
                reset();
                break;
            case START_NEXT_LIFE:
                setActorState(ActorState.ALIVE);
                wx = game.getLastCheckpoint().getCol() * Map.TILE_SIZE;
                wy = game.getLastCheckpoint().getRow() * Map.TILE_SIZE;
                position.set(wx, wy);
                velocity.set(0, 0);
                reset();
                break;
            case PLAYING:
                break;
            case LEVEL_CLEARED:
                ip = 0;
                waitTime = 0;
                int animationOffset = getAnimationOffset();
                setAnimation(4512 + animationOffset, 4513 + animationOffset, 4514 
                        + animationOffset, 4513 + animationOffset);
                Music.stop();
                Sound.play("level_clear");
                break;
        }
    }

}
