package interpreterL;

import interpreterL.parser.StreamTokenizer;
import interpreterL.parser.*;
import interpreterL.parser.ast.Prog;
import interpreterL.visitors.evaluation.Eval;
import interpreterL.visitors.evaluation.EvaluatorException;
import interpreterL.visitors.typechecking.TypeCheck;
import interpreterL.visitors.typechecking.TypecheckerException;

import java.io.*;

import static java.lang.System.err;

public class Interpret {
    public static void main(String[] args) throws IOException {

        FileInputStream in =  null;
        FileOutputStream output = null;
        File r = null;
        boolean tc = true;


        for(int i = 0; i < args.length; ++i ){

            if(args[i].equals("-ntc"))
                tc = false;

            else if(args[i].equals("-i")){

                if(i+1 > args.length || !args[i+1].endsWith(".txt"))
                    throw new IllegalArgumentException();

                r = new File(args[i+1]);
                try {
                    in = new FileInputStream(r);
                }catch(IOException ex){ throw new IllegalArgumentException();}

            }
            else if(args[i].equals("-o")){
                if(i+1 > args.length || !args[i+1].endsWith(".txt"))
                    throw new IllegalArgumentException();
                output = new FileOutputStream(args[i+1]);
            }


        }
        if (!(r==(null))){
            System.out.println("Interpreting file: " + r.getName());
        }
        Reader input;
        if(in != null) input = new InputStreamReader(in);
        else input = new InputStreamReader(System.in);

        try (Tokenizer tokenizer = new StreamTokenizer(input)) {
            Parser parser = new MyParser(tokenizer);
            Prog prog = parser.parseProg();
            if(tc)
                prog.accept(new TypeCheck());
            if(output!= null) {
                PrintWriter print = new PrintWriter(output);
                prog.accept(new Eval(print));
                print.close();
            }
            else
                prog.accept(new Eval());
        } catch (TokenizerException e) {
            err.println("Tokenizer error: " + e.getMessage());
        } catch (ParserException e) {
            err.println("Syntax error: " + e.getMessage());
        } catch (TypecheckerException e) {
            err.println("Static error: " + e.getMessage());
        } catch (EvaluatorException e) {
            err.println("Dynamic error: " + e.getMessage());
        } catch (Throwable e) {
            err.println("Unexpected error.");
            e.printStackTrace();
        }
    }
}
