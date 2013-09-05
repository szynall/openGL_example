package test;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Geometry.Triangle;
import Geometry.Vector3;



public class LoaderOBJ 
{
	public ArrayList<Vector3>	v = new ArrayList<Vector3>();//Lista punktów
    public ArrayList<Triangle>	f = new ArrayList<Triangle>();	//Lista trójk¹tów
    public ArrayList<Vector3>   n = new ArrayList<Vector3>();//Lista normalnych
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
			
			int cnt = 0;
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
					p2=v.get(Integer.parseInt(splitData[2])-1);
					p3=v.get(Integer.parseInt(splitData[3])-1);
					
					/*
					Vector3 n1,n2,n3, v1,v2;
					v1 = p2.sub(p1);
					v2 = p3.sub(p1);
					n1 = v1.cross(v2);	
					if (n1.z()<0)
						n1=n1.neg();
					n1 = n1.normalize();
					n1.vid = Integer.parseInt(splitData[1])-1;
					n1.nr = cnt;
					cnt++;
					
					//p2
					v1 = p1.sub(p2);
					v2 = p3.sub(p2);
					n2 = v1.cross(v2);	
					if (n2.z()<0)
						n2=n2.neg();
					n2 = n2.normalize();
					n2.vid = Integer.parseInt(splitData[2])-1;
					n2.nr = cnt;
					cnt++;
					
					//p3
					v1 = p2.sub(p3);
					v2 = p1.sub(p3);
					n3 = v1.cross(v2);	
					if (n3.z()<0)
						n3=n3.neg();
					n3 = n3.normalize();
					n3.vid = Integer.parseInt(splitData[3])-1;
					n3.nr = cnt;
					cnt++;
					*/
					f.add(new Triangle(p1,p2,p3));	
					//n.add(n1);
					//n.add(n2);
					//n.add(n3);
				}
				if (splitData[0].equals("vn")) 
				{ 
					n.add(new Vector3( Float.parseFloat( splitData[1]),Float.parseFloat( splitData[2]),Float.parseFloat( splitData[3])));
				}
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		/*
		Comparator<Vector3> cmp = new Comparator<Vector3>() {
            @Override
            public int compare(Vector3 o1, Vector3 o2) {
                return o1.vid - o2.vid;
            }

        };
        
		ArrayList<Vector3> nCopy = new ArrayList<Vector3>(n);
		Collections.sort(nCopy,cmp);
		Vector3 sumNormal;
		ArrayList<Integer>	normalSumList = new ArrayList<Integer>();
		for (int i = 0; i < 200; i++) 
		{
			for (int j = 0; j < 200; j++) 
			{
				sumNormal = new Vector3();

				if (nCopy.size()!=0)
				{
					while(nCopy.get(0).vid == i*200+j)
					{
						normalSumList.add(nCopy.get(0).nr);
						sumNormal.add(nCopy.get(0));
						nCopy.remove(0);
						if (nCopy.size()==0)
							break;
					}
					for (Integer cnt: normalSumList)
						n.set(cnt, sumNormal.normalize());
					normalSumList.clear();
				}
			}
		}
		
		FileWriter fstream = null;
		try {
			fstream = new FileWriter(System.getProperty("user.dir")+"\\normal.txt");
		BufferedWriter out = new BufferedWriter(fstream);
		for (Vector3 normal: n)
			out.write("vn "+normal.toString()+"\n");
		out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
