package es.deusto.ingenieria.aike.maze;

import es.deusto.ingenieria.aike.formulation.Operator;
import es.deusto.ingenieria.aike.formulation.State;

public class CarMovement extends Operator {

	public static enum Destination 
	{
		UP,
		DOWN,
		RIGHT,
		LEFT;
	}
	
	private Destination detination;
	
	public CarMovement(Destination detination) 
	{
		super("Move car '" + detination.toString() + "'");
		this.detination = detination;
	}
	
	protected boolean isApplicable(State state) //mequeda mirar que no valla para atras y que no este fuera
	{
		Board board = (Board)state.getInformation();	
		Tile currentTile= board.getCar();
		
	
		switch (this.detination) 
		{
			case UP:
			{
				//cuando me puedo mover hacia arriba???
				//cuando la casilla de encima de la que estoy no se sale de los bordes
				//y dependiendo de si la casilla en la que estoy es una X � una O 
				//si es una X tengo que mirar de donde venia 
				
				
				
				//se compara con ! 1 debido a que se sta comparando con la vida real
				if((currentTile.getRow() > 1) && (currentTile.getColumn() >= 1) && (currentTile.getColumn() <= board.getTiles()[0].length)) 
				{
				
					//la segunda y tercera condicion es para no hacer el primer movimiento fuera del tablero
					//significa que aunque me mueva una posicion hacia arriba, no me salgo del tablero
					//ahora compruebo que la casilla de encima no tiene una pared en su parte baja
					
					if(board.getTile(currentTile.getRow()-1, currentTile.getColumn()).isDown_wall())
					{
						//tiene una pared por lo que no me puedo mover hacia arriba
						System.out.println("UP false");
						return false;
					}
					else
					{
						//no tiene una pared, podria moverme, pero tengo que comparar las reglas del juego
						Tile previousTile = board.getPreviousTile();
						
						
						if(currentTile.getType().equals("X"))
						{
							//estoy en una x por lo que tengo que hacer un giro de 90 grados
							//compruebo si vengo de la misma fila o de la misma columna
							
							if(currentTile.getRow() == previousTile.getRow())
							{
								//estoy en la misma fila por lo que me he desplazado de columna
								//y si, me podria mover hacia arriba
								System.out.println("UP true");
								 return true;
							}
							else
							{
								//las columnas son iguales; estoy en la misma columna me he cambiado de fila
								//y no me podria mover hacia arriba, solo izquierda o derecha
								System.out.println("UP false");
								return  false;
							}
						}
						else
						{
							//es una O
							//en ese caso, solo me puedo mover hacia arriba si las columnas de curretn y previous coinciden
							
							//la segunda parte de la comprobacion es para comprobar que no volvamos para atras
							if((currentTile.getColumn() == previousTile.getColumn()) && (currentTile.getRow()-1 != previousTile.getRow()))
							{
								System.out.println("UP true");
								return  true;
							}
							else
							{
								System.out.println("UP false");
								return false;
							}
						}
					}
				}
				else
				{
					//como me mueva para arriba, me salgo del tablero...
					System.out.println("UP false");
					return false;
				}
			}
			
			case DOWN:
			{
				//cuando me puedo mover hacia abajo???
				//cuando la casilla de debajo de la que estoy no se sale de los bordes
				//y dependiendo de si la casilla en la que estoy es una X � una O 
				//si es una X tengo que mirar de donde venia 
				
				
				if((currentTile.getRow() < board.getTiles().length) && (currentTile.getColumn() >= 1) && (currentTile.getColumn() <= board.getTiles()[0].length))
				{
				
					//significa que aunque me mueva una posicion hacia abajo, no me salgo del tablero
					//ahora compruebo que la casilla de debajo no tiene una pared en su parte superior
					
					if(board.getTile(currentTile.getRow()+1, currentTile.getColumn()).isUp_wall())
					{
						//tiene una pared por lo que no me puedo mover hacia abajo
						System.out.println("DOWN false");
						return false;
					}
					else
					{
						//no tiene una pared, podria moverme, pero tengo que comparar las reglas del juego
						Tile previousTile = board.getPreviousTile();
						
						if(currentTile.getType().equals("X"))
						{
							//estoy en una x por lo que tengo que hacer un giro de 90 grados
							//compruebo si vengo de la misma fila o de la misma columna
							
							if(currentTile.getRow() == previousTile.getRow())
							{
								//estoy en la misma fila por lo que me he desplazado de columna
								//y si, me podria mover hacia arriba
								System.out.println("DOWN true");
								 return true;
							}
							else
							{
								//las columnas son iguales; estoy en la misma columna me he cambiado de fila
								//y no me podria mover hacia abajo, solo izquierda o derecha
								System.out.println("DOWN false");
								return  false;
							}
						}
						else
						{
							//es una O
							//en ese caso, solo me puedo mover hacia arriba si las columnas de curretn y previous coinciden
							
							//la segunda parte de la comprobacion es para comprobar que no volvamos para atras
							if((currentTile.getColumn() == previousTile.getColumn()) && (currentTile.getRow()+1 != previousTile.getRow()))
							{
								System.out.println("DOWN true");
								return  true;
							}
							else
							{
								System.out.println("DOWN false");
								return false;
							}
						}
					}
				}
				else
				{
					//como me mueva para abajo, me salgo del tablero...
					System.out.println("DOWN false");
					return false;
				}
			}
			case RIGHT:
			{
				//cuando me puedo mover hacia la derecha???
				//cuando la casilla a la derecha de la que estoy no se sale de los bordes
				//y dependiendo de si la casilla en la que estoy es una X � una O 
				//si es una X tengo que mirar de donde venia 

				
				if((currentTile.getColumn() < board.getTiles()[0].length) && (currentTile.getRow() >= 1) && (currentTile.getRow() <= board.getTiles().length))
				{
				
					//significa que aunque me mueva una posicion hacia la derecha, no me salgo del tablero
					//ahora compruebo que la casilla de mi derecha no tiene una pared en su parte izquierda
					
					if(board.getTile(currentTile.getRow(), currentTile.getColumn()+1).isLeft_wall())
					{
						//tiene una pared por lo que no me puedo mover hacia la derecha
						System.out.println("RIGHT false");
						return false;
					}
					else
					{
						//no tiene una pared, podria moverme, pero tengo que comparar las reglas del juego
						Tile previousTile = board.getPreviousTile();
						
						if(currentTile.getType().equals("X"))
						{
							//estoy en una x por lo que tengo que hacer un giro de 90 grados
							//compruebo si vengo de la misma fila o de la misma columna
							
							if(currentTile.getRow() == previousTile.getRow())
							{
								//estoy en la misma fila por lo que me he desplazado de columna
								//y no me podria mover a la derecha
								System.out.println("RIGHT false");
								 return false;
							}
							else
							{
								//las columnas son iguales; estoy en la misma columna me he cambiado de fila
								//y n me podria mover hacia la derecha
								System.out.println("RIGHT true");
								return  true;
							}
						}
						else
						{
							//es una O
							//en ese caso, solo me puedo mover hacia la derecha si las filas de curretn y previous coinciden
							
							if((currentTile.getRow() == previousTile.getRow()) && (currentTile.getColumn()+1 != previousTile.getColumn()))
							{
								System.out.println("RIGHT true");
								return  true;
							}
							else
							{
								System.out.println("RIGHT false");
								return false;
							}
						}
					}
				}
				else
				{
					//como me mueva para la derecha, me salgo del tablero...
					System.out.println("RIGHT false");
					return false;
				}
			}
			case LEFT:
			{
				//cuando me puedo mover hacia la izquierda???
				//cuando la casilla a la izuierda de la que estoy no se sale de los bordes
				//y dependiendo de si la casilla en la que estoy es una X � una O 
				//si es una X tengo que mirar de donde venia 

				
				if((currentTile.getColumn() > 1) && (currentTile.getRow() >= 1) && (currentTile.getRow() <= board.getTiles().length))
				{
				
					//significa que aunque me mueva una posicion hacia la izquierda, no me salgo del tablero
					//ahora compruebo que la casilla de mi izquierda no tiene una pared en su parte derecha
					
					if(board.getTile(currentTile.getRow(), currentTile.getColumn()-1).isRight_wall())
					{
						//tiene una pared por lo que no me puedo mover hacia la izquierda
						System.out.println("LEFT false");
						return false;
					}
					else
					{
						//no tiene una pared, podria moverme, pero tengo que comparar las reglas del juego
						Tile previousTile = board.getPreviousTile();
						
						if(currentTile.getType().equals("X"))
						{
							//estoy en una x por lo que tengo que hacer un giro de 90 grados
							//compruebo si vengo de la misma fila o de la misma columna
							
							if(currentTile.getRow() == previousTile.getRow())
							{
								//estoy en la misma fila por lo que me he desplazado de columna
								//y no me podria mover a la izquierda
								System.out.println("LEFT false");
								 return false;
							}
							else
							{
								//las columnas son iguales; estoy en la misma columna me he cambiado de fila
								//y n me podria mover hacia la izquierda
								System.out.println("LEFT true");
								return  true;
							}
						}
						else
						{
							//es una O
							//en ese caso, solo me puedo mover hacia la derecha si las filas de curretn y previous coinciden
							
							if((currentTile.getRow() == previousTile.getRow()) && (currentTile.getColumn()-1 != previousTile.getColumn()))
							{
								System.out.println("LEFT true");
								return  true;
							}
							else
							{
								System.out.println("LEFT false");
								return false;
							}
						}
					}
				}
				else
				{
					//como me mueva para la izquierda, me salgo del tablero...
					System.out.println("LEFT false");
					return false;
				}
			}
			default:
				System.out.println("NO ES NINGUNO DE LOS ANTERIORES");
				return false;
		}
	}

	protected State effect(State state) //ME HE QUEDADO AQUI...
	{
		Board board = (Board)state.getInformation(); //cogemos la situacion actual
		Board newBoard = (Board)board.clone();	//clonamos la situacion actual en un nuevo board
		
		Tile currentTile= board.getCar();
		
		switch (this.detination) 
		{
			case UP:
			{
				
				//quiero mover el coche a la tile que esta encima mio. por eso primero la cogemos
				Tile destinationTile = newBoard.getTile((currentTile.getRow()-1), currentTile.getColumn());
				newBoard.moveCar(destinationTile);
				break;
			}
			case DOWN:
			{
				//quiero mover el coche a la tile que esta debajo mio. por eso primero la cogemos
				Tile destinationTile = newBoard.getTile(currentTile.getRow()+1, currentTile.getColumn());
				newBoard.moveCar(destinationTile);
				break;
			}
			case RIGHT:
			{
				//quiero mover el coche a la tile que esta a mi derecha. por eso primero la cogemos
				Tile destinationTile = newBoard.getTile(currentTile.getRow(), currentTile.getColumn()+1);
				newBoard.moveCar(destinationTile);
				break;
			}
			case LEFT:
			{
				//quiero mover el coche a la tile que esta a mi izquierda. por eso primero la cogemos
				Tile destinationTile = newBoard.getTile(currentTile.getRow(), currentTile.getColumn()-1);
				newBoard.moveCar(destinationTile);
				break;
			}

		}

		return new State(newBoard);
	}
}