package test;


public class Triangle {
   private int p1;
   private int p2;
   private int p3;
   
   public Triangle() { }

   public Triangle(int p1, int p2, int p3) 
   {
          this.setP1(p1);
          this.setP2(p2);
          this.setP3(p3);
   }
   

	public int getP1() {
		return p1;
	}

	public void setP1(int p1) {
		this.p1 = p1;
	}

	public int getP2() {
		return p2;
	}

	public void setP2(int p2) {
		this.p2 = p2;
	}

	public int getP3() {
		return p3;
	}

	public void setP3(int p3) {
		this.p3 = p3;
	}
       
      
}