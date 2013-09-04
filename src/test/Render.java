package test;
 
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;



import Geometry.*;


class Renderer implements GLEventListener, KeyListener  
{
    private GLU glu;
    public LoaderOBJ loader = new LoaderOBJ("scene2.obj");
    public Camera camera;
    private boolean forward=false, backward=false, left=false, right = false, up = false, down = false, linesOn;

    
    
    @Override
    public void display(GLAutoDrawable drawable) {
    	//float time=drawable.getAnimator().getLastFPS();
    	//System.out.println(time);
        update();
		render(drawable);
    }
    
   
	private void render(GLAutoDrawable gLDrawable)
    {
        GL2 gl = gLDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);      
        gl.glLoadIdentity();
        gl.glTranslatef(-camera.eyePos.x(), -camera.eyePos.y(), -camera.eyePos.z());  
    
        for(int i=0;i<loader.f.size();i++)
        {
        	gl.glBegin(GL2.GL_TRIANGLES);	
        		gl.glNormal3f(loader.f.get(i).normal[0].x(),loader.f.get(i).normal[0].y(),loader.f.get(i).normal[0].z());
	        	gl.glVertex3f(loader.f.get(i).getP1().x(), loader.f.get(i).getP1().z(), loader.f.get(i).getP1().y());
	        	gl.glNormal3f(loader.f.get(i).normal[1].x(),loader.f.get(i).normal[1].y(),loader.f.get(i).normal[1].z());
	        	gl.glVertex3f(loader.f.get(i).getP2().x(), loader.f.get(i).getP2().z(), loader.f.get(i).getP2().y());
	        	gl.glNormal3f(loader.f.get(i).normal[2].x(),loader.f.get(i).normal[2].y(),loader.f.get(i).normal[2].z());
	        	gl.glVertex3f(loader.f.get(i).getP3().x(), loader.f.get(i).getP3().z(), loader.f.get(i).getP3().y());
	        gl.glEnd();
	        	
	       /*
	       gl.glColor4f(1.0f,0.0f,0.0f,1.0f);
	       gl.glLineWidth(0.5f); 
	        gl.glBegin(GL2.GL_LINES);	
        	//gl.glNormal3d(y2-y1,-(x2-x1),0);  
	        	gl.glVertex3f(loader.f.get(i).getP1().x(), loader.f.get(i).getP1().z(), loader.f.get(i).getP1().y());
	        	gl.glVertex3f(loader.f.get(i).getP1().x()+loader.f.get(i).normal[0].x(), 
	        			 loader.f.get(i).getP1().z()+loader.f.get(i).normal[0].z(), 
	        			 loader.f.get(i).getP1().y()+loader.f.get(i).normal[0].y());
	        gl.glEnd();
	        
	        gl.glColor4f(0.0f,1.0f,0.0f,1.0f);
	        gl.glBegin(GL2.GL_LINES);	
        	//gl.glNormal3d(y2-y1,-(x2-x1),0);  
	        	gl.glVertex3f(loader.f.get(i).getP3().x(), loader.f.get(i).getP3().z(), loader.f.get(i).getP3().y());
	        	gl.glVertex3f(loader.f.get(i).getP3().x()+loader.f.get(i).normal[0].x(), 
	        			 loader.f.get(i).getP3().z()+loader.f.get(i).normal[0].z(), 
	        			 loader.f.get(i).getP3().y()+loader.f.get(i).normal[0].y());
	        	gl.glEnd();
	        	gl.glColor4f(0.0f,0.0f,1.0f,1.0f);
        	gl.glBegin(GL2.GL_LINES);	
        	//gl.glNormal3d(y2-y1,-(x2-x1),0);  
	        	gl.glVertex3f(loader.f.get(i).getP2().x(), loader.f.get(i).getP2().z(), loader.f.get(i).getP2().y());
	        	gl.glVertex3f(loader.f.get(i).getP2().x()+loader.f.get(i).normal[0].x(), 
	        			 loader.f.get(i).getP2().z()+loader.f.get(i).normal[0].z(), 
	        			 loader.f.get(i).getP2().y()+loader.f.get(i).normal[0].y());
	        	gl.glEnd();
        	
	        
        	if(linesOn)
        	{
	        	gl.glColor4f(0.2f, 0.6f, 0.7f,1);
	        	gl.glBegin(GL2.GL_LINES);
		        	gl.glVertex3f(loader.f.get(i).getP1().x(), loader.f.get(i).getP1().z(), loader.f.get(i).getP1().y());
		        	gl.glVertex3f(loader.f.get(i).getP2().x(), loader.f.get(i).getP2().z(), loader.f.get(i).getP2().y());
	        	gl.glEnd();
	        	gl.glColor4f(0.2f, 0.6f, 0.7f,1);
	        	gl.glBegin(GL2.GL_LINES);
		        	gl.glVertex3f(loader.f.get(i).getP1().x(), loader.f.get(i).getP1().z(), loader.f.get(i).getP1().y());
		        	gl.glVertex3f(loader.f.get(i).getP3().x(), loader.f.get(i).getP3().z(), loader.f.get(i).getP3().y());
	        	gl.glEnd();
	        	gl.glColor4f(0.2f, 0.6f, 0.7f,1);
	        	gl.glBegin(GL2.GL_LINES);
		        	gl.glVertex3f(loader.f.get(i).getP3().x(), loader.f.get(i).getP3().z(), loader.f.get(i).getP3().y());
		        	gl.glVertex3f(loader.f.get(i).getP2().x(), loader.f.get(i).getP2().z(), loader.f.get(i).getP2().y());
	        	gl.glEnd();
        	}
        	*/
        	
        }
        gl.glFlush();
    }
    
    private void update()
    {
    	 if(forward)
    		 camera.moveCamera(new Vector3(0,0,100));
    	 if(backward)
    		 camera.moveCamera(new Vector3(0,0,-100));
    	 if(left)
    		 camera.moveCamera(new Vector3(100,0,0));
    	 if(right)
    		 camera.moveCamera(new Vector3(-100,0,0));
    	 if(up)
    		 camera.moveCamera(new Vector3(0,10,0));		
    	 if(down)
    		 camera.moveCamera(new Vector3(0,-10,0));	 	 
    }
    
    @Override
    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height) 
    {
    	System.out.println("reshape() called: x = "+x+", y = "+y+", width = "+width+", height = "+height);
        final GL2 gl = gLDrawable.getGL().getGL2();
 
        if (height <= 0) // avoid a divide by zero error!
        {
            height = 1;
        }
 
        final float h = (float) width / (float) height;
 
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60.0f, h, 1.0, 15000.0);
        glu.gluLookAt(	0,	0,	0,
				0,	0,	1000, 0,1,0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void init(GLAutoDrawable gLDrawable) 
    {
    	//gLDrawable.getAnimator().setUpdateFPSFrames(2, null);
    	System.out.println("init() called");
        GL2 gl = gLDrawable.getGL().getGL2();
        glu = GLU.createGLU(gl);
        gl.glClearColor(0.0f, 0.78f, 0.94f, 0.0f); // set background (clear) color
        gl.glClearDepth(1.0f);      // set clear depth value to farthest
        gl.glEnable(GL2.GL_DEPTH_TEST); // enables depth testing
        gl.glDepthFunc(GL2.GL_LESS);  // the type of depth test to do
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); // best perspective correction
        gl.glShadeModel(GL2.GL_SMOOTH); // blends colors nicely, and smoothes out lighting
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA); 
    	gl.glEnable( GL2.GL_BLEND ); 
        ((Component) gLDrawable).addKeyListener(this);
        //initLight(gl);
        doLighting(gl);
        float 	xx = loader.v.get(0).x(),
         		yy = loader.v.get(0).y(),
         		zz = loader.v.get(0).z();
        camera = new Camera(xx,zz,yy,xx,zz,yy+15000);
    }
    
    private void doLighting( GL2 gl )
    {
    	float[] LightAmbient= { 0.8f, 0.8f, 0.8f, 1.0f };
    	float[] LightDiffuse= { 0.34f, 0.7f, 0.0f, 1.0f };
    	float[] lightSpecular = {0.3f, 0.5f, 0.6f, 1f};
    	float z = loader.v.get(0).z();
        z+=500;
        float[] LightPosition = {loader.v.get(0).x(),z,loader.v.get(0).y(),1};
    	gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, LightAmbient,0);
    	gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, LightDiffuse,0);
    	gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION,LightPosition,0);
    	gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, lightSpecular, 0);
    	gl.glEnable(GL2.GL_LIGHT1);  
    	gl.glEnable(GL2.GL_LIGHTING); 
    	/*
		// Setup lighting
		float[] ambientColor = { 0.4f, 0.4f, 0.4f, 1.0f };

		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, lightPosition, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, lightAmbient, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, lightDiffuse, 0);
		gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, lightSpecular, 0);
		gl.glEnable( GL2.GL_LIGHTING );
		gl.glEnable(GL2.GL_LIGHT1);
		/*
        GlobalData.lightPos[0] = 50005;
        GlobalData.lightPos[1] = 30000;
        GlobalData.lightPos[2] = 50000;
        GlobalData.lightPos[3] = 1;
        gl.glEnable(GLLightingFunc.GL_LIGHTING);
        gl.glEnable(GLLightingFunc.GL_LIGHT0);
        float[] noAmbient ={ 0.1f, 0.1f, 0.1f, 1f }; // low ambient light
        float[] spec =    { 1f, 0.6f, 0f, 1f }; // low ambient light
        float[] diffuse ={ 1f, 1f, 1f, 1f };
        // properties of the light
        gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_AMBIENT, noAmbient, 0);
        gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_SPECULAR, spec, 0);
        gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_DIFFUSE, diffuse, 0);
        gl.glLightfv(GLLightingFunc.GL_LIGHT0, GLLightingFunc.GL_POSITION, GlobalData.lightPos, 0);
        */
    }
    
    public void initLight( GL2 gl)
    {
      // First Switch the lights on.
      gl.glEnable( GL2.GL_LIGHTING );
      gl.glEnable( GL2.GL_LIGHT1 );

      float z = loader.v.get(0).z();
      z+=1000;
      float[] lightPos = {loader.v.get(0).x(),z,loader.v.get(0).y(),1};
      float[] lightColorAmbient = {0.7f, 0.6f, 0.4f, 1f};
      float[] lightColorSpecular = {0.3f, 0.5f, 0.6f, 1f};
      
      // Position and direction (spotlight)
      gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, lightPos, 0);
      gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, lightColorAmbient, 0);
      gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, lightColorSpecular, 0);
      gl.glLightf( GL2.GL_LIGHT1, GL2.GL_CONSTANT_ATTENUATION, 0.2f );

    }
    
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);	
		if (keyCode == KeyEvent.VK_W)
			forward = true;
		if (keyCode == KeyEvent.VK_S)
			backward = true;
		if (keyCode == KeyEvent.VK_A) 
			left = true;
		if (keyCode == KeyEvent.VK_D)
			right = true;	
		if (keyCode == KeyEvent.VK_Q)
			up = true;	
		if (keyCode == KeyEvent.VK_E)
			down = true;	
		if (keyCode == KeyEvent.VK_1)
			linesOn = linesOn?false:true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);
		if (keyCode == KeyEvent.VK_W)
			forward = false;
		if (keyCode == KeyEvent.VK_S)
			backward = false;
		if (keyCode == KeyEvent.VK_A)
			left = false;
		if (keyCode == KeyEvent.VK_D)
			right = false;
		if (keyCode == KeyEvent.VK_Q)
			up = false;
		if (keyCode == KeyEvent.VK_E)
			down = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	
	public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged, boolean deviceChanged) 
	{
		System.out.println("displayChanged called");
	}
	
    @Override
	public void dispose(GLAutoDrawable arg0) 
	{
		System.out.println("dispose() called");
	}
    
}