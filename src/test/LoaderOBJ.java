package test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Geometry.Triangle;
import Geometry.Vector3;



public class LoaderOBJ 
{
	public ArrayList<Vector3>	v = new ArrayList<Vector3>();//Lista punktów
    public ArrayList<Triangle>	f = new ArrayList<Triangle>();	//Lista trójk¹tów

	/**
	 * Load obj
	 * 
	 * @param name
	 *            of obj file
	 * @throws IOException 
	 */
    
	public LoaderOBJ( String name)
	{
		StringBuffer fileData = new StringBuffer();
		BufferedReader reader = null;
		char[] buf = new char[1024]; //max size of line
		int numRead = 0;
		Vector3 p1,p2,p3;
		reader = new BufferedReader(new InputStreamReader(Thread.currentThread()
									.getContextClassLoader()
									.getResourceAsStream(name)));
		try { // try to generate two Lists
				// read data to StringBuffer
			while ((numRead = reader.read(buf)) != -1) 
			{
				String readData = String.valueOf(buf, 0, numRead);
				fileData.append(readData);
			}
			reader.close();
			
			// split line by line
			String[] data = fileData.toString().split("(\\r?\\n)+");
			
			// split only by black character
			for (int j = 0; j < data.length; j++) 
			{
				String[] splitData = data[j].split("\\s");
				if (splitData[0].equals("v")) 
				{
					v.add(new Vector3(Float.parseFloat(splitData[1]),
							Float.parseFloat(splitData[2]),
							Float.parseFloat(splitData[3])));
				} 
				else if (splitData[0].equals("f")) 
				{ 
					p1=v.get(Integer.parseInt(splitData[1])-1);
					p2=v.get(Integer.parseInt(splitData[3])-1);
					p3=v.get(Integer.parseInt(splitData[2])-1);
					
					//p1
					Vector3 n1,n2,n3, v1,v2;
					v1 = p2.sub(p1);
					v2 = p3.sub(p1);
					n1 = v1.cross(v2);	
					if (n1.z()<0)
						n1=n1.neg();
					n1 = n1.normalize();
					//p2
					v1 = p1.sub(p2);
					v2 = p3.sub(p2);
					n2 = v1.cross(v2);	
					if (n2.z()<0)
						n2=n2.neg();
					n2 = n2.normalize();
					//p3
					v1 = p2.sub(p3);
					v2 = p1.sub(p3);
					n3 = v1.cross(v2);	
					if (n3.z()<0)
						n3=n3.neg();
					n3 = n3.normalize();
					f.add(new Triangle(p1,p2,p3,n1,n2,n3));				
				}
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
