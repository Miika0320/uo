import java.io.*;

public class SecretMessage {

    public static void encrypt( String inputFilem, String outputFile, int key ) throws IOException, FileNotFoundException {

        InputStreamReader input = null;
		OutputStreamWriter out = null;
        // YOUR CODE HERE (remove the exception)
		input = new InputStreamReader( new FileInputStream( inputFilem ) );
		out = new OutputStreamWriter(new FileOutputStream( outputFile )); 
		int c = 0;
		int x = 0;
		char[] buffer = new char[1000];
		while ( (c=input.read())!=-1){
			buffer[x] =(char) c;
			x++;
		}
		int tempUni;
		for (int i=0;i<buffer.length;i++){
			tempUni = (int)buffer[i];
			buffer[i] = (char) (tempUni + key);
		}
		String str = new String(buffer).trim();
		out.write(str);
		input.close();
		out.close();
    }
	
	public static void decrypt( String inputFilem, String outputFile, int key ) throws IOException, FileNotFoundException {
		InputStreamReader input = null;
		OutputStreamWriter out = null;
        // YOUR CODE HERE (remove the exception)
		input = new InputStreamReader( new FileInputStream( inputFilem ) );
		out = new OutputStreamWriter(new FileOutputStream( outputFile )); 
		int c = 0;
		int x = 0;
		char[] buffer = new char[1000];
		while ( (c=input.read())!=-1){
			buffer[x] =(char) c;
			x++;
		}
		int tempUni;
		for (int i=0;i<x;i++){
			tempUni = (int)buffer[i];
			buffer[i] = (char) (tempUni - key);
		}
		String str = new String(buffer).trim();
		out.write(str);
		input.close();
		out.close();

    }

    public static void main( String[] args ) {

        if ( args.length != 4 ) {
            System.out.println( "Usage: java SecretMessage [encrypt|decrypt] inputFile OutputFile key" );
            System.exit( 0 );
        }

		if(args[0].equals("encrypt")){

		
            try {
                encrypt( args[1],args[2], Integer.parseInt(args[3]));
            } catch ( FileNotFoundException e ) {
                System.err.println( "File not found: "+e.getMessage() );
            } catch (IOException e) {
                System.err.println( "Cannot read/write file: "+e.getMessage() );
            }
		}
		else if(args[0].equals("decrypt")){

		
            try {
                decrypt( args[1],args[2], Integer.parseInt(args[3]));
            } catch ( FileNotFoundException e ) {
                System.err.println( "File not found: "+e.getMessage() );
            } catch (IOException e) {
                System.err.println( "Cannot read/write file: "+e.getMessage() );
            }
        }
		else{
			System.out.println( "Usage: java SecretMessage [encrypt|decrypt] inputFile OutputFile key" );
            System.exit( 0 );
		}
        
    }
}