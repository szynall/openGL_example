package test;
 
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


import Geometry.Vector3;


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
        float distance;
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        
        gl.glTranslatef(-camera.eyePos.x(), -camera.eyePos.y(), -camera.eyePos.z());  
    
        for(int i=0;i<loader.f.size();i++)
        {
        	distance= getDistance(camera.eyePos,new Vector3(loader.v.get(loader.f.get(i).getP1()-1).getX(), 
        													loader.v.get(loader.f.get(i).getP1()-1).getZ(), 
        													loader.v.get(loader.f.get(i).getP1()-1).getY()));
        	gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA); 
        	gl.glEnable( GL2.GL_BLEND ); 
        	if(distance<16000)
        		gl.glColor4f(0.4f,(10000.0f-distance)/10000.0f, 0.7f,1.0f);
        	else
        		gl.glColor4f(0.4f,0.5f,0.3f,1.0f);
        	
        	/*
        	if(distance<2000)
        		gl.glColor4f(0.8f,0.2f,0.3f,1.0f);
        	if(distance>2000 && distance <5000)
        		gl.glColor4f(0.8f,0.4f,0.3f,1.0f);
        	if(distance>5000 && distance <8000)
        		gl.glColor4f(0.8f,0.6f,0.3f,1.0f);
        	if(distance>8000 && distance <11000)
        		gl.glColor4f(0.8f,0.8f,0.3f,1.0f);
        	if(distance>11000)
        		gl.glColor4f(0.8f,1.0f,0.3f,1.0f);
        	*/
        	gl.glBegin(GL2.GL_TRIANGLES);	
	        	gl.glVertex3f(loader.v.get(loader.f.get(i).getP1()-1).getX(), loader.v.get(loader.f.get(i).getP1()-1).getZ(), loader.v.get(loader.f.get(i).getP1()-1).getY());
	        	gl.glVertex3f(loader.v.get(loader.f.get(i).getP2()-1).getX(), loader.v.get(loader.f.get(i).getP2()-1).getZ(), loader.v.get(loader.f.get(i).getP2()-1).getY());
	        	gl.glVertex3f(loader.v.get(loader.f.get(i).getP3()-1).getX(), loader.v.get(loader.f.get(i).getP3()-1).getZ(), loader.v.get(loader.f.get(i).getP3()-1).getY());
        	gl.glEnd();
        	
        	/*if(linesOn)
        	{
	        	gl.glColor4f(0.2f, 0.6f, 0.7f,1);
	        	gl.glBegin(GL2.GL_LINES);
		        	gl.glVertex3f(loader.v.get(loader.f.get(i).getP1()-1).getX(), loader.v.get(loader.f.get(i).getP1()-1).getZ(), loader.v.get(loader.f.get(i).getP1()-1).getY());
		        	gl.glVertex3f(loader.v.get(loader.f.get(i).getP2()-1).getX(), loader.v.get(loader.f.get(i).getP2()-1).getZ(), loader.v.get(loader.f.get(i).getP2()-1).getY());
	        	gl.glEnd();
	        	
	        	gl.glBegin(GL2.GL_LINES);
		        	gl.glVertex3f(loader.v.get(loader.f.get(i).getP2()-1).getX(), loader.v.get(loader.f.get(i).getP2()-1).getZ(), loader.v.get(loader.f.get(i).getP2()-1).getY());
		        	gl.glVertex3f(loader.v.get(loader.f.get(i).getP3()-1).getX(), loader.v.get(loader.f.get(i).getP3()-1).getZ(), 	loader.v.get(loader.f.get(i).getP3()-1).getY());
	        	gl.glEnd();
	        
	        	gl.glBegin(GL2.GL_LINES);
		        	gl.glVertex3f(loader.v.get(loader.f.get(i).getP1()-1).getX(), loader.v.get(loader.f.get(i).getP1()-1).getZ(), loader.v.get(loader.f.get(i).getP1()-1).getY());
		        	gl.glVertex3f(loader.v.get(loader.f.get(i).getP3()-1).getX(), loader.v.get(loader.f.get(i).getP3()-1).getZ(), loader.v.get(loader.f.get(i).getP3()-1).getY());
	        	gl.glEnd();
        	}*/
        	
        }
        gl.glFlush();
    }
    
    private void update()
    {
    	 if(forward)
    		 camera.moveCamera(new Vector3(0,0,200));
    	 if(backward)
    		 camera.moveCamera(new Vector3(0,0,-200));
    	 if(left)
    		 camera.moveCamera(new Vector3(200,0,0));
    	 if(right)
    		 camera.moveCamera(new Vector3(-200,0,0));
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
        glu.gluLookAt(	0,	100,	0,
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
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
        gl.glClearDepth(1.0f);      // set clear depth value to farthest
        gl.glEnable(GL2.GL_DEPTH_TEST); // enables depth testing
        gl.glDepthFunc(GL2.GL_LEQUAL);  // the type of depth test to do
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); // best perspective correction
        gl.glShadeModel(GL2.GL_SMOOTH); // blends colors nicely, and smoothes out lighting
        ((Component) gLDrawable).addKeyListener(this);
        //initLight(gl);
        float 	xx = loader.v.get(0).getX(),
         		yy = loader.v.get(0).getY(),
         		zz = loader.v.get(0).getZ();
        camera = new Camera(xx,zz,yy,xx,zz,yy+1000);
    }
    
    public void initLight( GL2 gl)
    {
      // First Switch the lights on.
      gl.glEnable( GL2.GL_LIGHTING );
      gl.glEnable( GL2.GL_LIGHT1 );

      float z = loader.v.get(0).getZ();
      z+=100;
      float[] lightPos = {loader.v.get(0).getX(),z,loader.v.get(0).getY(),1};
      float[] lightColorAmbient = {0.7f, 0.6f, 0.4f, 1f};
      float[] lightColorSpecular = {0.01f, 0.01f, 0.04f, 1f};
      
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

	private float getDistance(Vector3 a,  Vector3 b)
    {
 	   return (float) 
 			   Math.sqrt(
 			   Math.pow(a.x() - b.x(),2)+
 			   Math.pow(a.z() - b.z(),2)
 			   );
    }
	
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