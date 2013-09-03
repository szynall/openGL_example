package test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class LoaderOBJ 
{
	public ArrayList<Point>		v = new ArrayList<Point>();			//Lista punktów
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
					v.add(new Point(j+1,Float.parseFloat(splitData[1]),
							Float.parseFloat(splitData[2]),
							Float.parseFloat(splitData[3])));
				} 
				else if (splitData[0].equals("f")) 
				{ 
						f.add(new Triangle(	Integer.parseInt(splitData[1]),
											Integer.parseInt(splitData[2]),
											Integer.parseInt(splitData[3])));
				}

			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
