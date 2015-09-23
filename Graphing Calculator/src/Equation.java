import java.util.List;

public class Equation {
	 
	private int Max = 0;
	private int Min = 0;
	private String Eq = null;
	
	
	public Equation(String Equ, int Ma, int Mi)
	{
		this.Eq = Equ;
		this.Max = Ma;
		this.Min = Mi;
		
	}
	
	public List<Integer> Solve ()
	{
		
		
		for(int i = Min; i <= Max; i+=1)
		{
			for(int s = 0; s < Eq.length(); s++)
			{
				int c = 0;
				int y = 0;
				if((Eq.substring(s, s+1).equals("X")))
						{
							if(s != 0)
							{
								if(!((Eq.substring(s-1, s)).equals("/")) || ((Eq.substring(s-1, s)).equals("+")) || ((Eq.substring(s-1, s)).equals("*")) || ((Eq.substring(s-1, s)).equals("(")) || (Eq.substring(s-1, s).equals("-")) || ((Eq.substring(s+1, s+2)).equals("^")))
								{
									if(Eq.substring(s-2, s-1).equals("_"))
									{
										int x = (Integer.parseInt(Eq.substring(s-1, s)));
										y =(-1)*x*i;
									}
									else
									{
										int x = (Integer.parseInt(Eq.substring(s-1, s)));
										y = x*i;
									}
								
								}
							}
							
						}
				if((Eq.substring(s, s+1).equals("+")))
				{
					while((c+s+1)< Eq.length())
							{
								c++;
							}
					y=+ (Integer.parseInt(Eq.substring(s+1, s+2+c)));
				}
				
				if((Eq.substring(s, s+1).equals("-")))
				{
					while((c+s+1)< Eq.length())
							{
								c++;
							}
					y=- (Integer.parseInt(Eq.substring(s+1, s+2+c)));
				}
			}
		}
		return null;
		
	}
	
	

}
