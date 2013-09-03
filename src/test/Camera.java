package test;

import Geometry.*;

public class Camera {

	public Vector3 eyePos;
	public Vector3 targetPos;
	
	public Camera(float x, float y, float z, float tx, float ty, float tz)
	{
		eyePos = new Vector3(x,y,z);
		targetPos = new Vector3(tx,ty,tz);
	}
	
	public void moveCamera(Vector3 moveVector)
	{
		eyePos.add(moveVector);
	}
}
