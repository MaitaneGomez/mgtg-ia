package es.deusto.ingenieria.aike.maze;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


import es.deusto.ingenieria.aike.xml.InformationXMLReader;

public class BoardXMLReader extends InformationXMLReader 
{

	private int rows;
	private int columns;
	private int carRow;
	private int carColumn;
	private int flagRow;
	private int flagColumn;
	private boolean flagUP ;
	private boolean flagDOWN;
	private boolean flagRIGHT;
	private boolean flagLEFT;
	private Tile[][] initialTiles;
	private Tile initialCar;


	public BoardXMLReader(String xmlFile) 
	{
		super(xmlFile);
	}
	
	public Object getInformation() 
	{
		//esto es para poner el flag en su sitio y crear la posicion inicial del coche
		
		
		this.initialTiles[flagRow-1][flagColumn-1] = new Tile("F", flagRow, flagColumn, flagUP, flagDOWN, flagRIGHT, flagLEFT);
		this.initialCar = new Tile(carRow, carColumn);
		
		
		//estos for son para crear las casillas de circulos
		for(int i = 0 ; i < this.rows; i++)
		{
			for(int j = 0; j< this.columns; j++)
			{
				if(initialTiles[i][j] == null)
				{
					//creamos el tile de circulo
					initialTiles[i][j]= new Tile("O", i+1, j+1);
				}
			}
		}
		
		return new Board(this.initialTiles, this.initialCar);
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
	{
		
		try 
		{		
			if(qName.equals("aike:maze"))
			{
				this.rows = Integer.parseInt(attributes.getValue("rows"));
				this.columns = Integer.parseInt(attributes.getValue("columns"));
				this.initialTiles = new Tile[rows][columns];
			}
			else if(qName.equals("aike:car"))
				{
					this.carRow = Integer.parseInt(attributes.getValue("row"));
					this.carColumn = Integer.parseInt(attributes.getValue("column"));
				}
				else if(qName.equals("aike:flag"))
					{
						this.flagRow = Integer.parseInt(attributes.getValue("row"));
						this.flagColumn = Integer.parseInt(attributes.getValue("column"));
						
						if(attributes.getValue("top-wall").equals("yes"))
							this.flagUP = true;
						else
							this.flagUP = false;
						
						if(attributes.getValue("bottom-wall").equals("yes"))
							this.flagDOWN = true;
						else
							this.flagDOWN = false;
						
						if(attributes.getValue("right-wall").equals("yes"))
							this.flagRIGHT = true;
						else
							this.flagRIGHT = false;
						
						if(attributes.getValue("left-wall").equals("yes"))
							this.flagLEFT = true;
						else
							this.flagLEFT = false;
					}
					else if (qName.equals("aike:cross"))
					{
						int tempRow = Integer.parseInt(attributes.getValue("row"));
						int tempColumn = Integer.parseInt(attributes.getValue("column"));
						this.initialTiles[tempRow-1][tempColumn-1] = new Tile("X",tempRow, tempColumn);
					}
		} 
		catch (Exception ex) 
		{
			System.out.println(this.getClass().getName() + ".startElement(): " + ex);
		}
	}
}