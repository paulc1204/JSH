
import java.io.*; 
import java.util.HashSet; 


public class Uniq implements Application<PrintWriter> {

    private String fileName;
    
    @Override
    public void exec(ArrayList<String> args, BufferedReader input, PrintWriter writer) throws IOException {
        if (args.isEmpty()) {
            throw new RuntimeException("uniq: missing arguments");
        }
        if (args.size() != 1) {
            throw new RuntimeException("uniq: wrong arguments");
        }

        fileName = Jsh.currentDirectory + File.separator + args.get(0);
        PrintWriter pw = new PrintWriter("output.txt"); 

		BufferedReader br = new BufferedReader(new FileReader("input.txt")); 
		
		String line = br.readLine(); 
		
		HashSet<String> hs = new HashSet<String>(); 

        try(Stream<String> lines = Files.lines(Paths.get(fileName))){
            lines.sorted().forEach(s -> {
                try {
                    if(hs.add(line)) 
				        pw.println(line); 
			        line = br.readLine(); 
                } catch (IOException e) {
                    
                }      
            });
        }
        pw.flush(); 
		
		br.close(); 
		pw.close(); 
    }