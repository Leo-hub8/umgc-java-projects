package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.TextureKey;
import com.jme3.audio.AudioData;
import com.jme3.audio.AudioData.DataType;
import com.jme3.audio.AudioNode;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.font.BitmapText;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Sphere.TextureMode;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

/**
 * 
 * 
 * @author Caleb 
 */
public class ModelGame extends SimpleApplication {
    
    private AudioNode audio_gun, audio_nature;
    

    
    public static void main(String[] args) {
        ModelGame app = new ModelGame();
        app.start();
    }
    
  private BulletAppState bulletAppState;

  // Materials 
  Material wall_mat;
  Material stone_mat;
  Material floor_mat;
  Material pyro_mat;
 
  private RigidBodyControl    brick_phy;
  private static final Box    box;
  private RigidBodyControl    ball_phy;
  private static final Sphere sphere;
  private RigidBodyControl    floor_phy;
  private static final Box    floor;
  
  private static final float brickLength = 0.50f;
  private static final float brickWidth  = 0.50f;
  private static final float brickHeight = 0.50f;
  
  
  static {
    /** Initialize the cannon ball geometry */
    sphere = new Sphere(32, 32, 0.4f, true, false);
    sphere.setTextureMode(TextureMode.Projected);
    /** Initialize the brick geometry */
    box = new Box(brickLength, brickHeight, brickWidth);
    box.scaleTextureCoordinates(new Vector2f(1f, .5f));
    /** Initialize the floor geometry */
    floor = new Box(100f, 0.1f, 100f);
    floor.scaleTextureCoordinates(new Vector2f(3, 6));
  }
  

  @Override
  public void simpleInitApp() {
    
    bulletAppState = new BulletAppState();
    stateManager.attach(bulletAppState);
    //bulletAppState.getPhysicsSpace().enableDebug(assetManager);

    // camera
    cam.setLocation(new Vector3f(0, 4f, 6f));
    cam.lookAt(new Vector3f(2, 2, 0), Vector3f.UNIT_Y);
    
    viewPort.setBackgroundColor(new ColorRGBA(0.7f,0.8f,1f,2f));
        flyCam.setMoveSpeed(50);
        
    /** Add InputManager action: Left click triggers shooting. */
    inputManager.addMapping("shoot",
            new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
    inputManager.addListener(actionListener, "shoot");
    /** Initialize the scene, materials, and physics space */
    initMaterials();
    initWall();
    initFloor();
    initCrossHairs();
    initAudio();
  }
        private void initAudio(){
            audio_nature = new AudioNode(assetManager, "Sound/Environment/Ocean Waves.ogg", AudioData.DataType.Stream);
            audio_nature.setLooping(true);
            audio_nature.setPositional(true);
            audio_nature.setVolume(5);
            rootNode.attachChild(audio_nature);
            audio_nature.play();
            
            audio_gun = new AudioNode(assetManager, "Sound/Effects/Gun.wav", DataType.Buffer);
            audio_gun.setPositional(false);
            audio_gun.setLooping(false);
            audio_gun.setVolume(2);
            rootNode.attachChild(audio_gun);
        }

  private final ActionListener actionListener = new ActionListener() {
    @Override
    public void onAction(String name, boolean keyPressed, float tpf) {
      if (name.equals("shoot") && !keyPressed) {
          audio_gun.playInstance();
        makeCannonBall();
      }
    }
  };
  

  // Initialize 
  public void initMaterials() {
    wall_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    TextureKey key = new TextureKey("Textures/Terrain/BrickWall/BrickWall.jpg");
    key.setGenerateMips(true);
    Texture tex = assetManager.loadTexture(key);
    wall_mat.setTexture("ColorMap", tex);

    stone_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    TextureKey key2 = new TextureKey("Textures/Sky/St Peters/StPeters.jpg");
    key2.setGenerateMips(true);
    Texture tex2 = assetManager.loadTexture(key2);
    stone_mat.setTexture("ColorMap", tex2);

    floor_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    TextureKey key3 = new TextureKey("Textures/Terrain/splat/dirt.jpg");
    key3.setGenerateMips(true);
    Texture tex3 = assetManager.loadTexture(key3);
    tex3.setWrap(WrapMode.Repeat);
    floor_mat.setTexture("ColorMap", tex3);
  }

  //solid floor 
  public void initFloor() {
    Geometry floor_geo = new Geometry("Floor", floor);
    floor_geo.setMaterial(floor_mat);
    floor_geo.setLocalTranslation(0, -0.1f, 0);
    this.rootNode.attachChild(floor_geo);
    /* Make the floor physical with mass 0.0f! */
    floor_phy = new RigidBodyControl(0.0f);
    floor_geo.addControl(floor_phy);
    bulletAppState.getPhysicsSpace().add(floor_phy);
  }
  
  //loop to build wall
  public void initWall() {
    float startpt = brickLength / 4;
    float height = 0;
    for (int j = 0; j < 15; j++) {
      for (int i = 0; i < 6; i++) {
        Vector3f vt =
         new Vector3f(i * brickLength * 2 + startpt, brickHeight + height, 0);
        makeBrick(vt);
      }
      startpt = -startpt;
      height += 2 * brickHeight;
    }
  }

  
  public void makeBrick(Vector3f loc) {
    Geometry brick_geo = new Geometry("brick", box);
    brick_geo.setMaterial(wall_mat);
    rootNode.attachChild(brick_geo);
    brick_geo.setLocalTranslation(loc);
    brick_phy = new RigidBodyControl(2f);
    brick_geo.addControl(brick_phy);
    bulletAppState.getPhysicsSpace().add(brick_phy);
  }

  
   public void makeCannonBall() {
    Geometry ball_geo = new Geometry("cannon ball", sphere);
    ball_geo.setMaterial(stone_mat);
    rootNode.attachChild(ball_geo);
    ball_geo.setLocalTranslation(cam.getLocation());
    ball_phy = new RigidBodyControl(1f);
    ball_geo.addControl(ball_phy);
    bulletAppState.getPhysicsSpace().add(ball_phy);
    ball_phy.setLinearVelocity(cam.getDirection().mult(25));
  }

  
  protected void initCrossHairs() {
    guiNode.detachAllChildren();
    guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
    BitmapText ch = new BitmapText(guiFont, false);
    ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
    ch.setText("+");        // fake crosshairs :)
    ch.setLocalTranslation( // center
      settings.getWidth() / 2 - guiFont.getCharSet().getRenderedSize() / 3 * 2,
      settings.getHeight() / 2 + ch.getLineHeight() / 2, 0);
    guiNode.attachChild(ch);
  }
}