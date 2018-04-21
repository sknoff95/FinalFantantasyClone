Battle.Java has the main method. This pretty much exclusively will just place characters and enemies on the screen (represented as VectorObject boxes). If you go into BattleScreen.Java you can change the length of the "characters" and "enemies" arrays to see the scalability (limited only by the size of the sprites themselves). At their current size, they overlap whenever there are more than 8 objects on one side.

You'll need to have the whole javagames.util package from the book to run this.
