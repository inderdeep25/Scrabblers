package com.threed.jpct.example;

import java.lang.reflect.Field;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.sprites.SimpleSpriteBlueprint;
import com.sprites.SpriteBlueprintProvider;
import com.sprites.SpriteManager;
import com.sprites.TextSpriteBlueprint;
import com.threed.jpct.Camera;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Light;
import com.threed.jpct.Logger;
import com.threed.jpct.Object3D;
import com.threed.jpct.Primitives;
import com.threed.jpct.RGBColor;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;
import com.threed.jpct.World;
import com.threed.jpct.util.BitmapHelper;
import com.threed.jpct.util.MemoryHelper;

/**
 * A simple demo. This shows more how to use jPCT-AE than it shows how to write
 * a proper application for Android. It includes basic activity management to
 * handle pause and resume...
 * 
 * @author EgonOlsen
 * 
 */
public class HelloWorld extends Activity {

	// Used to handle pause and resume...
	private static HelloWorld master = null;

	private GLSurfaceView mGLView;
	private MyRenderer renderer = null;
	private FrameBuffer fb = null;
	private World world = null;
	private RGBColor back = new RGBColor(50, 50, 100);
	int[][] x=new int[5][5];
	private float touchTurn = 0;
	private float touchTurnUp = 0;

	private float xpos = -1;
	private float ypos = -1;

	private Object3D cube = null;
	private int fps = 0;

	private Light sun = null;

	protected void onCreate(Bundle savedInstanceState) {

		Logger.log("onCreate");

		if (master != null) {
			copy(master);
		}

		super.onCreate(savedInstanceState);
		mGLView = new GLSurfaceView(getApplication());

		mGLView.setEGLConfigChooser(new GLSurfaceView.EGLConfigChooser() {
			public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) {
				// Ensure that we get a 16bit framebuffer. Otherwise, we'll fall
				// back to Pixelflinger on some device (read: Samsung I7500)
				int[] attributes = new int[] { EGL10.EGL_DEPTH_SIZE, 16, EGL10.EGL_NONE };
				EGLConfig[] configs = new EGLConfig[1];
				int[] result = new int[1];
				egl.eglChooseConfig(display, attributes, configs, 1, result);
				return configs[0];
			}
		});

		renderer = new MyRenderer();
		mGLView.setRenderer(renderer);
		setContentView(mGLView);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mGLView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mGLView.onResume();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	private void copy(Object src) {
		try {
			Logger.log("Copying data from master Activity!");
			Field[] fs = src.getClass().getDeclaredFields();
			for (Field f : fs) {
				f.setAccessible(true);
				f.set(this, f.get(src));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean onTouchEvent(MotionEvent me) {

		if (me.getAction() == MotionEvent.ACTION_DOWN) {
			xpos = me.getX();
			ypos = me.getY();
			return true;
		}

		if (me.getAction() == MotionEvent.ACTION_UP) {
			xpos = -1;
			ypos = -1;
			touchTurn = 0;
			touchTurnUp = 0;
			return true;
		}

		if (me.getAction() == MotionEvent.ACTION_MOVE) {
			float xd = me.getX() - xpos;
			float yd = me.getY() - ypos;

			xpos = me.getX();
			ypos = me.getY();

			touchTurn = xd / -100f;
			touchTurnUp = yd / -100f;
			return true;
		}

		try {
			Thread.sleep(15);
		} catch (Exception e) {
			// No need for this...
		}

		return super.onTouchEvent(me);
	}

	protected boolean isFullscreenOpaque() {
		return true;
	}

	class MyRenderer implements GLSurfaceView.Renderer {

		private long time = System.currentTimeMillis();

		public MyRenderer() {
		}

		public void onSurfaceChanged(GL10 gl, int w, int h) {
			if (fb != null) {
				fb.dispose();
			}
			fb = new FrameBuffer(gl, w, h);

			if (master == null) {

				world = new World();
				world.setAmbientLight(20, 20, 20);

				sun = new Light(world);
				sun.setIntensity(250, 250, 250);
				
				/*Texture boardTileTexture=new Texture(getResources().openRawResource(R.raw.tile2),true);
				boardTileTexture.setFiltering(false);
				TextureManager.getInstance().addTexture("board_tile_texture", boardTileTexture);*/
				Texture blankTileTexture = new Texture(getResources().openRawResource(R.raw.blank), true);
				blankTileTexture.setFiltering(false);
				TextureManager.getInstance().addTexture("blank_tile_texture", blankTileTexture);
				
				Texture alphabetAtlasTexture = new Texture(getResources().openRawResource(R.raw.alpha_a), true);
				alphabetAtlasTexture.setFiltering(false);
				TextureManager.getInstance().addTexture("alphabet_atlas", alphabetAtlasTexture);
				
				Texture numberAtlasTexture = new Texture(getResources().openRawResource(R.raw.num1), true);
				numberAtlasTexture.setFiltering(false);
				TextureManager.getInstance().addTexture("number_atlas", numberAtlasTexture);
				
				
				
				SpriteBlueprintProvider.GetInstance().AddSimpleSpriteBlueprint("blank_tile_blueprint", new SimpleSpriteBlueprint("blank_tile_texture", new SimpleVector(0,0,0), 1.5f));
				SpriteBlueprintProvider.GetInstance().AddTextSpriteBlueprint("alphabet_blueprint", new TextSpriteBlueprint("A", "alphabet_atlas", new SimpleVector(0,0,0), 1.5f, new char[] {'A'}, 16, 16));
				SpriteBlueprintProvider.GetInstance().AddTextSpriteBlueprint("number_blueprint", new TextSpriteBlueprint("1", "number_atlas", new SimpleVector(0,0,0), 1.5f, new char[] {'1'}, 8, 8));
				TileAC ti = new TileAC(new SimpleVector(52,52,0),"A","1");
				
				
				//TileAC ob=new TileAC();
				
				
				// Create a texture out of the icon...:-)
				//Texture texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(getResources().getDrawable(R.drawable.icon)), 64, 64));
				Manager obj =new Manager();
				
				x=obj.createBoard(5);
				for(int i=0;i<5;i++)
					for(int j=0;j<5;j++){
						if(x[i][j]==0){
							Log.d("x",Integer.toString(x[i][j]));
							Texture boardAtlasTexture = new Texture(getResources().openRawResource(R.raw.tile2), true);
							boardAtlasTexture.setFiltering(false);
							TextureManager.getInstance().addTexture("board_tile_atlas"+Integer.toString(i)+Integer.toString(j), boardAtlasTexture);
							SpriteBlueprintProvider.GetInstance().AddTextSpriteBlueprint("Board_blueprint",
									new TextSpriteBlueprint(Integer.toString(x[i][j]),"board_tile_atlas"+Integer.toString(i)+Integer.toString(j),
											new SimpleVector(0,0, 0),1.5f,new char[]{'0','3','1','2','4'},32,32));
							TileAC tile = new TileAC(new SimpleVector( ((float) i)*52,((float) j)*52, 0),Integer.toString(x[i][j]));
							}
						else if(x[i][j]==10){
							Log.d("x",Integer.toString(1));
							Texture boardAtlasTexture = new Texture(getResources().openRawResource(R.raw.tile2), true);
							boardAtlasTexture.setFiltering(false);
							TextureManager.getInstance().addTexture("board_tile_atlas"+Integer.toString(i)+Integer.toString(j), boardAtlasTexture);
							SpriteBlueprintProvider.GetInstance().AddTextSpriteBlueprint("Board_blueprint",
															new TextSpriteBlueprint(Integer.toString(1),"board_tile_atlas"+Integer.toString(i)+Integer.toString(j),
																	new SimpleVector(0,0, 0),1.5f,new char[]{'0','3','1','2','4'},32,32));
							TileAC tile = new TileAC(new SimpleVector(((float) i)*52,((float) j)*52, 0),Integer.toString(1));
						}
						else if(x[i][j]==1){
							Log.d("x",Integer.toString(x[i][j]));
							Texture boardAtlasTexture = new Texture(getResources().openRawResource(R.raw.tile2), true);
							boardAtlasTexture.setFiltering(false);
							TextureManager.getInstance().addTexture("board_tile_atlas"+Integer.toString(i)+Integer.toString(j), boardAtlasTexture);
							SpriteBlueprintProvider.GetInstance().AddTextSpriteBlueprint("Board_blueprint",
															new TextSpriteBlueprint(Integer.toString(x[i][j]),"board_tile_atlas"+Integer.toString(i)+Integer.toString(j),
																	new SimpleVector(0,0, 0),1.5f,new char[]{'0','3','1','2','4'},32,32));
							TileAC tile = new TileAC(new SimpleVector(((float) i)*52,((float) j)*52, 0),Integer.toString(x[i][j]));
						}
						else if(x[i][j]==2){
							Log.d("x",Integer.toString(x[i][j]));
							Texture boardAtlasTexture = new Texture(getResources().openRawResource(R.raw.tile2), true);
							boardAtlasTexture.setFiltering(false);
							TextureManager.getInstance().addTexture("board_tile_atlas"+Integer.toString(i)+Integer.toString(j), boardAtlasTexture);
							SpriteBlueprintProvider.GetInstance().AddTextSpriteBlueprint("Board_blueprint",
															new TextSpriteBlueprint(Integer.toString(x[i][j]),"board_tile_atlas"+Integer.toString(i)+Integer.toString(j),
																	new SimpleVector(0,0, 0),1.5f,new char[]{'0','3','1','2','4'},32,32));
							TileAC tile = new TileAC(new SimpleVector(((float) i)*52,((float) j)*52, 0),Integer.toString(x[i][j]));
						}
						else if(x[i][j]==3){
							Log.d("x",Integer.toString(x[i][j]));
							Texture boardAtlasTexture = new Texture(getResources().openRawResource(R.raw.tile2), true);
							boardAtlasTexture.setFiltering(false);
							TextureManager.getInstance().addTexture("board_tile_atlas"+Integer.toString(i)+Integer.toString(j), boardAtlasTexture);
							SpriteBlueprintProvider.GetInstance().AddTextSpriteBlueprint("Board_blueprint",
															new TextSpriteBlueprint(Integer.toString(x[i][j]),"board_tile_atlas"+Integer.toString(i)+Integer.toString(j),
																	new SimpleVector(0,0, 0),1.5f,new char[]{'0','3','1','2','4'},32,32));
							TileAC tile = new TileAC(new SimpleVector(((float) i)*52,((float) j)*52, 0),Integer.toString(x[i][j]));
						}
						else if(x[i][j]==4){
							Log.d("x",Integer.toString(x[i][j]));
							Texture boardAtlasTexture = new Texture(getResources().openRawResource(R.raw.tile2), true);
							boardAtlasTexture.setFiltering(false);
							TextureManager.getInstance().addTexture("board_tile_atlas"+Integer.toString(i)+Integer.toString(j), boardAtlasTexture);
							SpriteBlueprintProvider.GetInstance().AddTextSpriteBlueprint("Board_blueprint",
															new TextSpriteBlueprint(Integer.toString(x[i][j]),"board_tile_atlas"+Integer.toString(i)+Integer.toString(j),
																	new SimpleVector(0,0, 0),1.5f,new char[]{'0','3','1','2','4'},32,32));
							TileAC tile = new TileAC(new SimpleVector(((float) i)*52,((float) j)*52, 0),Integer.toString(x[i][j]));
						}
					}
			
/*				Texture texture=new Texture(getResources().openRawResource(R.raw.alphabet),true);
				texture.setFiltering(false);
				TextureManager.getInstance().addTexture("texture", texture);
				//SpriteBlueprintProvider.GetInstance().AddtTextSpriteBlueprint("Alphabet",
				//								new TextSpriteBlueprint("text",texture,000,scale,char[] aplhabet,width));*/
				
				cube = Primitives.getCube(10);
				cube.calcTextureWrapSpherical();
				cube.setTexture("alphabet_atlas");
				cube.strip();
				cube.build();

				world.addObject(cube);

				Camera cam = world.getCamera();
				cam.moveCamera(Camera.CAMERA_MOVEOUT, 50);
				cam.lookAt(cube.getTransformedCenter());

				SimpleVector sv = new SimpleVector();
				sv.set(cube.getTransformedCenter());
				sv.y -= 100;
				sv.z -= 100;
				sun.setPosition(sv);
				MemoryHelper.compact();

				if (master == null) {
					Logger.log("Saving master Activity!");
					master = HelloWorld.this;
				}
			}
		}

		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		}

		public void onDrawFrame(GL10 gl) {
			if (touchTurn != 0) {
				cube.rotateY(touchTurn);
				touchTurn = 0;
			}

			if (touchTurnUp != 0) {
				cube.rotateX(touchTurnUp);
				touchTurnUp = 0;
			}

			fb.clear(back);
			world.renderScene(fb);
			world.draw(fb);
			SpriteManager.GetInstance().Draw(fb);
			fb.display();

			if (System.currentTimeMillis() - time >= 1000) {
				Logger.log(fps + "fps");
				fps = 0;
				time = System.currentTimeMillis();
			}
			fps++;
		}
	}
}