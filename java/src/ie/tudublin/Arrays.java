package ie.tudublin;

import processing.core.PApplet;



public class Arrays extends PApplet
{
	int mode = 0;

	String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

	float[] rainfall = {200, 260, 300, 150, 100, 50, 10, 40, 67, 160, 400, 420};

	int rainfallMax = 500;
	
	public float map1(float a, float b, float c, float d, float e)
	{
		float r1 = c -b;
		float r2 = e - d;

		float howFar = a - b;
		return d + (howFar / r1) * r2;
	}

	void randomize()
	{
		for (int i = 0; i < rainfall.length; i++) {
			rainfall[i] = random(rainfallMax);
		}
	}

	public void settings()
	{
		size(500,500);
		//fullScreen();

		for(int i = 0; i < months.length; i ++)
		{
			println("Month: " + months[i] + "\t" + rainfall[i]);
		}

		int minIndex = 0;
		for(int i= 0 ; i < rainfall.length ; i ++)
		{
			if (rainfall[i] < rainfall[minIndex])
			{
				minIndex = i;
			}
		}
		
		int maxIndex = 0;
		for(int i= 0 ; i < rainfall.length ; i ++)
		{
			if (rainfall[i] > rainfall[maxIndex])
			{
				maxIndex = i;
			}
		}

		println("The month with the minimum rainfall was " + months[minIndex] + " with " + rainfall[minIndex] + "rain");
		println("The month with the max rainfall was " + months[maxIndex] + " with " + rainfall[maxIndex] + "rain");
		
		
		float tot = 0;
		for(float f:rainfall)
		{
			tot += f;
		}

		float avg = tot / (float) rainfall.length;

		// a b-c d-e;
		println(map1(5, 0, 10, 0, 100));
		// 50

		println(map1(25, 20, 30, 200, 300));
		// 250

		println(map1(26, 25, 35, 0, 100));
		// 10 


	}

	public void setup() {
		colorMode(HSB);
		randomize();
	}

	
	public void draw()
	{	
		switch(mode)
		{
			case 0:
				background(0);
				stroke(255);
				line((width/months.length), height - months.length * 3, (width/months.length), (height/months.length));
				line(width - months.length * 3, height - months.length * 3, (width/months.length), height - months.length * 3);
				// float w = width / (float)months.length;
		
				for(int i = 0 ; i < months.length;  i ++)
				{
					textSize(width/30.0f);
					fill(255,0,255);
					text(46*i, width/100.0f, ((height - months.length * 3) - (i*(height/months.length)/1.1f)));
					textSize(width/32.0f);
					text(months[i], ((width / months.length) + (i*(width/months.length))/1.15f), height-months.length);
					float x = map1(i, 0, months.length, 0.0f, ((width - months.length * 3)-(width/months.length)));
					fill(i*22, 255, 255);
					rect(x+((width/months.length)), height - months.length * 3, ((width - months.length * 3)-(width/months.length))/months.length, map1(rainfall[i], 0, rainfallMax, 0, -((height - months.length * 3)-(height/months.length))));
				}
				break;

			case 1:
				background(0);
				stroke(255);
				line((width/months.length), height - months.length * 3, (width/months.length), (height/months.length));
				line(width - months.length * 3, height - months.length * 3, (width/months.length), height - months.length * 3);
				for(int i = 0 ; i < months.length;  i ++)
				{
					textSize(width/30.0f);
					fill(255,0,255);
					text(46*i, width/100.0f, ((height - months.length * 3) - (i*(height/months.length)/1.1f)));
					textSize(width/32.0f);
					text(months[i], ((width / months.length) + (i*(width/months.length))/1.15f), height-months.length);
				}
				for(int i = 1 ; i < months.length;  i ++)
				{
					float y = map1(rainfall[i - 1], 0, rainfallMax, 0, -((height - months.length * 3)-(height/months.length)));
					float x = map1(i - 1, 0, months.length, 0.0f, ((width - months.length * 3)-(width/months.length)));
					float nextx = map1(i, 0, months.length, 0.0f, ((width - months.length * 3)-(width/months.length)));
					float nexty = map1(rainfall[i], 0, rainfallMax, 0, -((height - months.length * 3)-(height/months.length)));
					float sizeOfbar = width/months.length;
					stroke(100,255,255);
					line(x+(sizeOfbar)*1.5f, (height - months.length * 3)+(y), (nextx)+(sizeOfbar)*1.5f,(height - months.length * 3)+(nexty));
				}
				break;
			default:
				// pass
				break;
		}
		
	} 

	public void keyPressed()
    {
        if (key >= '0' && key <= '9')
        {
            mode = key - '0';
        }
    }
}
